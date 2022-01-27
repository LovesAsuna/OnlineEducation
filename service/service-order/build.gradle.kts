plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.wxpay:wxpay-sdk:0.0.3")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-impl:3.0.2")
    implementation("com.sun.xml.bind:jaxb-core:3.0.2")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2021.1")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation(project(":common:server-base"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("com.baomidou:mybatis-plus-generator:3.5.1")
    testImplementation("org.apache.velocity:velocity-engine-core:2.3")
}

tasks.test {
    useJUnitPlatform()
}