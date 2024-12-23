import java.util.Scanner;
import java.util.UUID;

public class app {
    public static void main(String[] args) {
        WalletGeneration walletGen = new WalletGeneration();
        QRCodeFunctionality qrCodeFunc = new QRCodeFunctionality();
        Blockchainintegration blockchain = new Blockchainintegration();
        KeyStorage keyStorage = new KeyStorage();
        LiveCurrencyConversion currencyConverter = new LiveCurrencyConversion();
        TransactionHistory transactionHistory = new TransactionHistory();
        WalletBalance walletBalance = new WalletBalance();

        Scanner scanner = new Scanner(System.in);
        String walletAddress = null;

        while (true) {
            System.out.println("\n=== Secure Crypto Wallet Menu ===");
            System.out.println("1. Generate Wallet");
            System.out.println("2. Generate QR Code for Wallet");
            System.out.println("3. Simulate Blockchain Transaction");
            System.out.println("4. Store Private Key");
            System.out.println("5. Retrieve Private Key");
            System.out.println("6. Convert Cryptocurrency to Fiat");
            System.out.println("8. View Transaction History");
            System.out.println("9. View Wallet Balance");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    walletAddress = walletGen.generateWallet();
                    System.out.println("Your new wallet address: " + walletAddress);
                    break;
                case 2:
                    if (walletAddress == null) {
                        System.out.println("Please generate a wallet first!");
                    } else {
                        qrCodeFunc.generateQRCode(walletAddress, "wallet_qr.png");
                        System.out.println("QR Code generated for wallet: " + walletAddress);
                    }
                    break;
                case 3:
                    if (walletAddress == null) {
                        System.out.println("Please generate a wallet first!");
                    } else {
                        System.out.print("Enter amount to send in BTC: ");
                        double amountToSend = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        String receiverWallet = "WALLET-" + UUID.randomUUID();
                        blockchain.connectToBlockchain();
                        boolean success = blockchain.sendTransaction(walletAddress, receiverWallet, amountToSend, walletBalance, transactionHistory);

                        if (!success) {
                            System.out.println("Transaction failed due to insufficient balance.");
                        }
                    }
                    break;
                case 4:
                    if (walletAddress == null) {
                        System.out.println("Please generate a wallet first!");
                    } else {
                        System.out.print("Enter a private key to store: ");
                        String privateKey = scanner.nextLine();
                        keyStorage.storeKey(walletAddress, privateKey);
                    }
                    break;
                case 5:
                    if (walletAddress == null) {
                        System.out.println("Please generate a wallet first!");
                    } else {
                        String retrievedKey = keyStorage.retrieveKey(walletAddress);
                        System.out.println("Retrieved Key: " + retrievedKey);
                    }
                    break;
                case 6:
                    System.out.print("Enter amount in BTC to convert: ");
                    double amountInBTC = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter fiat currency (USD, EUR, GBP): ");
                    String currency = scanner.nextLine().toUpperCase();
                    currencyConverter.convertToFiat(amountInBTC, currency);
                    break;
                case 8:
                    transactionHistory.displayHistory();
                    break;
                case 9:
                    System.out.println("Wallet Balance: " + walletBalance.getBalance() + " BTC");
                    break;
                case 7:
                    System.out.println("Exiting... Thank you for using Secure Crypto Wallet!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
