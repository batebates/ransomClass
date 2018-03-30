package controller;

import module.Vector;

import java.util.stream.IntStream;

/**
 * Abstract class that implement initialisation of a distance calculation.
 */
abstract class Metrique {
    int s00;
    int s01;
    int s10;
    int s11;



    /**
     * Set variables s00,s01,s10,s11 with two vectors
     * @param a a Vector
     * @param b second Vector
     */
    private void initialisation(Vector a, Vector b){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        IntStream.range(0,a.getVectorArrayList().size()).forEach(
                n->{

                    if(a.getVectorArrayList().get(n).isContent() && b.getVectorArrayList().get(n).isContent()){

                        s11++;
                    }
                    else if(!a.getVectorArrayList().get(n).isContent() && b.getVectorArrayList().get(n).isContent())
                        s01++;
                    else if(a.getVectorArrayList().get(n).isContent() && !b.getVectorArrayList().get(n).isContent())
                        s10++;
                    else
                        s00++;

                });
    }

    /**
     * Abstract function:  call initialisation Function to 2 vectors
     * @param a a Vector
     * @param b second Vector
     * @return 0.0 by default
     */
    public double calcul(Vector a, Vector b){
        initialisation(a, b);
        return 0.0;
    }


}
