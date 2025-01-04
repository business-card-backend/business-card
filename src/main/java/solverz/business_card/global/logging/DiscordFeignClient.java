package solverz.business_card.global.logging;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${logging.discord-api-call.name}", url = "${logging.discord-api-call.webhook-url}")
public interface DiscordFeignClient {
    @PostMapping
    void sendMessage(@RequestBody DiscordMessage discordMessage);
}