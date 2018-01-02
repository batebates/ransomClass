package controller;



import module.UnitVector;
import module.Vector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


/**
 * Class that allow to collect data from files to create vector
 */
class RecupData {

    /**
     * Create a vector with a data file
     * File should contain a list of string separate by a carriage return.
     * @param fileName Dictionnary filename
     * @return Vector modele
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
     * @param modele modele Vector
     * @param fileName fileName ransomware
     * @param name vector name
     * @return a Vector
     */
    public Vector createVector(Vector modele,String fileName, String name){
        @SuppressWarnings("unchecked")
        Vector v = new Vector((ArrayList<UnitVector>) modele.getVectorArrayList().clone());
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            v.setName(String.valueOf(name));

            stream.forEach(s -> v.addByModel(modele,s));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }



}
