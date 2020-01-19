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
                发布时间：<span>${question.gmtCreate?string("yyyy-MM-dd HH:mm")}</span> |
                阅读数： <span>${(question.viewCount)!0}</span>
                <#if user?exists && user.id == question.creator>
                    <a class="community-menu pull-right" href="${base}/publish/${question.id}">
                        <span aria-hidden="true" class="glyphicon glyphicon-pencil">编辑</span>
                    </a>
                </#if>
            </span>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="display: block">

                <!--标签-->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <#list question.tag?split(",") as subtag>
                        <span class="label label-info question-tag">
                            <span class="glyphicon glyphicon-tags"></span>
                            <span class="label label-info">${subtag}</span>
                        </span>
                    </#list>
                </div>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                <!--内容-->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="question-view">
                    <textarea style="display:none;">${question.description}</textarea>
                </div>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <script type="text/javascript">
                    $(function () {
                        editormd.markdownToHTML("question-view", {});
                    });
                </script>
            </div>

            <!--回复-->
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4 style="margin-bottom: 23px;">
                    <span>${(question.commentCount)!0}</span> 个回复
                </h4>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp">
                <#list comments as comment>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comments">
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object-left img-rounded"
                                     src="${comment.user.avatarUrl}">
                            </a>
                        </div>
                        <div class="media-body" id="${'comment-body-'+comment.id}">
                            <h5 class="media-heading">
                                <span>${comment.user.name}</span>
                            </h5>
                            <div>${comment.content}</div>
                            <div class="menu">
                                <span class="operate">
                                    <span class="glyphicon glyphicon-thumbs-up"></span>
                                    0
                                </span>
                                <span class="operate" onclick="collapseComments(this)" data-id="${comment.id}">
                                    <span class="glyphicon glyphicon-comment"></span>
                                    ${comment.commentCount}
                                </span>

                                <span class="pull-right">${comment.gmtCreate?string("yyyy-MM-dd HH:mm")}</span>
                            </div>
                            <!--二级评论-->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments"
                                 id="${'comment-'+comment.id}">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <input class="form-control" placeholder="评论一下……" id="${'input-'+comment.id}"
                                           type="text">
                                    <button class="btn btn-success pull-right" onclick="comment(this)" data-id="${comment.id}"
                                            type="button">评论
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#list>
            </div>

            <!--回复输入框-->
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h4 style="margin-bottom: 20px;">
                    提交回复
                </h4>
                <hr class="col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="comment_section">
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object-left img-rounded"
                                     src="${(user.avatarUrl)!'/images/default-avatar.png'}">
                            </a>
                        </div>
                        <div class="media-body">
                            <h5 class="media-heading">
                                <span>${(user.name)!'匿名用户'}</span>
                            </h5>
                        </div>
                    </div>
                    <input id="question_id" value="${question.id}" type="hidden">
                    <textarea class="form-control comment" id="comment_content" rows="6"></textarea>
                    <button class="btn btn-success btn-comment" onclick="post()" type="button">回复</button>
                </div>
            </div>
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
                <ul class="question-related">
                    <#list relatedQuestions as related>
                        <li>
                            <a href="/question/${related.id}">${related.title}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>
<#include "${base}/footer.ftl">
</body>
</html>