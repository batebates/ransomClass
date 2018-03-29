package controller;


import module.Vector;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;


public class API {
    private final Vector modele;
    private final ArrayList <Vector> lstVectorFamily = new ArrayList<>();
    private final ArrayList <Metrique> metriques = new ArrayList<>();
    private final RecupData recupData;
    private final OptimVisitor visitor = new OptimVisitor();
    private final StringBuilder resultat = new StringBuilder("Cible      ");
    HashMap<String,Integer> testStatsTrue = new HashMap<>();
    HashMap<String,Integer> testStatsFalse = new HashMap<>();
    private Integer nbrFic = 0;
    private Boolean test = true;



    public void setIHM(vue.IHM IHM) {
        this.IHM = IHM;
    }

    private vue.IHM IHM = null;

    public API() throws Exception {
        System.out.println("Lancement de L'application");
        String fileNameDictionnary = "./dic.txt";
        recupData = new RecupData();
        modele = recupData.createVectorModele(fileNameDictionnary);
        String filePath = "./famille2/";
        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(dir)) {
            for (Path file: streams) {
                System.out.println("Fichier lu: "+ file.getFileName());
                lstVectorFamily.add(recupData.vectorFamily(modele,filePath +file.getFileName(), file.getFileName().toString()));

            }
        } catch (Exception x) {
            throw new Exception("Impossible de lire un fichier",x);

        }
        metriques.add(new Cosine());
        metriques.add(new Anderberg());
        metriques.add(new Hamming());
        metriques.add(new RusselRao());
        metriques.add(new Jaccard());
        metriques.add(new Poids());
    }
    public void walk( String path ) throws Exception {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath());
            }
            else {
                Vector vCible = recupData.extractor(modele,f.getAbsolutePath().toString(), resultat.toString());

                nbrFic += 1;
                resultat.append("\n");
                resultat.append(f.getAbsolutePath()+"\n");

                resultat.append("\n");
                for(Metrique m: metriques){
                    double somme = 0;
                    double moyenneMax =0;
                    String familleSuspect = "";
                    for(Vector vFamille:lstVectorFamily){

                        somme = m.calcul(vCible, vFamille);
                        if(somme>moyenneMax){
                            moyenneMax = somme;
                            familleSuspect = vFamille.getName();
                        }
                    }
                    resultat.append(m.getClass()+ " : " + familleSuspect +"\n");

                }

            }
        }
    }
    public void walkTest( String path ,Metrique m) throws Exception {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walkTest( f.getAbsolutePath(),m );
            }
            else {
                Vector vCible = recupData.extractor(modele,f.getAbsolutePath().toString(), resultat.toString());
                nbrFic += 1;

                    double somme = 0;
                    double moyenneMax =0;
                    String familleSuspect = "";
                    for(Vector vFamille:lstVectorFamily){
                        somme = m.calcul(vCible, vFamille);
                        if(somme>moyenneMax){
                            moyenneMax = somme;
                            familleSuspect = vFamille.getName();
                        }
                    }
                    if(f.getParentFile().getName().toString().equals(familleSuspect)){
                        Integer val = testStatsTrue.get(f.getParentFile().getName().toString());
                        testStatsTrue.put(f.getParentFile().getName().toString(),val+1);

                    }
                    else{
                        Integer val = testStatsFalse.get(familleSuspect);
                        testStatsFalse.put(familleSuspect,val+1);
                    }

            }
        }
    }

    public String Rechercher(String path) throws Exception {
        resultat.delete(0,resultat.length()-1);
        long debut =  System.currentTimeMillis();

        File root = new File( path );
        if(root.isDirectory()) {
            if (test) {
                nbrFic = 0;
                for (Metrique m : metriques) {
                    resultat.append(m.getClass() + " : " + "\n");
                    for (Vector vFamille : lstVectorFamily) {
                        testStatsTrue.put(vFamille.getName(), 0);
                        testStatsFalse.put(vFamille.getName(), 0);
                    }

                    walkTest(path, m);
                    for (Vector vFamille : lstVectorFamily) {
                        resultat.append("Test positif pour la famille: " + vFamille.getName() + "-> " + testStatsTrue.get(vFamille.getName()) + "\n");
                        resultat.append("Test negatif pour la famille: " + vFamille.getName() + "-> " + testStatsFalse.get(vFamille.getName()) + "\n");
                    }
                }
            } else {
                walk(path);
            }

        } else {

            StringBuilder resultat = new StringBuilder("Cible      ");
            Vector vCible = recupData.extractor(modele, path, resultat.toString());


            for (Metrique m : metriques) {
                resultat.append(m.getClass().getSimpleName()).append("     ");
            }


            resultat.append("\n");
            for (Vector vFamille : lstVectorFamily) {
                resultat.append(vFamille.getName()).append(": ");
                for (Metrique m : metriques) {
                    resultat.append(String.format("     %.3f     ", m.calcul(vCible, vFamille)));

                }

                resultat.append("\n ");
            }
            recupData.accept(visitor);
            vCible.accept(visitor);

            /*for (Vector vF : lstVectorFamily) {
                resultat.append(vF.getName()).append(": \n");
                for (Vector vFamille : lstVectorFamily) {
                    resultat.append(vFamille.getName()).append(": ");
                    for (Metrique m : metriques) {
                        resultat.append(String.format("     %.3f     ", m.calcul(vF, vFamille)));

                    }

                    resultat.append("\n ");
                }
            }
            System.out.println(resultat);*/


            return resultat.toString();
        }
        Writer finalWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./res.txt"), "utf-8"));

            try {

                finalWriter.write(resultat.toString());
            } catch (Exception e) {
                throw new Exception("Impossible d'écrire le fichier",e);
            }

        finalWriter.close();

        System.out.print("Temps d'execution en secondes: ");
        System.out.println(Double.toString((System.currentTimeMillis() - debut) / 1000F));


        return "Folder Sélectionner veuillez consulter le fichier Res.txt";

    }


}
