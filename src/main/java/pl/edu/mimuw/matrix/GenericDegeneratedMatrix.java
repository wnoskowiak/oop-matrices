package pl.edu.mimuw.matrix;

public abstract class GenericDegeneratedMatrix extends GenericMatrix{

    GenericDegeneratedMatrix(Shape shape){
        super(shape);
    }

    protected abstract IDoubleMatrix switchData(double... data);

    abstract protected double getValue(int i);
}
