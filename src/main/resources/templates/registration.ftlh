<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as login>
<@c.page>
    <h1>REGISTER</h1>
    <@login.login "/registration" true "${passwordError!}"  "${password2Error!}" "${usernameError!}" "${emailError!}"/>
</@c.page>