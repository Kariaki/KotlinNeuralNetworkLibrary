package com.NeuralNet.Components

import Computations.Matrix
import com.NeuralNet.Computations.Dimens
import com.NeuralNet.NeuralNetHelper.ActivationFunctions
import com.NeuralNet.NeuralNetHelper.ActivationInjections
import java.util.*
import Computations.random

class NetLayer(
    input: Matrix,
    var first_layer: Boolean = false,
    activation: ActivationFunctions = ActivationFunctions.SIGMOID
) {

    var layerNode: Matrix
    lateinit var layerWeights: Matrix
    lateinit var layerBias: Matrix
    var compute: Boolean = true
    var layerActivation = activation
    var input: Matrix

    init {
        this.input = input
        layerNode = input
    }

    fun compute(activation: ActivationFunctions, node: Int) {

        layerNode = if (compute)
            activate(activation, node)
        else
            layer(input, node)

    }


    //activate linear equation with any given activated function
    fun activate(activationFunctions: ActivationFunctions, node: Int): Matrix {
        var layerOutput = layer(input, node)
        layerOutput=applyInject(activationFunctions, layerOutput)
        // layerNode = layerOutput //assigning the activated matrix to the node of this  layer
        return layerOutput
    }

    //perform linear equation y=m*x+b
    fun layer(input: Matrix, node: Int): Matrix {
        var weights = random.randomMatrix(input.col, node)
        var bias = random.randomMatrix(1, node)
        layerWeights = weights
        layerBias = bias

        var output = (input * weights) + bias

        return output.t()
    }

    fun applyInject(activation: ActivationFunctions, layerOutput: Matrix):Matrix {
        when (activation) {
            ActivationFunctions.SIGMOID ->

                layerOutput.inject(ActivationInjections.sigmoidInjection)
            ActivationFunctions.RELU ->

                layerOutput.inject(ActivationInjections.reluInjection)
            ActivationFunctions.HYPERBOLIC_TANGENT ->

                layerOutput.inject(ActivationInjections.tanhInjection)
            ActivationFunctions.SOFTMAX ->

                layerOutput.inject(ActivationInjections.softmaxInjection)

        }
        return layerOutput
    }


}