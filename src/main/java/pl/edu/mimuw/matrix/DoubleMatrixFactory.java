package pl.edu.mimuw.matrix;


public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values) {
    return Sparse.newSparse(shape,values);
     // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix full(double[][] values) {
    return Full.MakeFull(values);
  }

  public static IDoubleMatrix identity(int size) {
    return new Identity(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return new Diagonal(diagonalValues);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return new AntiDiagonal(antiDiagonalValues);
  }

  public static IDoubleMatrix vector(double... values) {
    double[][] vals = new double[values.length][1];
    for(int i =0; i<values.length; i++){
      vals[i][0] = values[i];
    }
    return full(vals);
  }

  public static IDoubleMatrix zero(Shape shape) {
    return new Zero(shape);
  }
}
