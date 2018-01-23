package com.rqb.crm.manager.config.mybatis;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class PaginationInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int number;
    //每页的数量
    private int size;
    //总记录数
    private long totalElements;
    //总页数
    private int totalPages;
    //结果集
    private List<T> content;
    //是否为第一页
    private boolean first = false;
    //是否为最后一页
    private boolean last = false;


    public PaginationInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PaginationInfo(PageInfo<T> page) {
    	
    	this.content= page.getList();
    	this.number = page.getPageNum();
    	this.size = page.getPageSize();
    	this.totalElements = page.getTotal();
    	this.totalPages = page.getPages();
    	this.first = page.isIsFirstPage();
    	this.last = page.isIsLastPage();
    }

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "PaginationInfo [number=" + number + ", size=" + size + ", totalElements=" + totalElements
				+ ", totalPages=" + totalPages + ", content=" + content + ", first=" + first + ", last=" + last + "]";
	}

}
