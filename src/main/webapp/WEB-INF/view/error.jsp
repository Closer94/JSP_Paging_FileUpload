<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
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
           style="width:800px;padding-top: 70px;font-size:60px;font-family: 'Jua', sans-serif;">
            <a href="/newsSearch" style="text-decoration:none; ">404 오류 페이지 입니다.</a>

    </div>

</body>
</html>