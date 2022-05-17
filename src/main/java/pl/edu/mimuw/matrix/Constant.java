package pl.edu.mimuw.matrix;

public class Constant extends GenericDegeneratedMatrix {

    private final double value;

    Constant(Shape shape,double value){
        super(shape);
        this.value = value;
    }

    public IDoubleMatrix getCopy(){
        return new Constant(shape,value);
    }

    public IDoubleMatrix plus(double scalar){
        return new Constant(this.shape, this.value+scalar);
    }

    public IDoubleMatrix times(double scalar){
        return new Constant(this.shape, this.value*scalar);
    }

    public double getValue(int i){
        return this.value;
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
                newData[i][j] += this.value; 
            }
        }
        return Full.MakeFull(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        this.assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return this.getCopy();
        }
        // if (other.getClass().equals(Diagonal.class)) {
        //     double[][] newData = this.data();
        //     Diagonal newOther = (Diagonal) other;
        //     for (int i = 0; i < this.shape.rows; i++) {
        //         for (int j = 0; j < this.shape.columns; j++) {
        //             newData[i][j] *= newOther.getValue(j);
        //         }

        //     }
        //     return DoubleMatrixFactory.full(newData);
        // }
        Shape newShape = this.shape.shapeAfterMultiply(other.shape());
        double[][] newData = new double[newShape.rows][newShape.columns];
        double temp;
        for (int i = 0; i < newShape.rows; i++) {
            for (int j = 0; j < newShape.columns; j++) {
                temp = 0;
                for (int k = 0; k < this.shape.columns; k++) {
                    temp += other.get(k, j);
                }
                newData[i][j] = this.getValue(i)*temp;
            }
        }
        return DoubleMatrixFactory.full(newData);
    }

    public double frobeniusNorm() {
        return Math.sqrt(this.shape.columns * this.shape.rows * value);
    }

    public double normOne() {
        return  Math.abs(this.shape.rows*value);
    }

    public double normInfinity() {
        return  Math.abs(this.shape.columns*value);
    }

    public String toString() {
        String helpeString;
        String sign = Double.toString(this.value);
        switch (this.shape().columns) {
            case 1:
                helpeString = sign;
                break;
            case 2:
                helpeString = sign + " " + sign;
                break;
            default:
                helpeString = sign +" ··· "+ sign;
        }
        return new String(new char[this.shape().rows]).replace("\0", helpeString);
    }
    
}
