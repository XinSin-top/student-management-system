// 定义基本变量
var capt = null;
var user = null;
var name = null;
var id = null;
var auth = null;
var islogin = false;
// 解析base64图片
var gettingcaptcha = function(data){
    var a = "data:image/jpg;base64,";
    const b64data = data.split(",")[0];
    //分离取出
    capt = data.split(",")[1];
    a += b64data;
    document.getElementById("captcha_img").src = a;
}
//ajax get验证码
var getcaptcha = function(){
    var url = "/web/vc";
    $.get(url,gettingcaptcha);
}
//加载
window.onload = function(){
    //在页面加载完成后获取验证码图片
    getcaptcha();
    //设置需要的cookies为空值
    setCookie("name",name);
    setCookie("auth",auth);
    setCookie("id",id);
    setCookie("user",user);
    setCookie("isLogin",islogin);
}
//验证是否为空
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

// 验证登录是否成功
var onLogin = function(data,status){
    console.log("starting validation...");
    if(status != "success"){
        console.log("statuscode:" + status)
    }else{
        // var data = JSON.parse(data);
        if(data["isLogin"]){
            //密码验证正确json {"isLogin":true,"lastLoginTime":"2021-11-01 15:28:57","auth":"user","name":"李欣","id":18}
            //密码验证错误的json {"isLogin":false,"id":0}
            islogin = true;
            name = data["name"];
            auth = data["auth"];
            id = data["id"];
            setCookie("name",name);
            setCookie("auth",auth);
            setCookie("user",user);
            setCookie("isLogin",islogin);
            window.location.href = "/web/management.html"
        }else{
            document.getElementById("errorUsername").innerText = "账号或密码错误,请重试";
            document.getElementById("errorCaptcha").innerText = "";
        }
        console.log(data)
    }
}
// 发送登录请求
var login = function(){
    const UsernameElement = document.getElementById("username");
    const PasswordElement = document.getElementById("password");
    const CaptchaElement = document.getElementById("captcha");
    const Captcha = CaptchaElement.value;
    const Username = UsernameElement.value;
    user = Username;
    const Password = PasswordElement.value;
    const url = baseserverurl + "/web/login";
    if(Captcha === capt){
        console.log("captcha done....");
        $.post(url,JSON.stringify({"username":Username,"password":Password}),onLogin,"json"); //这里设置返回类型为json 直接返回json对象
        document.getElementById("errorCaptcha").style.color = "black";
        document.getElementById("errorCaptcha").innerText = "验证码正确,正在验证";
    }else{
        document.getElementById("captcha").value = "";
        document.getElementById("errorCaptcha").style.color = "red";
        document.getElementById("errorCaptcha").innerText = "验证码错误,请重新输入";
        console.log("Error captcha");
        getcaptcha();
    }
}
