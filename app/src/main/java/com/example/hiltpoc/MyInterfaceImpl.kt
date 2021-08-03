package com.example.hiltpoc

import javax.inject.Inject

/**
 * To perform field injection, Hilt needs to know how to provide instances of the necessary
 * dependencies from the corresponding component. A binding contains the information necessary
 * to provide instances of a type as a dependency.
 *
 * One way to provide binding information to Hilt is constructor injection. Use the @Inject
 * annotation on the constructor of a class to tell Hilt how to provide instances of that class.
 */
class MyInterfaceImpl @Inject constructor() : MyInterface {

    override fun sayGoodbye(): String {
        return "Goodbye"
    }
}