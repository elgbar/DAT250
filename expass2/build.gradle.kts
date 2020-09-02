import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    java
    idea
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "11"
compileTestKotlin.kotlinOptions.jvmTarget = "11"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}


dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.eclipse.persistence:eclipselink:2.7.7")
    implementation("org.apache.derby:derby:10.15.2.0")
    implementation("org.apache.derby:derbytools:10.15.2.0")

    testImplementation("junit:junit:4.13")
    testImplementation(kotlin("test-junit"))
}
