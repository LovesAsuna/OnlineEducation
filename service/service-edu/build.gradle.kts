plugins {
    kotlin("jvm")
}

group = "com.hyosakura"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

fun DependencyHandler.starter() {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2021.1")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.5.5")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation("com.alibaba:easyexcel:3.0.2")
    testImplementation("com.baomidou:mybatis-plus-generator:3.5.1")
    testImplementation("org.apache.velocity:velocity-engine-core:2.3")
}

dependencies {
    starter()
    implementation(project(":common:server-base"))
    implementation("mysql:mysql-connector-java:8.0.25")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.5")
}

sourceSets {
    main {
       resources {
           srcDir("src/main/kotlin/")
           exclude("**/*.kt")
       }
    }
}

configurations.all {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.test {
    useJUnitPlatform()
}