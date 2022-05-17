package pl.edu.mimuw;

import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.IDoubleMatrix;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class Main {

  public static void main(String[] args) {
    System.out.println("Macierz jednostkowa:\n");
    IDoubleMatrix iden = DoubleMatrixFactory.identity(10);
    System.out.println(iden.toString());
    System.out.println("Macierz zerowa:\n");
    IDoubleMatrix zero = DoubleMatrixFactory.zero(Shape.matrix(10, 10));
    System.out.println(zero.toString());
    System.out.println("Macierz diagonalna:\n");
    IDoubleMatrix diag = DoubleMatrixFactory.diagonal(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(diag.toString());
    System.out.println("Macierz antydiagonalna:\n");
    IDoubleMatrix antidiag = DoubleMatrixFactory.antiDiagonal(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(antidiag.toString());
    System.out.println("Macierz stała:\n");
    IDoubleMatrix cons = DoubleMatrixFactory.constant(Shape.matrix(10, 10), 42);
    System.out.println(cons.toString());
    System.out.println("Macierz wierszowa:\n");
    IDoubleMatrix constRow = DoubleMatrixFactory.constantRow(10, 2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(constRow.toString());
    System.out.println("Macierz kolumnowa:\n");
    IDoubleMatrix constColumn = DoubleMatrixFactory.constantColumn(10, 2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(constColumn.toString());
    System.out.println("Macierz pełna:\n");
    IDoubleMatrix full = DoubleMatrixFactory.full(new double[][] {
        new double[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100},
        new double[] { 11, 21, 31, 41, 51, 61, 71, 81, 91, 101},
        new double[] { 12, 22, 32, 42, 52, 62, 72, 82, 92, 102},
        new double[] { 13, 23, 33, 43, 53, 63, 73, 83, 93, 103},
        new double[] { 14, 24, 34, 44, 54, 64, 74, 84, 94, 104},
        new double[] { 15, 25, 35, 45, 55, 65, 75, 85, 95, 105},
        new double[] { 16, 26, 36, 46, 56, 66, 76, 86, 96, 106},
        new double[] { 17, 27, 37, 47, 57, 67, 77, 87, 97, 107},
        new double[] { 18, 28, 38, 48, 58, 68, 78, 88, 98, 108},
        new double[] { 19, 29, 39, 49, 59, 69, 79, 89, 99, 109},
    });
    System.out.println(full.toString());
    System.out.println("Macierz niereguralna:\n");
    IDoubleMatrix sparse = DoubleMatrixFactory.sparse(Shape.matrix(10, 10),
    MatrixCellValue.cell(0, 0, 1),
    MatrixCellValue.cell(1, 1, 2),
    MatrixCellValue.cell(0, 2, 3),
    MatrixCellValue.cell(5, 8, 4),
    MatrixCellValue.cell(1, 1, 5),
    MatrixCellValue.cell(3, 9, 6),
    MatrixCellValue.cell(7, 9, 16)
  );
  System.out.println(sparse.toString());
  System.out.println("Wektor:\n");
  IDoubleMatrix vec = DoubleMatrixFactory.vector(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(vec.toString());

    System.out.println("dodawanie macierzy:\n");
    System.out.println(cons.plus(antidiag).toString());
    System.out.println("mnożenie macierzy:\n");
    System.out.println(constRow.times(diag).toString());
    System.out.println("odejmowanie macierzy:\n");
    System.out.println(cons.minus(full).toString());
    System.out.println("ododawanie skalara:\n");
    System.out.println(diag.plus(16).toString());
    System.out.println("odejmowanie skalara:\n");
    System.out.println(constColumn.minus(7).toString());
    System.out.println("mnożenie przez skalar:\n");
    System.out.println(full.times(0.5).toString());

    System.out.println("norma Frobeniusa:");
    System.out.println(full.frobeniusNorm());
    System.out.println("norma maksimum:");
    System.out.println(full.normInfinity());
    System.out.println("norma pierwsza:");
    System.out.println(full.normOne());
  }
}
