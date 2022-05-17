package pl.edu.mimuw.matrix;

public class ConstantColumn extends OneDimDegenerated{

    private ConstantColumn(int rows, double... rowValues) {
        super(Shape.matrix(rows,rowValues.length),rowValues);
    }

    public static ConstantColumn makeConstantColumn(int rows, double... rowValues){
        assert rows > 0 & rowValues.length>0;
        return new ConstantColumn(rows,rowValues);
    }

    public IDoubleMatrix getCopy() {
        return new ConstantRow(this.shape.rows, this.values);
    }

    protected IDoubleMatrix switchData(double... data){
        return new ConstantColumn(this.shape.rows, data);
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        return this.getValue(column);
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
                newData[i][j] += this.getValue(j);
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
                    temp += this.getValue(k) * other.get(k, j);
                }
                newData[i][j] = temp;
            }
        }
        return Full.makeFull(newData);
    }

    public double frobeniusNorm() {
        double result = 0;
        for (int i = 0; i < this.shape.columns; i++) {

            result += this.shape.rows * (this.getValue(i)) * (this.getValue(i));
        }
        return Math.sqrt(result);
    }

    public double normOne() {

        double max = 0;
        for (int i = 0; i < this.shape.columns; i++) {
            max = Double.max(max, Math.abs(this.getValue(i)));
        }
        return this.shape.rows * max;
    }

    public double normInfinity() {
        double temp = 0;
        for (int j = 0; j < this.shape.columns; j++) {
            temp += Math.abs(this.getValue(j));
        }
        return temp;
    }

    public String toString() {
        printDimentions();
        String helpeString = "";
        for(double elem : this.values){
            helpeString += Double.toString(elem)+" ";
        }
        helpeString += "\n";
        return new String(new char[this.shape().rows]).replace("\0", helpeString);
    }
    
}
