package br.unisinos.kanban.view.mbean;

import br.unisinos.kanban.SessionContext;
import br.unisinos.kanban.model.Card;
import br.unisinos.kanban.model.CardState;
import br.unisinos.kanban.model.User;
import br.unisinos.kanban.repository.CardRepository;
import br.unisinos.kanban.service.CardService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Alex Carvalho
 */
@ManagedBean
@ViewScoped
public class ArchivedMbean {

    @Inject
    private SessionContext sessionContext;
    @Inject
    private CardRepository cardRepository;
    @Inject
    private CardService cardService;

    private List<Card> cards;

    public void loadArchived() {
        User user = sessionContext.getActiveUser();

        cards = cardRepository.findCards(user, CardState.ARCHIVED);
    }

    public void restoreCard(Card card) {
        cardService.restoreCard(card);

        loadArchived();
    }

    public void removeCard(Card card) {
        cardService.remove(card);

        loadArchived();
    }

    public List<Card> getCards() {
        return cards;
    }
}
