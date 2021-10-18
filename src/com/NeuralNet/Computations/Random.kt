package Computations

class random {
    companion object{
        fun randomMatrix(row:Int?,col:Int): Matrix {

            var output= Array(row!!){DoubleArray(col)}
            for (i in 0 until row){
                for(j in 0 until col){
                    var randomize= java.util.Random()
                    output[i][j]=randomize.nextDouble()

                }
            }
            return Matrix(output)
        }
    }
}