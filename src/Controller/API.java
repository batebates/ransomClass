package Controller;


import Module.Vector;
import Vue.IHM;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

//TODO: Faire le main, contenant une fonction d'initialisation récuperant la liste des classes de Ransomware
//TODO: le main tournera ensuite en boucle en attendant les instructions de l'utilisateur.
public class API {
    private final Vector modele;
    private final ArrayList <Vector> lstVectorFamily = new ArrayList<>();
    private final RecupData recupData;

    public API(){
        String fileNameDictionnary = "./dicoRansomware";
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
        StringBuilder resultat = new StringBuilder("Cible ");

        Vector vCible = recupData.createVector(modele,path, resultat.toString());

        //TODO: Métrique a changer
        ArrayList <Metrique> metriques = new ArrayList<>();
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
            resultat.append(m.getClass().getSimpleName()).append("    ");
        }


        resultat.append("\n");
        for(Vector vFamille:lstVectorFamily){
            resultat.append(vFamille.getName()).append(": ");
            for(Metrique m: metriques){
                resultat.append(String.format("     %.3f     ", m.calcul(vCible, vFamille)));

            }

            resultat.append(";\n ");
        }
        System.out.println(System.currentTimeMillis()-debut);

        return resultat.toString();
    }
}
