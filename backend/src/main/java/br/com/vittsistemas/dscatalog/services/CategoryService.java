package br.com.vittsistemas.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vittsistemas.dscatalog.entities.Category;
import br.com.vittsistemas.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll(){
		
		return repository.findAll();
		
	}
	
}
