package Controller;


import Module.Vector;
import Vue.IHM;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TODO: Faire le main, contenant une fonction d'initialisation récuperant la liste des classes de Ransomware
//TODO: le main tournera ensuite en boucle en attendant les instructions de l'utilisateur.
public class API {
    private Vector modele;
    private ArrayList <Vector> lstVectorFamily = new ArrayList<Vector>();
    private String fileNameDictionnary;
    RecupData recupData;

    public API(){
        fileNameDictionnary = "./dicoRansomware";
        recupData = new RecupData();
        modele = recupData.createVectorModele(fileNameDictionnary);
        String filePath = "./famille/";
        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(dir)) {
            for (Path file: streams) {
                System.out.println("Fichier lu: "+ file.getFileName());
                lstVectorFamily.add(recupData.createVector(modele,filePath +file.getFileName(), file.getFileName().toString()));

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            System.err.println(x);
        }
    }
    public static void main(String[] args) {
        JFrame fenetre = new IHM();

    }
    public String Rechercher(String path){
        String resultat = new String("Cible ");

        Vector vCible = recupData.createVector(modele,path,"Cible");

        //TODO: Métrique a changer
        ArrayList <Metrique> metriques = new ArrayList<Metrique>();
        metriques.add(new Cosine());
        metriques.add(new Anderberg());
        metriques.add(new Hamming());
        metriques.add(new RusselRao());
        metriques.add(new SokalSneath());
        metriques.add(new Kulzinsky());
        metriques.add(new Euclide());
        metriques.add(new Jaccard());

        long debut = System.currentTimeMillis();


        for(Metrique m: metriques){
            resultat += m.getClass().getSimpleName();
            resultat +="    ";
        }


        resultat +="\n";
        for(Vector vFamille:lstVectorFamily){
            resultat +=vFamille.getName();
            resultat +=": ";
            for(Metrique m: metriques){
                resultat += String.format("     %.3f     ",  m.calcul(vCible,vFamille));

            }

            resultat +=";\n ";
        }
        System.out.println(System.currentTimeMillis()-debut);

        return resultat;
    }
}
