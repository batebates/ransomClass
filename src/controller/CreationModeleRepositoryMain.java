package controller;


import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

class CreationModeleRepositoryMain {
    private static Logger log = Logger.getAnonymousLogger();
    static class CptString {
        private Integer compte;
        private String value;
        CptString(String s){
            compte = 1;
            value = s;
        }

        Integer getCompte() {
            return compte;
        }

        String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        void setCompte(Integer compte) {
            this.compte = compte;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof CptString)) {
                return false;
            }else{
            CptString c =(CptString) o;
            return value.equals(c.getValue());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "./famille/";
        ArrayList <CptString> stringList = new ArrayList<>();

        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(dir)) {
            for (Path file: streams) {

                System.out.println("Fichier lu: "+ file.getFileName());
                try (Stream<String> stream = Files.lines(Paths.get(filePath +file.getFileName()))) {

                    stream.forEach(s -> {
                        //if(s.matches("[A-Za-z0-9]+.dll") && s.length()>5 )
                            stringList.add(new CptString(s));
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
        System.out.println("Nombre de termes: " + stringList.size());
        stringList.stream().distinct().forEach(s -> System.out.println(s.value));
        log.log(Level.INFO,"Comptage en cours");
        stringList.forEach(s -> s.setCompte(toIntExact(stringList.stream().filter(sb -> sb.value.equals(s.value)).count())));
        log.log(Level.INFO,"Copie en cours");
        ArrayList<CptString> distinctList = new ArrayList<>();
        for (CptString c:stringList) {
            if (!distinctList.contains(c))
                distinctList.add(c);
        }
        log.log(Level.INFO,"Triage en cours");
        distinctList.sort(Comparator.comparing(CptString::getCompte));
        Collections.reverse(distinctList);
        log.log(Level.INFO,"Derniere boucle");

        Writer finalWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./resultat.txt"), "utf-8"));

        distinctList.forEach(cpt -> {
            try {

                finalWriter.write(cpt.value + " " + cpt.compte + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        finalWriter.close();


    }
}
