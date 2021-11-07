package com.NeuralNet.NeuralNetHelper

class GenerateInjection {
    companion object {
        fun getInjection(activationFunctions: ActivationFunctions): Injection {

            when (activationFunctions) {
                ActivationFunctions.SIGMOID ->
                    return ActivationInjections.sigmoidInjection
                ActivationFunctions.HYPERBOLIC_TANGENT ->
                    return ActivationInjections.tanhInjection
                ActivationFunctions.RELU ->
                    return ActivationInjections.reluInjection
                else ->
                    return object : Injection {
                        override fun injectPrime(x: Double, y: Double): Double {
                            return super.injectPrime(x, y)
                        }

                        override fun inject(x: Double): Double {
                            return super.inject(x)
                        }
                    }
            }
        }

        fun getInjection(costFunctions: CostFunctions): Injection {
            when (costFunctions) {
                CostFunctions.MEAN_SQUARE_ERROR ->
                    return CostInjectionFunction.meanSquareError
                CostFunctions.CROSS_ENTROPY ->
                    return CostInjectionFunction.crossEntropy

                else ->
                    return object : Injection {
                        override fun injectPrime(x: Double, y: Double): Double {
                            return super.injectPrime(x, y)
                        }

                        override fun inject(x: Double): Double {
                            return super.inject(x)
                        }
                    }
            }
        }
    }
}