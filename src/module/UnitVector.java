package module;

/**
 * Class that allow an entity of a binary vector
 * this entity is composed by a boolean and a String value
 */
public class UnitVector {


    /**
     * value content String value that represent a System call or a library
     */
    private final String value;
    /**
     * isContent indicate if value is content or not
     */
    private final Boolean isContent;

    private final Integer poids;

    /**
     * Constructor that create and set a UnitVector
     * @param s String
     * @param c Boolean
     */
    public UnitVector(String s,Boolean c,Integer p){
        isContent = c;
        value = s;
        poids = p;
    }


    /**
     * @return isContent value
     */
    public Boolean isContent() {
        return isContent;
    }

    /**
     *
     * @return String value of Unitvector
     */
    public String getValue() {
        return value;
    }

    public Integer getPoids() {return poids;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnitVector)) {
            return false;
        }else{
            UnitVector c =(UnitVector) o;
            return value.equals(c.getValue());
        }
    }

}
