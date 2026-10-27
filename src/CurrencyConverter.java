import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * DecodeLabs Java Programming Internship - Project 4
 * Currency Converter using BigDecimal for predictable decimal arithmetic.
 *
 * This version uses fixed demonstration rates and does not call a live API.
 */
public class CurrencyConverter {
    private static final int MONEY_SCALE = 2;

    /*
     * Demo rates: value means how many units of the currency equal 1 USD.
     * These are illustrative values only, not current exchange rates.
     */
    private final Map<String, BigDecimal> unitsPerUsd = new LinkedHashMap<>();

    public CurrencyConverter() {
        unitsPerUsd.put("USD", new BigDecimal("1.00"));
        unitsPerUsd.put("INR", new BigDecimal("83.00"));
        unitsPerUsd.put("EUR", new BigDecimal("0.92"));
        unitsPerUsd.put("GBP", new BigDecimal("0.79"));
        unitsPerUsd.put("JPY", new BigDecimal("150.00"));
        unitsPerUsd.put("AUD", new BigDecimal("1.52"));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CurrencyConverter converter = new CurrencyConverter();
            converter.run(scanner);
        }
    }

    private void run(Scanner scanner) {
        printHeader();

        boolean running = true;
        while (running) {
            printCurrencies();

            String source = readCurrency(scanner, "Enter source currency code: ");
            String target = readCurrency(scanner, "Enter target currency code: ");

            BigDecimal amount = readPositiveAmount(scanner, "Enter amount to convert: ");

            BigDecimal convertedAmount = convert(amount, source, target);

            System.out.println();
            System.out.println("=============== CONVERSION RESULT ===============");
            System.out.printf("From           : %s %s%n", source, formatMoney(amount));
            System.out.printf("To             : %s %s%n", target, formatMoney(convertedAmount));
            System.out.printf("Exchange route : %s -> USD -> %s%n", source, target);
            System.out.println("=================================================");

            System.out.print("\nWould you like to convert another amount? (Y/N): ");
            String answer = scanner.nextLine().trim();
            if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("YES")) {
                running = false;
            }
            System.out.println();
        }

        System.out.println("Thank you for using Currency Converter!");
    }

    /**
     * Converts through USD as a pivot:
     * amount in source / source units per USD * target units per USD.
     */
    public BigDecimal convert(BigDecimal amount, String source, String target) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative or null.");
        }

        String sourceCode = normalizeCurrency(source);
        String targetCode = normalizeCurrency(target);

        BigDecimal sourceRate = unitsPerUsd.get(sourceCode);
        BigDecimal targetRate = unitsPerUsd.get(targetCode);

        if (sourceRate == null || targetRate == null) {
            throw new IllegalArgumentException("Unsupported currency code.");
        }

        BigDecimal amountInUsd = amount.divide(sourceRate, 10, RoundingMode.HALF_EVEN);
        return amountInUsd.multiply(targetRate).setScale(MONEY_SCALE, RoundingMode.HALF_EVEN);
    }

    private String readCurrency(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String code = normalizeCurrency(scanner.nextLine());

            if (unitsPerUsd.containsKey(code)) {
                return code;
            }

            System.out.println("Unsupported currency. Choose one of: " + String.join(", ", unitsPerUsd.keySet()));
        }
    }

    private BigDecimal readPositiveAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                BigDecimal amount = new BigDecimal(input);
                if (amount.compareTo(BigDecimal.ZERO) > 0) {
                    return amount;
                }
                System.out.println("Amount must be greater than zero. Please try again.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid amount. Enter a number such as 1500 or 25.50.");
            }
        }
    }

    private String normalizeCurrency(String code) {
        return code == null ? "" : code.trim().toUpperCase();
    }

    private String formatMoney(BigDecimal amount) {
        return amount.setScale(MONEY_SCALE, RoundingMode.HALF_EVEN).toPlainString();
    }

    private void printHeader() {
        System.out.println("=================================================");
        System.out.println("               CURRENCY CONVERTER                ");
        System.out.println("=================================================");
        System.out.println("Note: The exchange rates below are demo rates,");
        System.out.println("not live market rates. Results are for practice.");
        System.out.println();
    }

    private void printCurrencies() {
        System.out.println("Supported currencies: " + String.join(", ", unitsPerUsd.keySet()));
        System.out.println("Demo rates (units per 1 USD):");
        for (Map.Entry<String, BigDecimal> entry : unitsPerUsd.entrySet()) {
            System.out.printf("  1 USD = %s %s%n", entry.getValue().stripTrailingZeros().toPlainString(), entry.getKey());
        }
        System.out.println();
    }
}
