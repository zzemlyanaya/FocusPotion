plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.zzemlyanaya.focuspotion"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.zzemlyanaya.focuspotion"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
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
        freeCompilerArgs += arrayOf(
            "-Xopt-in=com.google.android.horologist.annotations.ExperimentalHorologistApi",
        )
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
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
    implementation(libs.datastore)
    implementation(libs.serialization.json)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
}