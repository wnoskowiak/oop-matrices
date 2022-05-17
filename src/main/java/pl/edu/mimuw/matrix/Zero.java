package pl.edu.mimuw.matrix;

public class Zero extends Constant {

    public Zero(Shape shape) {
        super(shape, 0);
    }

    @Override
    public IDoubleMatrix getCopy() {
        return DoubleMatrixFactory.zero(this.shape);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        shape.assertMultiplyable(other.shape());
        return DoubleMatrixFactory.zero(shape.shapeAfterMultiply(other.shape()));
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return this.getCopy();
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        return other.getCopy();
    }

    @Override
    public double normOne() {
        return 0;
    }

    @Override
    public double normInfinity() {
        return 0;
    }

    @Override
    public double frobeniusNorm() {
        return 0;
    }
}
