package org.example.Monster.Monsters;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Others.Color;
import org.example.Elements.Element;
import org.example.Interfaces.GenericMonster;
import org.example.Monster.MonsterStates.inCage;
import org.example.Monster.MonsterStates.monsterState;
import org.example.Others.Position;

import java.util.Random;

public abstract class Monster extends Element implements GenericMonster {
    // Public variables used to control the frequency of movement in map and the frequency of animation
    public int monsterM = 0;
    public int mouthOpenM = 0;
    public Double monsterF; // Frequency in normal modes
    public Double atmF; // Frequency at the moment
    public Double monsterFrightF; // Frequency in fright modes
    //
    private int frequency = 27;
    public monsterState ms = new inCage(this);
    protected Position cagePosition = new Position(93,115);
    protected String movingDirection;
    private boolean rotate180 = false;
    // Sprites
    private char[][] monsterLeft1;
    private char[][] monsterLeft2;
    private char[][] monsterRight1;
    private char[][] monsterRight2;
    private char[][] monsterUp1;
    private char[][] monsterUp2;
    private char[][] monsterDown1;
    private char[][] monsterDown2;
    private char[][] monsterRun1;
    private char[][] monsterRun2;
    public Monster(int x,int y){
        super(x,y);
        atmF = monsterF;
        movingDirection = "left";
        monsterLeft1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ','#'},
                {'#','W','W','W','W',' ',' ','W','W','W','W',' ',' ','#'},
                {'#','0','0','W','W',' ',' ','0','0','W','W',' ',' ','#'},
                {' ','0','0','W','W',' ',' ','0','0','W','W',' ',' ',' '},
                {' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };
        monsterLeft2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ','#'},
                {'#','W','W','W','W',' ',' ','W','W','W','W',' ',' ','#'},
                {'#','0','0','W','W',' ',' ','0','0','W','W',' ',' ','#'},
                {' ','0','0','W','W',' ',' ','0','0','W','W',' ',' ',' '},
                {' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };

        monsterRight1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ','#'},
                {'#',' ',' ','W','W','W','W',' ',' ','W','W','W','W','#'},
                {'#',' ',' ','W','W','0','0',' ',' ','W','W','0','0','#'},
                {' ',' ',' ','W','W','0','0',' ',' ','W','W','0','0',' '},
                {' ',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };
        monsterRight2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ','#'},
                {'#',' ',' ','W','W','W','W',' ',' ','W','W','W','W','#'},
                {'#',' ',' ','W','W','0','0',' ',' ','W','W','0','0','#'},
                {' ',' ',' ','W','W','0','0',' ',' ','W','W','0','0',' '},
                {' ',' ',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
        monsterUp1 = new char[][]{

                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#','0','0',' ',' ',' ',' ','0','0','#','#','#'},
                {'#','#','W','0','0','W',' ',' ','W','0','0','W','#','#'},
                {'#','#','W','W','W','W',' ',' ','W','W','W','W','#','#'},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#'},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };
        monsterUp2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#','0','0',' ',' ',' ',' ','0','0','#','#','#'},
                {'#','#','W','0','0','W',' ',' ','W','0','0','W','#','#'},
                {'#','#','W','W','W','W',' ',' ','W','W','W','W','#','#'},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#'},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#'},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };

        monsterDown1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#'},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#'},
                {' ',' ','W','W','W','W',' ',' ','W','W','W','W',' ',' '},
                {' ',' ','W','0','0','W',' ',' ','W','0','0','W',' ',' '},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}

        };
        monsterDown2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#'},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#'},
                {' ',' ','W','W','W','W',' ',' ','W','W','W','W',' ',' '},
                {' ',' ','W','0','0','W',' ',' ','W','0','0','W',' ',' '},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
        monsterRun1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' '},
                {' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };
        monsterRun2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' '},
                {' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
    }
    ////////////////////////////////////////////////////
    // Draws                                          //
    ////////////////////////////////////////////////////
    public void draw(TextGraphics graphics, String colorM){
        if (position.getX() > 198) return;
        ms.draw(graphics,colorM);
    }
    public void normalDraw(TextGraphics graphics,String monsterColor){
        switch (movingDirection) {
            case "right":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterRight1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterRight2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterRight1, graphics, monsterColor);
                    break;
                }
            case "left":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterLeft1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterLeft2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterLeft1, graphics, monsterColor);
                    break;
                }
            case "down":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterDown1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterDown2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterDown1, graphics, monsterColor);
                    break;
                }
            case "up":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterUp1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterUp2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterUp1, graphics, monsterColor);
                    break;
                }
        }
    }
    public void blueDraw(TextGraphics graphics){
        if (mouthOpenM <= frequency) {
            drawTheStyle(monsterRun1, graphics, Color.getColor("0"));
            mouthOpenM++;
        } else if (mouthOpenM <= frequency * 2) {
            drawTheStyle(monsterRun2, graphics,  Color.getColor("0"));
            mouthOpenM++;
        } else {
            mouthOpenM = 1;
            drawTheStyle(monsterRun1, graphics,  Color.getColor("0"));
        }
    }
    public void darkDraw(TextGraphics graphics,String monsterColor){
        switch (movingDirection) {
            case "right":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterRight1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterRight2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterRight1, graphics, monsterColor);
                    break;
                }
            case "left":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterLeft1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterLeft2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterLeft1, graphics, monsterColor);
                    break;
                }
            case "down":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterDown1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterDown2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterDown1, graphics, monsterColor);
                    break;
                }
            case "up":
                if (mouthOpenM <= frequency) {
                    drawTheStyle(monsterUp1, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else if (mouthOpenM <= frequency * 2) {
                    drawTheStyle(monsterUp2, graphics, monsterColor);
                    mouthOpenM++;
                    break;
                } else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterUp1, graphics, monsterColor);
                    break;
                }
        }
    }
    ////////////////////////////////////////////////////
    // Game Observer                                  //
    ////////////////////////////////////////////////////
    @Override
    public void FrightHourStarted(){
        ms.FrightHourStarted();
        monsterM = 0;
        atmF = monsterFrightF;
    }
    @Override
    public void FrightHourEnded(){
        rotate180 = false;
        ms.FrightHourEnded();
        monsterM = 0;
        atmF = monsterF;
    }
    ////////////////////////////////////////////////////
    // Movement                                       //
    ////////////////////////////////////////////////////
    public void targetMove(Position p, boolean t, boolean b, boolean d, boolean e){
        int x = this.position.getX();
        int y = this.position.getY();
        double topo = Double.MAX_VALUE;
        double baixo = Double.MAX_VALUE;
        double esq = Double.MAX_VALUE;
        double dir = Double.MAX_VALUE;
        if (e) esq = distance(new Position(x - 1, y), p);
        if (d) dir = distance(new Position(x + 1, y), p);
        if (b) baixo = distance(new Position(x, y + 1), p);
        if (t) topo = distance(new Position(x, y - 1), p);
        double minv = Double.MAX_VALUE;
        if (d && dir <= minv ) {
            minv = dir;
        }
        if (b && baixo <= minv ) {
            minv = baixo;
        }
        if (e && esq <= minv  ) {
            minv = esq;
        }
        if (t && topo <= minv ){
            position = moveUp();
            movingDirection = "up";
        }else if (minv == esq) {
            position = moveLeft();
            movingDirection = "left";
        } else if (minv == baixo) {
            position = moveDown();
            movingDirection = "down";
        } else {
            position = moveRight();
            movingDirection = "right";
        }
    }
    public void frightMove(boolean t, boolean b, boolean d, boolean e){
        if (!rotate180) {
            firstFrightMove();
        } else { // Random direction of movement
            boolean flag = true;
            while (flag) {
                Random random = new Random();
                int randomNumber = random.nextInt(4);
                if (randomNumber == 0 && t ) {
                    position = moveUp();
                    movingDirection = "up";
                    flag = false;
                } else if (randomNumber == 1 && b ) {
                    position = moveDown();
                    movingDirection = "down";
                    flag = false;
                } else if (randomNumber == 2 && d ) {
                    position = moveRight();
                    movingDirection = "right";
                    flag = false;
                } else if (randomNumber == 3 && e) {
                    position = moveLeft();
                    movingDirection = "left";
                    flag = false;
                }
            }
        }
    }
    private void firstFrightMove(){
        if (movingDirection.equals("up")) {
            position = moveDown();
            movingDirection = "down";
        } else if (movingDirection.equals("down")) {
            position = moveUp();
            movingDirection = "up";
        } else if (movingDirection.equals("left")) {
            position = moveRight();
            movingDirection = "right";
        } else {
            position = moveLeft();
            movingDirection = "left";
        }
        rotate180 = true;
    }
    public void move(Position p,char[][]map) { // Check for wich directions the monster can go
        int x = this.position.getX();
        int y = this.position.getY();
        if ((x < 5 || x > 190)) {
            if (movingDirection == "right") {
                position = moveRight();
                return;
            }
            if (movingDirection == "left") {
                position = moveLeft();
                return;
            }
        }
        boolean t = true;
        boolean b = true;
        boolean e = true;
        boolean d = true;
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "down"){
                t = false;
                break;
            }
            if (y-1 >= 0 && y-1 <= 393 && x+i >= 0 && x+i <= 450){
                if(map[y-1][x+i] == 'P'||map[y-1][x+i] == 'c'||(map[y-1][x+i] == 'r' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))t = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "up"){
                b = false;
                break;
            }
            if (y+14 >= 0 && y+14 <= 393 && x+i >= 0 && x+i <= 450){
                if (map[y+14][x+i] == 'P'||map[y+14][x+i] == 'c'||(map[y+14][x+i] == 'r' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))b = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "right"){
                e = false;
                break;
            }
            if (y+i >= 0 && y+i <= 393 && x-1 >= 0 && x-1 <= 450){
                if (map[y+i][x-1] == 'P'||map[y+i][x-1] == 'c'||(map[y+i][x-1] == 'r' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))e = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "left"){
                d = false;
                break;
            }
            if (y+i >= 0 && y+i <= 393 && x+14 >= 0 && x+14 <= 450){
                if (map[y+i][x+14] == 'P'||map[y+i][x+14] == 'c'||(map[y+i][x+14] == 'r' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))d = false;
            }
        }
        ms.move(p,t,b,d,e);
    }
    ////////////////////////////////////////////////////
    // Others                                         //
    ////////////////////////////////////////////////////
    public void changeState(monsterState newState) {
        ms = newState;
    }
    public double distance(Position p, Position p1){
        double difX = p1.getX() - p.getX();
        double difY = p1.getY() - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    ////////////////////////////////////////////////////
    // Getters e Setters                              //
    ////////////////////////////////////////////////////
    public char[][] getMonsterDown1() {
        return monsterDown1;
    }
    public char[][] getMonsterLeft1() {
        return monsterLeft1;
    }
    public char[][] getMonsterRight1() {
        return monsterRight1;
    }
    public char[][] getMonsterRun1() {
        return monsterRun1;
    }
    public char[][] getMonsterUp1() {
        return monsterUp1;
    }
    public void setMs(monsterState ms) {
        this.ms = ms;
    }
    public void setMovingDirection(String movingDirection) {
        this.movingDirection = movingDirection;
    }
}

