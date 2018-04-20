package com.cjglimsjo.dromedary;

// TODO!
public interface ExceptionProcessor<T> {

    void process(T message, Exception exception) throws Exception;

//    public Route<T> processExceptions(ExceptionProcessor exceptionProcessor) {
//        exchanges.stream()
//                .filter(exchange -> !exchange.isValid())
//                .forEach(exchange -> processException(exchange, exceptionProcessor));
//
//        return this;
//    }
//
//    private Exchange processException(Exchange<T> exchange, ExceptionProcessor exceptionProcessor) {
//        try {
//            exceptionProcessor.process(exchange.getMessage(), exchange.getException());
//        } catch (Exception e) {
//            LOGGER.debug("Exception caught while processing message", e);
//            exchange.setException(e);
//        }
//
//        return exchange;
//    }
}
