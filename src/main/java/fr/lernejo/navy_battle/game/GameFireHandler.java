package fr.lernejo.navy_battle.game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.logic.GameBoard;
import fr.lernejo.navy_battle.logic.IPlayer;
import fr.lernejo.navy_battle.logic.Shoot;
import fr.lernejo.navy_battle.utils.URIParser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public final class GameFireHandler implements HttpHandler {
    private final GameBoard _board;
    private final IPlayer _player1;
    private final IPlayer _player2;
    private IPlayer _currentPlayer;

    public GameFireHandler( GameBoard board ) {
        _board = board;
        _player1 = board.getPlayer1();
        _player2 = board.getPlayer2();
        _currentPlayer = _player1;
    }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        URIParser parser = new URIParser(exchange.getRequestURI());
        String cell = parser.GetValueOf("cell");
        String res = "";
        Shoot hit = _currentPlayer.hit(cell.charAt(0), Integer.parseInt(cell.charAt(1) + ""));
        switch (hit) {
            case Miss: {
                res = "miss";
                break;
            }
            case Hit: {
                res = "hit";
                break;
            }
            case Sunk: {
                res = "sunk";
                break;
            }
        }
        JSONObject json = new JSONObject();
        json.put("consequence", res);
        json.put("shipLeft", _player2.hasShipLeft());
        _currentPlayer = _currentPlayer.name().equals("Player1") ? _player2 : _player1;
        json.put("Current Player", _currentPlayer.name());


        String response = json.toString(4) + "\n" + _board.toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();

    }
}
