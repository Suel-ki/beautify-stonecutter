import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

plugins {
	id("mod-platform")
	id("dev.kikugie.loom-back-compat")
}


stonecutter {
	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}
	replacements.string(current.parsed >= "26.1.2") {
		replace("FabricDataOutput", "FabricPackOutput")
	}
}

platform {
	loader = "fabric"
	dependencies {
		required("minecraft") {
			fabricLikeVersionRange = prop("deps.minecraft")
		}
		required("fabric-api") {
			slug("fabric-api")
			fabricLikeVersionRange = ">=${prop("deps.fabric-api")}"
		}
		required("fabricloader") {
			fabricLikeVersionRange = if (hasProperty("deps.fabric-loader-range")) prop("deps.fabric-loader-range")
			else ">=${prop("deps.fabric-loader")}"
		}
		optional("modmenu") {}

		optionalCompatMods()
	}
}

loom {
	val awDir = rootProject.file("src/${sc.current.version}/fabric/resources")
	val aw = listOf(awDir.resolve("beautify.classtweaker"), awDir.resolve("beautify.accesswidener"))
		.firstOrNull { it.exists() }
	if (aw != null) accessWidenerPath = aw
	val devJvmArgs = propsList("mod", "dev_jvm_args")
	runs.named("client") {
		client()
		generateRunConfig.set(true)
		runDirectory.set(layout.projectDirectory.dir("run"))
		programArguments.add("--username=Dev")
		jvmArguments.addAll(devJvmArgs)
		displayName.set("Fabric Client")
	}
	runs.named("server") {
		server()
		generateRunConfig.set(true)
		runDirectory.set(layout.projectDirectory.dir("run"))
		jvmArguments.addAll(devJvmArgs)
		displayName.set("Fabric Server")
	}
}

tasks.withType<Jar>().configureEach {
	exclude("io/github/suel_ki/beautify/platform/fabric/datagen/**")
}

tasks.named<Jar>("jar") {
	doLast {
		val jarFile = archiveFile.get().asFile
		if (!jarFile.exists()) return@doLast
		val tmp = File(jarFile.parentFile, jarFile.name + ".tmp")
		ZipFile(jarFile).use { zip ->
			ZipOutputStream(tmp.outputStream()).use { out ->
				for (entry in zip.entries()) {
					val bytes = zip.getInputStream(entry).readBytes()
					val data = if (entry.name == "fabric.mod.json") {
						String(bytes, Charsets.UTF_8)
							.replace(Regex(""",\s*"fabric-datagen"\s*:\s*\[[^\]]*\]"""), "")
							.toByteArray(Charsets.UTF_8)
					} else bytes
					out.putNextEntry(ZipEntry(entry.name))
					out.write(data)
					out.closeEntry()
				}
			}
		}
		tmp.copyTo(jarFile, overwrite = true)
		tmp.delete()
	}
}

fabricApi {
	configureDataGeneration {
		outputDirectory = file("${rootDir}/versions/datagen/${sc.current.version}/src/main/generated")
		client = true
	}
}

tasks.matching { it.name == "runDatagen" }.configureEach {
	(this as JavaExec).systemProperty("beautify_compat.all_woods", "true")
	val recipesAdvancements = file("${rootDir}/versions/datagen/${sc.current.version}/src/main/generated/data/beautify/advancement/recipes")
	val recipesAdvancementsPlural = file("${rootDir}/versions/datagen/${sc.current.version}/src/main/generated/data/beautify/advancements/recipes")
	doLast {
		recipesAdvancements.deleteRecursively()
		recipesAdvancementsPlural.deleteRecursively()
	}
}

repositories {
	mavenCentral()
	strictMaven("https://maven.terraformersmc.com/", "com.terraformersmc") { name = "TerraformersMC" }
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
}

configurations.all {
	resolutionStrategy {
		force("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("deps.minecraft")}")
	if (sc.current.parsed < "26") {
		mappings(loom.layered {
			officialMojangMappings()
			if (hasProperty("deps.parchment"))
				parchment("org.parchmentmc.data:parchment-${prop("deps.parchment")}@zip")
		})
	}
	modImplementation("net.fabricmc:fabric-loader:${prop("deps.fabric-loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("deps.fabric-api")}")
	if (hasProperty("deps.cloth-config")) {
		modLocalRuntime("me.shedaniel.cloth:cloth-config-fabric:${prop("deps.cloth-config")}")
	}
	modLocalRuntime("com.terraformersmc:modmenu:${prop("deps.modmenu")}")
}

val verSrc = rootProject.file("src/${sc.current.version}/fabric")
sourceSets["main"].java.srcDir(rootProject.file("src/fabric/java"))
val familySrc = rootProject.file("src/${if (sc.current.version.startsWith("26")) "26.x" else "1.21.x"}/fabric/java")
if (familySrc.exists()) sourceSets["main"].java.srcDir(familySrc)
sourceSets["main"].java.srcDir(verSrc.resolve("java"))
sourceSets["main"].resources.srcDir(verSrc.resolve("resources"))


repositories {
	maven("https://maven.shedaniel.me/") { name = "Shedaniel" }
	maven("https://cursemaven.com") { name = "CurseMaven" }
	maven("https://maven.blamejared.com") { name = "BlameJared" }
}

dependencies {
	if (hasProperty("deps.jade-curse")) {
		modCompileOnly("curse.maven:jade-324717:${prop("deps.jade-curse")}")
		modLocalRuntime("curse.maven:jade-324717:${prop("deps.jade-curse")}")
	} else {
		modCompileOnly("maven.modrinth:jade:${prop("deps.jade")}+fabric")
		modLocalRuntime("maven.modrinth:jade:${prop("deps.jade")}+fabric")
	}
	modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:${prop("deps.cloth-config")}")
	modLocalRuntime("mezz.jei:jei-${prop("deps.minecraft")}-fabric:${prop("deps.jei")}")
	if (hasProperty("deps.mezz-config")) {
		modLocalRuntime("net.mezzdev.config:mezz_config-${prop("deps.minecraft")}-fabric:${prop("deps.mezz-config")}")
	}
}

dependencies {
	modCompileOnly("com.terraformersmc:modmenu:${prop("deps.modmenu")}")
}
dependencies {
	if (hasProperty("deps.every-compat")) {
		modCompileOnly("maven.modrinth:every-compat:${prop("deps.every-compat")}")
	}
	if (hasProperty("deps.moonlight")) {
		modCompileOnly("maven.modrinth:moonlight:${prop("deps.moonlight")}")
	}
}



