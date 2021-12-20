plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        applicationId = "com.pol0.maquotes"

        compileSdk = AndroidSdk.compileSdkVersion
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    kotlinOptions {
        jvmTarget = "1.8"
    }


    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Local Modules
    implementation(project(BuildModules.domainModule))
    implementation(project(BuildModules.remoteModule))
    implementation(project(BuildModules.localModule))
    implementation(project(BuildModules.repositoryModule))

    //Ktx Core
    implementation(Libraries.ktxCore)

    //UI
    implementation(Libraries.appCompat)
    implementation(Libraries.materialComponents)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.fragment)
    implementation(Libraries.legacySupport)

    //Hilt
    implementation(Libraries.hilt)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    kapt(Libraries.hiltKapt)

    //Logging: Timber
    implementation(Libraries.timber)

    //navigation
    implementation(Libraries.navigationUiKtx)
    implementation(Libraries.navigationFragmentKtx)

    //Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.gson)
    implementation(Libraries.loggingInterceptor)

    //Room
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomKtx)
    kapt(Libraries.roomKapt)

    //Test
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.extJunit)

    //Android Test
    androidTestImplementation(TestLibraries.espresso)
}