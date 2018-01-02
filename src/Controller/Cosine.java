package Controller;

import Module.Vector;

import static java.lang.Math.sqrt;

/**
 * Class that allow to calculate Cosine Distance
 */
public class Cosine extends Metrique{
    /**
     * Calculation of Cosine distance into 2 vectors
     * @param A a Vector
     * @param B second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector A, Vector B) {
         super.calcul(A, B);
         return s11/(sqrt(s11+s10)*sqrt(s01+s11));
    }
}
