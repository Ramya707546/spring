package com.springbootmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmvc.entity.ProductEntity;
import com.springbootmvc.model.Productmodel;
import com.springbootmvc.repository.ProductRepository;
@Service
public class ProductService {
@Autowired
 ProductRepository productRepository;

			public void saveproductDetails(Productmodel productmodel) {
				
				{
					double stockValue;
					stockValue=productmodel.getPrice()*productmodel.getQuantity();
					
					double discountPrice;
					discountPrice=productmodel.getPrice()*productmodel.getDiscountRate()/100;
					
					double offerPrice;
					offerPrice=productmodel.getPrice()-discountPrice;
					
					double taxPrice;
					taxPrice=productmodel.getPrice()*0.8;
					
					double finalPrice;	
					finalPrice=offerPrice+taxPrice;
					ProductEntity productEntity=new ProductEntity();
					
					productEntity.setName(productmodel.getName());
					productEntity.setBrand(productmodel.getBrand());
					productEntity.setMadeIn(productmodel.getMadeIn());
					productEntity.setQuantity(productmodel.getQuantity());
					productEntity.setTaxPrice(taxPrice);
					productEntity.setDiscountPrice(discountPrice);
					productEntity.setOfferPrice(offerPrice);
					productEntity.setFinalPrice(finalPrice);
					productEntity.setStockValue(stockValue);
					
					productRepository.save(productEntity); 
			}
			}
				public List<ProductEntity> getAllproducts()
				{
					List<ProductEntity>products=productRepository.findAll();
					return products;
				}
				public ProductEntity searchById(long id) 
				{
					Optional<ProductEntity>optionalData=productRepository.findById(id);
					if(optionalData.isPresent())
					{
						ProductEntity product=optionalData.get();
						return product;
					}
					else
					{
						return null;
					}
				}
				public void deleteproductById(long id)
				{
					productRepository.deleteById(id);
				}
				public Productmodel editById(long id) {
					Optional<ProductEntity>optionalData=productRepository.findById(id);
					Productmodel product=new Productmodel();
					if(optionalData.isPresent())
					{ 
						ProductEntity eproduct=optionalData.get();
						product.setName(eproduct.getName());
						product.setBrand(eproduct.getBrand());
						product.setMadeIn(eproduct.getMadeIn());
						product.setPrice(eproduct.getPrice());
						product.setQuantity(eproduct.getQuantity());
						product.setDiscountRate(eproduct.getDiscountRate());
						return product;
					}
					else
					{
						return null;
					}
				}
				public void updateproduct(long id, Productmodel productmodel)
				{
					Optional<ProductEntity> optional=productRepository.findById(id);
					if(optional.isPresent())
					{
						ProductEntity entity=optional.get();
						entity.setName(productmodel.getName());
						entity.setBrand(productmodel.getBrand());
						entity.setMadeIn(productmodel.getMadeIn());
						entity.setPrice(productmodel.getPrice());
						entity.setQuantity(productmodel.getQuantity());
						entity.setDiscountRate(productmodel.getDiscountRate());
						
						double discountPrice;
						discountPrice=productmodel.getPrice()*productmodel.getDiscountRate()/100;
						
						double offerPrice;
						offerPrice=productmodel.getPrice()-discountPrice;
						
						double taxPrice;
						taxPrice=0.18*offerPrice;
						
						double stockValue;
						stockValue=productmodel.getPrice()*productmodel.getQuantity();
						
						double finalPrice;	
						finalPrice=offerPrice+taxPrice;
						
						entity.setOfferPrice(offerPrice);
						entity.setTaxPrice(taxPrice);
						entity.setStockValue(stockValue);
						entity.setFinalPrice(finalPrice);
						productRepository.save(entity);
					 
					}
				}
}
     
        

