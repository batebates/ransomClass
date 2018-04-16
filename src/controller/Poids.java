package controller;

import module.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                        p.s11+=1;
                    }
                    else if(!vModele.getVectorArrayList().get(n).isContent() && vCaract.getVectorArrayList().get(n).isContent()){
                        p.s01+=1;
                    }
                    else if(vModele.getVectorArrayList().get(n).isContent() && !vCaract.getVectorArrayList().get(n).isContent()){
                        p.s10+=1;
                    }else{
                        p.s00+=1;
                    }
                    listCoeffCalcul.replace(k,p);
                });
        //normeA = sqrt(normeA);
        //normeB = sqrt(normeB);
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
        if(normeA==0 || normeB ==0){
            return 0;
        }else {
            return produit / (1.0 * normeA * normeB);
        }/*
        if(s11+s10==0 || s01+s11==0){
            return 0;
        }
        return s11/(sqrt(s11+s10)*sqrt(s01+s11));*/
    }
    //@Override
    public double calcul2(Vector a, Vector b) {
        HashMap<Integer,Poids> listCoeffCalcul = this.initialisation2(a,b);
        double resultat =0.0;
        double coefficients = 0.0;
        for (Map.Entry<Integer,Poids> entry : listCoeffCalcul.entrySet()) {
            System.out.println("CLEF: " + entry.getKey());
            System.out.println(entry.getValue().s11);
            System.out.println(entry.getValue().s10);
            System.out.println(entry.getValue().s01);
            System.out.println(entry.getValue().s00);
            if(entry.getKey()<0){
                coefficients +=1;
                resultat += 1* entry.getValue().calcul2(a,b);
            } else {
                double coef;
                if(entry.getKey()==0){
                    coef =1000;
                }else {
                    coef = (1 + listCoeffCalcul.size() - entry.getKey());
                }
                coefficients += coef;
                resultat += coef * entry.getValue().calcul2(a, b);
            }
        }
        System.out.println("somme:" + resultat);
        System.out.println("coeffs:" + coefficients);
        return resultat/(1.0*coefficients);
    }

}
