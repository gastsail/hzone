[![](https://jitpack.io/v/gastsail/hzone.svg)](https://jitpack.io/#gastsail/hzone)


# HZone

![ezgif com-video-to-gif](https://github.com/gastsail/hzone/assets/24615408/8776d646-70ca-43d0-b5a4-89e87b36ea90)


HZone is a library that demonstrates how to implement a component that draws five different zones based on a BPM (heart rate beats per minute) value provided to it.

## Getting Started

To use HZone in your project, you need to add the following configuration to your `settings.gradle` file.

### Kotlin DSL

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```

### Groovy

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Next, add the library dependency to your app module's `build.gradle`.

### Kotlin DSL

```kotlin
dependencies {
    implementation("com.github.gastsail:hzone:1.0")
}
```

### Groovy

```groovy
dependencies {
    implementation 'com.github.gastsail:hzone:Tag'
}
```

##Usage

To use HZone in your application, import the `HZone` method and provide the necessary parameters.

```kotlin
import com.gastonsaillen.hzone.HZone

val averageBpm = 75 // Replace with your actual average BPM
HZone(averageBpm) {
    // Zone clicked
}
```

You can also specify the `onZoneClick` parameter to handle zone click events.

```kotlin
HZone(averageBpm = averageBpm, onZoneClick = {
    // Zone clicked
})
```

The `averageBpm` parameter is used to calculate the average BPM from the values you provide using the `calculateAverageBpm` function.

```kotlin
import com.gastonsaillen.hzone.calculateAverageBpm

val bpmValues = listOf(80, 85, 90)
val averageBpm = calculateAverageBpm(bpmValues)
```

Feel free to customize and integrate `HZone` into your project to visualize heart rate zones dynamically.



