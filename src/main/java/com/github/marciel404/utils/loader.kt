package com.github.marciel404.utils

import com.github.marciel404.commands.slash.eventos
import com.github.marciel404.events.*
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.requests.GatewayIntent

object loader {

    fun client(token: String){

        var Jda = JDABuilder.createLight(token)
            .enableIntents(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS
            )
            .disableIntents(
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_EMOJIS_AND_STICKERS
            )
            .addEventListeners(
                Gerais(),
                buttons(),
                eventos()
            )
            .build()

        loadCommands(Jda)
    }

    private fun loadCommands(client: JDA) {

        client.upsertCommand("anunciar_evento", "Anuncia um evento")
            .setGuildOnly(true)
            .addOption(
                OptionType.STRING,
                "json",
                "Json da embed"
            )
            .queue()

        client.upsertCommand("lista_participantes","Envia a lista de membros")
            .setGuildOnly(true)
            .queue()
    }
}