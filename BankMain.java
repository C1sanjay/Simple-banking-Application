package com.exercise;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BankMain {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter account number");
        String customerId=input.nextLine();
        System.out.println("Enter user name");
        String customerName=input.nextLine();
        System.out.println("Enter initial balance");
        int balance=input.nextInt();
        //create object for constructor 
        BankAccount ob1=new BankAccount(customerId,customerName,balance);
        ob1.showMenu();
    }
}

class BankAccount{
    public static final String delimiter = ",";
    int balance;
    String customerName;
    String customerId;
    //create constructor of BankAccount class

    BankAccount( String customerId,String customerName,int balance) {
        this.customerId =customerId ;
        this.customerName = customerName;
        this.balance = balance;
        String path="C:/Users/sanju/Documents/account.csv";//file path for writing.

        try
        {
            FileWriter fw=new FileWriter(path,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(customerId+","+customerName+","+balance);
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null,"Record save");

        }catch (Exception E)
        {
            JOptionPane.showMessageDialog(null,"Record not save");

        }
    }
   // create read method for reading csv file 
    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

// deposit method

    void deposit(int amount)
    {
        String mode="";


        int txn_amount=amount;
        String po="C:/Users/sanju/Documents/account_statement.csv";
        if(amount!=0) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter txn_type");
            String txn_type=sc.nextLine();
            System.out.println("Enter mode. 0=cash,1=online");
            int m=sc.nextInt();

            String[] store={"cash","online"};
            if(m==0)
            {

                mode=store[m];
            }
            if(m==1)
            {
                mode=store[m];
            }

            System.out.println("Enter account_number");
            int account_number=sc.nextInt();



            try
            {
                FileWriter fw=new FileWriter(po,true);
                BufferedWriter bw=new BufferedWriter(fw);
                PrintWriter pw=new PrintWriter(bw);
                balance=balance+amount;
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDate = myDateObj.format(myFormatObj);
                pw.println(formattedDate +","+txn_type+","+account_number+","+txn_amount+","+balance+","+mode);
                pw.flush();
                pw.close();
                JOptionPane.showMessageDialog(null,"Record save");

            }catch (Exception E)
            {
                JOptionPane.showMessageDialog(null,"Record not save");

            }
        }

    }
    //withdraw method.
    void withdraw(int amount)
    {
        String mode="";
        if (amount!=0)
        {
            int txn_amount=amount;
            String po="C:/Users/sanju/Documents/account_statement.csv";
            if(amount!=0) {
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter txn_type");
                String txn_type=sc.nextLine();
                System.out.println("Enter mode.0=cash,1=online,2=atm");
                int m=sc.nextInt();
                String[] store={"cash","online","atm"};
                if(m==0)
                {

                    mode=store[m];
                }
                if(m==1)
                {
                    mode=store[m];
                }
                if(m==2)
                {
                    mode=store[m];
                }

                System.out.println("Enter account_number");
                int account_number=sc.nextInt();

                try
                {
                    FileWriter fw=new FileWriter(po,true);
                    BufferedWriter bw=new BufferedWriter(fw);
                    PrintWriter pw=new PrintWriter(bw);
                    balance=balance-amount;
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    String formattedDate = myDateObj.format(myFormatObj);

                    pw.println(formattedDate+","+txn_type+","+account_number+","+txn_amount+","+balance+","+mode);
                    pw.flush();
                    pw.close();
                    JOptionPane.showMessageDialog(null,"Record save");

                }catch (Exception E)
                {
                    JOptionPane.showMessageDialog(null,"Record not save");

                }
            }


        }
    }


    void showMenu()
    {
        char option='\0';
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome"+customerName);
        System.out.println("Your Id is"+customerId);
        System.out.println("\n");
        System.out.println("A. Check balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("S. show statement");
        System.out.println("E. Exit");

        do {

            System.out.println("==========================================");
            System.out.println("Enter an options");
            System.out.println("===========================================");
            option=sc.next().charAt(0);
            System.out.println("\n");
            switch (option)
            {

                case 'A':
                    System.out.println("...........................");
                    System.out.println("Balance"+balance);
                    System.out.println("...........................");
                    System.out.println("\n");
                    break;
                case 'B':
                    System.out.println("...........................");
                    System.out.println("Enter an amount to deposite");
                    System.out.println("...........................");
                    int amount=sc.nextInt();
                    deposit(amount);
                    System.out.println("\n");
                    break;

                case 'C':
                    System.out.println("...........................");
                    System.out.println("Enter an amount to withdraw");
                    System.out.println("...........................");
                    int amount2=sc.nextInt();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;
                case 'S':
                    System.out.println("...........................");
                    System.out.println("Your activity  statement.");
                    String csvFile = "C:/Users/sanju/Documents/account_statement.csv";
                    BankAccount.read(csvFile);
                    System.out.println("...........................");
                    System.out.println("\n");
                    break;


                case 'E':
                    System.out.println("...........................");
                    break;
                default:
                    System.out.println("Invalid options ! Try again");
                    break;
            }

        }while (option!='E');
        System.out.println("Thank you for using our services");


    }
}
