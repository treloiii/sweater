<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<@c.page>
    <@h.header "/user" "Back"/>
    <h1>User Editor</h1>
    <div class="container mt-3">
        <form action="/user" method="post">
            <input class="form-control" type="text" value="${user.username}" name="username">
            <#list roles as role>
                <div>
                    <label><input type="checkbox" class="form-check-input" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
                </div>
            </#list>
            <input class="form-control" type="text" value="${user.id}" name="userId" hidden>
            <input type="text" value="${_csrf.token}" name="_csrf" hidden>
            <button class="btn btn-primary mt-3" type="submit">Submit</button>
        </form>
    </div>
</@c.page>