import io.fsa.rsp.RSP;

public class ConsoleApp {

    public static void main(final String[] args) {

        final java.io.Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        final RSP.Game game = new RSP.Game();
        if (console.readLine("Please choose the mode (a for automatic): ").equals("a")) {
            new ConsoleApp.AutomaticGameLoop() {
            }.run(console, game);
        } else {
            new ConsoleApp.GameLoop() {
            }.run(console, game);
        }

        System.exit(0);
    }

    private interface GameLoop {

        default void exit(final java.io.Console console, final RSP.Game game) {
            console.printf("%s", java.util.Arrays.toString(
                    game.getResults().stream().map(stats -> String.valueOf(stats.getResult()))
                            .collect(java.util.stream.Collectors.toList()).toArray()));
        }

        default void play(final java.io.Console console, final RSP.Game game) {
            do {
                final int value1 = readValue(console, "P1 enter value: ", game.getNumberOfValues());
                final int value2 = readValue(console, "P2 enter value: ", game.getNumberOfValues());
                console.printf("%s\n", String.valueOf(game.run(value1, value2)));
            } while ("y".equals(console.readLine("New game (y): ")));
        }

        default int readValue(final java.io.Console console, final String message, final int maxValue) {
            final int value;
            try {
                value = Integer.valueOf(console.readLine(String.format(message, maxValue)));
                if (value < 0 || value > maxValue) {
                    return readValue(console, "  Value must be (0 <= value <= %d): ", maxValue);
                }
            } catch (final NumberFormatException e) {
                return readValue(console, "  Value must be a number (0 <= value <= %d): ", maxValue);
            }
            return value;
        }

        default void run(final java.io.Console console, final RSP.Game game) {
            this.play(console, game);
            this.exit(console, game);
        }
    }

    private interface AutomaticGameLoop extends ConsoleApp.GameLoop {

        default void play(final java.io.Console console, final RSP.Game game) {
            int maxRounds = this.readValue(console, "Please enter the number of rounds: ", Integer.MAX_VALUE);
            final String player1Strategy = console
                    .readLine("Please enter the game strategy for player 1 (`r` for always rock): ");
            final String player2Strategy = console
                    .readLine("Please enter the game strategy for player 2 (`r` for always rock): ");
            final java.util.Random random = new java.util.Random();
            do {
                final int player1Value = player1Strategy.equals("r") ?
                        game.getStoneValue() :
                        random.nextInt(game.getNumberOfValues());
                final int player2Value = player2Strategy.equals("r") ?
                        game.getStoneValue() :
                        random.nextInt(game.getNumberOfValues());
                console.printf("%s\n", String.valueOf(game.run(player1Value, player2Value)));
                maxRounds--;
            } while (maxRounds > 0);
        }
    }
}
