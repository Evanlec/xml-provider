<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="uiComponents" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<jcr:node var="xmlActivityFileSettings" path="${renderContext.site.path}/xmlActivityFileSettings"/>
<c:set var="xmlFilePath" value="${xmlActivityFileSettings.properties['xmlFilePath'].string}"/>

<template:addResources type="javascript" resources="jquery.min.js"/>
<template:addResources type="javascript" resources="admin-bootstrap.js"/>
<template:addResources type="javascript" resources="jquery-ui.min.js,jquery.blockUI.js,workInProgress.js"/>
<template:addResources type="javascript" resources="xmlActivityFileConnectorUtils.js"/>

<template:addResources>
    <script type="text/javascript">
        var intervalValue;
        var API_URL = '${url.context}/modules/api/jcr/v1';
        var xmlFilePath = '${functions:escapeJavaScript(xmlFilePath)}';
        var readUrl = API_URL + "/live/${renderContext.UILocale}/paths${renderContext.site.path}/xmlActivityFileSettings";

        <c:choose>
            <c:when test="${empty xmlActivityFileSettings}">
                var mode = 'create';
                var writeUrl = API_URL + "/default/${renderContext.UILocale}/nodes/${renderContext.site.identifier}";
            </c:when>
            <c:otherwise>
                var mode = 'update';
                var writeUrl = API_URL + "/default/${renderContext.UILocale}/nodes/${xmlActivityFileSettings.identifier}";
        </c:otherwise>
        </c:choose>
    </script>
</template:addResources>

<div class="clearfix">
    <h1 class="pull-left"><fmt:message key="jnt_activityfileConnector"/></h1>
    <div class="pull-right">
        <img alt="" src="<c:url value="${url.currentModule}/img/xml_logo.png"/>"
             width="75" height="75">
    </div>
</div>

<div class="container">
    <div class="box-1">
        <div class="row-fluid">
            <div class="span6">
                <form class="form-horizontal" name="xmlParameters">
                    <fieldset>
                        <legend>
                        </legend>
                        <div class="control-group">
                            <label class="control-label">
                                <fmt:message key="jnt_activityfileConnector.xmlFilePath"/>
                            </label>
                            <div class="controls">
                                <input id="xmlFilePath" name="xmlFilePath" style="width:150px" type="text"
                                       value="${xmlFilePath}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button id="saveXmlActivityFileSettings" type="button" class="btn btn-primary"
                                        onclick="createUpdateXmlActivityFileParameters(intervalValue)" disabled>
                                    <fmt:message key="label.save"/>
                                </button>
                                <c:if test="${not empty xmlFilePath}">
                                    <button id="cancelXmlActivityFileSettings" type="button" class="btn btn-danger"
                                            onclick="resetXmlActivityFileSettings()" disabled>
                                        <fmt:message key="label.cancel"/>
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="span6" style="text-align:justify">
                <div class="alert alert-info">
                    <h4><fmt:message key="jnt_activityfileConnector.title"/></h4>
                    <p>
                        <fmt:message key="jnt_activityfileConnector.description"/><br/>
                        <fmt:message key="jnt_activityfileConnector.version"/><br/>
                    </p>
                </div>
                <c:if test="${not empty xmlFilePath}">
                    <jsp:useBean id="xmlFileDS" class="org.jahia.modules.xmlprovider.XmlDataSourceWritable" scope="page" />
                    <jsp:setProperty name="xmlFileDS" property="xmlFilePath"  value="${xmlFilePath}"/>
                </c:if>
            </div>
        </div>
    </div>
</div>