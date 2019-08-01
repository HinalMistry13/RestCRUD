<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<c:if test="${contactDetail == null }">
		<form action="<%=request.getContextPath()%>/add" method="post">
	</c:if>
	<c:if test="${contactDetail != null }">
		<form action="<%=request.getContextPath() %>/update?id=<c:out value='${contactDetail.cid}' />" method="post">
	</c:if>
		<table border="1" width="50%" cellpadding="5">
			<tr>
				<th colspan="2"><h3>Enter Contact Details</h3></th>
			</tr>
			<tr>
				<td align="right">Name</td>
				<td><input type="text" name="cname" required placeholder="Contact Name" value="${contactDetail.cname}"/></td>
			</tr>
			<tr>
				<td rowspan="2" align="right">Contact Nos:</td>
				<td>
					<input type="text" name="contactType" maxlength="10" required placeholder="Contact Type" value="${contactDetail.phones[0].type}"/> 
					<input type="text" name="contactNo" required placeholder="Contact Number" value="${contactDetail.phones[0].phoneNumber}"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="contactType" maxlength="10" required placeholder="Contact Type" value="${contactDetail.phones[1].type}"/> 
					<input type="text" name="contactNo" required placeholder="Contact Number" value="${contactDetail.phones[1].phoneNumber}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit" name="button" /></td>
			</tr>
		</table>
	</form>
		<br><br><br>
	<c:if test="${contactDetail == null }">
		<table border="1" width="50%" style="text-align: center;">
			<caption>
				<h2>List of Contacts</h2>
			</caption>
			<tr>
				<th width="10%">ID</th>
				<th width="30%">Name</th>
				<th width="20%">Type</th>
				<th width="20%">Phones</th>
				<th width="20%">Action</th>
			</tr>
			
			<c:forEach items="${list}" var="contact">
				<tr>
					<td rowspan="2"><c:out value="${contact.cid}" /></td>
					<td rowspan="2"><c:out value="${contact.cname}" /></td>
					<c:set var="counter" value="0"/>
					<c:forEach items="${contact.phones}" var="phone">
						<td><c:out value="${phone.type}" /></td>
						<td><c:out value="${phone.phoneNumber}" /></td>
						<c:if test="${counter==0}">
						<td rowspan="2">
							<a href="<%=request.getContextPath() %>/edit?id=<c:out value='${contact.cid}' />">Edit</a>
							/
							<a href="<%=request.getContextPath() %>/delete?id=<c:out value='${contact.cid}' />">Delete</a>
						</td>
						</c:if>
						<c:set var="counter" value="1"/>
						</tr>
					</c:forEach>
				</tr>				
			</c:forEach>
		</table>
	</c:if>
</div>
</body>
</html>