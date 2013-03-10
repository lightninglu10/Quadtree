package tracker;

/** Simulates the Vehicle's movement.
 * @author Patrick Lu
 */

class Vehicle {
    /** Initial y-position from main class. */
    private double ypos;

    /** Velocity. */
    private double velocity;

    /** Position in an array. */
    private Double[] position;

    /** Components of velocity in an array. */
    private Double[] components;

    /** Constructor for a new vehicle.
     * @param y a double
     * @param velo a double */
    public Vehicle(double y, double velo) {
        ypos = y;
        velocity = velo;
        position = new Double[2];
        position[0] = 0.0;
        position[1] = ypos;
        components = new Double[2];
        components[0] = velocity;
        components[1] = 0.0;
    }

    /** Gets position.
     * @return position a position */
    Double[] getpos() {
        return position;
    }

    /** Gets velocity components.
     * @return components xy speed */
    Double[] getvelo() {
        return components;
    }

    /** Vehicle's current position.
     * @param x xpos
     * @param y ypos
     * @param time the time
     * @param theta angle */
    void position(double x, double y, double time, double theta) {
        x = x + components[0] * time;
        y = y + components[1] * time;
        position[0] = x;
        position[1] = y;
        components[0] = velocity * Math.cos(theta);
        components[1] = velocity * Math.sin(theta);
    }

    /** Changes the car's velocity.
     * @param theta the angle of change. */
    void changevel(double theta) {
        components[0] = velocity * Math.cos(theta);
        components[1] = velocity * Math.sin(theta);
    }
}
