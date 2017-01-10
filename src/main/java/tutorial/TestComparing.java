package tutorial;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by geekslife on 2017. 1. 10..
 */
public class TestComparing {
    public interface Card extends Comparable<Card> {

        public enum Suit {
            DIAMONDS (1, "Diamonds"),
            CLUBS    (2, "Clubs"   ),
            HEARTS   (3, "Hearts"  ),
            SPADES   (4, "Spades"  );

            private final int value;
            private final String text;
            Suit(int value, String text) {
                this.value = value;
                this.text = text;
            }
            public int value() {return value;}
            public String text() {return text;}
        }

        public enum Rank {
            DEUCE  (2 , "Two"  ),
            THREE  (3 , "Three"),
            FOUR   (4 , "Four" ),
            FIVE   (5 , "Five" ),
            SIX    (6 , "Six"  ),
            SEVEN  (7 , "Seven"),
            EIGHT  (8 , "Eight"),
            NINE   (9 , "Nine" ),
            TEN    (10, "Ten"  ),
            JACK   (11, "Jack" ),
            QUEEN  (12, "Queen"),
            KING   (13, "King" ),
            ACE    (14, "Ace"  );
            private final int value;
            private final String text;
            Rank(int value, String text) {
                this.value = value;
                this.text = text;
            }
            public int value() {return value;}
            public String text() {return text;}
        }

        public Card.Suit getSuit();
        public Card.Rank getRank();
    }

    public interface Deck {

        List<Card> getCards();
        Deck deckFactory();
        int size();
        void addCard(Card card);
        void addCards(List<Card> cards);
        void addDeck(Deck deck);
        void shuffle();
        void sort();
        void sort(Comparator<Card> c);
        String deckToString();

        Map<Integer, Deck> deal(int players, int numberOfCards)
                throws IllegalArgumentException;

    }

    static class PlayingCard implements Card {

        private Card.Rank rank;
        private Card.Suit suit;

        public PlayingCard(Card.Rank rank, Card.Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public Card.Suit getSuit() {
            return suit;
        }

        public Card.Rank getRank() {
            return rank;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Card) {
                if (((Card)obj).getRank() == this.rank &&
                        ((Card)obj).getSuit() == this.suit) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }


        public int hashCode() {
            return ((suit.value()-1)*13)+rank.value();
        }

        public int compareTo(Card o) {
            return this.hashCode() - o.hashCode();
        }

        public String toString() {
            return this.rank.text() + " of " + this.suit.text();
        }
    }

    public static void main(String[] args) {
        Card card1 = new PlayingCard(Card.Rank.ACE, Card.Suit.DIAMONDS);
        Card card2 = new PlayingCard(Card.Rank.KING, Card.Suit.SPADES);
        System.err.println("playing card.."+card1.getRank().compareTo(card2.getRank()));
    }
}
