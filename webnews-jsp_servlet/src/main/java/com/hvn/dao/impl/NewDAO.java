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

	@Override
	public Long save(NewModel newModel) {
		//String sql = "INSERT INTO news ( title, content, categoryID) VALUES(?, ?, ?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news ( title, thumbnail, shortdescription, content, ");
		sql.append("categoryID, createddate, createdby) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy());

	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id=?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql= new StringBuilder("UPDATE news SET title = ?, thumbnail = ? ,");
		sql.append("shortdescription = ?,content = ?,  categoryid = ?,");
		sql.append("createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getCreatedBy(), 
				updateNew.getModifiedDate(), updateNew.getModifiedBy(), updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll() {
		String sql = "SELECT * FROM news";
		return query(sql, new NewMapper());
	}

}
