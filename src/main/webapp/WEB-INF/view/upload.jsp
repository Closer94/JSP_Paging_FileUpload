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
           style="width:500px; padding-top: 70px;font-size:60px;font-family: 'Jua', sans-serif; margin-left:100px"><a
                href="/newsSearch" style="text-decoration:none; ">파일업로드 시스템</a>

        <form action="/upload/files" method="post" enctype="multipart/form-data">

            <input class="form-control" type="file" id="formFileMultiple" multiple="multiple" name="file" />
            <br/>
            <div>
                <button class="btn btn-secondary" type="submit" name="save">업로드</button>
            </div>
        </form>

        <form name="fileForm" action="/upload/files" method="post" enctype="multipart/form-data">
                <input multiple="multiple"  type="file" name="picFile" required="required">
                <input type="submit" value="전송" />
        </form>
    </div>

</body>
</html>