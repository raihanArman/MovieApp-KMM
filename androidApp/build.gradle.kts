plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.randev.movieapp_kmm.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "com.randev.movieapp_kmm.android"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":common:core"))
    implementation(project(":common:domain"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")

    //Paging
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")


    // Compose Coil
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
    implementation("io.insert-koin:koin-android:3.1.4")
//    implementation("io.insert-koin:koin-androidx-compose::3.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01")
}