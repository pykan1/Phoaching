//buildscript {
//    dependencies {
//        classpath("com.google.gms:google-services:4.3.15")
//        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.4")
//    }
//}

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.android.application).apply(false)
//    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    alias(libs.plugins.cocoapods).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildFile)
}

