package it.esteco.jmastermind.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FeedbackTest {

    @Test
    void testCorrectGuess() {
        Feedback feedback = new Feedback(4, 0);
        Assertions.assertTrue(feedback.correctGuess());
    }

    @Test
    void testInCorrectGuess() {
        Feedback feedback = new Feedback(0, 0);
        Assertions.assertFalse(feedback.correctGuess());
    }

    @Test
    void testAnotherCorrectGuess() {
        Feedback feedback = new Feedback(3, 0);
        Assertions.assertFalse(feedback.correctGuess());
    }
}