/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gintong.ywxt.im.model.JTFile;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.ImChatmessageDao;
import com.ginkgocap.ywxt.dao.meeting.ImMucinfoDao;
import com.ginkgocap.ywxt.dao.meeting.ImMucmemberDao;
import com.ginkgocap.ywxt.dao.meeting.ImMucmessageDao;
import com.ginkgocap.ywxt.dao.meeting.ImRecordmessageDao;
import com.ginkgocap.ywxt.model.meeting.ChatMini;
import com.ginkgocap.ywxt.model.meeting.ImChatmessage;
import com.ginkgocap.ywxt.model.meeting.ImMucinfo;
import com.ginkgocap.ywxt.model.meeting.ImMucmember;
import com.ginkgocap.ywxt.model.meeting.ImMucmessage;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessageForCommunity;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.service.meeting.ImRecordmessageService;
import com.ginkgocap.ywxt.utils.EmojiUtil;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.community.Community;
import com.ginkgocap.ywxt.vo.query.meeting.ConnectionsMini;
import com.ginkgocap.ywxt.vo.query.social.CommunityNewCount;
import com.ginkgocap.ywxt.vo.query.social.Social;
import com.ginkgocap.ywxt.vo.query.social.SocialDetail;

@Service
@Transactional
public class ImRecordmessageServiceImpl implements ImRecordmessageService {
	@Autowired
	private ImRecordmessageDao imRecordmessageDao;

	@Autowired
	private ImMucmemberDao imMucmemberDao;

	@Autowired
	private ImMucinfoDao imMucinfoDao;

	@Autowired
	private ImMucmessageDao imMucmessageDao;

	@Autowired
	private ImChatmessageDao imChatmessageDao;

	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public ImRecordmessage getById(Long property) {
		return imRecordmessageDao.getById(property);
	}

	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long property) {
		imRecordmessageDao.delete(property);
	}

	/**
	 * 名称: getByUserId 描述: 根据userId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByUserId(java.lang.Integer property) {
		return imRecordmessageDao.getByUserId(property);
	}

	/**
	 * 名称: getByUserId2 描述: 根据userId2查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByUserId2(java.lang.Integer property) {
		return imRecordmessageDao.getByUserId2(property);
	}

	/**
	 * 名称: getByChatMessageId 描述: 根据chatMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByChatMessageId(java.lang.Integer property) {
		return imRecordmessageDao.getByChatMessageId(property);
	}

	/**
	 * 名称: getByMucId 描述: 根据mucId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByMucId(java.lang.Integer property) {
		return imRecordmessageDao.getByMucId(property);
	}

	/**
	 * 名称: getByMucCreateUserId 描述: 根据mucCreateUserId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByMucCreateUserId(java.lang.Integer property) {
		return imRecordmessageDao.getByMucCreateUserId(property);
	}

	/**
	 * 名称: getByMucMessageId 描述: 根据mucMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByMucMessageId(java.lang.Integer property) {
		return imRecordmessageDao.getByMucMessageId(property);
	}

	/**
	 * 名称: getByMucStartDate 描述: 根据mucStartDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByMucStartDate(java.util.Date property) {
		return imRecordmessageDao.getByMucStartDate(property);
	}

	/**
	 * 名称: getByType 描述: 根据type查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByType(java.lang.Integer property) {
		return imRecordmessageDao.getByType(property);
	}

	/**
	 * 名称: getByLastMessageDate 描述: 根据lastMessageDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByLastMessageDate(java.util.Date property) {
		return imRecordmessageDao.getByLastMessageDate(property);
	}

	/**
	 * 名称: getByNewCount 描述: 根据newCount查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByNewCount(java.lang.Integer property) {
		return imRecordmessageDao.getByNewCount(property);
	}

	/**
	 * 名称: getByMessageStartTime 描述: 根据messageStartTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByMessageStartTime(java.util.Date property) {
		return imRecordmessageDao.getByMessageStartTime(property);
	}

	/**
	 * 名称: getByStatus 描述: 根据status查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<ImRecordmessage> getByStatus(java.lang.String property) {
		return imRecordmessageDao.getByStatus(property);
	}

	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(ImRecordmessage entity) {
		imRecordmessageDao.saveOrUpdate(entity);
	}

	public List<MeetingVo> getChatMobileIndex(long user_id) {
		List<ImRecordmessage> list = imRecordmessageDao.getByUserIdAndStatus(Long.valueOf(user_id).intValue());
		if (list == null || list.size() == 0)
			return null;
		List<MeetingVo> result = new ArrayList<MeetingVo>(list.size());

		ChatMini mi = null;
		for (int i = 0; i < list.size(); i++) {
			ImRecordmessage irm = list.get(i);
			if (irm == null)
				continue;

			MeetingVo mv = new MeetingVo();
			mi = new ChatMini();

			if (irm.getType() == 1) { // 私聊

				// 基本信息
				List<Integer> userIds = new ArrayList<Integer>();
				userIds.add(irm.getUserId2());
				userIds.add(irm.getUserId());

				List<ConnectionsMini> listConnectionsMini = GinTongInterface.getListConnectionsMini(userIds);
				Map<Integer, String> connectionsMiniMap = new HashMap<Integer, String>();
				if (listConnectionsMini != null && listConnectionsMini.size() > 0) {
					List<String> listImageUrl = new ArrayList<String>(listConnectionsMini.size());
					for (int k = 0; k < listConnectionsMini.size(); k++) {
						ConnectionsMini cmi = listConnectionsMini.get(k);
						if (cmi == null)
							continue;
						if (cmi.getImage() != null)
							listImageUrl.add(cmi.getImage());
						connectionsMiniMap.put(cmi.getId(), cmi.getName());
					}
					if (listImageUrl != null && listImageUrl.size() > 0) {
						mi.setList_image(listImageUrl);
					}
				}
				// 基本信息结束

				mi.setMeeting_id(irm.getUserId2());
				mi.setNewCount(irm.getNewCount());

				ImChatmessage icm = imChatmessageDao.getById(StringUtils.isNumeric(irm.getChatMessageId() + "") ? Long.parseLong(irm
						.getChatMessageId() + "") : -1);

				if (icm != null) {
					mi.setIndex(icm.getId());
					mi.setRecvID(icm.getUserId2());
					mi.setSenderID(icm.getUserId1());
					mi.setSenderName(connectionsMiniMap.get(icm.getUserId1()));
					mi.setType(icm.getMsgType());
					mi.setCommtent(icm.getMsg());
					mi.setLast_chat_time(Utils.DateFormat(icm.getTime()));
					mi.setMessageID(icm.getMessageId());

					JTFile jtFile = new JTFile();
					jtFile.setUrl(icm.getJtFileUrl());
					jtFile.setSuffixName(icm.getJtFileSuffixName());
					jtFile.setType(StringUtils.isNumeric(icm.getJtFileType() + "") ? Integer.parseInt(icm.getJtFileType() + "") : -1);
					jtFile.setFileName(icm.getJtFileName());
					jtFile.setFileSize(icm.getJtFileSize());
					jtFile.setModuleType(icm.getJtFileModuleType());
					jtFile.setTaskId(icm.getJtFileTaskId());
					jtFile.setReserved1(icm.getJtFileReserved1());
					jtFile.setReserved2(icm.getJtFileReserved2());
					jtFile.setReserved3(icm.getJtFileReserved3());

					mi.setJtFile(jtFile);

					mv.setSort(icm.getTime());
				}
				mv.setChat(mi);
				mv.setType(4);

			} else if (irm.getType() == 2) { // 群聊
				List<Integer> userIds = null;

				// 查找所有成员
				List<ImMucmember> queryMUCMemberList = imMucmemberDao.getByMucid(irm.getMucId());
				if (queryMUCMemberList != null && queryMUCMemberList.size() != 0) {
					userIds = new ArrayList<Integer>(queryMUCMemberList.size());
					for (int k = 0; k < queryMUCMemberList.size(); k++) {
						ImMucmember imuc = queryMUCMemberList.get(k);
						if (imuc == null)
							continue;
						userIds.add(imuc.getUserId());

					}
				}
				// 根据成员ID 获取成员的头像信息
				List<ConnectionsMini> listConnectionsMini = GinTongInterface.getListConnectionsMini(userIds);
				Map<Integer, String> connectionsMiniMap = new HashMap<Integer, String>();
				if (listConnectionsMini != null && listConnectionsMini.size() > 0) {
					List<String> listImageUrl = new ArrayList<String>(listConnectionsMini.size());
					for (int k = 0; k < listConnectionsMini.size(); k++) {
						ConnectionsMini cmi = listConnectionsMini.get(k);
						if (cmi == null)
							continue;
						if (cmi.getImage() != null)
							listImageUrl.add(cmi.getImage());
						connectionsMiniMap.put(cmi.getId(), cmi.getName());
					}

					if (listImageUrl != null && listImageUrl.size() > 0) {
						mi.setList_image(listImageUrl);
					}
				}
				// 基本信息结束

				mi.setMeeting_id(irm.getMucId());
				mi.setNewCount(irm.getNewCount());

				ImMucinfo imi = imMucinfoDao.getById(StringUtils.isNumeric(irm.getMucId() + "") ? Long.parseLong(irm.getMucId() + "") : -1);
				if (imi != null) {
					if (imi.getType() == 2) { // 会议
						mi.setTitle(imi.getSubject());
						// mi.setStart_time(Utils.DateFormat(irm.getMucStartDate()));
					} else { // 群聊
						mi.setTitle(imi.getTitle());
					}
				}

				ImMucmessage immess = imMucmessageDao.getById(StringUtils.isNumeric(irm.getMucMessageId() + "") ? Long.parseLong(irm.getMucMessageId() + "") : -1);
				if (immess != null) {
					mi.setCommtent(immess.getMsg());
					mi.setMessageID(immess.getMessageId());
					mi.setSenderID(immess.getSenderId());
					mi.setSenderName(connectionsMiniMap.get(immess.getSenderId()));
					mi.setMessageType(immess.getMsgType());
					mi.setLast_chat_time(Utils.DateFormat(immess.getTime()));

					JTFile jtFile = new JTFile();
					jtFile.setUrl(immess.getJtFileUrl());
					jtFile.setSuffixName(immess.getJtFileSuffixName());
					jtFile.setType(StringUtils.isNumeric(immess.getJtFileType()) ? Integer.parseInt(immess.getJtFileType()) : 0);
					jtFile.setFileName(immess.getJtFileName());
					jtFile.setFileSize(immess.getJtFileSize());
					jtFile.setModuleType(immess.getJtFileModuleType());
					jtFile.setTaskId(immess.getJtFileTaskId());
					jtFile.setReserved1(immess.getJtFileReserved1());
					jtFile.setReserved2(immess.getJtFileReserved2());
					jtFile.setReserved3(immess.getJtFileReserved3());
					mi.setJtFile(jtFile);

					mv.setSort(immess.getTime()); // 设置排序得时间
				}
				mv.setChat(mi);
				mv.setType(5);
			} // end for
			result.add(mv);
		}
		return result;
	}

	/**
	 * 名称: getPrivateChatAndGroupChat 描述: 获取私聊和群聊
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<Social> getPrivateChatAndGroupChat(SocialListReq property) {
		// 2016-03-03 tanmin 通过Rest服务获取单聊跟群聊会话列表
		return GinTongInterface.getListIMRecord(property);
	}

	public Map<Integer,List<Social>> getPrivateChatAndGroupChatMap(SocialListReq property) {
		// 2016-03-03 tanmin 通过Rest服务获取单聊跟群聊会话列表
		return GinTongInterface.getListIMRecordMap(property);
	}
	
	/**
	 * 获取登录用户中存在未读消息的社群列表
	 * @author 周仕奇
	 * @date 2016年1月28日 下午6:57:01
	 * @param property
	 * @return
	 */
	public List<Community> getPrivateCommunity(SocialListReq property) {
		List<ImRecordmessageForCommunity> list = imRecordmessageDao.getCommunityByUserIdAndStatus(property);
		if (list == null || list.size() == 0)
			return null;
		// 去重复
		for (int i = 0; i < list.size(); i++) // 外循环是循环的次数
		{
			for (int j = list.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
			{
				if (defineUnique(list.get(i), list.get(j))) {
					list.remove(j);
				}
			}
		}
		Map<Integer, Integer> mucMessageMap = new HashMap<Integer, Integer>();

		List<Community> result = new ArrayList<Community>(list.size());
		SocialDetail mi = null;
		Community mv = null;
		List<Integer> imMucinfoIdList = new ArrayList<Integer>();
		List<Integer> imMucMessageIdList = new ArrayList<Integer>();
		List<Integer> userIdList = new ArrayList<Integer>();
		Map<Integer, ImMucmessage> imMucmessageMap = new HashMap<Integer, ImMucmessage>();
		Map<Integer, ImMucinfo> imMucinfoMap = new HashMap<Integer, ImMucinfo>();
		userIdList.add(property.getUserId().intValue());// 我的ID
		for (int i = 0; i < list.size(); i++) {
			ImRecordmessage irm = list.get(i);
			if (irm == null)
				continue;
			if (irm.getType() == 5) {// 社群
				mucMessageMap.put(irm.getMucId(), irm.getNewCount());
				if (null != irm.getMucMessageId() && imMucMessageIdList.contains(irm.getMucMessageId()) == false) {
					imMucMessageIdList.add(irm.getMucMessageId());
				}
				if (null != irm.getMucId() && imMucinfoIdList.contains(irm.getMucId()) == false) {
					imMucinfoIdList.add(irm.getMucId());
				}
				if (null != irm.getUserId2() && userIdList.contains(irm.getUserId2()) == false && null != irm.getChatMessageId()) {
					userIdList.add(irm.getUserId2());// 如果有人@了我，此字段为最新的一条@我的人的ID
					imMucMessageIdList.add(irm.getChatMessageId());// 如果有人@了我，此字段为最新的一条@我的那条消息的ID
				}
				continue;
			}
		}
		// 查群信息
		Map<Integer, List<Integer>> mucMemberMap = new HashMap<Integer, List<Integer>>();
		if (imMucinfoIdList.size() > 0) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("ids", imMucinfoIdList);
			// 查群信息
			List<ImMucinfo> imMucinfoList = imMucinfoDao.getByIds(tmp);
			if (null != imMucinfoList && imMucinfoList.size() > 0) {
				for (ImMucinfo ch : imMucinfoList) {
					Long affairId = ch.getAffairId();
					if (affairId == null || affairId <= 0L) {// 去掉事务的畅聊
						imMucinfoMap.put(ch.getId(), ch);
						if (null != ch.getCreateUserId() && userIdList.contains(ch.getCreateUserId()) == false) {
							userIdList.add(ch.getCreateUserId());
						}
					}
				}
			}

			// 查最近发言人
			List<Map<String, Object>> mucList = imMucmemberDao.getByMucids(tmp);
			if (null != mucList && mucList.size() > 0) {
				for (int i = 0; i < mucList.size(); i++) {
					Map<String, Object> map = mucList.get(i);
					Integer mucId = (Integer) map.get("mucId");
					if (null == mucId)
						continue;

					String users = "";
					if (map.get("userIds") instanceof String) {
						users = (String) map.get("userIds");
					} else if (map.get("userIds") instanceof byte[]) {
						byte[] bytes = (byte[]) map.get("userIds");
						try {
							users = new String(bytes, Charset.forName("utf-8"));
						} catch (Throwable e) {
							System.out.println("转换出错：byte_to_string_error:" + Arrays.toString(bytes));
						}
					}

					List<Integer> memberList = new ArrayList<Integer>();
					mucMemberMap.put(mucId, memberList);
					String[] arr = users.split(",");
					int size = Math.min(10, arr.length);// 多查几个，避免没有查到（实际上只需要最近发言的四个）
					for (int k = 0; k < size; k++) {
						Integer uid = Integer.valueOf(arr[k]);
						memberList.add(uid);
						if (userIdList.contains(uid) == false) {
							userIdList.add(uid);
						}
					}
				}
			}
		}

		// 查群聊记录
		if (imMucMessageIdList.size() > 0) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("ids", imMucMessageIdList);
			List<ImMucmessage> imMucinfoList = imMucmessageDao.getByIds(tmp);
			if (null != imMucinfoList && imMucinfoList.size() > 0) {
				for (ImMucmessage ch : imMucinfoList) {
					imMucmessageMap.put(ch.getId(), ch);
					if (null != ch.getSenderId() && userIdList.contains(ch.getSenderId()) == false) {
						userIdList.add(ch.getSenderId());
					}
				}
			}
		}
		// 查头像、用户名称
		Map<Integer, ConnectionsMini> connectionsMiniMap = new HashMap<Integer, ConnectionsMini>();
		if (userIdList.size() > 0) {
			List<ConnectionsMini> listConnectionsMini = GinTongInterface.getListConnectionsMini(userIdList);
			if (listConnectionsMini != null && listConnectionsMini.size() > 0) {
				for (int k = 0; k < listConnectionsMini.size(); k++) {
					ConnectionsMini cmi = listConnectionsMini.get(k);
					if (cmi == null)
						continue;
					connectionsMiniMap.put(cmi.getId(), cmi);
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			ImRecordmessageForCommunity irm = list.get(i);
			if (irm == null)
				continue;

			if (irm.getType() == 5) { // 社群
				mv = new Community();
				mi = new SocialDetail();
				ImMucinfo imi = imMucinfoMap.get(irm.getMucId());
				if (null == imi || null == imi.getId()) {
					continue;
				}
				mi = new SocialDetail();
				// 根据成员ID 获取成员的头像信息
				List<Integer> memberList = mucMemberMap.get(irm.getMucId());
				if (null != memberList && memberList.size() > 0) {
					List<String> listImageUrl = new ArrayList<String>();
					mi.setListImageUrl(listImageUrl);
					for (int x = 0; x < memberList.size(); x++) {
						Integer userId = memberList.get(x);
						ConnectionsMini connectionsMini = connectionsMiniMap.get(userId);
						if (null != connectionsMini && StringUtils.isNotBlank(connectionsMini.getImage())) {
							listImageUrl.add(connectionsMini.getImage());
						}
						if (listImageUrl.size() == 4)
							break;
					}
				}
				// 基本信息结束
				if (1 == imi.getType()) {
					mv.setTitle(imi.getTitle());
				} else if (2 == imi.getType()) {
					String title = imi.getTitle();
					mv.setTitle(StringUtils.isEmpty(title) ? imi.getSubject() : title.trim());
				} else {
					mv.setTitle(imi.getTitle());
				}
				ImMucmessage immess = imMucmessageMap.get(irm.getMucMessageId());
				if (immess != null) {
					immess.setMsg(EmojiUtil.emojiRecover(immess.getMsg())); // 处理emoji表情

					ConnectionsMini cmi = connectionsMiniMap.get(immess.getSenderId());
					if (null != immess.getMsgType() && StringUtils.isNotBlank(immess.getMsg())) {
						String message = immess.getMsg();
						message = null != cmi ? (cmi.getName() + ":" + message) : message;
						mi.setContent(message);
					} else {
						mi.setContent(immess.getMsg());
					}
					mi.setSenderID(Long.valueOf(immess.getSenderId()));
					if (null != cmi) {
						mi.setSenderName(cmi.getName());
					}
					mv.setTime(immess.getTime());
					mv.setOrderTime(immess.getTime()); // 设置排序得时间
				}
				if (null != irm.getUserId2() && null != irm.getChatMessageId()) {
					// 有人@了我
					ConnectionsMini cmi = connectionsMiniMap.get(irm.getUserId2());
					ImMucmessage mucMsg = imMucmessageMap.get(irm.getChatMessageId());
					if (null != cmi && StringUtils.isNotBlank(cmi.getName()) && irm.getChatMessageId() > 0 && null != mucMsg
							&& StringUtils.isNotBlank(mucMsg.getMessageId())) {
						mv.setAtMsgId(mucMsg.getMessageId());
						mv.setAtName(cmi.getName());
					}
				}
				ConnectionsMini cmi = connectionsMiniMap.get(imi.getCreateUserId());
				if (null != cmi) {
					mv.setCompereName(cmi.getName());
				}
				if (!Utils.isNullOrEmpty(imi.getId())) {
					mv.setId(Long.valueOf(imi.getId()));
				}
				mv.setSocialDetail(mi);
				mv.setType(4);
				mv.setNewCount(mucMessageMap.get(imi.getId()));// 获取未读消息数
				
				mv.setNewMessageRemind(irm.getNewMessageRemind());
				mv.setNickname(irm.getNickname());
				mv.setShowNickname(irm.getShowNickname());
				
				result.add(mv);
			}
		}
		return result;
	}
	
	
	/**
	 * 名称: 获取私聊和群聊的未读消息数
	 */
	public Integer getPrivateChatAndGroupChatNewCount(SocialListReq property) {
		List<ImRecordmessage> list = imRecordmessageDao.getByUserIdAndStatusWithoutAffair(property);
		if (list == null || list.size() == 0)
			return null;
		// 去重复
		for (int i = 0; i < list.size(); i++) // 外循环是循环的次数
		{
			for (int j = list.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
			{
				if (defineUnique(list.get(i), list.get(j))) {
					list.remove(j);
				}
			}
		}
		int newCountTotal = 0;
		List<Integer> imChatmessageIdList = new ArrayList<Integer>();
		Map<Integer, Integer> chatMessageMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> mucMessageMap = new HashMap<Integer, Integer>();
		List<Integer> imMucinfoIdList = new ArrayList<Integer>();
		List<Integer> userIdList = new ArrayList<Integer>();
		Map<Integer, ImChatmessage> imChatmessageMap = new HashMap<Integer, ImChatmessage>();
		Map<Integer, ImMucinfo> imMucinfoMap = new HashMap<Integer, ImMucinfo>();
		userIdList.add(property.getUserId().intValue());// 我的ID
		for (int i = 0; i < list.size(); i++) {
			ImRecordmessage irm = list.get(i);
			if (irm == null)
				continue;
			if (irm.getType() == 1) {// 私聊
				chatMessageMap.put(irm.getChatMessageId(), irm.getNewCount());
				if (null != irm.getChatMessageId() && imChatmessageIdList.contains(irm.getChatMessageId()) == false) {
					imChatmessageIdList.add(irm.getChatMessageId());
				}
				continue;

			}
			if (irm.getType() == 2) {// 群聊
				mucMessageMap.put(irm.getMucId(), irm.getNewCount());
				if (null != irm.getMucId() && imMucinfoIdList.contains(irm.getMucId()) == false) {
					imMucinfoIdList.add(irm.getMucId());
				}
				continue;
			}
		}

		// 查私聊记录
		if (imChatmessageIdList.size() > 0) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("ids", imChatmessageIdList);
			List<ImChatmessage> imChatmessageList = imChatmessageDao.getByIds(tmp);
			if (null != imChatmessageList && imChatmessageList.size() > 0) {
				for (ImChatmessage ch : imChatmessageList) {
					imChatmessageMap.put(ch.getId(), ch);
				}
			}
		}

		// 查群信息
		if (imMucinfoIdList.size() > 0) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("ids", imMucinfoIdList);
			// 查群信息
			List<ImMucinfo> imMucinfoList = imMucinfoDao.getByIds(tmp);
			if (null != imMucinfoList && imMucinfoList.size() > 0) {
				for (ImMucinfo ch : imMucinfoList) {
					Long affairId = ch.getAffairId();
					if (affairId == null || affairId <= 0L) {// 去掉事务的畅聊
						imMucinfoMap.put(ch.getId(), ch);
					}
				}
			}

			// 查群聊记录
			for (int i = 0; i < list.size(); i++) {
				ImRecordmessage irm = list.get(i);
				if (irm == null)
					continue;
				if (irm.getType() == 1) {// 私聊
					if (null == irm.getChatMessageId()) {
						continue;
					}
					ImChatmessage icm = imChatmessageMap.get(irm.getChatMessageId());
					if (icm == null) {
						continue;
					}
					// 基本信息结束
					Integer chatCount = chatMessageMap.get(irm.getChatMessageId());
					newCountTotal += chatCount == null ? 0 : chatCount;// 未读消息数
					continue;
				}

				if (irm.getType() == 2) { // 群聊
					ImMucinfo imi = imMucinfoMap.get(irm.getMucId());
					if (null == imi || null == imi.getId()) {
						continue;
					}
					Integer mucChatCount = mucMessageMap.get(imi.getId());
					newCountTotal += mucChatCount == null ? 0 : mucChatCount;// 获取未读消息数
				}
			}
		}
		return newCountTotal;
	}

	public boolean defineUnique(ImRecordmessage source, ImRecordmessage target) {
		if ((source.getUserId() == null ? 0 : source.getUserId().longValue()) == (target.getUserId() == null ? 0 : target.getUserId().longValue())
				&& (source.getUserId2() == null ? 0 : source.getUserId2().longValue()) == (target.getUserId2() == null ? 0 : target.getUserId2()
						.longValue())
				&& (source.getChatMessageId() == null ? 0 : source.getChatMessageId().intValue()) == (target.getChatMessageId() == null ? 0 : target
						.getChatMessageId().intValue())
				&& (source.getMucId() == null ? 0 : source.getMucId().intValue()) == (target.getMucId() == null ? 0 : target.getMucId().intValue())
				&& (source.getMucMessageId() == null ? 0 : source.getMucMessageId().intValue()) == (target.getMucMessageId() == null ? 0 : target
						.getMucMessageId().intValue())
				&& (source.getNewCount() == target.getNewCount())
				&& (source.getMucCreateUserId() == null ? 0 : source.getMucCreateUserId().intValue()) == (target.getMucCreateUserId() == null ? 0
						: target.getMucCreateUserId().intValue())
				&& (source.getType() == null ? 0 : source.getType().intValue()) == (target.getType() == null ? 0 : target.getType().intValue())
				&& (source.getStatus() == null ? "" : source.getStatus().trim())
						.equals((target.getStatus() == null ? "" : target.getStatus().trim()))) {
			return true;
		} else {
			return false;
		}
	}

	public List<ChatMini> getChatMessage(long user_id) {

		List<ImRecordmessage> list = imRecordmessageDao.getByUserIdGroup(user_id);
		if (list == null || list.size() == 0)
			return null;
		List<ChatMini> mini = new ArrayList<ChatMini>();
		for (int i = 0; i < list.size(); i++) {
			ImRecordmessage irm = list.get(i);
			if (irm == null)
				continue;
			ChatMini mi = new ChatMini();

			// 群聊
			List<Integer> userIds = null;

			// 查找所有成员
			List<ImMucmember> queryMUCMemberList = imMucmemberDao.getByMucid(irm.getMucId());
			if (queryMUCMemberList != null && queryMUCMemberList.size() != 0) {
				userIds = new ArrayList<Integer>(queryMUCMemberList.size());
				for (int k = 0; k < queryMUCMemberList.size(); k++) {
					ImMucmember imuc = queryMUCMemberList.get(k);
					if (imuc == null)
						continue;
					userIds.add(imuc.getUserId());

				}
			}
			// 根据成员ID 获取成员的头像信息
			List<ConnectionsMini> listConnectionsMini = GinTongInterface.getListConnectionsMini(userIds);
			Map<Integer, String> connectionsMiniMap = new HashMap<Integer, String>();
			if (listConnectionsMini != null && listConnectionsMini.size() > 0) {
				List<String> listImageUrl = new ArrayList<String>(listConnectionsMini.size());
				for (int k = 0; k < listConnectionsMini.size(); k++) {
					ConnectionsMini cmi = listConnectionsMini.get(k);
					if (cmi == null)
						continue;
					if (cmi.getImage() != null)
						listImageUrl.add(cmi.getImage());
					connectionsMiniMap.put(cmi.getId(), cmi.getName());
				}

				if (listImageUrl != null && listImageUrl.size() > 0) {
					mi.setList_image(listImageUrl);
				}
			}
			// 基本信息结束

			mi.setMeeting_id(irm.getMucId());
			mi.setNewCount(irm.getNewCount());

			ImMucinfo imi = imMucinfoDao.getById(StringUtils.isNumeric(irm.getMucId() + "") ? Long.parseLong(irm.getMucId() + "") : -1);
			if (imi != null) {
				if (imi.getType() == 2) { // 会议
					mi.setTitle(imi.getSubject());
					// mi.setStart_time(Utils.DateFormat(irm.getMucStartDate()));
				} else { // 群聊
					mi.setTitle(imi.getTitle());
				}
			}

			ImMucmessage immess = imMucmessageDao.getById(StringUtils.isNumeric(irm.getMucMessageId() + "") ? Long.parseLong(irm.getMucMessageId()
					+ "") : -1);
			if (immess != null) {
				mi.setCommtent(immess.getMsg());
				mi.setMessageID(immess.getMessageId());
				mi.setSenderID(immess.getSenderId());
				mi.setSenderName(connectionsMiniMap.get(immess.getSenderId()));
				mi.setMessageType(immess.getMsgType());
				mi.setLast_chat_time(Utils.DateFormat(immess.getTime()));

				JTFile jtFile = new JTFile();
				jtFile.setUrl(immess.getJtFileUrl());
				jtFile.setSuffixName(immess.getJtFileSuffixName());
				jtFile.setType(StringUtils.isNumeric(immess.getJtFileType()) ? Integer.parseInt(immess.getJtFileType()) : 0);
				jtFile.setFileName(immess.getJtFileName());
				jtFile.setFileSize(immess.getJtFileSize());
				jtFile.setModuleType(immess.getJtFileModuleType());
				jtFile.setTaskId(immess.getJtFileTaskId());
				jtFile.setReserved1(immess.getJtFileReserved1());
				jtFile.setReserved2(immess.getJtFileReserved2());
				jtFile.setReserved3(immess.getJtFileReserved3());
				mi.setJtFile(jtFile);

				mini.add(mi);
			}
		}
		return mini;

	}

	@Override
	public Integer getAllMeetingNewCount(Long property) {
		return imRecordmessageDao.getAllMeetingNewCount(property);
	}

	@Override
	public List<CommunityNewCount> getCommunityNewCountByUserId(Integer userId) {
		List<ImRecordmessage>  messageList = imRecordmessageDao.getRecordMessageListByUserId(userId);
		Map<Integer,Integer> resultMap = new HashMap<Integer,Integer>();
		for(ImRecordmessage message : messageList){
			resultMap.put(message.getMucId(),message.getNewCount());
		}
		List<CommunityNewCount>  result = imRecordmessageDao.getCommunityNewCountByUserId(userId);
		for(CommunityNewCount entity : result){
			entity.setNewCount(resultMap.get(entity.getMucId()));
		}
		 return result;
	}

}