package Controller;

import Module.Vector;

/**
 * Class that allow to calculate Sokal and Sneath Distance
 */
public class SokalSneath extends Metrique{

    /**
     * Calculation of Sokal and Sneath's distance into 2 vectors
     * @param A a Vector
     * @param B second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return 2.0*(s11+s00)/(2.0*(s11+s00)+s01+s10);
    }
}
