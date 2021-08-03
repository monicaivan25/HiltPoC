package com.example.hiltpoc

import javax.inject.Inject

class MyInterfaceImpl @Inject constructor() : MyInterface {

    override fun sayGoodbye(): String {
        return "Goodbye"
    }
}