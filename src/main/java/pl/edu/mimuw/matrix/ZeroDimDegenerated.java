package pl.edu.mimuw.matrix;

public abstract class ZeroDimDegenerated extends OneDimDegenerated{

    ZeroDimDegenerated(Shape shape, double value){
        super(shape);
        this.values = new double[]{value};
    }

    @Override
    protected double getValue(int i) {
        return this.values[0];
    }

    protected double getValue() {
        return this.values[0];
    }

    public IDoubleMatrix times(double scalar) {
        if (scalar == 0) {
            return DoubleMatrixFactory.zero(this.shape);
        }
        if (scalar == 1) {
            return this.getCopy();
        }
        return this.switchData(new double[]{scalar*this.getValue()});
    }
}
