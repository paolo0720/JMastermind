package it.esteco.jmastermind.logic;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public record Pattern(PegColor... pegs) {

    public Pattern(PegColor... pegs) {
        this.pegs = Arrays.copyOf(pegs, pegs.length);
    }

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
        Pattern pattern = (Pattern) o;
        return Arrays.equals(pegs, pattern.pegs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pegs);
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "pegs=" + Arrays.toString(pegs) +
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

        for (int i = 0; i < pegs.length; i++) {
            if (pegs[i] == guess.pegs[i]) {
                correct++;
            } else {
                secretPegs.get(pegs[i]).incrementAndGet();
                guessPegs.get(guess.pegs[i]).incrementAndGet();
            }
        }
        int wrong = 0;
        for (PegColor pegColor : PegColor.values()) {
            wrong += Math.min(secretPegs.get(pegColor).intValue(), guessPegs.get(pegColor).intValue());
        }
        return new Feedback(correct, wrong);
    }
}
