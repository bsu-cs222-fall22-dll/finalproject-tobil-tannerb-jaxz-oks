package edu.bsu.cs222;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

public class DiscordBot {

    static final String token = "MTAzNzE0MTgxODI3NTU0MTEzMw.GXWDIt.qXJn04f2J9VQnd9wbEXTDBm3ia5O6twJ_7ToJM";

    public static void main(String[] args){
        DiscordClient client = DiscordClient.create(token);

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->
                            Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                            }))
                    .then();

            // MessageCreateEvent example
            Mono<Void> handlePingCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();

                if (message.getContent().equalsIgnoreCase("!ping")) {
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage("pong!"));
                }

                return Mono.empty();
            }).then();

            // combine them!
            return printOnLogin.and(handlePingCommand);
        });

        login.block();
    }
}
