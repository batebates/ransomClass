package controller;


import module.Vector;
import vue.IHM;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class API {
    private final Vector modele;
    private final ArrayList <Vector> lstVectorFamily = new ArrayList<>();
    private final ArrayList <Metrique> metriques = new ArrayList<>();
    private final RecupData recupData;
    private final OptimVisitor visitor = new OptimVisitor();
    public API(){
        System.out.println("Lancement de L'application");
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
        metriques.add(new Cosine());
        metriques.add(new Anderberg());
        metriques.add(new Hamming());
        metriques.add(new RusselRao());
        metriques.add(new SokalSneath());
        metriques.add(new Kulzinsky());
        metriques.add(new Euclide());
        metriques.add(new Jaccard());
    }

    public static void main(String[] args) {
        JFrame fenetre = new IHM();

    }
    public String Rechercher(String path){
        long debut =  System.currentTimeMillis();
        StringBuilder resultat = new StringBuilder("Cible      ");
        Vector vCible = recupData.createVector(modele,path, resultat.toString());



        for(Metrique m: metriques){
            resultat.append(m.getClass().getSimpleName()).append("     ");
        }


        resultat.append("\n");
        for(Vector vFamille:lstVectorFamily){
            resultat.append(vFamille.getName()).append(": ");
            for(Metrique m: metriques){
                resultat.append(String.format("     %.3f     ", m.calcul(vCible, vFamille)));

            }

            resultat.append("\n ");
        }
        System.out.print("Temps d'execution en secondes: ");
        System.out.println(Double.toString((System.currentTimeMillis()-debut)/ 1000F));
        recupData.accept(visitor);
        vCible.accept(visitor);
        return resultat.toString();
    }


}
