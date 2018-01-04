package br.unisinos.kanban.service;

import br.unisinos.kanban.model.Card;
import br.unisinos.kanban.model.CardState;
import br.unisinos.kanban.repository.CardRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Alex Carvalho
 */
public class CardService {

    @Inject
    private CardRepository cardRepository;

    @Transactional
    public void remove(Card card) {
        cardRepository.remove(card);
    }

    @Transactional
    public void persist(Card card) {
        card.setPosition(cardRepository.findNextPosition(card.getUser(), card.getState()));
        cardRepository.persist(card);
    }

    @Transactional
    public Card update(Card card) {
        if (positionChange(card)) {
            fixPositions(card);
        }

        return cardRepository.update(card);
    }

    private boolean positionChange(Card card) {
        Card cardDb = cardRepository.finById(card.getId());

        return cardDb.getState() != card.getState() || !Objects.equals(cardDb.getPosition(), card.getPosition());
    }

    private void fixPositions(Card card) {
        List<Card> cards = cardRepository.findCards(card.getUser(), card.getState());

        cards.remove(card);
        cards.add(card.getPosition(), card);

        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            c.setPosition(i);

            cardRepository.update(c);
        }
    }

    @Transactional
    public void shelve(Card card) {
        card.setState(CardState.ARCHIVED);
        card.setPosition(-1);

        cardRepository.update(card);
    }

    @Transactional
    public void restoreCard(Card card) {
        card.setState(CardState.DONE);
        card.setPosition(cardRepository.findNextPosition(card.getUser(), card.getState()));

        cardRepository.update(card);
    }
}
