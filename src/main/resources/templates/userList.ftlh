<#import "parts/common.ftlh" as c>

<@c.page>
    Users list
    <table>
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
</@c.page>