import java.io.*;
import java.util.Scanner;
public class WeightedGrades
/** 
 * Mackenzie O'Brien
 * AP Computer Science pd 6 Blackstone
 * 3/16/15
 * 2D Array Take home 
 */
{
    public static void main(String[] args)
    {
        double[][] weights = new double[6][2];
        for (int z=0;z<4;z++){
            weights[z][0]=0.2;
            weights[z][1]=0.22;
        }
        for (int c=0;c<2;c++){
            weights[c][0]=0.1;
            weights[c][1]=0.06;
        }
        double[][] grades = new double[20][6];
        String[] names = new String[20];
        int num = readNamesAndGrades(names,grades);       
        double[][] finals = findAverages(grades,weights,num);
        printGrades(names,grades,finals,num);
    }

    private static int readNamesAndGrades(String[] names, double[][] grades)
    {
        Scanner s=new Scanner(System.in), inf = null;
        boolean valid=true;
        do
        {
            valid = true;
            try
            {
                System.out.print("Please enter file name: ");
                String fname = s.next();
                inf = new Scanner(new File("h:\\"+fname+".txt"));
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found");
                valid=false;
            }
        }
        while (!valid);

        int num=0;
        while (inf.hasNext()){
            String input=inf.nextLine();
            String [] parts=input.split(" ");
            names[num]=parts[0]+ parts[1];
            for (int x=0;x<6;x++)
            grades[num][x]=Double.parseDouble(parts[x+2]);
            num++;
        }
        inf.close();
        return num;
    }

    private static double[][] findAverages(double[][] grades, double[][] weights, int num)
    {
        double[][] finalAverages = new double[num][2];
        for (int x=0;x<num;x++)
            for (int y=0;y<6;y++){
                finalAverages[x][0]+=grades[num][y]*weights[y][0];
                finalAverages[x][1]+=grades[num][y]*weights[y][1];
            }
        return finalAverages;
    }

    private static void printGrades(String[] names, double[][] grades,double[][] finalGrades, int num)
    {
        char[] letterGrade = new char[2];
        int count = 0;
        System.out.println("Name\tQ1\tQ2\tQ3\tQ4\tME\tFE\tEHS\tGD1\tOTHER\tGD2");
        for (int x=0;x<num;x++){
            System.out.print(names[x]+ "\t");
            for (int f=0;f<6;f++){
                System.out.print(grades[x][f] +"\t");
                //a or b range
                if (finalGrades[x][f]>=89.5 && finalGrades[x][f]<91.5)
                    count++;
                //b or c range
                else if (finalGrades[x][f]>=79.5 && finalGrades[x][f]<83.5)
                    count++;
                //c or d range
                else if(finalGrades[x][f]>=69.5 && finalGrades[x][f]<73.5)
                    count++;
                //d or f range
                else if (finalGrades[x][f]>=59.5 && finalGrades[x][f]<63.5)
                    count++;
            }
            System.out.print(finalGrades[x][0] + "\t" + finalGrades[x][1]);
            System.out.println();
        }

        System.out.println("The number of different grades was " + count +".");    
    }
}
