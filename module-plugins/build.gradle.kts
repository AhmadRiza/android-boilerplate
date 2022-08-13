plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.classpath.android.gradle)
    implementation(libs.classpath.kotlin.gradle)
    // implementation(libs.classpath.ksp.gradle)
}

gradlePlugin {
    plugins {
        create("module-plugin") {
            id = "com.riza.github.module"
            implementationClass = "com.riza.github.plugins.LibraryPlugin"
        }

        create("kapt-setting-plugin") {
            id = "com.riza.github.kapt"
            implementationClass = "com.riza.github.plugins.KaptSettingsPlugin"
        }
        create("dagger-kapt-setting-plugin") {
            id = "com.riza.github.dagger.kapt"
            implementationClass = "com.riza.github.plugins.DaggerKaptSettingsPlugin"
        }
        create("jacoco-setting-plugin") {
            id = "com.riza.github.jacoco.config"
            implementationClass = "com.riza.github.plugins.JacocoSettingsPlugin"
        }
    }
}
