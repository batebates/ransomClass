package controller;

import module.Vector;

import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

public class Poids extends Metrique{
    private void initialisation(Vector a, Vector b){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        normeA = 0;
        normeB = 0;
        produit =0;
        IntStream.range(0,a.getVectorArrayList().size()).forEach(
                n->{

                    if(a.getVectorArrayList().get(n).isContent() && b.getVectorArrayList().get(n).isContent()){

                        s11 += b.getVectorArrayList().get(n).getPoids();
                        produit += b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();
                        normeA = b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();
                        normeB = b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();
                    }
                    else if(!a.getVectorArrayList().get(n).isContent() && b.getVectorArrayList().get(n).isContent()){
                        s01++;
                        normeA = b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();

                    }
                    else if(a.getVectorArrayList().get(n).isContent() && !b.getVectorArrayList().get(n).isContent()){
                        s10++;
                        normeB = b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();

                    }
                    else{
                        s00++;
                    }

                });
        normeA = sqrt(normeA);
        normeB = sqrt(normeB);
    }

    /**
     * Calculation of Hamming distance into 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return distance's value
     */
    @Override
    public double calcul(Vector a, Vector b) {
        this.initialisation(a,b);
        return produit /  1.0*normeA*normeB;
    }

}
