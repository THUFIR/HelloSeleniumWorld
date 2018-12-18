import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.3.11"
    id("com.github.johnrengelman.shadow") version "2.0.4"
}

group = "xxx.yyy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile  (group= "org.seleniumhq.selenium", name = "selenium-java", version =  "3.+" )
    compile  (group= "org.testng", name = "testng", version =  "6.+" )

//compile "org.testng:testng:6.3.1"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {

    baseName = "app"
    classifier = "demo"
    version = "1"


    manifest.attributes.apply {
        put("Implementation-Title", "Gradle Jar File Example")
        //put("Implementation-Version" version)
        put("Main-Class", "HelloSeleniumWorld.App")
    }

}





