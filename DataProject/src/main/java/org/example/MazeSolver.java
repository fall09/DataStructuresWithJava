package org.example;


public class MazeSolver{
    int[][] maze;
    Coordinate start =new Coordinate(1,1);
    Coordinate exit;
    boolean foundExit;

    Stack<Coordinate> path;

    Coordinate[] directions = {
            new Coordinate(-1, 0), //Up
            new Coordinate(1, 0),  //Down
            new Coordinate(0, -1), //Left
            new Coordinate(0, 1)   //Right
    };

    public MazeSolver(int[][] maze, Coordinate exit,Stack<Coordinate> path) {
        this.maze = maze;
        this.exit = exit;
        this.foundExit = false;
        this.path=path;
        solveMaze();
    }

    public void solveMaze() {

        solver(start.row, start.col);
    }

    private void solver(int row, int col) {
        if (row == exit.row && col == exit.col) {
            foundExit = true;
            path.push(new Coordinate(row,col));
            maze[row][col]=2;
            return;
        }

        if (foundExit) {
            return;
        }

        maze[row][col]=2;
        path.push(new Coordinate(row,col));


        for (Coordinate dir : directions) {
            int newRow = row + dir.row;
            int newCol = col + dir.col;

            if (canMove(newRow, newCol) && maze[newRow][newCol] == 0) {
                solver(newRow, newCol);
            }
        }

        if (!foundExit) {
            path.pop();
            maze[row][col]=0;
        }

    }

    private boolean canMove(int row, int col) {
        return (row >= 0) && (row < maze.length) && (col >= 0) && (col < maze[0].length) ;
    }



}