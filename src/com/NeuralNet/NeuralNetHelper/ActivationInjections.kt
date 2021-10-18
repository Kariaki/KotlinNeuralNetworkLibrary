package com.NeuralNet.NeuralNetHelper

import kotlin.math.exp
import kotlin.math.pow

object ActivationInjections {

    var sigmoidInjection = object : Injection {
        override fun inject(x: Double): Double = 1 / (1 + exp(-1 * x))

        override fun injectPrime(x: Double): Double {
            var sigmoid = 1 / (1 + exp(-1 * x))
            return sigmoid * (1 - sigmoid)
        }
    }
    var reluInjection = object : Injection {
        override fun inject(x: Double): Double = if (x < 0) 0.0 else x


        override fun injectPrime(x: Double): Double = if (x < 0) 0.0 else 1.0
    }
    var tanhInjection = object : Injection {
        override fun inject(x: Double): Double = (2 / 1 + exp(-2 * x)) - 1

        override fun injectPrime(x: Double): Double {
            var tanh = (2 / 1 + exp(-2 * x)) - 1
            return 1 - tanh.pow(2)
        }
    }
    var softmaxInjection = object : Injection {
        override fun inject(x: Double): Double {
            return 1.0
        }

        override fun injectPrime(x: Double): Double {
            return 1.0
        }
    }
}