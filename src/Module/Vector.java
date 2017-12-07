package Module;

import java.util.ArrayList;

public class Vector {
    private ArrayList<UnitVector> vector = new ArrayList<UnitVector>();
    private String nom = null;

    public ArrayList<UnitVector> getVector() {
        return vector;
    }

    public void setVector(ArrayList<UnitVector> vector) {
        this.vector = vector;
    }

    public Vector(){}
    public Vector(ArrayList<UnitVector> v){
        this.vector = v;
    }
    public void add(UnitVector u){
        this.vector.add(u);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void affichage(){
        for(UnitVector v: vector){
            if(v.getContenu()==true)
                System.out.println(v.getContenu() + " "+ v.getValue());
        }
    }

    public void addByModel(Vector m, String s){

        int i;
        for(UnitVector u: m.vector){

            if(u.getValue().equals(s)){
                i = m.vector.indexOf(u);
                this.vector.set(i,new UnitVector(s,true));
            }
        }
    }
}
