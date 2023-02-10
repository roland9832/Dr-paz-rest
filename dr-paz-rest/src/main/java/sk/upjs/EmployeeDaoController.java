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
import sk.upjs.drpaz.storage.dao.EmployeeDao;
import sk.upjs.drpaz.storage.entities.Employee;


@RestController
public class EmployeeDaoController {
	
	private EmployeeDao employeeDao = DaoFactory.INSTANCE.getEmployeeDao();
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value="/employee", method = RequestMethod.GET)
	public List<Employee> getAll(){
		return employeeDao.getAll();
		
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getById(@PathVariable long id) {
		Employee employee = null;
		try {
			employee = employeeDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EmployeeNotFoundException("Employee with id: " + id + " was not found");
		}
		return employee;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeDao.save(employee);
		return newEmployee; 
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeDao.save(employee);
		return newEmployee; 
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable long id) {
		Employee employee = null;
		try {
			employee = employeeDao.getById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EmployeeNotFoundException("Employee with id: " + id + " was not found");
		}
		
		boolean del = employeeDao.delete(id);
		return del;
	}
}
