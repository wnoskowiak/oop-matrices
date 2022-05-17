package pl.edu.mimuw.matrix;

public class ConstantRow extends GenericDegeneratedMatrix{

    private final double[] rowValues;

    ConstantRow(int columns, double... rowValues){
        super(Shape.matrix(rowValues.length, columns));
        this.rowValues = rowValues;
    }
    
}
