plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvmToolchain(17)
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }




    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)

            implementation("io.ktor:ktor-client-content-negotiation:2.3.11")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            api("io.ktor:ktor-serialization-kotlinx-json:2.3.11")

            api("io.insert-koin:koin-core:3.4.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            api("io.insert-koin:koin-android:3.4.1")
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.example.socialmediaapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


