<%--
  Created by IntelliJ IDEA.
  User: khs13
  Date: 2022-06-12
  Time: 오후 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eat</title>
    <link rel="stylesheet" href="${'resource/css/'}eatStyle.css">
    <script async src="${'resource/js/'}eat.js"></script>
</head>
<body>
<div class="background"></div>
<main>
    <div class="form-div">
        <h3>Find Menu<br><span class="border-bottom">Choose Menu</span></h3>
        <form class="form">
            <div class="category">
                <label>
                    <input type="button" value="1">한식
                </label>
                <label>
                    <input type="button" value="2">양식
                </label>
                <label>
                    <input type="button" value="3">중식
                </label>
                <label>
                    <input type="button" value="4">일식
                </label>
            </div>
            <div class="search">
                <input name="food" placeholder="food name">
                <input type="submit" value="Find">
            </div>
        </form>
    </div>
    <div class="food-list">
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/pizza.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pizza
            </div>
        </div>
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/pizza1.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pizza1
            </div>
        </div>
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/bread.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pang
            </div>
        </div>
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/bread.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pang
            </div>
        </div>
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/bread.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pang
            </div>
        </div>
        <div class="food">
            <div class="food-img">
                <img src="${'resource/img/'}food/bread.jpg" alt="pizza">
            </div>
            <div class="food-name">
                pang
            </div>
        </div>
    </div>
    <div class="food-rating">
        <div class="food-img">
            <img src="${'resource/img/'}food/bread.jpg" alt="pizza">
        </div>
        <div class="food-avr">평점 <span class="avr">5.0</span></div>
        <div class="rating-star">
            <div class="text">내 평가</div>
            <span class="star">
                <label data-value="1">★
                    <input type="radio" name="star" value="1">
                </label>
            </span>
            <span class="star">
                <label data-value="2">★
                    <input type="radio" name="star" value="2">
                </label>
            </span>
            <span class="star">
                <label data-value="3">★
                    <input type="radio" name="star" value="3">
                </label>
            </span>
            <span class="star">
                <label data-value="4">★
                    <input type="radio" name="star" value="4">
                </label>
            </span>
            <span class="star">
                <label data-value="5">★
                    <input type="radio" name="star" value="5">
                </label>
            </span>
        </div>
        <div class="food-name">pang</div>
        <div class="food-cate">양식</div>
        <div class="food-reply">
            <div class="reply">
                <div class="star">★★★★★</div>
                <span class="text">부드럽고 바삭한 식감.</span>
                <div class="user_id">-khs-</div>
            </div>
            <div class="reply">
                <div class="star">★★★★★</div>
                <span class="text">단연코 후회하지 않을 2022년도 최고의 맛과 감동. 역사적인 순간이 바로 지금 이 순간.</span>
                <div class="user_id">-khs2-</div>
            </div>
        </div>
    </div>
    <div class="login">
        <label>ID
            <input type="text" name="user_id" value="">
        </label>
        <label>PW
            <input type="text" name="user_pw" class="">
        </label>
    </div>
</main>
</body>
</html>
