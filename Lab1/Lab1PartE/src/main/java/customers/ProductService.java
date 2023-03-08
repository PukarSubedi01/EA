package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements  IProductService{
    IProductDAO productDAO ;
    IEmailSender emailSender ;
    @Autowired
    public void setProductDAO(IProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Autowired
    public void setEmailSender(IEmailSender emailSender){
        this.emailSender = emailSender;
    }


    @Override
    public void addProduct(String productName, int price, String email) {
    Product product = new Product(productName, price);
    productDAO.save(product);
        emailSender.sendEmail(email, "Product added " + productName + " as a new product");
    }


}
