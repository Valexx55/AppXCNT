/*
DIFERENCISA ENTRE PLUGIN Y ARTIFACT
| Momento    | Plugin                  | Artifact                 |
| ---------- | ----------------------- | ------------------------ |
| Fase       | Build time              | Runtime / Compile time   |
| Se ejecuta | Durante Gradle build    | En la app                |
| Afecta a   | Proceso de construcción | Comportamiento de la app |
| Añade      | Tasks, extensiones      | Clases, recursos         |
| Vive en    | Gradle                  | APK / AAB                |
*/

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "edu.cas.appxcnt.profe"
    compileSdk = 36

    defaultConfig {
        applicationId = "edu.cas.appxcnt.profe"
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
        viewBinding = true //configuración de vinculación de vistas
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
}