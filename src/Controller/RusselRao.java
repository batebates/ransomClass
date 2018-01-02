package Controller;

import Module.Vector;


/**
 * Class that allow to calculate Russel-Rao Distance
 */
public class RusselRao extends Metrique{
    /**
     * Calculation of Hamming distance into 2 vectors
     * @param A a Vector
     * @param B second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
        super.calcul(A, B);
        return s11 /  (1.0*(s11 + s01 + s10 + s00));
    }
}