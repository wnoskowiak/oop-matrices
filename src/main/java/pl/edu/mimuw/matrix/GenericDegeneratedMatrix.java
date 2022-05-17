package pl.edu.mimuw.matrix;

public abstract class GenericDegeneratedMatrix extends GenericMatrix{

    GenericDegeneratedMatrix(Shape shape){
        super(shape);
    }

    abstract protected double getValue(int i);
}
