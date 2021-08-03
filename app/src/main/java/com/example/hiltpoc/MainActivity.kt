package com.example.hiltpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Hilt provides dependencies to classes that have the @AndroidEntryPoint annotation.
 * Scope it @ActivityScope, due to it being an Activity.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    @Inject lateinit var injectedClass: InjectedClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(injectedClass.sayHello())
        println(injectedClass.sayBye())
    }
}



