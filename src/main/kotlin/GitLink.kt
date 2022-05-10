package dev.vdbroek

import com.sksamuel.hoplite.ConfigLoader
import dev.kord.core.Kord
import dev.kord.core.behavior.executeIgnored
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.core.supplier.EntitySupplyStrategy
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.kord.rest.builder.message.create.embed
import dev.vdbroek.config.AppConfig
import dev.vdbroek.utils.snowflake

val config = ConfigLoader().loadConfigOrThrow<AppConfig>("/application.conf")

suspend fun main() {
    val kord = Kord(config.bot.token) {
        defaultStrategy = EntitySupplyStrategy.cacheWithCachingRestFallback
    }

    kord.on<MessageCreateEvent> {
        if (
            message.data.author.id == "426479683249504270".snowflake &&
            message.channelId == config.discord.channelId.snowflake &&
            message.embeds.isNotEmpty()
        ) {
            val webhook = kord.getWebhookOrNull(config.discord.webhook.id.snowflake) ?: return@on
            val embed = message.embeds[0]
            println(embed)
            println(message.author)

            webhook.executeIgnored(config.discord.webhook.token) {
                username = message.data.author.username ?: "GitHub"
                avatarUrl =
                    if (message.data.author.avatar != null) "https://cdn.discordapp.com/avatars/${message.data.author.id}/${message.data.author.avatar}.webp?size=80" else null
                        ?: webhook.data.avatar
                        ?: "https://b.catgirlsare.sexy/H78e62IgOKHq.png"
                embed {
                    author {
                        icon = embed.author?.iconUrl ?: "https://b.catgirlsare.sexy/692WKMorWBmn.png"
                        url = embed.author?.url ?: ""
                        name = embed.author?.name ?: "Unknown"
                    }
                    title = embed.title
                    url = embed.url
                    color = embed.color
                    description = embed.description
                }
            }
        }
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
