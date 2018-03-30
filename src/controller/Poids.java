package controller;

import module.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

public class Poids extends Metrique{
    private double normeA;

    public double getNormeB() {
        return normeB;
    }

    public void setNormeB(double normeB) {
        this.normeB = normeB;
    }

    public double getProduit() {
        return produit;
    }

    public void setProduit(double produit) {
        this.produit = produit;
    }

    private double normeB;
    private double produit;

    public double getNormeA() {
        return normeA;
    }

    public void setNormeA(double normeA) {
        this.normeA = normeA;
    }

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
                        normeA += b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();
                        normeB += b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();
                    }
                    else if(!a.getVectorArrayList().get(n).isContent() && b.getVectorArrayList().get(n).isContent()){
                        s01++;
                        normeA += b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();

                    }
                    else if(a.getVectorArrayList().get(n).isContent() && !b.getVectorArrayList().get(n).isContent()){
                        s10++;
                        normeB += b.getVectorArrayList().get(n).getPoids()*b.getVectorArrayList().get(n).getPoids();

                    }
                    else{
                        s00++;
                    }

                });
        normeA = sqrt(normeA);
        normeB = sqrt(normeB);
    }

    private HashMap<Integer,Poids> initialisation2(Vector vModele, Vector vCaract){
        s00 = 0;
        s01 = 0;
        s10 = 0;
        s11 = 0;
        normeA = 0;
        normeB = 0;
        produit =0;
        HashMap<Integer,Poids> listCoeffCalcul = new HashMap<>();
        IntStream.range(0,vModele.getVectorArrayList().size()).forEach(
                n->{
                    Integer k = vModele.getVectorArrayList().get(n).getPoids();
                    Integer vCaractPoids =vCaract.getVectorArrayList().get(n).getPoids();
                    if(!listCoeffCalcul.containsKey(k)){
                        listCoeffCalcul.put(k,new Poids());
                    }
                    Poids p = listCoeffCalcul.get(k);

                    if(vModele.getVectorArrayList().get(n).isContent() && vCaract.getVectorArrayList().get(n).isContent()){
                        p.setProduit(p.produit+(vCaractPoids*vCaractPoids));
                        p.setNormeA(p.normeA+(vCaractPoids*vCaractPoids));
                        p.setNormeB(p.normeB+(vCaractPoids*vCaractPoids));
                    }
                    else if(!vModele.getVectorArrayList().get(n).isContent() && vCaract.getVectorArrayList().get(n).isContent()){
                        p.setNormeA(p.normeA+(vCaractPoids*vCaractPoids));
                    }
                    else if(vModele.getVectorArrayList().get(n).isContent() && !vCaract.getVectorArrayList().get(n).isContent()){
                        p.setNormeB(p.normeB+(vCaractPoids*vCaractPoids));
                    }


                });
        normeA = sqrt(normeA);
        normeB = sqrt(normeB);
        return listCoeffCalcul;
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

    public double calcul2(Vector a, Vector b) {
        this.initialisation2(a,b);
        return produit /  1.0*normeA*normeB;
    }

}
