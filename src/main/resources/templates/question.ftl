<!DOCTYPE HTML>
<#assign base=springMacroRequestContext.contextPath />
<head>
    <title>${question.title}</title>
    <#include "${base}/import.ftl">
</head>
<body>
<#include "${base}/navigation.ftl">
<div class="container-fluid main profile">
    <div class="row">

        <!--左边主要内容-->
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
            <!--正文-->
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4 class="question-title"><span>${question.title}</span></h4>
                <span class="text-desc">
                作者：<span>${question.user.name}</span> |
                发布时间：<span>${question.gmtCreate?string("yyyy-MM-dd hh:mm")}</span> |
                阅读数： <span>${(question.viewCount)!0}</span>
            </span>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                <!--内容-->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="question-view">
                    <textarea style="display:none;">${question.description}</textarea>
                </div>
                <script type="text/javascript">
                    $(function () {
                        editormd.markdownToHTML("question-view", {});
                    });
                </script>

                <!--标签-->
                <#--<hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <span class="label label-info question-tag" th:each="tag : ${question.tag.split(',')}">
                        <span class="glyphicon glyphicon-tags"></span>
                        <span class="label label-info" th:text="${tag}"></span>
                    </span>
                </div>-->

                <!--编辑-->
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <#if user?exists && user.id == question.creator>
                    <a class="community-menu" href="${base}/publish/?id='+${question.id}}">
                        <span aria-hidden="true" class="glyphicon glyphicon-pencil">编辑</span>
                    </a>
                    </#if>
                </div>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            </div>

            <!--回复-->
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>
                    <span>${(question.commentCount)!0}</span> 个回复
                </h4>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp">
                <#--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comments" th:each="comment : ${comments}">
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object img-rounded"
                                     th:src="${comment.user.avatarUrl}">
                            </a>
                        </div>
                        <div class="media-body" th:id="${'comment-body-'+comment.id}">
                            <h5 class="media-heading">
                                <span th:text="${comment.user.name}"></span>
                            </h5>
                            <div th:text="${comment.content}"></div>
                            <div class="menu">
                                <span class="glyphicon glyphicon-thumbs-up icon"></span>
                                <span class="comment-icon"
                                      onclick="collapseComments(this)" th:data-id="${comment.id}">
                                    <span class="glyphicon glyphicon-comment"></span>
                                    <span th:text="${comment.commentCount}"></span>
                                </span>
                                <span class="pull-right"
                                      th:text="${#dates.format(comment.gmtCreate,'yyyy-MM-dd')}"></span>
                            </div>
                            <!--二级评论&ndash;&gt;
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments"
                                 th:id="${'comment-'+comment.id}">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <input class="form-control" placeholder="评论一下……" th:id="${'input-'+comment.id}"
                                           type="text">
                                    <button class="btn btn-success pull-right" onclick="comment(this)" th:data-id="${comment.id}"
                                            type="button">评论
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
            </div>

            <!--回复输入框-->
            <#--<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>
                    提交回复
                </h4>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="comment_section">
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object img-rounded"
                                     th:src="${session.user != null ? session.user.avatarUrl : '/images/default-avatar.png'}">
                            </a>
                        </div>
                        <div class="media-body">
                            <h5 class="media-heading">
                                <span th:text="${session.user != null ? session.user.name : '匿名用户'}"></span>
                            </h5>
                        </div>
                    </div>
                    <input id="question_id" th:value="${question.id}" type="hidden">
                    <textarea class="form-control comment" id="comment_content" rows="6"></textarea>
                    <button class="btn btn-success btn-comment" onclick="post()" type="button">回复</button>
                </div>
            </div>-->

        </div>

        <!--右边信息块-->
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>发起人</h4>
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object-left img-rounded" src="${question.user.avatarUrl}">
                        </a>
                    </div>
                    <div class="media-body">
                        <h5 class="media-heading">
                            <span>${question.user.name}</span>
                        </h5>
                    </div>
                </div>
            </div>

            <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <#include "${base}/side.ftl">

            <!--相关问题-->
            <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4>相关问题</h4>
                <#--<ul class="question-related">
                    <li th:each="related : ${relatedQuestions}">
                        <a th:href="@{'/question/'+ ${related.id}}" th:text="${related.title}"></a>
                    </li>
                </ul>-->
            </div>
        </div>
    </div>
</div>
<#include "${base}/footer.ftl">
</body>
</html>