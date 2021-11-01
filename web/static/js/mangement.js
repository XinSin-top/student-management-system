window.onload = function(){
    var islogin = getCookie("isLogin");
    if(islogin==="false"){
        alert("你还没登陆哦！");
        window.location.href = "/stu/index.html"
    }
    document.getElementById("helloHref").innerText = "欢迎您： " + getCookie("name");;

}