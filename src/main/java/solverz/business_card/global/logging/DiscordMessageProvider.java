package solverz.business_card.global.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static solverz.business_card.global.logging.DiscordMessage.createDiscordMessage;

@RequiredArgsConstructor
@Component
public class DiscordMessageProvider {
    private final DiscordFeignClient discordFeignClient;

    public void sendMessage(String message) {
        DiscordMessage discordMessage = createDiscordMessage(message);
        sendMessageToDiscord(discordMessage);
    }

    private void sendMessageToDiscord(DiscordMessage discordMessage) {
        discordFeignClient.sendMessage(discordMessage);
    }
}