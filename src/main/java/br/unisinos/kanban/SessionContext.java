package br.unisinos.kanban;

import br.unisinos.kanban.model.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * @author Alex Carvalho
 */
@SessionScoped
public class SessionContext implements Serializable {

    private User activeUser;


    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
