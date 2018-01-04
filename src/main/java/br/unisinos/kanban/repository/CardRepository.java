package br.unisinos.kanban.repository;

import br.unisinos.kanban.model.Card;
import br.unisinos.kanban.model.CardState;
import br.unisinos.kanban.model.Card_;
import br.unisinos.kanban.model.User;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Alex Carvalho
 */
public class CardRepository extends BaseRepository<Card> {

    public List<Card> findCards(User user, CardState state) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Card> cq = cb.createQuery(Card.class);

        Root<Card> root = cq.from(Card.class);

        cq.where(cb.and(
                cb.equal(root.get(Card_.user), user),
                cb.equal(root.get(Card_.state), state)

        ));

        cq.orderBy(cb.asc(root.get(Card_.position)));

        TypedQuery<Card> query = getEntityManager().createQuery(cq);

        return query.getResultList();
    }

    public Card finById(Integer id) {
        return getEntityManager().find(Card.class, id);
    }

    public Integer findNextPosition(User user, CardState state) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);

        Root<Card> root = cq.from(Card.class);

        cq.where(cb.and(
                cb.equal(root.get(Card_.user), user),
                cb.equal(root.get(Card_.state), state)
        ));

        cq.select(cb.max(root.get(Card_.position)));

        Integer position = getEntityManager().createQuery(cq).getSingleResult();

        return position != null ? position + 1 : 0;
    }
}
