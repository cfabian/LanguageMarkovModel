package markovModel;

import java.io.*;
import java.util.*;

public class MarkovModel<T> {
    

    class IndexFrequencyPair
    {
        public IndexFrequencyPair(int index, int freq)
        {
            this.index = index;
            this.freq = freq;
        }
        public int index;
        public int freq; 
    }
    protected HashMap<String, IndexFrequencyPair> string_to_index = new HashMap<String, IndexFrequencyPair>();
    protected Matrix<Double> m_probabilities = null;
    
    protected List<String> wordMap = null;   
    // protected Vector<Vector<Integer>> frequencies = new Vector<Vector<Integer>>();
    // protected Vector<Vector<Double>> probabilities = new Vector<Vector<Double>>();
    
    public MarkovModel(String fileName) {
        String[] words = WordParser(fileName);
        // for(int i = 0; i < words.length; i++)
        // {
        //     System.err.println(words[i]);
        // }
        makeModel(words);
        // prettyPrint(1, 0, 15, 0, 15);
        generateText(20);
    }
    
    protected String generateText(int number_of_words) {
        int min = 0;
        int max = string_to_index.size() - 1;
        Random rand = new Random();
        int word = rand.nextInt((max - min) + 1) + min;
        
        
        // Gets the first word, each has an equal probability. Essentially.
        System.out.printf("%s", wordMap.get(word).substring(0, 1).toUpperCase() + wordMap.get(word).substring(1));
        
        double random_number = 0.0;
        double probability_sum = 0.0;
        int previous_word_index = word;
        for(int i = 0; i < number_of_words; i++)
        {
            random_number = rand.nextDouble();
            probability_sum = 0.0;
            // Can probably speed this up if we precompute the sums and then 
            // perform a modified binary search.
            for(int j = 0; j < m_probabilities.getNumberOfColumns(); j++)
            {
                probability_sum += m_probabilities.at(previous_word_index, j);
                if(probability_sum >= random_number)
                {
                    System.out.printf(" %s", wordMap.get(j));
                    previous_word_index = j;
                    break;
                }
            }
        }
        // for(int i = 0; i < length; i ++) {
        //     Double randNum = rand.nextDouble();
        //     Double s = 0.0;
        //     for(int j = 0; j < probabilities.size(); j ++) {
        //         s += probabilities.get(j).get(word);
        //         if(s >= randNum) {
        //             System.out.printf(" %s", wordMap.get(j));
        //             word = j;
        //             break;
        //         }
        //     }
        // }
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

        // Overpopulate the string list (Probably a bad idea).
        wordMap = new ArrayList<String>(words.length); 
        // Populate the indicies first then, actually
        for(String i : words)
        {
            if(string_to_index.containsKey(i))
            {
                IndexFrequencyPair temp = string_to_index.get(i);
                temp.freq += 1;
            }
            else
            {
                wordMap.add(string_to_index.size(), i);                
                string_to_index.put(i, new IndexFrequencyPair(string_to_index.size(), 1));
            }
        }
        
        // Make a 2d matrix
        m_probabilities = new Matrix<Double>(string_to_index.size(), string_to_index.size());
        // m_probabilities.fillMatrixWithDefaultConstructors(Double.class);
        // Need to populate the matrix with default values.
        for(int i = 0; i < m_probabilities.getNumberOfRows(); i++)
            for(int j = 0; j < m_probabilities.getNumberOfColumns(); j++)
                m_probabilities.set(i, j, new Double(0.0));
        IndexFrequencyPair current = null;
        IndexFrequencyPair prev = string_to_index.get(words[0]); // Get the index for the first word.
        for(int i = 1; i < words.length; i++)
        {
            current = string_to_index.get(words[i]); // Get the index/frequency for the next word.
            // System.err.println("i: " + Integer.toString(prev.index) + "    j: " + Integer.toString(current.index));
            m_probabilities.set(prev.index, current.index, m_probabilities.at(prev.index, current.index) + (1.0/prev.freq)); // Add the frequency to the pairing.
            prev = current;
        }

        // Need to do a sum division of the frequencies. (Maybe)
        // for(int i = 0; i < m_probabilities.getNumberOfRows(); i++)
        // {
        //     for(int j = 0; j < m_probabilities.getNumberOfColumns(); j++)
        //     {

        //     }
        // }
        // What the hell does this line do.
        // frequencies.get(wordMap.indexOf(words[i])).set(wordMap.indexOf(words[i - 1]), frequencies.get(wordMap.indexOf(words[i])).get(wordMap.indexOf(words[i - 1])) + 1);

        
        // Dont need this
        // int count = 0;
        
        // for(int i = 0; i < frequencies.size(); i ++) {
        //     for(int j = 0; j < frequencies.size(); j ++) {
        //         count += frequencies.get(j).get(i);
        //     }
            
        //     for(int j = 0; j < frequencies.size(); j ++) {
        //         if(frequencies.get(j).get(i) > 0) {
        //             probabilities.get(j).set(i, ((double)frequencies.get(j).get(i) / (double)count));
        //         }
        //     }
        //     count = 0;
        // }
    }
    
    protected void saveModel() {
        
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
                // if(matrix == 0) {
                    // System.out.printf("%12d", frequencies.get(i).get(j));
                    // System.out.printf("%12d", string_to_index.get(word_Map.get(i)).index string_to_inde frequencies.get(i).get(j));
                // } else {
                    System.out.printf("%12f", m_probabilities.at(i, j));
                // }
            }
            System.out.printf("\n");
        }
    }
}