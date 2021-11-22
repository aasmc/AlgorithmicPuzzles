package geeks_for_geeks.algorithms.math;

import java.util.ArrayList;

public class Solutions {
    public ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer> result = new ArrayList<>();
        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            result.add(-1);
            return result;
        }
        double root1 = Math.floor(((-b + Math.sqrt(discriminant)) / (2 * a)));
        double root2 = Math.floor(((-b - Math.sqrt(discriminant)) / (2 * a)));
        result.add((int)root1);
        result.add((int)root2);
        return result;
    }
}
