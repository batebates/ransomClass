package controller;

import module.Vector;


/**
 * Class that allow to calculate Russel-Rao Distance
 */
public class RusselRao extends Metrique{
    /**
     * Calculation of Hamming distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return s11 /  (1.0*(s11 + s01 + s10 + s00));
    }
}