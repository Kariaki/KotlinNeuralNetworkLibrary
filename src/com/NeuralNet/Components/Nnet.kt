package com.NeuralNet.Components

import Computations.Matrix
import com.NeuralNet.Computations.Dimens

import com.NeuralNet.NeuralNetHelper.ActivationFunctions
import com.NeuralNet.NeuralNetHelper.Injection

class Nnet(var input: Matrix, var target: Matrix, costInjection: Injection) {


    var layersStack: MutableList<NetLayer> = mutableListOf()
    var unActivatedLayer: MutableList<NetLayer> = mutableListOf()
    lateinit var error: Matrix
    var costInjection: Injection
    lateinit var lastLayer: NetLayer
    lateinit var derivative: Matrix

    init {
        unActivatedLayer.add(NetLayer(input, true))
        layersStack.add(NetLayer(input, true))
        this.costInjection = costInjection
    }


    fun addLayer(nodes: Int, activationFunctions: ActivationFunctions = ActivationFunctions.HYPERBOLIC_TANGENT): Nnet {
        /*
        adds layer to the network
         */
        var input: Matrix = if (layersStack.size > 0)
            layersStack[layersStack.size - 1].layerNode
        else
            layersStack[0].layerNode
        var outputLayer = NetLayer(input)
        unActivatedLayer.add(outputLayer)
        outputLayer
            .apply {
                compute(activationFunctions, nodes)
            }
        layersStack.add(outputLayer)

        if (target.dimen() == outputLayer.layerNode.dimen()) {

            computeError(outputLayer)

        }
        return this
    }

    fun computeError(outputLayer:NetLayer){
        error = outputLayer.layerNode
        derivative = outputLayer.layerNode
        derivative.injectPrime(costInjection, target)

        error.inject(costInjection, target)
    }
    fun feedForward() {

        if (this.layersStack.isNotEmpty()) {
            val layerNum = 1..this.layersStack.size
            for (layer in layerNum) {
                var previous = this.layersStack[layer - 1]
                var currentLayer = this.layersStack[layer]

                this.layersStack[layer].layerNode = currentLayer.applyInject(
                    currentLayer.layerActivation,

                    (previous.layerNode * currentLayer.layerWeights) + currentLayer.layerBias
                )
                if(layer==this.layersStack.size-1){
                    computeError(this.layersStack[layer])
                }
            }
        }

    }

    fun train(learningRate: Double, epoch: Int) {
        /* call train to start training the network, learning rate is a chosen constant that should range from 0 to 1
        epoch is the number of times you want to run backprop
         */
        var range = 0..epoch
        for (i in range) {

            var train = BackProp(this)
                .apply {
                    computeWeightDerivative(learningRate)
                }
            layersStack = train.getLayerStack()
            //feedForward()

        }
    }

    fun printLayer() {

        this.layersStack.forEach {
            println(it.layerWeights.dimen())
        }
    }


}