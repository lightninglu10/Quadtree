package tracker;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import util.QuadTree;
import java.util.Iterator;

/** Simulates the movement of the car.
 * @author Patrick Lu
 */

class Simulator {
    /** Seed value from main class. */
    private double seed;

    /** Max distance from main class. */
    private double maxd;

    /** Initial y-position from main class. */
    private double ypos;

    /** Velocity. */
    private double velocity;

    /** Chooser for turning. */
    private Chooser choose;

    /** Theta, for turning. */
    private double theta;

    /** Keeps track of position. */
    private Double[] pos;

    /** Array of Vx, Vy. */
    private Double[] vxvy;

    /** Constructor for simulator class.
     * @param seeds seed value
     * @param dis max distance
     * @param yp y position
     * @param vel velocity */
    public Simulator(double seeds, double dis, double yp, double vel) {
        seed = seeds;
        maxd = dis;
        ypos = yp;
        velocity = vel;
        choose = new Chooser(seed);
        theta = 0.0;
        pos = new Double[2];
        vxvy = new Double[2];
    }

    /** Gets position.
     * @return position */
    Double[] getpos() {
        return pos;
    }

    /** Gets vxvy.
     * @return vxvy */
    Double[] getvel() {
        return vxvy;
    }

    /** Creates an arraylist of all multiples up to max.
     * @param x pulse time
     * @param max time
     * @return result an arraylist of all times */
    static ArrayList<Double> multiples(double x, double max) {
        ArrayList<Double> result = new ArrayList<Double>();
        result.add(x);
        while (result.get(result.size() - 1) < max
               && !(result.get(result.size() - 1) + x > max)) {
            result.add(result.get(result.size() - 1) + x);
        }
        return result;
    }

    /** Creates a hashmap of times of pulses and how many posts pulse.
     * @param list an arraylist of pulse times
     * @param max the max time of simulation
     * @return result treemap that maps pulsetimes with number of pulses */
    TreeMap<Double, Double> pulsetimes(ArrayList<Double> list, double max) {
        TreeMap<Double, Double> result = new TreeMap<Double, Double>();
        ArrayList<ArrayList<Double>> mult = new ArrayList<ArrayList<Double>>();
        for (double i : list) {
            mult.add(multiples(i, max));
        }
        for (ArrayList<Double> x : mult) {
            for (double y : x) {
                if (result.containsKey(y)) {
                    result.put(y, result.get(y) + 1);
                } else {
                    result.put(y, (double) 1);
                }
            }
        }
        return result;
    }

    /** Distance formula.
     * @param x xpos
     * @param y ypos
     * @param x1 x1pos
     * @param y1 y1pos
     * @return c sqrt of numbers */
    double distance(double x, double y, double x1, double y1) {
        double a; double b; double c;
        a = Math.pow((x - x1), 2);
        b = Math.pow((y - y1), 2);
        c = Math.sqrt((a + b));
        return c;
    }

    /** Handles the movement of the car.
     * @param input a treemap input
     * @param car a vehicle
     * @param sets your quadtree set
     * @param tmax the max time */
    void movement(TreeMap<Double, Double> input, Vehicle car,
                  QuadTree<Post> sets, double tmax) {
        TreeMap<Integer, ArrayList<ArrayList<Double>>> hold =
            new TreeMap<Integer, ArrayList<ArrayList<Double>>>();
        TreeMap<Integer, String> posits = new TreeMap<Integer, String>();
        double timelast = 0; PostView view = new PostView();
        if (input.size() == 0) {
            car.position(car.getpos()[0], car.getpos()[1], tmax, 0);
        }
        for (Map.Entry<Double, Double> entry : input.entrySet()) {
            double key = entry.getKey(); double value = entry.getValue();
            pos = car.getpos(); vxvy = car.getvelo();
            car.position(pos[0], pos[1], key - timelast, theta);
            for (int i = 0; i < value; i += 1) {
                if (choose.choose(pos[0], pos[1], vxvy[0], vxvy[1]) == -1) {
                    theta += Math.PI / 4;
                } else {
                    theta -= Math.PI / 4;
                }
                car.changevel(theta);
            } timelast = key;
            Iterator<Post> iter = sets.iterator(pos[0] - maxd, pos[1] - maxd,
                                                pos[0] + maxd, pos[1] + maxd);
            while (iter.hasNext()) {
                Post posts = iter.next();
                ArrayList<ArrayList<Double>> holder =
                    new ArrayList<ArrayList<Double>>();
                ArrayList<Double> viewing = new ArrayList<Double>();
                if (distance(pos[0], pos[1], view.getX(posts),
                             view.getY(posts)) <= maxd) {
                    viewing.add(pos[0]); viewing.add(pos[1]); viewing.add(key);
                    for (int i = 0; i < value; i += 1) {
                        holder.add(viewing);
                    }
                    String a = " at (" + String.format("%.1f", view.getX(posts))
                        + ", " + String.format("%.1f", view.getY(posts)) + "):";
                    posits.put(posts.getID(), a);
                    if (hold.containsKey(posts.getID())) {
                        holder = hold.get(posts.getID());
                        holder.add(viewing); hold.put(posts.getID(), holder);
                    } else {
                        hold.put(posts.getID(), holder);
                    }
                }
            }
        }
        if (input.size() != 0 && input.lastKey() != tmax) {
            car.position(car.getpos()[0], car.getpos()[1],
                         tmax - input.lastKey(), theta);
        }
        System.out.println("\nReports:");
        for (Map.Entry<Integer, ArrayList<ArrayList<Double>>> entry
                 : hold.entrySet()) {
            int key = entry.getKey();
            ArrayList<ArrayList<Double>> value = entry.getValue();
            System.out.println("   " + "Post " + "#" +  key + posits.get(key));
            for (ArrayList<Double> x : value) {
                printreport(x);
            }
        } printfinal(tmax, car);
    }

    /** Print out the reports.
     * @param x an arraylist of doubles */
    void printreport(ArrayList<Double> x) {
        System.out.println("      " + "("
                           + String.format("%.1f", x.get(0))
                           + ", " + String.format("%.1f", x.get(1))
                           + ")@" + String.format("%.1f", x.get(2)));
    }

    /** Print out final position.
     * @param tmax max time
     * @param car a vehicle */
    void printfinal(double tmax, Vehicle car) {
        System.out.println("\nFinal position: ("
                           + String.format("%.1f", car.getpos()[0]) + ", "
                           + String.format("%.1f", car.getpos()[1]) + ")@"
                           + String.format("%.1f", tmax));
    }
}
