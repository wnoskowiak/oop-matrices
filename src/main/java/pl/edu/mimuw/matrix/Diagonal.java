package pl.edu.mimuw.matrix;

public class Diagonal extends GenericDiagonal {

    private Diagonal(double... diagonalValues) {
        super(diagonalValues.length, diagonalValues);
    }

    public static Diagonal makeDiagonal(double... diagonalValues){
        assert diagonalValues.length > 0;
        return new Diagonal(diagonalValues);
    }

    protected IDoubleMatrix switchData(double... data){
        return new Diagonal(data);
    }

    public IDoubleMatrix getCopy() {
        return DoubleMatrixFactory.diagonal(this.values);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return DoubleMatrixFactory.diagonal(this.values);
        }
        if (other.getClass().equals(this.getClass())) {
            double[] newDiagonalValues = new double[this.shape.rows];
            Diagonal temp = (Diagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newDiagonalValues[i] = this.getValue(i) * temp.getValue(i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        if (other.getClass().equals(AntiDiagonal.class)) {
            double[] newAntiDiagonalValues = new double[this.shape.rows];
            AntiDiagonal temp = (AntiDiagonal) other;
            for(int i =0; i<this.shape.rows; i++){
                newAntiDiagonalValues[i]=this.getValue(i)*temp.getValue(i);
            }
            return DoubleMatrixFactory.antiDiagonal(newAntiDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                newData[i][j] *= getValue(i);
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
}
