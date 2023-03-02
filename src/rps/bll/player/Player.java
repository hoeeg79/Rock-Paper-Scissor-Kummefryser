package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.*;
import java.util.stream.Collectors;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }

    @Override
    public Move randomMove(IGameState state) {
        Random random = new Random();
        int moveNo = random.nextInt(3);
        Move move = null;

        if (moveNo == 0) {
            move = Move.Rock;
        }
        if (moveNo == 1) {
            move = Move.Paper;
        }
        if (moveNo == 2) {
            move = Move.Scissor;
        }
        return move;
    }

    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        PlayerType player = PlayerType.Human;
        ArrayList<Move> playerMoves = new ArrayList<>();
        for (Result result : results) {
            PlayerType winner = result.getWinnerPlayer().getPlayerType();
            PlayerType loser = result.getLoserPlayer().getPlayerType();
            if (Objects.equals(winner, player)) {
                playerMoves.add(result.getWinnerMove());
            } else if (Objects.equals(loser, player)) {
                playerMoves.add(result.getLoserMove());
            }
        }

        List<Move> lastMoves = new ArrayList<>();
        if (playerMoves.size() >= 10) {
            lastMoves = playerMoves.subList(playerMoves.size() - 10, playerMoves.size());
        }
        int rock = Collections.frequency(playerMoves, Move.Rock);
        int scissor = Collections.frequency(playerMoves, Move.Scissor);
        int paper = Collections.frequency(playerMoves, Move.Paper);
        Move predictedMove;

        if (rock > scissor && rock > paper) {
            predictedMove = Move.Paper;
        } else if (scissor > rock && scissor > paper) {
            predictedMove = Move.Rock;
        } else if (paper > rock && paper > scissor){
            predictedMove = Move.Scissor;
        } else {
            predictedMove = randomMove(state);
        }

        return predictedMove;
    }
}
