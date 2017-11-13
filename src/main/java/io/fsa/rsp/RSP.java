package io.fsa.rsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RSP {

    enum Value {

        ROCK, SCISSORS, PAPER;

        static Value valueOf(final int value) {
            if (value < 0 || RSP.Value.values().length <= value) {
                throw new IllegalArgumentException();
            } else {
                return RSP.Value.values()[value];
            }
        }
    }

    public enum Result {

        PLAYER_1, PLAYER_2, DRAW;

        private static final Map<RSP.Value, RSP.Value> RULES = new HashMap<>();

        static {
            RULES.put(RSP.Value.ROCK, RSP.Value.SCISSORS);
            RULES.put(RSP.Value.SCISSORS, RSP.Value.PAPER);
            RULES.put(RSP.Value.PAPER, RSP.Value.ROCK);
        }

        static Result getResult(final Player player1, final Player player2) {
            if (player1.getValue() == player2.getValue()) {
                return DRAW;
            } else if (RULES.get(player1.getValue()) == player2.getValue()) {
                return PLAYER_1;
            } else {
                return PLAYER_2;
            }
        }
    }

    public interface Player {

        Value getValue();
    }

    public static final class Stats {

        private final RSP.Player player1;

        private final RSP.Player player2;

        Stats(final Player player1, final Player player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        public final RSP.Result getResult() {
            return RSP.Result.getResult(this.player1, this.player2);
        }
    }

    public static final class Game {

        private final List<RSP.Stats> stats = new ArrayList<>();

        public final int getNumberOfValues() {
            return RSP.Value.values().length;
        }

        public final int getRockValue() {
            return RSP.Value.ROCK.ordinal();
        }

        public final List<RSP.Stats> getStats() {
            return new ArrayList<>(stats);
        }

        public final RSP.Result run(final int value1, final int value2) {
            final RSP.Stats stats = new RSP.Stats(() -> RSP.Value.valueOf(value1), () -> RSP.Value.valueOf(value2));
            this.stats.add(stats);
            return stats.getResult();
        }
    }
}
