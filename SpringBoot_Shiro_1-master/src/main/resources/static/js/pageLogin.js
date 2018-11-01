//用于验证超时后的再次登陆页面是否正常
    var pathName =  window.document.location.pathname;
    var loginPath = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
if(self==top) {
    window.location = loginPath + "/login";
}