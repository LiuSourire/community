<!DOCTYPE HTML>
<#assign base=springMacroRequestContext.contextPath />
<head>
    <title>${sectionName}</title>
    <#include "${base}/import.ftl">
</head>
<body>
<#include "${base}/navigation.ftl">
<div class="container-fluid main profile">
    <div class="row">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
            <h2><span>${sectionName}</span></h2>
            <hr>
            <#if section == 'questions'>
                <#list pagination.questionDTOS as question>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
                                <span>${question.gmtCreate?string("yyyy-MM-dd hh:mm:ss")}</span></span>
                                <a class="community-menu" href="${base}/publish/${question.id}">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true">编辑</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
            <#if section == 'replies'>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <#--<div class="media" th:each="notification : ${pagination.data}">
                        <div class="media-body">
                            <p class="media-heading">
                                <span th:text="${notification.notifierName +' ' + notification.typeName + ' '}"></span>
                                <a th:href="@{'/notification/'+ ${notification.id}}"
                                   th:text="${notification.outerTitle}">
                                </a>
                                <span class="label label-danger" th:if="${notification.status == 0}">未读</span>
                            </p>
                        </div>
                    </div>-->
                </div>
            </#if>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <#if pagination.showFirstPage>
                                <li>
                                    <a href="${base}/profile/${section}/?current=1" aria-label="Previous">
                                        <span aria-hidden="true">&lt;&lt;</span>
                                    </a>
                                </li>
                            </#if>
                            <#if pagination.showPrev>
                                <li>
                                    <a href="${base}/profile/${section}/?current=${pagination.current - 1}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&lt;</span>
                                    </a>
                                </li>
                            </#if>
                            <#list pagination.pages as page>
                                <li ${(pagination.current == page)?string("class='active'","")}>
                                    <a href="${base}/profile/${section}/?current=${page}" >${page}</a>
                                </li>
                            </#list>
                            <#if pagination.showNext>
                                <li>
                                    <a href="${base}/profile/${section}/?current=${pagination.current +1 }"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&gt;</span>
                                    </a>
                                </li>
                            </#if>
                            <#if pagination.showLastPage>
                                <li>
                                    <a href="${base}/profile/${section}/?current=${pagination.totalPage}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&gt;&gt;</span>
                                    </a>
                                </li>
                            </#if>
                            <#--<li th:if="${pagination.showEndPage}">
                                <a th:href="@{'/profile/'+${section}(page=${pagination.totalPage})}" aria-label="Previous">
                                    <span aria-hidden="true">&gt;&gt;</span>
                                </a>
                            </li>-->
                        </ul>
                    </nav>
                </div>
            </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <div class="list-group section">
                <a href="/profile/questions"
                    ${(section == 'questions')?string("class='active list-group-item'","class='list-group-item'")}>我的问题</a>
                <a href="/profile/replies"
                        ${(section == 'replies')?string("class='active list-group-item'","class='list-group-item' ")}>
                    最新回复
                    <span class="badge" <#--th:text="${session.unreadCount}"-->>10</span>
                </a>
            </div>
        </div>
    </div>
</div>
<#include "${base}/footer.ftl">
</body>
</html>