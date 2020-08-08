package com.hvn.dao.impl;

import java.util.List;

import com.hvn.dao.ICategoryDAO;
import com.hvn.mapper.CategoryMapper;
import com.hvn.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}
	
}
