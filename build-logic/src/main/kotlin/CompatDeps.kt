val COMPAT_MODS: List<Pair<String, String>> = listOf(
	"atmospheric" to "atmospheric",
	"autumnity" to "autumnity",
	"betterend" to "betterend",
	"betternether" to "betternether",
	"biomesoplenty" to "biomesoplenty",
	"environmental" to "environmental",
	"natures_spirit" to "natures-spirit",
	"quark" to "quark",
	"regions_unexplored" to "regions-unexplored",
	"twilightforest" to "twilight-forest"
)

fun DependenciesConfig.optionalCompatMods() {
	COMPAT_MODS.forEach { (modid, slug) ->
		optional(modid) {
			slug(slug)
			forgeLikeVersionRange.set("[0,)")
		}
	}
}
