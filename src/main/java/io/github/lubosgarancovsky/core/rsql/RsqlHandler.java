package io.github.lubosgarancovsky.core.rsql;


import cz.jirutka.rsql.parser.ast.Node;

public interface RsqlHandler {

    Node parse(String query);
}

