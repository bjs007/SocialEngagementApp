<!-- Created by Lei Liu -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
        .main{

            height: 24px;
                        width:100%;
        }
        .left{
            float: left;
            width: 300px;
            height: 23px;
                        margin-left:10px;
        }
                .right{
           margin-right: 10px;
            height: 23px;
            margin-left: 350px;
        }
        body{
            font: 16px/1.5 "Mircisoft Yahei";
        }
        img{
            display: block;
        }
        a{
            text-decoration: none;
            text-underline: none;

        }
        .demo{
            width: 600px;
            /*height: 400px;*/
            border: 1px solid #ddd;
            margin:0 auto;
        }
        .title{
            overflow: hidden;
        }
        .title img{
            float: left;
            margin-right: 10px;
}

        .title .name{
            font-weight: bold;
        }
        .title .intro{
            color:#999;
        }
        .content h1{
            color: blue;
        }
        .content a{
            display: inline-block;
            height: 20px;
            border: 1px solid #ddd;
            background-color: #ddd;
            color: #000;
        }
        .comment{
            overflow: hidden;
            margin-top: 20px;
        }
        .comment img{
            float: left;
        }
</style>
<div class="main">
    <div class="left">
        <form action="${pageContext.request.contextPath}/selectbydate" method="get">
                Date Filter: <input type="date" name="user_date"/><input type="submit" value="Filter"/>
        </form>
    </div>
    <div class="right" align="center">
       <form action="${pageContext.request.contextPath}/selectbytype" method="get">
                 Type Filter:
                 <select name="events types">
                        <option value="empty"></option>
                        <option value="events">Events</option>
                                <option value="broadcast">Broadcast</option>
                        </select>
                        <input type="submit" value="Filter"/>
                </form>
    </div>
</div>
<br>
<div id="tt">
        <c:forEach items="${list}" var="home">
        <div class="demo">
                <div class="title">
                <c:choose>
                <c:when test="${(home.entry_type == 1)}">
                <img src="images/1.png"/>
                </c:when>
                <c:otherwise>
                <img src="images/2.png"/>
                </c:otherwise>
                </c:choose>
                <p class="name">User: ${home.user_id}</p>
                <p class="intro">Post Date: ${home.create_date_time}</p>
        </div>
        <div class="content">
                <p>${home.activity_desc}</p>
        </div>
        <c:if test="${(home.entry_type == 1)}">
        <div style="text-align:right"><a href="${pageContext.request.contextPath}/editEvent?event_id=${home.post_id}">Go to Event</a></div>
        </c:if>
        <div class="comment" align="center">
                <form method="get" action="${pageContext.request.contextPath}/comsubmit">
                        <input type="hidden" name = "info" value = "${home }">
                        <textarea name = "infoarea" style="width:300px;height:80px;"></textarea>
                <input type="submit" value="SUBMIT COMMENTS">
                </form>
        </div>
        </div>
        <br>
        </c:forEach>
</div>