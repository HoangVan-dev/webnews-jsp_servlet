package com.hvn.dao.impl;

import java.util.List;

import com.hvn.dao.INewDAO;
import com.hvn.mapper.NewMapper;
import com.hvn.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryId=?";
		return query(sql, new NewMapper(), categoryId);
	}

}
