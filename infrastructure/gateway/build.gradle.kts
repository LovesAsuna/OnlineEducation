plugins {
    kotlin("jvm")
}

group = "com.hyosakura"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2021.1")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer:3.0.4")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter:2.5.5")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation(project(":common:server-base"))
}