package com.hvn.service;

import java.util.List;

import com.hvn.model.NewModel;
import com.hvn.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNew);
	void delete(long[] ids);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
