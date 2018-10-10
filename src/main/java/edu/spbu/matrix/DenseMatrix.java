package edu.spbu.matrix;
import java.io.*;
import java.util.Scanner;

public class DenseMatrix implements Matrix
{
    public int rows = 0;
    public int cols = 0;
    public double[][] matrix;

    @Override
    public int amountColumns() {
        return cols;
    }

    @Override
    public int amountRows() {
        return rows;
    }

    @Override
    public void changeMatrix(int x, int y)
    {
        rows = x;
        cols = y;
        matrix = new double[rows][cols];
    }

    @Override
    public double getElem(int row, int col) {
        return matrix[row][col];
    }

    /**
     * @param fileName
     */
    public DenseMatrix(String fileName) throws IOException {
        if(fileName.trim().length()==0)
            return;
        Scanner scan = new Scanner(new File(fileName));
        StringBuilder sb = new StringBuilder();
        int lenRow = 1;
        int lenCol = 1;
        String str = scan.nextLine();
        sb.append(str);
        sb.append(" ");
        for(int i=0; i < str.length(); i++)
        {
            char sym = str.charAt(i);
            if (sym == ' ') {
                lenRow++;
            }
        }
        while (scan.hasNextLine())
        {
            str = scan.nextLine();
            sb.append(str);
            sb.append(" ");
            lenCol++;
        }
        scan.close();
        changeMatrix(lenCol, lenRow);
        String text = new String(sb);
        String [] numbers = text.split(" ");
        int i, j, index=0;
        for(i = 0; i < lenCol; i++)
        {
            for(j = 0; j < lenRow; j++)
            {
                matrix[i][j] = Double.parseDouble(numbers[index]);
                index++;
            }
        }
    }

    /**
     * однопоточное умножение матриц
     * должно поддерживаться для всех 4-х вариантов
     *
     * @param o
     * @return
     */

    @Override
    public Matrix mul(Matrix o) throws IOException {

        int newRows = rows;
        int newCols = o.amountColumns();
        DenseMatrix result = new DenseMatrix("");
        result.changeMatrix(newRows,newCols);
        for (int i = 0; i < newRows; i++)
        {
            for (int j = 0; j < newCols; j++)
            {
                for (int k = 0; k < cols; k++)
                {
                    result.matrix[i][j] += matrix[i][k] * o.getElem(k,j);
                }
            }
        }
        return result;
    }

    /**
     * многопоточное умножение матриц
     *
     * @param o
     * @return
     */
    @Override public Matrix dmul(Matrix o)
    {
        return null;
    }

    /**
     * спавнивает с обоими вариантами
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Matrix))
            return false;
        Matrix Q = ((Matrix)o);
        if(Q.amountRows() != rows || Q.amountColumns() != cols)
            return false;
        int i, j;
        for(i = 0; i < rows; i++)
            for(j=0; j< cols; j++)
                if(matrix[i][j] != Q.getElem(i,j))
                    return false;
        return true;
    }
}