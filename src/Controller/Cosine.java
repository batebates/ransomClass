package Controller;

import Module.Vector;

import static java.lang.Math.sqrt;

public class Cosine extends Metrique{
    @Override
    public double calcul(Vector A, Vector B) {
         super.calcul(A, B);
        //System.out.println(s11);
         return s11/(sqrt(s11+s10)*sqrt(s01+s11));
    }
}
