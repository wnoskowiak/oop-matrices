package pl.edu.mimuw.matrix;

public class Constant extends ZeroDimDegenerated {

    protected Constant(Shape shape,double value){
        super(shape, value);
    }

    public static Constant makeConstant(Shape shape,double value){
        assert shape != null & shape.rows>0 & shape.columns>0;
        return new Constant(shape, value);
    }

    public IDoubleMatrix getCopy(){
        return new Constant(shape,this.getValue());
    }

    protected IDoubleMatrix switchData(double... data){
        return new Constant(this.shape, data[0]);
    }

    public IDoubleMatrix plus(double scalar){
        return new Constant(this.shape, this.getValue()+scalar);
    }

    public double getValue(int i){
        return this.getValue();
    }

    public double get(int row, int column){
        assertGet(row, column);
        return getValue(row);
    }

    public IDoubleMatrix plus(IDoubleMatrix other){
        super.assertPlus(other);
        double[][] newData = other.data();
        for(int i = 0; i<this.shape.rows; i++){
            for(int j = 0; j<this.shape.columns; j++){
                newData[i][j] += this.getValue(); 
            }
        }
        return Full.makeFull(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        this.assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return this.getCopy();
        }
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        double[][] newData = new double[newShape.rows][newShape.columns];
        double temp;
        for (int i = 0; i < newShape.rows; i++) {
            for (int j = 0; j < newShape.columns; j++) {
                temp = 0;
                for (int k = 0; k < this.shape.columns; k++) {
                    temp += other.get(k, j);
                }
                newData[i][j] = this.getValue()*temp;
            }
        }
        return Full.makeFull(newData);
    }

    public double frobeniusNorm() {
        return Math.sqrt(this.shape.columns * this.shape.rows * this.getValue());
    }

    public double normOne() {
        return  Math.abs(this.shape.rows*this.getValue());
    }

    public double normInfinity() {
        return  Math.abs(this.shape.columns*this.getValue());
    }

    public String toString() {
        printDimentions();
        String helpeString = this.getChar(this.shape.columns,Double.toString(this.getValue())) + "\n";
        return new String(new char[this.shape().rows]).replace("\0", helpeString);
    }
    
}
