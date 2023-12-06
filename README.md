[![](https://jitpack.io/v/gastsail/hzone.svg)](https://jitpack.io/#gastsail/hzone)


# HZone

![ezgif com-video-to-gif](https://github.com/gastsail/hzone/assets/24615408/79bdb8e7-8cac-47c0-b0f8-c8201369943b)

HZone is a Kotlin library that demonstrates how to implement a component that draws five different zones based on a BPM (heart rate beats per minute) value provided to it.

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

```Groovy
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
    implementation("com.github.gastsail:hzone:[latest]")
}
```

### Groovy

```Groovy
dependencies {
    implementation 'com.github.gastsail:hzone:[latest]'
}
```

## Usage

To use `HZone` in your application, import the `HZone` method and provide the necessary parameters.

```kotlin
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
val bpmValues = listOf(80, 85, 90)
val averageBpm = calculateAverageBpm(bpmValues)
```

Feel free to customize and integrate `HZone` into your project to visualize heart rate zones dynamically.

If you like this repo please don't forget to leave a ⭐️




