<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Vinpriser</title>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="wineprices.css?version=1.4" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center mainHeader">
                    <h1>Vinpriser</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 headerTile">
                    <p class="priceHeader">Navn</p>
                </div>
                <div class="col-md-2 headerTile">
                    <p class="priceHeader">Pris</p>
                </div>
                <div class="col-md-2 headerTile">
                    <p class="priceHeader">Antal</p>
                </div>
                <div class="col-md-2 headerTile">
                    <p class="priceHeader">Opdateret</p>
                </div>
            </div>

            <c:forEach var="price" items="${winePrices}">
                <div class="row">
                    <div class="col-md-6 rowTile">
                        <p>${price.name}&nbsp;&nbsp;<a href="${price.url}"><span class="glyphicon glyphicon-link"></span></a></p>
                    </div>
                    <div class="col-md-2 rowTile">
                        <p>${price.price} kr.&nbsp;&nbsp;<a href="#"><span class="glyphicon glyphicon-eye-open"></span></a></p>
                    </div>
                    <div class="col-md-2 rowTile">
                        <p>${price.amount}</p>
                    </div>
                    <div class="col-md-2 rowTile">
                        <p>${price.date}</p>
                    </div>
                </div>
            </c:forEach>

            <div class="row">
                <div class="col-md-12 text-center summaryTile">
                    Antal vine fra ${sourceName}: ${wineCount}
                </div>
            </div>
        </div>

        <script src="//code.jquery.com/jquery-3.2.1.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>