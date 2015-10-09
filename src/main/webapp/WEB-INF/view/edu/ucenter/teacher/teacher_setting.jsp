<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>教师资料设置</title>
  <script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/teacher/u_teacher.js"></script>

  <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>

  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

  <link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify-3.1/uploadify.css?v=${v}" />
  <script type="text/javascript" src="${ctximg}/static/common/uploadify-3.1/jquery.uploadify.min.js?v=${v}"></script>
  <script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>


  <script type="text/javascript">
    var materialType = '${materialType}';
    var webSitewritetoken = '${webSiteMap}';
    $(function(){
      // 初始化图片上传
      uploadPicLoad();
      // 视频上传插件
      uploadVideoLoad();
      // 富文本编辑器
      kindeditInit('teachingSuccess');
      kindeditInit('teachingLive');
      kindeditInit('peculiarity');
      //时间插件
      $("#birthday").datepicker(
        {
          yearRange: '1960:+0',
          regional:"zh-CN",
          changeYear: true,
          changeMonth: true,
          dateFormat:"yy-mm-dd "
        });
      var degreeId = $("#hiddendegree").val();
      if (degreeId != 0){
        $("#degreetext").html($("#degreeId_" + degreeId).text());
      }
      if(materialType == ''){
        materialType = "base";
      }
      changeTabs(materialType);
    })
  </script>
</head>
<body>
<input type="hidden" name="teacher.id" value="${teacher.id}" id="teacherId"/>
<input type="hidden" name="teacher.userId" value="${teacher.userId}" id="teacherUserId"/>
<input type="hidden" name="teacher.teacherSubject" value="" id="teacherSubject"/>
<input type="hidden" name="teacher.teacherMajor" value="" id="teacherMajor"/>
<input type="hidden" id="hiddendegree" value="${teacher.degree}"/>

<input type="hidden" name="teacher.cardStatus" id="card_status" value="${teacher.cardStatus}" />
<input type="hidden" name="teacher.educationStatus" id="education_status" value="${teacher.educationStatus}" />
<input type="hidden" name="teacher.teachingStatus" id="teaching_status" value="${teacher.teachingStatus}" />
<input type="hidden" name="teacher.specialtyStatus" id="specialty_status" value="${teacher.specialtyStatus}" />
<div class="u-m-right">
  <article class="u-m-center">
    <section class="u-m-c-wrap">
      <section class="u-m-c-head">
        <ul id="uTabTitle" class="fl u-m-c-h-txt of">
          <li class="current uHover" onclick="changeTabs('base')" id="base">
            <a title="基本信息" href="javascript:void(0)">基本信息</a>
          </li>
          <li class="uHover" onclick="changeTabs('senior')" id="senior">
            <a title="高级信息" href="javascript:void(0)">高级信息</a>
          </li>
        </ul>
        <div class="clear"></div>
      </section>
      <div id="uTabCont" class="mt30">

        <!-- 基本资料开始 -->
        <article id="teacher_material_baseinfo">
          <section class="mb50">
            <div class="right-person-box">

              <!-- 基本资料开始 -->
              <div class="right-person-box-in mb30" id="teacher_material_base">
                <div class="right-person-box-in-l fl">
                  <p>
                    <tt>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</tt>
                    <tt>${teacher.userExpand.realname}</tt>
                  </p>
                  <p>
                    <tt>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</tt>
                    <tt>${teacher.userExpand.nickname}</tt>
                  </p>
                  <p>
                    <tt>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</tt>
                    <tt>${teacher.sex == 0 ? "男" : "女"}</tt>
                  </p>
                  <p>
                    <tt>出生日期：</tt>
                    <tt>
                      <c:if test="${teacher.userExpand.birthday != null && teacher.userExpand.birthday != ''}">
                        <fmt:formatDate value="${teacher.userExpand.birthday}" pattern="yyyy-MM-dd"/>
                      </c:if>
                      <c:if test="${teacher.userExpand.birthday == null || teacher.userExpand.birthday == ''}">
                        1990-01-01
                      </c:if>
                    </tt>
                  </p>
                  <%--<p>
                    <tt>省&nbsp;&nbsp;市&nbsp;&nbsp;区：</tt>
                    <tt>北京市朝阳区</tt>
                  </p>
                  <p>
                    <tt>常住地址：</tt>
                    <tt>北京朝阳区北四环中路</tt>
                  </p>--%>
                  <p>
                    <tt>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</tt>
                    <tt>
                      <c:if test="${teacher.degree==0}">无</c:if>
                      <c:if test="${teacher.degree==1}">高中以下</c:if>
                      <c:if test="${teacher.degree==2}">高中或中专</c:if>
                      <c:if test="${teacher.degree==3}">大专</c:if>
                      <c:if test="${teacher.degree==4}">本科</c:if>
                      <c:if test="${teacher.degree==5}">研究生</c:if>
                      <c:if test="${teacher.degree==6}">博士及以上</c:if>
                    </tt>
                  </p>
                  <p>
                    <tt>毕业院校：</tt>
                    <tt>${teacher.finishSchool}</tt>
                  </p>
                  <p>
                    <tt>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</tt>
                    <tt>${teacher.profession}</tt>
                  </p>
                  <p>
                    <tt>教授阶段：</tt>
                    <tt>${fn:replace(teacher.subjects, ',', '、')}</tt>
                  </p>
                  <p>
                    <tt>教授科目：</tt>
                    <tt>${fn:replace(teacher.majors, ',', '、')}</tt>
                  </p>
                  <p>
                    <tt>隐私模式：</tt>
                    <tt>
                      ${teacher.userExpand.hideStatus == 1 ? "开启" : "关闭"}
                      &nbsp;&nbsp;<span style="color:red; font-size: 13px">(隐私模式开启--不显示姓名和头像，显示昵称)</span></tt>
                  </p>
                </div>
                <div class="right-person-box-in-r fr u-teaname-btn">
                  <a href="javascript:void(0)" onclick="onclickSetting('base')">编辑</a>
                </div>
                <div class="clear"></div>
              </div>
              <!-- 基本资料结束 -->

              <!-- 基本资料编辑开始 -->
              <div class="right-person-box-infor" id="teacher_material_setting_base" style="display:none">
                <ol class="password-body-boy">
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">姓名：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" id="realname" value="${teacher.userExpand.realname}" maxlength="" name="teacher.userExpand.realname">
                      <em class="vam fl ml10 mt13 pr">&nbsp;
                        <tt class="fsize14 f-fM c-red-1 pa" style="top:-9px;right:-102px;" id="realnameError"></tt>
                      </em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">昵称：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <input  class="input-1 fl" type="text" id="user_nickname" value="${teacher.userExpand.nickname}" maxlength="" name="teacher.userExpand.nickname">
                      <tt class="fsize14 f-fM c-999 pa" style="top:5px;right:-180px;">昵称在隐藏模式开启后显示</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">性别：</tt>
                    </span>
                    <span class="vam pa-bo-boy-txt pa-bo-boy-txt-1">
                        <input type="radio" style="margin: 0 0 0 10px;" placeholder="" ${teacher.sex != 1 ? "checked='checked'" : ""} value="0" id="gender1" name="sex">
                        <label class="c-666 fsize14 f-fM">男</label>
                        <input type="radio" style="margin: 0 0 0 30px;" placeholder="" value="1" ${teacher.sex == 1 ? "checked='checked'" : ""} id="gender0" name="sex">
                        <label class="c-666 fsize14 f-fM">女</label>
                    </span>
                    <div class="clear"></div>
                  </li>
                  <%--<li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">省市区：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <div class="selectUI fl">
                        <div class="job-select" style="width:120px;z-index:999;">
                          <section class="j-s-defalt">
                            <em class="icon14 fr mt5">&nbsp;</em>
                            <span>河北</span>
                          </section>
                          <section style="display: none;" class="j-s-option">
                            <div class="j-s-o-box">
                              <p><a href="javascript: void(0)" title="">北京</a></p>
                              <p><a href="javascript: void(0)" title="">天津</a></p>
                              <p><a href="javascript: void(0)" title="">上海</a></p>
                            </div>
                          </section>
                        </div>
                      </div>
                      <div class="selectUI fl">
                        <div style="width:120px;z-index:999;" class="job-select">
                          <section class="j-s-defalt">
                            <em class="icon14 fr mt5">&nbsp;</em>
                            <span>北京市</span>
                          </section>
                          <section class="j-s-option">
                            <div class="j-s-o-box">
                              <p><a href="javascript: void(0)" title="">石家庄市</a></p>
                              <p><a href="javascript: void(0)" title="">天津市</a></p>
                              <p><a href="javascript: void(0)" title="">上海市</a></p>
                            </div>
                          </section>
                        </div>
                      </div>
                      <div class="selectUI fl">
                        <div style="width:120px;z-index:999;" class="job-select">
                          <section class="j-s-defalt">
                            <em class="icon14 fr mt5">&nbsp;</em>
                            <span>朝阳区</span>
                          </section>
                          <section class="j-s-option">
                            <div class="j-s-o-box">
                              <p><a href="javascript: void(0)" title="">昌平区</a></p>
                              <p><a href="javascript: void(0)" title="">桥东区</a></p>
                              <p><a href="javascript: void(0)" title="">桥西区</a></p>
                            </div>
                          </section>
                        </div>
                      </div>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">常住地址：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" value="" maxlength="" name="">
                      <em class="Correct vam ml5 fl mt13">&nbsp;</em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>--%>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">学历：</tt>
                    </span>
                    <label class="pa-bo-boy-txt" id="degree_text">
                      <div class="selectUI fl">
                        <div class="job-select" style="width:150px;z-index:99;">
                          <section class="j-s-defalt">
                            <em class="icon14 fr mt5">&nbsp;</em>
                            <span id="degreetext">其他</span>
                          </section>
                          <section style="display: none;" class="j-s-option">
                            <div class="j-s-o-box">
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(1)" id="degreeId_1">高中以下</a></p>
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(2)" id="degreeId_2">高中或中专</a></p>
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(3)" id="degreeId_3">大专</a></p>
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(4)" id="degreeId_4">本科</a></p>
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(5)" id="degreeId_5">研究生</a></p>
                              <p><a href="javascript: void(0)" title="" onclick="checkdegree(6)" id="degreeId_6">博士</a></p>
                            </div>
                          </section>
                        </div>
                      </div>
                      <div class="clear"></div>
                    </label>
                    <em class="vam fl ml10 mt13 pr">&nbsp;
                      <tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-50px;" id="degreeError"></tt>
                    </em>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">毕业院校：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" id="finishSchool" value="${teacher.finishSchool}" maxlength="" name="teacher.finishSchool">
                      <em class="vam ml5 fl mt13">&nbsp;
                        <tt class="fsize14 f-fM c-red-1 pa" style="right:-180px;" id="finishSchoolError"></tt>
                      </em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">专业：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" id="profession" value="${teacher.profession}" maxlength="" name="teacher.profession">
                      <em class="vam ml5 fl mt13">&nbsp;
                        <tt class="fsize14 f-fM c-red-1 pa" style="right:-180px;" id="professionError"></tt>
                      </em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">出生日期：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" readonly="readonly" id="birthday" value="<fmt:formatDate value='${teacher.userExpand.birthday}' pattern='yyyy-MM-dd'/>" maxlength="" name="teacher.userExpand.birthday">
                      <em class="vam ml5 fl mt13">&nbsp;
                        <tt class="fsize14 f-fM c-999 pa" style="top:13px;right:-185px;" id="birthdayError"></tt>
                      </em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教授阶段：</tt>
                    </span>
                    <label class="pa-bo-boy-txt" style="width:440px;">
                      <c:forEach items="${twoSubject}" var="grade">
                        <span class="disIb mr20">
                        	<input type="checkbox" name="grade" ${grade.checkSelected == 'true' ? "checked='checked'" : ""} class="c-icon" id="${grade.subjectId}" value="${grade.subjectId}">
                        	<label class="c-666 fsize14 f-fM">${grade.subjectName}</label>
                        </span>
                      </c:forEach>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教授科目：</tt>
                    </span>
                    <label class="pa-bo-boy-txt" style="width:440px;">
                      <c:forEach items="${majors}" var="major">
                        <span class="disIb mr20">
                        	<input type="checkbox" name="major" ${major.checkSelected == 'true' ? "checked='checked'" : ""} class="c-icon" id="${major.id}" value="${major.id}">
                        	<label class="c-666 fsize14 f-fM mr20">${major.name}</label>
                        </span>
                      </c:forEach>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">隐私模式：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input type="radio" name="hideStatus" ${teacher.userExpand.hideStatus == 1 ? "checked='checked'" : ""} class="c-icon" value="1">
                      <label class="c-666 fsize14 f-fM mr20" for="">开启</label>&nbsp;&nbsp;
                      <input type="radio" name="hideStatus" ${teacher.userExpand.hideStatus == 0 ? "checked='checked'" : ""} class="c-icon" value="0">
                      <label class="c-666 fsize14 f-fM mr20" for="">关闭</label>
                      <span style="color:red; font-size: 13px">(隐私模式开启--不显示姓名和头像，显示昵称)</span>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a class="c-btn bing-btn ml100" href="javascript:void(0)" onclick="baseInfoSetting()">
                      确定
                    </a>
                    <a class="c-btn bing-btn-fq ml40" href="javascript:void(0)" onclick="cancelOnclick('base')">
                      取消
                    </a>
                  </li>
                </ol>
              </div>
              <!-- 基本资料编辑结束 -->

            </div>
          </section>
        </article>
        <!-- 基本资料结束 -->

        <!-- 高级资料开始 -->
        <article id="teacher_material_seniorinfo" style="display:none;">
          <section class="mb50">
            <div class="right-person-box">
              <!-- 高级资料展示开始 -->
              <div class="right-person-box-in mb30" id="teacher_material_senior">
                <div class="right-person-box-in-l fl">
                  <p>
                    <tt>教&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</tt>
                    <tt>${teacher.seniority}&nbsp;年</tt>
                  </p>
                  <div class="u-t-jsxq mt20">
                    <p class="u-t-jsxq-tit">
                      <tt>教学宣言：</tt>
                    </p>
                    <p class="u-t-jsxq-in">
                      ${teacher.teachingEnounce}
                    </p>
                  </div>
                  <div class="u-t-jsxq mt20">
                    <p class="u-t-jsxq-tit">
                      <tt>教学特点：</tt>
                    </p>
                    <p class="u-t-jsxq-in">
                      ${teacher.peculiarity}
                    </p>
                  </div>
                  <div class="u-t-jsxq mt20">
                    <p class="u-t-jsxq-tit">
                      <tt>工作/学习经历：</tt>
                    </p>
                    <p>
                      ${teacher.teachingLive}
                    </p>
                  </div>
                  <div class="u-t-jsxq mt20">
                    <p class="u-t-jsxq-tit">
                      <tt>成果展示：</tt>
                    </p>
                    <p>
                      ${teacher.teachingSuccess}
                    </p>
                  </div>
                  <div class="mt20">
                    <p class="u-t-jsxq-tit pb20">
                      <tt>视频展示：<span class="fsize12 c-999 f-fM">(最多上传3个视频，重新上传时，请先删除再上传)</span></tt>
                    </p>
                    <ul class="clearfix e-b-tp-list u-t-jsxq">
                      <c:forEach items="${videoList}" var="video">
                      <li style="margin-bottom:30px;margin-right:30px;height:150px;width:150px">
                        <a class="tac txtOf mr30" href="javascript:dialog('${video.videoUrl}',20,'','')">
                          <img style="height:150px;width:150px" class="videoss" src="${video.imageUrl}" alt="${video.name}">
                          <em class="icon-bf pa">&nbsp;</em>
                          <div class="teach-fc-bg-shot pa"></div>
                        </a>
                        <a href="javascript:void(0)" onClick="clearPic(this, ${video.id})" class="close-eva"></a>
                      </li>
                      </c:forEach>
                      <li id="video_last" class="last last-1">
                        <section class="uploadBtnWrap pr">
                          <div class="ke-inline-block ">
                            <span class="">
                                <input type="button" id="fileupload_video" value="添加" class="">
                            </span>
                          </div>
                        </section>
                      </li>
                    </ul>
                    <div class="clear"></div>
                  </div>
                  <div class="u-t-jsxq mt20">
                    <p class="u-t-jsxq-tit pb20">
                      <tt>图片展示：<span class="fsize12 c-999 f-fM">(最多上传3个图片，重新上传时，请先删除再上传)</span></tt>
                    </p>
                    <!-- 图片展示 -->
                    <ul class="clearfix e-b-tp-list u-t-jsxq">
                      <c:forEach items="${imageList}" var="imgs">
                        <li style="margin-bottom:30px;margin-right:30px;height:150px;width:150px">
                          <img style="height:150px;width:150px" class="imgss" src="<%=staticImageServer%>${imgs.imageUrl}" alt="" title="${imgs.name}" lang="${imgs.type}">
                          <a href="javascript:void(0)" onClick="clearPic(this, ${imgs.id})" class="close-eva"></a>
                        </li>
                      </c:forEach>
                      <li id="last" class="last last-1">
                        <section class="uploadBtnWrap pr">
                          <div class="ke-inline-block ">
                            <span class="">
                                <input type="button" id="fileupload_img" value="添加" class="">
                            </span>
                          </div>
                        </section>
                      </li>
                    </ul>
                    <!-- 图片展示 -->
                    <div class="clear"></div>
                  </div>
                </div>
                <div class="right-person-box-in-r fr u-teaname-btn">
                  <a href="javascript:void(0)" onclick="onclickSetting('senior')">编辑</a>
                </div>
                <div class="clear"></div>
              </div>
              <!-- 高级资料展示结束 -->

              <!-- 高级资料编辑开始 -->
              <div class="right-person-box-infor" id="teacher_material_setting_senior" style="display: none;">
                <ol class="password-body-boy">
                  <li>
                      <span class="vam pa-bo-boy-tit">
                          <tt class="fisze20 c-org f-fM vam">*</tt>
                          <tt class="fsize14 c-666 f-fM vam">教龄(年)：</tt>
                      </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" value="${teacher.seniority}" maxlength="" name="teacher.seniority" id="seniority">
                      <em class="vam fl ml10 mt13 pr">&nbsp;
                        <tt class="fsize14 f-fM c-red-1 pa" style="top:-9px;right:-102px;" id="seniorityError"></tt>
                      </em>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教学宣言：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <textarea class="vam" name="" maxlength="50" style="width:300px;height:70px" id="teachingEnounce" placeholder="请输入您的教学宣言">${teacher.teachingEnounce}</textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:20px;right:-78px;">50字以内</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教学特点：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <textarea class="vam" name="" maxlength="" id="peculiarity">${teacher.peculiarity}</textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:40px;right:-78px;">300字以内</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">工作/学习经历：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <textarea class="vam" name="" maxlength="" id="teachingLive">${teacher.teachingLive}</textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:40px;right:-78px;">300字以内</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教学成功经历：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <textarea class="vam" name="" maxlength="" id="teachingSuccess">${teacher.teachingSuccess}</textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:40px;right:-78px;">300字以内</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a class="c-btn bing-btn ml100" href="javascript:void(0)" onclick="seniorInfoSetting()">
                      确定
                    </a>
                    <a class="c-btn bing-btn-fq ml40" href="javascript:void(0)" onclick="cancelOnclick('senior')">
                      取消
                    </a>
                  </li>
                </ol>
              </div>
              <!-- 高级资料编辑结束 -->

            </div>
          </section>
        </article>
        <!-- 高级资料结束 -->

      </div>
    </section>
  </article>
</div>
</body>
</html>
