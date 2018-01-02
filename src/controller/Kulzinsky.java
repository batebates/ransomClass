package controller;

import module.Vector;

/**
 * Class that allow to calculate Kulzinsky Distance
 */
public class Kulzinsky extends Metrique{

    /**
     * Calculation of Kluzinsky's distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return s11/(1.0*(s10+s01));
    }
}
