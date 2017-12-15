package Controller;



import Module.UnitVector;
import Module.Vector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


/**
 * Class that allow to collect data from files to create vector
 */
public class RecupData {

    /**
     * Create a vector with a data file
     * File should contain a list of string separate by a carriage return.
     * @param fileName
     * @return
     */
    public Vector createVectorModele(String fileName){
        Vector modele = new Vector();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.distinct().forEach(s -> modele.add(new UnitVector(s,false)));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return modele;
    }

    /**
     * Create a Vector with a data file and a model
     * The Vector is based on model, each string values is by default considered like "not content"
     * but Strings find in datafile and content in model are set like "content"
     * however other string that are not content in model are ignored
     * @param modele
     * @param fileName
     * @param name
     * @return
     */
    public Vector createVector(Vector modele,String fileName, String name){

        Vector v = new Vector((ArrayList) modele.getVector().clone());
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            v.setName(String.valueOf(name));

            stream.forEach(s ->{
                v.addByModel(modele,s);

            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }



}
