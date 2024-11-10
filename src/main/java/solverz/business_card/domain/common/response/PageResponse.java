package solverz.business_card.domain.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "페이지 응답")
public record PageResponse<T>(
        @Schema(description = "페이지 내 아이템 개수")
        int totalItems,

        @Schema(description = "페이지에 담긴 아이템들")
        List<T> contents
) {
        public static <T> PageResponse<T> of(List<T> contents) {
            return PageResponse.<T>builder()
                    .totalItems(contents.size())
                    .contents(contents)
                    .build();
        }
}
