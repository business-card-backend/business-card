package solverz.business_card.global.logging;

public record DiscordMessage (
    String content
){
    public static DiscordMessage createDiscordMessage(String content) {
        return new DiscordMessage(content);
    }
}