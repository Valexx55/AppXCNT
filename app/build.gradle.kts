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

import java.util.Properties

// Leer la clave desde local.properties
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
val mapApiKey: String = localProperties.getProperty("MAP_API_KEY") ?: ""

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
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
        resValue("string", "map_api_key", mapApiKey)
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
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    // implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
}