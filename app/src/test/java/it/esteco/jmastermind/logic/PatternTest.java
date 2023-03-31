package it.esteco.jmastermind.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.random.RandomGenerator;

import static it.esteco.jmastermind.logic.PegColor.BLUE;
import static it.esteco.jmastermind.logic.PegColor.GREEN;
import static it.esteco.jmastermind.logic.PegColor.RED;
import static it.esteco.jmastermind.logic.PegColor.WHITE;
import static it.esteco.jmastermind.logic.PegColor.YELLOW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatternTest {

    @Nested
    class TestMatch {
        @Test
        void testCheckWhenNoneMatch() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(0, 0), secret.match(guess));
        }

        @Test
        void testCheckFirstCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(RED, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckFirstCorrectWhenWhite() {
            Pattern secret = new Pattern(WHITE, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckSecondCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, GREEN, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckSecondCorrectWhenWhite() {
            Pattern secret = new Pattern(RED, WHITE, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckThirdCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, BLUE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckThirdCorrectWhenWhite() {
            Pattern secret = new Pattern(RED, GREEN, WHITE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckFourthCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, YELLOW);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckFourthCorrectWhenWhite() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, WHITE);
            Pattern guess = new Pattern(WHITE, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testCheckFirstTwoCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(RED, GREEN, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(2, 0), secret.match(guess));
        }

        @Test
        void testCheckSecondTwoCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, GREEN, BLUE, WHITE);

            Assertions.assertEquals(new Feedback(2, 0), secret.match(guess));
        }

        @Test
        void testCheckLastTwoCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, BLUE, YELLOW);

            Assertions.assertEquals(new Feedback(2, 0), secret.match(guess));
        }

        @Test
        void testCheckFirstThreeCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(RED, GREEN, BLUE, WHITE);

            Assertions.assertEquals(new Feedback(3, 0), secret.match(guess));
        }

        @Test
        void testCheckLastThreeCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, GREEN, BLUE, YELLOW);

            Assertions.assertEquals(new Feedback(3, 0), secret.match(guess));
        }

        @Test
        void testCheckAllCorrect() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(RED, GREEN, BLUE, YELLOW);

            Assertions.assertEquals(new Feedback(4, 0), secret.match(guess));
        }

        @Test
        void testMatchOneYellowIncorrectInFirstPosition() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(YELLOW, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(0, 1), secret.match(guess));
        }

        @Test
        void testMatchOneYellowIncorrectInSecondPosition() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, YELLOW, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(0, 1), secret.match(guess));
        }

        @Test
        void testMatchOneYellowIncorrectInThirdPosition() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, WHITE, YELLOW, WHITE);

            Assertions.assertEquals(new Feedback(0, 1), secret.match(guess));
        }

        @Test
        void testMatchOneYellowCorrectInFirstPosition() {
            Pattern secret = new Pattern(YELLOW, GREEN, BLUE, RED);
            Pattern guess = new Pattern(YELLOW, WHITE, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testMatchOneYellowCorrectAndAnotherYellow() {
            Pattern secret = new Pattern(YELLOW, GREEN, BLUE, RED);
            Pattern guess = new Pattern(YELLOW, YELLOW, WHITE, WHITE);

            Assertions.assertEquals(new Feedback(1, 0), secret.match(guess));
        }

        @Test
        void testMatchTwoYellowIncorrectInSecondThirdPosition() {
            Pattern secret = new Pattern(YELLOW, GREEN, BLUE, YELLOW);
            Pattern guess = new Pattern(WHITE, YELLOW, YELLOW, WHITE);

            Assertions.assertEquals(new Feedback(0, 2), secret.match(guess));
        }

        @Test
        void testMatchTwoRedIncorrectInSecondThirdPosition() {
            Pattern secret = new Pattern(RED, GREEN, BLUE, RED);
            Pattern guess = new Pattern(WHITE, RED, RED, WHITE);

            Assertions.assertEquals(new Feedback(0, 2), secret.match(guess));
        }
    }

    @Nested
    class TestEqualsHashCode {
        @Test
        void testEqualsWithSameObject() {
            Pattern pattern = new Pattern(RED, BLUE, YELLOW, WHITE);
            assertEquals(pattern, pattern);
        }

        @Test
        void testEqualsWithEqualPatterns() {
            Pattern pattern1 = new Pattern(RED, BLUE, YELLOW, WHITE);
            Pattern pattern2 = new Pattern(RED, BLUE, YELLOW, WHITE);
            assertEquals(pattern1, pattern2);
            assertEquals(pattern2, pattern1);
        }

        @Test
        void testEqualsWithDifferentPatterns() {
            Pattern pattern1 = new Pattern(RED, BLUE, YELLOW, WHITE);
            Pattern pattern2 = new Pattern(BLUE, YELLOW, RED, WHITE);
            assertNotEquals(pattern1, pattern2);
            assertNotEquals(pattern2, pattern1);
        }

        @Test
        void testEqualsWithNullObject() {
            Pattern pattern = new Pattern(RED, BLUE, YELLOW, WHITE);
            assertNotEquals(null, pattern);
        }

        @Test
        void testEqualsWithDifferentClass() {
            Pattern pattern = new Pattern(RED, BLUE, YELLOW, WHITE);
            assertNotEquals("not a pattern object", pattern);
        }

        @Test
        void testEqualsWithDifferentPegColors() {
            Pattern pattern1 = new Pattern(RED, BLUE, YELLOW, WHITE);
            Pattern pattern2 = new Pattern(RED, BLUE, GREEN, WHITE);
            assertNotEquals(pattern1, pattern2);
            assertNotEquals(pattern2, pattern1);
        }

        @Test
        void testHashCodeWithEqualPatterns() {
            Pattern pattern1 = new Pattern(RED, BLUE, YELLOW, WHITE);
            Pattern pattern2 = new Pattern(RED, BLUE, YELLOW, WHITE);
            assertEquals(pattern1.hashCode(), pattern2.hashCode());
        }

    }

    @Nested
    class TestCreateRandomPattern {
        @Test
        void testConstantPattern() {
            Pattern randomPattern = Pattern.createRandomPattern(() -> 0);
            Assertions.assertEquals(new Pattern(PegColor.values()[0], PegColor.values()[0], PegColor.values()[0], PegColor.values()[0]), randomPattern);
        }

        @Test
        void textPseudoRandomPattern() {
            Iterator<Integer> intStream = List.of(0, 1, 2, 3).iterator();
            Pattern randomPattern = Pattern.createRandomPattern(new RandomGenerator() {
                @Override
                public long nextLong() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public int nextInt(int bound) {
                    return intStream.next();
                }
            });
            Assertions.assertEquals(new Pattern(PegColor.values()[0], PegColor.values()[1], PegColor.values()[2], PegColor.values()[3]), randomPattern);
        }
    }
}

