<#import "parts/common.ftlh" as c>
<@c.page>
    <h1>
        Simple parser
        <a href="https://ru.stackoverflow.com/">
            russian stackoverflow
        </a>
    </h1>
    <hr>
    <h2>Question details with name "${question.name}"</h2>
    <hr>
    <table border="1">
        <tr>
            <th>Author</th>
            <th>Date of publication</th>
            <th>Answers</th>
        </tr>

        <tr>
            <td>${question.detail.author}</td>
            <td>${question.detail.dateOfCreated}</td>
            <td>${question.detail.answers}</td>

        </tr>

    </table>

</@c.page>