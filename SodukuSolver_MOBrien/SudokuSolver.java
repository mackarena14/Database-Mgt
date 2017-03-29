import java.util.Scanner;
import java.io.*;

/**
 *Solves a soduku puzzle from inputter numbers, 0 representing a blank box
 * 
 * Mackenzie O'Brien 
 * AP Computer Science Pd 6
 * 2/11/15
 */
public class SudokuSolver
{
    int numZeros=0;
    public  static void main()
    {
        Scanner s=new Scanner(System.in);
        System.out.print("Input size of puzzle: ");
        SudokuPuzzle puzzle=new SudokuPuzzle(s.nextInt());
        System.out.println("Original Puzzle");
        puzzle.printPuzzle();
        if (puzzle.getNumZero()==0)
            puzzle.printPuzzle();
        else 
            System.out.print ("Unsolvable");
    }

    private int findSolutions(int row,int col,SudokuPuzzle puzzle)
    {
        if (puzzle.getNumZero()>0){
            while (puzzle.getLocation(row,col)!=0){
                col++;
                if (col>puzzle.getSize()){
                    col=0;
                    row++;
                    if (row>puzzle.getSize())
                        return puzzle.getNumZero();
                }
            }
            if (puzzle.getNumZero()>0){
                for (int digit=1;digit<=9;digit++)
                {
                    boolean ok=checkPosition(digit,row,col,puzzle);
                    if (ok){
                        puzzle.setLocation(row,col,digit);
                        findSolutions(row,col,puzzle);
                        if(puzzle.getNumZero()>0)
                        {
                            puzzle.setLocation(row,col,0);
                        }
                    }
                }
            }
        }
        return puzzle.getNumZero();
    }

    private static boolean checkPosition(int digit,int row,int col, SudokuPuzzle puzzle)
    {
        //check row 
        for (int x=0;x<puzzle.getSize();x++){
            if(puzzle.getLocation(x,col)==digit)
                return false;
        }
        //check column
        for (int y=0;y<puzzle.getSize();y++){
            if (puzzle.getLocation(row,y)==digit)
                return false;
        }
        col=col/3*3;
        row=row/3*3;
        //check block
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++){
                if(puzzle.getLocation(row+x,col+y)==digit)
                    return false;
            }
        }
        return true;
    }
}
