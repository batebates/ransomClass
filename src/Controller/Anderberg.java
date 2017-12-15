package Controller;

import Module.Vector;

/**
 * Class that allow to calculate Anderberg Distance
 */
public class Anderberg extends Metrique{

    /**
     * Calculation of Anderberg's distance into 2 vectors
     * @param A
     * @param B
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return s11/(s11+2.0*(s10+s01));
    }
}
