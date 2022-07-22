<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <meta charset="utf-8">
    <title>
        뉴스 검색 시스템
    </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Noto+Sans+KR:100,300,400,500,700,900&display=swap&subset=korean"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Jua&display=swap&subset=korean" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap&subset=korean" rel="stylesheet">

    <style>
            table {
                width: 100%;
                border: 1px solid #444444;
                border-collapse: collapse;
            }
            .positionHead{
                max-width: 100px;
                min-width: 500px;
                margin:0 auto;
                margin-top: -15px;

            }
            .positionBody{
                max-width: 800px;
                margin:0 auto;
                margin-top: 110px;
            }
            a {
                display: inline-block;
                transition: .3s;
                -webkit-transform: scale(1);
                transform: scale(1);
                text-decoration: none;
                color: #000000;
            }
            #ulStyle{
                margin-left: 100px;
                padding: 10px;
                width: 1300px;
                height: 70px;
            }
            #liStyle{
                font-family: 'Do Hyeon', sans-serif;
                font-size: 25px;
                list-style:none;
                float: left;
                padding:0px;
            }
            .jb-text {
                padding: 5px 10px;
                text-align: center;
                position: absolute;

                transform: translate( -50%, -50% );
                font-size: 50px;
                color: white;
                font-family: 'Noto Sans KR', sans-serif;
            }

            input[type=text]:focus{
                background: #add8e6;
            }
            input[type=password]:focus{
                background: #add8e6;
            }

    </style>
</head>
<body style="background:#edf1f8;" onload="InitializeStaticMenu();">
<div class="positionHead">
    <p title="검색 페이지로 이동"
       style="width:400px;padding-top: 70px;font-size:60px;font-family: 'Jua', sans-serif; margin-left:100px"><a
            href="/newsSearch" style="text-decoration:none; ">뉴스 검색 시스템</a>
</div>

    <!-- 페이징 소스 -->
    <div style="padding-left: 90px;">
        <c:set var="pageNumber" value="${pageNumber}" />
        <c:set var="pageSize" value="${pageSize}" />
        <c:set var="totalPages" value="${totalPages}" />
        <c:set var="startPage" value="${startPage}" />
        <c:set var="endPage" value="${(totalPages == 0) ? 1 : (startPage + (pageSize - 1) < totalPages ? startPage + (pageSize - 1) : totalPages)}" />
        <c:set var="end" value="${totalPages/10 > 5 ? 5:totalPages/10 }" />
        <div>
            <table style="width:1000px;">
                <tbody>
                    <tr>
                        <td>페이지 번호</td>
                        <td>페이지 사이즈</td>
                        <td>전체 페이지 개수</td>
                        <td>시작 페이지</td>
                        <td>마지막 페이지</td>
                    </tr>
                    <tr>
                         <td><c:out value="${pageNumber}"/></td>
                         <td><c:out value="${pageSize}"/></td>
                         <td><c:out value="${totalPages}"/></td>
                         <td><c:out value="${startPage}"/></td>
                         <td><c:out value="${endPage}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <nav aria-label="Page navigation">

            <ul class="pagination">

                <li class="page-item">
                    <c:choose>
                        <c:when test="${startPage > 1}">
                            <a href="${pageContext.request.contextPath }?page=${startPage - pageSize}" class="page-link">◀</a>
                        </c:when>
                        <c:otherwise>
                             <span class="page-link" onclick="alert('이전 페이지가 없습니다.');">◀</span>
                        </c:otherwise>
                    </c:choose>
                </li>

            <c:forEach var="page" begin="${startPage}" end="${endPage}" >
                <li class="page-item">
                    <a href="paging?page=${page-1}" class="page-link">${page}</a>
                </li>
            </c:forEach>

                <li class="page-item">
                    <c:choose>
                        <c:when test="${endPage < totalPages}">
                            <a href="${pageContext.request.contextPath }/paging?page=${startPage + pageSize}" class="page-link">▶</a>
                        </c:when>
                        <c:otherwise>
                             <span class="page-link" onclick="alert('다음 페이지가 없습니다.');">▶</span>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </nav>
    </div>

    <br/>
    <div>
        <table border="1" class="table table-striped" style="width:500px; margin-left: 700px">
            <thead>
                <tr>
                    <th>No</th>
                    <th>모델명</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="model" items="${modelList}" varStatus="status">
                    <tr>
                        <td style="width:50px">${model.id}</td>
                        <td style="width:100px">${model.name}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>