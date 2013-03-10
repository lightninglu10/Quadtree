package tracker;

/** Class for the posts.
 * @author Patrick Lu
 */

public class Post {

    /** X value. */
    private double x;

    /** Y value. */
    private double y;

    /** Delta T. */
    private double t;

    /** Unique number for each post. */
    private static int num;

    /** Gives unique number. */
    private int unique;

    /** Constructor for the post.
     * @param x1 x value
     * @param y2 y value
     * @param dt pulse time*/
    public Post(double x1, double y2, double dt) {
        x = x1;
        y = y2;
        t = dt;
        num += 1;
        unique = num;
    }

    /** Return the x coordinate. */
    double getX() {
        return x;
    }

    /** Return the y coordinate. */
    double getY() {
        return y;
    }

    /** Return delta t. */
    double getT() {
        return t;
    }

    /** Returns the stringID. */
    int getID() {
        return unique;
    }
}
