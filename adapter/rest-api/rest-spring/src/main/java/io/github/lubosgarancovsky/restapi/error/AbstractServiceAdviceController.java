package io.github.lubosgarancovsky.restapi.error;

import com.fasterxml.jackson.core.JsonParseException;
import io.github.lubosgarancovsky.domain.error.BusinessError;
import io.github.lubosgarancovsky.domain.error.BusinessException;
import io.github.lubosgarancovsky.restapi.dto.ErrorDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.lubosgarancovsky.domain.error.ServiceErrorCode.UNKNOWN_SERVICE_ERROR;
import static io.github.lubosgarancovsky.restapi.error.BaseApiErrorCode.BAD_JSON_FORMAT;

public abstract class AbstractServiceAdviceController {

    private static final Logger log = LoggerFactory.getLogger(AbstractServiceAdviceController.class);

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorDetailResponse> handle(JsonParseException e) {
        log.error("Handle {}", e.getClass().getName(), e);
        return mapErrorToResponse(BAD_JSON_FORMAT.createError());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDetailResponse> handle(BusinessException e) {
        log.error("Handle {}", e.getClass().getName(), e);
        return mapErrorToResponse(e.getError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailResponse> handle(Exception e) {
        log.error("Handle {}", e.getClass().getName(), e);
        return mapErrorToResponse(UNKNOWN_SERVICE_ERROR.createError());
    }

    public abstract ResponseEntity<ErrorDetailResponse> mapErrorToResponse(
            BusinessError businessError);
}
