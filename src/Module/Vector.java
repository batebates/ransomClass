package Module;

import java.util.ArrayList;

/**
 * Class that allow binaries vectors.
 * this type of vector is composed by UnitVector
 */
public class Vector {
    /**
     * Represent vector name
     */
    private String name = null;

    /**
     * Contain an unities vectors list
     */
    private ArrayList<UnitVector> vector = new ArrayList<>();

    /**
     * Simple constructor
     */
    public Vector(){}

    /**
     * Constructor of a Vector instance with a List
     * @param v  UnitVector ArrayList
     */
    public Vector(ArrayList<UnitVector> v){
        this.vector = v;
    }


    /**
     * @return Array vector
     */
    public ArrayList<UnitVector> getVector() {
        return vector;
    }

    /**
     * Set Array vector
     * @param vector UnitVector ArrayList
     */
    public void setVector(ArrayList<UnitVector> vector) {
        this.vector = vector;
    }



    /**
     * Add an Unitvector to instance
     * @param u UnitVector
     */
    public void add(UnitVector u){
        this.vector.add(u);
    }

    /**
     * @return vector name
     */
    public String getName() {
        return name;
    }

    /**
     * Set vector name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Print a Vector
     */
    public void affichage(){
        for(UnitVector v: vector){
            //if(v.isContent()==true)
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
        for(UnitVector u: m.vector){

            if(u.getValue().equals(s)){
                i = m.vector.indexOf(u);
                this.vector.set(i,new UnitVector(s,true));
            }
        }
    }
}
