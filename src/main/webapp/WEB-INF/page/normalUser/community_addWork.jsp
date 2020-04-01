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
<title>夜雨时社区>发表作品</title>
</head>
<body>
	<form>
		<div>
			<span>作品类型</span>
			<input type="radio" name="isEssay" value="0"/>诗作
			<input type="radio" name="isEssay" value="1"/>随笔
		</div>
		<div>
			<span>标题</span>
			<input type="text" name="title"/>
		</div>
		<div>
			<span>正文</span>
			<textarea name="text" rows="3" cols="30"></textarea>
		</div>
		<div>
			<span id="addImage">点击添加图片</span>
			<span style="display:none">
				<input id="file" type="file" name="imgPath" multiple data-url="<%=basePath%>/cloth/uploadImage">
			</span>
		</div>
	</form>
</body>
</html>