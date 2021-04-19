package com.company;
import com.sun.javaws.IconUtil;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.String;
import java.io.FileReader;


public class Functions {


    String[] line;

    int size = 0;


    //====================COLORS================================
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    String Lines[];
    void read() throws IOException
    {


        FileReader reader = new FileReader("file.txt");
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));
        String s;
        Lines = new String[50];
        int i =0;
        while((s=br.readLine())!=null){

           Lines[i]=s;
           i++;
        }


        System.out.println("Comments: "+ANSI_GREEN+" green"+ANSI_RESET);
        System.out.println("Numbers: "+ANSI_BLUE+" BLUE"+ANSI_RESET);
        System.out.println("reserved words: "+ANSI_CYAN+" CYAN"+ANSI_RESET);
        System.out.println("operators: "+ANSI_YELLOW+" YELLOW"+ANSI_RESET);
        System.out.println("sign: "+ANSI_WHITE+" WHITE"+ANSI_RESET);

       /* for(i=0;i<20;++i)
        {
            System.out.println("line "+i+"   "+Lines[i]);
        }*/
   // System.out.println(fileContent);
    }

    void print(char col,char str[], int size,int pos)
    {
        switch(col)
        {
            case 'b':
                break;
            case 'r':
                break;
            case 'g': //comments
                for(int i =0;i<size;++i)
                {
                    if(i>= pos)
                    {
                        System.out.print(ANSI_GREEN+str[i]+ANSI_RESET);

                    }
                    else
                    {
                        System.out.print(ANSI_RESET+str[i]+ANSI_RESET);
                    }

                }
                System.out.println('\n');
                break;
            case 'y':
                break;
            case 'B': //blue    Numbers

                break;
            case 'p':
                break;
            case 'c':
                break;
            case 'w':
                break;

        }


    }


    void analysis()
    {
        String line;
        int sizeCh,j=0,k=0,g=0,indexCom=-1,stop=0;
        String[] words;
        String word;
        char[] ch;
        int in = 0;
        char[] num={'0','1','2','3','4','5','6','7','8','9',};
        String[] reservedWords = new String[] {
                "byte","short","int","long","char","boolean","float","double","boolean","if","else",
                "switch","case","default","while","do","break","continue","for","try","catch","finally",
                "throw","throws","private","protected","public","import","package","class","interface","extends","implements",
                "static","final","void","abstract","native","new","return","this","super","synchronized","synchronized",
                "const","goto"};
        String[] operators = new String[] {"+","-","*","/","++","--","+=","-=","==","!=",">","<","<=",">=","&&","||","="};
        String[] znak = new String[] {".",",",";",":","'","!","?","==","!=",">","<","<=",">=","&&","||","="};
        String[] indef = new String[] {"int","double","char","float","boolean","long"};
       for (int i =0;i< Lines.length;++i)
       {
           line=Lines[i];
            in =1;
        char f= '"';

           words = line.split(" ");
           for(j=0;j< words.length;++j)
           {
            word = words[j];

             //comments
                if(stop==1)
                {
                    stop =0;
                    break;

                }
                in=1;

                while (in > 0)
                {
                    switch(in)
                    {
                        case 1:
                                //================comments==================
                                 indexCom =word.indexOf("//");
                                if(indexCom > -1)
                                {
                                    for(k=j;k<words.length;++k)
                                    {
                                        System.out.print(ANSI_GREEN+ " " +words[k]+ " "+ANSI_RESET);
                                    }
                                    indexCom = -1;
                                    in=0;
                                    stop=1;
                                }
                                else
                                    in=2;
                            break;
                        case 2:
                                //=====================NUM===================
                                if(word.matches("-?\\d+(\\.\\d+)?"))
                                {
                                    System.out.print(ANSI_BLUE+' '+word+ANSI_RESET);
                                    in=0;
                                    break;
                                }
                                else
                                    in=3;
                            break;
                        case 3:
                                //==============reserved words====================
                                for(k=0;k < reservedWords.length;++k)
                                {

                                    if (word.equals(reservedWords[k]))
                                    {
                                        System.out.print(ANSI_CYAN+ " " +word+ " "+ANSI_RESET);
                                        in =0;
                                        break;
                                    }


                                }
                                for(k=0;k<indef.length;++k)
                                {
                                    if (word.equals(indef[k]))
                                    {
                                        System.out.print(ANSI_PURPLE+ " " +words[j+1]+ " "+ANSI_RESET);
                                    j++;
                                    }
                                }
                                if(in>0)
                                {
                                    in=4;
                                }

                                break;
                        case 4:
                            //=====================operators===================
                            for(k=0;k < operators.length;++k)
                            {

                                if (word.equals(operators[k]))
                                {
                                    System.out.print(ANSI_YELLOW+ " " +word+ " "+ANSI_RESET);
                                    in =0;
                                }

                            }
                            if(in>0)
                            {
                                in=5;
                            }
                            break;
                        case 5:
                            //=====================znak===================
                            char lap = '"';

                            for(k=0;k < znak.length;++k)
                            {

                                if (word.equals(znak[k]) || word.equals("\""))
                                {
                                    System.out.print(ANSI_WHITE+ " " +word+ " "+ANSI_RESET);
                                    in =0;
                                    break;
                                }

                            }
                            if(in>0)
                            {
                                in=6;
                            }
                            break;
                        case 6:
                            in=0;
                            System.out.print(' ' +word+' ');
                            break;

                        default:

                            break;
                    }
                }









           }


           System.out.print("\n"+ANSI_RESET);







       }

    }
}
