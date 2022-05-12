package pl.edu.mimuw.matrix;

public class Identity extends Diagonal{
    public Identity(int size){
        super.super(Shape.matrix(size, size));
    }
}
