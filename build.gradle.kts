import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.20"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.20"
}

apply {
    plugin("org.jetbrains.kotlin.plugin.allopen")
    plugin("org.jetbrains.kotlin.plugin.jpa")
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("javax.persistence.Entity")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("net.javacrumbs.shedlock:shedlock-spring:4.25.0")
    implementation("net.javacrumbs.shedlock:shedlock-provider-redis-spring:4.25.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
