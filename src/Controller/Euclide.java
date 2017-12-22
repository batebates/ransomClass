package Controller;


import Module.Vector;

import static java.lang.Math.sqrt;

/**
 * Class that allow to calculate Euclidian Distance
 */

public class Euclide extends Metrique {
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param A
     * @param B
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return (1.0*sqrt(s01+s10))/(s00+s01+s10+s11);
    }
}
