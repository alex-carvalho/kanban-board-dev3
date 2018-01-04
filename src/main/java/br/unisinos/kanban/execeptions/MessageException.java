package br.unisinos.kanban.execeptions;

/**
 * @author Alex Carvalho
 */
public class MessageException extends RuntimeException {

    public MessageException(String message) {
        super(message);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
