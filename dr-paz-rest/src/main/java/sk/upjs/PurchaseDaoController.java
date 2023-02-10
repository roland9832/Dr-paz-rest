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
import sk.upjs.drpaz.storage.dao.PurchaseDao;
import sk.upjs.drpaz.storage.entities.Product;
import sk.upjs.drpaz.storage.entities.Purchase;

@RestController
public class PurchaseDaoController {

	PurchaseDao purchaseDao = DaoFactory.INSTANCE.getPurchaseDao();
	
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
	public Purchase getById(@PathVariable long id){
		Purchase purchase = null;
		try {
			purchase = purchaseDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new PurchaseNotFoundException("Purchase with id: "+ id + " was not found");
		}
		return purchase;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public List<Purchase> getAll(){
		return purchaseDao.getAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public Purchase addPurchase(@RequestBody Purchase purchase) {
		Purchase newPurchase = purchaseDao.save(purchase);
		return newPurchase;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/purchase", method = RequestMethod.PUT)
	public Purchase updatePurchase(@RequestBody Purchase purchase) {
		Purchase newPurchase = purchaseDao.save(purchase);
		return newPurchase;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/purchase/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {
		Purchase purchase = null;
		try {
			purchase = purchaseDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException("Product with id: " + id + " was not found");
		}
		
		boolean del = purchaseDao.delete(id);
		return del;
	}
	
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/productPurchase/{id}", method = RequestMethod.GET)
	public List<Product> getProductsByPurchaseId(@PathVariable long id){
		return purchaseDao.getProductsByPurchaseId(id);
	}
	
}
