import java.util.HashMap;
import java.util.Map;

public class LiveCurrencyConversion {
    private Map<String, Double> exchangeRates;

    public LiveCurrencyConversion() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 50000.0); // 1 BTC = 50,000 USD
        exchangeRates.put("EUR", 46000.0); // 1 BTC = 46,000 EUR
        exchangeRates.put("GBP", 40000.0); // 1 BTC = 40,000 GBP
    }

    public void convertToFiat(double amountInBTC, String currency) {
        if (exchangeRates.containsKey(currency)) {
            double amountInFiat = amountInBTC * exchangeRates.get(currency);
            System.out.println(amountInBTC + " BTC is equivalent to " + amountInFiat + " " + currency);
        } else {
            System.out.println("Currency " + currency + " is not supported.");
        }
    }
}
