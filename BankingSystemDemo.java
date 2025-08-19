import java.util.Scanner;

// Abstract base class
abstract class AbstractBankList {
    abstract void addAccount(String customerName, double balance);
    abstract void showAccounts();
    abstract boolean deleteAccount(String customerName);

    // Static nested class for AccountNode
    static class AccountNode {
        String customerName;
        double balance;
        AccountNode next;
        AccountNode prev;

        AccountNode(String name, double balance) {
            this.customerName = name;
            this.balance = balance;
            this.next = null;
            this.prev = null;
        }
    }
}

// Doubly linked list for banking system
class DoublyBankList extends AbstractBankList {
    private AccountNode head;

    @Override
    void addAccount(String customerName, double balance) {
        AccountNode newNode = new AccountNode(customerName, balance);
        if (head == null) {
            head = newNode;
        } else {
            AccountNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        System.out.println("Account for \"" + customerName + "\" created successfully.");
    }

    @Override
    void showAccounts() {
        if (head == null) {
            System.out.println("No accounts to display.");
            return;
        }
        AccountNode temp = head;
        System.out.println("Customer Accounts:");
        while (temp != null) {
            System.out.println("-> Customer: " + temp.customerName + ", Balance: $" + temp.balance);
            temp = temp.next;
        }
    }

    @Override
    boolean deleteAccount(String customerName) {
        AccountNode temp = head;
        while (temp != null) {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                } else {
                    head = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                System.out.println("Account for \"" + customerName + "\" deleted successfully.");
                return true;
            }
            temp = temp.next;
        }
        System.out.println("Account for \"" + customerName + "\" not found.");
        return false;
    }
}

// Main menu-driven class
public class BankingSystemDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AbstractBankList bankList = new DoublyBankList();
        int choice;

        do {
            System.out.println("\n==== Banking Menu ====");
            System.out.println("1. Add Customer Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Delete Customer Account");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    while (!sc.hasNextDouble()) {
                        System.out.println("Invalid input! Enter a valid amount.");
                        sc.next();
                    }
                    double balance = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    bankList.addAccount(name, balance);
                    break;

                case 2:
                    bankList.showAccounts();
                    break;

                case 3:
                    System.out.print("Enter customer name to delete account: ");
                    String delName = sc.nextLine();
                    bankList.deleteAccount(delName);
                    break;

                case 4:
                    System.out.println("Exiting Banking System...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
