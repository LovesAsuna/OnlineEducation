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
    api("org.springframework.boot:spring-boot-starter-data-redis:2.5.5")
    implementation("org.apache.commons:commons-pool2:2.11.1")
    api("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    api(project(":common:common-utils"))
}

