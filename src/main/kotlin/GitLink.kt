package dev.vdbroek

import com.sksamuel.hoplite.ConfigLoader
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.vdbroek.config.AppConfig

val config = ConfigLoader().loadConfigOrThrow<AppConfig>("/application.conf")

suspend fun main() {
    val kord = Kord(config.bot.token)

    kord.on<MessageCreateEvent> {
        if (message.author?.isBot == true) return@on
        println(message)
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
