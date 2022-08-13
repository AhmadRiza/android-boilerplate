import java.net.URI

rootProject.name = "Github Catalog"

pluginManagement {
    includeBuild("./module-plugins")

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.riza.github.module") {
                useModule("com.android.tools.build:gradle:7.2.2")
            }
        }
    }
}

include(
    ":app",
)