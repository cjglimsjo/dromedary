package com.cjglimsjo.dromedary;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cjglimsjo.dromedary.validation.Assert.notNull;

public class Route<T> {

    // TODO: Temp for choices
    private ChoiceRoute<T> parentChoiceRoute;

    private List<Exchange<T>> exchanges;

    public Route(Collection<T> messages) {
        notNull(messages, "messages must not be null");

        this.exchanges = messages.stream()
                .filter(Objects::nonNull)
                .map(Exchange::new)
                .collect(Collectors.toList());
    }

    // TODO: Temp for choices
    public Route(List<Exchange<T>> exchanges, ChoiceRoute<T> parentChoiceRoute) {
        notNull(exchanges, "exchanges must not be null");

        this.exchanges = exchanges;
        this.parentChoiceRoute = parentChoiceRoute;
    }

    public Route<T> process(Processor<T> processor) {
        exchanges.stream()
                .filter(Exchange::isValid)
                .forEach(exchange -> process(exchange, processor));

        return this;
    }

    public Route<T> processAll(Processor<T> processor) {
        exchanges.forEach(exchange -> process(exchange, processor));

        return this;
    }

    private void process(Exchange<T> exchange, Processor<T> processor) {
        try {
            processor.process(exchange);
        } catch (Exception e) {
            if (exchange.isValid()) {
                exchange.invalidate(e);
            }
        }
    }

    public ChoiceRoute<T> startIf() {
        return new ChoiceRoute<>(this);
    }

    public ChoiceRoute<T> end() {
        return parentChoiceRoute;
    }

    public Collection<T> returnValid() {
        return exchanges.stream()
                .filter(Exchange::isValid)
                .map(Exchange::getMessage)
                .collect(Collectors.toList());
    }

//    public Collection<T> returnAll() {
//        return exchanges.stream()
//                .map(Exchange::getMessage)
//                .collect(Collectors.toList());
//    }

    public Collection<Exchange<T>> getValidExchanges() {
        return exchanges.stream()
                .filter(Exchange::isValid)
                .collect(Collectors.toList());
    }

    public List<Exchange<T>> getExchanges() {
        return exchanges;
    }
}
