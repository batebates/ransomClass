package controller;


import module.Vector;

import static java.lang.Math.sqrt;

/**
 * Class that allow to calculate Euclidian Distance
 */

public class Euclide extends Metrique {
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return (1.0*sqrt(s01+s10))/(s00+s01+s10+s11);
    }
}
