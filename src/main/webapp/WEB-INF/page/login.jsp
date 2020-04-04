<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
<title>夜雨时登录页面</title>
</head>
<body>
	<div class="login">
    	<div class="login_body">
        	<div class="login_center">
            	<form action="${pageContext.request.contextPath }/login/login" method="post" name="loginForm">
                	<div class="login_top">密码登录</div>
                	<!-- 提示错误信息 -->
                	<div class="errorInfo">${loginErrorInfo }</div>
                	<!--用户名-->
                	<div class="username">
                    	<span></span>
                    	<input id="userName" type="text" placeholder="用户名" name="userName" value="${user.userName}">
                	</div>
                	<!-- 提示错误信息 -->
                	<div class="errorInfo"></div>
                	<!--密码-->
                	<div class="password">
                    	<span></span>
                    	<input id="password" type="password" placeholder="密码" name="password" value="${user.password}">
                	</div>
                	<!-- 提示错误信息 -->
                	<div class="errorInfo">${verifyCodeError}</div>
                	<!--验证码-->
                	<div class="verify">
                    	<span></span>
                    	<input id="verifyCode" type="text" placeholder="验证码" name="verifyCode" value="${user.verifyCode}">
                    	<img src="${pageContext.request.contextPath }/loadIcode" id="changeImg">
                	</div>
                	<!-- 登录角色 -->
                	<div class="login_role">
                		<label><input name="role" type="radio" value="normalUser" id="role_user" checked="checked">普通用户</label>
                        <label><input name="role" type="radio" value="admin" id="role_admin">管理员</label>
                	</div>
                	<!-- 忘记密码 -->
                	<div class="login_findPwd">
                		<a href="${pageContext.request.contextPath }/index/findPassword">忘记密码，去找回</a>
                	</div>
                	<div style="clear:both"></div>
                	<!--登录按钮-->
                	<div class="login_btn">
                    	<input type="button" value="登录" onclick="loginSubmit()"/>
                	</div>
                	<!--注册按钮-->
                	<div class="register_btn">
                    	<a href="${pageContext.request.contextPath}/index/register">去注册</a>
                	</div>
            	</form>
        	</div>
    	</div>
	</div>
	<script>
		var canLogin = true;
		/*
		*点击刷新验证码
		*/
		document.getElementById("changeImg").onclick = function() {
			var obj = document.getElementById("changeImg");
			obj.src = "${pageContext.request.contextPath }/loadIcode?"+ new Date().getTime();
		}
		/*
		*判断用户名格式
		*/
		var userName = document.getElementById("userName");
		var phone = /^1[3456789]\d{9}$/;//手机号（用户名格式）
		var errorInfo1 = document.getElementsByClassName("errorInfo")[0];
		userName.oninput = function(){
			if(!phone.test(userName.value))
			{	
				errorInfo1.innerText = "用户名为11位手机号码";
				canLogin = false;
			}
			else
			{
				errorInfo1.innerText = "";
				canLogin = true;
			}
		}
		/*
		*判断密码格式
		*/
		var password = document.getElementById("password");
		var pwd = /^[a-zA-Z0-9]{6,12}$/;//密码（ 6至12位字符）
		var errorInfo2 = document.getElementsByClassName("errorInfo")[1];
		password.oninput = function(){
			if(!pwd.test(password.value))
			{
				errorInfo2.innerText = "密码为6至12位数字或英文字符";
				canLogin = false;
			}
			else
			{
				errorInfo2.innerText = "";
				canLogin = true;
			}
		}
		/*
		*提交登录信息给LoginController
		*/
		var verifyCode = document.getElementById("verifyCode");
		var errorInfo3 = document.getElementsByClassName("errorInfo")[2];
		function loginSubmit(){
			if(userName.value == "" || password.value == "" || verifyCode.value == "")
			{
				if(userName.value == "")
					errorInfo1.innerText = "用户名不能为空";
				if(password.value == "")
					errorInfo2.innerText = "密码不能为空";
				if(verifyCode.value == "")
					errorInfo3.innerText = "请输入验证码";
				return false;
			}
			else if(canLogin == false)//刷新
			{
				location.reload();
				return false;
			}
			else
			{
				document.loginForm.submit();//提交登录信息
				return true;
			}
		}
		/*
		*获取后台给的数据
		*/
		/*
		*验证码错误
		*/
		if("${verifyCodeError}" != "")
		{
			var role = document.getElementsByName("role");
			if(role[0].value == "${user.role}")
			{
				role[0].checked = "checked";
				role[1].checked = "";
			}
			else
			{
				role[1].checked = "checked";
				role[0].checked = "";
			}
		}
		/*
		*用户名或密码错误
		*/
		if("${loginErrorInfo}" !="")
		{
			var role = document.getElementsByName("role");
			if(role[0].value == "${user.role}")
			{
				role[0].checked = "checked";
				role[1].checked = "";
			}
			else
			{
				role[1].checked = "checked";
				role[0].checked = "";
			}
		}
	</script>
</body>

</html>