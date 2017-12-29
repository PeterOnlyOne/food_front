package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyFood {

	public Integer getFoodListCount(String typeId){
		String sql = "select count(rowid) as cr from FOOD t where t.TYPEID like '%"+typeId+"%'";
		List<Map> list = DbUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	public PageTools getFoodListPage(Integer curPage,String typeId){
		Integer totalCount = getFoodListCount(typeId);
		PageTools pt = new PageTools(curPage, totalCount, 6);
		List<Map> list = DbUtils.query("select * from (select t.*,ft.TYPENAME,rownum rn from FOOD t inner join FOODTYPE ft on t.TYPEID=ft.TYPEID where t.TYPEID like '%"+typeId+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
}
