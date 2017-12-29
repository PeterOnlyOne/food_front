package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyOrder {

	public Integer getOrderId(){
		String sql = "select nvl(max(ORDERID),0)+1 as myId from FOODORDER";
		List<Map> result = DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("MYID").toString());
	}
	
	public Integer saveOrder(String deskId,Integer state) throws Exception{
		Integer orderId = getOrderId();
		String sql = "insert into FOODORDER values('"+orderId+"','"+deskId+"',sysdate,'"+state+"')";
		DbUtils.excute(sql);
		return orderId;
	}
	
	public void saveOrderDetail(Integer orderId,Integer foodId,Integer count) throws Exception{
			String sql = "insert into FOODORDERDETAIL values((select nvl(max(DETAILID),0)+1 from FOODORDERDETAIL),"
					+ "'"+orderId+"','"+foodId+"','"+count+"')";
			DbUtils.excute(sql);
	}
}
