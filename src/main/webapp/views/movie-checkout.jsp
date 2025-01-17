<%@ page import="model.Order" %>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from pixner.net/boleto/demo/movie-checkout by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 21 Mar 2024 12:43:36 GMT -->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/all.min.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/flaticon.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/odometer.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <link rel="stylesheet" href="assets/css/main.css">

    <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon">

    <title>Boleto  - Online Ticket Booking Website HTML Template</title>


</head>

<body>
<!-- ==========Preloader========== -->
<div class="preloader">
    <div class="preloader-inner">
        <div class="preloader-icon">
            <span></span>
            <span></span>
        </div>
    </div>
</div>
<!-- ==========Preloader========== -->
<!-- ==========Overlay========== -->
<div class="overlay"></div>
<a href="#0" class="scrollToTop">
    <i class="fas fa-angle-up"></i>
</a>
<!-- ==========Overlay========== -->

<!-- ==========Header-Section========== -->
<header class="header-section">
    <div class="container">
        <div class="header-wrapper">
            <div class="logo">
                <a href="index">
                    <img src="assets/images/logo/logo.png" alt="logo">
                </a>
            </div>
            <ul class="menu">
                <li>
                    <a href="#0">Home</a>
                    <ul class="submenu">
                        <li>
                            <a href="index">Home One</a>
                        </li>
                        <li>
                            <a href="index-2">Home Two</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#0" class="active">movies</a>
                    <ul class="submenu">
                        <li>
                            <a href="movie-grid">Movie Grid</a>
                        </li>
                        <li>
                            <a href="movie-list">Movie List</a>
                        </li>
                        <li>
                            <a href="movie-details">Movie Details</a>
                        </li>
                        <li>
                            <a href="movie-details-2">Movie Details 2</a>
                        </li>
                        <li>
                            <a href="movie-ticket-plan">Movie Ticket Plan</a>
                        </li>
                        <li>
                            <a href="movie-seat-plan">Movie Seat Plan</a>
                        </li>
                        <li>
                            <a href="#0" class="active">Movie Checkout</a>
                        </li>
                        <li>
                            <a href="popcorn">Movie Food</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#0">events</a>
                    <ul class="submenu">
                        <li>
                            <a href="events">Events</a>
                        </li>
                        <li>
                            <a href="event-details">Event Details</a>
                        </li>
                        <li>
                            <a href="event-speaker">Event Speaker</a>
                        </li>
                        <li>
                            <a href="event-ticket">Event Ticket</a>
                        </li>
                        <li>
                            <a href="event-checkout">Event Checkout</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#0">sports</a>
                    <ul class="submenu">
                        <li>
                            <a href="sports">Sports</a>
                        </li>
                        <li>
                            <a href="sport-details">Sport Details</a>
                        </li>
                        <li>
                            <a href="sports-ticket">Sport Ticket</a>
                        </li>
                        <li>
                            <a href="sports-checkout">Sport Checkout</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#0">pages</a>
                    <ul class="submenu">
                        <li>
                            <a href="about">About Us</a>
                        </li>
                        <li>
                            <a href="apps-download">Apps Download</a>
                        </li>
                        <li>
                            <a href="sign-in">Sign In</a>
                        </li>
                        <li>
                            <a href="sign-up">Sign Up</a>
                        </li>
                        <li>
                            <a href="404">404</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#0">blog</a>
                    <ul class="submenu">
                        <li>
                            <a href="blog">Blog</a>
                        </li>
                        <li>
                            <a href="blog-details">Blog Single</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="contact">contact</a>
                </li>
                <li class="header-button pr-0">
                    <a href="sign-up">join us</a>
                </li>
            </ul>
            <div class="header-bar d-lg-none">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
    </div>
</header>
<!-- ==========Header-Section========== -->

<!-- ==========Banner-Section========== -->
<%
    Order myOrder  = (Order) request.getAttribute("order");
    if(myOrder != null){
%>
<%
} else {
%>
<script>
    alert("Not found your order")
    window.location = "index"
</script>
<%
    }
%>
<section class="details-banner hero-area bg_img seat-plan-banner" data-background="assets/images/banner/banner04.jpg">
    <div class="container">
        <div class="details-banner-wrapper">
            <div class="details-banner-content style-two">
                <h3 class="title">${order.getTicket().getMovie().getTitle()}</h3>
                <div class="tags">
                    <a href="#0">${order.getTicket().getCinema()}</a>
                    <a href="#0">Vietnamese | English sub - 2D</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ==========Banner-Section========== -->

<!-- ==========Page-Title========== -->
<section class="page-title bg-one">
    <div class="container">
        <div class="page-title-area">
            <div class="item md-order-1">
                <a href="movie-ticket-plan" class="custom-button back-button">
                    <i class="flaticon-double-right-arrows-angles"></i>back
                </a>
            </div>
            <div class="item date-item">
                <span class="date">${order.getTicket().getShowTime()}</span>
                <select class="select-bar">
                    <option value="sc1">09:40</option>
                    <option value="sc2">13:45</option>
                    <option value="sc3">15:45</option>
                    <option value="sc4">19:50</option>
                </select>
            </div>
            <div class="item">
                <h5 class="title">05:00</h5>
                <p>Mins Left</p>
            </div>
        </div>
    </div>
</section>
<!-- ==========Page-Title========== -->

<!-- ==========Movie-Section========== -->
<div class="movie-facility padding-bottom padding-top">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="checkout-widget d-flex flex-wrap align-items-center justify-cotent-between">
                    <div class="title-area">
                        <h5 class="title">Already a Boleto  Member?</h5>
                        <p>Sign in to earn points and make booking easier!</p>
                    </div>
                    <a href="#0" class="sign-in-area">
                        <i class="fas fa-user"></i><span>Sign in</span>
                    </a>
                </div>
                <div class="checkout-widget checkout-contact">
                    <h5 class="title">Share your Contact  Details </h5>
                    <form class="checkout-contact-form">
                        <div class="form-group">
                            <input type="text" placeholder="Full Name">
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Enter your Mail">
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Enter your Phone Number ">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Continue" class="custom-button">
                        </div>
                    </form>
                </div>
                <div class="checkout-widget checkout-contact">
                    <h5 class="title">Promo Code </h5>
                    <form class="checkout-contact-form">
                        <div class="form-group">
                            <input type="text" placeholder="Please enter promo code">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Verify" class="custom-button">
                        </div>
                    </form>
                </div>
                <div class="checkout-widget checkout-card mb-0">
                    <h5 class="title">Payment Option </h5>
                    <ul class="payment-option">
                        <li class="active">
                            <a href="#0">
                                <img src="assets/images/payment/card.png" alt="payment">
                                <span>Credit Card</span>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <img src="assets/images/payment/card.png" alt="payment">
                                <span>Debit Card</span>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <img src="assets/images/payment/paypal.png" alt="payment">
                                <span>paypal</span>
                            </a>
                        </li>
                    </ul>
                    <h6 class="subtitle">Enter Your Card Details </h6>
                    <form class="payment-card-form">
                        <div class="form-group w-100">
                            <label for="card1">Card Details</label>
                            <input type="text" id="card1">
                            <div class="right-icon">
                                <i class="flaticon-lock"></i>
                            </div>
                        </div>
                        <div class="form-group w-100">
                            <label for="card2"> Name on the Card</label>
                            <input type="text" id="card2">
                        </div>
                        <div class="form-group">
                            <label for="card3">Expiration</label>
                            <input type="text" id="card3" placeholder="MM/YY">
                        </div>
                        <div class="form-group">
                            <label for="card4">CVV</label>
                            <input type="text" id="card4" placeholder="CVV">
                        </div>
                        <div class="form-group check-group">
                            <input id="card5" type="checkbox" checked>
                            <label for="card5">
                                <span class="title">QuickPay</span>
                                <span class="info">Save this card information to my Boleto  account and make faster payments.</span>
                            </label>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="custom-button" value="make payment">
                        </div>
                    </form>
                    <p class="notice">
                        By Clicking "Make Payment" you agree to the <a href="#0">terms and conditions</a>
                    </p>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="booking-summery bg-one">
                    <h4 class="title">booking summery</h4>
                    <ul>
                        <li>
                            <h6 class="subtitle">${order.getTicket().getMovie().getTitle()}</h6>
                            <span class="info">Vietnamese | English-2d</span>
                        </li>
                        <li>
                            <h6 class="subtitle"><span>${order.getTicket().getCinema()}</span><span>${order.getTicket().getSeatNumber().size()}</span></h6>
                            <div class="info"><span>10 SEP TUE, 11:00 PM</span> <span>Tickets</span></div>
                        </li>
                        <li>
                            <h6 class="subtitle mb-0"><span>Tickets  Price</span><span>$${order.getTicket().getPrice()}</span></h6>
                        </li>
                    </ul>
                    <%--                        <ul class="side-shape">--%>
                    <%--                            <li>--%>
                    <%--                                <h6 class="subtitle"><span>combos</span><span>$57</span></h6>--%>
                    <%--                                <span class="info"><span>2 Nachos Combo</span></span>--%>
                    <%--                            </li>--%>
                    <%--                            <li>--%>
                    <%--                                <h6 class="subtitle"><span>food & bevarage</span></h6>--%>
                    <%--                            </li>--%>
                    <%--                        </ul>--%>
                    <ul>
                        <li>
                            <span class="info"><span>price</span><span>$${order.getTicket().getPrice()}</span></span>
                            <span class="info"><span>vat</span><span>$15</span></span>
                        </li>
                    </ul>
                </div>
                <div class="proceed-area  text-center">
                    <h6 class="subtitle"><span>Amount Payable</span><span>$${order.getFinalPrice()}</span></h6>
                    <a href="#0" class="custom-button back-button">proceed</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ==========Movie-Section========== -->

<!-- ==========Newslater-Section========== -->
<footer class="footer-section">
    <div class="newslater-section padding-bottom">
        <div class="container">
            <div class="newslater-container bg_img" data-background="assets/images/newslater/newslater-bg01.jpg">
                <div class="newslater-wrapper">
                    <h5 class="cate">subscribe to Boleto </h5>
                    <h3 class="title">to get exclusive benifits</h3>
                    <form class="newslater-form">
                        <input type="text" placeholder="Your Email Address">
                        <button type="submit">subscribe</button>
                    </form>
                    <p>We respect your privacy, so we never share your info</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="footer-top">
            <div class="logo">
                <a href="index-1">
                    <img src="assets/images/footer/footer-logo.png" alt="footer">
                </a>
            </div>
            <ul class="social-icons">
                <li>
                    <a href="#0">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                </li>
                <li>
                    <a href="#0" class="active">
                        <i class="fab fa-twitter"></i>
                    </a>
                </li>
                <li>
                    <a href="#0">
                        <i class="fab fa-pinterest-p"></i>
                    </a>
                </li>
                <li>
                    <a href="#0">
                        <i class="fab fa-google"></i>
                    </a>
                </li>
                <li>
                    <a href="#0">
                        <i class="fab fa-instagram"></i>
                    </a>
                </li>
            </ul>
        </div>
        <div class="footer-bottom">
            <div class="footer-bottom-area">
                <div class="left">
                    <p>Copyright © 2020.All Rights Reserved By <a href="#0">Boleto </a></p>
                </div>
                <ul class="links">
                    <li>
                        <a href="#0">About</a>
                    </li>
                    <li>
                        <a href="#0">Terms Of Use</a>
                    </li>
                    <li>
                        <a href="#0">Privacy Policy</a>
                    </li>
                    <li>
                        <a href="#0">FAQ</a>
                    </li>
                    <li>
                        <a href="#0">Feedback</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<!-- ==========Newslater-Section========== -->


<script src="assets/js/jquery-3.3.1.min.js"></script>
<script src="assets/js/modernizr-3.6.0.min.js"></script>
<script src="assets/js/plugins.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/isotope.pkgd.min.js"></script>
<script src="assets/js/magnific-popup.min.js"></script>
<script src="assets/js/owl.carousel.min.js"></script>
<script src="assets/js/wow.min.js"></script>
<script src="assets/js/countdown.min.js"></script>
<script src="assets/js/odometer.min.js"></script>
<script src="assets/js/viewport.jquery.js"></script>
<script src="assets/js/nice-select.js"></script>
<script src="assets/js/main.js"></script>
</body>


<!-- Mirrored from pixner.net/boleto/demo/movie-checkout by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 21 Mar 2024 12:43:37 GMT -->
</html>