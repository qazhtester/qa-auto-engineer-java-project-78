import com.github.spotbugs.snom.assign

plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("jacoco")
    id("com.github.spotbugs") version "6.5.8"
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}

spotbugs {
    toolVersion.set("4.8.6")
    ignoreFailures.set(false)
    effort.set(com.github.spotbugs.snom.Effort.DEFAULT)
    reportLevel.set(com.github.spotbugs.snom.Confidence.MEDIUM)
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask> {
    val sarifReport = reports.create("sarif")

    sarifReport.apply {
        required.set(true)
        outputLocation.set(layout.buildDirectory.file("reports/spotbugs/main.sarif"))
    }
}


