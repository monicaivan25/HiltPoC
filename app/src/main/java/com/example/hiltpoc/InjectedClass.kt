package com.example.hiltpoc

import javax.inject.Inject

class InjectedClass @Inject constructor(
    //constructor injection
    private val myInterface: MyInterface
){
    fun sayHello(): String {
        return "Hello"
    }

    fun sayBye(): String{
        return myInterface.sayGoodbye()
    }
}