plugins {
	id("mod-platform")
	id("net.neoforged.moddev")
}

stonecutter {
	replacements.string(current.parsed >= "1.21.11") {
		replace("ResourceLocation", "Identifier")
		replace("location()", "identifier()")
	}
}

platform {
	loader = "neoforge"
	dependencies {
		required("minecraft") {
			forgeLikeVersionRange = prop("deps.minecraft")
		}
		required("neoforge") {
			forgeLikeVersionRange.set("[${prop("deps.neoforge")},)")
		}

		optionalCompatMods()
	}
}

neoForge {
	version = prop("deps.neoforge")

	if (hasProperty("deps.parchment")) parchment {
		val (mc, ver) = prop("deps.parchment").split(':')
		mappingsVersion = ver
		minecraftVersion = mc
	}

	runs {
		val devJvmArgs = propsList("mod", "dev_jvm_args")
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "NeoForge Client (${stonecutter.current.version})"
			programArgument("--username=Dev")
			jvmArguments.addAll(devJvmArgs)
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "NeoForge Server (${stonecutter.current.version})"
			jvmArguments.addAll(devJvmArgs)
		}
	}

	mods {
		register(prop("mod.id")) {
			sourceSet(sourceSets["main"])
		}
	}
	sourceSets["main"].resources.srcDir("${rootDir}/versions/datagen/${sc.current.version}/src/main/generated")
}

repositories {
	mavenCentral()
	strictMaven("https://api.modrinth.com/maven", "maven.modrinth") { name = "Modrinth" }
}


tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}

val verSrc = rootProject.file("src/${sc.current.version}/neoforge")
sourceSets["main"].java.srcDir(rootProject.file("src/neoforge/java"))
val familySrc = rootProject.file("src/${if (sc.current.version.startsWith("26")) "26.x" else "1.21.x"}/neoforge/java")
if (familySrc.exists()) sourceSets["main"].java.srcDir(familySrc)
sourceSets["main"].java.srcDir(verSrc.resolve("java"))
sourceSets["main"].resources.srcDir(verSrc.resolve("resources"))

repositories {
	maven("https://maven.shedaniel.me/") { name = "Shedaniel" }
	maven("https://maven.blamejared.com") { name = "BlameJared" }
}

dependencies {
	compileOnly("maven.modrinth:jade:${prop("deps.jade")}+neoforge")
	runtimeOnly("maven.modrinth:jade:${prop("deps.jade")}+neoforge")
	runtimeOnly("mezz.jei:jei-${prop("deps.minecraft")}-neoforge:${prop("deps.jei")}")
	if (hasProperty("deps.mezz-config")) {
		runtimeOnly("net.mezzdev.config:mezz_config-${prop("deps.minecraft")}-neoforge:${prop("deps.mezz-config")}")
	}
	if (hasProperty("deps.every-compat")) {
		compileOnly("maven.modrinth:every-compat:${prop("deps.every-compat")}")
	}
	if (hasProperty("deps.moonlight")) {
		compileOnly("maven.modrinth:moonlight:${prop("deps.moonlight")}")
	}
	compileOnly("me.shedaniel.cloth:cloth-config-neoforge:${prop("deps.cloth-config")}")
}
