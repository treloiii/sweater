<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as login>
<@c.page>
    REGISTER
    ${message!}
    <@login.login "/registration"/>
</@c.page>