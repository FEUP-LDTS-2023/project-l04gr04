package org.example.Chars;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Others.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    private int currentScore;
    private int lp = 120; // X position of the Start of Score
    private Numero numero = new Numero(lp + 10, 10);
    private List<Integer> reverseDisplay = new ArrayList<>(Collections.nCopies(1, 0));
    public Score() {
        numero.changeNumber(0);
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void draw(TextGraphics graphics) {
        numero.draw(graphics);
        if (updateReverseDisplay(currentScore)) {
            for (int i = reverseDisplay.size() - 1; i >= 0; i--) {
                graphics.setBackgroundColor(TextColor.Factory.fromString(Color.getColor("background")));
                graphics.fillRectangle(new TerminalPosition(lp - i * 10, 10), new TerminalSize(7, 7), ' ');
                Numero n = new Numero(lp - i * 10, 10);
                n.changeNumber(reverseDisplay.get(i));
                n.draw(graphics);
            }
        }
    }
    ////////////////////////////////////////////////////
    // Others                                         //
    ////////////////////////////////////////////////////
    public boolean updateReverseDisplay(int number) {
        List<Integer> original = reverseDisplay;
        reverseDisplay.clear();
        while (number > 0) {
            int last = number % 10;
            reverseDisplay.add(last);
            number /= 10;
        }
        return original.equals(reverseDisplay);
    }
    public void increment(int inc) {
        currentScore += inc;
        updateReverseDisplay(currentScore);
    }
    ////////////////////////////////////////////////////
    // Getters                                        //
    ////////////////////////////////////////////////////
    public int getCurrentScore() {
        return currentScore;
    }
    public List<Integer> getReverseDisplay() {
        return reverseDisplay;
    }
}
