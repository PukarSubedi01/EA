package bank.model;

public class Transaction {
    private long fromAccountNumber;
    private long toAccountNumber;
    private double amount;
    private String description;

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
