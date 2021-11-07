package NeuralNetHelper

import kotlin.math.exp
import kotlin.math.tanh

class Activitations {

    companion object{

        fun sigmoid(x:Double):Double= 1/(1+ exp(x*-1))

        fun sigmoidPrime(x:Double):Double= tanh(x)

        fun costFunction(){}
    }
}