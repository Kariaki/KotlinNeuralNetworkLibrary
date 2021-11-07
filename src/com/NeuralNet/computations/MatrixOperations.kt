package com.NeuralNet.Computations

import Computations.Matrix

class Util {


    companion object {


        fun add(a: Matrix, b: Matrix): Matrix {
            val output = Array(a.home_array[0].size) {
                DoubleArray(
                    a.home_array.size
                )
            }
            for (i in a.home_array.indices) {
                for (j in 0 until a.home_array[0].size) {
                    output[j][i] = a.home_array[i][j] + b.home_array[i][j]
                }
            }
            return Matrix(output)
        }

        fun subtract(a: Matrix, b: Matrix): Matrix {
            var output = Array(a.home_array.size) { DoubleArray(a.home_array[0].size) }
            for (i in 0 until a.home_array.size) {
                for (j in 0 until a.home_array[0].size) {
                    output[i][j] = (a.home_array[i][j] - b.home_array[i][j])
                }
            }

            return Matrix(output)
        }
        @Throws(MisMatchMatrix::class)
        fun dot(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {

            var i: Int
            var j: Int
            var k: Int
            val output = Array(a.size) {
                DoubleArray(
                    b[0].size
                )
            }
            if (a[0].size == b.size) {

                i = 0
                while (i < a.size) {
                    j = 0
                    while (j < b[0].size) {
                        k = 0
                        while (k < b.size) {
                            output[i][j] += a[i][k] * b[k][j]
                            k++
                        }
                        j++
                    }
                    i++
                }
            } else {
                throw MisMatchMatrix()
            }
            return output
        }
    }


}