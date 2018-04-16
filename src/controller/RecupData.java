package controller;



import module.UnitVector;
import module.Vector;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


/**
 * Class that allow to collect data from files to create vector
 */
class RecupData implements IVisitable{
    private Integer eachLines = 0;
    private Integer eachLinesSplit = 0;
    private Integer eachLinesSplitVector = 0;

    /**
     * Create a vector with a data file
     * File should contain a list of string separate by a carriage return.
     * @param fileName Dictionnary filename
     * @return Vector modele
     */
    public Vector createVectorModele(String fileName) throws IOException {
        Vector modele = new Vector();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.distinct().forEach(s -> {
                String[] arr = s.split(" ");
                modele.add(new UnitVector(arr[0],false,Integer.parseInt(arr[1])));
            });

        } catch (IOException e) {
            throw new IOException("Impossible de lire le fichier",e);
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
    public Vector extractor(Vector modele, String fileName, String name) throws IOException {
        @SuppressWarnings("unchecked")
        Vector v = new Vector((ArrayList<UnitVector>) modele.getVectorArrayList().clone());
        try (Stream<String> stream = Files.lines(Paths.get(fileName),  StandardCharsets.ISO_8859_1)) {
            v.setName(String.valueOf(name));

            stream.forEach(s -> {
                eachLines++;
                for(String n : s.split("[^0-9A-Za-z._]+")){
                    eachLinesSplit++;
                    if( n.length()>5 && n.length()<32 && (n.matches("[A-Zl][A-Za-z]+") || n.matches("[A-Za-z0-9]+.dll")) ){
                        v.addByModel(modele,n,0);
                    }
                }
                //v.addByModel(modele,s);
            });


        } catch (IOException e) {
            throw new IOException("Impossible de lire le fichier",e);
        }
        //v.affichage();
        return v;
    }


    public Vector vectorFamily(Vector modele, String fileName, String name) throws IOException {
        @SuppressWarnings("unchecked")
        Vector v = new Vector((ArrayList<UnitVector>) modele.getVectorArrayList().clone());
        try (Stream<String> stream = Files.lines(Paths.get(fileName),  StandardCharsets.ISO_8859_1)) {
            v.setName(String.valueOf(name));

            stream.forEach(s -> {
                eachLines++;
                String[] arr = s.split(" ");

                if(arr.length ==2 && arr[0]!=null && arr[1]!=null)
                    v.addByModel(modele,arr[0],Integer.parseInt(arr[1]));
            });


        } catch (IOException e) {
            throw new IOException("Impossible de lire le fichier",e);
        }
        //v.affichage();
        return v;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public Integer getEachLines() {
        return eachLines;
    }

    public Integer getEachLinesSplit() {
        return eachLinesSplit;
    }

}
