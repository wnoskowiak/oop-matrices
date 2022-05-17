package pl.edu.mimuw.matrix;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Sparse extends GenericMatrix{

    protected final ArrayList<MatrixCellValue> values;

    private Sparse(Shape shape, ArrayList<MatrixCellValue> values){
        super(shape);
        ArrayList<MatrixCellValue> vals = new ArrayList<MatrixCellValue>(values);
        Collections.sort(vals, MatrixCellValue.MatrixCellValueComparator);
        this.values = vals;
    }

    public static Sparse makeSparse(Shape shape, MatrixCellValue... values){
        for(MatrixCellValue elem : values){
            shape.assertInShape(elem.row,elem.column);
        }
        ArrayList<MatrixCellValue> vals = new ArrayList<MatrixCellValue>(Arrays.asList(values));
        return new Sparse(shape, vals);
    }

    public IDoubleMatrix times(IDoubleMatrix other){
        super.assertTimes(other);
        if(other.getClass().equals(this.getClass())){
            Sparse newOther = (Sparse)other;
            Shape newShape = this.shape.shapeAfterMultiply(newOther.shape());
            ArrayList<MatrixCellValue> newArray = new ArrayList<MatrixCellValue>();
            for(MatrixCellValue elem1 : this.values){
                for(MatrixCellValue elem2 : newOther.values){
                    if(elem1.column == elem2.row){
                        newArray = addMatrixElement(newArray, (new MatrixCellValue(elem1.row, elem2.column, elem1.value*elem2.value)));
                    }
                }
            }
            return new Sparse(newShape, newArray);
        }
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        double[][] newData = new double[newShape.rows][newShape.columns];
        for(MatrixCellValue elem : this.values){
            for(int i = 0; i<newShape.columns; i++)
            newData[elem.row][i] += elem.value*other.get(elem.column,i);
        }
        return Full.makeFull(newData);
    }

    public IDoubleMatrix getCopy() {
        return new Sparse(this.shape, this.values);
    }

    private ArrayList<MatrixCellValue> addMatrixElement(ArrayList<MatrixCellValue> list, MatrixCellValue elem){
        MatrixCellValue temp;
        for(int i = 0; i<list.size(); i++){
            temp = list.get(i);
            if(temp.row ==elem.row & temp.column == elem.column){
                list.set(i,(new MatrixCellValue(elem.row, elem.column, temp.value+elem.value)));
                return list;
            }
        }
        list.add((new MatrixCellValue(elem.row, elem.column, elem.value)));
        return list;
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        for(MatrixCellValue elem : this.values){
            if(elem.row == row && elem.column == column){
                return elem.value;
            }
        }
        return 0;
    }

    public double frobeniusNorm() {
        double result = 0;
        for (int i = 0; i < values.size(); i++) {
            result += this.values.get(i).value*this.values.get(i).value;
        }
        return Math.sqrt(result); 
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        this.assertPlus(other);
        if(other.getClass().equals(this.getClass())){
            Sparse newOther = (Sparse)other;
            ArrayList<MatrixCellValue> newArray = new ArrayList<MatrixCellValue>(this.values);
            for(MatrixCellValue elem : newOther.values){
                newArray = this.addMatrixElement(newArray, elem);
            }
            return new Sparse(this.shape,newArray);
        }
        double[][] newData = other.data();
        for(MatrixCellValue elem : this.values) {
            newData[elem.row][elem.column] += elem.value;
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix times(double scalar) {
        MatrixCellValue[] newValues = new MatrixCellValue[this.values.size()];
        for(int i = 0; i<this.values.size(); i++){
            MatrixCellValue temp = this.values.get(i);
            newValues[i] = new MatrixCellValue(temp.row, temp.column, temp.value*scalar);
        }
        return DoubleMatrixFactory.sparse(this.shape, newValues);
    }

    // public IDoubleMatrix plus(double scalar) {
    //     this.assertPlus(scalar);
    //     int temp;
    //     MatrixCellValue tmp;
    //     ArrayList<MatrixCellValue> vals =  new ArrayList<MatrixCellValue>(this.values);
    //     for(int i = 0; i<this.shape.rows; i++) {
    //         temp = this.getIndex(i,i);
    //         if(temp< 0){
    //             vals.add(new MatrixCellValue(i, i, scalar));
    //         }
    //         tmp = new MatrixCellValue(i, i, scalar + this.values.get(temp).value);
    //         vals.set(temp, tmp);
    //     }
    //     return new Sparse(this.shape, vals);
    // }

    public double normInfinity() {
        double result = Double.MIN_VALUE, temp;
        for(int i = 0; i<this.shape.rows; i++){
            temp = 0;
            for(MatrixCellValue elem : this.values){
                if(elem.row == i){
                    temp += Math.abs(elem.value);
                }
            }
            result = Double.max(result, temp);
        }
        return result;
    }

    public double normOne() {
        double result = Double.MIN_VALUE, temp;
        for(int i = 0; i<this.shape.columns; i++){
            temp = 0;
            for(MatrixCellValue elem : this.values){
                if(elem.column == i){
                    temp += Math.abs(elem.value);
                }
            }
            result = Double.max(result, temp);
        }
        return result;
    }

}
