var dom = null;
var circle = null;
var option = {
	color: ['#83d5af','#ffffff'],
	series: [
		{
			type: 'pie',
			data: [
				{
					value: 0,
					name: '空闲内存',
				},
				{
					value: 0,
					name: '已用内存',
				}
			]
		}
	]
};
window.onload = function(){
    var islogin = getCookie("isLogin");
    var auth = getCookie("auth");
    if(islogin==="false"){
        alert("你还没登陆哦！");
        window.location.href = "/web/index.html"
    }else{
		if(auth == "user"){
			document.getElementById("userman").style = "display : none";
			document.getElementById("dormman").style = "display : none";
			//document.getElementById("userman").href="/web/me.html";
		}
		document.getElementById("helloHref").innerText = "欢迎您： " + getCookie("name");
		dom = document.getElementById("operationWindowOnTheRight");
		circle = echarts.init(dom);
		circle.setOption(option)
	}
}
function getData(data){
	option.series[0].data[0].value = data.JVMfree;
	//option.series[0].data[0].name = "空闲内存" + data.JVMfree / (1024 * 1024) + "MB";
	option.series[0].data[1].value = data.JVMtotal;
	//option.series[0].data[1].name = "已用内存" + data.JVMtotal / (1024 * 1024) + "MB";
	circle.setOption(option)
}
function getDataTime(){
	url = baseserverurl + "/web/ram"
	$.get(url,getData,"json")
}
function unlogin(){
	setCookie("name",null);
	setCookie("auth",null);
	setCookie("id",null);
	setCookie("user",null);
	setCookie("isLogin",null);
	window.location.href = "/web/index.html";
}
window.setInterval("getDataTime()",1000);