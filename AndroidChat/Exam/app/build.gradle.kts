plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.exam"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.exam"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.firebase:firebase-firestore:22.1.2")
    implementation ("com.google.firebase:firebase-auth:11.4.2")
    implementation ("com.firebaseui:firebase-ui-auth:7.1.1")
    implementation ("com.google.firebase:firebase-storage:16.1.0")
   implementation ("com.firebaseui:firebase-ui-database:8.0.0")
//    implementation ("com.firebaseui:firebase-ui-firestore:8.0.0")
//    implementation ("com.firebaseui:firebase-ui-auth:8.0.0")
//    implementation ("com.firebaseui:firebase-ui-storage:8.0.0")
//    implementation("com.google.android.gms:play-services-auth:17.0.0")
    //implementation ("com.google.firebase:firebase-analytics:17.2.2")
}