package domain.user;

import domain.card.Deck;

import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
    private List<Player> players;

    public Gamers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void initPlayersCards(Deck deck) {
        players.forEach(x -> x.initCard(deck));
    }

    public String nameString() {
        return players.stream().map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void moreCard(Deck deck) {
        players.stream().filter(x -> !x.isDealer())
                .forEach(x -> x.moreCard(deck));
    }

    public Dealer findDealer() {
        return (Dealer) players.stream().filter(Player::isDealer)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public String toString() {
        return players.stream().map(Player::toString).collect(Collectors.joining("\n"));
    }
}
