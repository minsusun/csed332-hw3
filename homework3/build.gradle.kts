plugins {
    java
    jacoco
}

group = "edu.postech.csed332"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "edu.postech.csed332.homework3.GameUI"
    }
}
