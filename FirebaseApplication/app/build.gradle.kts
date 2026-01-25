plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Firebase
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.firebaseapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.firebaseapplication"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // ================= CORE =================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // ================= COMPOSE =================
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // ================= FIREBASE =================
    implementation("com.google.firebase:firebase-auth-ktx:23.1.0")          // Authentication
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.0")     // Firestore
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")      // Realtime Database
    implementation("com.google.firebase:firebase-storage-ktx:21.0.0")       // Storage
    implementation("com.google.firebase:firebase-analytics-ktx:22.0.2")     // Analytics
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.4.4")   // Crashlytics
    implementation("com.google.firebase:firebase-config-ktx:22.0.0")        // Remote Config

    // ================= GOOGLE SIGN-IN =================
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // ================= IMAGE (COIL) =================
    implementation("io.coil-kt:coil-compose:2.7.0")

    // ================= TEST =================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}
