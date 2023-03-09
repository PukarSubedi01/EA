package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
        jdbcTemplate.update("DELETE from supplier",namedParameters);
    }

    public void removeProduct(String productNumber) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE from product WHERE " +"productNumber = :productNumber",namedParameters);

    }
    public void save(Product product) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productNumber, :name, :price)",namedParameters);

        Map<String,Object> namedParameterscc = new HashMap<String,Object>();
        namedParameterscc.put("productnumber", product.getProductNumber());
        namedParameterscc.put("id", product.getSupplier().getId());
        namedParameterscc.put("name", product.getSupplier().getName());
        namedParameterscc.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :id, :name, :phone, :productnumber)",namedParameterscc);

    }
        public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getString("productnumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

            for (Product product: products){
                Supplier supplier = getSupplierForProduct(product.getProductNumber());
                product.setSupplier(supplier);
            }
        return products;
    }

    Supplier getSupplierForProduct(String productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }

    public Product getProductById(String productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getString("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);
        return product;

    }

    public Product getProductByName(String name){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("name", name);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "name =:name ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getString("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));
        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);

        return product;

    }

}
