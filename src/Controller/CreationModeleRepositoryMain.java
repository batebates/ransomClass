package Controller;

import Module.UnitVector;
import Module.Vector;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

public class CreationModeleRepositoryMain {
    public static class CptString {
        private Integer compte;
        private String value;

        public CptString(String s){
            compte = 1;
            value = s;
        }

        public Integer getCompte() {
            return compte;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setCompte(Integer compte) {
            this.compte = compte;
        }

        @Override
        public boolean equals(Object o) {
            CptString c =(CptString) o;
            return value.equals(c.getValue());
        }
    }

    public static void main(String[] args) {
        String filePath = "./famille/";
        String fileResult = "Resultat";
        ArrayList <CptString> stringList = new ArrayList<CptString>();

        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(dir)) {
            for (Path file: streams) {
                System.out.println("Fichier lu: "+ file.getFileName());
                try (Stream<String> stream = Files.lines(Paths.get(filePath +file.getFileName()))) {

                    stream.forEach(s -> {
                            stringList.add(new CptString(s));

                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            System.err.println(x);
        }
        stringList.stream().distinct().forEach(s -> System.out.println(s.value));
        System.out.println("Comptage en cours");
        stringList.stream().forEach(s -> {
            s.setCompte(toIntExact(stringList.stream().filter(sb -> sb.value.equals(s.value)).count()));

        });
        System.out.println("Copie en cours");
        ArrayList<CptString> distinctList = new ArrayList<CptString>();
        for (CptString c:stringList) {
            if (!distinctList.contains(c))
                distinctList.add(c);
        }
        System.out.println("Triage en cours");
        distinctList.sort(Comparator.comparing(CptString::getCompte));
        Collections.reverse(distinctList);

        try {
            System.out.println("Derniere boucle");
            Writer finalWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./resultat.txt"), "utf-8"));

            distinctList.stream().forEach(cpt -> {
                try {

                    finalWriter.write(cpt.value + " " + cpt.compte + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException ex) {
            // report
        }
        //RecupData recupData = new RecupData();
        //Vector modele = recupData.createVectorModele(fileName);
        //ArrayList<Vector> lstVector = new ArrayList<Vector>();



    }
}
