package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyDesk {

	/**
	 * 查询所有餐桌
	 * @return
	 */
	public List<Map> getTableListAll(){
		String sql = "select * from desk";
		return DbUtils.query(sql);
	}
	
}
