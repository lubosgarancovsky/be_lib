package io.github.lubosgarancovsky.port;

import io.github.lubosgarancovsky.domain.marker.Query;

public interface UseCaseQuery<Q extends Query, O> {

    O execute(Q query);
}
