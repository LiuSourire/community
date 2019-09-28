<!DOCTYPE HTML>
<#assign base=springMacroRequestContext.contextPath />
<head>
    <title>爪巴社区</title>
    <!--<head th:insert="~{import :: head}"></head>-->
    <#include "${base}/import.ftl">
</head>
<script type="application/javascript">
    window.onload = function () {
        var closable = window.localStorage.getItem("closable");
        if (closable == "true") {
            window.close();
            window.localStorage.removeItem("closable");
        }
    }
</script>
<body>
<!--<div th:insert="~{navigation :: nav}"></div>-->
<#include "${base}/navigation.ftl">
<div class="container-fluid main">
    <div class="row">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
            <h3><span class="glyphicon glyphicon-list" aria-hidden="true"></span> 发现</h3>
            <#--<ul class="nav nav-tabs">
                <li role="presentation" th:class="${sort == 'new' || sort == '' ? 'active':''}">
                    <a th:href="@{/(sort='new')}">最新</a>
                </li>
                <li role="presentation" th:class="${sort == 'hot30' ? 'active':''}">
                    <a th:href="@{/(sort='hot30')}">30天最热</a>
                </li>
                <li role="presentation" th:class="${sort == 'hot7' ? 'active':''}">
                    <a th:href="@{/(sort='hot7')}">7天最热</a>
                </li>
                <li role="presentation" th:class="${sort == 'hot' ? 'active':''}">
                    <a th:href="@{/(sort='hot')}">最热</a>
                </li>
                <li role="presentation" th:class="${sort == 'no' ? 'active':''}">
                    <a th:href="@{/(sort='no')}" class="red">消灭零回复</a>
                </li>
            </ul>-->
            <#if pagination.total == 0>
                <div class="media">
                    还没有人提出问题啊~~~~
                </div>
            <#else>
                <#list pagination.questionDTOS as question>
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object img-rounded"
                                     src="${question.user.avatarUrl}">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">
                                <a href="${base}/question/${question.id}">${question.title}</a>
                            </h4>
                            <span class="text-desc"><span>${(question.commentCount)!0}</span> 个回复 •
                            <span>${(question.viewCount)!0}</span> 次浏览 •
                            <span>${question.gmtCreate?string("yyyy-MM-dd HH:mm:ss")}</span></span>
                        </div>
                    </div>
                </#list>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <#if pagination.showFirstPage>
                            <li>
                                <a href="/?current=1" aria-label="Previous">
                                    <span aria-hidden="true">&lt;&lt;</span>
                                </a>
                            </li>
                        </#if>
                        <#if pagination.showPrev>
                            <li>
                                <a href="/?current=${pagination.current - 1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                        </#if>
                        <#list pagination.pages as page>
                            <li ${(pagination.current == page)?string("class='active'","")}>
                                <a href="/?current=${page}" >${page}</a>
                            </li>
                        </#list>
                        <#if pagination.showNext>
                            <li>
                                <a href="/?current=${pagination.current +1 }"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                        </#if>
                        <#if pagination.showLastPage>
                            <li>
                                <a href="/?current=${pagination.totalPage}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&gt;&gt;</span>
                                </a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </#if>
        </div>
        <#--<div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <div th:insert="~{side :: side}"></div>
            相关问题&ndash;&gt;
            <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>热门标签</h4>
                <ul class="question-related">
                    <li th:each="tag : ${tags}">
                        <a th:href="@{/(page=1,search=${search},tag=${tag},sort=${sort})}" th:text="${tag}"></a>
                    </li>
                </ul>
            </div>
        </div>-->
    </div>
</div>
<!--<div th:insert="~{footer :: foot}"></div>-->
<#include "${base}/footer.ftl">
</body>
</html>