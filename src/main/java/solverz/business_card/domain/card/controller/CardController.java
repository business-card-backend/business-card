package solverz.business_card.domain.card.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.response.GetCardResponse;
import solverz.business_card.domain.card.service.CardService;

@Tag(name = "Card", description = "명함 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardController {
    private final CardService cardService;

    @Operation(summary = "명함 상세보기 API", description = "특정 명함의 상세내용을 요청하는 API")
    @GetMapping
    public ResponseEntity<GetCardResponse> getCard(GetCardRequest request) {
        GetCardResponse response = cardService.getCard(request);
        return ResponseEntity.ok(response);
    }
}
