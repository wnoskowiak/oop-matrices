package pl.edu.mimuw;

import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.IDoubleMatrix;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class Main {

  public static void main(String[] args) {
    IDoubleMatrix iden = DoubleMatrixFactory.identity(10);
    System.out.println(iden.toString());
    IDoubleMatrix zero = DoubleMatrixFactory.zero(Shape.matrix(10, 10));
    System.out.println(zero.toString());
    IDoubleMatrix diag = DoubleMatrixFactory.diagonal(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(diag.toString());
    IDoubleMatrix antidiag = DoubleMatrixFactory.antiDiagonal(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(antidiag.toString());
    IDoubleMatrix cons = DoubleMatrixFactory.constant(Shape.matrix(10, 10), 42);
    System.out.println(cons.toString());
    IDoubleMatrix constRow = DoubleMatrixFactory.constantRow(10, 2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(constRow.toString());
    IDoubleMatrix constColumn = DoubleMatrixFactory.constantColumn(10, 2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(constColumn.toString());
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
    IDoubleMatrix sparse = DoubleMatrixFactory.sparse(Shape.matrix(10, 10),
    MatrixCellValue.cell(0, 0, 1),
    MatrixCellValue.cell(1, 1, 2),
    MatrixCellValue.cell(0, 2, 3),
    MatrixCellValue.cell(5, 8, 4),
    MatrixCellValue.cell(1, 1, 5),
    MatrixCellValue.cell(3, 9, 6)
  );
  System.out.println(sparse.toString());
  IDoubleMatrix vec = DoubleMatrixFactory.vector(2., 4., 8., 10., 12., 14., 16., 18., 20., 22.);
    System.out.println(vec.toString());

    System.out.println(cons.plus(antidiag).toString());
    System.out.println(constRow.times(diag).toString());
    System.out.println(cons.minus(full).toString());
    System.out.println(diag.plus(16).toString());
    System.out.println(constColumn.minus(7).toString());
    System.out.println(full.times(0.5).toString());

    System.out.println(full.frobeniusNorm());
    System.out.println(full.normInfinity());
    System.out.println(full.normOne());
  }
}
