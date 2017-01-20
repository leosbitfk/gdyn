<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!-- 
	更改密码页面
 	-->
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
<title>我的信息</title>
</head>
<body>
<div class="container">
	<div class="row clearfix ">
		<div class="col-md-12 column">
			<ul class="nav  nav-pills">
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
		</div>
	</div>
	<div class="row clearfix input">
		<div class="col-md-12 column">
			<form class="form" action="${pageContext.request.contextPath}/password" method="post">
				<div class="form-group input-group">
					 <label for="Opassword">请输入原密码</label><input  name="Opassword" type="password" class="form-control" required autofocus/>
				</div>
				<div class="form-group input-group">
					 <label for="Npassword">请输入新密码</label><input id="Npassword" name="Npassword" type="password" class="form-control" required/>
				</div>
				<div class="form-group input-group">
					 <label for="Cpassword">请确认新密码</label><input id="Cpassword" name="Cpassword" type="password" class="form-control" required />
				</div>
				<div class="err text-warning">${error}</div>
				</div> <button type="submit" class="btn btn-default">提交更改</button>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("button").click(function(event){
		if($("#Npassword").val()!=$("#Cpassword").val()){
			event.preventDefault();
			$(".err").text("两次输入不匹配");
			$("#Npassword").val("");
			$("#Cpassword").val("");
		}if($("#Npassword").val()==$("Opassword").val()){
			$(".err").text("新旧密码相同");
			event.preventDefault();
			$("#Npassword").val("");
			$("#Opassword").val("");
		}
	})
})
</script>
</html>