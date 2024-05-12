package io.github.lubosgarancovsky.restapi.dto;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(stagedBuilder = true)
public interface ErrorDetailResponse {

    String faultCode();

    String faultMessage();

    List<String> faultMessageParams();
}
