package pl.edu.mimuw.matrix;

public class Zero extends GenericMatrix {

    public Zero(Shape shape) {
        super(shape);
    }

    public IDoubleMatrix getCopy() {
        return DoubleMatrixFactory.zero(this.shape);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        shape.assertMultiplyable(other.shape());
        return DoubleMatrixFactory.zero(shape.shapeAfterMultiply(other.shape()));
    }

    public IDoubleMatrix times(double scalar) {
        return this.getCopy();
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        return other.getCopy();
    }

    public IDoubleMatrix plus(double scalar) {
        this.assertPlus(scalar);
        return DoubleMatrixFactory.diagonal(scalar);
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
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
