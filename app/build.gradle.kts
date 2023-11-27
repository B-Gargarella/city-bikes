plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    apply(from = "../versions.gradle")

    namespace = "com.bgargarella.citybikes"
    compileSdk = extra["compile_sdk_version"] as Int

    defaultConfig {
        applicationId = "com.bgargarella.citybikes"
        minSdk = extra["min_sdk_version"] as Int
        targetSdk = extra["target_sdk_version"] as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildFeatures {
            buildConfig = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    val javaVersion = extra["java_version"] as JavaVersion

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }

    buildToolsVersion = extra["build_tools_version"] as String
}

dependencies {
    val androidMaterialVersion = rootProject.extra["android_material_version"]
    implementation("com.google.android.material:material:$androidMaterialVersion")

    val junitVersion = rootProject.extra["junit_version"]
    testImplementation("junit:junit:$junitVersion")
    val extJunitVersion = rootProject.extra["ext_junit_version"]
    androidTestImplementation("androidx.test.ext:junit:$extJunitVersion")
    val espressoCoreVersion = rootProject.extra["espresso_core_version"]
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCoreVersion")
}