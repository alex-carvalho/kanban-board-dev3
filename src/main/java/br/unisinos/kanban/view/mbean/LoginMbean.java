package br.unisinos.kanban.view.mbean;

import br.unisinos.kanban.SessionContext;
import br.unisinos.kanban.model.User;
import br.unisinos.kanban.service.UserService;
import br.unisinos.kanban.view.utils.FacesUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * @author Aléx Carvalho
 */
@ManagedBean
@ViewScoped
public class LoginMbean {

    @Inject
    private UserService userService;
    @Inject
    private SessionContext sessionContext;

    private User user = new User();

    public LoginMbean() {
    }

    public void login() {
        User auth = userService.authenticate(user.getEmail(), user.getPassword());

        sessionContext.setActiveUser(auth);

        FacesUtils.reloadPage();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
