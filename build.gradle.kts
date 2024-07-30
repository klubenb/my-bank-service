plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.flywaydb.flyway") version "9.8.1"
    id("jacoco")
}

group = "ru.skillfactory"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

extra["springCloudVersion"] = "2023.0.1"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
//    //    web
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter")
//    implementation("io.swagger.core.v3:swagger-annotations:2.2.22")
//    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//    //    db
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.flywaydb:flyway-core")
//    implementation("io.hypersistence:hypersistence-utils-hibernate-63:3.7.3")
//    annotationProcessor("org.hibernate:hibernate-jpamodelgen:6.4.4.Final")
//    // mapper
//    implementation("org.mapstruct:mapstruct:1.5.3.Final")
//    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
//
//    compileOnly("org.projectlombok:lombok")
//    runtimeOnly("org.postgresql:postgresql")
//    annotationProcessor("org.projectlombok:lombok")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//
//
//
//
    // web
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // db
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("io.hypersistence:hypersistence-utils-hibernate-63:3.7.3")
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:6.4.4.Final")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    // mapper
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:5.11.0")
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

springBoot {
    buildInfo()
}
