package org.example.Numbers;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Element;
import org.example.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    int currentScore;
    Numero numero = new Numero(10, 10);
    private List<Integer> reverseDisplay = new ArrayList<>(Collections.nCopies(1, 0));
    private List<Position> digitPositions = new ArrayList<>();
    public List<Integer> getReverseDisplay() {
        return reverseDisplay;
    }
    public boolean updateReverseDisplay(int number) {
        List<Integer> original = reverseDisplay;
        Position digit = new Position(50, 10);
        reverseDisplay.clear();
        while (number > 0) {
            int last = number % 10;
            reverseDisplay.add(last);
            digitPositions.add(new Position(digit.getX() + 10, digit.getY()));
            number /= 10;
        }
        return original.equals(reverseDisplay);
    }
    public int getNumber() {
        currentScore = 0;
        int c = 1;
        for (int i = reverseDisplay.size() - 1; i >= 0; i--) {
            currentScore += (reverseDisplay.get(i) * c);
            c *= 10;
        }
        return currentScore;
    }
    public void increment(int inc) {
        currentScore = getNumber();
        currentScore += inc;
        updateReverseDisplay(currentScore);
    }
    public void draw(int inc) {
        increment(inc);
        List<Integer> original = reverseDisplay;
        if (!updateReverseDisplay(currentScore)) {
            for (int i = reverseDisplay.size() - 1; i > 0; i--) { // último algarismo é sempre zero
                if (original.get(i) != reverseDisplay.get(i)) numero.changeNumber(reverseDisplay.get(i));
            }
        }
        numero.changeNumber(0);
    }
}
