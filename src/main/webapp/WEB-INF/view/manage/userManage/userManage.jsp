<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!-- 
	用户信息管理页面
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
			<c:if test="${role=='admin'}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>
							id
						</th>
						<th>
							用户名
						</th>
						<th>
							email
						</th>
						<th>
							权限
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users }" var="user" varStatus="status">
					<c:if test="${status.count%2==0}">
					<tr class="success">
					</c:if>
					<c:if test="${status.count%2==1}">
					<tr>
					</c:if>
						<td>
							${user.id }
						</td>
						<td>
							${user.username }
						</td>
						<td>
							${user.email }
						<td>
							${user.role }
						</td>
						<td>
						<a href="${pageContext.request.contextPath }/updateUser?id=${user.id}">修改</a>
						<a href="#">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table> <button type="button" class="btn btn-default">增加</button>
			</c:if>
			<c:if test="${role!='admin'}">
				<p style="font-size: larger; font-weight: bold;text-align: center;">你不够高级，无法访问这个页面</p>
			</c:if>
		</div>
	</div>
</div>
	<div class="m-t-md " >
		<p class="pull-right">&copy;
			2016 广东青年网络宣传部</p>
	</div>
</body>
</html>