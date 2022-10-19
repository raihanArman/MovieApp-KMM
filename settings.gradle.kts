pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieApp-KMM"
include(":androidApp")
include(":shared")
include(":common:data")
include(":common:domain")
include(":libraries:network")
include(":libraries:database")
include(":libraries:preferences")
include(":common:core")
