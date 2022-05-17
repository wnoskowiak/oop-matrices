package pl.edu.mimuw.matrix;

import java.util.Comparator;

public final class MatrixCellValue {

  public final int row;
  public final int column;
  public final double value;

  public static Comparator<MatrixCellValue> MatrixCellValueComparator = new Comparator<MatrixCellValue>(){
    @Override
     public int compare(MatrixCellValue c1, MatrixCellValue c2)
     {
       int temp = Integer.compare(c1.row, c2.row);
       if(temp == 0){
         return Integer.compare(c1.column, c2.column);
       }
       return temp;
     }
 };

  public MatrixCellValue(int row, int column, double value) {
    this.column = column;
    this.row = row;
    this.value = value;
  }

  @Override
  public String toString() {
    return "{" + value + " @[" + row + ", " + column + "]}";
  }

  public static MatrixCellValue cell(int row, int column, double value) {
    return new MatrixCellValue(row, column, value);
  }
}
