package com.example.hiltpoc

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SingletonClass @Inject constructor(){
}

@ActivityScoped
@AndroidEntryPoint
class ScopeActivity: AppCompatActivity() {
//    This yields a build error
//    @Inject
//    lateinit var fragmentClass: FragmentClass

    @Inject
    lateinit var singletonClass: SingletonClass
}

@FragmentScoped
class FragmentClass @Inject constructor(){
}