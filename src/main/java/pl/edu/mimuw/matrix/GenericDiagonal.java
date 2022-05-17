package pl.edu.mimuw.matrix;

public abstract class GenericDiagonal extends GenericDegeneratedMatrix {

    public GenericDiagonal(int size) {
        super(Shape.matrix(size, size));
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        if (row != column) {
            return 0;
        }
        return getValue(row);
    }

    public IDoubleMatrix times(double scalar) {
        if (scalar == 1) {
            return this.getCopy();
        }
        double[] newDiagonalValues = new double[this.shape.rows];
        for (int i = 0; i < this.shape.rows; i++) {
            newDiagonalValues[i] = scalar * this.getValue(i);
        }
        return DoubleMatrixFactory.diagonal(newDiagonalValues);
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        if (other.getClass().getName() == Zero.class.getName()) {
            return this.getCopy();
        }
        if (other.getClass().getSuperclass().equals(this.getClass())) {
            GenericDiagonal temp = (GenericDiagonal) other;
            double[] newDiagonalValues = new double[this.shape.rows];
            for (int i = 0; i < this.shape.rows; i++) {
                newDiagonalValues[i] = this.getValue(i) + temp.getValue(i);
            }
            return DoubleMatrixFactory.diagonal(newDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            newData[i][i] += getValue(i);
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix plus(double scalar) {
        if (scalar == 0) {
            return this.getCopy();
        }
        double[] newDiagonalValues = new double[this.shape.rows];
        for (int i = 0; i < this.shape.rows; i++) {
            newDiagonalValues[i] = scalar + this.getValue(i);
        }
        return DoubleMatrixFactory.diagonal(newDiagonalValues);
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
                return "0 ··· 0 ";
        }
    }

    public String toString() {

        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getZeros(i) + Double.toString(this.getValue(i)) + " "
                    + this.getZeros(this.shape.columns - i) + "\n";
        }
        return result;
    }
}
