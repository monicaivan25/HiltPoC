package com.example.hiltpoc

import javax.inject.Inject

class OtherInjectedClass @Inject constructor(){
    fun sayGoodbye(): String{
        return "Goodbye"
    }
}