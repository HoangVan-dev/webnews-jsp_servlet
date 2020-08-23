package com.hvn.dao;

import java.util.List;

import com.hvn.model.NewModel;
import com.hvn.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(long categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNew);
	void delete(long id );
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
