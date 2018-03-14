package markovModel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.*;
import java.io.IOException;


final class MatrixWriter
{
    public static void writeToFile(String filename, Matrix mat) throws IOException
    {
        // Path file_path = Paths.get(filename);
        // ByteArrayOutputStream class_data = new ByteArrayOutputStream();
        FileOutputStream out_file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(out_file);
        out.writeObject(mat);
    }
}