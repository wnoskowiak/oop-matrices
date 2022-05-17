package pl.edu.mimuw.matrix;

public class Identity extends GenericDiagonal{

    private Identity(int size){
        super(size, new double[]{});
    }

    public static Identity makeIdentity(int size){
        assert size > 0;
        return new Identity(size);
    }

    public IDoubleMatrix getCopy(){
        return new Identity(this.shape.rows);
    }

    public IDoubleMatrix times(double scalar) {
        if (scalar == 0) {
            return DoubleMatrixFactory.zero(this.shape);
        }
        if (scalar == 1) {
            return new Identity(this.shape.rows);
        }
        double[] newData = new double[this.shape.rows];
        for(int i = 0; i<this.shape.rows; i++){
            newData[i] = scalar;
        }
        return Diagonal.makeDiagonal(newData);
    }

    protected IDoubleMatrix switchData(double... data){
        if(data[0] == 1){
            return this.getCopy();
        }
        return this.times(data[0]);
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
