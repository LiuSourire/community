<!DOCTYPE HTML>
<#assign base=springMacroRequestContext.contextPath>
<head>
    <title>过程PROCESS</title>
    <#include "${base}/import.ftl">
</head>
<body>
<#include "${base}/navigation.ftl">
<div class="jumbotron" style="min-height: 300px;">
    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><h1>出错啦！！！</h1>
        <p>${message!"服务太热啦，要不然稍等下再来试试~"}</p>
        <p><a class="btn btn-primary btn-lg" href="${base}/" role="button">回到主页</a></p>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
        <img class="img-thumbnail" style="width: 35%;" src="/images/official.jpg">
    </div>
</div>

</div>
<#include "${base}/footer.ftl">
</body>
</html>