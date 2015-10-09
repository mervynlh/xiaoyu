<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
  <link rel="stylesheet" href="${ctximg}/static/edu/css/ucenter.css"/>
  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
  <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

  <script src="${ctximg}/static/edu/js/front/teacher/teacher_ingormation.js"></script>

  <link rel="stylesheet" type="text/css" media="screen" href="/static/common/uploadify/uploadify.css?v=${v}" />
  <script type="text/javascript" src="/static/common/uploadify/swfobject.js?v=${v}"></script>
  <script type="text/javascript" src="/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>
  <script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js?v=<%=version%>"></script>


  <script type="text/javascript">
    $(function(){
      //时间插件
      $("#birthday").datepicker(
        {
          yearRange: '1960:+0',
          regional:"zh-CN",
          changeYear: true,
          changeMonth: true,
          dateFormat:"yy-mm-dd "
        });
      // 富文本编辑器
      kindeditInit('teachingSuccess');
      kindeditInit('teachingLive');
      kindeditInit('peculiarity');
    })
  </script>
</head>
<body>
<input type="hidden" name="teacher.id" value="${teacher.id}" id="teacherId"/>
<input type="hidden" name="teacher.userId" value="${teacher.userId}" id="teacherUserId"/>
<input type="hidden" name="teacher.teacherSubject" value="" id="teacherSubject"/>
<input type="hidden" name="teacher.teacherMajor" value="" id="teacherMajor"/>
<input type="hidden" id="hiddendegree" value="${teacher.degree}"/>

<input type="hidden" name="teacher.userExpand.avatar" id="imagesUrl" value="" />
<input type="hidden" name="teacher.card" id="card_imagesUrl" value="" />
<input type="hidden" name="teacher.education" id="education_imagesUrl" value="" />
<input type="hidden" name="teacher.teaching" id="teaching_imagesUrl" value="" />
<input type="hidden" name="teacher.specialty" id="specialty_imagesUrl" value="" />

<section class="container">
  <div class="u-m-right">
    <article class="u-m-center">
      <section class="u-m-c-wrap">
        <div class="u-t-inws-bg u-t-inws-bg-1"></div>
        <div style="padding-left:120px;" class="u-t-intion-box">
          <!-- 基本资料 -->
          <article class="intion-box-in" id="teacher_info_1">
            <div class="intion-box-in-tit mt50">
              <p class="hLh36">
                <tt class="fsize20 c-333 f-fM">1.</tt>
                <tt class="fsize16 c-333 f-fM">基本信息</tt>
              </p>
            </div>
            <div class="intion-box-in-boy">
              <div id="select-1" class="i-b-i-b-l fl">
                <ol class="password-body-boy">
                  <li>
                    <span class="vam pa-bo-boy-tit">
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
                        <tt class="fsize14 c-666 f-fM vam">昵称：</tt>
                    </span>
                    <label class="pa-bo-boy-txt pr">
                      <input  class="input-1 fl" type="text" id="user_nickname" value="${teacher.userExpand.nickname}" maxlength="" name="teacher.userExpand.nickname">
                      <tt class="fsize14 f-fM c-999 pa" style="top:13px;right:-185px;">昵称在隐藏模式开启后显示</tt>
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
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fsize14 c-666 f-fM vam">学历：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <div class="selectUI fl">
                        <div class="job-select" style="width:150px;z-index:99;">
                          <section class="j-s-defalt">
                            <em class="icon14 fr mt5">&nbsp;</em>
                            <span>其他</span>
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
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
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
                        <tt class="fsize14 c-666 f-fM vam">教授阶段：</tt>
                    </span>
                    <div class="pa-bo-boy-txt" style="width:500px;">
                      <c:forEach items="${twoSubject}" var="grade">
                        <span class="disIb mr20">
                          <input type="checkbox" name="grade" ${grade.checkSelected == 'true' ? "checked='checked'" : ""} class="c-icon" id="${grade.subjectId}" value="${grade.subjectId}">
                          <label class="c-666 fsize14 f-fM mr20">${grade.subjectName}</label>
                        </span>
                      </c:forEach>
                    </div>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教授科目：</tt>
                    </span>
                    <div class="pa-bo-boy-txt" style="width:440px;">
                      <c:forEach items="${majors}" var="major">
                        <span class="disIb mr20">
                          <input type="checkbox" name="major" ${major.checkSelected == 'true' ? "checked='checked'" : ""} class="c-icon" id="${major.id}" value="${major.id}">
                          <label class="c-666 fsize14 f-fM mr20">${major.name}</label>
                        </span>
                      </c:forEach>
                    </div>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <span class="vam pa-bo-boy-tit">
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
                </ol>
              </div>
              <div class="i-b-i-b-r mt30 fl">
                <div>
                  <p class="fsize14 f-fM c-666 mb20">头像</p>
                  <div class="tx tac">
                    <img alt="" src="/static/edu/img/avatar-boy.gif" id="defaultavatar">
                    <p class="fsize14 f-fM c-999 mt10">160X160</p>
                  </div>
                </div>
                <div class="mt50 ml30">
                  <div style="width:200px;height:200px;" class="sftp pr">

                    <img alt="" src="" height="200" width="200" id="picpath" style="display: none">
                    <a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
                      <input type="file" id="fileuploadButton"/>
                    </a>

                  </div>
                </div>
              </div>
              <div class="clear"></div>
              <div class="pt30">
                <ol class="password-body-boy">
                  <li class="tac">
                    <a class="c-btn bing-btn" href="javascript:void(0)" onclick="onclickNext(2)">
                      下一步
                    </a>
                  </li>
                </ol>
              </div>
            </div>
          </article>
          <!-- 基本资料 -->
          <!-- 高级资料 -->
          <article class="intion-box-in" id="teacher_info_2" style="display:none">
            <div class="intion-box-in-tit mt50">
              <p class="hLh36">
                <tt class="fsize20 c-333 f-fM">2.</tt>
                <tt class="fsize16 c-333 f-fM">高级信息</tt>
              </p>
            </div>
            <div class="intion-box-in-boy">
              <div style="width:100%;" id="select-2" class="i-b-i-b-l">
                <ol class="password-body-boy">
                  <li>
                    <span class="vam pa-bo-boy-tit">
                        <tt class="fisze20 c-org f-fM vam">*</tt>
                        <tt class="fsize14 c-666 f-fM vam">教龄(年)：</tt>
                    </span>
                    <label class="pa-bo-boy-txt">
                      <input  class="input-1 fl" type="text" placeholder="请输入您的教龄" maxlength="" name="teacher.seniority" id="seniority">
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
                      <textarea class="vam" name="" maxlength="50" style="width:300px;height:70px" id="teachingEnounce" placeholder="请输入您的教学宣言"></textarea>
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
                      <textarea class="vam" name="" maxlength="" id="peculiarity" placeholder="请输入您的教学特点"></textarea>
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
                      <textarea class="vam" name="" maxlength="" id="teachingLive" placeholder="请输入您的工作/学习经历"></textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:40px;right:-78px;">500字以内</tt>
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
                      <textarea class="vam" name="" maxlength="" id="teachingSuccess" placeholder="请输入您的教学成功经历"></textarea>
                      <tt class="fsize14 f-fM c-999 pa" style="top:40px;right:-78px;">300字以内</tt>
                      <div class="clear"></div>
                    </label>
                    <div class="clear"></div>
                  </li>
                </ol>
              </div>
              <div class="pt30">
                <ol class="password-body-boy">
                  <li class="tac">
                    <a class="c-btn bing-btn" href="javascript:void(0)" onclick="onclickNext(3)">
                      下一步
                    </a>
                  </li>
                </ol>
              </div>
            </div>
          </article>
          <!-- 高级资料 -->
          <!-- 资质认证 -->
          <article class="intion-box-in" id="teacher_info_3" style="display:none">
            <div class="intion-box-in-tit mt50">
              <p class="hLh36">
                <tt class="fsize20 c-333 f-fM">3.</tt>
                <tt class="fsize16 c-333 f-fM">资料认证</tt>
              </p>
            </div>
            <div class="intion-box-in-boy">
              <div style="width:100%;" id="select-3" class="i-b-i-b-l">
                <ul class="clearfix sczl-list">
                  <li>
                    <p class="tac fsize14 f-fM c-999">身份证</p>
                    <div class="sftp pr mt10" style="width:200px;height:200px">

                        <img alt="" src="" height="200" width="200" id="card_img" style="display: none">
                        <a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
                          <input type="file" id="fileupload_card"/>
                        </a>

                    </div>
                  </li>
                  <li>
                    <p class="tac fsize14 f-fM c-999">学历证</p>
                    <div class="sftp pr mt10" style="width:200px;height:200px">

                      <img alt="" src="" height="200" width="200" id="education_img" style="display: none">
                      <a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
                        <input type="file" id="fileupload_education"/>
                      </a>

                    </div>
                  </li>
                  <li>
                    <p class="tac fsize14 f-fM c-999">老师证</p>
                    <div class="sftp pr mt10" style="width:200px;height:200px">

                      <img alt="" src="" height="200" width="200" id="teaching_img" style="display: none">
                      <a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
                        <input type="file" id="fileupload_teaching"/>
                      </a>

                    </div>
                  </li>
                  <li>
                    <p class="tac fsize14 f-fM c-999">专业资质证</p>
                    <div class="sftp pr mt10" style="width:200px;height:200px">

                      <img alt="" src="" height="200" width="200" id="specialty_img" style="display: none">
                      <a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
                        <input type="file" id="fileupload_specialty"/>
                      </a>

                    </div>
                  </li>
                </ul>
                <div class="clear"></div>
                <p class="tac mt20">
                  <em class="icon18 icon-xl-u vam">&nbsp;</em>
                  <tt class="fsize12 c-org-1 f-fM vam">温馨提示：用户不会看到您的身份证、学历证、老师证、专业资质认证等证件的照片，请放心上传。</tt>
                </p>
              </div>
              <div class="pt30">
                <ol class="password-body-boy">
                  <li class="tac">
                    <a class="c-btn bing-btn" href="javascript:void(0)" onclick="onclickNext(4)">
                      下一步
                    </a>
                  </li>
                </ol>
              </div>
            </div>
          </article>
          <!-- 资质认证 -->
        </div>
      </section>
    </article>
  </div>
</section>
</body>
</html>
