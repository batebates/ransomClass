package Controller;

import Module.Vector;

/**
 * Class that allow to calculate Kulzinsky Distance
 */
public class Kulzinsky extends Metrique{

    /**
     * Calculation of Kluzinsky's distance into 2 vectors
     * @param A
     * @param B
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return s11/(1.0*(s10+s01));
    }
}
