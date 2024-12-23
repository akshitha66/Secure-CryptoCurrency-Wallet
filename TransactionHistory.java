import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<String> history;

    public TransactionHistory() {
        history = new ArrayList<>();
    }

    public void addTransaction(String transactionDetails) {
        history.add(transactionDetails);
        System.out.println("Transaction added to history.");
    }

    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
    }

    public String displayHistoryAsString() {
        if (history.isEmpty()) {
            return "No transaction history available.";
        } else {
            StringBuilder historyString = new StringBuilder("Transaction History:\n");
            for (String transaction : history) {
                historyString.append(transaction).append("\n");
            }
            return historyString.toString();
        }
    }
    
}
