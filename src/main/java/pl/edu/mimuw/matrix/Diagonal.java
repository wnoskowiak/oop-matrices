package pl.edu.mimuw.matrix;

public class Diagonal extends GenericDiagonal {

    private final double[] diagonalValues;

    protected Diagonal(double... diagonalValues) {
        super(diagonalValues.length);
        this.diagonalValues = diagonalValues;
    }

    public IDoubleMatrix getCopy() {
        return DoubleMatrixFactory.diagonal(this.diagonalValues);
    }

    protected double getDiagonalValues(int index) {
        return this.diagonalValues[index];
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return DoubleMatrixFactory.diagonal(this.diagonalValues);
        }
        if (other.getClass().equals(this.getClass())) {
            double[] newDiagonalValues = new double[this.shape.rows];
            Diagonal temp = (Diagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newDiagonalValues[i] = this.getDiagonalValues(i) * temp.getDiagonalValues(i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                newData[i][j] *= getDiagonalValues(i);
            }
        }
        return DoubleMatrixFactory.full(newData);
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

    public double frobeniusNorm() {
        double result = 0;
        for (double cur : this.diagonalValues) {
            result += cur * cur;
        }
        return Math.sqrt(result);
    }

}
