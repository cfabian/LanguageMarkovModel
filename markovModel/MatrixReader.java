package markovModel;

import java.io.*;

final static class MatrixReader
{


    public Matrix readFromFile(String filename)
    {
        // Path file_path = Paths.get(filename);
        FileInputStream in_file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(in_file);
        out.readObject(mat);
    }
}