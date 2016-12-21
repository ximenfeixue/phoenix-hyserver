<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>测试</title>
</head>
<script src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<body>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="methodChange" >
	测试方<select id="changeUrlSelect" onchange="javascript:changeUrl();">
		<option value="0">请选择</option>
		<option value="2">会议首页[6.1]</option>
		<option value="5">我的邀请函列表[6.2]</option>
		<option value="1">创建会议 [6.3]</option>
		<option value="4">当面邀请[6.4]</option>
		<option value="6">根据用户id和会议id获取会议详情[6.5]</option>
		<option value="7">接受邀请、拒绝邀请、取消报名[6.6]</option>
		<option value="8">报名[6.7]</option>
		<option value="11">修改议题[6.9]</option>
		<option value="38">添加会议笔记明细[6.10]</option>
		<option value="14">根据会议id获取当前用户的会议笔记及明细[6.11]</option>	
		<option value="18">删除我创建的会议[6.13]</option>
		<option value="19">归档、删除我的会议[6.14]</option>
		<option value="33">获取我的会议[6.15]</option>
		<option value="16">会议签到[6.16]</option>	
		<option value="17">报名审核[6.17]</option>	
		<option value="23">删除会议笔记[6.18]</option>
		<option value="24">查询会议笔记[6.19]</option>
		<option value="26">修改会议状态[6.20]</option>
		<option value="35">获取用户报名会议必填信息 [6.21]</option>
		<option value="28">修改会议[6.22]</option>
		<option value="25">会议广场列表[6.23]</option>	
		<option value="31">新建自定义会议标签[6.24]</option>
		<option value="32">获取用户的自定义标签列表[6.25]</option>
		<option value="37">会议搜索[6.26]</option>
		<option value="20">我的通知列表[6.27]</option>
		<option value="15">保存会议笔记[6.28]</option>	
		<option value="34">完善报名信息 [6.29]</option>
		<option value="40">删除我的会议[6.31]</option>
	    <option value="41">会议首页[6.31]</option>
		<option value="36">获取用户可转发的会议列表 [6.33]</option>
		<option value="36">获取我的可转发的会议列表[6.36]</option>
	    <option value="42">群聊[6.31]</option>
		<option value="43">社交列表[4.2-newFrame]</option>
		<option value="43_1">新社交列表[4.3_1-newFrame]</option>
		<option value="44_1">获取我的会议列表[4.4_1-newFrame]</option>
		<option value="45_1">获取用户的未读消息数</option>
		<option value="44">获取我的转发社交列表[4.3-newFrame]</option>
		<option value="45">转发会议前获取转发会议的详细信息接口[4.4-newFrame]</option>
		<option value="46">获取行业列表[6.46]</option>
		<option value="47">删除自定义会议标签[6.47]</option>
		<option value="48">删除议题[6.48]</option>
		<option value="49">删除通知[6.49]</option>
		<option value="50">删除邀请函[6.50]</option>
		<option value="51">更新通知为已读状态[6.51]</option>
		<option value="52">根据会议id获取当前用户的会议笔记列表[6.52]</option>
		<option value="53">删除会议成员[6.53]</option>
		<option value="54">保存模板[6.54]</option>
		<option value="55">删除模板[6.55]</option>
		<option value="56">获取用户的模板[6.56]</option>
		<option value="57">批量删除邀请函[6.57]</option>
		<option value="59">大数据推送[6.59]</option>
		<option value="60">热门会议[6.60]</option>
		<option value="61">相似会议[6.61]</option>
		<option value="62">结束议题[6.62]</option>
		<option value="63">会议聊天历史记录[6.63]</option>
		
		<option value="71">社群获取未读消息数getCommunityNewCountByUserId</option>
	</select>
</div>
<div id="urlDiv" >
	测试地址:<input id="url" size="100" value=""/>
</div>
<div id="userIdDiv" >
	当前用户id:<input id="userId" size="100" value="7"/>
	
</div>
<div id="urlDiv" >
	测试地址全路径<input id="allUrl" size="100" value=""/>
</div>
<!-- <div id="" >
	errcode<input id="errCode" size="100" value=""/>
</div>
<div id="" >
	errmessage<input id="errMessage" size="100" value=""/>
</div> -->
<div>
描述:<textarea id="descText" rows="8" cols="120"></textarea>
</div>
<div id="showResultDiv" >
<div style="float: left">
	<div style="margin-top: 100px;float: left">输入参数</div>
	<iframe id="paramIf" width="400" height="330" style="margin-left: 10px;margin-top: 100px" frameborder=1 scrolling=auto src="<%=request.getContextPath() %>/meeting/params/"></iframe>
	</div>
<div style="float: right;margin-right: 180px">
	<iframe id="showResult" style="margin-top: 100px;float: right" width="400" height="330" frameborder=1 scrolling=auto src="<%=request.getContextPath() %>/meeting/result/"></iframe>
	<div style="margin-top: 100px;float: right;margin-right: 50px">输出参数</div>
	<input type="button" style="margin-top: 200px;margin-left: 50px;" onclick="getMethodReturnJsonValue();" value="提交">
	</div>
</div>

</body>

<script type="text/javascript">
var sessionId="";
$(document).ready(function(){
	$("#changeUrlSelect option").each(function(){
		var $_html=$(this).html();
		//$(this).html($(this).val()+": "+ $_html);
	});

	});
var loginInfo = "{'clientID':'客户端串号','clientPassword':'客户端配置登录密码','imei':'手机串号','version':'客户端版本号,四段数字,如1.6.0.0609','platform':'平台,如:iPhone','model':'型号,如:iPhone 3G','resolution':'分辨率,如:480x320','systemName':'系统名称,如:iOS','systemVersion':'系统版本 (版本号用点号分开)','channelID':'渠道id',  'loginString':'登录字符串，对于之前登陆过的用户自动登录时使用','password':'密码'}";
var desc="";
function changeUrl() {
	var selectValue = $("#changeUrlSelect option:selected").val();
	var url = "";
	switch (selectValue) {
	case '0':
		url="";
		 desc="";
		loginInfo="";
		$("#paramIf").contents().find("#paramsDiv").val("");
		break;
	case '1':
		url = "/meeting/add.json";
		 desc="";
		loginInfo = '{"meetingName":"test2","meetingAddress":"朝阳区","meetingAddressPosX":"101","meetingAddressPosY":"101","country":"0","province":"北京","city":"北京","town":"北京","startTime":"2015-09-09 14:00:00","endTime":"2015-10-09 14:00:00","meetingType":0,"meetingStatus":0,"isSecrecy":true,"memberCount":21,"meetingDesc":"123123","createId":2,"createName":null,"taskId":"2","createTime":"2015-09-09 14:00:00","listIndustry":["能源", "公共事业"],"listMeetingMember":[{"memberId":2,"memberType":1,"memberName":"xxj","memberPhoto":"pic.jpg","memberMeetStatus":0,"attendMeetStatus":0,"attendMeetType":0},{"memberId":14386,"memberType":1,"memberName":"xxj","memberPhoto":"/meet/a.jpg","memberMeetStatus":0,"attendMeetStatus":0,"attendMeetType":1},{"memberId":14561,"memberType":1,"memberName":"xxj","memberPhoto":"/meet/a.jpg","memberMeetStatus":0,"attendMeetStatus":0,"attendMeetType":1},{"memberId":14561,"memberType":0,"memberName":"xxj","memberPhoto":"/meet/a.jpg","memberMeetStatus":0,"attendMeetStatus":0,"attendMeetType":1}],"listMeetingPic":[{"picPath":"/meeting/pic/0059187979","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187981","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187989","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-29 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187973","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187975","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187977","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0023145539","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","createDate":"2015-09-09 14:00:00","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187983","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187985","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0059187987","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null},{"picPath":"/meeting/pic/0023145540","picName":null,"picRealName":"DSC_0251.JPG","picDesc":null,"ishomePage":null,"fileIndexId":null,"createUserId":0,"createUserName":"","picStatus":"1","picDel":null,"updateDate":null,"createDateString":"2014-09-28 15:00:00","updateDateString":null}],"listMeetingTime":[{"start_time":"2014-09-28 15:00:00","end_time":"2014-10-28"}],"listMeetingTopicQuery":[{"topicContent":"test","topicStartTime":null,"topicEndTime":"2015-09-09 14:00:00","topicDesc":"sadasd","topicFileName":"asd","topicFilePath":"sad","taskId":"asd"},{"topicContent":"八荣八耻","topicStartTime":null,"topicEndTime":"2015-09-09 14:00:00","topicDesc":"听听八荣八耻","topicFileName":null,"topicFilePath":null,"taskId":null}],"listMeetingData":[{"dataName":"12","dataId":12,"dataType":0,"dataReqType":0,"dataUrl":""},{"dataName":"32","dataId":12,"dataType":1,"dataReqType":0,"dataUrl":"www.baidu.com"}],"listMeetingPeople":[{"peopleId":1,"peopleName":"21","peoplePhoto":"12","peopleDesc":"12","isShare":true,"peopleType":"1"},{"peopleId":2,"peopleName":"22","peoplePhoto":"22","peopleDesc":"22","isShare":"true","peopleType":"1"}],"listMeetingSignLabel":[{"labelName":"姓名","isCustom":"0","createId":"14236","createName":"李昂","createTime":"2015-09-08 14:00:00"}],"listMeetingOrgan":[{"organId":"10","organName":"中科院","organPhoto":"testPhoto","organType":"1"}]}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '2':
		url = "/my/meetingList.json";
		 desc="返回值 type 0 ：会议，1 ：邀请函，2:通知";
		loginInfo = "{ 'memberId':'14561'}";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	/* case '3':
		url = "/meeting/test.json";
		 desc="";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	case '4':
		url = "/member/add.json";
		 desc="";
		loginInfo='{"attendMeetStatus":"0","attendMeetType":0,"meetingId":"633","memberId":"1","memberMeetStatus":0,"memberName":"111","memberPhoto":"111","memberType":0}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '5':
		url = "/my/getMyInvitation.json";
		 desc="";
		loginInfo='{"memberId":"14561"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '6':
		url = "/meeting/getByIdAndMemberId.json";
		 desc='';
		loginInfo='{"id":"633","memberId":"14561"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '7':
		desc="type:0.未答复 1接受邀请2拒绝邀请 5取消参会";
		url = "/member/changeAttendMeetStatus.json";
		loginInfo='{"meetingId":"633","memberId":"14561","type":"0"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '8':
		url = "/member/signUpMeeting.json";
		 desc="";
		loginInfo='{"meetingId":"633","memberId":"14561","memberName":"xxj","memberPhoto":"/meet/a.jpg"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
/* 	case '9':
		url = "/topic/getMeetingTopicList.json";
		 desc="";
		loginInfo='{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	/* case '10':
		url = "/topic/getTopicChatList.json";
		 desc="";
		loginInfo='{"topicId":"20"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	case '11':
		url = "/topic/updateTopic.json";
		 desc="";
		loginInfo='{"id":1257,"meetingId":1326,"topicContent":"陈永梅的第二个议题","topicStartTime":null,"topicEndTime":null,"topicDesc":"","taskId":"nimabide","memberId":21,"memberName":"陈永梅","memberPic":"http://192.168.101.90:5555/img/user/image/?module=user&uId=21&userId=21&imgPath=142059765738000628","memberDesc":"北京金桐网投资有限公司","createId":13363,"createName":"邓燕鸿","createTime":"2015-01-09 11:05:49","updateTime":"2015-01-09 11:05:49","memberImage":"http://192.168.101.90:5555/img/user/image/?module=user&uId=21&userId=21&imgPath=142059765738000628","path":"","listMeetingPic":null,"listMeetingFile":null}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	/* case '12':
		url = "/member/getmeetingMemberList.json";
		 desc="";
		loginInfo='{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '13':
		url = "/member/getmeetingVisitantList.json";
		 desc="";
		loginInfo='{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	 case '14':
		url = "/note/getNoteByMeetingId.json";
		 desc="";
		loginInfo='{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; 
	 case '15':
		url = "/note/addNote.json";
		 desc="";
		loginInfo='{"creater":0,"createTime":null,"meetingChatId":0,"meetingId":"633","meetingNoteTitle":"八荣八只","listMeetingNoteDetail":[{"meetingNoteContent":"八荣八耻1","meetingNoteId":0,"formatedContent":"[八荣八耻1]","taskId":"9987"}]}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; 
	case '16':
		url = "/member/signInMeeting.json";
		 desc="";
		loginInfo='{"meetingId":"633","memberId":"14561","signDistance":"100m" }';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '17':
		url = "/member/signUpReview.json";
		 desc="reviewStatus：审核状态 ：1：同意报名，2：拒绝报名";
		loginInfo='{"meetingId":"633","memberId":"14561","reviewStatus":"1"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '18':
		url = "/meeting/deleteNotBeginMeeting.json";
		 desc="";
		loginInfo='{"meetingId":"635","memberId":"2"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '19':
		url = "/my/changeMyMemberMeetStatus.json";
		 desc="type 0：保存，1：删除 ,\n 删除不是删除会议，知识在我的会议列表不显示，不能删除进行中的";
		loginInfo='{"meetingId":"634","memberId":"124","type":"0"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '20':
		url = "/notice/getNoticeList.json";
		 desc="";
		loginInfo='{"memberId":"13626","meetingId":"1357"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '23':
		url = "/note/deleteNote.json";
		 desc="";
		loginInfo='{"noteId":"6"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '24':
		url = "/note/getNoteById.json";
		 desc="";
		loginInfo='{"noteId":"2"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '25':
		url = "/my/getMeetingSquare.json";
		 desc="";
		loginInfo='{"memberId":"14561","time":"month","location":"","industry":"能源","keyword":"1","client":"ios","position":"118.77147503233,32.054128923368","index":0,"size":10}';
		desc="location='city'或者'other'时,position:'城市名' location='near' 时 position:'经纬度坐标'查询所有会议，按距离远近排序";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '26':
		url = "/meeting/changeMeetingStatus.json";
		 desc="";
		loginInfo='{"meetingId":"633","meetingStatus":"3"}';
		desc="meetingStatus	1：发起,2会议进行中，3会议结束,\n在这里只能发起草稿\n结束会议必须在结束时间到了以后";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	/* case '27':
		url = "/meeting/getFileListByTaskId.json";
		 desc="";
		loginInfo='{"taskId":"taskId-123456"}';
		desc="";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	case '28':
		url = "/meeting/upate.json";
		 desc="";
		loginInfo='{"attendMeetingCount":0,"city":"北京市","country":0,"createId":1151,"createImage":"http://211.103.198.41:4445//get/image/?url=/webserver/upload/web1/90/1499162836052.jpg","createName":"祁磊","endTime":"2015-01-08 20:39:08","id":1151,"isSecrecy":false,"isSignUp":false,"listMeetingData":[{"dataId":1570,"dataName":"不算了","dataReqType":12,"dataType":0,"id":0,"meetingId":1151},{"dataId":1381349,"dataName":"印尼海军称在航班失联海域打捞出超过40具遗体","dataReqType":1,"dataType":1,"id":0,"meetingId":1151}],"listMeetingMember":[{"attendMeetStatus":2,"attendMeetType":0,"excuteMeetSign":0,"id":16266,"isSign":0,"meetingId":1151,"memberId":13423,"memberMeetStatus":0,"memberName":"祁磊","memberPhoto":"http://211.103.198.41:4445//get/image/?url=/webserver/upload/web1/90/1499162836052.jpg","memberType":2},{"attendMeetStatus":1,"attendMeetType":0,"excuteMeetSign":0,"id":16267,"isSign":0,"meetingId":1151,"memberId":13924,"memberMeetStatus":0,"memberName":"大数据量","memberPhoto":"http://192.168.101.90:5555/img/user/image/?module=user&uId=13924&userId=13924&imgPath=default","memberType":0}],"listMeetingPeople":[{"peopleId":141446105004305090,"peopleName":"阿代","peoplePhoto":"http://192.168.101.90:5555/img/user/image/?module=people&userId=141446105004305087&uId=13423","meetingId":1151,"peopleType":"1"}],"listMeetingSignLabel":[],"listMeetingTime":[{"meetingId":1151,"endTime":"2015-01-08 20:39:08","startTime":"2015-01-08 18:39:08"}],"listMeetingTopicQuery":[{"createId":1151,"createName":"祁磊","createTime":"2015-01-08 22:29:21","id":0,"meetingId":1151,"memberId":13924,"memberName":"大数据量","memberPic":"http://192.168.101.90:5555/img/user/image/?module=user&uId=13924&userId=13924&imgPath=default","taskId":"nimabide","topicContent":"吞吞吐吐","topicDesc":"阿诗丹顿","topicEndTime":"2015-01-08 00:26:33","topicStartTime":"2015-01-08 22:29:54","updateTime":"2015-01-08 22:29:21"}],"meetingAddress":"二里庄","meetingAddressPosX":"116.373763","meetingAddressPosY":"40.001133","meetingDesc":"4G啊给丁丁地图哈让他把突然","meetingName":"玉米现货","meetingStatus":0,"meetingType":1,"memberCount":100,"memberMeetStatus":0,"province":"北京市","signInCount":0,"startTime":"2015-01-08 18:39:08","town":"海淀区","type":0}';
		desc="";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '29':
		url = "/meeting/deleteNotBeginMeeting.json";
		 desc="";
		loginInfo='{"meetingId":"22","memberId":"14561"}';
		desc="";
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	/* case '30':
		url = "/note/addNoteDetail.json";
		 desc="";
		loginInfo='{"meetingNoteContent":"八荣八耻1","meetingNoteId":12}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break; */
	case '31':
		url = "/label/addMeetingLabel.json";
		 desc="";
		loginInfo='{"labelName":"街道","createId":14356,"createName":"李昂","createTime":"2012-12-12"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '32':
		url = "/label/getMeetingLabelByCreateId.json";
		 desc="";
		loginInfo='{ "memberId":12}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '33':
		url = "/my/MyMeeting.json";
		 desc="";
		loginInfo='{"memberId":"14561","type":"2","keyword":"","index":"0","size":"10","memberType":"0"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '34':
		url = "/member/improveSignInformation.json";
		 desc="";
		loginInfo='{"listMeetingSignLabelDataQuery":[{"id":null,"mslabelId":2,"mslabelName":"姓名","labelContent":"test","memberId":14561,"memberName":"姓名","isCustomer":0,"isPeopleData":0,"createId":null,"createName":null,"createTime":null}]}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '35':
		url = "/member/getRequiredSignupInfo.json";
		 desc="";
		loginInfo='{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '36':
		url = "/my/getMyForwardingMeeting.json";
		 desc="";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '37':
		url = "/meeting/seach.json";
		 desc="";
		loginInfo='{"keyword":"","index":1,"size":10,"hlpre":"<a>","hlext":"</a>","fg":8}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '38':
		url = "/note/addNoteDetailByChat.json";
		desc="";
		loginInfo='{"meetingId":"919","taskId":"taskId-123456","messageId":"706399D69F64063B"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '39':
		url = "/topic/enteredMeetingNotice.json";
		desc="";
		loginInfo='{"meetingId":"1084","topicId":"972"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '40':
		url = "/meeting/delete.json";
		desc="";
		loginInfo='{"meetingId":"1084,1091","memberId":"7"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '41':
		url = "/meeting/index.json";
		desc="";
		loginInfo='{"user_id":10001}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '42':
		url = "/meeting/groupchat.json";
		desc="";
		loginInfo='{"user_id":10001}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '43':
		url = "/meeting/socialList.json";
		desc="社交列表";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '43_1':
		url = "/meeting/socialList2.json";
		desc="新社交列表";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '44_1':
		url = "/meeting/meetingList.json";
		desc="获取我的会议列表";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '45_1':
		url = "/meeting/fetchNewCount.json";
		desc="获取用户的未读消息数";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '44':
		url = "/meeting/getMyForwardingSocial.json";
		desc="获取我的转发社交列表";
		loginInfo='';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '45':
		url = "/meeting/getForwardingMeetingData.json";
		desc="转发会议前获取转发会议的详细信息接口";
		loginInfo='{"meetingId":1092,"topicId":980}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '46':
		url = "/meeting/getMeetingIndustryData.json";
		desc="获取会议的行业列表接口";
		break;
	case '47':
		url = "/label/deleteMeetingLabel.json";
		desc = "删除自定义会议标签";
		loginInfo='{"labelId":1}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '48':
		url = "/topic/deleteTopic.json";
		desc = "•删除议题";
		loginInfo='{"topicId":1}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '49':
		url = "/notice/deleteNotice.json";
		desc = "•删除通知";
		loginInfo='{"meetingId":"1091","noticeId":""}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '50':
		url = "/my/deleteMyInvitation.json";
		desc = "•删除邀请函";
		loginInfo='{"meetingId":"1866","memberId":"7"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '51':
		url = "/notice/updateNoticeReadState.json";
		desc = "•更新通知为已读状态";
		loginInfo='{"meetingId":"1091","noticeId":""}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '52':
		url = "/note/getNoteListByMeetingId.json";
		desc = "";
		loginInfo = '{"meetingId":"633"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '53':
		url = "/member/deleteMeetingMember.json";
		desc = "";
		loginInfo = '{"meetingId":"633","memberId":"21"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '54':
		url = "/template/save.json";
		desc = "包含模块有邀请参会人、邀请主讲人、填写报名信息，多个用逗号隔开";
		loginInfo = '{"templateName":"测试模板","userId":"21","modules":"1,2,3"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '55':
		url = "/template/delete.json";
		desc = "删除单个模板只需id参数，删除用户的全部模板传userId";
		loginInfo = '{"id":"2100","userId":"21"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '56':
		url = "/template/getByUserId.json";
		desc = "";
		loginInfo = '{"userId":"21"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '57':
		url = "/my/deleteMyInvitationBatch.json";
		desc="参数为邀请函ID，多个用逗号隔开";
		loginInfo='{"id":"111,112"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '59':
		url = "/meeting/getBigData.json";
		desc = "";
		loginInfo = '{"userId":"4","targetId":"1153","page":"1","rows":"10","scope":"2"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '60':
		url = "/meeting/hotList.json";
		desc = "";
		loginInfo = '{"page":"1","rows":"4","type":"1"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '61':
		url = "/meeting/getSimilarMeeting.json";
		desc = "";
		loginInfo = '{"targetId":"1153","page":"1","rows":"4"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '62':
		url = "/topic/finishTopic.json";
		desc="参数为议题ID，多个用逗号隔开";
		loginInfo='{"topicId":"979"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '63':
		url = "/meeting/getMeetingMessage.json";
		desc="会议ID不能为空";
		loginInfo='{"meetingId":"1103","topicId":"993","page":"1","size":"10","time":"2014-12-22"}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	case '71':
		url = "/meeting/getCommunityNewCountByUserId/13594";
		loginInfo='{}';
		$("#paramIf").contents().find("#paramsDiv").val(loginInfo);
		break;
	}
	$("#url").val(url);
	$("#allUrl").val('<%=basePath %>'+url);
	$("#descText").html(desc);
}

function getMethodReturnJsonValue() {
	var selectValue = $("#changeUrlSelect option:selected").val();
	if(selectValue=='0'){
		alert("请选择");
		return;
	}
	loginInfo=$("#paramIf").contents().find("#paramsDiv").val();
	var url = $("#url").val();
	var user = $("#userId").val();
	console.info("userid="+user);
	var jtNickName="test";
	url='<%=request.getContextPath()%>'+url;
	$.ajax({
		url : url,
		data :  loginInfo,
		beforeSend: function(xhr){
			xhr.setRequestHeader('sessionID', "eXNzLTM0MzQtZHNmNTUtMjIyNTYzMTQxNDE1MjgwNDM5NTMz");
			xhr.setRequestHeader('jtNickName',jtNickName);	
			xhr.setRequestHeader('jtUserId', user);
		},
		async : false,
		type : 'POST',
		dataType : "text",
		cache : false,
		success : function(data,statusText, jqXHR) {
			console.info(data);
			sessionId=jqXHR.getResponseHeader("sessionID");
			$("#showResult").contents().find("#resultDiv").val(data);
			//$("#resultDiv").html(data);
		}, 
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            debugger;
			alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
	});
	
}
</script>
</html>

