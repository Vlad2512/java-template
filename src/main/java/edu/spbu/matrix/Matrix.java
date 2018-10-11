package edu.spbu.matrix;

import java.io.IOException;

/**
 *
 */



public interface Matrix
{

  int amountColumns();
  int amountRows();
  void changeMatrix(int x, int y);
  double getElem(int x, int y);

  /**
   * однопоточное умнджение матриц
   * должно поддерживаться для всех 4-х вариантов
   * @param o
   * @return
   */

  Matrix mul(Matrix o) throws IOException;

  /**
   * многопоточное умножение матриц
   * @param o
   * @return
   */
  Matrix dmul(Matrix o);

}
