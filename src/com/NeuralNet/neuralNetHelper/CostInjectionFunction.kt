package com.NeuralNet.NeuralNetHelper

import kotlin.math.pow

object CostInjectionFunction {

    var crossEntropy = object : Injection {
        override fun inject(x: Double, y: Double): Double {
            return 1.0
        }

        override fun injectPrime(x: Double, y: Double): Double {
            return 1.0
        }


    }
    var meanSquareError = object : Injection {
        override fun inject(y_: Double, y: Double): Double {
            return (y-y_).pow(2)
        }

        override fun injectPrime(x: Double, y: Double): Double {
            return 2*(x-y)
        }

    }

}