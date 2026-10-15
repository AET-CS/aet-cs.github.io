---
title: Bank Account Class
header-includes:
    - \usepackage{fullpage}
---


```java
public class BankAccount {
  private String owner;
  private double balance;

  public String nickname;
  private final int accountNumber;              // final means it can't change

  private static int numAccounts = 0;          // how many accounts have been created
  private static double interestRate = 0.02;   // same rate for every account
  public static final double MIN_BALANCE = 0.0;

  public BankAccount(String owner, double startingBalance) {
    this.owner = owner;
    this.balance = startingBalance;
    this.nickname = "";
    numAccounts++;                            // update the shared counter
    this.accountNumber = numAccounts;         // use it to give each account a unique number
  }

  public BankAccount(String owner) {
    this.owner = owner;
    this.balance = 0;
    this.nickname = "";
    numAccounts++;
    this.accountNumber = numAccounts;         // use it to give each account a unique number
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String newOwner) {
    owner = newOwner;
  }

  public double getBalance() {
    return balance;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
    }
  }

  public boolean withdraw(double amount) {
    if (amount > 0 && balance - amount >= MIN_BALANCE) {
      balance -= amount;
      return true;
    }
    return false;
  }

  public boolean transferTo(BankAccount other, double amount) {
    if (withdraw(amount))                     // calling another method of this object
    {
      other.deposit(amount);
      return true;
    }
    return false;
  }

  public void addInterest() {
    balance += computeInterest();
  }

  private double computeInterest() {
    return balance * interestRate;            // instance method reading a static field
  }

  public static int getNumAccounts() {
    return numAccounts;
  }

  public static void setInterestRate(double rate) {
    interestRate = rate;                      // changes the rate for EVERY account
  }

  public boolean equals(BankAccount other) {
    return this.accountNumber == other.accountNumber;
  }

  public String toString() {
    return "Account #" + accountNumber + " (" + owner + "): $" + String.format("%.2f", balance);
  }




  static void main(String[] args) {
    BankAccount a = new BankAccount("Ada", 100.0);
    BankAccount b = new BankAccount("Grace");          // uses the other constructor

    System.out.println(a);                              // toString is called automatically
    System.out.println(b);
    System.out.println("Accounts created: " + BankAccount.getNumAccounts());

    a.transferTo(b, 40.0);
    System.out.println("After transfer:");
    System.out.println(a);
    System.out.println(b);

    System.out.println("Withdraw $500 from b? " + b.withdraw(500.0));

    BankAccount.setInterestRate(0.10);                  // affects both accounts
    a.addInterest();
    b.addInterest();
    System.out.println("After 10% interest:");
    System.out.println(a);
    System.out.println(b);

    a.nickname = "rainy day fund";                      // public field: no getter needed
    System.out.println("a's nickname: " + a.nickname);
  }
}
```