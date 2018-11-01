package com.lin.utils;

import java.util.List;

import com.lin.domain.User;

public class Page {

	private int startIndex;// 起始下标
	private int currentPage;// 当前页
	private int pagesize = 3;// 显示几条记录
	private int count; // 总记录数
	private int totalPages;// 总页数
	private List<User> user;

	public Page() {

	}

	public Page(int count, int currentPage) {

		this.count = count;// 总记录数

		this.currentPage = currentPage;// 当前页

		if (count % this.pagesize == 0) {// 判断总记录书除以每页的记录数是否为0

			this.totalPages = count / this.pagesize;

		} else {

			this.totalPages = count / this.pagesize + 1;
		}

		this.startIndex = (this.currentPage - 1) * this.pagesize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
