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

import sk.upjs.drpaz.storage.dao.CategoryDao;
import sk.upjs.drpaz.storage.dao.DaoFactory;
import sk.upjs.drpaz.storage.entities.Category;
import sk.upjs.drpaz.storage.entities.Product;

@RestController
public class CategoryDaoController {

	
	private CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public Category getById(@PathVariable long id) {
		Category category = null;
		try {
			category = categoryDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new CategoryNotFoundException("Category with id: " + id + " was not found");
		}
		return category;
	}
	
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public List<Category> getAllProducts(){
		return categoryDao.getAll();
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public Category addProduct(@RequestBody Category category) {
		Category newCategory = categoryDao.save(category);
		return newCategory; 
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/category", method = RequestMethod.PUT)
	public Category updateProduct(@RequestBody Category category) {
		Category newCategory = categoryDao.save(category);
		return newCategory;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {
		Category category = null;
		try {
			category = categoryDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new CategoryNotFoundException("Category with id: " + id + " was not found");
		}
		
		boolean del = categoryDao.delete(id);
		return del;
	}
}
