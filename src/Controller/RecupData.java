package Controller;



import Module.UnitVector;
import Module.Vector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RecupData {

    public Vector createVectorModele(String fileName){
        Vector modele = new Vector();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.distinct().forEach(s -> modele.add(new UnitVector(s,false)));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return modele;
    }

    public Vector createVector(Vector modele,String fileName, String name){

        Vector v = new Vector((ArrayList) modele.getVector().clone());
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            v.setNom(String.valueOf(name));

            stream.forEach(s ->{
                v.addByModel(modele,s);

            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }



}
