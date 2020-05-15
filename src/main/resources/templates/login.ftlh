<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as login>
<@c.page>
    <h1>LOGIN PAGE</h1>
    <#if Session??&& Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${type}" role="alert">
            ${message}
        </div>
    </#if>
    <@login.login "/login" false/>
    <a href="/registration">REGISTRATION</a>
</@c.page>
