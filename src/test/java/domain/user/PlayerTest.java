package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    public void playerInitTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player).isNotNull();
    }

    @Test
    public void playerFieldTest() {
        Player player = new Player("이름1", 10000d);
        assertThat(player.getCards()).isNotNull();
    }

    @Test
    public void playerAddCardFromDeckTest() {
        Player player = new Player("이름1", 10000d);
        Deck deck = new Deck();
        player.addCard(deck.drawCard());
        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    public void playerGetScoreTest() {
        Player player = new Player("이름1", 10000d);
        Deck deck = new Deck();

        assertThat(player.getScore()).isEqualTo(0);
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        assertThat(player.getScore()).isGreaterThanOrEqualTo(2);
        assertThat(player.getScore()).isLessThanOrEqualTo(21);
    }

    @Test
    public void isDealerTest() {
        Player player = new Player("이름1", 10000d);
        Dealer dealer = new Dealer();

        assertThat(player.isDealer()).isFalse();
        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    public void isDealerUnderSixteenTest() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Symbol.TEN, Type.SPADE));
        dealer.addCard(new Card(Symbol.TEN, Type.HEART));
        assertThat(dealer.isDealerUnderSixteen()).isFalse();
    }

    @Test
    public void aceCardCountTest() {
        Player player = new Player("이름1", 10000d);

        assertThat(player.aceCardCount()).isEqualTo(0);
        player.addCard(new Card(Symbol.ACE, Type.HEART));
        player.addCard(new Card(Symbol.ACE, Type.SPADE));
        player.addCard(new Card(Symbol.ACE, Type.CLUB));
        assertThat(player.aceCardCount()).isEqualTo(3);
    }
}