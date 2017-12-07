package Module;

public class UnitVector {
    private Boolean contenu;
    private String value;

    public UnitVector(String s,Boolean c){
        contenu = c;
        value = s;
    }
    public Boolean getContenu() {
        return contenu;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setContenu(Boolean contenu) {
        this.contenu = contenu;
    }


}
