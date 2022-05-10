package dev.vdbroek.config

data class BotConfig(
    val id: String,
    val token: String,
    val ownerId: String
)

data class DiscordConfig(
    val fromGuild: String,
    val fromChannel: String,
    val toGuild: String,
    val toChannel: String
)

data class AppConfig(
    val bot: BotConfig,
    val discord: DiscordConfig
)
