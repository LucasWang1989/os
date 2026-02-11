
$(document).ready(function () {
    //shop：加入购物车
    // var data=[{id:"",amount:""}];
    // sessionStorage.setItem("selectedDish", JSON.stringify(data));
    // var getdata = JSON.parse(sessionStorage.getItem("selectedDish"));
    // console.log(getdata);

    $(".lists li").each(function (i, model) {
        var objLi = $(this);
        //点击购物车
        $(objLi).find(".join").click(function () {
            $(objLi).addClass("on");
            $(objLi).find(".number").text(1);

            CountNumPrice();
        });
        //点击减数量
        $(objLi).find(".num_l").click(function () {
            var number = parseInt($(objLi).find(".number").text()) - 1;
            var cart = $(objLi).attr("cart");
            var productId = $(objLi).attr("productid");

            if (number > 0) {
                $(objLi).find(".number").text(number);
                var dish = JSON.parse(sessionStorage.getItem(productId));
                dish.amount = number;
                sessionStorage.setItem(productId, JSON.stringify(dish));
            } else {
                if (cart == 1) {//Operation on cart page
                    $(objLi).remove();

                } else if (cart == 2) {//Operation on menu page
                    if (number == 0) {
                        $(objLi).find(".number").text(number);
                    }
                } else {
                    $(objLi).removeClass("on");
                }

                sessionStorage.removeItem(productId);
            }

            CountNumPrice();
        });
        //点击加数量
        $(objLi).find(".num_r").click(function () {
            var number = parseInt($(objLi).find(".number").text()) + 1;
            var productId = $(objLi).attr("productid");

            $(objLi).find(".number").text(number);

            if(number == 1) {
                var name = $(objLi).find(".t").text();
                var imagePath = $(objLi).find(".pimgpath")[0].src;
                var price = $(objLi).attr("price");
                var dish = {"id":productId,
                    "amount":number,
                    "price":price,
                    "imagePaht":imagePath,
                    "name":name
                };
                sessionStorage.setItem(productId, JSON.stringify(dish));
            }else {
                var dish = JSON.parse(sessionStorage.getItem(productId));
                dish.amount = number;
                sessionStorage.setItem(productId, JSON.stringify(dish));
            }
            CountNumPrice();
        });
        //点击查看产品详情
        $(objLi).find(".view").click(function () {
            $(objLi).find(".detail").show(200);
        });
    });
    //addr：添加收货地址
    $("#btnSave").click(function () {
        if ($("#name").val() == "") {
            weui_dialog_alert(1);
            return;
        }
        if ($("#phone").val() == "") {
            weui_dialog_alert(2);
            return;
        }
        if ($("#dong").val() == "") {
            weui_dialog_alert(3);
            return;
        }
        if ($("#ceng").val() == "") {
            weui_dialog_alert(4);
            return;
        }
        if ($("#fang").val() == "") {
            weui_dialog_alert(5);
            return;
        }
        window.location.href="cart.html"
    });
    //order：订单tab切换
    // $(".navlist li").each(function () {
    //     $(this).click(function () {
    //         $(".navlist li").removeClass("active");
    //         $(this).addClass("active");
    //
    //         var type = $(this).attr("type");
    //         $(".orderlist").hide();
    //         $("#order_" + type + "").show();
    //     });
    // });
});
//address：搜索触发事件
function searchfocus() {
    $(".list").addClass("ng-hide");
}
//address：搜索焦点离开事件
function searchblur() {
    $(".list").removeClass("ng-hide");
}
//address：搜索改变事件
function kwsearch() {
    $("#branch1").removeClass("ng-hide");
}
//address：点击选择地址
function showselect(type) {
    if (type == 1) {
        $("#addrselect").removeClass("ng-hide");
    } else {
        $("#addrselect").addClass("ng-hide");
    }
}
//shop：计算数量和价格
function CountNumPrice() {
    var totalcartnumber = 0;//数量
    var totalmoney = 0;//价格
    $(".lists .on").each(function (i, model) {
        var objLi = $(this);
        var number = parseInt($(objLi).find(".number").text());
        var price = parseFloat($(objLi).attr("price"));
        
        totalcartnumber = totalcartnumber + number;
        totalmoney = totalmoney + (number * price);
    });
    $("#totalcartnumber").text(totalcartnumber);
    $("#totalmoney").text(totalmoney.toFixed(2));
}
//shop：关闭产品详情
function CloseDetail(obj) {
    $(obj).parent(".detail").hide(100);
}
//弹出dialog
function weui_dialog_alert(code) {
    var msg = "";
    if (code==1) {
        msg = "填写姓名";
    }
    if (code == 2) {
        msg = "手机号格式错误";
    }
    if (code == 3) {
        msg = "选择栋";
    }
    if (code == 4) {
        msg = "选择楼层";
    }
    if (code == 5) {
        msg = "填写房间号";
    }
    $("#_alert_").show();
    $("#_alert_ #alert-msg").html(msg);
}
//关闭dialog
function weui_dialog_close() {
    $("#_alert_").hide();
}