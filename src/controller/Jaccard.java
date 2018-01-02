package controller;

import module.Vector;

/**
 * Class that allow to calculate Jaccard Distance
 */

public class Jaccard extends Metrique{
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        super.calcul(a, b);
        return ((1.0*(s11))/(1.0*(s01+s10+s11)));
    }



}
