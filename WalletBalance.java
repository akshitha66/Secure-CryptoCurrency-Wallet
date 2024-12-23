public class WalletBalance {
    private double balance;

    public WalletBalance() {
        this.balance = 1.0; // Initial balance in BTC
    }

    public void updateBalance(double amount) {
        this.balance += amount;
        System.out.println("Wallet balance updated. New balance: " + this.balance + " BTC");
    }

    public double getBalance() {
        return this.balance;
    }
}
