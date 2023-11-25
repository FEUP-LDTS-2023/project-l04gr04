package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Element implements GenericMonster  {
    public String mode = "Scatter";
    protected String movingDirection = "null";
    public int mouthOpenM = 0;
    char[][] monsterLeft1;
    char[][] monsterLeft2;
    char[][] monsterRight1;
    char[][] monsterRight2;
    char[][] monsterUp1;
    char[][] monsterUp2;
    char[][] monsterDown1;
    char[][] monsterDown2;
    public String facingDirection;

    private int frequency = 15;
    private boolean rotate180 = false;
    public Monster(int x,int y){
        super(x,y);
        facingDirection = "left";
        monsterLeft1 = new char[][]{
                {'#','#','#','#','#',' ',' ',' ',' ','#','#','#','#','#'},
                {'#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#'},
                {'#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#'},
                {'#',' ','+','+',' ',' ',' ',' ','+','+',' ',' ',' ','#'},
                {'#','+','+','+','+',' ',' ','+','+','+','+',' ',' ','#'},
                {'#','0','0','+','+',' ',' ','0','0','+','+',' ',' ','#'},
                {' ','0','0','+','+',' ',' ','0','0','+','+',' ',' ',' '},
                {' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ',' ',' '},
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
                {'#',' ','+','+',' ',' ',' ',' ','+','+',' ',' ',' ','#'},
                {'#','+','+','+','+',' ',' ','+','+','+','+',' ',' ','#'},
                {'#','0','0','+','+',' ',' ','0','0','+','+',' ',' ','#'},
                {' ','0','0','+','+',' ',' ','0','0','+','+',' ',' ',' '},
                {' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ',' ',' '},
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
                {'#',' ',' ',' ','+','+',' ',' ',' ',' ','+','+',' ','#'},
                {'#',' ',' ','+','+','+','+',' ',' ','+','+','+','+','#'},
                {'#',' ',' ','+','+','0','0',' ',' ','+','+','0','0','#'},
                {' ',' ',' ','+','+','0','0',' ',' ','+','+','0','0',' '},
                {' ',' ',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' '},
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
                {'#',' ',' ',' ','+','+',' ',' ',' ',' ','+','+',' ','#'},
                {'#',' ',' ','+','+','+','+',' ',' ','+','+','+','+','#'},
                {'#',' ',' ','+','+','0','0',' ',' ','+','+','0','0','#'},
                {' ',' ',' ','+','+','0','0',' ',' ','+','+','0','0',' '},
                {' ',' ',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' '},
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
                {'#','#','+','0','0','+',' ',' ','+','0','0','+','#','#'},
                {'#','#','+','+','+','+',' ',' ','+','+','+','+','#','#'},
                {'#',' ','+','+','+','+',' ',' ','+','+','+','+',' ','#'},
                {'#',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ','#'},
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
                {'#','#','+','0','0','+',' ',' ','+','0','0','+','#','#'},
                {'#','#','+','+','+','+',' ',' ','+','+','+','+','#','#'},
                {'#',' ','+','+','+','+',' ',' ','+','+','+','+',' ','#'},
                {'#',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ','#'},
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
                {'#',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ','#',},
                {'#',' ','+','+','+','+',' ',' ','+','+','+','+',' ','#',},
                {' ',' ','+','+','+','+',' ',' ','+','+','+','+',' ',' ',},
                {' ',' ','+','0','0','+',' ',' ','+','0','0','+',' ',' ',},
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
                {'#',' ',' ','+','+',' ',' ',' ',' ','+','+',' ',' ','#',},
                {'#',' ','+','+','+','+',' ',' ','+','+','+','+',' ','#',},
                {' ',' ','+','+','+','+',' ',' ','+','+','+','+',' ',' ',},
                {' ',' ','+','0','0','+',' ',' ','+','0','0','+',' ',' ',},
                {' ',' ',' ','0','0',' ',' ',' ',' ','0','0',' ',' ',' ',},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' '},
                {'#',' ',' ','#','#','#',' ',' ','#','#','#',' ',' ','#'}
        };
    }
    public void draw(TextGraphics graphics, String colorM){
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        switch (facingDirection){
            case "right":
                if (mouthOpenM<=frequency){
                    drawTheStyle(monsterRight1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=frequency*2 ){
                    drawTheStyle(monsterRight2,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterRight1,graphics,colorM);
                    break;
                }

            case "left":
                if (mouthOpenM<=frequency){
                    drawTheStyle(monsterLeft1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=frequency*2){
                    drawTheStyle(monsterLeft2,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterLeft1,graphics,colorM);
                    break;
                }
            case "down":
                if (mouthOpenM<=frequency){
                    drawTheStyle(monsterDown1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=frequency*2){
                    drawTheStyle(monsterDown2,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterDown1,graphics,colorM);
                    break;
                }
            case "up":
                if (mouthOpenM<=frequency){
                    drawTheStyle(monsterUp1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=frequency*2){
                    drawTheStyle(monsterUp2,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else {
                    mouthOpenM = 1;
                    drawTheStyle(monsterUp1,graphics,colorM);
                    break;
                }
        }
    }
    public double distance(Position p, Position p1){
        double difX = p1.getX() - p.getX();
        double difY = p1.getY() - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    public void move(Position p,char[][]map) {
        int x = this.position.getX();
        int y = this.position.getY();
        boolean t = true;
        boolean b = true;
        boolean e = true;
        boolean d = true;
        for (int i = 0 ; i < 14 ; i++){
            if (y-1 >= 0 && y-1 <= 391 && x+i >= 0 && x+i <= 367){
                if(map[y-1][x+i] == 'P')t = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (y+14 >= 0 && y+14 <= 391 && x+i >= 0 && x+i <= 367){
                if (map[y+14][x+i] == 'P')b = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (y+i >= 0 && y+i <= 391 && x-1 >= 0 && x-1 <= 367){
                if (map[y+i][x-1] == 'P')e = false;
            }
        }
        for (int i = 0 ; i < 14 ; i++){
            if (y+i >= 0 && y+i <= 391 && x+14 >= 0 && x+14 <= 367){
                if (map[y+i][x+14] == 'P')d = false;
            }
        }
        if (mode.equals("fright")) {
            if (!rotate180) {
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
                    if (randomNumber == 0 && t && !movingDirection.equals("down")) {
                        position = moveUp();
                        movingDirection = "up";
                        flag = false;
                    } else if (randomNumber == 1 && b && !movingDirection.equals("up")) {
                        position = moveDown();
                        movingDirection = "down";
                        flag = false;
                    } else if (randomNumber == 2 && d && !movingDirection.equals("left")) {
                        position = moveRight();
                        movingDirection = "right";
                        flag = false;
                    } else if (randomNumber == 3 && e && !movingDirection.equals("right")) {
                        position = moveLeft();
                        movingDirection = "left";
                        flag = false;
                    }
                }
            }
        } else {
            double topo = 2000000000;
            double baixo = 2000000000;
            double esq = 2000000000;
            double dir = 2000000000;
            if (e) esq = distance(new Position(x - 1, y), p);
            if (d) dir = distance(new Position(x + 1, y), p);
            if (b) baixo = distance(new Position(x, y + 1), p);
            if (t) topo = distance(new Position(x, y - 1), p);
            double minv = 20000000;
            if (d && dir <= minv && !movingDirection.equals("left")) minv = dir;
            if (b && baixo <= minv && !movingDirection.equals("up")) {
                minv = baixo;
            }
            if (e && esq <= minv && !movingDirection.equals("right")) {
                minv = esq;
            }
            if (t && topo <= minv && !movingDirection.equals("down")) {
                minv = topo;
                position = moveUp();
                movingDirection = "up";
            } else if (minv == esq) {
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
    }
}

