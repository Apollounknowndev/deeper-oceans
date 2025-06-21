
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.10.4"
    id ("me.modmuss50.mod-publish-plugin") version "0.8.4"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenCentral()
    maven("https://api.modrinth.com/maven")
}

group = "dev.worldgen.deeper.oceans"

val modVersion: String by project
version = modVersion

cloche {
    mappings {
        official()
    }

    metadata {
        modId = "deeper_oceans"
        name = "Deeper Oceans"
        description = "Oceans, but deeper!"
        license = "MIT"
        icon = "pack.png"

        author("Apollo")
    }

    common {
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            compileOnly("org.spongepowered:mixin:0.8.3")
        }
    }
    val shared120 = common("shared:1.20") {}
    val shared121 = common("shared:1.21") {}

    fabric("fabric:1.20.1") {
        dependsOn(shared120)

        loaderVersion = "0.16.13"
        minecraftVersion = "1.20.1"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            fabricApi("0.92.6")
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-fabric-1.20")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }

            entrypoint("main") {
                value = "dev.worldgen.deeper.oceans.fabric.DeeperOceansFabric"
            }
        }

    }

    fabric("fabric:1.21.1") {
        dependsOn(shared121)

        loaderVersion = "0.16.13"
        minecraftVersion = "1.21.1"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            fabricApi("0.116.1")
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-fabric-1.21")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }

            entrypoint("main") {
                value = "dev.worldgen.deeper.oceans.fabric.DeeperOceansFabric"
            }
        }
    }

    fabric("fabric:1.21.6") {
        dependsOn(shared121)

        loaderVersion = "0.16.13"
        minecraftVersion = "1.21.6"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            fabricApi("0.127.0", "1.21.6")
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-fabric-1.21.5")
        }

        includedClient()
        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }

            entrypoint("main") {
                value = "dev.worldgen.deeper.oceans.fabric.DeeperOceansFabric"
            }
        }
    }

    forge("forge:1.20.1") {
        dependsOn(shared120)

        loaderVersion = "47.4.0"
        minecraftVersion = "1.20.1"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-forge-1.20")
        }

        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }
        }
    }

    neoforge("neoforge:1.21.1") {
        dependsOn(shared121)

        loaderVersion = "21.1.26"
        minecraftVersion = "1.21.1"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-neoforge-1.21")
        }

        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }
        }
    }

    neoforge("neoforge:1.21.6") {
        dependsOn(shared121)

        loaderVersion = "21.6.0-beta"
        minecraftVersion = "1.21.6"
        mixins.from(file("src/common/main/deeper_oceans.mixins.json"))

        dependencies {
            modRuntimeOnly("maven.modrinth:lithostitched:1.4.8-neoforge-1.21.5")
        }

        runs {
            client()
            server()
        }

        metadata {
            dependencies {
                dependency {
                    modId = "lithostitched"
                    required = true
                }
            }
        }
    }
}

publishMods {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    println("Loader? ")
    val loader = reader.readLine()
    val loaderName = loader.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else {
            it.toString()
        }
    }
    println("Game version? ")
    val gameVersion = reader.readLine()


    file = file("build/libs/deeper-oceans-$modVersion-$loader-$gameVersion.jar")
    changelog = """
        We are so back.
        
        - Updated to 1.21.1 and 1.21.6.
        - Decreased the default depth from 3x deeper to 2x deeper.
        - Lowered ocean monuments by 20 blocks by default.
        - Added a config file for changing the ocean depth multiplier and ocean monument offset.
    """.trimIndent()
    type = STABLE
    modLoaders.add(loader)
    displayName = "v$modVersion ~ $loaderName $gameVersion"
    version = "$modVersion-$loader-$gameVersion"

    dryRun = false

    modrinth {
        accessToken = providers.environmentVariable("TOKEN_MR")
        projectId = "yqHsPROA"
        minecraftVersions.add(gameVersion)
        requires("lithostitched")
    }

    curseforge {
        accessToken = providers.environmentVariable("TOKEN_CF")
        projectId = "714177"
        minecraftVersions.add(gameVersion)
        requires("lithostitched")
    }
}