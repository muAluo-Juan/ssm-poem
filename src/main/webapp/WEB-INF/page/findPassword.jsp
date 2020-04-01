<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + 
                                      request.getServerName() + ":" +
                                      request.getServerPort() + path;
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath %>/css/findPassword.css"/>
<script src="<%=basePath%>/js/jquery.min.js"></script>
<title>夜雨时找回密码页面</title>
</head>
<body>
	<div style="position:relative">
    	<div class="findPwd_body">
        	<div class="findPwd_center">
            	<form action="<%=basePath %>/login/findPassword" method="post" name="findPasswordForm">
                	<div class="findPwd_top">找回密码</div>
                	<!--用户名-->
                	<div class="username">
                    	<span class="left">手机号</span>
                    	<input id="userName" type="text" name="userName">
                    	<button id="verifyCodeBtn" type="button">发送验证码</button>
                	</div>
                	<!--验证码-->
                	<div class="verify">
                		<span class="left">验证码</span>
                    	<input id="verifyCode" type="text" name="verifyCode" value="">
                    	<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<!--新密码-->
                	<div class="newPwd">
                    	<span class="left">新密码</span>
                    	<input id="newPassword" type="password" name="newPassword">
                    	<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<div style="clear:both"></div>
                	<!--找回密码按钮-->
                	<div class="findPwd_btn">
                    	<input id="findPwd" type="button" value="重置密码"/>
                	</div>
                	<!--登录按钮-->
                	<div class="login_btn">
                    	<a href="<%=basePath %>/index/login">返回登录</a>
                	</div>
            	</form>
        	</div>
    	</div>
	</div>

</body>
<script type="text/javascript">
	var canGet = false;
	$(document).ready(function(){
		var userName = document.getElementById("userName");
		var phone = /^1[3456789]\d{9}$/;//手机号（用户名格式）
		var phoneRight = false;
		userName.onblur=function(){
			if(userName.value === "")
			{
				canGet = false;
				phoneRight = false;
			}
			else if(!phone.test(userName.value))
			{	
				canGet = false;
				phoneRight = false;
			}
			else{
				//手机号是否已注册过（是否有效）
				request("post","<%=basePath%>/login/judgeUserExist",{"userName":userName.value},getAnswerSuccess,serverError,true);
				phoneRight = true;
			}
		}
		$("#verifyCodeBtn").css("cursor","pointer");
		/*
		*获取验证码
		*/
		$("#verifyCodeBtn").click(function(){
			if(phoneRight == false)
				alert("手机号无效！");
			else{
				request("post","<%=basePath%>/getPhoneCode",{},getAnswerSuccess,serverError,true);
			}
		});
		/*
		*验证码
		*/
		var verifyCode = document.getElementById("verifyCode");
		var errorInfo = document.getElementsByClassName("errorInfo")[0];
		verifyCode.onblur = function(){
			if(verifyCode.value === "")
			{
				errorInfo.innerText = "验证码不能为空";
				canGet = false;
			}
			else{
				canGet = true;
				errorInfo.innerText = "";
			}
		}
		/*
		*密码
		*/
		var password = document.getElementById("newPassword");
		var pwd = /^[a-zA-Z0-9]{6,12}$/;//密码（ 6至12位字符）
		var errorInfo1 = document.getElementsByClassName("errorInfo")[1];
		password.oninput = function(){
			if(!pwd.test(password.value))
			{
				errorInfo1.innerText = "密码为6至12位数字或英文字符";
				canGet = false;
			}
			else
			{
				errorInfo1.innerText = "";
				canGet = true;
			}
		}
		/*
		*点击找回密码按钮，验证验证码
		*/
		var findPwd = document.getElementById("findPwd");
		findPwd.onclick = function(){
			if(canGet == false || password.value === "" || verifyCode.value === "")
			{
				if(password.value === "")
					errorInfo1.innerText = "密码不能为空";
				if(verifyCode.value === "")
					errorInfo.innerText = "验证码不能为空";
				if(canGet == false)
					alert("输入的信息有误！");
			}
			else
				request("post","<%=basePath%>/login/verifyPhoneCode",{"phoneCode":verifyCode.value,"userName":userName.value,"password":password.value},getAnswerSuccess,serverError,true);
		}
	});
	function request(method,url,data,successCallBack,errorCallBack,async){
        $.ajax({
        	url: url,
            async:async,
            data: data,
            method: method
        }).done(successCallBack).fail(errorCallBack);
    }
	
	function getAnswerSuccess(data){
		var realData = JSON.parse(data);
		//显示手机验证码在页面上
		if(realData.code == 1)
		{
			var lastTime = 10;
			setCodeTime(lastTime,realData.data);
		}
		//用户名不存在
		if(realData.code == 2)
		{
			canGet = false;
			alert("该手机号未注册，发送失败！")
		}
		//用户名存在
		if(realData.code == 3)
		{
			canGet = true;
		}
		//手机验证码错误
		if(realData.code == 4)
		{
			var errorInfo = document.getElementsByClassName("errorInfo")[0];
			errorInfo.innerText = "验证码错误";
		}
		//密码重置成功
		if(realData.code == 5)
		{
			alert(realData.description);
			window.location = "<%=basePath %>/index/login";
		}
		if(realData.code == 6)
		{
			alert(realData.description);
		}
	}
	
	function serverError(XMLHttpRequest, textStatus){
	    console.log("responseText:",XMLHttpRequest.responseText);
	    console.log("status:",XMLHttpRequest.status);
	    console.log("textStatus:",textStatus);
	    console.log("readyState:",XMLHttpRequest.readyState);
	    alert("服务器故障");
	}
	
	/*
	*验证码倒计时
	*/
	function setCodeTime(lastTime,data){
		if (lastTime === 0) {
			$("#verifyCodeBtn").css("cursor","pointer");
	        $("#verifyCodeBtn").attr('disabled',false);
	        $("#verifyCodeBtn").text("重新发送");
	        lastTime = 10;
	        return;
	    } else {
	    	$("#verifyCodeBtn").css("cursor","");
	    	$("#verifyCodeBtn").attr('disabled',true);
	        $("#verifyCodeBtn").text("验证码"+data+"（"+lastTime+"s）");
	        lastTime--;
	    }
	    setTimeout(function() {setCodeTime(lastTime,data)},1000);
	}
</script>
</html>