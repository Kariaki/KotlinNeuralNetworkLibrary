package com.NeuralNet.Components

import Computations.Matrix
import com.NeuralNet.NeuralNetHelper.GenerateInjection
import com.NeuralNet.NeuralNetHelper.Injection

class BackProp(net: Nnet) {

    var layersStack = net.layersStack
    var unActivatedLayerStack = net.unActivatedLayer
    var layerUpdate: MutableList<NetLayer> = mutableListOf()

    var initialDerivative: Matrix = net.derivative

    fun computeWeightDerivative(learningRate: Double): MutableList<NetLayer> {

        layerUpdate = layersStack
        for (layer in layersStack.size - 1 downTo 1) { // iterate through the layers to perform back prop

            var currentLayer = layersStack[layer]
            var nextLayer = layersStack[layer - 1]
            if (layer == layersStack.size - 1) {
                //computing the first error, this is required to find the weight and bias update for the last layer
                var layerL = unActivatedLayerStack[unActivatedLayerStack.size - 1].layerNode
                    .apply {
                        //injecting the derivative into the current layer nodes
                        injectPrime(
                            GenerateInjection.getInjection(
                                unActivatedLayerStack[unActivatedLayerStack.size - 1].layerActivation
                            )
                        )
                        //apply scalar multiplication with injection

                    }
                // layerL=layerL.multiply(initialDerivative)

                initialDerivative = layerL.multiply(initialDerivative)
                //  println(initialDerivative.dimen())

                // initialDerivative = layerL
            } else {

                var previousLayer = layersStack[layer + 1]
                //moving backward into the network to compute derivatives and update weights

                var unActivatedCurrentLayerLayerNodePrime = unActivatedLayerStack[layer].layerNode
                    .apply {
                        injectPrime(GenerateInjection.getInjection(currentLayer.layerActivation))
                    }
                initialDerivative *= previousLayer.layerWeights.t()
                initialDerivative = initialDerivative.multiply(unActivatedCurrentLayerLayerNodePrime)

            }
            val weight = (nextLayer.layerNode.t() * initialDerivative)


            val weightUpdate = currentLayer.layerWeights - weight

            weightUpdate.apply {
                inject(object : Injection {
                    override fun inject(x: Double): Double {
                        return learningRate * x
                    }
                })
            }
            layersStack[layer].layerWeights=weightUpdate

            println("layer weight")
            println(currentLayer.layerWeights.dimen())
            println("update weight")
            println(weightUpdate.dimen())

        }

        return layerUpdate

    }

    fun getLayerStack():MutableList<NetLayer> = layersStack


    fun computeBiasDerivative(): Matrix {

        for (layer in layersStack.size - 1 downTo 1) { // iterate through the layers to perform back prop

            if (layer == layersStack.size - 1) {
                //computing the first error, this is required to find the weight and bias update for the last layer
                var layerL = unActivatedLayerStack[unActivatedLayerStack.size - 1].layerNode
                    .apply {
                        //injecting the derivative into the current layer nodes
                        injectPrime(
                            GenerateInjection.getInjection(
                                unActivatedLayerStack[unActivatedLayerStack.size - 1].layerActivation
                            )
                        )
                        multiply(initialDerivative)

                    }

                initialDerivative = layerL

                // layerL=layerL.multiply(initialDerivative)
                // initialDerivative = layerL
            } else {

                //moving backward into the network to compute derivatives and update weights

                var currentLayer = layersStack[layer]
                var previousLayer = layersStack[layer + 1]
                var currentLayerNode = currentLayer.layerNode
                var nextLayer = layersStack[layer - 1]
                var unActivatedCurrentLayerLayerNode = unActivatedLayerStack[layer].layerNode
                    .apply {
                        injectPrime(GenerateInjection.getInjection(currentLayer.layerActivation))
                    }
                initialDerivative *= previousLayer.layerWeights.t()
                initialDerivative = initialDerivative.multiply(unActivatedCurrentLayerLayerNode).t()

                var weight = initialDerivative.t() * nextLayer.layerNode

                println(weight.dimen())
                /*  println("initial derivative of current layer")
                  initialDerivative.dimen().print()
                  println("input of next layer ")
                  nextLayer.layerNode.dimen().print()


                 */

            }
        }


        return initialDerivative
    }

    //multiply derivative with constant to get new weight

    fun computeNewWeight(derivative: Matrix, constant: Matrix): Matrix = derivative * constant


}