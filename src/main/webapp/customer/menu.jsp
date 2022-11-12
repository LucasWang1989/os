<%@ page import="nz.ac.sit.os.domain.product.ProductModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Title here -->
    <title>SIT. CAFE</title>
    <!-- Description, Keywords and Author -->
    <meta name="description" content="SIT. CAFE" />
    <meta name="keywords" content="SIT. CAFE" />
    <meta name="author" content="SIT. CAFE" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no" />
    <link rel="shortcut icon" href="images/ico/favicon.ico" type="image/x-icon">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/response.css" />
    <!-- jQuery -->
    <script src="js/jquery.js" type="text/javascript"></script>
    <!-- Custom JS -->
    <script type="text/javascript" src="js/custom.js"></script>
</head>
<body style="position:relative;">
    <div class="ng-scope">
        <div class="loca ng-scope">
            <a id="tableNo" class="change ng-binding" href="" tableno="<%=request.getAttribute("tableNo")%>">Table No: <%=request.getAttribute("tableNo")%></a>
        </div>
        <div class="ng-scope">
            <img src="img/tof/timg-6.jpg" width="100%">
        </div>
        <ul class="lists lists-tof ng-scope">
            <%
                List<ProductModel> products = (List<ProductModel>)request.getAttribute("products");
                for (ProductModel product : products) {
            %>
                    <li class="on" price="<%=product.getPrice().divide(new BigInteger("100"))%>" cart="2" productid="<%=product.getId()%>">
                        <div class="img view"><img class="pimgpath" src="<%=product.getImagePath()%>" alt="..." /></div>
                        <p class="t"><%=product.getName()%></p>
                        <p class="price">$<%=product.getPrice().divide(new BigInteger("100"))%></p>
                        <div class="showaddcart meshop">
                            <div class="num_con">
                                <img src="img/num_l.png" class="img-responsive num_l" alt="...">
                                <span class="number">0</span>
                                <img src="img/num_r.png" class="img-responsive num_r" alt="...">
                            </div>
                        </div>
                        <div class="detail">
                            <div class="back" onclick="CloseDetail(this);"><img src="img/close.png" class="img-responsive" alt="..." /></div>
                            <div class="title"></div>
                            <div class="img"><img src="img/tof/timg-6.jpg" class="img-responsive" alt="..." /></div>
                            <div class="desc">

                            </div>
                        </div>
                    </li>
            <%
                }
            %>
        </ul>
    </div>
    <!-- pay-->
    <div class="cart ng-scope">
        <div class="r"><a id="cart-link1" href="cart.html" onclick="toCart()">Confirm</a></div>
        <div class="l ng-binding">
            $<label id="totalmoney">0</label>
        </div>
        <i id="totalcartnumber" class="ng-binding cartnum">0</i>
        <a href="cart.html" id="cart-link2" onclick="toCart()"><img src="img/shop_cart01.png" alt="..."></a>
    </div>
    <!--/pay-->
</body>

<script type="text/javascript">
    $(document).ready(function () {
        if(sessionStorage.length > 0) {
            sessionStorage.clear();
        }
    });
    
    function toCart() {


        if(sessionStorage.length == 0) {
            $("#cart-link1").attr("href","cart_empty.html");
            $("#cart-link2").attr("href","cart_empty.html");

            var tableno = $("#tableNo").attr("tableno");
            var table = {"id":tableno};
            sessionStorage.setItem("table", JSON.stringify(table));
        }else {
            var tableno = $("#tableNo").attr("tableno");
            var table = {"id":tableno};
            sessionStorage.setItem("table", JSON.stringify(table));
        }
    }
</script>
</html>
