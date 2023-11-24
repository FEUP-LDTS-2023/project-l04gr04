package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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


    private boolean rotate180 = false;
    public Monster(int x,int y){
        super(x,y);
        facingDirection = "right";
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
        if (mode.equals("fright"))graphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        drawTheStyle(monsterDown1,graphics, colorM);
        switch (facingDirection){
            case "right":
                if (mouthOpenM<=5){
                    drawTheStyle(monsterRight1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=10 ){
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
                if (mouthOpenM<=5){
                    drawTheStyle(monsterLeft1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM<=10 ){
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
                if (mouthOpenM<=5){
                    drawTheStyle(monsterDown1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM>5 && mouthOpenM<=10){
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
                if (mouthOpenM<=5){
                    drawTheStyle(monsterUp1,graphics,colorM);
                    mouthOpenM++;
                    break;
                }
                else if (mouthOpenM>5 && mouthOpenM<=10){
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

    protected double distance(Position p, Position p1){
        double difX = p1.getX()+2.5 - p.getX();
        double difY = p1.getY()+1 - p.getY();
        return Math.sqrt(difX * difX + difY * difY);
    }
    public void move(Position p,char[][]map,TextGraphics graphics,String color){
        int x = this.position.getX();
        int y = this.position.getY();
        if(x == 1 && y == 25 && movingDirection.equals("left")){
            position = new Position(80,25);
            return;
        }
        if(position.getX() == 77 && position.getY() == 25 && movingDirection.equals("right")){
            position = new Position(1,25);
            return;
        }
        boolean t = true;
        boolean b = true;
        boolean e = true;
        boolean d = true;
        if (map[y - 1][x] == 'P' || map[y - 1][x + 1] == 'P' || map[y - 1][x + 2] == 'P' || map[y - 1][x + 3] == 'P' || map[y - 1][x + 4] == 'P') t = false;
        if (map[y + 3][x] == 'P' || map[y + 3][x + 1] == 'P' || map[y + 3][x + 2] == 'P' || map[y + 3][x + 3] == 'P' || map[y + 3][x + 4] == 'P') b = false;
        if (map[y][x - 1] == 'P' || map[y + 1][x - 1] == 'P' || map[y + 2][x - 1] == 'P') e = false;
        if (map[y][x + 5] == 'P' || map[y + 1][x + 5] == 'P' || map[y + 2][x + 5] == 'P') d = false;
        if (mode.equals("fright")){
            if(!rotate180){
                if (movingDirection.equals("up")){
                    position = moveDown();
                    movingDirection = "down";
                }else if(movingDirection.equals("down")){
                    position = moveUp();
                    movingDirection = "up";
                }else if(movingDirection.equals("left")){
                    position = moveRight();
                    movingDirection = "right";
                }else{
                    position = moveLeft();
                    movingDirection = "left";
                }
                rotate180 = true;
            }else{
                boolean flag = true;
                while(flag){
                    Random random = new Random();
                    int randomNumber = random.nextInt(4);
                    if (randomNumber == 0 && t && !movingDirection.equals("down")){
                        position = moveUp();
                        movingDirection = "up";
                        flag = false;
                    }else if(randomNumber == 1 && b && !movingDirection.equals("up")){
                        position = moveDown();
                        movingDirection = "down";
                        flag = false;
                    }else if(randomNumber == 2 && d && !movingDirection.equals("left")){
                        position = moveRight();
                        movingDirection = "right";
                        flag = false;
                    }else if(randomNumber == 3 && e && !movingDirection.equals("right")){
                        position = moveLeft();
                        movingDirection = "left";
                        flag = false;
                    }
                }}
        }else {
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

