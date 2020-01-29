package hackerrank.sudoku;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static boolean all_numbers_used(boolean [] number) {
        for (int i = 0; i < 9; i++) {
            if  (!number[i]) {
                return false;
            }
        }
        return true;
    }
    
    static String box_toString(int box, int [][] grid) {
        String line[] = new String[3];
        String curLine = "";
        int col = (int) Math.floor(Double.valueOf(box % 3))*3;
        int row = (int) Math.floor(Double.valueOf(box / 3))*3;
        for(int j = row; j < (row +3); j++) {
            for(int k = col; k < (col +3); k++) {
                curLine = curLine.concat(Integer.toString(grid[j][k]));
            }
            line[j-row] = curLine;
            curLine = "";
        }
        return line[0] + "\r\n" + line[1] + "\r\n" + line[2];
    }
    
    static boolean valid_box(int box, int [][] grid) {
        boolean number[] = new boolean[9];
        int col = (int) Math.floor(Double.valueOf(box % 3))*3;
        int row = (int) Math.floor(Double.valueOf(box / 3))*3;
        for(int j = row; j < (row +3); j++) {
            for(int k = col; k < (col +3); k++) {
                if (!number[grid[j][k]-1]) {
                    number[grid[j][k]-1] = true;
                } else {
                    return false;
                }
            }
        }
        return all_numbers_used(number);
    }
    
    static boolean valid_row(int row, int [][] grid) {
        boolean number[] = new boolean[9];
        for(int k = 0; k < 9; k++) {
            if (!number[grid[row][k]-1]) {
                number[grid[row][k]-1] = true;
            } else {
                return false;
            }
        }
        return all_numbers_used(number);    
    }
    
    static boolean valid_col(int col, int [][] grid) {
        boolean number[] = new boolean[9];
        for(int j = 0; j < 9; j++) {
            if (!number[grid[j][col]-1]) {
                number[grid[j][col]-1] = true;
            } else {
                return false;
            }
        }
        return all_numbers_used(number);
    }
    
    static boolean valid_board(int [][] grid) {
        boolean retVal = true;
        for(int i = 0; i < 9; i++) {
            //System.out.println(box_toString(i,grid));
            retVal = retVal && 
                    valid_box(i, grid) &&
                    valid_row(i, grid) &&
                    valid_col(i, grid);
        }
        return retVal;
    }
    
    static void sudoku_solve(int [][] grid){
        //sudoku_print(grid);
    }
    static boolean can_place(int[][] board, int row, int col, int num) {
        for (int d = 0; d < board.length; d++) {
            if (board[row][d] == num){ 
                return false;
            }
        } 
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num){ 
                return false; 
            } 
        } 
        int sqrt = (int) Math.sqrt(board.length); 
        int boxRowStart = row - row % sqrt; 
        int boxColStart = col - col % sqrt; 
        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            } 
        } 
        return true; 
    }
    public static boolean solve_rec(int[][] board, int n) { 
        int row = -1; 
        int col = -1; 
        boolean isEmpty = true; 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                }
                isEmpty = false;  
                break; 
            }
            if (!isEmpty) {
                break;
            } 
        }
        if (isEmpty) {
            return true; 
        } 
        for (int num = 1; num <= n; num++) {
            if (can_place(board, row, col, num))  {
                board[row][col] = num; 
                if (solve_rec(board, n)) {
                    sudoku_print(board);
                    return true; 
                } else {
                    board[row][col] = 0; // replace it 
                } 
            } 
        } 
        return false; 
    }
    static void sudoku_print(int [][] grid){
        for(int j = 0; j < 9; j++) {
            for(int k = 0; k < 9; k++) {
                System.out.print(grid[j][k] + " ");
            }
            System.out.println();
        }                
    }

    public static void main(String[] args) {
String testCase = "2" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 8 0 0 0 0 4 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 6 0 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"2 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 0 2 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 8 0 0 0 0 4 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 6 0 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n" +
"2 0 0 0 0 0 0 0 0" + "\r\n" +
"0 0 0 0 0 0 2 0 0" + "\r\n" +
"0 0 0 0 0 0 0 0 0" + "\r\n";

String testCase2 = "2" + "\r\n" +
"3 4 5 6 7 8 9 1 2" + "\r\n" +
"6 7 8 9 1 2 3 4 5" + "\r\n" +
"9 1 2 3 4 5 6 7 8" + "\r\n" +
"1 2 3 4 5 6 7 8 9" + "\r\n" +
"4 5 6 7 8 9 1 2 3" + "\r\n" +
"7 8 9 1 2 3 4 5 6" + "\r\n" +
"2 3 4 5 6 7 8 9 1" + "\r\n" +
"5 6 7 8 9 1 2 3 4" + "\r\n" +
"8 9 1 2 3 4 5 6 7" + "\r\n" +
"3 4 5 6 7 8 9 1 2" + "\r\n" +
"6 7 8 9 1 2 3 4 5" + "\r\n" +
"9 1 2 3 4 5 6 7 8" + "\r\n" +
"1 2 3 4 5 6 7 8 9" + "\r\n" +
"4 5 6 7 8 9 1 2 3" + "\r\n" +
"7 8 9 1 2 3 4 5 6" + "\r\n" +
"2 3 4 5 6 7 8 9 1" + "\r\n" +
"5 6 7 8 9 1 2 3 4" + "\r\n" +
"8 9 1 2 3 4 5 6 7" + "\r\n";

        
ByteArrayInputStream testInputStream = new ByteArrayInputStream(testCase.getBytes());       
System.setIn(testInputStream);
        
        Scanner in = new Scanner(System.in);
        int n;
        int board[][] = new int[9][9];

        n = in.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 9; j++) {
                for(int k = 0; k < 9; k++) {
                    board[j][k] = in.nextInt();
                }
            }
            //sudoku_solve(board);
            solve_rec(board, 9)
//            System.out.println(valid_board(board));
        }
    }
}

