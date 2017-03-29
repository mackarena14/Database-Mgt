import java.util.*;
/**
 * Write a description of class Isola here.
 * Mackenzie O'Brien
 * 3.2.15
 */
public class Isola
{
    public static void main()
    {
        char again='y';
        while(again=='y'){
            String[][] board=new String[7][7];
            Scanner s=new Scanner(System.in);
            //initialize board spots to numbers
            board[0][0]="1"; board[0][1]="2";board[0][2]="3";board[0][3]="4";board[0][4]="5";board [0][5]="6"; board[0][6]="7";
            board[1][0]="8"; board[1][1]="9";board[1][2]="10";board[1][3]="11";board[1][4]="12";board [1][5]="13"; board[1][6]="14";
            board[2][0]="15"; board[2][1]="16";board[2][2]="17";board[2][3]="18";board[2][4]="19";board [2][5]="20"; board[2][6]="21";
            board[3][0]="22"; board[3][1]="23";board[3][2]="24";board[3][3]="25";board[3][4]="26";board [3][5]="27"; board[3][6]="28";
            board[4][0]="29"; board[4][1]="30";board[4][2]="31";board[4][3]="32";board[4][4]="33";board [4][5]="34"; board[4][6]="35";
            board[5][0]="36"; board[5][1]="37";board[5][2]="38";board[5][3]="39";board[5][4]="40";board [5][5]="41"; board[5][6]="42";
            board[6][0]="43"; board[6][1]="44";board[6][2]="45";board[6][3]="46";board[6][4]="47";board [6][5]="48"; board[6][6]="49";
            System.out.println();
            //set positions of players
            System.out.print("Position of player 1?: ");
            int pos1=s.nextInt();
            System.out.print("Position of player 2?: ");
            int pos2=s.nextInt();
            int rpos1=0; int rpos2=0; int cpos2=0; int cpos1=0;
            rpos1=pos1/7;
            rpos2=pos2/7;
            cpos1=pos1%7-1;
            cpos2=pos2%7-1;
            if (pos1%7==0){
                cpos2=6;
                rpos1=rpos1-1;
            }
            if (pos2%7==0){
                cpos2=6;
                rpos2=rpos2-1;
            }
            board[rpos1][cpos1]="*";
            System.out.println(rpos1 + " "+ cpos1);
            System.out.println(rpos2 +" "+ cpos2);
            board[rpos2][cpos2]="@";
            for (int x=0;x<7;x++){
                System.out.println();
                for ( int y=0;y<7;y++)
                    System.out.print(board[x][y]+ "\t");
            }

            //set tiles blank from inputted list
            int g=1;
            while (g!=0)
            {
                System.out.print("spot already taken?: ");
                g=s.nextInt();
                if (g!=0){
                    if (g>42)
                        board[6][g-43]="taken";
                    else if (g<43 && g>35)
                        board[5][g-36]="taken";
                    else if (g<36 && g>28)
                        board[4][g-29]="taken";
                    else if (g<29 && g>21)
                        board[3][g-22]="taken";
                    else if (g<22 && g>14)
                        board[2][g-15]="taken";
                    else if (g<15 && g>7)
                        board[1][g-8]="taken";
                    else if (g<8)
                        board[0][g-1]="taken";
                }
            }

            String[] output1=new String[7];int count1=0; String[] temp=new String[7];int a=0;
            //check left
            if (cpos2-1>-1){
                for (int x=cpos2-1;x>-1;x--)
                {
                    if (board[rpos2][x].equals("taken") || board[rpos2][x].equals("*"))
                        break;
                    else{
                        temp[count1]=board[rpos2][x];
                        count1++;
                    }
                }
            }
            //reverse array order so that it reads 123 instead of 321
            for (int x=count1-1;x>-1;x--){
                output1[a]=temp[x];
                a++;
            }

            //check right
            String[] output2=new String[7]; int count2=0;
            if (cpos2+1<7){
                for (int x=cpos2+1;x<7;x++)
                {
                    if (board[rpos2][x].equals("taken") || board[rpos2][x].equals("*"))
                        break;
                    else{
                        output2[count2]=board[rpos2][x];
                        count2++;
                    }
                }
            }

            //check vertically up
            String[] output3=new String[7]; int count3=0;
            if (rpos2-1>-1){
                for (int x=rpos2-1;x>-1;x--)
                {
                    if (board[x][cpos2].equals("taken")|| board[x][cpos2].equals("*"))
                        break;
                    else{
                        output3[count3]=board[x][cpos2];
                        count3++;
                    }
                }
            }
            //check vertical down
            String[] output4=new String[7]; int count4=0;
            if (rpos2+1<7){
                for (int x=rpos2+1;x<7;x++)
                {
                    if (board[x][cpos2].equals("taken")|| board[x][cpos2].equals("*"))
                        break;
                    else{
                        output4[count4]=board[x][cpos2];
                        count4++;
                    }
                }
            }

            //diagonal bottom left 
            int count5=0;String[]output5=new String[7]; int j=1;
            //row increase column decrease
            while (rpos2+j<7 && cpos2-j>-1) {
                if (board[rpos2+j][cpos2-j].equals("taken")|| board[rpos2+j][cpos2-j].equals("*"))
                    break;
                else{
                    output5[count5]=board[rpos2+j][cpos2-j];
                    count5++;
                }
                j++;
            }

            //dijgonal top left 
            int count6=0; String[] output6=new String[7]; int u=1;
            while (rpos2-u<-1 && cpos2+u<7) {
                if (board[rpos2-u][cpos2-u].equals("taken")|| board[rpos2-u][cpos2-u].equals("*"))
                    break;
                else{
                    output6[count6]=board[rpos2-u][cpos2-u];
                    count6++;
                }
                u++;
            } 

            //diagonal top right
            int count7=0; String[] output7=new String[7]; int b=1;
            // row decrease column increase
            while (rpos2-b>-1 && cpos2+b<7){
                if (board[rpos2-b][cpos2+b].equals("taken")|| board[rpos2-b][cpos2+b].equals("*"))
                    break;
                else{
                    output7[count7]=board[rpos2-b][cpos2+b];
                    count7++;
                }
                b++;
            } 
            //diagonal bottom right
            int count8=0; String[] output8=new String[7]; int c=1;
            //row increase column increase
            while (rpos2+c<7 && cpos2+c<7) {
                if (board[rpos2+c][cpos2+c].equals("taken")|| board[rpos2+c][cpos2+c].equals("*"))
                    break;
                else{
                    output8[count8]=board[rpos2+c][cpos2+c];
                    count8++;
                }
                c++;
            } 
            //compare counts to see which way is the longest
            System.out.println();
            if( count1>count2 && count1>count3 && count1>count4 && count1>count5 
            && count1>count6 && count1>count7 && count1>count8)
                for (int x=0; x<count1;x++){
                    System.out.print(output1[x]);
                    if (x<count1-1)
                        System.out.print( ", ");
                }
            else  if( count2>count1 && count2>count3 && count2>count4 && count2>count5 
            && count2>count6 && count2>count7 && count2>count8)
                for (int x=0; x<count2;x++){
                    System.out.print(output2[x]);
                    if (x<count2-1)
                        System.out.print( ", ");
                }
            else if(count3>count2 && count3>count1 && count3>count4 && count3>count5 
            && count3>count6 && count3>count7 && count3>count8)
                for (int x=0; x<count3;x++){
                    System.out.print(output3[x]);
                    if (x<count3-1)
                        System.out.print( ", ");
                }
            else if(count4>count2 && count4>count3 && count4>count1 && count4>count5
            && count4>count6 && count4>count7 && count4>count8)
                for (int x=0; x<count4;x++){
                    System.out.print(output4[x]);
                    if (x<count4-1)
                        System.out.print( ", ");
                }   
            else if (count5>count1 && count5>count2 && count5>count3 && count5>count4 
            && count5>count6 && count5>count7 && count5>count8)
                for (int x=0; x<count5;x++){
                    System.out.print(output5[x]);
                    if (x<count5-1)
                        System.out.print( ", ");
                }
            else if (count6>count1 && count6>count2 && count6>count3 && count6>count4 
            && count6>count5 && count6>count7 && count6>count8)
                for (int x=0; x<count6;x++){
                    System.out.print(output6[x]);
                    if (x<count6-1)
                        System.out.print(", ");
                }
            else if (count7>count1 && count7>count2 && count7>count3 && count7>count4 
            && count7>count5 && count7>count6 && count7>count8)
                for (int x=0; x<count7;x++){
                    System.out.print(output7[x]);
                    if (x<count7-1)
                        System.out.print(", ");
                }
            else if (count8>count1 && count8>count2 && count8>count3 && count8>count4
            && count8>count5 && count8>count7 && count8>count6)
                for (int x=0; x<count8;x++){
                    System.out.print(output8[x]);
                    if (x<count8-1)
                        System.out.print(", ");
                }
            else if (count1==0 && count2==0&& count3==0 
            && count4==0 && count5==0&& count6==0)
                System.out.print("NONE");
            System.out.println();
            //enables program to run as many times as needed
            System.out.print("run again?: ");
            again=s.next().charAt(0);
        }
    }
}
