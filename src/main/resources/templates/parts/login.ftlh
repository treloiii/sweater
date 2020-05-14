<#macro login path isRegister>
    <div class="container">
        <form action="${path}" method="post">
            <div>
                <label> User Name : <input class="form-control" type="text" name="username"/> </label>
            </div>
            <div>
                <label> Password: <input class="form-control" type="password" name="password"/> </label>
            </div>
            <#if isRegister>
                <div>
                    <label> Email: <input class="form-control" type="email" name="email" placeholder="email@em.com"/> </label>
                </div>
            </#if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div>
                <input class="btn btn-primary mb-3" type="submit" value="Sign In"/>
            </div>
        </form>
    </div>
</#macro>

<#macro logout>
    <div class="container">
        <form action="/logout" method="post">
            <input class="btn btn-primary" type="submit" value="Sign Out"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>
</#macro>