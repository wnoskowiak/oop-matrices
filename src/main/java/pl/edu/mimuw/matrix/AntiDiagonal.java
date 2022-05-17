package pl.edu.mimuw.matrix;

public class AntiDiagonal extends OneDimDegenerated{

    public IDoubleMatrix getCopy(){
        return DoubleMatrixFactory.antiDiagonal(this.values);
    }

    private AntiDiagonal(double... diagonalValues) {
        super(Shape.matrix(diagonalValues.length, diagonalValues.length), diagonalValues);
    }

    public static AntiDiagonal makeAntiDiagonal(double... diagonalValues){
        assert diagonalValues.length > 0;
        return new AntiDiagonal(diagonalValues);
    }

    protected IDoubleMatrix switchData(double... data){
        return DoubleMatrixFactory.antiDiagonal(data);
    }

    protected double getValue(int index) {
        return this.values[index];
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        if (row != (this.shape.columns -1  - column)) {
            return 0;
        }
        return getValue(row);
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        if (other.getClass().getName() == Zero.class.getName()) {
            return this.getCopy();
        }
        if (other.getClass().equals(this.getClass())) {
            AntiDiagonal temp = (AntiDiagonal) other;
            double[] newAntiDiagonalValues = new double[this.shape.rows];
            for (int i = 0; i < this.shape.rows; i++) {
                newAntiDiagonalValues[i] = this.getValue(i) + temp.getValue(i);
            }
            return DoubleMatrixFactory.antiDiagonal(newAntiDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            newData[i][(this.shape.columns-1-i)] += getValue(i);
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return DoubleMatrixFactory.antiDiagonal(this.values);
        }
        if (other.getClass().equals(this.getClass())) {
            double[] newDiagonalValues = new double[this.shape.rows];
            AntiDiagonal temp = (AntiDiagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newDiagonalValues[i] = this.getValue(i) * temp.getValue(this.shape.rows-1-i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        if (other.getClass().equals(Diagonal.class)){
            double[] newAntiDiagonalValues = new double[this.shape.rows];
            Diagonal temp = (Diagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newAntiDiagonalValues[i] = this.getValue(i) * temp.getValue(this.shape.rows-1-i);
            }
            return DoubleMatrixFactory.antiDiagonal(newAntiDiagonalValues);
        }
        double[][] newData = other.data();
        double[] temp;
        for (int i = 0; i < this.shape.columns/2; i++) {
            temp = newData[i];
            newData[i] = newData[newData.length-1-i];
            newData[i] = temp;
        }
        for (int i = 0; i < this.shape.rows; i++) {
            for(int j = 0; j<newData[0].length; j++){
                newData[i][j] *= this.getValue(i);
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public double normOne() {
        double max = Double.NEGATIVE_INFINITY;
        for (double cur : this.values) {
            max = Math.max(max, Math.abs(cur));
        }
        return max;
    }

    public double normInfinity() {
        return this.normOne();
    }

    public double frobeniusNorm() {
        double result = 0;
        for (double cur : this.values) {
            result += cur * cur;
        }
        return Math.sqrt(result);
    }

    private String getZeros(int i) {
        switch (i) {
            case 0:
                return "";
            case 1:
                return "0 ";
            case 2:
                return "0 0 ";
            default:
                return "0···0 ";
        }
    }

    public String toString() {

        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getZeros(this.shape.columns -1-i) + Double.toString(this.getValue(this.shape.columns -1-i)) + " "
                    + this.getZeros(i) + "\n";
        }
        return result;
    }

}
