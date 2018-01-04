package br.unisinos.kanban.service;

import br.unisinos.kanban.execeptions.MessageException;
import br.unisinos.kanban.model.User;
import br.unisinos.kanban.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author Alex Carvalho
 */
public class UserService {

    @Inject
    private UserRepository userRepository;


    @Transactional
    public void persist(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new MessageException("Email já cadastrado.");
        }

        userRepository.persist(user);
    }

    @Transactional
    public User update(User user) {
        return userRepository.update(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new MessageException("Usuário ou senha inválida!");
    }
}
