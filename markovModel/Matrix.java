package markovModel;

import java.io.*;
import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.lang.reflect.Array;


import com.sun.istack.internal.localization.NullLocalizable;

final class Matrix<T> implements Serializable
{
    private ArrayList<T> m_matrix = null;
    private int m_number_of_columns = 0, m_number_of_rows = 0;
    private static final long serialVersionUID = 1L; // Needed to write the object to a file.


    public Matrix()
    {
    }

    public Matrix(int num_rows, int num_columns)
    {
        m_number_of_columns = num_columns;
        m_number_of_rows = num_rows;
        m_matrix = new ArrayList<T>(num_columns * num_rows);
    }


    public T at(int row, int col)
    {
        // Bounds checkint is already done by the array object so no need to do that.
        return m_matrix.get(row * m_number_of_columns + col);
    }

    public final int getNumberOfColumns()        { return m_number_of_columns; }
    public final int getNumberOfRows()           { return m_number_of_rows; }
    public final int getTotalNumberOfIndicies()  { return m_matrix.size(); }
};