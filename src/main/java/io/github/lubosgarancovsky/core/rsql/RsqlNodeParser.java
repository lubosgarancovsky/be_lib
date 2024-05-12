package io.github.lubosgarancovsky.core.rsql;

import cz.jirutka.rsql.parser.ast.Node;

public interface RsqlNodeParser<T> {

    T parse(Node node);
}
