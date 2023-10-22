
 import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    String name;
    int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
}

class Game {
    Player player1;
    Player player2;
    Player winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;
    }

    public void playGame() {
        // Simulate the game logic here and determine the winner.
        // You can add your game logic in this method.
        // For simplicity, we'll just set a random winner.
        if (Math.random() < 0.5) {
            winner = player1;
        } else {
            winner = player2;
        }
    }
}

class Tournament {
    List<Player> participants;
    List<Game> games;
    Player winner;

    public Tournament(List<Player> participants) {
        this.participants = participants;
        this.games = new ArrayList<>();
        this.winner = null;
    }
}

public class GameManagementSystem {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        List<Tournament> tournaments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Player");
            System.out.println("2. Create Game");
            System.out.println("3. Create Tournament");
            System.out.println("4. Add Tournament Results");
            System.out.println("5. Exit");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter player name: ");
                String playerName = scanner.nextLine();
                Player player = new Player(playerName);
                players.add(player);
            } else if (choice.equals("2")) {
                if (players.size() < 2) {
                    System.out.println("You need at least two players to create a game.");
                } else {
                    System.out.println("Select two players for the game:");
                    for (int i = 0; i < players.size(); i++) {
                        System.out.println((i + 1) + ". " + players.get(i).name);
                    }
                    int player1Index = Integer.parseInt(scanner.nextLine()) - 1;
                    int player2Index;
                    do {
                        System.out.print("Select the second player: ");
                        player2Index = Integer.parseInt(scanner.nextLine()) - 1;
                    } while (player2Index == player1Index);

                    Player player1 = players.get(player1Index);
                    Player player2 = players.get(player2Index);

                    Game game = new Game(player1, player2);
                    game.playGame();
                    System.out.println("Game result: " + game.player1.name + " vs. " + game.player2.name +
                            " - Winner: " + game.winner.name);
                }
            } else if (choice.equals("3")) {
                if (players.size() < 2) {
                    System.out.println("You need at least two players to create a tournament.");
                } else {
                    System.out.print("Enter the number of participants: ");
                    int numParticipants = Integer.parseInt(scanner.nextLine());
                    if (numParticipants >= 2 && numParticipants <= players.size()) {
                        List<Player> tournamentParticipants = new ArrayList<>();
                        for (int i = 0; i < numParticipants; i++) {
                            System.out.println("Select a participant:");
                            for (int j = 0; j < players.size(); j++) {
                                System.out.println((j + 1) + ". " + players.get(j).name);
                            }
                            int participantIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            tournamentParticipants.add(players.remove(participantIndex));
                        }
                        Tournament tournament = new Tournament(tournamentParticipants);
                        tournaments.add(tournament);
                        System.out.println("Tournament created with " + numParticipants + " participants.");
                    } else {
                        System.out.println("Invalid number of participants.");
                    }
                }
            } else if (choice.equals("4")) {
                if (tournaments.isEmpty()) {
                    System.out.println("No tournaments available to add results.");
                } else {
                    Tournament tournament = tournaments.get(tournaments.size() - 1);
                    if (tournament.winner != null) {
                        System.out.println("Tournament already has a winner.");
                    } else if (tournament.participants.size() == 1) {
                        tournament.winner = tournament.participants.get(0);
                        System.out.println("Tournament winner: " + tournament.winner.name);
                    } else {
                        System.out.println("The tournament is not complete yet.");
                    }
                }
            } else if (choice.equals("5")) {
                break;
            }
        }
    }
}
