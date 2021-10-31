var capt = null;
var user = null;
var name = null;
var id = null;
var auth = null;
var islogin = false;
var gettingcaptcha = function(data,status){
    var a = "data:image/jpg;base64,";
    var b64data = data.split(",")[0];
    capt = data.split(",")[1];
    a += b64data;
    document.getElementById("captcha_img").src = a;
}
var getcaptcha = function(){
    var url = "/stu/vc";
    $.get(url,gettingcaptcha);
}
window.onload = function(){
    getcaptcha();
    setCookie("name",name);
    setCookie("auth",auth);
    setCookie("id",id);
    setCookie("user",user);
    setCookie("isLogin",islogin)
}
var errorUsername = function(){
    if(document.getElementById("username").value === ""){
        document.getElementById("errorUsername").innerText = "用户名不能为空！";
    }else{
        document.getElementById("errorUsername").innerText = "";
    }
}
var errorPassword = function(){
    if(document.getElementById("password").value === ""){
        document.getElementById("errorPassword").innerText = "密码不能为空！";
    }else{
        document.getElementById("errorPassword").innerText = "";
    }
}
var errorCaptcha = function(){
    if(document.getElementById("captcha").value === ""){
        document.getElementById("errorCaptcha").innerText = "验证码不能为空！";
    }else{
        document.getElementById("errorCaptcha").innerText = "";
    }
}

var onLogin = function(data,status){
    console.log("starting validation...");
    if(status != "success"){
        console.log("statuscode:" + status)
        alert("登录失败，请求到服务器错误，错误代码：" + status)
    }else{
        var data = JSON.parse(data);
        console.log(data);
        if(data["can"]){
            islogin = true;
            name = data["sa_name"];
            auth = data["sa_authority"];
            id = data["sa_id"];
            setCookie("name",name);
            setCookie("auth",auth);
            setCookie("id",id);
            setCookie("user",user);
            setCookie("isLogin",islogin);
            window.location.href = "/stu/management.html"
        }
    }
}
var login = function(){
    var UsernameElement = document.getElementById("username");
    var PasswordElement = document.getElementById("password");
    var CaptchaElement = document.getElementById("captcha");
    var Captcha = CaptchaElement.value;
    var Username = UsernameElement.value;
    user = Username;
    var Password = PasswordElement.value;
    var url = "/stu/login";
    if(Captcha === capt){
        console.log("captcha done....");
        $.post(url,JSON.stringify({"username":Username,"password":Password}),onLogin);
    }else{
        alert("验证码错误！");
        console.log("Error captcha");
        getcaptcha();
    }
}
