package solverz.business_card.domain.card.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.request.PostCardRequest;
import solverz.business_card.domain.card.response.GetCardResponse;
import solverz.business_card.domain.card.response.PostCardResponse;
import solverz.business_card.domain.card.service.CardService;

@Tag(name = "Card", description = "명함 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardController {
    private final CardService cardService;

    @Operation(summary = "명함 상세보기 API", description = "특정 명함의 상세내용을 요청하는 API")
    @PostMapping("/detail")
    public ResponseEntity<GetCardResponse> getCard(GetCardRequest request) {
        GetCardResponse response = cardService.getCard(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "명함 등록 API", description = "명함 등록을 요청하는 API")
    @PostMapping
    public ResponseEntity<PostCardResponse> enrollCard(PostCardRequest request) {
        PostCardResponse response = cardService.enrollCard(request);
        return ResponseEntity.ok(response);
    }
}
