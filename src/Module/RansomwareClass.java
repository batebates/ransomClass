package Module;



//TODO: permet de recuperer une liste de vector modèle de ransomware
public class RansomwareClass {
    private String className;
    private Vector vector;

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public String getClassName() {
        return className;
    }

    public Vector getVector() {
        return vector;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
