<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as out>
<@c.page>
    <div class="container mb-3 mt-3 head">
        <div class="row">
            <div class="col">
                <@out.logout/>
            </div>
            <div class="col">
                <span><a class="btn btn-primary" href="${ref}"><#if ref=="/user">User list<#else>Profile</#if></a></span>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col">
            </div>
            <div class="col">
                <form method="post" action="/main" enctype="multipart/form-data">
                    <input class="form-control ${(textError??)?string('is-invalid','')}" value="<#if message??>${message.text}</#if>" type="text" name="text" placeholder="enter message">
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                    <input class="form-control ${(tagError??)?string('is-invalid','')}" value="<#if message??>${message.tag}</#if>" type="text" name="tag" placeholder="tag">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                    <div class="custom-file">
                        <input class="custom-file-input" type="file" id="custom-file" name="file">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                    <input class="form-control" type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="btn btn-primary mb-3 mt-3" type="submit">Add</button>
                </form>
            </div>
            <div class="col">
                <form method="get" action="/main">
                    <input class="form-control" placeholder="search filter" type="text" name="filter" value="${filter!}">
                    <button class="btn btn-primary mb-3 mt-3" type="submit">Search</button>
                </form>
            </div>
        </div>
    </div>
    <div><h3>Список сообщений</h3></div>
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