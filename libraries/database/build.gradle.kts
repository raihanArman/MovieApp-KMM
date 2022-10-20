plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("koin")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "database"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:runtime:1.5.4")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.4")
                implementation("io.insert-koin:koin-core:3.2.2")

                implementation("io.ktor:ktor-client-core:2.1.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.2")
                implementation("io.ktor:ktor-client-logging:2.0.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.4")
                implementation("io.ktor:ktor-client-okhttp:2.0.0")
                implementation("io.ktor:ktor-client-android:2.0.0")
                implementation("io.insert-koin:koin-android:3.1.6")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.4")
                implementation("io.ktor:ktor-client-darwin:2.0.0")
                implementation("io.ktor:ktor-client-ios:2.0.0")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.randev.database"
    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}

sqldelight {
    database("MovieDatabase") {
        packageName = "com.randev.movieapp_kmm.db"
        sourceFolders = listOf("sqldelight")
    }
}