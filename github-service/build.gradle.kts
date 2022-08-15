plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.riza.github.module")
    id("com.riza.github.kapt")
    id("com.riza.github.dagger.kapt")
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
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
}