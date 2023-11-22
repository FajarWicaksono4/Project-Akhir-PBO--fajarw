import java.util.*;

class Asset {
    private String type;
    private double value;

    public Asset(String type, double value) {
        this.type = type;
        this.value = value;
    }

    // Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

class Investment {
    private Asset asset;
    private String investmentType;

    public Investment(Asset asset, String investmentType) {
        this.asset = asset;
        this.investmentType = investmentType;
    }

    // Getters and setters

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }
}

class Payment {
    private Asset asset;
    private String paymentType;

    public Payment(Asset asset, String paymentType) {
        this.asset = asset;
        this.paymentType = paymentType;
    }

    // Getters and setters

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}

class User {
    private String username;
    private String password;
    private List<Transaction> transactions;
    private List<Asset> assets;
    private List<Investment> investments;
    private List<Payment> payments;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
        this.assets = new ArrayList<>();
        this.investments = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void displayInfo() {
        System.out.println("User Information");
        System.out.println("Username: " + username);
    }
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void viewAllUserTransactions(Map<String, User> users) {
        System.out.println("All User Transactions:");
        for (User user : users.values()) {
            System.out.println("User: " + user.getUsername());
            for (Transaction transaction : user.getTransactions()) {
                System.out.println("Type: " + transaction.getType() + ", Amount: $" + transaction.getAmount());
            }
            System.out.println("---------------");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Admin");
    }
}

class BankTeller extends User {
    public BankTeller(String username, String password) {
        super(username, password);
    }

    public void viewAllUserInformation(Map<String, User> users) {
        System.out.println("All User Information:");
        for (User user : users.values()) {
            user.displayInfo();
            System.out.println("---------------");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Bank Teller");
    }
}

class AccountType {
    private String typeName;

    public AccountType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

class Bank {
    private String bankName;
    private Map<String, User> users;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.users = new HashMap<>();
    }

    public String getBankName() {
        return bankName;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

public class BankingSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank("MyBank");

        // Adding sample users
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");

        bank.addUser(user1);
        bank.addUser(user2);

        // Adding an admin
        Admin admin = new Admin("admin", "admin");

        BankTeller bankTeller = new BankTeller("teller", "teller");
        bank.addUser(bankTeller);

        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. Bank Teller Login");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin(admin, bank);
                    break;
                case 2:
                    bankTellerLogin(bank);
                    break;
                case 3:
                    userLogin(bank);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminLogin(Admin admin, Bank bank) {
        System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            adminMenu(admin, bank);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void bankTellerLogin(Bank bank) {
        System.out.print("Enter bank teller username: ");
        String username = scanner.next();
        System.out.print("Enter bank teller password: ");
        String password = scanner.next();

        BankTeller bankTeller = (BankTeller) bank.getUsers().get(username);

        if (bankTeller != null && username.equals(bankTeller.getUsername()) && password.equals(bankTeller.getPassword())) {
            bankTellerMenu(bankTeller, bank);
        } else {
            System.out.println("Invalid bank teller credentials.");
        }
    }

    private static void userLogin(Bank bank) {
        System.out.print("Enter user username: ");
        String username = scanner.next();
        System.out.print("Enter user password: ");
        String password = scanner.next();

        User user = bank.getUsers().get(username);

        if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            userMenu(user, bank);
        } else {
            System.out.println("Invalid user credentials.");
        }
    }

    private static void adminMenu(Admin admin, Bank bank) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View All User Transactions");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.viewAllUserTransactions(bank.getUsers());
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bankTellerMenu(BankTeller bankTeller, Bank bank) {
        while (true) {
            System.out.println("Bank Teller Menu:");
            System.out.println("1. View All User Information");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bankTeller.viewAllUserInformation(bank.getUsers());
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu(User user, Bank bank) {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    performTransaction(user, "Deposit", depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    performTransaction(user, "Withdraw", withdrawalAmount);
                    break;
                case 3:
                    displayUserTransactions(user);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void performTransaction(User user, String type, double amount) {
        if (amount > 0) {
            user.addTransaction(new Transaction(type, amount));
            if (type.equals("Deposit")) {
                System.out.println("Deposit successful. Current balance: $" + (user.getTransactions().stream()
                        .filter(t -> t.getType().equals("Deposit"))
                        .mapToDouble(Transaction::getAmount)
                        .sum()));
            } else if (type.equals("Withdraw")) {
                double totalWithdrawals = user.getTransactions().stream()
                        .filter(t -> t.getType().equals("Withdraw"))
                        .mapToDouble(Transaction::getAmount)
                        .sum();
                if (amount <= totalWithdrawals) {
                    System.out.println("Withdrawal successful. Current balance: $" + (totalWithdrawals - amount));
                } else {
                    System.out.println("Insufficient funds.");
                }
            }
        } else {
            System.out.println("Invalid transaction amount.");
        }
    }

    private static void displayUserTransactions(User user) {
        System.out.println("User Transactions:");
        for (Transaction transaction : user.getTransactions()) {
            System.out.println("Type: " + transaction.getType() + ", Amount: $" + transaction.getAmount());
        }
    }
}
