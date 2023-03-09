package customers;

public class Product {

    private String productNumber;
    private String name;
    private int price;

    private Supplier supplier;

    public Product(String productNumber, String name, int price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", supplier=" + supplier +
                '}';
    }
}
