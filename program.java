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
        else return "Int("+s+")";


    }
    public static void analyse(char[] str) {
        System.out.println(str);
        String s = new String(str);

        String[] arr = s.split("\\s+");
        int sign = 0;
        for (int i = 0; i < arr.length; i++) {

            char[] ch = arr[i].toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (j == 0) {
                    if (isAlpha(ch[j]))
                        sign = 1;
                    else if (isInt(ch[j]))
                        sign = 2;
                    else if (ch[j] == '+' && ch.length == 1) {
                        System.out.println("Plus");
                        break;
                    } else if (ch[j] == '*' && ch.length == 1) {
                        System.out.println("Star");
                        break;
                    } else if (ch[j] == ',' && ch.length == 1) {
                        System.out.println("Comma");
                        break;
                    } else if (ch[j] == '(' && ch.length == 1) {
                        System.out.println("LParenthesis");
                        break;
                    } else if (ch[j] == ')' && ch.length == 1) {
                        System.out.println("RParenthesis");
                        break;
                    } else if (ch[j] == ':' && ch.length == 2 && ch[j + 1] == '=') {
                        System.out.println("Assign");
                        break;
                    } else if (ch[j] == ':' && ch.length == 1) {
                        System.out.println("Colom");
                        break;
                    } else {
                        System.out.println("Unknown");
                        return;
                    }
                } else {
                    if (sign == 1) {
                        if (!isAlpha(ch[j])&&!isInt(ch[j]))
                        {
                            System.out.println("Unknown");
                            return;
                        }
                    }
                    else
                    {
                        if(isAlpha(ch[j]))
                        {
                            System.out.println(work(new String(ch).substring(0,j)));
                            ch=new String(ch).substring(j,ch.length).toCharArray();
                            j=-1;
                            continue;
                            //analyse(new String(ch).substring(j+1,ch.length+1).toCharArray());
                        }
                    }
                }
                if(j==ch.length-1)
                {
                    System.out.println(work(new String(ch)));
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
