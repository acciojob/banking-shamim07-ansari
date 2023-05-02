package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name= name;
        this.balance = balance;
        this.minBalance= minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception {
        if (sum > 9 * digits)
            throw new Exception("Account Number can not be generated");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            if (sum >= 9) {
                str.append('9');
                sum -= 9;
            } else {
                str.append(sum);
                sum = 0;
            }
        }
        return str.toString();
    }


    //Each digit of an account number can lie between 0 and 9 (both inclusive)
    //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
    //If it is not possible, throw "Account Number can not be generated" exception


    public void deposit(double amount) {
        this.balance +=amount;
        //add amount to balance

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.minBalance < this.balance - amount)
            throw new Exception("Insufficient Balance");
        this.balance -=amount;
    }
    public double getBalance(){
        return balance;
    }

}