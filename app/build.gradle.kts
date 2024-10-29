plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.zzemlyanaya.focuspotion"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.zzemlyanaya.focuspotion"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        jniLibs {
            pickFirsts += listOf("**/armeabi-v7a/libc++_shared.so", "**/x86/libc++_shared.so", "**/arm64-v8a/libc++_shared.so", "**/x86_64/libc++_shared.so")
        }
        resources {
            excludes += listOf("META-INF/rxjava.properties", "META-INF/rxkotlin.properties", "/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(libs.bundles.androidx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.wear)
    implementation(libs.bundles.horologist)

    implementation(libs.play.services.wearable)

    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
}