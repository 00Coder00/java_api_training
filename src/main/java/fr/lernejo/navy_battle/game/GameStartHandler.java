package fr.lernejo.navy_battle.game;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.logic.GameBoard;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class GameStartHandler implements HttpHandler {
    private static final ArrayList<String> METHODS = new ArrayList<>(List.of("GET", "POST"));
    private final GameBoard _board = new GameBoard();

    public GameBoard getBoard() {
        return _board;
    }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        System.out.println(new String(requestBody.readAllBytes()));
        if (METHODS.contains(exchange.getRequestMethod())) {
            JSONObject json = new JSONObject();
            json.put("id", "0c575465-21f6-43c9-8a2d-bc64c3ae6241");
            json.put("url", "http://localhost:9876");
            json.put("message", "May the best code win");

            String response = json.toString(4);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        } else {
            System.out.println("Method not supported");
        }

    }
}
