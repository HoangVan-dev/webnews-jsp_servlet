package com.hvn.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.hvn.dao.ICategoryDAO;
import com.hvn.model.CategoryModel;
import com.hvn.service.ICategoryService;

public class CategoryService implements ICategoryService {
	@Inject
	private ICategoryDAO categoryDao;
	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

}
