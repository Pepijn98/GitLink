package dev.vdbroek

import com.sksamuel.hoplite.ConfigLoader
import dev.kord.core.Kord
import dev.kord.core.behavior.execute
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
        println(message.author?.id)
        println("426479683249504270".snowflake)
        println("-----------------------------------------")
        println(message.channelId)
        println(config.discord.channelId.snowflake)
        println("-----------------------------------------")
        println(message.embeds)
        println(message.embeds.isNotEmpty())

        if (
            message.author?.id == "426479683249504270".snowflake &&
            message.channelId == config.discord.channelId.snowflake &&
            message.embeds.isNotEmpty()
        ) {
            val webhook = kord.getWebhookOrNull(config.discord.webhook.id.snowflake) ?: return@on
            val embed = message.embeds[0]
            println(embed)
            println(message.author)

            webhook.execute(config.discord.webhook.token) {
                username = message.author?.username ?: "GitHub"
                avatarUrl = message.author?.avatar?.url ?: webhook.data.avatar ?: "https://b.catgirlsare.sexy/H78e62IgOKHq.png"
                embed {
                    author {
                        icon = embed.author?.iconUrl ?: "https://b.catgirlsare.sexy/692WKMorWBmn.png"
                        url = embed.author?.url ?: ""
                        name = embed.author?.name ?: "Unknown"
                    }
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
