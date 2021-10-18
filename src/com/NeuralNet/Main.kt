package com.NeuralNet

import Computations.Matrix
import com.NeuralNet.Components.BackProp
import com.NeuralNet.Components.Nnet
import com.NeuralNet.Computations.Dimens
import com.NeuralNet.Computations.MisMatchMatrix
import com.NeuralNet.NeuralNetHelper.ActivationFunctions
import com.NeuralNet.NeuralNetHelper.CostInjectionFunction
import com.NeuralNet.modelData.Data

import kotlin.jvm.JvmStatic

object Main {

    @JvmStatic
    fun main(args: Array<String>) {


        var data = Data()
            .apply {
                process()
            }


        var input = Matrix(data.nData)
        input.print()


        /*
        var target = Matrix(data.label)
        var net = Nnet(input, target, CostInjectionFunction.meanSquareError)
            .apply {

                addLayer(5, ActivationFunctions.HYPERBOLIC_TANGENT)
                addLayer(4, ActivationFunctions.HYPERBOLIC_TANGENT)
                addLayer(3, ActivationFunctions.HYPERBOLIC_TANGENT)
                addLayer(3, ActivationFunctions.SIGMOID)

            }
        net.train(.75,20)

         */

    }

    fun mainValue(a:Int, b: (value:Int,fisher:Int)-> Unit){
        println(b(a,100))
    }

    fun generator(a:Int ,nextSeed:Int,limit:Int,startAt:Int):Int{
        var currentNumber= startAt
        currentNumber=(a*currentNumber +nextSeed)%limit;
        return currentNumber
    }
}
