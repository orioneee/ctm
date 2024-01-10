plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}
android {
    namespace = "com.orion.ctm"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val coreKtx = "1.12.0"
    val activityCompose = "1.8.2"
    val composeBom = "2023.10.01"
    val liveData = "1.6.0-beta03"
    val lifeCycleRunTime = "2.6.2"


    implementation("androidx.core:core-ktx:$coreKtx")
    implementation("androidx.activity:activity-compose:$activityCompose")

    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")

    implementation("androidx.compose.runtime:runtime-livedata:$liveData")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleRunTime")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.orioneee"
                artifactId = "ctm"
                version = "1.0.0"
                from(components["release"])
            }
        }
    }
}