package Controller;

import Module.Vector;

public class Cosine extends Metrique{
    @Override
    public double calcul(Vector A, Vector B) {
         super.calcul(A, B);
        //System.out.println(s11);
         return s11/(s11+2.0*(s10+s01));
    }
}
