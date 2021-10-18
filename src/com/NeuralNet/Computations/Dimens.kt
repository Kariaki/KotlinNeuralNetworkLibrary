package com.NeuralNet.Computations

data class Dimens(
    var width:Int,
    var height:Int
){


    constructor(vararg nodes:Int) : this(0,0) {

    }
}