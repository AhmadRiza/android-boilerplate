@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.allopen")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.riza.github.module")
    id("com.riza.github.kapt")
    id("com.riza.github.dagger.kapt")
    alias(libs.plugins.anvil)
}

android {
    testOptions {
        unitTests.all { it.useJUnitPlatform() }
    }
}

allOpen {
    annotation("com.riza.github.OpenClass")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":network"))

    // DI
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // Kotlin
    implementation(libs.kotlin)
    implementation(libs.kotlin.datetime)
    implementation(libs.coroutines.core)

    // General
    implementation(libs.android.annotations)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.gson)

    testImplementation(libs.mockk)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.coroutines.test)
}
