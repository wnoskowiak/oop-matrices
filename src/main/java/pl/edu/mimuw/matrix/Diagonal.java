package pl.edu.mimuw.matrix;

public class Diagonal extends GenericMatrix {

    private final double[] diagonalValues;

    public Diagonal(double... diagonalValues) {
        super(Shape.matrix(diagonalValues.length, diagonalValues.length));
        this.diagonalValues = diagonalValues;
    }

    private double getDiagonalValues(int index){
        return 
    }

    public double get(int row, int column) {
        shape.assertInShape(row, column);
        if (row != column) {
            return 0;
        }
        return this.diagonalValues[row];
    }

    public IDoubleMatrix times(IDoubleMatrix other){
        this.shape.assertMultiplyable(other.shape());
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        if(other instanceof Diagonal){
            double[] newDiagonalValues = new double[newShape.rows];
            for(int i = 0; i<newShape.rows; i++){
                newDiagonalValues[i] = this.diagonalValues[i]*other.get(i, i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        if(other instanceof Identity){
            return DoubleMatrixFactory.diagonal(this.diagonalValues);
        }
        double[][] newData = new double[newShape.rows][newShape.columns];
        for(int i = 0; i<newShape.rows; i++){
            for(int j = 0; j<newShape.columns; j++){
                newData[i][j]=diagonalValues[i]*other.get(i, j);
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        assert this.shape.equals(other);
        if(other instanceof Diagonal){
            double[] newDiagonalValues = new double[this.shape.rows];
            for(int i = 0; i<this.shape.rows; i++){
                newDiagonalValues[i] = this.diagonalValues[i]+other.get(i, i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        if(other instanceof Identity){
            return DoubleMatrixFactory.diagonal(this.diagonalValues);
        }
        double[][] newData = other.data();
        for(int i = 0; i<this.shape.rows; i++){
                newData[i][i]+=diagonalValues[i];
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix minus(IDoubleMatrix other){
        assert this.shape.equals(other);
        if(other instanceof Diagonal){
            double[] newDiagonalValues = new double[this.shape.rows];
            for(int i = 0; i<this.shape.rows; i++){
                newDiagonalValues[i] = this.diagonalValues[i]-other.get(i, i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        if(other instanceof Identity){
            return DoubleMatrixFactory.diagonal(this.diagonalValues);
        }
        double[][] newData = other.data();
        for(int i = 0; i<this.shape.rows; i++){
                newData[i][i]-=diagonalValues[i];
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix times(double scalar) {
        double[] newDiagonalValues = this.diagonalValues;
        for (int i = 0; i < diagonalValues.length; i++) {
            newDiagonalValues[i] *= scalar;
        }
        return DoubleMatrixFactory.diagonal(newDiagonalValues);
    }

    public IDoubleMatrix plus(double scalar) {
        double[] newDiagonalValues = this.diagonalValues;
        for (int i = 0; i < diagonalValues.length; i++) {
            newDiagonalValues[i] += scalar;
        }
        return DoubleMatrixFactory.diagonal(newDiagonalValues);
    }

    public IDoubleMatrix minus(double scalar) {
        double[] newDiagonalValues = this.diagonalValues;
        for (int i = 0; i < diagonalValues.length; i++) {
            newDiagonalValues[i] -= scalar;
        }
        return DoubleMatrixFactory.diagonal(newDiagonalValues);
    }

    public double normOne() {
        double max = Double.NEGATIVE_INFINITY;
        for (double cur : this.diagonalValues) {
            max = Math.max(max, cur);
        }
        return max;
    }

    public double normInfinity() {
        return this.normOne();
    }

    public double frobeniusNorm(){
        double result = 0;
        for(double cur: this.diagonalValues){
            result += cur*cur;
        }
        return Math.sqrt(result);
    }

}
