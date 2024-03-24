package org.example;

public class MazeSolverProject  {

        public static void main(String[] args) {
        int grid = 5;
        int[][] maze = getMaze(grid);
        Stack <Coordinate> path = new Stack();
        maze[1][1]=2;
        MazeUtility.plotMaze(maze);
        Coordinate target = new Coordinate(2 * grid - 1, 2 * grid - 1);
        MazeSolver solver = new MazeSolver(maze,target,path);
         printPath(maze,path);
        MazeUtility.plotMaze(maze);


    }




    public static int[][] getMaze(int grid) {
        MazeGenerator maze = new MazeGenerator(grid);
        String str = maze.toString();

        int[][] maze2D = MazeUtility.Convert2D(str);
        return maze2D;
    }




    // Additional print method for stack remain unchanged
    public static void printPath(int[][]maze, Stack <Coordinate> path){
            //Empty stack
            Stack <Coordinate> trial=new Stack<>();

            //draw path
            while (!path.isEmpty()){
                Coordinate coordinate=path.pop();
                int row= coordinate.row;
                int col= coordinate.col;
                maze[row][col]=2;
                trial.push(coordinate);

            }

        int numRows = maze.length;
        int numCols = maze[0].length;

        //print path

        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                if (maze[y][x] == 1) {
                    System.out.print('X');
                } else if (maze[y][x] == 0) {
                    System.out.print(' ');
                } else if (maze[y][x] == 2) {
                    System.out.print('*');
                }
            }
            System.out.println();
        }


        //push elements back to stack

        while (!trial.isEmpty()){
            Coordinate coordinate=trial.pop();
            path.push(coordinate);
        }


    }





}
