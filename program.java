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
    public static String work(String s)
    {
        if(s.equals("BEGIN"))
            return "Begin";
        else if (s.equals("END"))
            return "End";
        else if (s.equals("FOR"))
            return "For";
        else if (s.equals("IF"))
            return "If";
        else if (s.equals("THEN"))
            return "Then";
        else if (s.equals("ELSE"))
            return "Else";
        else if(isAlpha(s.charAt(0)))
            return "Ident("+s+")";
        else if(s.equals(":"))
            return "Colon";
        else if(s.equals(":="))
            return "Assign";
        else {

            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)!='0'||i==s.length()-1)
                    return "Int("+s.substring(i)+")";
            }


        }
        return "";
    }

    public static void analyse(char[] str) {
        String s = new String(str);
        String[] arr = s.split("\\s+");
        int sign = 0;
        int pos=0;
        for (int i = 0; i < arr.length; i++) {

            char[] ch=arr[i].toCharArray();// 001456asf
            char[] stack=new char[ch.length];
            for(int j=0;j<ch.length;j++)
            {
            //   System.out.println(j+" "+pos);
                if(pos==0)
                {
                    if(isAlpha(ch[j]))
                    {
                        stack[pos++]=ch[j];
                        sign=1;
                    }
                    else if(isInt(ch[j]))
                    {
                        stack[pos++]=ch[j];
                        sign=2;
                    }
                    else if(ch[j]==':')
                    {
                        stack[pos++]=ch[j];
                        sign=3;
                    }
                    else if(ch[j]=='+')
                    {
                        System.out.println("Plus");
                        pos=0;
                        continue;

                    }
                    else if(ch[j]=='*')
                    {
                        System.out.println("Star");
                        pos=0;
                        continue;

                    }
                    else if(ch[j]==',')
                    {
                        System.out.println("Comma");
                        pos=0;
                        continue;

                    }
                    else if(ch[j]=='(')
                    {
                        System.out.println("LParenthesis");
                        pos=0;
                        continue;

                    }
                    else if(ch[j]==')')
                    {
                        System.out.println("RParenthesis");
                        pos=0;
                        continue;

                    }
                    else {
                        System.out.println("Unknown");
                        return;
                    }

                }
                else
                    {
                        if(sign==1)
                    {
                        if(!isInt(ch[j])&&!isAlpha(ch[j]))
                        {
                            System.out.println(work(new String(stack).substring(0,pos)));
                            pos=0;
                            j-=1;
                            continue;
                        }
                        else stack[pos++]=ch[j];
                    }
                    else if(sign==2)
                    {
                        if(!isInt(ch[j]))
                        {

                            System.out.println(work(new String(stack).substring(0,pos)));
                            //System.out.println(j);
                            pos=0;
                           j-=1;
                            continue;
                        }
                        else stack[pos++]=ch[j];
                    }
                    else if(sign==3)
                    {
                        if(ch[j]!='=')
                        {
                            System.out.println("Colon");
                            pos=0;
                            j-=1;
                            //continue;
                        }else {
                            System.out.println("Assign");
                            pos=0;
                           // continue;
                        }

                    }

                }
                if(j==ch.length-1&&pos!=0)
                {
                    System.out.println(work(new String(stack).substring(0,pos)));
                    pos=0;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
     
        String Path=args[0];
        File  file=new File(Path);
        FileInputStream f=new FileInputStream(file);
        byte[] buf = new byte[4096];
        int len=0;
        while((len=f.read(buf)) !=-1){
            //System.out.println(Arrays.toString(buf));

            char[] str=new char[len];
            int pos=0;
            for(int i=0;i<len;i++)
            {
                str[pos++]=(char)buf[i];
            }
            analyse(str);
        }
    }
}
