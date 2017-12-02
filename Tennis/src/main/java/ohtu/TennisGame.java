package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    
    private final String player1Name;
    private final String player2Name;
    private final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};
    private final String deuce = "Deuce";
    private final String even = "All";
    private final String advantageFor = "Advantage ";
    private final String winFor = "Win for ";
    private final String scoreSeparator = "-";

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else if (playerName.equals(player2Name)) {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score;
        if (player1Score >= 4 || player2Score >= 4) {
            score = checkAdvantage();
        } else {
            score = numberScore();
        }
        return score;
    }

    private String numberScore() {
        String score = scores[player1Score];
        score += scoreSeparator;
        if (player1Score == player2Score) {
            score += even;
        } else {
            score += scores[player2Score];
        }
        return score;
    }

    private String advantageScore() {
        if (player1Score > player2Score) {
            return advantageFor + player1Name;
        } else if (player1Score == player2Score) {
            return deuce;
        } else {
            return advantageFor + player2Name;
        }
    }

    private String winner() {
        if (player1Score > player2Score) {
            return winFor + player1Name;
        } else {
            return winFor + player2Name;
        }
    }

    private String checkAdvantage() {
        if (Math.abs(player1Score - player2Score) > 1) {
            return winner();
        } else {
            return advantageScore();
        }
    }
}
