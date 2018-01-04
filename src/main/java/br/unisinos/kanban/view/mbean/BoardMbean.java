package br.unisinos.kanban.view.mbean;

import br.unisinos.kanban.SessionContext;
import br.unisinos.kanban.model.Card;
import br.unisinos.kanban.model.CardState;
import br.unisinos.kanban.model.User;
import br.unisinos.kanban.repository.CardRepository;
import br.unisinos.kanban.service.CardService;
import br.unisinos.kanban.view.utils.FacesUtils;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alex Carvalho
 */
@ManagedBean
@ViewScoped
public class BoardMbean {

    @Inject
    private SessionContext sessionContext;
    @Inject
    private CardRepository cardRepository;
    @Inject
    private CardService cardService;

    private Map<CardState, List<Card>> cards = new HashMap<>();

    @PostConstruct
    public void init() {
        loadCards();
    }

    public void loadCards() {
        User user = sessionContext.getActiveUser();

        cards.clear();

        cards.put(CardState.TO_DO, cardRepository.findCards(user, CardState.TO_DO));
        cards.put(CardState.DOING, cardRepository.findCards(user, CardState.DOING));
        cards.put(CardState.DONE, cardRepository.findCards(user, CardState.DONE));
    }

    public void onDrop() {
        String id = Faces.getRequestParameter("id");
        String stage = Faces.getRequestParameter("stage");
        String index = Faces.getRequestParameter("index");

        Card card = cardRepository.finById(Integer.valueOf(id));

        card.setState(CardState.valueOf(stage));
        card.setPosition(Integer.valueOf(index));

        cardService.update(card);
    }

    public void saveCard(Card card, boolean filed) {
        if (card.getId() == null) {
            cardService.persist(card);
        } else if (filed) {
            cardService.shelve(card);
        } else {
            cardService.update(card);
        }

        loadCards();

        FacesUtils.addMessageSaveSuccess();
    }

    public void removeCard(Card card) {
        cardService.remove(card);

        loadCards();
    }

    public List<Card> getCardsToDo() {
        return cards.get(CardState.TO_DO);
    }

    public List<Card> getCardsDoing() {
        return cards.get(CardState.DOING);
    }

    public List<Card> getCardsDone() {
        return cards.get(CardState.DONE);
    }
}
