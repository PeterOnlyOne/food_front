package cn.et.model;

import java.util.List;

public class PageTools {

	/**
	 * 构造参数
	 * @param curPage 传入的当前页
	 * @param totalCount 数据库查询的总记录数
	 * @param pageCount 每页显示的条数
	 */
	public PageTools(Integer curPage,Integer totalCount, Integer pageCount){
		this.curPage = curPage;
		this.totalCount = totalCount;
		this.pageCount = (pageCount==null?this.pageCount:pageCount);
		this.prePage = (curPage==1?1:(curPage-1));
		this.nextPage = (curPage==totalPage?totalPage:(curPage+1));
		this.totalPage = (totalCount%this.pageCount==0?(totalCount/this.pageCount):(totalCount/this.pageCount+1));
		this.startIndex = (curPage-1)*this.pageCount+1;
		this.endIndex = curPage*this.pageCount;
	}
	/**
	 * 开始索引
	 * 逻辑：(curPage-1)*pageCount+1
	 */
	private Integer startIndex;
	/**
	 * 结束索引
	 * 逻辑：curPage*pageCount
	 */
	private Integer endIndex;
	/**
	 * 当前页
	 */
	private Integer curPage;
	/**
	 * 上一页
	 * 逻辑：perPage=(curPage==1?1:curPage-1)
	 */
	private Integer prePage;
	/**
	 * 下一页
	 * 逻辑：nextPage=(curPage==totalPage?totalPage:curPage+1)
	 */
	private Integer nextPage;
	/**
	 * 每页显示条数
	 */
	private Integer pageCount=10;
	/**
	 * 总页数
	 * 逻辑：totalPage=(totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1)
	 */
	private Integer totalPage;
	/**
	 * 总记录数（从数据库查询）
	 */
	private Integer totalCount;
	/**
	 * 存储最终查询的数据
	 */
	private List data;
	
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}
