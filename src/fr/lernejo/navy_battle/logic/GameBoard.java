package fr.lernejo.navy_battle.logic;

public final class GameBoard implements Viewable {
    public static final int ROW_NUMBER = 10;
    public static final int COL_NUMBER = 10;
    private final Player _player1;
    private final Player _player2;

    public GameBoard() {
        _player1 = new Player("Player1");
        _player2 = new Player("Player2");
        _player1.GenerateShips('A', 0, (char) ('A' + 5), 10);
        _player2.GenerateShips((char) ('A' + 5), 0, (char) ('A' + 10), 10);
    }

    public String toString() {
        char[][] sea = new char[ROW_NUMBER][COL_NUMBER];
        for (int i = 0; i < sea.length; i++) {
            for (int j = 0; j < sea[i].length; j++) {
                sea[i][j] = ' ';
            }
        }
        visualize(sea);
        String res = "";
        for (int i = 0; i < COL_NUMBER; i++) {
            res += " -----";
        }
        res += "\n";
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COL_NUMBER; j++) {
                res += "|  " + sea[i][j] + "  ";
            }
            res += "|\n";
        }
        for (int i = 0; i < COL_NUMBER; i++) {
            res += " -----";
        }
        res += "\n";
        return res;
    }

    @Override
    public void visualize( char[][] sea ) {
        _player1.visualize(sea);
        _player2.visualize(sea);
    }

    public IPlayer getPlayer1() {
        return _player1;
    }

    public IPlayer getPlayer2() {
        return _player2;
    }
}

interface Viewable {
    void visualize( char[][] sea );
}
