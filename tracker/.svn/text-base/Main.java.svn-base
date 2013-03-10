package tracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import util.QuadTree;
import java.util.regex.Pattern;

/** Main class of the tracker program.
 * @author Patrick Lu
 */
public class Main {

    /** The main tracker program.  ARGS are as described in the
     *  project 2 handout:
     *      [--debug=N] [ INPUTFILE [ OUTPUTFILE ] ]
     */
    public static void main(String... args) {
        String inputFileName, outputFileName;
        inputFileName = outputFileName = null;
        int debug = 0;
        if (args.length == 1) {
            inputFileName = args[0];
        }
        if (args.length == 2 && args[0].length() >= 7
            && args[0].substring(0, 7).equals("--debug")) {
            debug = Integer.parseInt(args[0].substring(8));
            inputFileName = args[1];
        } else if (args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];
        }
        if (args.length == 3) {
            debug = Integer.parseInt(args[0].substring(8));
            inputFileName = args[1];
            outputFileName = args[2];
        }
        if (inputFileName != null) {
            try {
                System.setIn(new FileInputStream(inputFileName));
            } catch (FileNotFoundException e) {
                System.err.printf("Error: could not open %s%n", inputFileName);
                System.exit(1);
            }
        }
        if (outputFileName != null) {
            try {
                System.setOut(new PrintStream(outputFileName));
            } catch (FileNotFoundException e) {
                System.err.printf("Error: could not open %s%n",
                                   outputFileName);
                System.exit(1);
            }
        }
        parse();
        run();
        Simulator simulate = new Simulator(seed, maxd, ypos, velocity);
        Vehicle car = new Vehicle(ypos, velocity);
        pulses = simulate.pulsetimes(pulse, tmax);
        simulate.movement(pulses, car, set, tmax);
        System.out.close();
    }

    /** Keeps track of theta. */
    private static Double theta;

    /** Keeps track of position. */
    private static Double[] position;

    /** Array of Vx, Vy. */
    private static Double[] vxvy;

    /** HashMap of the pulse times. */
    private static TreeMap<Double, Double> pulses
        = new TreeMap<Double, Double>();

    /** List of pulse times. */
    private static ArrayList<Double> pulse = new ArrayList<Double>();

    /** Max length of simulation. */
    private static double tmax;

    /** Seed value. */
    private static double seed;

    /** Maximum distance an observation post can detect vehicle. */
    private static double maxd;

    /** Initial y-position. */
    private static double ypos;

    /** Velocity. */
    private static double velocity;

    /** Set of posts. */
    private static QuadTree<Post> set = new QuadTree<Post>(new PostView(), 3);

    /** Obtains the values parsed.
     */
    static void parse() {
        ArrayList<Double> hold = new ArrayList<Double>();
        ArrayList<Double> holdp = new ArrayList<Double>();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNext() && hold.size() < 5) {
            String current = inp.next();
            checkdub(current);
            if (current.indexOf("#") == -1) {
                hold.add(Double.parseDouble(current));
            } else if (current.indexOf("#") != -1
                       && current.indexOf("#") != 0) {
                hold.add(Double.parseDouble(current.substring(0,
                                                       current.indexOf("#"))));
                inp.nextLine();
            } else {
                inp.nextLine();
            }
        }
        if (hold.size() != 5) {
            System.err.println("Error: not enough doubles");
            System.exit(1);
        }
        if (hold.get(0) < 0) {
            tmax = 0.0;
        } else {
            tmax = hold.get(0);
        }
        seed = hold.get(1); maxd = Math.abs(hold.get(2));
        ypos = hold.get(3); velocity = hold.get(4);
        while (inp.hasNext()) {
            String current = inp.next();
            checkdub(current);
            if (holdp.size() == 3) {
                set.add(new Post(holdp.get(0), holdp.get(1), holdp.get(2)));
                if (holdp.get(2) < tmax && holdp.get(2) > 0.0) {
                    pulse.add(holdp.get(2));
                }
                if (holdp.get(2) < 0.0) {
                    System.err.println("Error, negative pulse time");
                    System.exit(1);
                }
                holdp = new ArrayList<Double>();
            }
            if (current.indexOf("#") == -1) {
                holdp.add(Double.parseDouble(current));
            } else if (current.indexOf("#") != -1
                       && current.indexOf("#") != 0) {
                hold.add(Double.parseDouble(
                         current.substring(0, current.indexOf("#"))));
                inp.nextLine();
            } else {
                inp.nextLine();
            }
        }
        postadd(holdp);
    }

    /** Checks if string is double.
     * @param current string to check */
    static void checkdub(String current) {
        if (!Pattern.matches("\\-?\\d+(\\.\\d+)?", current)
            && !current.equals("#") && !(current.indexOf("#") != -1)) {
            System.err.println("Not a double.");
            System.exit(1);
        }
    }

    /** Takes care of the post inclusion.
     * @param holdp arraylist of values for post. */
    static void postadd(ArrayList<Double> holdp) {
        if (holdp.size() == 3) {
            set.add(new Post(holdp.get(0), holdp.get(1), holdp.get(2)));
            if (holdp.get(2) < tmax && holdp.get(2) > 0.0) {
                pulse.add(holdp.get(2));
            }
            if (holdp.get(2) < 0.0) {
                System.err.println("Error, negative pulse time");
                System.exit(1);
            }
            holdp = new ArrayList<Double>();
        }
        if (holdp.size() != 0) {
            System.err.println("Error: not enough doubles");
            System.exit(1);
        }
    }

    /** Runs through the simulation. */
    static void run() {
        System.out.println("Simulation parameters: ");
        System.out.println("   Total simulated time: " + Double.toString(tmax));
        System.out.println("   Random seed: " + Integer.toString(
                                                (int) seed));
        System.out.println("   Maximum detection radius: "
                           + Double.toString(maxd));
        System.out.println("   Initial vertical location: "
                           + Double.toString(ypos));
        System.out.println("   Initial horizontal velocity of vehicle: "
                           + Double.toString(velocity));
    }


    /** Print brief description of the command-line format. */
    static void usage() {
        System.err.println("You need to have format such that it is"
                           + "[ --debug = N ] [ INPUTFILE [ OUTPUTFILE ] ].");
        System.exit(1);
    }
}
