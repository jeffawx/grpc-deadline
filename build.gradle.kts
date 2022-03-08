import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.airwallex.grpc-spring") apply false
}

group = "demo"
version = "0.0.1-SNAPSHOT"

allprojects {
    repositories {
        mavenLocal()
        maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.airwallex.grpc-spring")

    java.sourceCompatibility = JavaVersion.VERSION_11

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }

        test {
            useJUnitPlatform()
        }
    }
}
