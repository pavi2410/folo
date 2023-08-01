plugins {
    id("com.android.application") version "8.1.0" apply false
    kotlin("android") version "1.8.21" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.google.firebase.crashlytics") version "2.9.7" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}