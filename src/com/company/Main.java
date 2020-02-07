package com.company;


import org.omg.CORBA.MARSHAL;

enum Direction { UP, RIGHT, DOWN, LEFT } // можно вынести в отдельный класс

/*
Реализовать класс Robot с конструкторами, в т.ч. конструктор копирования,
методами get.. и turnLeft turnRight stepForward
Реализовать класс-наследник от Robot
с функцией moveTo , перемещающей робота
из текущего положения в заданную точку
используя только функции, реализованные в Robot
 */

class Robot {
    public Robot() {
        x = 0; y = 0; dir = Direction.UP;
    }

    public Robot(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void turnRight() {
        switch (getDir()){
            case LEFT:
                dir = Direction.UP;

                break;
            case DOWN:
                dir = Direction.LEFT;
                break;

            case UP:
                dir = Direction.RIGHT;
                break;
            case RIGHT:
                dir = Direction.DOWN;
                break;

        }
    }
    public void turnLeft() {
        switch (getDir()){
            case RIGHT:
                dir = Direction.UP;
                break;
            case UP:
                dir = Direction.LEFT;
                break;
            case DOWN:
                dir = Direction.RIGHT;
                break;
            case LEFT:
                dir = Direction.DOWN;
                break;
        }


    }
    public void stepForward() {
        switch (getDir()){
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDir() {
        return dir;
    }
    private Direction dir = Direction.UP;
}

class SmartRobot extends Robot {
    public SmartRobot() {
        super(0,0, Direction.UP);
    }
    public void moveTo(int toX, int toY) {
        int curX = getX();
        int curY = getY();
        Direction curDir = getDir();
        if (curX>toX){
            while (curDir != Direction.LEFT){
                turnLeft();
                curDir = getDir();
            }
        }
        else {
            while (curDir != Direction.RIGHT){
                turnRight();
                curDir = getDir();
            }
        }

        int amountOfStepsX = Math.abs(toX-curX);
        for (int i = 0; i < amountOfStepsX ; i++) {
            stepForward();
        }

        if (curY>toY){
            while (curDir != Direction.DOWN){
                turnRight();
                curDir = getDir();
            }
        }
        else {
            while (curDir != Direction.UP){
                turnRight();
                curDir = getDir();
             }
        }

        int amountOfStepsY = Math.abs(toY - curY);
        for (int i = 0; i < amountOfStepsY ; i++) {
            stepForward();
        }


    }

}

public class Main {

    public static void main(String[] args) {
	SmartRobot robot = new SmartRobot();
	robot.moveTo(5,-2);
	robot.moveTo(10,-5);
	robot.moveTo(2,7);
	System.out.println(robot.getX());
	System.out.println(robot.getY());
    }
}
