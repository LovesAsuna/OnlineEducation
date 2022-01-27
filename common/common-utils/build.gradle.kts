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
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    api("io.jsonwebtoken:jjwt:0.9.1")
    compileOnly("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
}