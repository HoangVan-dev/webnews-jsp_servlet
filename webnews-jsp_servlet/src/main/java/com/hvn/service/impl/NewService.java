package com.hvn.service.impl;

import java.sql.Timestamp;
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
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setCreatedBy("");
		Long newId = newDao.save(newModel);
		return newDao.findOne(newId);
	}

	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDao.findOne(updateNew.getId());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNew.setModifiedBy("");
		newDao.update(updateNew);
		return newDao.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			//before delete news, must delete comment because in comment has forienkey newID) 
			newDao.delete(id);
		}
		
	}

}
