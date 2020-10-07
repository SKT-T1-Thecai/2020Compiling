import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class program{
    public static  boolean isInt(char s)
    {
        return s>='0'&&s<='9';
    }
    public static boolean isAlpha(char s)
    {
        return (s>='a'&&s<='z')||(s>='A'&&s<='Z');
    }
    public static void analyse(char[] str)
    {
        char[] stack=new char[str.length];
        System.out.println(str);
     int pos=0;
     int sign=0;
     for(int i=0;i<str.length;i++)
     {
         if(str[i]=='\r'&&str[i+1]=='\n')
         {
             i++;
             continue;
         }
         if(pos==0)//stack为空
         {
             stack[pos++]=str[i];
             if(isInt(stack[0]))
                 sign=1;
             else if(isAlpha(stack[0]))
                 sign=2;
             else sign=3;
         }else{
            if(sign==1&&!isInt(str[i]))
            {
                System.out.println("Int("+new String(stack).substring(0,pos)+")");
                pos=0;
                sign=0;
                i--;
                continue;
            }
            else if(sign==2&&!isInt(str[i])&&!isAlpha(str[i]))
            {
                System.out.println("Ident("+new String(stack).substring(0,pos)+")");
                pos=0;
                i--;
                continue;
            }
            else stack[pos++]=str[i];
         }


         String test= new String(stack).substring(0,pos);
         if(test.equals("BEGIN"))
         {
             System.out.println("Begin");
             pos=0;
             continue;
         }
         if(test.equals("END"))
         {
             System.out.println("End");
             pos=0;
             continue;
         }
         if(test.equals("FOR"))
         {
             System.out.println("For");
             pos=0;
             continue;
         }
         if(test.equals("IF"))
         {
             System.out.println("If");
             pos=0;
             continue;
         }
         if(test.equals("THEN"))
         {
             System.out.println("Then");
             pos=0;
             continue;
         }
         if(test.equals("ELSE"))
         {
             System.out.println("Else");
             pos=0;
             continue;
         }
         if(test.equals(":")&&str[i+1]!='=') {
             System.out.println("Colon");
             pos=0;
             continue;
         }
         if(test.equals("+"))
         {
             System.out.println("Plus");
             pos=0;
             continue;
         }
         if(test.equals("*"))
         {
             System.out.println("Star");
             pos=0;
             continue;
         }
         if(test.equals(","))
         {
             System.out.println("Comma");
             pos=0;
             continue;
         }
         if(test.equals("("))
         {
             System.out.println("LParenthesis");
             pos=0;
             continue;
         }
         if(test.equals(")"))
         {
             pos=0;
             System.out.println("RParenthesis");
             continue;
         }
         if(test.equals(":="))
         {
             System.out.println("Assign");
             pos=0;
             continue;
         }


     }



    }
    public static void main(String[] args) throws IOException {
        String Path="D:\\IDEA projects\\work10_9\\test.txt";
        File  file=new File(Path);
        FileInputStream f=new FileInputStream(file);
        byte[] buf = new byte[4096];
        while(f.read(buf) !=-1){
           //System.out.println(Arrays.toString(buf));
          char[] str=new char[buf.length];
          int pos=0;
          for(byte b : buf)
          {
                str[pos++]=(char)(b);
          }
          analyse(str);


        }
    }
}
