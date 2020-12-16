package com.itcps2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcps2.filling.model.Cupconsume;
import com.itcps2.repository.CupconsumeRepository;
import com.itcps2.repository.UserRepository;

@Service
public class CupconsumeService {
    @Autowired
	private CupconsumeRepository cr;
	public Optional<Cupconsume> getCupConsumeId(int cid)
	{
		return cr.findById(cid);
		
	}
	public Cupconsume getCupConsumeName(String name)
	{
	Iterable<Cupconsume> list=cr.findAll();
	
      Cupconsume c=new Cupconsume();
		for(Cupconsume item : list)
		{
			
			if(item.getSize().equals(name))
			{
				c=item;
			}
		}
		return c;
//		articleRepository.findAll().forEach(e -> list.add(e))
		
	}
	public Cupconsume getCupConsumeBySize(String size)
	{
	Iterable<Cupconsume> list=cr.findAll();
	
      Cupconsume c=new Cupconsume();
		for(Cupconsume item : list)
		{
			
			if(item.getSize().equals(size))
			{
				c=item;
			}
		}
		if(c!=null)
		return c;
		else 
			return null;
//		articleRepository.findAll().forEach(e -> list.add(e))
		
	}
}