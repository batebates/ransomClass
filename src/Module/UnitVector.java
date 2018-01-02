package Module;

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


    /**
     * Constructor that create and set a UnitVector
     * @param s String
     * @param c Boolean
     */
    public UnitVector(String s,Boolean c){
        isContent = c;
        value = s;
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

}
