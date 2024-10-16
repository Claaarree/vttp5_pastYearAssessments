package textAnalyser;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String dirName = args[0];
        if (args.length < 0) {
            System.out.println("Please input directory name");
            System.exit(-1);
        }

        Console console = System.console();
        String fileName = console.readLine("Please input file name: ");
        File toRead = new File (dirName + File.separator + fileName);

        //Reading text file
        FileReader fr = new FileReader(toRead);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        ArrayList <String> words = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.length() <= 0){
                continue;
            }
            //Removing all punctuations
            line = line.replaceAll("\\p{Punct}", "");
            //Removing extra spaces in between words
            line = line.trim().replaceAll(" +", " ");;
            //Changing all words to lowercase
            line = line.toLowerCase();
            String[] wordArray = line.split(" ");
            for (String w : wordArray) {
                words.add(w);
            }
           
        }
        br.close();
        fr.close();

        //Creating a Map to store the words
        Map <String , Map <String, Integer>> wordMap = new HashMap<>();
        Map <String , Integer> wordDist= new HashMap<>();
        for (int i = 0; i < words.size() - 1; i++) {
            String currWord = words.get(i);
            String nextWord = words.get(i + 1);
            if (!wordMap.containsKey(currWord)) {
                wordMap.put(currWord, new HashMap<>());
            }
            wordDist = wordMap.get(currWord);

            //Accessed inner map
            int count = 0;
            if (!wordDist.containsKey(nextWord)) {
                wordDist.put(nextWord, count);
            }
            count = wordDist.get(nextWord);
            count++;
            wordDist.put(nextWord, count);            
        }
        
        double probability;
        for (String cr : wordMap.keySet()) {
            System.out.println(cr);
            wordDist = wordMap.get(cr);
            double totalCount = 0.0;
            Object[] values = wordDist.values().toArray();
            for (int j = 0; j < values.length; j++) {
                totalCount += (int)values[j];
            }
            for (String nw : wordDist.keySet()){
                probability = wordDist.get(nw) / totalCount;
                System.out.printf("\t %s %d %f \n", nw , wordDist.get(nw), probability);
            }
        }    
        
    }
}
