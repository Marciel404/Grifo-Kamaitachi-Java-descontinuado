package com.github.marciel404.commands.slash;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import static com.github.marciel404.utils.Configs.configData;

public class eventos extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!event.isFromGuild()) return;

        switch (event.getName()){
            case "lista_participantes" -> {
                MessageEmbed e = new EmbedBuilder()
                        .setTitle("Lista de participantes")
                        .build();
                event.reply("Enviado com sucesso").setEphemeral(true).queue();
                String[] a = {"channels","participantes_evento"};
                event.getGuild()
                        .getTextChannelById(
                                configData("configData.json", a )
                        )
                        .sendMessageEmbeds(e)
                        .setActionRow(
                                Button.success("entrarEvento", "Entrar"),
                                Button.danger("sairEvento", "Sair"),
                                Button.secondary("finalizarLista", "Finalizar"))
                        .queue();
            }
        }
    }
}
