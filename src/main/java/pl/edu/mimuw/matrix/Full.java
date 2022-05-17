package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class Full extends GenericMatrix {

    protected final double[][] data;

    private Full(double[][] values) {
        super(Shape.matrix(values.length, values[0].length));
        data = new double[values.length][values[0].length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Arrays.copyOf(values[i], values[i].length);
        }
    }

    public static Full makeFull(double[][] values){
        assert values != null;
        assert (values.length!=0);
        for(int i = 1; i<values.length; i++){
            assert values[i-1].length == values[i].length;
        }
        return new Full(values);
    }

    public IDoubleMatrix getCopy() {
        return DoubleMatrixFactory.full(this.data);
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        return this.data[row][column];
    }

    public IDoubleMatrix times(double scalar) {
        if (scalar == 0) {
            return DoubleMatrixFactory.zero(this.shape);
        }
        if (scalar == 1) {
            return this.getCopy();
        }
        double[][] newData = this.data();
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                newData[i][j] *= scalar;
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        if (!other.getClass().equals(this.getClass())) {
            return other.plus(this);
        }
        // Full nOther = (Full) other;
        double[][] newData = this.data();
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                newData[i][j] += other.get(i,j);
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        this.assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return this.getCopy();
        }
        if (other.getClass().equals(Diagonal.class)) {
            double[][] newData = this.data();
            Diagonal newOther = (Diagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                for (int j = 0; j < this.shape.columns; j++) {
                    newData[i][j] *= newOther.getValue(j);
                }

            }
            return DoubleMatrixFactory.full(newData);
        }
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        double[][] newData = new double[newShape.rows][newShape.columns];
        double temp;
        for (int i = 0; i < newShape.rows; i++) {
            for (int j = 0; j < newShape.columns; j++) {
                temp = 0;
                for (int k = 0; k < this.shape.columns; k++) {
                    temp += this.data[i][k] * other.get(k, j);
                }
                newData[i][j] = temp;
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public double frobeniusNorm() {
        double result = 0;
        for (int i = 0; i < this.shape.rows; i++) {
            for (int j = 0; j < this.shape.columns; j++) {
                result += (this.data[i][j])*(this.data[i][j]);
            }
        }
        return Math.sqrt(result);
    }

    public double normOne() {
        double max = 0, temp;
        for (int i = 0; i < this.shape.columns; i++) {
            temp = 0;
            for (int j = 0; j < this.shape.rows; j++) {
                temp += Math.abs(this.data[j][i]);
            }
            max = Double.max(max, temp);
        }
        return max;
    }

    public double normInfinity() {
        double max = 0, temp;
        for (int i = 0; i < this.shape.rows; i++) {
            temp = 0;
            for (int j = 0; j < this.shape.columns; j++) {
                temp += Math.abs(this.data[i][j]);
            }
            max = Double.max(max, temp);
        }
        return max;
    }

}
