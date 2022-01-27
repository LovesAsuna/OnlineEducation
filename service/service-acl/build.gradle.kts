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
    implementation("org.springframework.boot:spring-boot-starter:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation(project(":common:security"))
    implementation(project(":common:server-base"))
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation("com.alibaba:fastjson:1.2.79")
}

sourceSets {
    main {
        resources {
            srcDir("src/main/kotlin/")
            exclude("**/*.kt")
        }
    }
}