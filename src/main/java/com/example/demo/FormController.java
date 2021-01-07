package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.Exceptions.ResourceNotFoundException;
import com.sun.el.stream.Optional;

import antlr.collections.List;

@Controller

public class FormController {
	
	@Autowired
	CustomersRepo repo;
	@RequestMapping("/")
	public String details() {
		return "services";
	}
	
	
	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "services";
	}
	
	@RequestMapping("/getdetails")
	public String getdetails() {
		return "ViewCustomers";
	}
	
	
	
	
	@PostMapping("/getdetails")
	public ModelAndView viewdetails(@RequestParam int cid) {
		ModelAndView mv = new ModelAndView("Retrive");
		Customers customers = repo.findById(cid).orElse(null);
		mv.addObject(customers);
		return mv;
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public java.util.List<Customers> getCustomers() {
		return repo.findAll();
	}
	
//	@RequestMapping("/customers/2")
//	@ResponseBody
//	public String getCustomers1() {
//		return repo.findById(2).toString();
//	}
	

//	@RequestMapping("/customers/{cid}")
//	@ResponseBody
//	public java.util.Optional<Customers> getCustomersall(@PathVariable("cid") int cid) {
//		return repo.findById(cid);
//	}
	
	@GetMapping("/customers/{cid}")
	@ResponseBody
	public Customers getAllCustomers(@PathVariable("cid") int cid) {
		return repo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Customer Not found with id:" +cid));
	}
	
	
	@PostMapping("/customers")  
	public Customers getCustomers3(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}
	
	@DeleteMapping("/customers/{cid}")
	public String getCustomers4(@PathVariable("cid") int cid) {
//		Customers cust = repo.getOne(cid);
		repo.delete(repo.getOne(cid));
		return "Success";
	
	}
	
	@PutMapping(path="/customers",consumes = {"application/json"})
	public Customers getCustomers5(@RequestBody Customers customers) {
			 repo.save(customers);
			 return customers;
	
	}
}
