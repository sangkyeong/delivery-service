package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.enums.UserStatus;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    /*가입처리로직
    * 1. request -> entity
    * 2. entity-> save
    * 3. save Entity -> reponse
    * 4. response return
    * */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register((entity));
        var response = userConverter.toResponse(newEntity);
        return response;

/*        return Optional.ofNullable((request))
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));*/
    }
}
