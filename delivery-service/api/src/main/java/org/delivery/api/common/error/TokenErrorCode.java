package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements errorCodeIfs {

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),

    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),

    TOKEN_EXCEPTION(400, 2002, "토큰 알 수 없는 에러"),
    ;

    private final Integer httpStatusCode;

    private final Integer errorCode;

    private final String description;
}