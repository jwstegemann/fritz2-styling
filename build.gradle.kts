buildscript {
    repositories {
        mavenLocal() //FIXME: nicht einchecken
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin"))
        classpath("dev.fritz2:fritz2-gradle-plugin:0.8-SNAPSHOT")
    }
}

plugins {
    kotlin("multiplatform") version ("1.4.0")
    kotlin("kapt") version ("1.4.0")
//    id("dev.fritz2.fritz2-gradle") version "0.8"
}

apply(plugin = "dev.fritz2.fritz2-gradle")
repositories {
    mavenLocal()
    jcenter()
}

kotlin {
    kotlin {
        jvm()
        js().browser()

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(kotlin("stdlib"))
                }
            }
            val jvmMain by getting {
                dependencies {
                }
            }
            val jsMain by getting {
                dependencies {
                    implementation(npm("stylis", "4.0.2"))
                    implementation(npm("murmurhash", "1.0.0"))
                }
            }
        }
    }
}
