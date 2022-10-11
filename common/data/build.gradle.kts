plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("koin")
    kotlin("plugin.serialization") version "1.6.20"
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4"
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "data"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.realm.kotlin:library-base:0.11.1")
                implementation("io.insert-koin:koin-core:3.1.4")

                implementation("io.ktor:ktor-client-core:2.0.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
                implementation("io.ktor:ktor-client-logging:2.0.0")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")

                implementation("co.touchlab:kermit:1.1.3")
                implementation(kotlin("stdlib-common"))
                implementation(project(mapOf("path" to ":common:core")))
                implementation(project(mapOf("path" to ":common:domain")))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.0.0")
                implementation("io.ktor:ktor-client-android:2.0.0")
                implementation("io.insert-koin:koin-android:3.1.6")
                implementation("com.github.chuckerteam.chucker:library:3.5.2")
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
    namespace = "com.randev.data"
    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}