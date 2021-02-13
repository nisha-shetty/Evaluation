package com.robosoft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtils {
	private static CollectionUtils instance;

	public static CollectionUtils getInstance() {
		if (instance == null) {
			instance = new CollectionUtils();
		}
		return instance;
	}

	public List<Integer> getIdList(Integer[] inIds) {
		List<Integer> idList = null;
		if (inIds != null && inIds.length > 0) {
			idList = Arrays.asList(inIds);
		} else {
			return getListWithZeroValue();
		}
		return idList;
	}

	public List<Integer> getIdList(List<Integer> idList) {
		if (idList == null || idList.isEmpty()) {
			return getListWithZeroValue();
		}
		return idList;
	}

	private List<Integer> getListWithZeroValue() {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(0);
		return idList;
	}
}
