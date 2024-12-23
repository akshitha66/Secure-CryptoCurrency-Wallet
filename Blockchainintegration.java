public class Blockchainintegration {
    public void connectToBlockchain() {
        // Simulating a blockchain connection
        System.out.println("Connecting to blockchain...");
        try {
            Thread.sleep(1000); // Simulate network delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to blockchain successfully!");
    }

    public boolean sendTransaction(String sender, String receiver, double amount, WalletBalance walletBalance, TransactionHistory transactionHistory) {
        // Check if sender has enough balance
        if (walletBalance.getBalance() < amount) {
            System.out.println("Insufficient balance to complete the transaction.");
            return false;
        }

        System.out.println("Initiating transaction...");
        try {
            Thread.sleep(1000); // Simulate transaction processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display transaction details
        System.out.println("Transaction Details:");
        System.out.println("From: " + sender);
        System.out.println("To: " + receiver);
        System.out.println("Amount: " + amount + " BTC");
        System.out.println("Transaction broadcasted successfully!");

        // Update balance and add transaction to history
        walletBalance.updateBalance(-amount);
        transactionHistory.addTransaction("From: " + sender + ", To: " + receiver + ", Amount: " + amount + " BTC");

        return true;
    }
}
