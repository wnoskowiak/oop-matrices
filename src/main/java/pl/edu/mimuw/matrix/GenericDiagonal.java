package pl.edu.mimuw.matrix;

public abstract class GenericDiagonal extends OneDimDegenerated {

    public GenericDiagonal(int size, double ... values) {
        super(Shape.matrix(size, size), values);
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

    public double get(int row, int column) {
        this.assertGet(row, column);
        if (row != column) {
            return 0;
        }
        return getValue(row);
    }

    public String toString() {

        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getChar(i,"0") + Double.toString(this.getValue(i)) + " "
                    + this.getChar(this.shape.columns -1 - i,"0") + "\n";
        }
        return result;
    }

}
