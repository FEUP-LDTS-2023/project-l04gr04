package org.example.Monster;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.example.Element;
import org.example.Monster.States.*;
import org.example.Position;

import java.util.Objects;
import java.util.Random;

public abstract class Monster extends Element implements GenericMonster {
    public monsterState ms = new inCage(this);
    protected String movingDirection;
    public int monsterM = 0;
    public Double monsterF;
    public Double atmF;
    public Double monsterFrightF;
    public int mouthOpenM = 0;
    char[][] monsterLeft1;
    char[][] monsterLeft2;
    char[][] monsterRight1;
    char[][] monsterRight2;
    char[][] monsterUp1;
    char[][] monsterUp2;
    char[][] monsterDown1;
    char[][] monsterDown2;
    char[][] monsterRun1;
    char[][] monsterRun2;
    private int frequency = 27;
    protected Position cagePosition = new Position(93,115);
    private boolean rotate180 = false;
    @Override
    public void FrightHourStarted(){
        ms.FrightHourStarted();
        monsterM = 0;
        atmF = monsterFrightF;
    }
    @Override
    public void FrightHourEnded(){

        ms.FrightHourEnded();
        monsterM = 0;
        atmF = monsterF;
    }
    public Monster(int x,int y){
        super(x,y);
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
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#',},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#',},
                {' ',' ','W','W','W','W',' ',' ','W','W','W','W',' ',' ',},
                {' ',' ','W','0','0','W',' ',' ','W','0','0','W',' ',' ',},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}

        };
        monsterDown2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ','W','W',' ',' ',' ',' ','W','W',' ',' ','#',},
                {'#',' ','W','W','W','W',' ',' ','W','W','W','W',' ','#',},
                {' ',' ','W','W','W','W',' ',' ','W','W','W','W',' ',' ',},
                {' ',' ','W','0','0','W',' ',' ','W','0','0','W',' ',' ',},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
        monsterRun1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',},
                {' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ','#',' ',' ',' ','#','#',' ',' ',' ','#',' ',' '},
                {' ','#','#','#',' ',' ','#','#',' ',' ','#','#','#',' '}
        };
        monsterRun2 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#',},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W','W',' ',' ','W','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',},
                {' ',' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',},
                {' ',' ',' ','W',' ','W',' ','W',' ','W',' ',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
    }
    public void draw(TextGraphics graphics, String colorM){
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        if (position.getX() > 198) return;
        ms.draw(graphics,colorM);
    }
    public void changeState(monsterState newState) {
        ms = newState;
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
    public void blueDraw(TextGraphics graphics,String monsterColor){
        if (mouthOpenM <= frequency) {
            drawTheStyle(monsterRun1, graphics, monsterYesColor);
            mouthOpenM++;
        } else if (mouthOpenM <= frequency * 2) {
            drawTheStyle(monsterRun2, graphics, monsterYesColor);
            mouthOpenM++;
        } else {
            mouthOpenM = 1;
            drawTheStyle(monsterRun1, graphics, monsterYesColor);
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
    public double distance(Position p, Position p1){
        double difX = p1.getX() - p.getX();
        double difY = p1.getY() - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    public void targetMove(Position p,char[][]map, boolean t, boolean b, boolean d, boolean e){
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
        if (!rotate180) { // Isto tem de ser alterado , variável não tá a ser reposta
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
            } else {
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
                if(map[y-1][x+i] == 'P'||map[y-1][x+i] == 'c'||(map[y-1][x+i] == 'R' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))t = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "up"){
                b = false;
                break;
            }
            if (y+14 >= 0 && y+14 <= 393 && x+i >= 0 && x+i <= 450){
                if (map[y+14][x+i] == 'P'||map[y+14][x+i] == 'c'||(map[y+14][x+i] == 'R' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))b = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "right"){
                e = false;
                break;
            }
            if (y+i >= 0 && y+i <= 393 && x-1 >= 0 && x-1 <= 450){
                if (map[y+i][x-1] == 'P'||map[y+i][x-1] == 'c'||(map[y+i][x-1] == 'R' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))e = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (movingDirection == "left"){
                d = false;
                break;
            }
            if (y+i >= 0 && y+i <= 393 && x+14 >= 0 && x+14 <= 450){
                if (map[y+i][x+14] == 'P'||map[y+i][x+14] == 'c'||(map[y+i][x+14] == 'R' && !ms.modeOn().equals("eaten") && !ms.modeOn().equals("inCage")))d = false;
            }
        }
        ms.move(p,map,t,b,d,e);
    }
    public void pacManLost() {

    }
}

