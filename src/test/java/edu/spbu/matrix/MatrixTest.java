package edu.spbu.matrix;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MatrixTest
{
  /**
   * ожидается 4 таких теста
   */

  @Test
  public void sizeM() throws IOException {
    DenseMatrix m1 = new DenseMatrix("Matrix1.txt");
    System.out.println(m1.rows);
    System.out.println(m1.cols);
  }


  @Test
  public void mulDD() throws IOException {
    Matrix m1 = new DenseMatrix("Matrix1.txt");
    Matrix m2 = new DenseMatrix("Matrix2.txt");
    Matrix expected = new DenseMatrix("Result.txt");
    assertEquals(expected, m1.mul(m2));
  }
}
