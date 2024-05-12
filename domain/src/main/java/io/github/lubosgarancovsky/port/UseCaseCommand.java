package io.github.lubosgarancovsky.port;

import io.github.lubosgarancovsky.domain.marker.Command;

public interface UseCaseCommand<C extends Command, O> {

    O execute(C command);
}
