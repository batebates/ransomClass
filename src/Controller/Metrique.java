package Controller;

import Module.Vector;

import java.util.stream.IntStream;

abstract public class Metrique {
    protected int s00;
    protected int s01;
    protected int s10;
    protected int s11;
    public void Initialisation(Vector A, Vector B){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        IntStream.range(0,A.getVector().size()).forEach(
                n->{

                    if(A.getVector().get(n).getContenu() && B.getVector().get(n).getContenu()){

                        s11++;
                    }
                    else if(A.getVector().get(n).getContenu()==false && B.getVector().get(n).getContenu())
                        s01++;
                    else if(A.getVector().get(n).getContenu() && B.getVector().get(n).getContenu()==false)
                        s10++;
                    else
                        s00++;

                });
    }
    public double calcul(Vector A, Vector B){
        Initialisation(A, B);
        return 0.0;
    }


}
