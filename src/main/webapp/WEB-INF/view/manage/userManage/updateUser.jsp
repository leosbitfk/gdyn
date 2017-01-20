<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!-- 
	管理员更新用户信息
 	-->
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
//对session进行判断，只有具有session权限的才能访问该页面。
String userName = (String) session.getAttribute("user");
if (userName == null) {
   request.setAttribute("error", "请重新登陆");
   RequestDispatcher rd = request.getRequestDispatcher("../../account.jsp");
   rd.forward(request, response);
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" /><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/nav.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<head>
<title>管理系统</title>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="nav nav-pills">
				<li >
					 <a href="#">首页</a>
				</li>
				<li>
					 <a href="#">简介</a>
				</li>
				<li class="disabled">
					 <a href="#">信息</a>
				</li>
				<li class="dropdown pull-right active">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">${sessionScope.user}<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							 <a href="${pageContext.request.contextPath}/myuserinfo">我的信息</a>
						</li>
						<li>
							 <a href="${pageContext.request.contextPath}/getrole">用户管理</a>
						</li>
						<li>
							 <a href="#">更多设置</a>
						</li>
						<li class="divider">
						</li>
						<li>
							 <a href="${pageContext.request.contextPath}/loginout">退出</a>
						</li>
					</ul>
				</li>
			</ul>
			<form class="form" action="${pageContext.request.contextPath }/updateInfo" method="post">
				<div class="input-group">
  					<span class="input-group-addon">id</span>
  						<input name="changeId" type="text" class="form-control" placeholder="${user.id }">
						<!-- 因为控制台要通过id来查询，所以这里需要传一个id上去实例化对象 -->
  						<input type="hidden" name="id" value="${user.id }">
				</div>
				<div class="input-group">
  					<span class="input-group-addon">用户名</span>
  						<input name="username" type="text" class="form-control" placeholder="${user.username }">
				</div>
				<div class="input-group">
  					<span class="input-group-addon">email</span>
  						<input name="email" type="text" class="form-control" placeholder="${user.email }">
				</div>
				<div class="input-group">
  					<span class="input-group-addon">密码</span>
  						<input id="new" name="password" type="password" class="form-control"  >
				</div>
				<div class="input-group">
  					<span class="input-group-addon">确认新密码</span>
  						<input id="com" type="password" class="form-control" >
				</div>
				<div class="input-group">
					<label><input id="user" type="radio" name="role" value="user"/>user</label>
					<label><input id="admin"type="radio" name="role" value="admin"/>admin</label>
				</div>
				<p class="err text-warning"></p>
				<button type="submit" class="btn btn-default">提交修改</button>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("button").click(function(event){
		if($("#new").val()!=$("#com").val()){
			event.preventDefault();
			$(".err").text("两次输入不匹配");
			$("#new").val("");
			$("#com").val("");
		}
	})
	if($("#user").val()=="user"){
		$("#user").attr("checked","checked");
		$("#admin").attr("checked","");
	}else{
		$("#admin").attr("checked","checked");
		$("#user").attr("checked","");
	}
})
</script>
</html>