<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<!DOCTYPE html>
    <!--[if IE 9 ]><html class="ie9" data-ng-app="materialAdmin" data-ng-controller="materialadminCtrl as mactrl"><![endif]-->
    <![if IE 9 ]><html data-ng-app="materialAdmin" data-ng-controller="materialadminCtrl as mactrl"><![endif]>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Material Admin</title>

        <!-- Vendor CSS -->
        <link href="<%=request.getContextPath()%>/resources/vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-loading-bar/src/loading-bar.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="<%=request.getContextPath()%>/resources/css/app.min.1.css" rel="stylesheet" id="app-level">
        <link href="<%=request.getContextPath()%>/resources/css/app.min.2.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resources/css/demo.css" rel="stylesheet">

    </head>

    <body data-ng-class="{ 'sw-toggled': mactrl.layoutType === '1'}">

        <data ui-view></data>

        <!-- Older IE warning message -->
        <!--[if lt IE 9]>
            <div class="ie-warning">
                <h1 class="c-white">Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
                <div class="iew-container">
                    <ul class="iew-download">
                        <li>
                            <a href="<%=request.getContextPath()%>/resources/http://www.google.com/chrome/">
                                <img src="<%=request.getContextPath()%>/resources/img/browsers/chrome.png" alt="">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/resources/https://www.mozilla.org/en-US/firefox/new/">
                                <img src="<%=request.getContextPath()%>/resources/img/browsers/firefox.png" alt="">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/resources/http://www.opera.com">
                                <img src="<%=request.getContextPath()%>/resources/img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/resources/https://www.apple.com/safari/">
                                <img src="<%=request.getContextPath()%>/resources/img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/resources/http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="<%=request.getContextPath()%>/resources/img/browsers/ie.png" alt="">
                                <div>IE (New)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>
        <![endif]-->


        <!-- Core -->
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Angular -->
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular/angular.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-animate/angular-animate.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-resource/angular-resource.min.js"></script>
        
        <!-- Angular Modules -->
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-loading-bar/src/loading-bar.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>

        <!-- Common Vendors -->
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/Waves/dist/waves.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/ng-table/dist/ng-table.min.js"></script>
       

        <!-- Placeholder for IE9 -->
        <!--[if IE 9 ]>
            <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->

        <!-- Using below vendors in order to avoid misloading on resolve -->
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot/jquery.flot.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot/jquery.flot.resize.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/moment/min/moment.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot-orderBars/js/jquery.flot.orderBars.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot/jquery.flot.pie.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendors/bower_components/angular-nouislider/src/nouislider.min.js"></script>
        
        
        <!-- App level -->
        <script src="<%=request.getContextPath()%>/resources/js/app.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/config.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/controllers/main.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/services.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/templates.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/controllers/ui-bootstrap.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/controllers/table.js"></script>


        <!-- Template Modules -->
        <script src="<%=request.getContextPath()%>/resources/js/modules/template.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/ui.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/charts/flot.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/charts/other-charts.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/form.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/media.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/components.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/calendar.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/modules/demo.js"></script>
    </body>
</html>

