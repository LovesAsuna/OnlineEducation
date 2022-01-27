plugins {
    kotlin("jvm")
}

group = "com.hyosakura"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("commons-dbutils:commons-dbutils:1.7")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.5.5")
    implementation("com.alibaba.otter:canal.client:1.1.0")

}