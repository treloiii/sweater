<#import "parts/common.ftlh" as c>
<#import 'parts/header.ftlh' as h>
<@c.page>
    <@h.header "/main" "To main"/>
    <h1>Users list</h1>
    <div class="m-5">
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td><#list user.roles as role>${role} <#sep>, </#list></td>
                        <td><a href="/user/${user.id}">Edit</a></td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</@c.page>