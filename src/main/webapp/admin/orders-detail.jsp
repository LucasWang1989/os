<%@ page import="nz.ac.sit.os.domain.product.ProductModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Restaurant Admin Dashboard </title>
	<!-- Favicon icon -->
	<link rel="icon" type="image/png" sizes="16x16" href="./images/favicon.png">
	<link href="./vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="./vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">

	<%
		List<ProductModel> orderProducts = (List<ProductModel>)request.getAttribute("orderProducts");
	%>
</head>
	<body>

		<!--*******************
			Preloader start
		********************-->
		<div id="preloader">
			<div class="sk-three-bounce">
				<div class="sk-child sk-bounce1"></div>
				<div class="sk-child sk-bounce2"></div>
				<div class="sk-child sk-bounce3"></div>
			</div>
		</div>
		<!--*******************
			Preloader end
		********************-->

		<!--**********************************
			Main wrapper start
		***********************************-->
		<div id="main-wrapper">
			<!--**********************************
				Nav header start
			***********************************-->
			<div class="nav-header">
				<a href="fetch-order" class="brand-logo">
					<img src="images/logo.png" width="30%">
					<div class="brand-title">Café</div>
				</a>
				<div class="nav-control">
					<div class="hamburger">
						<span class="line"></span><span class="line"></span><span class="line"></span>
					</div>
				</div>
			</div>
			<!--**********************************
				Nav header end
			***********************************-->

			<!--**********************************
				Header start
			***********************************-->
			<div class="header">
				<div class="header-content">
					<nav class="navbar navbar-expand">
						<div class="collapse navbar-collapse justify-content-between">
							<div class="header-left">
								<div class="dashboard_bar">
									Order Detail
								</div>
							</div>

							<ul class="navbar-nav header-right">
								<li class="nav-item dropdown header-profile">
									<a class="nav-link" href="#" role="button" data-toggle="dropdown">
										<img src="images/profile/pic1.jpg" width="20" alt=""/>
										<div class="header-info">
											<span class="fs-20 font-w500">Lucas Wang</span>
											<small>Super Admin</small>
										</div>
									</a>
									<div class="dropdown-menu dropdown-menu-right">
										<a href="./login.html" class="dropdown-item ai-icon">
											<svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
											<span class="ml-2">Logout </span>
										</a>
									</div>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
			<!--**********************************
				Header end ti-comment-alt
			***********************************-->

			<!--**********************************
				Sidebar start
			***********************************-->
			<div class="deznav">
				<div class="deznav-scroll">
					<ul class="metismenu" id="menu">
						<li><a class=" ai-icon" href="/admin/fetch-order" aria-expanded="false">
							<i class="flaticon-044-menu"></i>
							<span class="nav-text">Orders List</span>
						</a>
						</li>
						<li><a class=" ai-icon" href="/admin/fetch-product" aria-expanded="false">
							<i class="flaticon-031-ellipsis"></i>
							<span class="nav-text">Product List</span>
						</a>
						</li>
						<li><a class=" ai-icon" href="/admin/fetch-latest-waited-order" aria-expanded="false">
							<i class="flaticon-044-menu"></i>
							<span class="nav-text">Screen Display</span>
						</a>
						</li>
					</ul>


				</div>
			</div>
			<!--**********************************
				Sidebar end
			***********************************-->

			<!--**********************************
				Content body start
			***********************************-->
			<div class="content-body">
				<!-- row -->
				<div class="container-fluid">
					<div class="container-fluid">
						<div class="d-flex mb-3 mb-lg-4 align-items-center">
							<div class="mr-auto d-none d-lg-block">
								<div class="d-flex flex-wrap align-items-center">
									<h3 class="text-black font-w600 mb-0 fs-28 mr-5">#<%=orderProducts.get(0).getOrderNo()%></h3>
									<input id="orderNoInput" type="hidden" value="<%=orderProducts.get(0).getOrderNo()%>">
									<div class="d-flex">
										<a class="mb-0 text-black font-w500 fs-18" href="#">Orders / </a>
										<a class="mb-0 font-w400 fs-18 ml-2" href="#" > Order Detaills </a>
									</div>
								</div>
							</div>
							<div class="d-flex align-items-center">
<%--								<a href="javascript:void(0);" class="btn btn-outline-danger text-nowrap rounded-0 mr-3 ">Cancel Order</a>--%>
									<div class="btn btn-success d-block text-white rounded-0" onclick="cooked()">
										<svg class="mr-2" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
											<path d="M14.7576 15.8041H16.5252C16.4832 16.2551 16.1031 16.6094 15.6414 16.6094C15.1797 16.6094 14.7995 16.2551 14.7576 15.8041ZM8.32598 16.6094C8.78766 16.6094 9.16781 16.2551 9.20977 15.8041H7.4422C7.48411 16.2551 7.86427 16.6094 8.32598 16.6094ZM6.11719 14.6791H13.0004V8.56444V7.39064H6.11719V14.6791ZM24 12C24 18.6168 18.6168 24 12 24C5.38317 24 0 18.6168 0 12C0 5.38317 5.38317 0 12 0C18.6168 0 24 5.38317 24 12ZM19.0078 11.7096C19.0078 11.5545 18.9782 11.4095 18.9172 11.2662L17.844 8.73923C17.6538 8.29134 17.2027 8.00194 16.695 8.00194H14.1254V6.82814C14.1254 6.5175 13.8735 6.26564 13.5629 6.26564H5.55469C5.24405 6.26564 4.99219 6.5175 4.99219 6.82814V15.2416C4.99219 15.5523 5.24405 15.8041 5.55469 15.8041H6.31509C6.35873 16.8758 7.24378 17.7344 8.32598 17.7344C9.40819 17.7344 10.2932 16.8758 10.3369 15.8041H13.6305C13.6741 16.8758 14.5592 17.7344 15.6414 17.7344C16.7236 17.7344 17.6086 16.8758 17.6523 15.8041H18.4453C18.756 15.8041 19.0078 15.5523 19.0078 15.2416V11.7096ZM16.8086 9.17906C16.7923 9.14067 16.7392 9.12694 16.695 9.12694H14.1254V14.6791H17.8828V11.7096C17.8828 11.709 17.8828 11.7087 17.8829 11.7086C17.8825 11.7077 17.8822 11.7069 17.8818 11.706L16.8086 9.17906Z" fill="white"/>
										</svg>
										Delivered
									</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xl-12">
								<div class="row">
									<div class="col-xl-12">
										<div class="card">
											<div class="card-body p-0">
												<div class="table-responsive order-list card-table">
													<table class="table items-table table-responsive-md">
														<tbody>
														<tr class="bg-primary">
															<th class="text-black font-w500 fs-20">Items</th>
															<th style="width:10%;" class="text-black font-w500 fs-20">Qty</th>
															<th style="width:10%;" class="text-black font-w500 fs-20">Price</th>
															<th class="my-0 text-black font-w500 fs-20 wspace-no d-md-none d-lg-table-cell">Total Price</th>
															<th></th>
														</tr>
														<%
															for (ProductModel orderProduct : orderProducts) {
																BigInteger totalPrice = orderProduct.getDishNumber()
																		.multiply(orderProduct.getPrice()).divide(new BigInteger("100"));
														%>
														<tr>
															<td>
																<div class="media">
																	<a href="ecom-product-detail.html"><img class="mr-3 img-fluid rounded" src="/customer/<%=orderProduct.getImagePath()%>" alt="DexignZone" width="87px" height="87px" ></a>
																	<div class="media-body">
																		<small class="mt-0 mb-1 font-w500"><a class="text-primary" href="javascript:void(0);">MAIN COURSE</a></small>
																		<h5 class="mt-0 mb-2 mb-sm-3"><a class="text-black" href="ecom-product-detail.html"><%=orderProduct.getName()%></a></h5>
																		<div class="star-review d-flex fs-14">
																			<i class="fa fa-star text-orange"></i>
																			<i class="fa fa-star text-orange"></i>
																			<i class="fa fa-star text-orange"></i>
																			<i class="fa fa-star text-gray"></i>
																			<i class="fa fa-star text-gray"></i>
																			<span class="ml-3 text-dark">(454 revies)</span>
																		</div>
																	</div>
																</div>
															</td>
															<td>
																<h4 class="my-0  font-w600"><%=orderProduct.getDishNumber()%>x</h4>
															</td>
															<td>
																<h4 class="my-0  font-w600">$<%=orderProduct.getPrice().divide(new BigInteger("100"))%></h4>
															</td>
															<td class="d-md-none d-lg-table-cell">
																<h4 class="my-0  font-w600">$<%=totalPrice%></h4>
															</td>
														</tr>
														<%
															}
														%>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xl-12">
										<div class="card">
											<div class="widget-timeline-icon">
												<ul class="timeline">
													<li>
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Order Created</h4>
														</a>
													</li>

													<%
														// Pay success and no to cook
														if("1".equals(orderProducts.get(0).getPayStatus())
														&& "0".equals(orderProducts.get(0).getCookingStatus())) {
													%>
													<li>
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Payment Success</h4>
															<!--															<p class="fs-14 mb-0 ">Fri, 22 Jul 2020, 10:44 AM</p>-->
														</a>
													</li>
													<li class="border-success">
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">On Cooking</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<li>
														<div class="icon bg-success"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Order Delivered</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<%
														// Pay success and finish cooking
													}else if("1".equals(orderProducts.get(0).getPayStatus())
															&& "1".equals(orderProducts.get(0).getCookingStatus())) {

													%>
													<li>
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Payment Success</h4>
															<!--															<p class="fs-14 mb-0 ">Fri, 22 Jul 2020, 10:44 AM</p>-->
														</a>
													</li>
													<li>
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">On Cooking</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<li>
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Order Delivered</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<%
														// no pay
													}else if("0".equals(orderProducts.get(0).getPayStatus())){
													%>
													<li class="border-success">
														<div class="icon bg-primary"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Payment Success</h4>
															<!--															<p class="fs-14 mb-0 ">Fri, 22 Jul 2020, 10:44 AM</p>-->
														</a>
													</li>
													<li class="border-success">
														<div class="icon bg-success"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">On Cooking</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<li>
														<div class="icon bg-success"></div>
														<a class="timeline-panel text-muted" href="#">
															<h4 class="mb-2 mt-0">Order Delivered</h4>
															<!--															<p class="fs-14 mb-0 ">Sat, 23 Jul 2020, 01:24 PM</p>-->
														</a>
													</li>
													<%
													}
													%>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--**********************************
				Content body end
			***********************************-->

		</div>
		<!--**********************************
			Main wrapper end
		***********************************-->

<!--**********************************
    Scripts
***********************************-->
<!-- Required vendors -->
<script src="./vendor/global/global.min.js"></script>
<script src="./vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
<script src="./vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="./js/plugins-init/datatables.init.js"></script>
<script src="./js/custom.js"></script>
<script src="./js/deznav-init.js"></script>
<script src="./js/demo.js"></script>
<!--<script src="./js/styleSwitcher.js"></script>-->
<script type="text/javascript">
	function cooked() {
		var orderNo = $("#orderNoInput").val();

		var tempform = document.createElement("form");

		tempform.action = "update-order-cooking-status";
		tempform.method = "post";
		tempform.style.display="none"
		tempform.enctype = "application/x-www-form-urlencoded;charset=utf-8";

		var opt = document.createElement("input");
		opt.name = "orderNo";
		opt.value = orderNo;
		tempform.appendChild(opt);

		var opt2 = document.createElement("input");
		opt2.name = "mode";
		opt2.value = "1";
		tempform.appendChild(opt2);

		var opt2 = document.createElement("input");
		opt2.type = "submit";
		tempform.appendChild(opt2);
		document.body.appendChild(tempform);
		tempform.submit();
		document.body.removeChild(tempform);
	}
</script>
</body>
</html>