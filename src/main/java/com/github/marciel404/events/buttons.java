package com.github.marciel404.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

import static com.github.marciel404.functions.text.escritor;
import static com.github.marciel404.functions.text.leitor;

public class buttons extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (!event.isFromGuild()) return;

        switch (event.getComponentId()) {

            case "entrarEvento" -> {

                var e = event.getMessage().getEmbeds().get(0);

                String descriptionEmbed = "";
                if (e.getDescription() != null)
                    descriptionEmbed = e.getDescription();

                if (descriptionEmbed.contains(event.getMember().getAsMention())){
                    event.reply("Você já está na lista").setEphemeral(true).queue();
                }else{
                    String[] v = {
                            "listaevento"
                    };
                    try {
                        escritor(
                                "listaevento",
                                descriptionEmbed + "\n" +event.getMember().getAsMention(),
                                v
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    MessageEmbed e2;
                    try {
                        e2 = new EmbedBuilder()
                                .setTitle("Lista participantes")
                                .setDescription(leitor("listaevento", v))
                                .build();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    event.getMessage().editMessageEmbeds(e2).queue();

                    event.reply("Adicionado a lista").setEphemeral(true).queue();

                }
            }
            case "sairEvento" -> {

                var e = event.getMessage().getEmbeds().get(0);

                if (e.getDescription() == null){
                    event.reply("Não tem ninguem na lista").setEphemeral(true).queue();
                    return;
                }

                if (e.getDescription().contains(event.getMember().getAsMention())){
                    String[] v = {
                            "listaevento"
                    };
                    try {
                        escritor(
                                "listaevento",
                                e.getDescription().replace(event.getMember().getAsMention(),""),
                                v
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    MessageEmbed e2;
                    try {
                        e2 = new EmbedBuilder()
                                .setTitle("Lista participantes")
                                .setDescription(leitor("listaevento", v))
                                .build();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    event.getMessage().editMessageEmbeds(e2).queue();

                    event.reply("Removido da lista").setEphemeral(true).queue();

                }else{
                    event.reply("Você não está na lista").setEphemeral(true).queue();
                }

            }
            case "finalizarLista" -> {
                event.reply("Lista Finalizada").setEphemeral(true).queue();
                event.getMessage().editMessageComponents().queue();
            }
        }
    }
}
