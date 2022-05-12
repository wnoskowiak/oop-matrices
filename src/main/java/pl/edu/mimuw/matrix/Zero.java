package pl.edu.mimuw.matrix;

public class Zero extends GenericMatrix {

    public Zero(Shape shape) {
        super(shape);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        shape.assertMultiplyable(other.shape());
        return DoubleMatrixFactory.zero(shape.shapeAfterMultiply(other.shape()));
    }

    public IDoubleMatrix times(double scalar) {
        return this;
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert this.shape.equals(other.shape());
        return other;
    }

    public IDoubleMatrix plus(double scalar) {
        shape.assertSquare();
        return DoubleMatrixFactory.diagonal(scalar);
    }

    public IDoubleMatrix minus(IDoubleMatrix other) {
        assert this.shape.equals(other.shape());
        return other.times(-1);
    }

    public IDoubleMatrix minus(double scalar) {
        shape.assertSquare();
        return DoubleMatrixFactory.diagonal(-scalar);
    }

    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return 0;
    }

    public double normOne() {
        return 0;
    }

    public double normInfinity() {
        return 0;
    }

    public double frobeniusNorm() {
        return 0;
    }

    public String toString() {
        String helpeString;
        switch (this.shape().columns) {
            case 1:
                helpeString = "0";
                break;
            case 2:
                helpeString = "00";
                break;
            default:
                helpeString = "0···0";
        }
        return new String(new char[this.shape().rows]).replace("\0", helpeString);
    }
}
