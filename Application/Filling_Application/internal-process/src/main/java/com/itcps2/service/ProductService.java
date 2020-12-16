package com.itcps2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.itcps2.filling.model.Product;
import com.itcps2.repository.ProductRepository;

public class ProductService {

	@Autowired
	private ProductRepository cr;
	public Optional<Product> getProductById(int cid)
	{
		return cr.findById(cid);
		
	}
	
	public Product getProductByName(String name)
	{
	Iterable<Product> list=cr.findAll();
	
      Product c=new Product();
		for(Product item : list)
		{
			
			if(item.getName().equals(name))
			{
				c=item;
			}
		}
		return c;
//		articleRepository.findAll().forEach(e -> list.add(e))
		
	}
}
