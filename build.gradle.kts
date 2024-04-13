plugins {
    id("java")
}

group = "net.creative-conduit"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.jboss.resteasy:resteasy-undertow:6.2.8.Final")
    implementation("org.jboss.resteasy:resteasy-cdi:6.2.8.Final")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    implementation("org.jboss.weld.servlet:weld-servlet-core:5.1.2.Final")
}

tasks.test {
    useJUnitPlatform()
}
