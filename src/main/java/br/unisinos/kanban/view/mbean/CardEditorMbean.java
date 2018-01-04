package br.unisinos.kanban.view.mbean;

import br.unisinos.kanban.SessionContext;
import br.unisinos.kanban.model.Card;
import br.unisinos.kanban.model.CardState;
import br.unisinos.kanban.repository.CardRepository;
import br.unisinos.kanban.service.CardService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * @author Alex Carvalho
 */
@ManagedBean
@ViewScoped
public class CardEditorMbean {

    @Inject
    private SessionContext sessionContext;
    @Inject
    private CardRepository cardRepository;
    @Inject
    private CardService cardService;

    private Card cardEdit;

    private boolean filed;

    public void createNewCard() {
        cardEdit = new Card();
        cardEdit.setState(CardState.TO_DO);
        cardEdit.setUser(sessionContext.getActiveUser());
        cardEdit.setColor("#dedede");
    }

    public void editCard(Card card) {
        cardEdit = cardRepository.finById(card.getId());
    }

    public Card getCardEdit() {
        return cardEdit;
    }

    public void setCardEdit(Card cardEdit) {
        this.cardEdit = cardEdit;
    }

    public boolean isFiled() {
        return filed;
    }

    public void setFiled(boolean filed) {
        this.filed = filed;
    }
}
