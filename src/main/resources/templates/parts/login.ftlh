<#macro login path isRegister passwordError="" password2Error="" usernameError="" emailError="">
    <div class="container">
        <form action="${path}" method="post">
            <div>
                <label> User Name : <input class="form-control <#if usernameError!="">${(usernameError??)?string('is-invalid','')}</#if>" type="text" name="username"/> </label>
            </div>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
            <div>
                <label> Password: <input class="form-control <#if passwordError!="">${(passwordError??)?string('is-invalid','')}</#if>" type="password" name="password"/> </label>
            </div>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
            <#if isRegister>
                <div>
                    <label> Confirm password: <input class="form-control <#if password2Error!="">${(password2Error??)?string('is-invalid','')}</#if>" type="password" name="password2"/> </label>
                </div>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
                <div>
                    <label> Email: <input class="form-control <#if emailError!="">${(emailError??)?string('is-invalid','')}</#if>" type="email" name="email" placeholder="email@em.com"/> </label>
                </div>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
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