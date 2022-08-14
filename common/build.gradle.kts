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
    // Kotlin
    implementation(libs.android.core.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.kotlin.datetime)

    implementation(libs.appcompat)
    implementation(libs.android.core)

    // Android Architecture Components
    implementation(libs.lifecycle.livedata.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // DI
    implementation(libs.javax.inject)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
}