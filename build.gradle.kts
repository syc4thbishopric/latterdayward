import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.8.0"
	kotlin("plugin.spring") version "1.8.0"
	// openapi via springdoc - https://github.com/springdoc/springdoc-openapi-gradle-plugin
	id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
	id("org.siouan.frontend-jdk11") version "6.0.0"
}

group = "code.latterdayward"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

frontend {
	nodeVersion.set("19.5.0")
	assembleScript.set("run build")
	cleanScript.set("run clean")
	checkScript.set("run check")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-mail")

	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.14")

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
	this.archiveFileName.set("latter-day-ward.jar")
	launchScript()
}