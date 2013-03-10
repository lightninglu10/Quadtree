package tracker;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;

public class test{

    public static void main(String[] args) {
        HashMap j = new HashMap();
        ArrayList<Double> k = new ArrayList<Double>();
        k.add((double) 2); k.add((double) 3); k.add((double) 4);
        j = pulsetimes(k, 15);
        System.out.println(j.get(1));
    }

    /** Creates a hashset of times of pulses and how many posts pulse. */
    static HashMap pulsetimes(ArrayList<Double> list, double max) {
        HashMap<Double, Double> result = new HashMap<Double, Double>();
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

    /** Creates an arraylist of all multiples up to Y. */
    static ArrayList<Double> multiples(double x, double max) {
        ArrayList<Double> result = new ArrayList<Double>();
        result.add(x);
        while (result.get(result.size() - 1) < max) {
            result.add(result.get(result.size() - 1) + x);
        }
        return result;
    }
}
