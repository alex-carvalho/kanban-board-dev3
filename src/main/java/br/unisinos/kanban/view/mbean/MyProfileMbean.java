package br.unisinos.kanban.view.mbean;

import br.unisinos.kanban.SessionContext;
import br.unisinos.kanban.model.User;
import br.unisinos.kanban.service.UserService;
import br.unisinos.kanban.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * @author Alex Carvalho
 */
@ManagedBean
@ViewScoped
public class MyProfileMbean {

    @Inject
    private UserService userService;
    @Inject
    private SessionContext sessionContext;
    private User user;

    @PostConstruct
    public void init() {
        user = sessionContext.getActiveUser();
    }

    public void updateProfile() {
        userService.update(user);

        FacesUtils.addMessageSaveSuccess();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
