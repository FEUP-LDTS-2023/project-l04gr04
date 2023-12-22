package org.example;

import java.util.HashMap;
import java.util.Map;

public class Color {
    private static final Map<String, String> Colors = new HashMap<>();
    static {
        Colors.put("background", "#000000");
        Colors.put("white", "#FFFFFF");
        Colors.put("walls", "#2121DE");
        Colors.put("gate", "#FFB8FF");
        Colors.put("yellow", "#FFB897");
        Colors.put("coins", "#959043");
        Colors.put("pink", "#FFB8FF");
        Colors.put("red", "#FF0000");
        Colors.put("blue", "#00FFFF");
        Colors.put("orange", "#FFB852");
        Colors.put("player", "#B5D221");
        Colors.put("R", "#EC2324");
        Colors.put("W", "#FFFFFF");
        Colors.put("B", "#9F6022");
        Colors.put("Y", "#FFB897");
        Colors.put("y", "#FCFF00");
        Colors.put("a", "#B5D221");
        Colors.put("A", "#47B3B0");
        Colors.put("K", "#46B6FE");
        Colors.put("k", "#2121FF");
        Colors.put("g", "#00FD00");
        Colors.put("o", "#FFB652");
        Colors.put("O", "#DE9751");
        Colors.put("b", "#E49B51");
        Colors.put("0", "#0000FF");
    }

    public static String getColor(String nomeCor) {
        return Colors.get(nomeCor);
    }

}
