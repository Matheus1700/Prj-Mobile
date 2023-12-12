plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.duolingo"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    viewBinding {
        enable = true
    }

    defaultConfig {
        applicationId = "com.example.duolingo"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //add-2
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    //add-1
    implementation ("androidx.navigation:navigation-fragment:2.7.5")
    implementation ("androidx.navigation:navigation-ui:2.7.5")
    implementation ("androidx.lifecycle:lifecycle-common:2.5.1")

    implementation ("com.google.guava:guava:30.1-jre")

    implementation ("androidx.room:room-runtime:2.3.0")
    annotationProcessor ("androidx.room:room-compiler:2.3.0")

    testImplementation("junit:junit:4.13.2");
    testImplementation("org.mockito:mockito-core:3.12.4");
    androidTestImplementation("androidx.test:core:1.4.0");
    testImplementation("com.android.support.test:runner:1.0.2");
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0");
    androidTestImplementation ("androidx.test:rules:1.4.0");
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.4.0");

}