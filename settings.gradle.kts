rootProject.name = "grpc-deadline"

pluginManagement {
    repositories {
        mavenLocal()
        maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "1.6.10"
        id("com.airwallex.grpc-spring") version "1.2.1"
    }
}

include("api", "service1", "service2", "service3")
