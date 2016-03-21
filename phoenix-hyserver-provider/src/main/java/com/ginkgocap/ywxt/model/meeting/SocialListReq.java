package com.ginkgocap.ywxt.model.meeting;

public class SocialListReq implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Integer startRow;
	private Integer pageSize;
	private String withNewRelation;

	public SocialListReq() {
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		if (startRow > 0) {
			this.startRow = (startRow - 1) * pageSize;
		} else {
			this.startRow = startRow;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getWithNewRelation() {
		return withNewRelation;
	}

	public void setWithNewRelation(String withNewRelation) {
		this.withNewRelation = withNewRelation;
	}
}
