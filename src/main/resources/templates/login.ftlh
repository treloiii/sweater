<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as login>
<@c.page>
    <h1>LOGIN PAGE</h1>
    <@login.login "/login"/>
    <a href="/registration">REGISTRATION</a>
</@c.page>
