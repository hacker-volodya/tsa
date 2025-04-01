allprojects {
    version = "1.0"
    group = "tsa"
}

subprojects {
    // Apply common settings
    apply(plugin = "java")

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    java {
        withSourcesJar()
        withJavadocJar()
    }

    // Only enable publishing for the specified modules
    if (project.name in listOf("tsa-core", "tsa-jettons", "tsa-metrics", "tsa-networking", "tsa-sarif", "tsa-test-gen")) {
        apply(plugin = "maven-publish")

        publishing {
            publications {
                create<MavenPublication>("maven") {
                    groupId = project.group.toString()
                    artifactId = project.name
                    version = project.version.toString()
                    from(components["java"])
                }
            }
        }
    } else {
        // For modules that should not be published, disable publish tasks
        tasks.matching { it.name.startsWith("publish") }.configureEach {
            enabled = false
        }
    }
}
