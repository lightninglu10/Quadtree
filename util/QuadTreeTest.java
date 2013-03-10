package util;

import org.junit.Test;
import static org.junit.Assert.*;

import static util.UnitTest.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import tracker.Post;
import tracker.PostView;

/** Tests of SimpleSet2D class.
 *  @author */
public class QuadTreeTest {

    /** Test size. */
    @Test
    public void testSize() {
        QuadTree<double[]> S = new QuadTree<double[]>(VIEW, 4);
        LinkedList<double[]> list = new LinkedList<double[]>();
        assertEquals("Bad initial size", 0, S.size());
        ArrayList<double[]> box = new ArrayList<double[]>();
        double[] p = pnt(0.0, 0.0);
        S.add(PTS[0]);
        S.add(PTS[1]);
        S.add(PTS[2]);
        S.add(PTS[3]);
        S.add(PTS[4]);
        S.add(PTS[5]);
        S.add(PTS[6]);
        S.add(PTS[7]);
        S.add(PTS[8]);
        double[] o = new double[2];
        Iterator<double[]> iter = S.iterator();
        Iterator<double[]> boxiter = S.iterator(-1.0, -1.0, 1.0, 1.0);
        assertEquals("Iterator broken", true,
                     Arrays.toString(iter.next()).equals("[0.0, 0.0]"));
        assertEquals("Doesn't contain item", true, S.contains(PTS[0]));
        assertEquals("Doesn't contain item", true, S.contains(PTS[1]));
        assertEquals("Doesn't contain item", true, S.contains(PTS[2]));
        assertEquals("Doesn't contain item", true, S.contains(PTS[3]));
        assertEquals("Bad non-empty size", 9, S.size());
        //S.remove(PTS[1]);
        //assertEquals("Doesn't contain item", false, S.contains(PTS[1]));
        //assertEquals("Bad size after removal", 3, S.size());
        for (double[] q : PTS) {
            System.out.println(Arrays.toString(q));
            S.add(q);
        }
        assertEquals("Doesn't contain item 0", true, S.contains(PTS[0]));
        assertEquals("Doesn't contain item 1", true, S.contains(PTS[1]));
        assertEquals("Doesn't contain item 2", true, S.contains(PTS[2]));
        assertEquals("Doesn't contain item 3", true, S.contains(PTS[3]));
        assertEquals("Doesn't contain item 4", true, S.contains(PTS[4]));
        assertEquals("Doesn't contain item 5", true, S.contains(PTS[5]));
        assertEquals("Doesn't contain item 6", true, S.contains(PTS[6]));
        assertEquals("Doesn't contain item 7", true, S.contains(PTS[7]));
        assertEquals("Doesn't contain item 8", true, S.contains(PTS[8]));
        assertEquals("Bad size after multiple insertions",
                     PTS.length, S.size());
        Post one = new Post(1.0, 2.0, 3.0);
        Post two = new Post(1.2, 2.2, 2.5);
        PostView view = new PostView();
        QuadTree<Post> Q = new QuadTree<Post>(view, 1);
        Q.add(one); Q.add(two); Q.add(one);
        assertEquals("Added two of the same element", 2, Q.size());
    }
}
