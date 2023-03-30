package com.github.marciel404.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import static com.github.marciel404.functions.text.escritor;

public class Gerais extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {

        System.out.println("Eu entrei como " + event.getJDA().getSelfUser().getName());
        event.getJDA().setAutoReconnect(true);

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getName().contains("ticket-")) {

            String[] author = {
                    event.getAuthor().getId(),
                    event.getAuthor().getName(),
                    event.getMember().getEffectiveName(),
                    "ticket"
            };
            try {
                escritor("tickets/" +
                        event.getGuildChannel()
                                .asTextChannel()
                                .getParentCategory()
                                .getName()
                                .replace(
                                        "ticket ",
                                        "") + "/" +
                                event.getAuthor().getId() + ".txt",
                        event.getMessage().getContentDisplay(),
                        author
                        );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
