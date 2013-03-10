package tracker;

import util.PointView;

/** Views Posts.
 * @author Patrick Lu */

public class PostView implements PointView<Post> {

    /** Return the x coordinate.
     * @param obj post object */
    public double getX(Post obj) {
        return obj.getX();
    }

    /** Return the y coordinate.
     * @param obj post object */
    public double getY(Post obj) {
        return obj.getY();
    }

    /** Return the delta T.
     * @param obj post object */
    public double getT(Post obj) {
        return obj.getT();
    }

    /** Return true iff P1 is equivalent to P2.
     * @param p1 Post
     * @param p2 Post */
    public boolean equals(Post p1, Post p2) {
        return p1.getID() == p2.getID();
    }
}
