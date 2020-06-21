package com.hvn.dao;

import java.util.List;

import com.hvn.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	List<CategoryModel> findAll();
}
