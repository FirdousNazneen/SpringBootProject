package com.sathya3.springmvc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya3.springmvc.entity.ProductEntity;
import com.sathya3.springmvc.model.ProductModel;
import com.sathya3.springmvc.service.ProductService;

import jakarta.validation.Valid;


@Controller
public class ProductController {
@Autowired
ProductService  productService;

//form
	//@GetMapping("/productform")
	//public String getProductForm()
	//{
		//return "product";
	//}
	
	
	//@PostMapping("/SaveProduct")
	//public String saveProduct(ProductModel ProductModel)
	//{
		//ProductService.saveProductDetails(ProductModel);
		//return "success";
		
	//}

//form with value
@GetMapping("/productform")
 public String getproductForm(Model model)
{
	ProductModel  productModel=new ProductModel();
	
	productModel .setMadeIn("India");
	productModel .setQuantity(2);
	productModel .setDiscountRate(10.5);
	model.addAttribute("productModel", productModel);
	return "add-product";
}

@PostMapping("/saveproduct")
public String saveProduct(@Valid  ProductModel productModel, BindingResult bindingResult, Model model) {
    // To store validation errors if any
    HashMap<String, String> validationErrors = new HashMap<>();
    
    // If there are validation errors, populate the errors map and return the form
    if (bindingResult.hasErrors()) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        model.addAttribute("validationErrors", validationErrors);
        return "add-product";  // Return the form page with errors
    }
    
    // If no errors, save product details
    productService.saveProductDetails(productModel);
    return "redirect:/getallproducts";  // Redirect to list of all products
}

// Get all products and show them
@GetMapping("/getallproducts")
public String getProductForm(Model model) {
    List<ProductEntity> products = productService.getAllProducts();
    model.addAttribute("products", products);
    return "productlist";  // Render the page with all products
}

	
//search by id
	@GetMapping("/getsearchform")
	public String getsearchForm()
	{
		return "Search-Product";
	}
	
	@PostMapping("/searchbyid")
	public String searchById(@RequestParam Long id,Model model)
	{
		ProductEntity product=productService.searchProductById(id);
		model.addAttribute("product",product);
		return "Search-Product";
	}
	
//delete operation   
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return "redirect:/getallproducts";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable ("id")Long id,Model model) {

		ProductModel product= productService.showEditForm(id);
		model.addAttribute("product",product);
		model.addAttribute("id",id);
	    return "edit-product";
	}
	 
	@PostMapping("/editbyid/{id}")
	public String editById(@PathVariable("id") Long id, ProductModel productModel ) {
		productService.editById(id, productModel);	
		return "redirect:/getallproducts";
	}

}
	
	



