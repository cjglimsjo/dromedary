package com.cjglimsjo.dromedary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceRoute<T> {

    private Route<T> parentRoute;

    private List<Exchange<T>> unmatchedExchanges;

    public ChoiceRoute(Route<T> parentRoute) {
        this.parentRoute = parentRoute;
        this.unmatchedExchanges = new ArrayList<>(parentRoute.getExchanges());
    }

    public Route<T> choice(Predicate<T> predicate) {
        List<Exchange<T>> matchingExchanges = unmatchedExchanges.stream()
                .filter(predicate::matches)
                .collect(Collectors.toList());

        unmatchedExchanges.removeAll(matchingExchanges);
        return new Route<>(matchingExchanges, this);
    }

    public Route<T> otherwise() {
        List<Exchange<T>> matchingExchanges = new ArrayList<>(unmatchedExchanges);

        unmatchedExchanges.removeAll(matchingExchanges);
        return new Route<>(matchingExchanges, this);
    }

    public Route<T> end() {
        return parentRoute;
    }
}
