package module;

import java.util.ArrayList;

/**
 * Class that allow binaries vectors.
 * this type of vectorArrayList is composed by UnitVector
 */
public class Vector {
    /**
     * Represent vectorArrayList name
     */
    private String name = null;

    /**
     * Contain an unities vectors list
     */
    private ArrayList<UnitVector> vectorArrayList = new ArrayList<>();

    /**
     * Simple constructor
     */
    public Vector(){}

    /**
     * Constructor of a Vector instance with a List
     * @param v  UnitVector ArrayList
     */
    public Vector(ArrayList<UnitVector> v){
        this.vectorArrayList = v;
    }


    /**
     * @return Array vectorArrayList
     */
    public ArrayList<UnitVector> getVectorArrayList() {
        return vectorArrayList;
    }

    /**
     * Set Array vectorArrayList
     * @param vectorArrayList UnitVector ArrayList
     */
    public void setVectorArrayList(ArrayList<UnitVector> vectorArrayList) {
        this.vectorArrayList = vectorArrayList;
    }



    /**
     * Add an Unitvector to instance
     * @param u UnitVector
     */
    public void add(UnitVector u){
        this.vectorArrayList.add(u);
    }

    /**
     * @return vectorArrayList name
     */
    public String getName() {
        return name;
    }

    /**
     * Set vectorArrayList name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Print a Vector
     */
    public void affichage(){
        for(UnitVector v: vectorArrayList){
                System.out.println(v.isContent() + " "+ v.getValue());
        }
    }

    /**
     * Add a UnitVector to instance when String value of a UnitVector is present in a model Vector
     * @param m Vector
     * @param s String
     */
    public void addByModel(Vector m, String s){

        int i;
        for(UnitVector u: m.vectorArrayList){

            if(u.getValue().equals(s)){
                i = m.vectorArrayList.indexOf(u);
                this.vectorArrayList.set(i,new UnitVector(s,true));
            }
        }
    }
}
