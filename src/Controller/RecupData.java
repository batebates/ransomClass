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

        public static void main(String[] args) {
            Vector modele = new Vector();
            ArrayList <Vector> lstVector = new ArrayList<Vector>();
            String fileName = "./dico.txt";

            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                stream.distinct().forEach(s -> modele.add(new UnitVector(s,false)));


            } catch (IOException e) {
                e.printStackTrace();
            }


            IntStream.range('a', 'l').forEach(
                    n -> {
                        modele.affichage();

                        try (Stream<String> stream = Files.lines(Paths.get("./"+(char) n +".txt"))) {
                            Vector v = new Vector((ArrayList) modele.getVector().clone());
                            v.setNom(String.valueOf(n));

                            stream.forEach(s ->{
                                v.addByModel(modele,s);

                            });

                            //stream.forEach(s -> modele.addByModel(modele,s));
                            //System.out.println(v.getNom());

                            lstVector.add(v);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );


            Cosine cosine = new Cosine();
            for(Vector vCible:lstVector){
                System.out.println("Vecteur Cible: "+ vCible.getNom() + ":\n");
                for(Vector v:lstVector) {
                    System.out.println("Vecteur: "+ v.getNom() + ":"+ cosine.calcul(vCible,v));
                }
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
            //v.affichage();



        }
/*
* Recuperer les string du dico
* les ajouter dans un Vector a 1 en les triant dans l'ordre alpha
* construire une liste de vector avec ce modèle
* sortir la liste des chaines de caractère jamais detecter
* */

}
