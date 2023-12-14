[![](https://jitpack.io/v/gastsail/hzone.svg)](https://jitpack.io/#gastsail/hzone)

# HZone

![ezgif com-video-to-gif](https://github.com/gastsail/hzone/assets/24615408/79bdb8e7-8cac-47c0-b0f8-c8201369943b)

HZone is a Kotlin library that demonstrates how to implement a component that draws five different zones based on a BPM (heart rate beats per minute) value provided to it.

The inspiration was taken from the apple watch in which you can see these zones in action doing any activity, the idea of this library is to bring this feature to your Android project.

![image](https://help.apple.com/assets/65246DF0BCB79A35E50C3B5B/65246DF5BC91E425490A66F4/en_US/9b1f9230ad244b4fce98a40ae3a6d8fa.png)

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

The concept of heart zones, as defined by the [Zoladz method](https://en.wikipedia.org/wiki/Heart_rate#Zoladz_method) for heart rate calculation, is explained in detail on [Wikipedia](https://en.wikipedia.org/wiki/Heart_rate).

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
val bpmValues = viewModel.bpmValues.collectAsStateWithLifecycle()
val averageBpm = calculateAverageBpm(bpmValues)
```

`Colors` can be customized sending your own `Custom Zones`, take in mind that the max zones are 5 so you will be limited sending the list to the first 5 entries.

```
val customZones = listOf(
                        Zone(
                            color = Color.Green,
                            text = "Zone 1",
                            zoneType = ZoneType.ZONE_1,
                            zoneEnabled = false
                        ),
                        Zone(
                            color = Color.DarkGray,
                            text = "Zone 2",
                            zoneType = ZoneType.ZONE_2,
                            zoneEnabled = false
                        )
                    )
                    HZone(
                        modifier = Modifier.padding(16.dp),
                        averageBpm = viewModel.averageBpm.collectAsState().value,
                        customZones = customZones,
                        onZoneClick = {})
```

*Remember that the `bpmValues` should be a stream that comes from a data source, this can be modeled in a viewmodel that emits each bpm to the view and the view collect it as state.*

Feel free to customize and integrate `HZone` into your project to visualize heart rate zones dynamically.

If you like this repo please don't forget to leave a ⭐️
