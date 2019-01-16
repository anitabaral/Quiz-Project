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
public class ScannerTest{  
 public static void main(String args[]){  
   Scanner sc=new Scanner(System.in);  
     
   System.out.println("Enter your rollno");  
   int rollno=sc.nextInt();  
   System.out.println("Enter your name");  
   String name=sc.next();  
   System.out.println("Enter your fee");  
   double fee=sc.nextDouble();  
   System.out.println("Rollno:"+rollno+" name:"+name+" fee:"+fee);  
   sc.close();  
 }  
}   