import java.util.Scanner;
/**
 * Write a description of class Lisp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lisp
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.print("Please enter first expression");
        String lisp1=s.nextLine();
        lisp1=lisp1.substring(2,lisp1.length()-1);
        //lisp 1
        String answer1="'(";
        for (int x=lisp1.length();x>=1;x--)
        {
            answer1+=lisp1.substring(x-1,x);
        }
        answer1+=")";
        System.out.print("1." + answer1);
        //lisp 2
        System.out.print("Please enter second expression");
        String lisp2=s.nextLine();
        int length2=lisp2.length();
        String answer2="'(";
        lisp2=lisp2.substring(3,length2);
        int count=0;
        for(int y=0;y<lisp2.length();y++){
            if (lisp2.charAt(y)==(lisp2.charAt(y+2)))
                count++;
            else if (lisp2.charAt(y)!=(lisp2.charAt(y+2)))
                answer2="'(("+count +" " +lisp2.charAt(y)+ " ) ";
        }
        answer2+=")";
        System.out.print("2." + answer2);
        //lisp3
        System.out.print("Please enter third expression");
        String lisp3=s.nextLine();
        String answer3="'(";
        int length3=lisp3.length();
        lisp3=lisp3.substring(3,length3);
        int count2=0;
        for(int z=0;z<lisp3.length();z++){
            if (lisp3.charAt(z)==(lisp3.charAt(z+2)))
                count2++;
            else if (lisp3.charAt(z)!=(lisp3.charAt(z+2)))
                if (count2==1)
                    answer3=lisp2.charAt(z)+ " ";
                else if (count2>1)
                    answer3="("+count +" " +lisp3.charAt(z)+ " ) ";
        }
        answer3+=")";
        System.out.print("3." + answer3);
        //lisp 4 
        System.out.print("Please enter fourth expression");
        String lisp4=s.nextLine();
        String answer4="'(";
        int length4=lisp4.length();
        int n=lisp4.charAt(length4-1);
        lisp4=lisp4.substring(3,length4-2);
        int count3=1;
        int j=0;
        String[]lisp4array=new String[10];
        for (int g=0;g<10;g++){
            lisp4array[g]=lisp4.substring(j,j+4);
            j=j+4;
            if (count3%2!=0){
                answer4+=lisp4array[g];
                count3++;
            }
            else if (count3%2==0)
                count3++;
        }
        answer4+=" "+ n+")";
        System.out.print("4." + answer4);
        //lisp 5 unfinished
        System.out.print("Please enter last expression");
        String lisp5=s.nextLine();
        String answer5="'(";
        int length5=lisp5.length();
        int n2=lisp5.charAt(length5-1);
        lisp5=lisp5.substring(3,length5);
        int count4=1;
        int k=0;
        String[]lisp5array=new String[10];
        for (int h=0;h<10;h++){
            lisp5array[h]=lisp5.substring(k,k+4);
            k=k+4;
        }
        for (int t=1;t<=lisp5.length()/4;t++)
            if (t%n!=0)
                answer5+=lisp5array[t-1];
            else if (t%n==0)
                answer5+=") '(";
        System.out.print("5." + answer5);
    }

}
