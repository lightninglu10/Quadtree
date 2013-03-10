package tracker;

import org.junit.Test;
import static org.junit.Assert.*;
import ucb.junit.textui;
import util.QuadTree;
import java.util.ArrayList;
import java.util.TreeMap;

/** Central dispatching point for all testing.
 *  @author */
public class UnitTest {

    /** Run the JUnit tests in the tracker package. */
    public static void main(String[] ignored) {
        textui.runClasses(tracker.UnitTest.class);
    }

    @Test public void testmovement() {
        Simulator simulate = new Simulator(42.0, 1000.0, 10.0, 5.0);
        PostView view = new PostView();
        TreeMap<Double, Double> tree = new TreeMap<Double, Double>();
        Vehicle car = new Vehicle(10.0, 5.0);
        Post one = new Post(1.0, 2.0, 3.0);
        Post two = new Post(1.2, 2.2, 2.5);
        QuadTree<Post> Q = new QuadTree<Post>(view, 3);
        Q.add(one); Q.add(two);
        ArrayList<Double> pulse = new ArrayList<Double>();
        pulse.add(1.0); pulse.add(2.0);
        pulse.add(3.0); pulse.add(4.0); pulse.add(5.0);
        tree = simulate.pulsetimes(pulse, 5);
    }

    @Test public void testposition() {
        Vehicle car = new Vehicle(10.0, 5.0);
        car.position(car.getpos()[0], car.getpos()[1], 1, Math.PI / 4);
        assertEquals(5.0, car.getpos()[0], 0);
        assertEquals(10.0, car.getpos()[1], 0);
        car.position(car.getpos()[0], car.getpos()[1], 1, Math.PI / 2);
        assertEquals(5.0 + 5.0 * Math.cos(Math.PI / 4), car.getpos()[0], 0);
        assertEquals(10.0 + 5.0 * Math.sin(Math.PI / 4), car.getpos()[1], 0);
        car.position(car.getpos()[0], car.getpos()[1], 1, Math.PI / 4);
        assertEquals(5.0 + 5.0 * Math.cos(Math.PI / 4)
                     + 5.0 * Math.cos(Math.PI / 2), car.getpos()[0], 0);
        assertEquals(10.0 + 5.0 * Math.sin(Math.PI / 4) + 5.0, car.getpos()[1],
                     0);
    }

    @Test public void testPostView() {
        Post one = new Post(1.0, 2.0, 3.0);
        Post two = new Post(1.2, 2.2, 2.5);
        PostView view = new PostView();
        assertEquals("Not right", one.getID(), 3);
        assertEquals("not Right", two.getID(), 4);
        assertEquals(1.0, view.getX(one), 0);
        assertEquals(2.0, view.getY(one), 0);
        assertEquals("not right", true, view.equals(one, one));
        assertEquals("not right", false, view.equals(one, two));
    }

    @Test public void testmultiples() {
        Simulator simulate = new Simulator(42, 10, 10, 10);
        TreeMap<Double, Double> results = new TreeMap<Double, Double>();
        ArrayList<Double> result = new ArrayList<Double>();
        ArrayList<Double> multiple = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> mult = new ArrayList<ArrayList<Double>>();
        result.add(2.0); result.add(3.0); result.add(4.0);
        multiple = simulate.multiples(2.0, 10.0);
        assertEquals("Wrong size for multiples", 5, multiple.size());
        results = simulate.pulsetimes(result, 10);
        assertEquals("Wrong number of pulses", 7, results.size());
    }

}
