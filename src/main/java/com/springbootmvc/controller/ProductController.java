package com.springbootmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootmvc.entity.ProductEntity;
import com.springbootmvc.model.Productmodel;
import com.springbootmvc.service.ProductService;


@Controller
public class ProductController{
	@Autowired
	ProductService productService;
	@GetMapping("/productform")
	public String getproductform()
	{
		return "add-Product";
	}
	@PostMapping("/saveproduct")
	public String saveproduct(Productmodel productmodel)
	{
		System.out.println(productmodel);
		productService.saveproductDetails(productmodel);
		return "success";  
	}
	@GetMapping("/getAllproducts")
	public String getAllproducts(Model model)
	{
		List<ProductEntity>products=productService.getAllproducts();
	model.addAttribute("products",products);
	return "Product-list";
	}
	@GetMapping("/getsearchform")
	public String getsearchform()
	{
		return "search-product";
	}
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam long id,Model model)
	{
		ProductEntity product=productService.searchById(id);
		model.addAttribute("product", product);
		return "search-product";
	}
	@GetMapping("/delete/{id}")
	public String deleteproductById(@PathVariable ("id")long Id)
	{
		productService.deleteproductById(Id);
		return "redirect:/getAllproducts";
	} 
	@GetMapping("/edit/{id}") 
	public String editById(@PathVariable long id,Model model)
	{
		Productmodel product=productService.editById(id);
		model.addAttribute("product",product);
		return "edit-product";
	}
	@PostMapping("/editproductsave/{id}")
	public String updateproduct(@PathVariable long id, Productmodel productmodel)
	{
		productService.updateproduct(id, productmodel);
		return "redirect:/getAllproducts";
	}
	
}

