package io.github.lubosgarancovsky.restapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@JsonDeserialize(as = ImmutableErrorDetailResponseItem.class)
@JsonSerialize(as = ImmutableErrorDetailResponseItem.class)
@Value.Immutable
public interface ErrorDetailResponseItem {

    String serviceId();

    String faultCode();

    String faultMessage();

    List<String> faultMessageParams();
}
