<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create View</title>
</head>
<body>
<div align="center">
    <h2><a href="/Book">Book Management</a></h2>
    <fieldset>
        <legend>Create Information</legend>

        <form method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="price" id="price"></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" id="description"></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td><select name="categoryList" id="categoryList" multiple>
                        <c:forEach items="${categoryList}" var="cate">
                            <option value="${cate.id}">${cate.name}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Create"></td>
                </tr>
            </table>
        </form>
    </fieldset>
    <h3><c:if test="${message!=null}">
        <span>${message}</span>
    </c:if></h3>
</div>

</body>
</html>