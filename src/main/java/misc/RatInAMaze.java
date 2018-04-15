package misc;

import java.util.LinkedList;
import java.util.List;

public class RatInAMaze {
    private static final int[][] maze = {
        //0 1 2 3 4
        { 1,0,1,1,0 }, // 0
        { 1,1,1,1,1 }, // 1
        { 0,0,1,1,0 }, // 2
        { 1,0,0,1,1 }  // 3
    };
    private static final int maxRows = maze.length;
    private static final int maxColumns = maze[0].length;

    private static String rowColumnToString(int row, int column) {
        return "" + row + "," + column;
    }

    private static boolean alreadyVisited(List<String> trail, int row, int column) {
        final String sCoordinate = rowColumnToString(row, column);
        for (String p : trail) {
            if (sCoordinate.equals(p)) {
                return true;
            }
        }

        return false;
    }

    private static boolean canGoUp(int[][] maze, int row, int column) {
        if (row == 0) {
            return false;
        }

        return maze[row - 1][column] == 1;
    }

    private static boolean canGoRight(int[][] maze, int row, int column) {
        if (column == maxColumns - 1) {
            return false;
        }

        return maze[row][column + 1] == 1;
    }

    private static boolean canGoDown(int[][] maze, int row, int column) {
        if (row == maxRows - 1) {
            return false;
        }

        return maze[row + 1][column] == 1;
    }

    private static boolean canGoLeft(int[][] maze, int row, int column) {
        if (column == 0) {
            return false;
        }

        return maze[row][column - 1] == 1;
    }

    private static boolean isSolved(int row, int column) {
        return (row == maxRows - 1 && column == maxColumns - 1);
    }

    private static void solveMaze(int[][] maze, int row, int column, List<String> trail) {
        if (alreadyVisited(trail, row, column)) {
            return;
        }

        trail.add(rowColumnToString(row, column));

        if (isSolved(row, column)) {
            System.out.println(trail);
            return;
        }

        if (canGoUp(maze, row, column)) {
            solveMaze(maze, row - 1, column, trail);
        }

        if (canGoRight(maze, row, column)) {
            solveMaze(maze, row, column + 1, trail);
        }

        if (canGoDown(maze, row, column)) {
            solveMaze(maze, row + 1, column, trail);
        }

        if (canGoLeft(maze, row, column)) {
            solveMaze(maze, row, column - 1, trail);
        }

        // Couldn't go anywhere from this point. Hence, remove this coordinate from the trail.
        trail.remove(trail.size() - 1);
    }

    public static void main(String[] arg) {
        List<String> trail = new LinkedList<>();
        solveMaze(maze, 0, 0, trail);
    }
}
