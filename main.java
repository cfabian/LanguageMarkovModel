import markovModel.*;
class main
{
    public static void main(String[] args)
    {
        MarkovModel a = new MarkovModel<>("test.txt");
        String text = a.generateText(20);
        System.out.printf("%s\n", text);
    }
}