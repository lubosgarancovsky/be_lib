package io.github.lubosgarancovsky.restapi.mapper;

import io.github.lubosgarancovsky.domain.error.BusinessError;
import io.github.lubosgarancovsky.domain.error.NestedBusinessError;
import io.github.lubosgarancovsky.restapi.dto.ErrorDetailResponse;
import io.github.lubosgarancovsky.restapi.dto.ImmutableErrorDetailResponse;
import io.github.lubosgarancovsky.restapi.dto.ErrorDetailResponseItem;
import io.github.lubosgarancovsky.restapi.dto.listing.ImmutableErrorDetailResponseItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;

public class ErrorDetailResponseMapper {

    public static ResponseEntity<ErrorDetailResponse> mapErrorToResponse(
            BusinessError businessError, String serviceName, String correlationId) {
        List<ErrorDetailResponseItem> errorResponseItems =
                new LinkedList<>() {
                    {
                        add(
                                ImmutableErrorDetailResponseItem.builder()
                                        .serviceId(serviceName)
                                        .faultCode(businessError.code().toString())
                                        .faultMessage(businessError.code().template())
                                        .addAllFaultMessageParams(businessError.params())
                                        .build());
                        addAll(
                                businessError.nestedErrors().stream()
                                        .map(ErrorDetailResponseMapper::mapNestedErrorToResponseItem)
                                        .toList());
                    }
                };

        return ResponseEntity.status(businessError.code().type().getNumCode())
                .headers(headers -> headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .body(
                        ImmutableErrorDetailResponse.builder()
                                .correlationId(correlationId)
                                .addAllFault(errorResponseItems)
                                .build());
    }

    public static ErrorDetailResponseItem mapNestedErrorToResponseItem(
            NestedBusinessError nestedError) {
        return ImmutableErrorDetailResponseItem.builder()
                .serviceId(nestedError.serviceId())
                .faultCode(nestedError.faultCode())
                .faultMessage(nestedError.faultMessage())
                .addAllFaultMessageParams(nestedError.faultMessageParams())
                .build();
    }
}