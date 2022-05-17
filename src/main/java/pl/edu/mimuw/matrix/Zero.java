package pl.edu.mimuw.matrix;

public class Zero extends Constant {

    private Zero(Shape shape) {
        super(shape, 0);
    }

    public static Zero makeZero(Shape shape){
        assert shape.rows >0 & shape.columns >0;
        return new Zero(shape);
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
    
    public String toString() {

        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getChar(this.shape.columns,"0")+ "\n";
        }
        return result;
    }

}
