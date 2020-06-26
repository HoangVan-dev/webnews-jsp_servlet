package com.hvn.dao;

import java.util.List;

import com.hvn.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findByCategoryId(long categoryId);
	Long save(NewModel newModel);
}
