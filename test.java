import markovModel.MarkovModel;

public class test {
    
    public static void main (String args[]) {
        MarkovModel M = new MarkovModel("test.txt");
        
        M.generateText(20);
        // M.saveModel();
    }
}