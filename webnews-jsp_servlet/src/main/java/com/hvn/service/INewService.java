package com.hvn.service;

import java.util.List;

import com.hvn.model.NewModel;

public interface INewService {
	List<NewModel> findByCategoryId(long categoryId);
}
