package com.ginkgocap.ywxt.vo.query.social;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 社交明细vo
 * @Author: qinguochao
 * @CreateDate: 2015-01-22
 * @Version: [v1.0]
 */
public class SocialDetail implements Serializable {
	private static final long serialVersionUID = -4951531866420976010L;
		/** 发送者用户id*/
		private java.lang.Long senderID;
		/** 发送方名称 */
		private java.lang.String senderName;
		/** 社交图片*/
		private List<String> listImageUrl;
		
		/** 社交显示内容，社交类型为聊天时（包含私聊、群聊）封装最后一条聊天记录，通知显示通知内容，邀请函显示邀请函内容 */
		private java.lang.String content;
		 /**  会议开始时间，会议有效    */	
		private int modal;
		
		public int getModal() {
			return modal;
		}
		public void setModal(int modal) {
			this.modal = modal;
		}
		/**getXXX() and SetXX()*/
		public java.lang.Long getSenderID() {
			return senderID;
		}
		public void setSenderID(java.lang.Long senderID) {
			this.senderID = senderID;
		}
		public java.lang.String getSenderName() {
			return senderName;
		}
		public void setSenderName(java.lang.String senderName) {
			this.senderName = senderName;
		}
		public List<String> getListImageUrl() {
			return listImageUrl;
		}
		public void setListImageUrl(List<String> listImageUrl) {
			this.listImageUrl = listImageUrl;
		}
		public java.lang.String getContent() {
			return content;
		}
		public void setContent(java.lang.String content) {
			this.content = content;
		}
		
}
