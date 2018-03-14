package markovModel;

import java.io.*;
import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

final class Matrix<T> implements Serializable
{
    private Vector<T> m_matrix = null;
    private int m_number_of_columns = 0, m_number_of_rows = 0;
    private static final long serialVersionUID = 1L; // Needed to write the object to a file.


    public Matrix()
    {
    }

    public Matrix(int num_rows, int num_columns)
    {
        System.err.println("Number of Rows: " + Integer.toString(num_rows) + "        Number of Columns: " + Integer.toString(num_columns));
        m_number_of_columns = num_columns;
        m_number_of_rows = num_rows;
        System.err.println("Number of Rows in class: " + Integer.toString(num_rows) + "        Number of Columns: " + Integer.toString(num_columns));        
        m_matrix = new Vector<T>(num_columns * num_rows); // This constructor only sets the inital capacity, annoying....
        m_matrix.setSize(num_columns * num_rows); // This sets the size but only fills it with nulls.
        System.err.println("Size of Matrix: " + Integer.toString(m_matrix.size()));
    }


    public T at(int row, int col)
    {
        // Bounds checkint is already done by the array object so no need to do that.
        return m_matrix.get(row * m_number_of_columns + col);
    }

    public T set(int row, int col, T val)
    {
        // Bounds checkint is already done by the array object so no need to do that.
        return m_matrix.set(row * m_number_of_columns + col, val);
    }

    // public void fillMatrixWithDefaultConstructors(Class class_type)
    // {
    //     // Populates the List with default constructors.
    //     try{
    //         Constructor<T> cons = class_type.getConstructor();
    //         for(int i = 0; i < m_number_of_columns * m_number_of_rows; i++)
    //             m_matrix.add(cons.newInstance());  
    //     }
    //     catch(IllegalAccessException e) {}
    //     catch(IllegalArgumentException e) {}
    //     catch(InstantiationException e) {}
    //     catch(InvocationTargetException e) {}
    //     catch(ExceptionInInitializerError e) {}
    //     catch(NoSuchMethodException e) {}
    // }
    public void setup(int num_rows, int num_columns)
    {
        m_number_of_columns = num_columns;
        m_number_of_rows = num_rows;
        m_matrix = new Vector<T>(num_columns * num_rows);   
        m_matrix.setSize(num_columns * num_rows); // This sets the size but only fills it with nulls.
    }

    public final int getNumberOfColumns()        { return m_number_of_columns; }
    public final int getNumberOfRows()           { return m_number_of_rows; }
    public final int getTotalNumberOfIndicies()  { return m_matrix.size(); }
    public final int size()                      { return m_matrix.size(); }
};