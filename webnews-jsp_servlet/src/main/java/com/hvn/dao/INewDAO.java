package com.hvn.dao;

import java.util.List;

import com.hvn.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(long categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNew);
	void delete(long id );
}
