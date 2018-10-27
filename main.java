import markovModel.*;
import java.io.File;
class main
{
    public static void main(String[] args)
    {
        if(args.length > 0) {
            File f = new File(args[0]);
            if(f.exists() && !f.isDirectory()) {
                MarkovModel a = new MarkovModel<>(args[0]);
                String text = a.generateText(20);
                System.out.printf("%s\n", text);
            } else {
                System.out.printf("%s is not a valid file.\n", args[0]);
                return;
            }
        } else {
            MarkovModel a = new MarkovModel<>("test.txt");
            String text = a.generateText(20);
            System.out.printf("%s\n", text);
        }
    }
}