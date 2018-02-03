package module;

import controller.IVisitable;
import controller.IVisitor;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class that allow binaries vectors.
 * this type of vectorArrayList is composed by UnitVector
 */
public class Vector implements IVisitable{
    private Integer cpt=0;
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
        this.vectorArrayList.sort(Comparator.comparing(UnitVector::getValue));
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
        int a,b,c;
        a=0;
        b=m.vectorArrayList.size();

        while((b-a)>1){
            //System.out.println(s);
            cpt+=1;
            c = (a+b)/2;

            if(m.vectorArrayList.get(c).getValue().equals(s)){
                this.vectorArrayList.set(c,new UnitVector(s,true));
                break;
            }
            else if(m.vectorArrayList.get(c).getValue().compareTo(s)>0)
                b=c;

            else{
                a=c;
                //System.out.println("je passe");
                }
        }
    }

    public Integer getCpt() {
        return cpt;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
