package Controller;


import Module.Vector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TODO: Faire le main, contenant une fonction d'initialisation récuperant la liste des classes de Ransomware
//TODO: le main tournera ensuite en boucle en attendant les instructions de l'utilisateur.
public class API {

    public static void main(String[] args) {
        String fileName = "./dico.txt";
        RecupData recupData = new RecupData();
        Vector modele = recupData.createVectorModele(fileName);
        ArrayList <Vector> lstVector = new ArrayList<Vector>();


        IntStream.range('a', 'l').forEach(
                n -> {

                    lstVector.add(recupData.createVector(modele,"./"+(char) n +".txt",Character.toString((char) n)));

                }
        );

        //TODO: Métrique a changer
        Metrique metrique = new Euclide();

        System.out.print(";");
        for(Vector vCible:lstVector){
            System.out.print(vCible.getName()+";");
        }
        System.out.println();
        for(Vector vCible:lstVector){
            System.out.print(vCible.getName()+";");
            for(Vector v:lstVector) {
                System.out.print(metrique.calcul(vCible,v)+";");
            }
            System.out.println();
        }


        Vector v = new Vector((ArrayList) modele.getVector().clone());
        //read file into stream, try-with-resources

        try (Stream<String> stream = Files.lines(Paths.get("./l.txt"))) {


            stream.forEach(s ->{
                v.addByModel(modele,s);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
