<%@ page import="nz.ac.sit.os.domain.product.ProductModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- Title here -->
    <title>SIT. CAFE -- ORDER LIST</title>
    <!-- Description, Keywords and Author -->
    <meta name="description" content="SIT. CAFE -- ORDER LIST" />
    <meta name="keywords" content="SIT. CAFE -- ORDER LIST" />
    <meta name="author" content="SIT. CAFE -- ORDER LIST" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no" />
    <link rel="shortcut icon" href="Images/ico/favicon.ico" type="image/x-icon">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/response.css" />
    <!-- jQuery -->
    <script src="js/jquery.js" type="text/javascript"></script>
    <!-- Custom JS -->
    <script type="text/javascript" src="js/custom.js"></script>

    <%
        List<ProductModel> orderProducts = (List<ProductModel>)request.getAttribute("orderProducts");
        String tableNo = "";
        String orderNo = "";
        if(!orderProducts.isEmpty()) {
            tableNo = orderProducts.get(0).getTableNo();
            orderNo = orderProducts.get(0).getOrderNo();
        }

        BigInteger totalPrice = new BigInteger("0");
    %>
</head>
<body>
    <div class="ng-scope">
        <header class="ng-scope">
            <h4 class="title-order">
                <a class="title-top" href="member.html">My Order</a>
            </h4>
        </header>
        <div class="orderlist" id="order_lists">
            <ul class="lists ng-scope">
                <li>
                    <p>2018-06-16 17:58:49 <span class="status">PAID</span></p>
                    <p>Order No: <%=orderNo%></p>
                    <p>Table No: <%=tableNo%></p>
                    <ul class="pdtlist">
                        <%
                            for (ProductModel orderProduct : orderProducts) {
                                totalPrice = totalPrice.add(orderProduct.getDishNumber()
                                        .multiply(orderProduct.getPrice()).divide(new BigInteger("100")));
                        %>
                            <li>
                                <div class="l">
                                    <img src="/customer/<%=orderProduct.getImagePath()%>"
                                         class="img-responsive" alt="..." width="87px" height="87px"/>
                                </div>

                                <div class="r">
                                    <p class="t"><%=orderProduct.getName()%></p>
                                    <p class="money">$<%=orderProduct.getPrice().divide(new BigInteger("100"))%> x
                                        <%=orderProduct.getDishNumber()%></p>
                                </div>
                            </li>
                        <%
                            }
                        %>
                    </ul>
                    <div class="bottom">
                        <p>Total: $<%=totalPrice%></p>
                        <p class="money">Actually Paid: $<%=totalPrice%></p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>

