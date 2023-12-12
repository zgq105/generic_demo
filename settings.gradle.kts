pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library(
                "navigation-safe-args-gradle-plugin",
                "androidx.navigation",
                "navigation-safe-args-gradle-plugin"
            )
                .version("2.5.3")
        }
    }
}

rootProject.name = "demo01"
include(":app")
include(":module1")
include(":module2")
include(":common")
include(":nativelib_m3")
