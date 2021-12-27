import fr.lernejo.navy_battle.logic.GameBoard;
import fr.lernejo.navy_battle.logic.IPlayer;
import fr.lernejo.navy_battle.logic.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private GameBoard _board;

    @Test
    void CreateBord() {
        _board = new GameBoard();
        Assertions.assertNotNull(_board);
    }

    @Test
    void TestPlayer() {
        _board = new GameBoard();
        IPlayer player = _board.getPlayer1();
        player.hit('A',2);
        Assertions.assertEquals(player.name(),"Player1");
        Assertions.assertEquals(player.toString(),"Player1");
        Assertions.assertTrue(player.hasShipLeft());
    }

    @Test
    void Show() {
        _board = new GameBoard();
        Player player1 = (Player) _board.getPlayer1();
        Player player2 = (Player) _board.getPlayer1();
        char[][] sea = new char[20][20];
        player1.visualize(sea);
        player2.visualize(sea);

    }
}
