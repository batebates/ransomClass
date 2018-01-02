package controller;

import module.Vector;

import static java.lang.Math.sqrt;

/**
 * Class that allow to calculate Cosine Distance
 */
public class Cosine extends Metrique{
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
         super.calcul(a, b);
         return s11/(sqrt(s11+s10)*sqrt(s01+s11));
    }
}
