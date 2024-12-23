import java.util.HashMap;
import java.util.Map;

public class KeyStorage {
    private Map<String, String> keyStore = new HashMap<>();

    // Store a private key for a wallet address
    public void storeKey(String walletAddress, String privateKey) {
        keyStore.put(walletAddress, privateKey);
        System.out.println("Key stored for wallet: " + walletAddress);
    }

    // Retrieve a private key for a wallet address
    public String retrieveKey(String walletAddress) {
        if (keyStore.containsKey(walletAddress)) {
            return keyStore.get(walletAddress);
        } else {
            System.out.println("No key found for wallet: " + walletAddress);
            return null;
        }
    }
}
