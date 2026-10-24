import dev.kikugie.stonecutter.controller.StonecutterControllerExtension
import me.modmuss50.mpp.ModPublishExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.getByType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import java.io.File


class ModRootPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		with(project) {
			val stonecutter = extensions.getByType<StonecutterControllerExtension>()
			val properties = stonecutter.properties
			val modVersion = properties.getAs<String>("mod.version")
			val channelTag = properties.getAs<String>("mod.channel_tag")
			val changelogText = file("CHANGELOG.md").takeIf { it.exists() }?.readText() ?: ""
			val githubToken = providers.environmentVariable("GITHUB_TOKEN")

			extensions.configure<ModPublishExtension>("publishMods") {
				dryRun = envTrue("PUB_DRY_RUN") || !envTrue("PUB_GITHUB_ENABLE")
				version = modVersion
				changelog.set(changelogText)
				type = releaseTypeFromChannelTag(channelTag)
				if (envTrue("PUB_GITHUB_ENABLE")) {
					github {
						accessToken = githubToken
						repository = providers.environmentVariable("GITHUB_REPOSITORY")
						commitish = providers.environmentVariable("GITHUB_SHA").orElse("main")
						tagName = providers.environmentVariable("RELEASE_TAG")
							.orElse(providers.environmentVariable("GITHUB_REF_NAME"))
						allowEmptyFiles = true
					}
				}
			}

			stonecutter.tasks {
				order("publishModrinth")
				order("publishCurseforge")
			}

			tasks.register("runActiveClient") {
				group = "stonecutter"
				description = "Run client of the active Stonecutter version"
				dependsOn(stonecutter.current!!.project + ":runClient")
			}
			tasks.register("runActiveServer") {
				group = "stonecutter"
				description = "Run server of the active Stonecutter version"
				dependsOn(stonecutter.current!!.project + ":runServer")
			}

			for (version in stonecutter.versions.map { it.version }.distinct()) tasks.register("publish$version") {
				group = "publishing"
				dependsOn(stonecutter.tasks.named("publishMods") { metadata.version == version })
			}

			val datagenPaths = stonecutter.versions.map { ":${it.project}:runDatagen" }
			gradle.projectsEvaluated {
				var previous: Task? = null
				for (path in datagenPaths) {
					val run = project(path.substringBeforeLast(':')).tasks.findByPath("runDatagen") ?: continue
					previous?.let { run.mustRunAfter(it) }
					previous = run
				}
			}

			tasks.register("datagen") {
				group = "stonecutter"
				description = "Runs data generation for every supported version, then fixes the recipes"
				dependsOn(datagenPaths)
			}

		}
	}
}

