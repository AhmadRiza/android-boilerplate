import com.android.build.gradle.internal.feature.BundleAllClasses

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.riza.github.kapt")
    id("com.riza.github.dagger.kapt")
    alias(libs.plugins.anvil)
}

tasks.withType(BundleAllClasses::class.java).configureEach {
    outputs.cacheIf { false }
}

android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    buildToolsVersion = libs.versions.androidBuildTools.get()

    defaultConfig {
        applicationId = "com.kitabisa.android"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    signingConfigs {
        getByName("debug") {
            storeFile = file("../debug.keystore")
            storePassword = "rizakey"
            keyAlias = "rizadebugkey"
            keyPassword = "riza"
        }
        create("release") {
            storeFile = file(
                System.getenv("HOME") +
                        "/MobileUI/keystores/kitabisa-key.keystore"
            )
            //todo replace with System ENV
            storePassword = "rizakey"
            keyAlias = "rizadebugkey"
            keyPassword = "riza"
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".staging"
            isMinifyEnabled = false
            isShrinkResources = false
            isCrunchPngs = false
            isTestCoverageEnabled = project.hasProperty("enableCoverage")
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isTestCoverageEnabled = false
            signingConfig = signingConfigs.getByName("debug") // todo change with release
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Tips to add test case, change the test build type to "debug" first and after done
    // change it again to "staging"
    testBuildType = "debug"

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }

    buildFeatures {
//        dataBinding = false
//        viewBinding = true
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
            )
        )
    }
}

dependencies {

}
