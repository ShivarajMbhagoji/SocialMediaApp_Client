plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.9.10"
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
            implementation(libs.kotlinx.coroutines.core)
            api(libs.ktor.serialization.kotlinx.json)

            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:2.3.11")
            api("io.insert-koin:koin-android:3.3.1")
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
dependencies {
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(project(":shared"))
    implementation(project(":shared"))
    implementation(project(":shared"))
}


