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
        System.out.println("1.  " + answer1);
        //lisp 2
        System.out.print("Please enter second expression");
        String lisp2=s.nextLine();
        String answer2="'(";
        lisp2=lisp2.substring(2,lisp2.length()-1);
        char[] letters2=new char[40];
        int[]counts2=new int[40];
        for (int i=0;i<40;i++)
            counts2[i]=1;
        int countB=0;
        int count2lists=0;
        for (int u=0;u<lisp2.length();u=u+2){
            letters2[countB]=lisp2.charAt(u);
            countB++;
        }
        for (int o=0;o<=countB;o++){
            if(letters2[o]==letters2[o+1])
                counts2[count2lists]++;
            else {
                answer2+="("+counts2[count2lists]+ " "+ letters2[o]+")";
                count2lists++;
            }
        }
        answer2+=")";
        System.out.println("2.  " + answer2);

        //lisp3
        System.out.print("Please enter third expression");
        String lisp3=s.nextLine();
        String answer3="'(";
        lisp3=lisp3.substring(2,lisp3.length()-1);
        char[] letters3=new char[40];
        int[]counts=new int[40];
        for (int i=0;i<40;i++)
            counts[i]=1;
        int count=0;
        int countlists=0;
        for (int u=0;u<lisp3.length();u=u+2){
            letters3[count]=lisp3.charAt(u);
            count++;
        }
        for (int o=0;o<=count;o++){
            if(letters3[o]==letters3[o+1])
                counts[countlists]++;
            else {
                if(counts[countlists]==1)
                    answer3+=letters3[o];
                else
                {
                    answer3+="("+counts[countlists]+ " "+ letters3[o]+")";
                    countlists++;
                }
            }
        }
        answer3+=")";
        System.out.print("3.  " + answer3);

        //lisp 4
        System.out.print("Please enter fourth expression");
        String lisp4=s.nextLine();
        String answer4="'(";  
        lisp4=lisp4.substring(2,lisp4.length()-1);
        int n=lisp4.charAt(lisp4.length()-1)-48;
        lisp4=lisp4.substring(0,lisp4.length()-1);
        String[] lists4=new String[20];
        int startpos4=0;
        int q=0;

        int endpos4=6;
        while (startpos4<=lisp4.length()-6)
        {
            lists4[q]=(lisp4.substring(startpos4,endpos4));  
            startpos4=endpos4;
            endpos4+=6;
            q=q+1;
        }
        for(int p=0;p<q;p=p+2){
            answer4+=lists4[p];
        }
        answer4=answer4.trim();
        System.out.print("4.  "+answer4+ " " + n+ ")");
        //lisp 5
        System.out.print("Please enter last expression");
        String lisp5=s.nextLine();
        String answer5="'(";
        lisp5=lisp5.substring(2,lisp5.length()-1);
        int n2=lisp5.charAt(lisp5.length()-1)-48;
        String[] lists5=new String[20];
        int startpos5=0;
        int w=0;
        int endpos5=6;
        while (startpos5<=lisp5.length()-6)
        {
            lists5[w]=(lisp5.substring(startpos5,endpos5));  
            if (w%n2==0 && w>0){
                answer5=answer5.trim();
                answer5+=(") '("+lists5[w]);
            }
            else
                answer5+=(lists5[w]);
            startpos5=endpos5;
            endpos5+=6;
            w++;
        }
        answer5=answer5.trim();
        System.out.print("5.  " + answer5+ " "+n2+ ")");
    }
}
