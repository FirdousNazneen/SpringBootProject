package com.sathya3.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya3.springmvc.entity.ProductEntity;
import com.sathya3.springmvc.model.ProductModel;
import com.sathya3.springmvc.repository.ProductRepository;


@Service
public class ProductService {
    @Autowired
     ProductRepository productRepository;

    public void saveProductDetails(ProductModel productModel)
    {
  	  double stockValue;
  	  stockValue=productModel.getPrice() * productModel.getQuantity();
  	  
  	  double discountprice;
  	  discountprice=productModel.getPrice()*productModel.getDiscountRate()/100;
  	  
  	  double offerPrice;
  	  offerPrice=productModel.getPrice() - discountprice;
  	 
  	  
  	  double taxPrice;
  	  double taxRate=18;
  	  taxPrice = offerPrice * taxRate/100;
        
  	  double finalPrice;
  	  finalPrice = taxPrice + offerPrice ;
  	  
  	  ProductEntity productentity=new ProductEntity();
  	  
  	productentity.setName(productModel.getName());
  	productentity.setBrand(productModel.getBrand());
  	productentity.setMadeIn(productModel.getMadeIn());
  	productentity.setPrice(productModel.getPrice());
  	productentity.setQuantity(productModel.getQuantity());
  	productentity.setDiscountRate(productModel.getDiscountRate());
  	  
  	
  	  productentity.setStockvalue(stockValue);
        productentity.setDiscountprice(discountprice);
        productentity.setOfferprice(offerPrice);
        productentity.setTaxprice(taxPrice);
        productentity.setFinalprice(finalPrice);
        
        
        productRepository.save(productentity);
    }
    
    //getallproducts
    public List<ProductEntity>getAllProducts(){
    	
    	List<ProductEntity>products = productRepository.findAll();
    
    	return products;
    }
    
    
     //id
    public ProductEntity searchProductById(Long id)
    {
    	Optional<ProductEntity> OptionalData=productRepository.findById(id);
    	if(OptionalData.isPresent())
    	{
    		ProductEntity product=OptionalData.get();
    				return product;
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //delete
    public void deleteProductById(Long id) {
    	productRepository.deleteById(id);
    	
    }

	public ProductModel showEditForm(Long id) {
		Optional<ProductEntity> OptionalData=productRepository.findById(id);
		  if(OptionalData.isPresent())
		  {
			  ProductEntity entity=OptionalData.get();
			  
			  ProductModel productModel=new ProductModel();
			  
			  productModel.setName(entity.getName());
			  productModel.setBrand(entity.getBrand());
			  productModel.setMadeIn(entity.getMadeIn());
			  productModel.setPrice(entity.getPrice());
			  productModel.setQuantity(entity.getQuantity());
			  productModel.setDiscountRate(entity.getDiscountRate());
			  
			  return productModel;
		  }
		  else
		  {
			  return null;  
		  }
			  
	}
    
   
    //edit
public void editById(Long id, ProductModel productModel) {
        
		Optional<ProductEntity> OptionalData=productRepository.findById(id);
		  if(OptionalData.isPresent())
		  {
			  ProductEntity productEntity=OptionalData.get();
			  
			  double stockValue;
			  stockValue=productModel.getPrice()*productModel.getQuantity();
			  
			  double discountprice;
			  discountprice=productModel.getPrice()*productModel.getDiscountRate()/100;
			  
			  double offerPrice;
			  offerPrice=productModel.getPrice()-discountprice;
			 
			  
			  double taxPrice;
			  double taxRate=18;
			  taxPrice=offerPrice*taxRate/100;
		      
			  double finalPrice;
			  finalPrice=offerPrice +(offerPrice * (taxRate/100));
			  
			  
			  productEntity.setName(productModel.getName());
			  productEntity.setBrand(productModel.getBrand());
			  productEntity.setMadeIn(productModel.getMadeIn());
			  productEntity.setPrice(productModel.getPrice());
			  productEntity.setQuantity(productModel.getQuantity());
			  productEntity.setDiscountRate(productModel.getDiscountRate());
			  
			  productEntity.setStockvalue(stockValue);
			  productEntity.setStockvalue(taxPrice);
		      productEntity.setDiscountprice(discountprice);
		      productEntity.setOfferprice(offerPrice);
		      productEntity.setFinalprice(finalPrice);

		      productRepository.save(productEntity);
			  
			  
			  
		  }
	

    }

	

	
    
}


	  


