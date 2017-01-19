<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
//对session进行判断，只有具有session权限的才能访问该页面。
String userName = (String) session.getAttribute("user");
if (userName == null) {
   request.setAttribute("error", "请重新登陆");
   RequestDispatcher rd = request.getRequestDispatcher("../account.jsp");
   rd.forward(request, response);
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" /><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

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
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">${username}<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							 <a href="${pageContext.request.contextPath}/myuserinfo">我的信息</a>
						</li>
						<li>
							 <a href="#">设置栏目</a>
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
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form class="form" action="${pageContext.request.contextPath}/myuserinfo" method="post">
				<div class="form-group">
					 <label for="id">id</label><input id="id" type="text" class="form-control" placeholder="${user.id }" />
				</div>
				<div class="form-group">
					 <label for="username">username</label><input id="username" type="text" class="form-control" placeholder="${user.username }" />
				</div>
				<div class="form-group">
					 <label for="email">email</label><input id="email" type="text" class="form-control" placeholder="${user.email }" />
				</div>
				</div> <button type="submit" class="btn btn-default">提交更改</button>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(function(){
	$("button").click(function(event){
		
		if($("#id").val()!=""
				||$("#username").val()!=""
				||$("#email").val()!=""){
			
			var r=confirm("是否提交更改");
			if(!r){
				event.preventDefault();
			}
		}else{
			event.preventDefault();
		}
	});
})
</script>
</html>