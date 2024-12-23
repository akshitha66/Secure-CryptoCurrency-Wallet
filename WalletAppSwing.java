import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class WalletAppSwing {
    private WalletGeneration walletGen = new WalletGeneration();
    private KeyStorage keyStorage = new KeyStorage();
    private Blockchainintegration blockchain = new Blockchainintegration();
    private QRCodeFunctionality qrCodeFunc = new QRCodeFunctionality();
    private LiveCurrencyConversion currencyConverter = new LiveCurrencyConversion();
    private TransactionHistory transactionHistory = new TransactionHistory();
    private WalletBalance walletBalance = new WalletBalance();

    private String walletAddress = null;

    public WalletAppSwing() {
        JFrame frame = new JFrame("Secure Crypto Wallet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Create Buttons
        JButton generateWalletButton = new JButton("Generate Wallet");
        JButton generateQRCodeButton = new JButton("Generate QR Code");
        JButton sendTransactionButton = new JButton("Send Transaction");
        JButton storeKeyButton = new JButton("Store Private Key");
        JButton retrieveKeyButton = new JButton("Retrieve Private Key");
        JButton convertCurrencyButton = new JButton("Convert BTC to Fiat");
        JButton viewHistoryButton = new JButton("View Transaction History");
        JButton viewBalanceButton = new JButton("View Wallet Balance");

        // Add Buttons to a Panel
        JPanel panel = new JPanel();
        panel.add(generateWalletButton);
        panel.add(generateQRCodeButton);
        panel.add(sendTransactionButton);
        panel.add(storeKeyButton);
        panel.add(retrieveKeyButton);
        panel.add(convertCurrencyButton);
        panel.add(viewHistoryButton);
        panel.add(viewBalanceButton);

        frame.add(panel);

        // Button Actions
        generateWalletButton.addActionListener(e -> {
            walletAddress = walletGen.generateWallet();
            JOptionPane.showMessageDialog(frame, "Wallet Address: " + walletAddress);
        });

        generateQRCodeButton.addActionListener(e -> {
            if (walletAddress == null) {
                JOptionPane.showMessageDialog(frame, "Please generate a wallet first!");
            } else {
                qrCodeFunc.generateQRCode(walletAddress, "wallet_qr.png");
                JOptionPane.showMessageDialog(frame, "QR Code generated for wallet: " + walletAddress);
            }
        });

        sendTransactionButton.addActionListener(e -> {
            if (walletAddress == null) {
                JOptionPane.showMessageDialog(frame, "Please generate a wallet first!");
            } else {
                String receiverWallet = "WALLET-" + UUID.randomUUID();
                String amountStr = JOptionPane.showInputDialog("Enter amount in BTC:");
                double amount = Double.parseDouble(amountStr);

                blockchain.connectToBlockchain();
                boolean success = blockchain.sendTransaction(walletAddress, receiverWallet, amount, walletBalance, transactionHistory);

                if (success) {
                    JOptionPane.showMessageDialog(frame, "Transaction successful!\nSent " + amount + " BTC to " + receiverWallet);
                } else {
                    JOptionPane.showMessageDialog(frame, "Transaction failed due to insufficient balance.");
                }
            }
        });

        storeKeyButton.addActionListener(e -> {
            if (walletAddress == null) {
                JOptionPane.showMessageDialog(frame, "Please generate a wallet first!");
            } else {
                String privateKey = JOptionPane.showInputDialog("Enter private key:");
                keyStorage.storeKey(walletAddress, privateKey);
                JOptionPane.showMessageDialog(frame, "Private key stored!");
            }
        });

        retrieveKeyButton.addActionListener(e -> {
            if (walletAddress == null) {
                JOptionPane.showMessageDialog(frame, "Please generate a wallet first!");
            } else {
                String retrievedKey = keyStorage.retrieveKey(walletAddress);
                JOptionPane.showMessageDialog(frame, "Retrieved Key: " + retrievedKey);
            }
        });

        convertCurrencyButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount in BTC:");
            double amount = Double.parseDouble(amountStr);
            String currency = JOptionPane.showInputDialog("Enter fiat currency (USD, EUR, GBP):").toUpperCase();

            currencyConverter.convertToFiat(amount, currency);
            JOptionPane.showMessageDialog(frame, amount + " BTC converted to " + currency);
        });

        viewHistoryButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, transactionHistory.displayHistoryAsString());
        });

        viewBalanceButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Wallet Balance: " + walletBalance.getBalance() + " BTC");
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new WalletAppSwing();
    }
}
