package io.github.lubosgarancovsky.core.listing;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

import java.util.Set;

public class ListFilteringOperators {

    public static final ComparisonOperator LIKE = new ComparisonOperator("=like=", true);

    public static Set<ComparisonOperator> all() {
        Set<ComparisonOperator> operators = RSQLOperators.defaultOperators();
        operators.add(ListFilteringOperators.LIKE);
        return operators;
    }
}
