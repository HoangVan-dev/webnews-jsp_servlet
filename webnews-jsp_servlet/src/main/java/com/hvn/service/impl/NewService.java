package com.hvn.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.hvn.dao.INewDAO;
import com.hvn.model.NewModel;
import com.hvn.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDAO newDao;
	
	@Override
	public List<NewModel> findByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		Long newId = newDao.save(newModel);
		System.out.println(newId);
		return null;
	}

}
