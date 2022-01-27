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
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2021.1")
    implementation(fileTree("lib") { include("*.jar") })
    implementation(project(":common:server-base"))
    implementation("com.aliyun:aliyun-java-sdk-core:4.5.1")
    implementation("com.aliyun:aliyun-java-sdk-vod:2.15.11")
    implementation("com.aliyun:aliyun-java-sdk-kms:2.10.1")
    implementation("com.alibaba:fastjson:1.2.78")
}