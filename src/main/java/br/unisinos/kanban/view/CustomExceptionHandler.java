package br.unisinos.kanban.view;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

/**
 * @author Alex Carvalho
 */
class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;

    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable temp = null;
            Throwable exception = context.getException();

            while (null != (temp = exception.getCause()) && (exception != temp)) {
                exception = temp;
            }

            final FacesContext fc = FacesContext.getCurrentInstance();

            try {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));

            } finally {
                i.remove();
            }
        }
        getWrapped().handle();
    }
}