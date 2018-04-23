package com.nbh.playingcard;

public class Card {

    Suite suite;

    String rank;

    public Card() {
    }

    public Card(Suite suite, String rank) {
        this.suite = suite;
        this.rank = rank;
    }

    /**
     * @return suite
     **/
    public Suite getSuite() {
        return suite;
    }

    /**
     * @return rank
     **/
    public String getRank() {
        return rank;
    }
}
