plugins {
	kotlin("jvm") version "2.2.0"
	id("org.springframework.boot") version "4.0.6"
	id("io.spring.dependency-management") version "1.1.7"
    java
}

group = "com.2026-01"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib"))
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	// Swagger/OpenAPI - Documentación visual de endpoints
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
	// H2 console se habilita mediante `com.h2database:h2` y propiedades de Spring
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

kotlin {
	sourceSets["main"].kotlin.srcDir("src/main/java")
	sourceSets["test"].kotlin.srcDir("src/test/java")
}
