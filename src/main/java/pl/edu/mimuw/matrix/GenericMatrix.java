package pl.edu.mimuw.matrix;

public abstract class GenericMatrix implements IDoubleMatrix {
    public final Shape shape;

    public GenericMatrix(Shape shape) {
        this.shape = shape;
    }

    public Shape shape() {
        return this.shape;
    }

    public IDoubleMatrix minus(IDoubleMatrix other) {
        this.assertMinus(other);
        return this.plus(other.times(-1));
    }

    public IDoubleMatrix minus(double scalar) {
        this.assertMinus(scalar);
        return this.plus(-scalar);
    }

    void assertGet(int row, int column) {
        shape.assertInShape(row, column);
    }

    public IDoubleMatrix plus(double scalar) {
        if (scalar == 0) {
            return this.getCopy();
        }
        Constant temp = new Constant(this.shape, scalar);
        return temp.plus(this);
    }


    public double[][] data() {
        Shape shape = this.shape();
        double[][] result = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                result[i][j] = this.get(i, j);
            }
        }
        return result;
    }

    public void assertTimes(IDoubleMatrix other) {
        shape.assertMultiplyable(other.shape());
    }

    public void assertPlus(IDoubleMatrix other) {
        assert this.shape.equals(other.shape());
    }

    public void assertPlus(double scalar) {
        shape.assertSquare();
    }

    public void assertMinus(IDoubleMatrix other) {
        this.plus(other);
    }

    public void assertMinus(double scalar) {
        this.plus(scalar);
    }

}
