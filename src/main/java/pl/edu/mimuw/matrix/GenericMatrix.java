package pl.edu.mimuw.matrix;

public abstract class GenericMatrix implements IDoubleMatrix {
    public final Shape shape;

    public GenericMatrix(Shape shape){
        this.shape = shape;
    }

    public Shape shape() {
        return this.shape;
    }

    public double[][] data(){
        Shape shape = this.shape();
        double[][] result = new double[shape.rows][shape.columns];
        for(int i = 0; i<shape.rows; i++){
            for(int j = 0; j<shape.columns; j++){
                result[i][j] = this.get(i,j);
            }
        }
        return result;
    }

    // public String toString(){
    //     Shape shape = this.shape();
    //     String result = "";
    //     for(int i = 0; i<shape.rows; i++){
    //         for(int j = 0; j<shape.columns; j++){
    //             result = result + Double.toString(this.get(i,j)) + " ";
    //         }
    //         result = result.substring(0,result.length()-1) + '\n';
    //     }
    //     return result;
    // }

}
