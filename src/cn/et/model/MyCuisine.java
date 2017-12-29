package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyCuisine {

	/**
	 * 查询所有菜系
	 * @return
	 */
	public List<Map> getCuisineListAll(){
		String sql = "select * from FOODTYPE";
		return DbUtils.query(sql);
	}
	
}
