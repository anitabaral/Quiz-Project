/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANITA
 */
import java.util.Scanner;
public class String1 {

    /**
     * @param args the command line arguments
     */
     public static String removeCharAt(String s, int pos) {
      return s.substring(0, pos) + s.substring(pos + 1);
     }
    public static void main(String[] args) {
        // TODO code application logic here
    Scanner scan=new Scanner(System.in);
    int count=0;
    String u=scan.next();
    String v=scan.next();
    String s=u.toLowerCase();
    String t=v.toLowerCase();
    int m=s.length();
    int n=t.length();
    int b=m;
    int c=n;
    
    int o=m+n;
   
    for(int i=0;i<b;i++){
         if(m>n){
             s=removeCharAt(s,0);
             
             count++;
             m--;
    }
         else 
             break;
    }
    m=b;
    
    for(int i=0;i<c;i++){
        if(m<n){
            t=removeCharAt(t,0);
            n--;
            count++;
            
        }
        break;
    }
    n=c;
    for(int i=0;i<o;i++){
        if(s.equals(t))
            break;
            else{
            count=count+2;
             s=removeCharAt(s,0);
             t=removeCharAt(t,0);
        }
    }
    
    if(s.equals(t))
        System.out.println(count);
    else
        System.out.print(o);
    }
    
}
