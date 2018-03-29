/**
 *  This program builds ransomware and goodware vectors pattern
 */
package controller;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

class CreationModeleRepositoryMain {
    private static Integer taux = 75;
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

    private static ArrayList <CptString> extractionStringFiles(String dirPath, Path file){
        ArrayList <CptString> stringList = new ArrayList<>();

                System.out.println("Fichier lu: "+ file.getFileName());
                System.out.println("Chemin du fichier: "+ dirPath);
                try (Stream<String> stream = Files.lines(Paths.get(dirPath +file.getFileName()), StandardCharsets.ISO_8859_1)) {
                    stream.forEach(s -> {

                        for(String n : s.split("[^0-9A-Za-z._]+")){
                            if( n.length()>5 && n.length()<32 && (n.matches("[A-Zl][A-Za-z]+") || n.matches("[A-Za-z0-9]+.dll")) ){
                                stringList.add(new CptString(n)); }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
        ArrayList<CptString> distinctList = new ArrayList<>();
        for (CptString c:stringList) {
            if (!distinctList.contains(c))
                distinctList.add(c);
        }
        return distinctList;
    }
    private static ArrayList<CptString> extractionListStringFile(Path dir){
        ArrayList<CptString> listExtractionByFile = new ArrayList<>();
        Integer numberFile =0;
        try (DirectoryStream<Path> subStreams = Files.newDirectoryStream(dir)) {
            for (Path file: subStreams) {
                numberFile++;
                listExtractionByFile.addAll(extractionStringFiles(dir.toRealPath().toString() + "/",file));
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
        countString(listExtractionByFile);
        log.log(Level.INFO,"Copie en cours");
        ArrayList<CptString> distinctList = new ArrayList<>();
        for (CptString c:listExtractionByFile) {
            if (numberFile*(taux/100.0) <= c.compte && !distinctList.contains(c)) {
                c.setCompte(c.compte * 100 / numberFile);
                distinctList.add(c);
            }
        }
        return distinctList;
    }

    private static void countString(ArrayList <CptString> stringList){
        log.log(Level.INFO,"Comptage en cours");
        stringList.forEach(s -> s.setCompte(toIntExact(stringList.stream().filter(sb -> sb.value.equals(s.value)).count())));
    }

    public static void main(String[] args) throws IOException {
        log.log(Level.INFO,"Construction des liste de chaines de caractère par fichiers");
        String dirRansomware = "./Apprentissage/ransomware/";
        String dirGoodware = "./Apprentissage/goodware/";
        HashMap<String,ArrayList <CptString>> listModeleFamille = new HashMap<>();
        ArrayList <CptString> modeleGoodware = new ArrayList<>();
        ArrayList <CptString> modeleDistinctMalware = new ArrayList<>();

        Path dir = Paths.get(dirRansomware);
        try (DirectoryStream<Path> streams = Files.newDirectoryStream(dir)) {
            for (Path subDir: streams) {
                ArrayList<CptString> familleRansomware = extractionListStringFile(subDir);
                listModeleFamille.put(subDir.getFileName().toString(), familleRansomware);
                modeleDistinctMalware.addAll(familleRansomware);
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }

        for (Map.Entry<String, ArrayList<CptString>> entry : listModeleFamille.entrySet()) {
            //entry.getKey()
            //entry.getValue()
            ArrayList<CptString> tempoList = new ArrayList<>();
            for (Map.Entry<String, ArrayList<CptString>> entryWOut : listModeleFamille.entrySet()) {
                if(entry.getKey() != entryWOut.getKey()){
                    tempoList.addAll(entryWOut.getValue());
                }
                //entryWOut.getKey()
                //entryWOut.getValue()
            }
            for(CptString elt: entry.getValue()){
                if(tempoList.contains(elt)){
                    elt.setCompte(elt.getCompte()*50/100);
                }
            }
        }




        ArrayList<CptString> modeleMalware = new ArrayList<>();
        for (CptString c:modeleDistinctMalware) {
            if (!modeleMalware.contains(c))
                modeleMalware.add(c);
        }


        dir = Paths.get(dirGoodware);
        modeleGoodware = extractionListStringFile(dir);


        ArrayList<CptString> modeleMain = new ArrayList<>();
        for (CptString c:modeleGoodware) {

            if (!modeleMain.contains(c)) {
                c.setCompte(0);
                modeleMain.add(c);
            }
            else{
                CptString t = modeleMain.get(modeleMain.indexOf(c));
                t.setCompte(t.getCompte()+1);
            }
        }
        for (CptString c:modeleMalware) {

            if (!modeleMain.contains(c)) {
                c.setCompte(0);
                modeleMain.add(c);
            }
            else{
                CptString t = modeleMain.get(modeleMain.indexOf(c));
                t.setCompte(t.getCompte()+1);
            }
        }

        Writer finalWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./dic.txt"), "utf-8"));

        for (CptString cpt:modeleMain) {
            try {

                finalWriter.write(cpt.value +" "+ cpt.compte +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        finalWriter.close();

        finalWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./familles/goodware"), "utf-8"));
        for (CptString cpt:modeleGoodware) {
            try {

                finalWriter.write(cpt.value +" "+ cpt.compte +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        finalWriter.close();
        /**
         *  Ecriture des fichiers modèles des ransomwares
         */
        for (Map.Entry<String, ArrayList<CptString>> entry : listModeleFamille.entrySet()) {
            finalWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./familles/" + entry.getKey()), "utf-8"));
            for (CptString cpt:entry.getValue()) {
                try {

                    finalWriter.write(cpt.value +" "+ cpt.compte +"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            finalWriter.close();
        }




        log.log(Level.INFO,"Construction des listes de chaines de caractère par famille");
        log.log(Level.INFO,"Nettoyage des listes");
        log.log(Level.INFO,"Attribut des poids");
    }


}

