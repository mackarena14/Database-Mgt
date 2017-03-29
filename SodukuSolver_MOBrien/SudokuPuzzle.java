import java.util.Scanner;
import java.io.*;

/**
 * Write a description of class SudokuBoard here.
 * 
 * Mackenzie O'Brien
 * AP Comp Sci period 6
 * 2.23.15
 */
public class SudokuPuzzle
{
    private int size;
    private int numZero;
    private int[][]puzzle;
    private static Scanner s=new Scanner(System.in);
    public SudokuPuzzle(int num)
    {
        size=num;
        numZero=0;
        puzzle=new int [num][num];
    }

    public  void getPuzzle()
    {
        boolean valid=true;
        Scanner s = new Scanner(System.in), inf = null;

        do
        {
            valid = true;
            try
            {
                System.out.print("Please enter file name");
                String fname = "";
                inf = new Scanner(new File("h:\\"+fname+".txt"));
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found");
                valid=false;
            }
        }
        while (!valid);
        for (int x=0;x<size;x++)
        {
            for (int y=0;y<size;y++){
                puzzle[x][y]=inf.nextInt();
                if (puzzle[x][y]==0)
                    numZero++;
            }
        }
        inf.close();
    }

    public  void printPuzzle()
    {
        String str="";
        for (int x=0;x<size;x++){
            for (int y=0;y<size;y++){
                str+=puzzle[x][y]+" ";
            }
            str+= "\n";
        }
        System.out.println(str);
    }

    public void setLocation(int row, int col,int num)
    {
        puzzle[row][col]=num;
        if (num==0)
            numZero++;
        else 
            numZero--;
    }

    public  int getLocation(int num, int num2)
    {
        return puzzle[num][num2];
    }

    public int getSize()
    {
        return size;
    }
    public int getNumZero()
    {
        return numZero;
    }
}
