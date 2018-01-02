package controller;

import module.Vector;

/**
 * Class that allow to calculate Anderberg Distance
 */
public class Anderberg extends Metrique{

    /**
     * Calculation of Anderberg's distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return s11/(s11+2.0*(s10+s01));
    }
}
