package pl.edu.mimuw.matrix;

public class ConstantRow extends OneDimDegenerated {

    ConstantRow(int columns, double... rowValues) {
        super(Shape.matrix(rowValues.length, columns),rowValues);
    }

    public static ConstantRow makeConstantRow(int rows, double... rowValues){
        assert rows > 0 & rowValues.length>0;
        return new ConstantRow(rows,rowValues);
    }

    protected IDoubleMatrix switchData(double... data){
        return new ConstantRow(this.shape.columns, data);
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        return this.getValue(row);
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        assertPlus(other);
        if(other.getClass().equals(this.getClass())){
            ConstantRow newOther = (ConstantRow)other;
            double[] newDiagonalValues = new double[this.values.length];
            for (int i = 0; i < this.values.length; i++) {
                newDiagonalValues[i] = newOther.getValue(i) * this.getValue(i);
            }
            return new ConstantRow(this.shape.columns, newDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                newData[i][j] += this.getValue(i);
            }
        }
        return Full.makeFull(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other){
        this.assertTimes(other);
        if(other.getClass().equals(Identity.class)) {
            return this.getCopy();
        }
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        if(other.getClass().equals(Zero.class)){
            return Zero.makeZero(newShape);
        }
        double[][] newData = new double[newShape.rows][newShape.columns];
        double temp;
        for (int i = 0; i < newShape.rows; i++) {
            for (int j = 0; j < newShape.columns; j++) {
                temp = 0;
                for (int k = 0; k < this.shape.columns; k++) {
                    temp += other.get(k, j);
                }
                newData[i][j] = this.getValue(i)*temp;
            }
        }
        return Full.makeFull(newData);
    }

    public double frobeniusNorm() {
        double result = 0;
        for (int i = 0; i < this.shape.rows; i++) {

            result += this.shape.columns * (this.getValue(i)) * (this.getValue(i));
        }
        return Math.sqrt(result);
    }

    public double normOne() {
        double temp = 0;
        for (int j = 0; j < this.shape.rows; j++) {
            temp += Math.abs(this.getValue(j));
        }
        return temp;
    }

    public double normInfinity() {
        double max = 0;
        for (int i = 0; i < this.shape.rows; i++) {
            max = Double.max(max, Math.abs(this.getValue(i)));
        }
        return this.shape.columns * max;
    }

    public String toString() {
        printDimentions();
        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getChar(this.shape.columns,Double.toString(this.getValue(i)))+ "\n";
        }
        return result;
    }

}
