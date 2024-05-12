package io.github.lubosgarancovsky.core.port;


import io.github.lubosgarancovsky.core.marker.Command;

public interface UseCaseVoidCommand<C extends Command> {

    void execute(C command);
}

