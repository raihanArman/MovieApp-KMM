plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("koin")
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
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "Shared"
//            isStatic = false
//            transitiveExport = true

            export(project(":common:domain"))
            export(project(":common:data"))
            export(project(":libraries:network"))
            export(project(":libraries:database"))
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.2.2")

                api(project(":common:domain"))
                api(project(":common:data"))
                api(project(":libraries:network"))
                api(project(":libraries:database"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
//                implementation("io.insert-koin:koin-android:3.1.6")
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
        }

        iosSimulatorArm64Main.dependsOn(iosMain)

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
    namespace = "com.randev.shared"
    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}