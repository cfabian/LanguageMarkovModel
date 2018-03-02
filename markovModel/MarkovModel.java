package markovModel;

import java.io.*;
import java.util.*;

public class MarkovModel<T> {
    
    protected Vector<String> wordMap = new Vector<String>();
    protected Vector<Vector<Integer>> frequencies = new Vector<Vector<Integer>>();
    protected Vector<Vector<Double>> probabilities = new Vector<Vector<Double>>();
    
    public MarkovModel(String fileName) {
        String[] words = WordParser(fileName);
        makeModel(words);
        // prettyPrint(1, 0, 15, 0, 15);
        generateText(20);
        saveModel();
    }
    
    public String generateText(int length) {
        int min = 0;
        int max = probabilities.size() - 1;
        Random rand = new Random();
        int word = rand.nextInt((max - min) + 1) + min;
        
        System.out.printf("%s", wordMap.get(word).substring(0, 1).toUpperCase() + wordMap.get(word).substring(1));
        
        for(int i = 0; i < length; i ++) {
            Double randNum = rand.nextDouble();
            Double s = 0.0;
            for(int j = 0; j < probabilities.size(); j ++) {
                s += probabilities.get(j).get(word);
                if(s >= randNum) {
                    System.out.printf(" %s", wordMap.get(j));
                    word = j;
                    break;
                }
            }
        }
        System.out.printf(".\n");
        
        return "";
    }
    
    protected String[] WordParser(String fileName) {
        // System.out.println(fileName);
        String content = "";
        
        try {
            content = new Scanner(new File(fileName)).useDelimiter("\\Z").next().trim().replaceAll("[-–\".,!?&;:*><©=)(/]{1,}", "").replaceAll("\\s{2,}", " ").toLowerCase();
            
        } catch(Exception e) {
            e.printStackTrace();
            
        }
        return content.split("[ \n]");
    }
    
    protected void makeModel(String[] words) {
        for(int i = 0; i < words.length; i ++) {
            // System.out.println(words[i]);
            if(wordMap.contains(words[i]) == false) {
                wordMap.add(words[i]);
                Vector<Integer>frequency = new Vector<Integer>();
                for(int j = 0; j < frequencies.size(); j ++) {
                    frequency.add(0);
                }
                frequencies.add(frequency);
                for(int j = 0; j < frequencies.size(); j ++) {
                    frequencies.get(j).add(0);
                }
            }
            if(i > 0) {
                frequencies.get(wordMap.indexOf(words[i])).set(wordMap.indexOf(words[i - 1]), frequencies.get(wordMap.indexOf(words[i])).get(wordMap.indexOf(words[i - 1])) + 1);
            }
        }
        
        for(int i = 0; i < frequencies.size(); i ++) {
            Vector<Double>tmp = new Vector<Double>();
            for(int j = 0; j < frequencies.size(); j ++) {
                tmp.add(0.0);
            }
            probabilities.add(tmp);
        }
        
        int count = 0;
        
        for(int i = 0; i < frequencies.size(); i ++) {
            for(int j = 0; j < frequencies.size(); j ++) {
                count += frequencies.get(j).get(i);
            }
            
            for(int j = 0; j < frequencies.size(); j ++) {
                if(frequencies.get(j).get(i) > 0) {
                    probabilities.get(j).set(i, ((double)frequencies.get(j).get(i) / (double)count));
                }
            }
            count = 0;
        }
    }
    
    protected void saveModel() {
        FileOutputStream f1 = null;
        FileOutputStream f2 = null;
        FileOutputStream f3 = null;
        try {
            f1 = new FileOutputStream("wordMap.txt");
            f2 = new FileOutputStream("frequencies.txt");
            f3 = new FileOutputStream("probabilities.txt");
            
            for(int i = 0; i < wordMap.size(); i ++) {
                f1.write((wordMap.get(i)).getBytes());
                if(i != wordMap.size() - 1) {
                    f1.write((",").getBytes());
                }
            }
            
            for(int i = 0; i < frequencies.size(); i ++) {
                for(int j = 0; j < frequencies.size(); j ++) {
                    f2.write((frequencies.get(i).get(j)).toString().getBytes());
                    if(j != frequencies.size() - 1) {
                        f2.write((",").getBytes());
                    } else {
                        f2.write(("\n").getBytes());
                    }
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(f1 != null) {
                    f1.close();
                }
            }catch(IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
    
    protected void prettyPrint(int matrix, int x1, int x2, int y1, int y2) {
        System.out.printf("%12s", " ");
        
        for(int i = x1; i < x2; i ++) {
            System.out.printf("%12s", wordMap.get(i));
        }
        
        System.out.printf("\n");
        
        for(int i = y1; i < y2; i ++) {
            System.out.printf("%12s", wordMap.get(i));
            for(int j = x1; j < x2; j ++) {
                if(matrix == 0) {
                    System.out.printf("%12d", frequencies.get(i).get(j));
                } else {
                    System.out.printf("%12f", probabilities.get(i).get(j));
                }
            }
            System.out.printf("\n");
        }
    }
}