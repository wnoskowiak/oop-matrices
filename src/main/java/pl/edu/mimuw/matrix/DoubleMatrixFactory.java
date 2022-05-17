package pl.edu.mimuw.matrix;


public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values) {
    return Sparse.makeSparse(shape,values);
  }

  public static IDoubleMatrix full(double[][] values) {
    return Full.makeFull(values);
  }

  public static IDoubleMatrix identity(int size) {
    return Identity.makeIdentity(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return Diagonal.makeDiagonal(diagonalValues);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return AntiDiagonal.makeAntiDiagonal(antiDiagonalValues);
  }

  public static IDoubleMatrix vector(double... values) {
    double[][] vals = new double[values.length][1];
    for(int i =0; i<values.length; i++){
      vals[i][0] = values[i];
    }
    return full(vals);
  }

  public static IDoubleMatrix zero(Shape shape) {
    return Zero.makeZero(shape);
  }

  public static IDoubleMatrix constant(Shape shape,double value) {
    return Constant.makeConstant(shape, value);
  }

  public static IDoubleMatrix constantColumn(int rows, double... rowValues) {
    return ConstantColumn.makeConstantColumn(rows, rowValues);
  }

  public static IDoubleMatrix constantRow(int columns, double... rowValues) {
    return ConstantRow.makeConstantRow(columns, rowValues);
  }
}
