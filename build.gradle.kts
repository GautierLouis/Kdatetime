import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("native.cocoapods") version "1.6.10"
    id("com.android.library")
}

group = "com.louis.kdatetime"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") { }
    watchos()
    watchosSimulatorArm64()
    tvos()
    tvosSimulatorArm64()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    android {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }


    cocoapods {
        summary = "A DateTimeFormatter written for KMP"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.0"
        watchos.deploymentTarget = "7.2"
        tvos.deploymentTarget = "15.2"
        osx.deploymentTarget = "11.0"
        framework {
            baseName = rootProject.name
            embedBitcode(BITCODE)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }


        val iosMain by getting
        val watchosMain by getting
        val tvosMain by getting
//        val macosMain by getting
        val watchosSimulatorArm64Main by getting
        val tvosSimulatorArm64Main by getting

        val iosTest by getting
        val watchosTest by getting
        val tvosTest by getting
//        val macosTest by getting
        val watchosSimulatorArm64Test by getting
        val tvosSimulatorArm64Test by getting

        listOf(
            watchosMain,
            tvosMain, /*macosMain,*/
            watchosSimulatorArm64Main,
            tvosSimulatorArm64Main
        )
            .forEach { it.dependsOn(iosMain) }

        listOf(
            watchosTest,
            tvosTest, /*macosTest,*/
            watchosSimulatorArm64Test,
            tvosSimulatorArm64Test
        )
            .forEach { it.dependsOn(iosTest) }
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}