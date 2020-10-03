import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.50"
    application
    id("com.github.johnrengelman.shadow") version "5.1.0" apply false
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    id("org.jetbrains.kotlin.kapt") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.72" apply false
    id("idea")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}


allprojects {

    version = "0.1"
    group = "camunda-message-buffer-plugin"

    repositories {
        jcenter()
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                javaParameters = true
            }
        }
    }
}

/**
 * Copy files used in dockerfile build
 */
//tasks.create("setupDockerFiles"){
//    copy {
//        from("./common/tasklist")
//        into("./docker/tasklist")
//    }
//    copy {
//        from("./common/forms")
//        into("./docker/forms")
//    }
//}

/**
 * Cleanup docker files
 */
tasks.clean {
//    delete("./docker/forms")
//    delete("./docker/tasklist")
    delete(fileTree("./docker"){
        include("*.jar")
    })
}