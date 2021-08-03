# HiltPoC
Hilt proof of concept done in Kotlin.
[Official documentation.](https://developer.android.com/training/dependency-injection/hilt-android#kts)

## Dependencies
Add the Hilt plugin in the root project's build.gradle:
```kotlin
buildscript {
    ...
    ext.hilt_version = '2.35'
    dependencies {
        ...
        classpath "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
    }
}
```
Apply the Gradle plugin and add the dependencies in the app build.gradle:
```kotlin
plugins {
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    ...
}

dependencies {
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
}
```
Enable Java 8 by adding the following to the app build.gradle:
```kotlin
android {
    ...
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
```

## Hilt Application
All apps that use Hilt must contain an _Application_ class, annotated with __@HiltAndroidApp__.
```kotlin
@HiltAndroidApp
class ExampleApplication : Application() { ... }
```
> @HiltAndroidApp triggers code generation: it generates a base class serving as an app-level dependency container. This component is attached to the __Application__ object's lifecycle, and provides dependencies to it.

