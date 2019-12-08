<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 上面这两行是java代码的引用 --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="/static/js/jquery-3.1.1.min.js"></script>

<head>
    <title>仓库管理系统→登录</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="/static/js/login.js"></script>
    <link href="/static/css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<html>
<body>

<h1>仓库管理系统登陆注册<sup>2016</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);"
                                           tabindex="7">快速登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>

            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">

<%--
    action="<%=request.getContextPath()%>/userAction/reg" method="post"

<%=request.getContextPath()%> 这是指向我们应用的根路径

mothod是说明我们请求的方式，我们这里才用了post，至于其他的方法就不一一介绍了，详细信息请百度查找“ http请求 ”

form表单中，每个input的name我们需要和后端的接口那边的字段对应。

当我们的字段对应后，spring可以自动把请求的内容转换为适应的对象。

小提示：我们可以直接debug我们的程序，只要取消断点程序就可以顺序执行，加入断点只要程序流转到那里，他就会自动调试。
--%>
                <div class="login_form">
                    <form action="<%=request.getContextPath()%>/userAction/login" name="loginform"
                          accept-charset="utf-8" id="login_form" class="loginForm"
                          method="post"><input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>

                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>

                            <div class="inputOuter" id="uArea">

                                <input type="text" id="u" name="loginId" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>

                            <div class="inputOuter" id="pArea">

                                <input type="password" id="p" name="pwd" class="inputstyle"/>
                            </div>
                        </div>

                        <div style="padding-left:50px;margin-top:20px;">
                            <input type="submit" value="登 录"
                                   style="width:150px;"
                                   class="button_blue"/></div>
                    </form>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">

        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8" action="<%=request.getContextPath()%>/userAction/reg"
                  method="post">
                <input type="hidden" name="to" value="reg"/>
                <input type="hidden" name="did" value="0"/>
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li>

                        <label for="user" class="input-tips2">用户名：</label>

                        <div class="inputOuter2">
                            <input type="text" id="user" name="loginId" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>

                        <label for="user" class="input-tips2">姓名：</label>

                        <div class="inputOuter2">
                            <input type="text" id="name" name="name" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>

                        <div class="inputOuter2">
                            <input type="password" id="passwd" name="pwd" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>

                        <div class="inputOuter2">
                            <input type="password" id="passwd2" name="" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="cellNumber" class="input-tips2">手机号：</label>

                        <div class="inputOuter2">

                            <input type="text" id="cellNumber" name="cellNumber" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="sex" class="input-tips2">性别：</label>

                        <div class="inputOuter2">

                            <input type="text" id="sex" name="sex" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="duty" class="input-tips2">duty：</label>

                        <div class="inputOuter2">

                            <input type="text" id="duty" name="duty" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="age" class="input-tips2">年龄：</label>

                        <div class="inputOuter2">

                            <input type="age" id="age" name="age" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <div class="inputArea">
                            <input type="button" id="reg" style="margin-top:10px;margin-left:85px;" class="button_blue"
                                   value="同意协议并注册"/> <a href="#" class="zcxy" target="_blank">注册协议</a>
                        </div>

                    </li>
                    <div class="cl"></div>
                </ul>
            </form>


        </div>


    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>

</body>
</html>
