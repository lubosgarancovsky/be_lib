package io.github.lubosgarancovsky.port;

import io.github.lubosgarancovsky.domain.marker.Command;

public interface UseCaseVoidCommand<C extends Command> {

    void execute(C command);
}
