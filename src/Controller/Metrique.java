package Controller;

import Module.Vector;

import java.util.stream.IntStream;

/**
 * Abstract class that implement initialisation of a distance calculation.
 */
abstract public class Metrique {
    protected int s00;
    protected int s01;
    protected int s10;
    protected int s11;

    /**
     * Set variables s00,s01,s10,s11 with two vectors
     * @param A
     * @param B
     */
    public void Initialisation(Vector A, Vector B){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        IntStream.range(0,A.getVector().size()).forEach(
                n->{

                    if(A.getVector().get(n).isContent() && B.getVector().get(n).isContent()){

                        s11++;
                    }
                    else if(A.getVector().get(n).isContent()==false && B.getVector().get(n).isContent())
                        s01++;
                    else if(A.getVector().get(n).isContent() && B.getVector().get(n).isContent()==false)
                        s10++;
                    else
                        s00++;

                });
    }

    /**
     * Abstract function:  call Initialisation Function to 2 vectors
     * @param A
     * @param B
     * @return 0.0 by default
     */
    public double calcul(Vector A, Vector B){
        Initialisation(A, B);
        return 0.0;
    }


}
