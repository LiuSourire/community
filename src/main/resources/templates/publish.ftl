<!DOCTYPE HTML>
<#assign base=springMacroRequestContext.contextPath />


<head>
    <title>发布 - 过程PROCESS</title>
    <#include "${base}/import.ftl">
</head>
<body>

<#include "${base}/navigation.ftl">
<div class="container-fluid main">
    <div class="row">
        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
            <h2><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 发起</h2>
            <hr>

            <form action="/publish" method="post">
                <input type="hidden" name="id" value="${(question.id)!""}">
                <div class="form-group">
                    <label for="title">问题标题（简单扼要）:</label>
                    <input type="text" class="form-control" id="title" name="title"
                           placeholder="问题标题……" value="${(question.title)!""}"
                           autocomplete="off">
                </div>
                <div class="form-group" id="question-editor">
                    <label for="description">问题补充 (必填，请参照右侧提示):</label>
                    <textarea name="description" id="description"  <#--style="display:none;"-->
                              class="form-control" cols="30" rows="10">${(question.description)!""}</textarea>
                </div>
                <#--<script type="text/javascript">
                    $(function () {
                        var editor = editormd("question-editor", {
                            width: "100%",
                            height: 350,
                            path: "/js/lib/",
                            delay: 0,
                            watch: false,
                            placeholder: "请输入问题描述",
                            imageUpload: true,
                            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                            imageUploadURL: "/file/upload",
                        });
                    });
                </script>-->
                <div class="form-group">
                    <label for="tag">添加标签:</label>
                    <input type="text" class="form-control"  id="tag" name="tag"
                           value="${(question.tag)!""}"
                           autocomplete="off"
                           placeholder="输入标签，以，号分隔"
                           <#--onclick="showSelectTag()"-->>
                    <#--<div id="select-tag" class="publish-tag-tab">
                        <ul class="nav nav-tabs" role="tablist">
                            &lt;#&ndash;<li role="presentation" th:each="selectCategory,selectCategoryStat: ${tags}"
                                th:class="${selectCategoryStat.first ? 'active':''}">
                                <a th:href="${'#'+selectCategory.categoryName}" aria-controls="home" role="tab"
                                   data-toggle="tab" th:text="${selectCategory.categoryName}"></a>
                            </li>&ndash;&gt;
                        </ul>
                        <div class="tab-content">
                            &lt;#&ndash;<div role="tabpanel" th:id="${selectCategory.categoryName}"
                                 th:each="selectCategory: ${tags}"
                                 th:class="${selectCategoryStat.first ? 'active tab-pane':'tab-pane'}">
                                <span>
                                    <span class="label label-info" th:each="selectTag: ${selectCategory.tags}">
                                    <span class="glyphicon glyphicon-tags" onclick="selectTag(this)"
                                          th:data-tag="${selectTag}"
                                          th:text="${' '+selectTag}">
                                    </span>
                                </span>
                                </span>
                            </div>&ndash;&gt;
                        </div>
                    </div>-->
                </div>

                <div class="container-fluid main ">
                    <div class="row">
                        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                            <#if (error)??>
                                <div class="alert alert-danger col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    ${error}
                                </div>
                            </#if>
                        </div>
                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                            <button type="submit" class="btn btn-success btn-publish ">
                                发布
                            </button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
            <h3>问题发起指南</h3>
            • 问题标题: 请用精简的语言描述您发布的问题，不超过25字 <br>
            • 问题补充: 详细补充您的问题内容，并确保问题描述清晰直观, 并提供一些相关的资料<br>
            • 选择标签: 选择一个或者多个合适的标签，用逗号隔开，每个标签不超过10个字<br>
        </div>
    </div>
</div>
<#--<div th:insert="~{footer :: foot}"></div>-->
<#include "${base}/footer.ftl">
</body>
</html>
