<!DOCTYPE html>



<body>

<div>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">爪巴社区</span>
                </button>
                <a class="navbar-brand" href="/">爪巴社区</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <form class="navbar-form navbar-left" action="/" method="get">
                    <div class="form-group">
                        <input type="text" class="form-control" name="search" placeholder="搜索问题">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
                <ul class="nav navbar-nav">
                    <li><a href="https://detail.youzan.com/show/goods/newest?alias=2xj0uxwujrwg6" target="_blank"
                           onclick="gtag('event', 'click', {'event_category': 'LINK','event_label': 'BOOK_STORE','transport_type': 'NAV'})">码匠书店</a>
                    </li>
                    <li><a href="http://www.mawen.co/question/92" target="_blank"
                           onclick="gtag('event', 'click', {'event_category': 'LINK','event_label': 'WEB','transport_type': 'NAV'})">前端面试题</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if (Session.user)??>
                        <li>
                            <a href="/publish">提问</a>
                        </li>
                        <li><a href="/profile/replies">通知 <span class="badge"></span></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">
                                <span>${Session.user.name!'张三'}</span>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/profile/questions">我的问题</a></li>
                                <li><a href="/logout">退出登录</a></li>
                            </ul>
                        </li>
                    <#else>
                        <li>
                            <a href="https://github.com/login/oauth/authorize?client_id=Iv1.84767ed757986a3c&redirect_uri=http://localhost:8897/callback&scope=user&state=1">登录</a>
                        </li>
                    </#if>


                </ul>
            </div>
        </div>
    </nav>
</div>

</body>

</html>
