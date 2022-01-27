plugins {
    kotlin("jvm")
}

group = "com.hyosakura"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-starter:2.5.5")
    compileOnly("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation(project(":common:server-base"))
    implementation("com.alibaba:fastjson:1.2.78")
    implementation("com.aliyun:aliyun-java-sdk-core:4.5.30")
}