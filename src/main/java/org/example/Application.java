package org.example;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game(100,100);
        game.run();
    }
}
