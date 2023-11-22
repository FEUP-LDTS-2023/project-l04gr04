package org.example;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game(91,57);
        game.run();
    }
}
