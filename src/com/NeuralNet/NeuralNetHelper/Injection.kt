package com.NeuralNet.NeuralNetHelper

interface Injection {

    fun inject(x: Double,y:Double): Double{
        return 0.0
    }
    fun injectPrime(x: Double,y:Double): Double{
        return 0.0
    }

    fun inject(x:Double):Double{
        return 0.0
    }

    fun injectPrime(x:Double):Double{
        return 0.0
    }


}