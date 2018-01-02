package controller;

import module.Vector;

/**
 * Class that allow to calculate Sokal and Sneath Distance
 */
public class SokalSneath extends Metrique{

    /**
     * Calculation of Sokal and Sneath's distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return 2.0*(s11+s00)/(2.0*(s11+s00)+s01+s10);
    }
}
