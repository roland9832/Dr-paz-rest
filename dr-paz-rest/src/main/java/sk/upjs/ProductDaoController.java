package sk.upjs;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.drpaz.storage.dao.DaoFactory;
import sk.upjs.drpaz.storage.dao.ProductDao;
import sk.upjs.drpaz.storage.entities.Product;



@RestController
public class ProductDaoController {
	
	private ProductDao productDao = DaoFactory.INSTANCE.getProductDao();
	
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public List<Product> getAllProducts(){
		return productDao.getAll();
		
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getById(@PathVariable long id) {
		Product product = null;
		try {
			product = productDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException("Product with id: " + id + " was not found");
		}
		return product;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
	public List<Product> getByName(@PathVariable String name){
		List<Product> products = null;
		try {
			System.out.println(name);
			products = productDao.getByName(name);
		}catch (Exception e) {
			throw new ProductNotFoundException("Product with name: " + name + " was not found");
		}
		return products;
	}
	
	
//	public List<Product> getByCategory()
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) {
		Product newProduct = productDao.save(product);
		return newProduct; 
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/products", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		Product newProduct = productDao.save(product);
		return newProduct; 
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {
		Product product = null;
		try {
			product = productDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException("Product with id: " + id + " was not found");
		}
		
		boolean del = productDao.delete(id);
		return del;
	}
	
	
}
