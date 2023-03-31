package it.esteco.jmastermind.logic;

public record Feedback(int correct, int wrong) {

    public boolean correctGuess() {
        return correct == 4;
    }
}
