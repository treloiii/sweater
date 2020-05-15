<#import "parts/common.ftlh" as c>
<@c.page>
    <h3>${username}</h3>
    <div class="container">
        <form method="post">

            <div>
                <label> Password: <input class="form-control" type="password" name="password"/> </label>
            </div>
                <div>
                    <label> Email: <input class="form-control" type="email" name="email" value="${email!''}" placeholder="email@em.com"/> </label>
                </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div>
                <input class="btn btn-primary mb-3" type="submit" value="Save"/>
            </div>
        </form>
    </div>
</@c.page>
