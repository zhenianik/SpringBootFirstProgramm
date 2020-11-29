<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Hello, guest</h5>
    <div>This is a simple clone off Twitter</div>
    <form method="post" enctype="multipart/form-data">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="some@some.com"/>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Добавить</button>
        </div>
    </form>
</@c.page>