<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%--导入jstl标签--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shrio"  %>

<%--spring通用标签库--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--spring表单绑定库--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- 设置页面全局变量 --%>
<c:set var="domain" value="http://localhost:8080" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="basePath" value="${domain}${ctx}" />



<%--静态页面变量--%>
<c:set var="thumbnailCdnDomain" value="http://ojhrc7v2d.bkt.clouddn.com/" />
<c:set var="toyPictureCdnDomain" value="http://ojhr4porl.bkt.clouddn.com/" />
<c:set var="slideCdnDomain" value="http://ojhrh9rpy.bkt.clouddn.com/" />

<%-- 设置JS全局变量--%>
<%--<script type="text/javascript">--%>
<%--var Global = {};--%>
<%--//   动态请求--%>
<%--Global.domain = "${domain}";--%>
<%--Global.ctx = "${pageContext.request.contextPath}";--%>
<%--Global.basePath = "${basePath}";--%>
<%--//静态请求--%>
<%--Global.cdnDomain = "${cdnDomain}";--%>
<%--Global.cdnPath = "${cdnPath}";--%>
<%--</script>--%>