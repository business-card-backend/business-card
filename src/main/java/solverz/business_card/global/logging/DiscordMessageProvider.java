package solverz.business_card.global.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import java.util.Arrays;

import static solverz.business_card.global.logging.DiscordMessage.createDiscordMessage;

@RequiredArgsConstructor
@Component
public class DiscordMessageProvider {
    private final DiscordFeignClient discordFeignClient;
    private final Environment environment;

    public void sendMessage(String message) {
        DiscordMessage discordMessage = createDiscordMessage(message);
        sendMessageToDiscord(discordMessage);
    }

    private void sendMessageToDiscord(DiscordMessage discordMessage) {
        if (!Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            discordFeignClient.sendMessage(discordMessage);
        }
    }
}