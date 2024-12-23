import java.util.UUID;

public class WalletGeneration {
    public String generateWallet() {
        // Simulate wallet creation
        String walletAddress = "WALLET-" + UUID.randomUUID();
        System.out.println("Wallet successfully generated!");
        System.out.println("Wallet Address: " + walletAddress);
        return walletAddress;
    }
}

