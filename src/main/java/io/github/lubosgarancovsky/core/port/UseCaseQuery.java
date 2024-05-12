package io.github.lubosgarancovsky.core.port;

import io.github.lubosgarancovsky.core.marker.Query;

public interface UseCaseQuery<Q extends Query, O> {

    O execute(Q query);
}
