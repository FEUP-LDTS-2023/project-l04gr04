package org.example.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    int currentScore;
    List<Integer> reverseDisplay = new ArrayList<>(Collections.nCopies(1, 0));
    public List<Integer> getReverseDisplay() {
        return reverseDisplay;
    }
    public void updateReverseDisplay(int number) {
        while (number > 0) {
            int last = number % 10;
            reverseDisplay.add(last);
            number /= 10;
        }
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
    public void draw() {

    }
}
