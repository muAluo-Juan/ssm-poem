<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + 
                                      request.getServerName() + ":" +
                                      request.getServerPort() + path;
%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=basePath %>/css/reset.css"/>
<link rel="stylesheet" href="<%=basePath %>/css/index.css"/>
<link rel="stylesheet" href="<%=basePath %>/css/register.css"/>
<script src="<%=basePath%>/js/jquery.min.js"></script>
<title>夜雨时注册页面</title>
</head>

<body>
	<div>
    	<div class="register_body">
        	<div class="register_center">
            	<form action="<%=basePath %>/login/register" method="post" name="registerForm">
                	<div class="register_top">用户注册</div>
                	
                	<!--用户名-->
                	<div class="username">
                    	<span class="left">用户名</span>
                    	<input id="userName" type="text" name="userName" value="${user.userName}">
                    	<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<!--笔名-->
                	<div class="penName">
                		<span class="left">笔名</span>
                    	<input id="penName" type="text" name="penName" value="${user.penName}">
                		<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<!--密码-->
                	<div class="password">
                		<span class="left">密码</span>
                    	<input id="password" type="password"  name="password" value="${user.password}">
                		<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<!--确认密码-->
                	<div class="password">
                		<span class="left">确认密码</span>
                    	<input id="confirmPassword" type="password" name="confirmPassword" value="${user.confirmPassword}">
                		<!-- 提示错误信息 -->
                		<div class="errorInfo"></div>
                	</div>
                	<!-- 性别 -->
                	<div class="sex">
                		<span class="left">性别</span>
                		<label class="boy"><input name="sex" type="radio" value="1" id="sex_boy" checked="checked">男</label>
                        <label><input name="sex" type="radio" value="0" id="sex_girl">女</label>
                	</div>
                	<!--验证码-->
                	<div class="verify">
                		<span class="left">验证码</span>
                    	<input id="verifyCode" type="text" name="verifyCode" value="">
                		<button id="verifyCodeBtn" type="button">发送验证码</button>
                		<!-- 提示错误信息 -->
                		<div class="errorInfo">${phoneCodeError}</div>
                	</div>
                	<div style="clear:both"></div>
                	<!--注册按钮-->
                	<div class="register_btn">
                    	<input id="register" type="button" value="注册" onclick="registerSubmit()"/>
                	</div>
                	<!--登录按钮-->
                	<div class="login_btn">
                    	<a href="<%=basePath %>/index/login">去登录</a>
                	</div>
            	</form>
        	</div>
    	</div>
	</div>
</body>
<script type="text/javascript">
	var canRegister = false;
	$(document).ready(function(){
		$("#verifyCodeBtn").css("cursor","pointer");
		/*
		*获取验证码
		*/
		$("#verifyCodeBtn").click(function(){
			request("post","<%=basePath%>/getPhoneCode",{},getAnswerSuccess,serverError,true);
		});
		/*
		*检验输入格式
		*/
		/*
		*用户名
		*/
		var userName = document.getElementById("userName");
		var phone = /^1[3456789]\d{9}$/;//手机号（用户名格式）
		var errorInfo1 = document.getElementsByClassName("errorInfo")[0];
		userName.onblur=function(){
			if(userName.value === "")
			{
				errorInfo1.innerText = "用户名不能为空";
				canRegister = false;
			}
			else if(!phone.test(userName.value))
			{	
				errorInfo1.innerText = "用户名为11位手机号码";
				canRegister = false;
			}
			else{
				//请求判断用户名是否可用（手机号是否已注册过）
				request("post","<%=basePath%>/login/judgeUserExist",{"userName":userName.value},getAnswerSuccess,serverError,true);
			}
		}
		/*
		*笔名
		*/
		var penName = document.getElementById("penName");
		var errorInfo2 = document.getElementsByClassName("errorInfo")[1];
		penName.onblur = function(){
			if(penName.value == "")
			{
				errorInfo2.innerText = "笔名不能为空";
				canRegister = false;
			}
			else if(penName.value.length > 15)
			{
				errorInfo2.innerText = "笔名太长了，换一个吧";
				canRegister = false;
			}
			else
			{
				errorInfo2.innerText = "";
				canRegister = true;
			}
		}
		/*
		*密码
		*/
		var password = document.getElementById("password");
		var pwd = /^[a-zA-Z0-9]{6,12}$/;//密码（ 6至12位字符）
		var errorInfo3 = document.getElementsByClassName("errorInfo")[2];
		password.onblur = function(){
			if(password.value === "")
			{
				errorInfo3.innerText = "请输入密码";
				canRegister = false;
			}
			else if(!pwd.test(password.value))
			{
				errorInfo3.innerText = "密码为6至12位数字或英文字符";
				canRegister = false;
			}
			else
			{
				errorInfo3.innerText = "";
				canRegister = true;
			}
		}
		/*
		*确认密码
		*/
		var confirmPassword = document.getElementById("confirmPassword");
		var errorInfo4 = document.getElementsByClassName("errorInfo")[3];
		confirmPassword.onblur = function(){
			if(confirmPassword.value !== password.value)
			{
				errorInfo4.innerText = "两次密码不一致";
				canRegitser = false;
				confirmPassword.value = "";
			}
			else{
				canRegitser = true;
				errorInfo4.innerText = "";
			}
		}
		/*
		*验证码
		*/
		var verifyCode = document.getElementById("verifyCode");
		var errorInfo5 = document.getElementsByClassName("errorInfo")[4];
		verifyCode.onblur = function(){
			if(verifyCode.value === "")
			{
				errorInfo5.innerText = "验证码不能为空";
				canRegitser = false;
			}
			else{
				canRegitser = true;
				errorInfo5.innerText = "";
			}
		}
		/*
		*提交注册信息给LoginController
		*/
		
		var registerBtn = document.getElementById("register");
		registerBtn.onclick = function(){
			if(userName.value === "" || penName.value === "" || password.value === "" || confirmPassword.value === "" || verifyCode.value === "")//刷新
			{
				alert("信息填写不完整或格式有误！");
				return false;
			}
			else
			{
				document.registerForm.submit();//提交登录信息
				return true;
			}
		}
		if("${phoneCodeError}" != "")
		{
			var sex = document.getElementsByName("sex");
			if(sex[0].value == "${user.sex}")
			{
				sex[0].checked = "checked";
				sex[1].checked = "";
			}
			else
			{
				sex[1].checked = "checked";
				sex[0].checked = "";
			}
		}
		if("${registerSuccess}" != "")
		{
			alert("${registerSuccess}");
			window.location = "<%=basePath %>/index/login";
		}
			
	})
	
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
		//用户名可用
		if(realData.code == 2)
		{
			canRegister = true;
			var errorInfo1 = document.getElementsByClassName("errorInfo")[0];
			errorInfo1.innerText = "";
		}
		//用户名不可用
		if(realData.code == 3)
		{
			canRegister = false;
			var errorInfo1 = document.getElementsByClassName("errorInfo")[0];
			errorInfo1.innerText = realData.description;
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