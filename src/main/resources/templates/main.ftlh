<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as out>
<@c.page>
    <div>
        <@out.logout/>
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post" action="/main" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="enter message">
            <input type="text" name="tag" placeholder="tag">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Add</button>
        </form>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Search</button>
    </form>
    <div>Список сообщений</div>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <b>${message.tag}</b>
        <strong>${message.authorName}</strong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}">
            </#if>
        </div>
    </div>
    <#else>
        <p>No messages</p>
    </#list>
</@c.page>