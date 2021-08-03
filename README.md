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
All apps that use Hilt must contain an _Application_ class, annotated with `@HiltAndroidApp`.
```kotlin
@HiltAndroidApp
class ExampleApplication : Application() { ... }
```
> @HiltAndroidApp triggers code generation: it generates a base class serving as an app-level dependency container. This component is attached to the __Application__ object's lifecycle, and provides dependencies to it.

## Dependency injection
Hilt provides dependencies to classes that have the `@AndroidEntryPoint` annotation. If you annotate an Android class with @AndroidEntryPoint, then you also must annotate the classes that depend on it.
```kotlin
@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() { ... }
```
> @AndroidEntryPoint generates an individual component for each class in your project. These components can receive dependencies from their respective parents, but not from their children, as per the component hierarchy:
![component hierarchy](https://developer.android.com/images/training/dependency-injection/hilt-hierarchy.svg)


### Field injection
For field injection, Hilt needs to know how to provide instances of the dependencies from the component. A _binding_ contains the info on how to provide instances of a type as a dependency.

#### Constructor Injection
One way to provide the _binding_ info is constructor injection. This is done by using the `@Inject`  on the constructor.
```kotlin
class AnalyticsAdapter @Inject constructor(
  private val service: AnalyticsService
) { ... }
```
>In the example, __AnalyticsAdapter__ has __AnalyticsService__ as a dependency. Therefore, Hilt must also know how to provide instances of AnalyticsService.

### Modules
If we are dealing with an interface or a class from an external library, we cannot constructor-inject it.

The `@Module` annotation tells Hilt how to provide instances of certain types. Modules must also be annotated with `@InstallIn`, to tell Hilt which class the module will be installed in.
```kotlin
@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {
}
```
### Interface injection
Interfaces cannot have constructor injections.The `@Binds` annotation over an abstract method, tells Hilt which implementation to use.
```kotlin
interface AnalyticsService {
  fun analyticsMethods()
}

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.
class AnalyticsServiceImpl @Inject constructor(
  ...
) : AnalyticsService { ... }

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

  @Binds
  abstract fun bindAnalyticsService(
    analyticsServiceImpl: AnalyticsServiceImpl
  ): AnalyticsService
}
```

### External library class injection
We use `@Provides` over a custom function __inside a Hilt module__ to tell Hilt how to provide an instance of a certain type.
```kotlin 
@Provides
fun provideAnalyticsService(
  // Potential dependencies of this type
): AnalyticsService {
    return Retrofit.Builder()
             .baseUrl("https://example.com")
             .build()
             .create(AnalyticsService::class.java)
}
```

#### Scopes
Scopes determine whether a class can be injected into another class. The order is descendent, meaning: the Application class, which is the only SingletonComponent, __cannot be injected into any other components__, but it can have __any other component injected into it__.
A class with the `@ViewModelScoped` annotation can have a `@ActivityScoped` anotated class injected into it, but it cannot have an `@ActivityRetainedScoped` anotated class injected into it.

![scopes](https://i.imgur.com/I2v2qtQ.png)

Example:
```kotlin
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
```
