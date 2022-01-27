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
    implementation(project(":common:server-base"))
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    testImplementation("com.baomidou:mybatis-plus-generator:3.5.1")
    testImplementation("org.apache.velocity:velocity-engine-core:2.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}