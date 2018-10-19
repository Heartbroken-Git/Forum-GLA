<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
	
	<title>FORUM</title>
	
	
	
	<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">
	
	
		<div id="wrapcentre">
	
			<div id="pagecontent">
	
				<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
					cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td class="row1">
                                <c:choose>
                                    <c:when test = "${auth}">
                                        <p class="breadcrumbs">Connecté en tant que ${username}</p>
                                    </c:when>

                                    <c:otherwise>
                                        <p class="breadcrumbs">Non connecté</p>
                                    </c:otherwise>
                                </c:choose>

							</td>
						</tr>
					</tbody>
				</table>
	
				<br clear="all" />
	
					<table cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td valign="middle" align="left"><img src="fichiers/button_topic_new.gif" alt="Post new topic" title="Post new topic" /></td>
							</tr>
						</tbody>
					</table>
	
				<br clear="all" />
	
					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td class="cat" colspan="4">
									<table cellspacing="0" width="100%">
										<tbody>
											<tr class="nav">
												<td valign="middle">&nbsp;</td>
												<td valign="middle" align="right">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
	
							<tr>
	
								<th>&nbsp;Topics&nbsp;</th>
								<th>&nbsp;Auteur&nbsp;</th>
								<th>&nbsp;R&eacute;ponses&nbsp;</th>
								<th>&nbsp;Vues&nbsp;</th>
							</tr>
	
	                        <c:forEach items="${sujets}" var="sujetCourant">
                                <td class="row1"><a class="topictitle" href="${pageContext.request.contextPath}/threads/${sujetCourant.getIdSujet()}" >${sujetCourant.getTitre()}</a></td>
                                <td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">${sujetCourant.getAuteur().getLogin()}</a></p></td>
                                <td class="row1" align="center" width="50"><p class="topicdetails">${sujetCourant.getNbMessages()}</p></td>
                                <td class="row2" align="center" width="50"><p class="topicdetails">TBD</p></td>
                            </c:forEach>
	
							<%--<tr>
								<td class="row1"><a class="topictitle" href="${pageContext.request.contextPath}/threads/example" >Mon fil de discussion</a></td>
								<td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">Yves</a></p></td>
								<td class="row1" align="center" width="50"><p class="topicdetails">10</p></td>
								<td class="row2" align="center" width="50"><p class="topicdetails">1234</p></td>
							</tr>
	
							<tr>
								<td class="row1"><a class="topictitle" href="${pageContext.request.contextPath}/threads/example">Mon fil de discussion</a></td>
								<td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">Yves</a></p></td>
								<td class="row1" align="center" width="50"><p class="topicdetails">10</p></td>
								<td class="row2" align="center" width="50"><p class="topicdetails">1234</p></td>
							</tr>
	
							<tr>
								<td class="row1"><a class="topictitle" href="${pageContext.request.contextPath}/threads/example">Mon fil de discussion</a></td>
								<td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">Yves</a></p></td>
								<td class="row1" align="center" width="50"><p class="topicdetails">10</p></td>
								<td class="row2" align="center" width="50"><p class="topicdetails">1234</p></td>
							</tr>
	
							<tr>
								<td class="row1"><a class="topictitle" href="${pageContext.request.contextPath}/threads/example">Mon fil de discussion</a></td>
								<td class="row2" align="center" width="130"><p class="topicauthor"><a class="username-coloured" href="#">Yves</a></p></td>
								<td class="row1" align="center" width="50"><p class="topicdetails">10</p></td>
								<td class="row2" align="center" width="50"><p class="topicdetails">1234</p></td>
							</tr>--%>
	
	
						</tbody>
					</table>
					<br clear="all" />
			</div>
	
			<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
				cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="row1">
                            <p class="breadcrumbs" ><a class="index" href="${pageContext.request.contextPath}/home">Index du forum</a></p>
						</td>
					</tr>
				</tbody>
			</table>
	
		</div>
	
	</body>
</html>
