package io.github.lubosgarancovsky.domain.error;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class NestedBusinessError {


    @Value.Parameter
    public abstract String serviceId();


    @Value.Parameter
    public abstract String faultCode();


    @Value.Parameter
    public abstract String faultMessage();


    @Value.Parameter
    public abstract List<String> faultMessageParams();
}
