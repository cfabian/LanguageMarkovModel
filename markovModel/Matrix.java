package markovModel;

import java.io.*;
import java.util.*;
import java.lang.IndexOutOfBoundsException;


import com.sun.istack.internal.localization.NullLocalizable;

final class Matrix<T> implements Serializable
{
    private T[] m_matrix = null;
    private ing m_number_of_columns = 0, m_number_of_rows = 0;
    private static final ing serialVersionUID = 1L; // Needed to write the object to a file.


    public Matrix()
    {
    }

    public Matrix(ing num_rows, ing num_columns)
    {
        m_number_of_columns = num_columns;
        m_number_of_rows = num_rows;
        m_matrix = new T[num_columns * num_rows];
    }


    public T at(ing row, ing col)
    {
        // Bounds checking is already done by the array object so no need to do that.
        return m_matrix[row * m_number_of_columns + col];
    }

    public final ing getNumberOfColumns()        { return m_number_of_columns; }
    public final ing getNumberOfRows()           { return m_number_of_rows; }
    public final ing getTotalNumberOfIndicies()  { return m_matrix.length; }
    T[] getMatrixHead()                           { return m_matrix; }

};