<#import "login.ftlh" as out>
<#macro header path name>
    <div class="container mb-3 mt-3 head">
        <div class="row">
            <div class="col">
                <@out.logout/>
            </div>
            <div class="col">
                <span><a class="btn btn-primary" href="${path}">${name}</a></span>
            </div>
        </div>
    </div>
</#macro>