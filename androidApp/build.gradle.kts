plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    kotlin("plugin.serialization") version "1.9.10"


}

android {
    namespace = "com.example.socialmediaapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.socialmediaapp.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation(libs.compose.material3)
    implementation("androidx.activity:activity-compose:1.7.2")

//    compose destination
    implementation(libs.core.v210beta02)
    ksp(libs.ksp.v210beta02)

//    splashScreen
    implementation(libs.androidx.core.splashscreen)


    implementation(libs.androidx.datastore.preferences)

    implementation(libs.koin.androidx.compose.v340)

    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.coil.compose)

//ktor
//    implementation(libs.ktor.client.okhttp)

//    coroutines
    implementation(libs.kotlinx.coroutines.android)
}