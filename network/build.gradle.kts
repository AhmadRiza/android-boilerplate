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

    implementation(libs.kotlin)
    implementation(libs.coroutines.core)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)

    implementation(libs.android.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.okhttp.mockwebserver)
}