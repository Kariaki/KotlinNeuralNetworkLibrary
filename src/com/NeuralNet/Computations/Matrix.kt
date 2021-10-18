package Computations

import com.NeuralNet.Computations.Dimens
import com.NeuralNet.Computations.Util
import com.NeuralNet.NeuralNetHelper.Injection

class Matrix() {

    lateinit var home_array: Array<DoubleArray>
    lateinit var singleArray: Array<Double>
    var row: Int? = null
    var col: Int? = null

    constructor(single: Array<Double>) : this() {
        singleArray = single
    }

    constructor(array: Array<DoubleArray>) : this() {
        home_array = array
        row = home_array.size
        col = home_array[0].size
    }

    // operator overloading to cross multiply two matrices
    operator fun times(n_array: Matrix): Matrix {

        return Matrix(Util.dot(home_array, n_array.home_array))
    }

    operator fun minus(matrix: Matrix): Matrix {
        return Util.subtract(Matrix(home_array), matrix)
    }

    fun transpose(): Matrix {
        val output = Array(home_array[0].size) {
            DoubleArray(
                home_array.size
            )
        }
        for (i in home_array.indices) {
            for (j in 0 until home_array[0].size) {
                output[j][i] = home_array[i][j]
            }
        }
       // home_array = output
        return Matrix(output)
    }

    fun inject(injection: Injection) {
        var output = Array(home_array.size) { DoubleArray(home_array[0].size) }
        for (i in 0 until home_array.size) {
            for (j in 0 until home_array[0].size) {
                output[i][j] = injection.inject(home_array[i][j])
            }
        }
        home_array = output
    }

    fun injectPrime(injection: Injection) {
        var output = Array(home_array.size) { DoubleArray(home_array[0].size) }
        for (i in 0 until home_array.size) {
            for (j in 0 until home_array[0].size) {
                output[i][j] = injection.injectPrime(home_array[i][j])

            }
        }
        home_array = output
    }

    fun inject(injection: Injection, matrix: Matrix) {
        var output = Array(home_array.size) { DoubleArray(home_array[0].size) }
        for (i in 0 until home_array.size) {
            for (j in 0 until home_array[0].size) {
                output[i][j] = injection.inject(home_array[i][j], matrix.home_array[i][j])
            }
        }
        home_array = output
    }

    fun injectPrime(injection: Injection, matrix: Matrix) {
        var output = Array(home_array.size) { DoubleArray(home_array[0].size) }
        for (i in 0 until home_array.size) {
            for (j in 0 until home_array[0].size) {
                output[i][j] = injection.injectPrime(home_array[i][j], matrix.home_array[i][j])
            }
        }
        home_array = output
    }

    // addition of two matrices
    fun add(matrix: Matrix): Matrix {

        var lead = Matrix(Util.add(Matrix(home_array), matrix).home_array)
        home_array = lead.home_array
        return lead
    }

    //scalar matrix multiplication
    fun multiply(matrix: Matrix): Matrix {
        var output = Array(home_array.size) { DoubleArray(home_array[0].size) }
        for (i in 0 until home_array.size) {
            for (j in 0 until home_array[0].size) {
                output[i][j] = (home_array[i][j]* matrix.home_array[i][j])
            }
        }
        return Matrix(output)
    }


    operator fun plus(matrix: Matrix): Matrix {
        val output = Array(home_array[0].size) {
            DoubleArray(
                home_array.size
            )
        }
        for (i in home_array.indices) {
            for (j in 0 until home_array[0].size) {
                output[j][i] = home_array[i][j] + matrix.home_array[0][j]
            }
        }
        return Matrix(output)
    }


    fun dimen(): Dimens {
        // print("${home_array.size} ${home_array[0].size}")

        return Dimens(home_array.size, home_array[0].size)
    }

    fun print() {
        for (a in home_array) {
            for (value in a) {
                print(value.toString() + "\t")
            }
            println()
        }
    }

    fun t(): Matrix {

        var output = transpose()
      //  home_array = output.home_array
        return output

    }

    fun sum(): Double {
        var output = 0.0
        for (i in home_array.indices) {
            for (j in home_array[0].indices) {
                output += home_array[i][j]
            }
        }
        return output
    }

}