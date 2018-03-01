package markovModel;

import java.io.*;
import java.io.IOException;

final class MatrixReader
{
    public static Matrix readFromFile(String filename, final Class<Matrix> readObjectType) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        // Path file_path = Paths.get(filename);
        FileInputStream in_file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(in_file);
        return readObjectType.cast(in.readObject());
    }
}