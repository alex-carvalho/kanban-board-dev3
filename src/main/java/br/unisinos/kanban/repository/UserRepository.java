package br.unisinos.kanban.repository;

import br.unisinos.kanban.model.User;
import br.unisinos.kanban.model.User_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Carvalho
 */
public class UserRepository extends BaseRepository<User> {

    public User findByEmail(String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get(User_.email), email));

        TypedQuery<User> query = getEntityManager().createQuery(cq);

        query.setMaxResults(1);

        List<User> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
}
