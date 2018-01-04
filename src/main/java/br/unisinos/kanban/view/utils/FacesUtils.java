package br.unisinos.kanban.view.utils;

import org.omnifaces.util.Ajax;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Alex Carvalho
 */
public class FacesUtils {

    public static void addMessageSaveSuccess() {
        addInfoMessage("Salva com sucesso!");
    }

    public static void addInfoMessage(String message) {
        FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("", infoMessage);
    }

    public static void reloadPage() {
        Ajax.oncomplete("location.reload()");
    }
}
