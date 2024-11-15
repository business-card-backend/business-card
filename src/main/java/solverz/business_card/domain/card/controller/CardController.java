package solverz.business_card.domain.card.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solverz.business_card.domain.card.request.DeleteCardRequest;
import solverz.business_card.domain.card.request.GetCardRequest;
import solverz.business_card.domain.card.request.PostCardRequest;
import solverz.business_card.domain.card.request.PutCardRequest;
import solverz.business_card.domain.card.response.*;
import solverz.business_card.domain.card.service.CardService;
import solverz.business_card.domain.common.response.PageResponse;

@Tag(name = "Card", description = "명함 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card")
public class CardController {
    private final CardService cardService;

    @Operation(summary = "명함 목록 API", description = "특정 멤버의 명함 목록을 요청하는 API")
    @Parameter(name = "page", description = "페이지 번호")
    @Parameter(name = "size", description = "페이지 크기")
    @GetMapping
    public ResponseEntity<PageResponse<GetCardSummaryResponse>> getCardList(
            @RequestParam String memberToken,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<GetCardSummaryResponse> response = cardService.getCardList(memberToken, pageable);
        return ResponseEntity.ok(response);
    }

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

    @Operation(summary = "명함 수정 API", description = "명함 수정을 요청하는 API")
    @PutMapping
    public ResponseEntity<PutCardResponse> modifyCard(PutCardRequest request) {
        PutCardResponse response = cardService.modifyCard(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "명함 삭제 API", description = "명함 삭제를 요청하는 API")
    @DeleteMapping
    public ResponseEntity<DeleteCardResponse> deleteCard(DeleteCardRequest request) {
        DeleteCardResponse response = cardService.deleteCard(request);
        return ResponseEntity.ok(response);
    }
}
