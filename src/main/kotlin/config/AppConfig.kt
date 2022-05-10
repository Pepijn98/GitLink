package dev.vdbroek.config

data class BotConfig(
    val id: String,
    val token: String,
    val ownerId: String
)

data class Webhook(
    val oldId: String,
    val id: String,
    val token: String
)

data class DiscordConfig(
    val channelId: String,
    val webhook: Webhook
)

data class AppConfig(
    val env: String,
    val bot: BotConfig,
    val discord: DiscordConfig
)
