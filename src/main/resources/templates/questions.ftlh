<#import "parts/common.ftlh" as c>
<@c.page>
    <h1>
        Simple parser
        <a href="https://ru.stackoverflow.com/">
            russian stackoverflow
        </a>
    </h1>
    <hr>
    <h2>Questions</h2>
    <hr>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Details</th>
        </tr>
        <#list questions as q>
            <tr>
                <td>${q.name}</td>
                <td>
                    <a href="http://localhost:8080/${q.detail.id}">
                        http://localhost:8080/${q.detail.id}
                    </a>
                </td>
            </tr>
        </#list>
    </table>

</@c.page>
