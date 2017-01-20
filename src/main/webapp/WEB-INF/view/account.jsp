<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 
	登陆页面
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" /><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style>  
.col-center-block {  
    float: none;  
    display: block;  
    margin-left: auto;  
    margin-right: auto;  
}  
</style>  
<title>管理系统登陆</title>
</head>
<body>
	<div class="container">
		<div class="row myCenter">
			<div class="col-xs-6 col-md-4 col-center-block">
				<form class="form-signin" action="${pageContext.request.contextPath}/dologin" method="post">
					<h2 class="form-signin-heading">请登录</h2>
					<label for="username" class="sr-only">用户名</label> 
					<input type="text" id="username" name="id" class="form-control" placeholder="用户名" required autofocus> 
					<label for="inputPassword" class="sr-only">密码</label> 
					<input id="flag" type="hidden" name="loginType" value="0" />
					<input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
					<div class="err text-warning">${error}</div>
					<div class="checkbox">
						<label> <input type="checkbox" value="remember-me">
							记住我
						</label>
					</div>
					<input id="login" class="btn btn-lg btn-primary btn-block" type="submit" value="登录">
				</form>
			</div>
		</div>
	</div>
</body>
<!--<script type="text/javascript">

$(function() {
	$("#login").click(function(){
		if($("#flag").attr("value")==0||$(".err").text()!=null){
			$("#flag").attr("value","1");
		}else{
			$("#flag").attr("value","2");
		}
	});
});
</script>-->
</html>