package com.example.hiltpoc

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MyBindsModule {

    @Binds
    abstract fun bindInterfaceImplementation(interfaceImpl: MyInterfaceImpl): MyInterface
}