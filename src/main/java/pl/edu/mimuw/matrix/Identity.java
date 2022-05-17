package pl.edu.mimuw.matrix;

public class Identity extends GenericDiagonal{
    public Identity(int size){
        super(size);
    }

    public IDoubleMatrix getCopy(){
        return DoubleMatrixFactory.identity(this.shape.columns);
    }

    protected double getValue(int index){
        return 1;
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        this.assertTimes(other);
        return other.getCopy();
    }

    public double normOne() {
        return 1;
    }

    public double normInfinity() {
        return 1;
    }

    public double frobeniusNorm(){
        return 1;
    }

}
