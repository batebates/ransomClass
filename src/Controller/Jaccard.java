package Controller;

import Module.Vector;

/**
 * Class that allow to calculate Jaccard Distance
 */

public class Jaccard extends Metrique{
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param A a Vector
     * @param B second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return ((1.0*(s11))/(1.0*(s01+s10+s11)));
    }



}
