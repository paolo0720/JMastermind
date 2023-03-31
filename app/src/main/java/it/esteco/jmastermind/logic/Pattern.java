package it.esteco.jmastermind.logic;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public record Pattern(PegColor peg1, PegColor peg2, PegColor peg3, PegColor peg4) {

    public static Pattern createRandomPattern(RandomGenerator randomGenerator) {
        PegColor[] values = PegColor.values();
        int bound = values.length;
        int i = randomGenerator.nextInt(bound);
        int j = randomGenerator.nextInt(bound);
        int k = randomGenerator.nextInt(bound);
        int l = randomGenerator.nextInt(bound);
        return new Pattern(values[i], values[j], values[k], values[l]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pattern other = (Pattern) o;
        return peg1 == other.peg1 && peg2 == other.peg2 && peg3 == other.peg3 && peg4 == other.peg4;
    }

    @Override
    public int hashCode() {
        return Objects.hash(peg1, peg2, peg3, peg4);
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "peg1=" + peg1 +
                ", peg2=" + peg2 +
                ", peg3=" + peg3 +
                ", peg4=" + peg4 +
                '}';
    }

    public Feedback match(Pattern guess) {
        int correct = 0;
        Map<PegColor, AtomicInteger> secretPegs = new EnumMap<>(PegColor.class);
        Map<PegColor, AtomicInteger> guessPegs = new EnumMap<>(PegColor.class);
        for (PegColor pegColor : PegColor.values()) {
            secretPegs.put(pegColor, new AtomicInteger());
            guessPegs.put(pegColor, new AtomicInteger());
        }
        PegColor[] pegs = new PegColor[]{peg1, peg2, peg3, peg4};
        PegColor[] other = new PegColor[]{guess.peg1, guess.peg2, guess.peg3, guess.peg4};

        for (int i = 0; i < pegs.length; i++) {
            if (pegs[i] == other[i]) {
                correct++;
            } else {
                secretPegs.get(pegs[i]).incrementAndGet();
                guessPegs.get(other[i]).incrementAndGet();
            }
        }
        int wrong = 0;
        for (PegColor pegColor : PegColor.values()) {
            wrong += Math.min(secretPegs.get(pegColor).intValue(), guessPegs.get(pegColor).intValue());
        }
        return new Feedback(correct, wrong);
    }
}
