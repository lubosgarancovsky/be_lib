package io.github.lubosgarancovsky.core.port;

import io.github.lubosgarancovsky.core.marker.Command;

public interface UseCaseCommand<C extends Command, O> {

    O execute(C command);
}

