package pl.edu.mimuw.matrix;

public abstract class OneDimDegenerated extends GenericDegeneratedMatrix {

    protected double[] values;

    OneDimDegenerated(Shape shape, double... data){
        super(shape);
        values = data;
    }

    public IDoubleMatrix getCopy(){
        return this.switchData(this.values);
    }

    protected double getValue(int i) {
        return this.values[i];
    }

    public IDoubleMatrix times(double scalar) {
        if (scalar == 0) {
            return DoubleMatrixFactory.zero(this.shape);
        }
        if (scalar == 1) {
            return this.getCopy();
        }
        double[] newValues = new double[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = scalar * this.getValue(i);
        }
        return this.switchData(newValues);
    }
}
