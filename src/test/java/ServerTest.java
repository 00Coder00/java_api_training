import fr.lernejo.navy_battle.Launcher;
import fr.lernejo.navy_battle.game.PingHandler;
import org.junit.jupiter.api.Test;

public class ServerTest {
    @Test
    void CreateServer() {
        try {
            Launcher.LaunchServer("5000");
        } catch (Exception e) {

        }
    }

    @Test
    void CreateClient() {
        try {
            Launcher.LaunchClient("6000", "http://localhost:5000");
        } catch (Exception e ) {

        }
    }

    @Test
    void PingTest() {
        PingHandler handler = new PingHandler();
    }
}
