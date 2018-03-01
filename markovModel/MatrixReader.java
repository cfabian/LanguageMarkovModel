package markovModel;

import java.io.*;

final class MatrixReader
{
    public static Matrix readFromFile(String filename)
    {
        // Path file_path = Paths.get(filename);
        FileInputStream in_file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(in_file);
        in.readObject(mat);
    }
}