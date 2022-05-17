package pl.edu.mimuw.matrix;

public class AntiDiagonal extends OneDimDegenerated{

    public IDoubleMatrix getCopy(){
        return AntiDiagonal.makeAntiDiagonal(this.values);
    }

    private AntiDiagonal(double... diagonalValues) {
        super(Shape.matrix(diagonalValues.length, diagonalValues.length), diagonalValues);
    }

    public static AntiDiagonal makeAntiDiagonal(double... diagonalValues){
        assert diagonalValues.length > 0;
        return new AntiDiagonal(diagonalValues);
    }

    protected IDoubleMatrix switchData(double... data){
        return AntiDiagonal.makeAntiDiagonal(data);
    }

    protected double getValue(int index) {
        return this.values[index];
    }

    public double get(int row, int column) {
        this.assertGet(row, column);
        if (row != (this.shape.columns -1  - column)) {
            return 0;
        }
        return getValue(row);
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        this.assertPlus(other);
        if (other.getClass().getName() == Zero.class.getName()) {
            return this.getCopy();
        }
        if (other.getClass().equals(this.getClass())) {
            AntiDiagonal temp = (AntiDiagonal) other;
            double[] newAntiDiagonalValues = new double[this.shape.rows];
            for (int i = 0; i < this.shape.rows; i++) {
                newAntiDiagonalValues[i] = this.getValue(i) + temp.getValue(i);
            }
            return AntiDiagonal.makeAntiDiagonal(newAntiDiagonalValues);
        }
        double[][] newData = other.data();
        for (int i = 0; i < this.shape.rows; i++) {
            newData[i][(this.shape.columns-1-i)] += getValue(i);
        }
        return Full.makeFull(newData);
    }

    public IDoubleMatrix times(IDoubleMatrix other) {
        assertTimes(other);
        if (other.getClass().equals(Identity.class)) {
            return AntiDiagonal.makeAntiDiagonal(this.values);
        }
        if (other.getClass().equals(this.getClass())) {
            double[] newDiagonalValues = new double[this.shape.rows];
            AntiDiagonal temp = (AntiDiagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newDiagonalValues[i] = this.getValue(i) * temp.getValue(this.shape.rows-1-i);
            }
            return Diagonal.makeDiagonal(newDiagonalValues);
        }
        if (other.getClass().equals(Diagonal.class)){
            double[] newAntiDiagonalValues = new double[this.shape.rows];
            Diagonal temp = (Diagonal) other;
            for (int i = 0; i < this.shape.rows; i++) {
                newAntiDiagonalValues[i] = this.getValue(i) * temp.getValue(this.shape.rows-1-i);
            }
            return AntiDiagonal.makeAntiDiagonal(newAntiDiagonalValues);
        }
        double[][] newData = other.data();
        double[] temp;
        for (int i = 0; i < this.shape.columns/2; i++) {
            temp = newData[i];
            newData[i] = newData[newData.length-1-i];
            newData[i] = temp;
        }
        for (int i = 0; i < this.shape.rows; i++) {
            for(int j = 0; j<newData[0].length; j++){
                newData[i][j] *= this.getValue(i);
            }
        }
        return Full.makeFull(newData);
    }

    public double normOne() {
        double max = Double.NEGATIVE_INFINITY;
        for (double cur : this.values) {
            max = Math.max(max, Math.abs(cur));
        }
        return max;
    }

    public double normInfinity() {
        return this.normOne();
    }

    public double frobeniusNorm() {
        double result = 0;
        for (double cur : this.values) {
            result += cur * cur;
        }
        return Math.sqrt(result);
    }

    public String toString() {
        printDimentions();
        String result = "";
        for (int i = 0; i < this.shape.rows; i++) {
            result += this.getChar(this.shape.columns -1-i, "0") + Double.toString(this.getValue(this.shape.columns -1-i)) + " "
                    + this.getChar(i,"0") + "\n";
        }
        return result;
    }

}
