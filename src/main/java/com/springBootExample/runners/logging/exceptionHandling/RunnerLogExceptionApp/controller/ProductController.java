package com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp.controller;

import com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp.Exception.ProductNotfoundException;
import com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product mac = new Product();
        mac.setId("1001");
        mac.setName("MAC 13 inch");
        mac.setPrice(95000.00);
        productRepo.put(mac.getId(), mac);

        Product hp = new Product();
        hp.setId("1002");
        hp.setName("HP Pro 24inch");
        hp.setPrice(76000.00);
        productRepo.put(hp.getId(), hp);
    }

    /* **GET**
     * Method to display the products in json format
    Run the following in Postman
    GET: http://localhost:9090/products
    Response:
        [
         {
             "id": "1002",
             "name": "HP Pro 24inch",
             "price": 76000.00
         },
         {
             "id": "1001",
             "name": "MAC 13 inch",
             "price": 95000.00
         }
     ]
     */
    @RequestMapping(value = "/products")
    public ResponseEntity<Collection<Product>> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }





    /*	**POST**
     * Method to add product
       Run the following in Postman
       POST: http://localhost:9090/products
       Body - raw - json :
        {
            "id": "1003",
            "name": "Dell",
            "price": 72000.00
        }
        Response:
            Product is created successfully
        Now if we run
        GET: http://localhost:9090/products
        Response: display newly added product along with existing products.

        NOTE: If we stop server and run again DempApplicaton.java
        then Response will be two products only.
        */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }


    /*	**PUT**
     * Method to update product
     * NOTE 1: If product already exists then it will update.
     *
     * NOTE 2: while update a product we have to pass all values not only the
     * value which we want to update, we have to pass remaining values also in json format.
     * If not the values become ZERO  or null.

       Run the following in Postman
       PUT: http://localhost:9090/products/1003
       Body - raw - json :
        {
             "name": "Dell 24inch",
       }
        Response:
            Product is updated successfully
        Now if we run
        GET: http://localhost:9090/products
        Response: display newly updated product along with existing products.

        NOTE: If we stop server and run again DempApplicaton.java
        then Response will be two products only.
        * NOTE 3:  If PRODUCT is not available it throws exception message
        * PUT: http://localhost:9090/products/1
        * {
            "id": "1003",
            "name": "Dell",
            "price": 72000.00
        }
        * Response: Product not found from ProductNotfoundException
        */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }



    /*	**DELETE**
     * Method to delete product
     * Run the following in Postman
       PUT: http://localhost:9090/products/1003
       Response:
            Product is deleted successfully
    */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }
}
