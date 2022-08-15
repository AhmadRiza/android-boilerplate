plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.riza.github.module")
    id("com.riza.github.kapt")
    id("com.riza.github.dagger.kapt")
}

kapt {
    arguments {
        arg("room.incremental", "true")
    }
}

room {
    schemaLocationDir.set(file("$projectDir/schemas"))
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("androidTest").assets
            .srcDir(files("$projectDir/schemas"))
    }

    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/Cache_debug.kotlin_module",
            )
        )
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlin)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // DI
    implementation(libs.dagger)
    implementation(libs.javax.inject)
    kapt(libs.dagger.compiler)

    // Room Database
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.kotlin.junit)
    androidTestImplementation(libs.test.junit.ktx)
}
