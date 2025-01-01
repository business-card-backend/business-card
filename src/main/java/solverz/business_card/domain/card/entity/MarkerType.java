package solverz.business_card.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import solverz.business_card.domain.common.annotation.CheckEnumValid;
import solverz.business_card.domain.common.execption.BusinessException;
import solverz.business_card.domain.common.execption.ErrorCode;

@Getter
@CheckEnumValid(enumClass = MarkerType.class)
public enum MarkerType {
    // 수트 캐릭터 마커
    MARKER_CHARACTER1("MARKER_CHARACTER1"),
    MARKER_CHARACTER2("MARKER_CHARACTER2"),
    MARKER_CHARACTER3("MARKER_CHARACTER3"),
    MARKER_CHARACTER4("MARKER_CHARACTER4"),
    MARKER_CHARACTER5("MARKER_CHARACTER5"),
    MARKER_CHARACTER6("MARKER_CHARACTER6"),
    MARKER_CHARACTER7("MARKER_CHARACTER7"),
    MARKER_CHARACTER8("MARKER_CHARACTER8"),
    MARKER_CHARACTER9("MARKER_CHARACTER9"),
    MARKER_CHARACTER10("MARKER_CHARACTER10"),
    MARKER_CHARACTER11("MARKER_CHARACTER11"),
    MARKER_CHARACTER12("MARKER_CHARACTER12"),
    MARKER_CHARACTER13("MARKER_CHARACTER13"),
    MARKER_CHARACTER14("MARKER_CHARACTER14"),
    MARKER_CHARACTER15("MARKER_CHARACTER15"),
    MARKER_CHARACTER16("MARKER_CHARACTER16"),

    // 날씨 마커
    MARKER_WEATHER1("MARKER_WEATHER1"),
    MARKER_WEATHER2("MARKER_WEATHER2"),
    MARKER_WEATHER3("MARKER_WEATHER3"),
    MARKER_WEATHER4("MARKER_WEATHER4"),
    MARKER_WEATHER5("MARKER_WEATHER5"),
    MARKER_WEATHER6("MARKER_WEATHER6"),
    MARKER_WEATHER7("MARKER_WEATHER7"),

    // 기호 마커
    MARKER_SIGN1("MARKER_SIGN1"),
    MARKER_SIGN2("MARKER_SIGN2"),
    MARKER_SIGN3("MARKER_SIGN3"),
    MARKER_SIGN4("MARKER_SIGN4"),
    MARKER_SIGN5("MARKER_SIGN5"),
    MARKER_SIGN6("MARKER_SIGN6"),
    MARKER_SIGN7("MARKER_SIGN7"),

    // 하트 모양 마커
    MARKER_HEART1("MARKER_HEART1"),
    MARKER_HEART2("MARKER_HEART2"),
    MARKER_HEART3("MARKER_HEART3"),

    // 별 모양 마커
    MARKER_STAR1("MARKER_STAR1"),
    MARKER_STAR2("MARKER_STAR2"),
    MARKER_STAR3("MARKER_STAR3"),

    // 기타 마커
    MARKER_ETC1("MARKER_ETC1"),
    MARKER_ETC2("MARKER_ETC2"),
    MARKER_ETC3("MARKER_ETC3"),
    MARKER_ETC4("MARKER_ETC4"),
    MARKER_ETC5("MARKER_ETC5");

    private final String value;

    MarkerType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MarkerType fromString(String value) {
        for (MarkerType markerType : MarkerType.values()) {
            if (markerType.getValue().equalsIgnoreCase(value)) {
                return markerType;
            }
        }
        throw new BusinessException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
