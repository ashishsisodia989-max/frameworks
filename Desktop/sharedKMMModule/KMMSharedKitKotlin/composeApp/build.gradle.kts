import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.android.library")
    id("maven-publish")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // ✅ Define targets only once
    val iosX64Target = iosX64()
    val iosArm64Target = iosArm64()
    val iosSimulatorArm64Target = iosSimulatorArm64()

    val iosTargets = listOf(iosX64Target, iosArm64Target, iosSimulatorArm64Target)

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "KMMSharedKit" // or your desired framework name
            isStatic = false // dynamic framework (usually better for SPM
            freeCompilerArgs += listOf("-Xbinary=bundleId=com.example.KMMSharedKit")
        }
    }

    // Create a task to build XCFramework bundling all targets:
    val buildType = "RELEASE"



    val createXCFramework by tasks.registering(Exec::class) {
        dependsOn(
            iosArm64Target.binaries.getFramework(buildType).linkTaskProvider,
            iosSimulatorArm64Target.binaries.getFramework(buildType).linkTaskProvider
        )

        val iosArm64Framework = iosArm64Target.binaries.getFramework(buildType).outputDirectory.resolve("KMMSharedKit.framework")
        val iosSimArm64Framework = iosSimulatorArm64Target.binaries.getFramework(buildType).outputDirectory.resolve("KMMSharedKit.framework")

        commandLine(
            "xcodebuild",
            "-create-xcframework",
            "-framework", iosArm64Framework.absolutePath,
            "-framework", iosSimArm64Framework.absolutePath,
            "-output", buildDir.resolve("bin/KMMSharedKit.xcframework").absolutePath
        )
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.kmmsharedkit"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

