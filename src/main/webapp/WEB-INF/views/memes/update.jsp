<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Meme: ${meme.id}</title>
</head>
<div>
    <form method="post" action="<c:url value='/memes/update.simple'/>">
        <input name="meme-id" style="display:none" type="text" value="${meme.id}"/>
        <table>
            <tr>
               <td>Background:</td>
               <td>
                   <select id="meme-bg-select" name="meme-bg-id">
                       <c:forEach items="${allBackgrounds}" var="eaBg">
                           <c:choose>
                               <c:when test="${eaBg.id.longValue() == meme.memeBackground.id.longValue() }">
                                   <option value="${eaBg.id}" selected>
                                       <c:out value="${eaBg.description}"/>
                                   </option>
                               </c:when>
                               <c:otherwise>
                                   <option value="${eaBg.id}">
                                       <c:out value="${eaBg.description}"/>
                                   </option>
                               </c:otherwise>
                           </c:choose>
                       </c:forEach>
                   </select>
               </td>
            </tr>
            <tr>
                <td>Top Text:</td>
                <td><input name="top-text" type="text" id="top-text-input" value="${meme.topText.text}"/></td>
            </tr>
            <tr>
                <td>Bottom Text:</td>
                <td><input name="bottom-text" type="text" id="bottom-text-input" value="${meme.bottomText.text}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" id="submit-button" value="Update"/>
                </td>
            </tr>
        </table>
    </form>
</div>