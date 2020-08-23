package com.hvn.paging;

import com.hvn.sort.Sorter;

public interface Pageble {

	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
