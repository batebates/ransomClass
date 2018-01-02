package Controller;

import Module.Vector;

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
     * @param A a Vector
     * @param B second Vector
     */
    private void Initialisation(Vector A, Vector B){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        IntStream.range(0,A.getVector().size()).forEach(
                n->{

                    if(A.getVector().get(n).isContent() && B.getVector().get(n).isContent()){

                        s11++;
                    }
                    else if(!A.getVector().get(n).isContent() && B.getVector().get(n).isContent())
                        s01++;
                    else if(A.getVector().get(n).isContent() && !B.getVector().get(n).isContent())
                        s10++;
                    else
                        s00++;

                });
    }

    /**
     * Abstract function:  call Initialisation Function to 2 vectors
     * @param A a Vector
     * @param B second Vector
     * @return 0.0 by default
     */
    public double calcul(Vector A, Vector B){
        Initialisation(A, B);
        return 0.0;
    }


}
