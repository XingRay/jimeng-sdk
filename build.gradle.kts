plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.xingray"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(17)
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("io.github.xingray", "jimeng-sdk", version.toString())

    pom {
        name.set("Jimeng SDK")
        description.set("Kotlin SDK for Jimeng AI on Volcengine Visual Intelligence platform")
        url.set("https://github.com/XingRay/jimeng-sdk")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("XingRay")
                name.set("XingRay")
                url.set("https://github.com/XingRay")
            }
        }

        scm {
            url.set("https://github.com/XingRay/jimeng-sdk")
            connection.set("scm:git:git://github.com/XingRay/jimeng-sdk.git")
            developerConnection.set("scm:git:ssh://git@github.com/XingRay/jimeng-sdk.git")
        }
    }
}
