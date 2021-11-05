let html ="";
//页面加载完毕时操作
let nownum = 1;
let init = true;
let activeline = -1;
window.onload = function () {
    //获取全部人员
	var islogin = getCookie("isLogin");
	//登录验证
	var auth = getCookie("auth");
	if(islogin==="false"){//未登录
	    alert("你还没登陆哦！");
	    window.location.href = "/web/index.html";//返回登录页
	}else{
		//用户权限判断
		if(auth == "user"){//用户权限组为user
			alert("你所在的用户组无权访问该页面！");
			window.location.href = "/web/management.html";
		}else{
			document.getElementById("helloHref").innerText = "欢迎您： " + getCookie("name");
			$("paginationUlId").css("margin",0);
			getPer(1,10);
		}
	}

}

// 发送请求方法
let getPer = function (min,max){
    const url = baseserverurl + "/web/person?min=" + min + "&max=" + max;
    $.post(url,res,"json");
}
//对表格进行操作 拼接数据
let total = null;
//在列表视图中的任意一行被点击时
function onTableClick(line){
	//先将被点击的这行的active类激活（显示灰底）
	document.getElementById("line" + line + "id").classList.add("active");
	document.getElementById("line" + line + "name").classList.add("active");
	document.getElementById("line" + line + "account").classList.add("active");
	document.getElementById("line" + line + "password").classList.add("active");
	document.getElementById("line" + line + "lastLogin").classList.add("active");
	document.getElementById("line" + line + "auth").classList.add("active");
	//如果被点击过了（激活的行数为-1）
	if(activeline != -1){
		//移除之前被点击的行的active类
		document.getElementById("line" + activeline + "id").classList.remove("active");
		document.getElementById("line" + activeline + "name").classList.remove("active");
		document.getElementById("line" + activeline + "account").classList.remove("active");
		document.getElementById("line" + activeline + "password").classList.remove("active");
		document.getElementById("line" + activeline + "lastLogin").classList.remove("active");
		document.getElementById("line" + activeline + "auth").classList.remove("active");	
	}
	if(activeline === line){
		//如果点击了同一行（取消选择）
		activeline = -1;//设置激活的行为-1（也就是没有被点击过）
		//设置按钮为不可用
		document.getElementById("change").disabled = true;
		document.getElementById("remove").disabled = true;
	}else{
		//点击的是不同的行
		//设置按钮为可用
		document.getElementById("change").disabled = false;
		document.getElementById("remove").disabled = false;
		activeline = line;//将激活行数设置为被激活的行数
	}
}
let auth_add = "";
let auth_change = "";
function select_auth(which){
	document.getElementById("dropdownMenu1").innerText = which + "▼";
	auth_add = which;
}
function select_auth_change(which){
	document.getElementById("auth_change").innerText = which + "▼";
	auth_change = which;
}
let res =  function(data){
    //一次只展示十条数据
	html = ""
	total = data.total;
    for (let i = 0; i <data['data'].length; i++) {
		let thisuser = data['data'][i];
		if(thisuser.lastLogin === undefined){
			thisuser.lastLogin = "此用户还未曾登录过";
		}
		thisuser.i = i;
        html += "<tr><td id='line{i}id' onclick='onTableClick({i})'>{id}</td><td id='line{i}name' onclick='onTableClick({i})'>{name}</td><td id='line{i}account' onclick='onTableClick({i})'>{account}</td><td id='line{i}password' onclick='onTableClick({i})'>{password}</td><td id='line{i}lastLogin' onclick='onTableClick({i})'>{lastLogin}</td><td id='line{i}auth' onclick='onTableClick({i})'>{auth}</td></tr>".format(thisuser);
    }
    document.getElementById("tbody").innerHTML = html;
    document.getElementById("allPersonId").innerHTML = "全部人员: " + total + "人";
	if(init){
		setBgTable();
		addPag(total);
		goto(1);
		init = false;
	}
}
//更改表格背景
let setBgTable = function (){
    document.getElementById("tbody").style.backgroundColor = "white";
    document.getElementById("tbody").style.opacity = "0.85";
}
//上一页
function back_(){
	if(nownum - 1 <= 0){
		goto(1);
	}else{
		goto(nownum - 1);
	}
}
//下一页
function next(){
	if(nownum + 1 >= Math.ceil(total/10)){
		goto(Math.ceil(total/10));
	}else{
		goto(nownum + 1);
	}
} 
//跳转到某一页
function goto(num){
	document.getElementById("button"+nownum).classList.remove("active");
	nownum = num;
	let min = 10 * (num-1) + 1;
	let max = 10 * num;
	document.getElementById("button"+num).classList.add("active");
	activeline = -1;
	document.getElementById("change").disabled = true;
	document.getElementById("remove").disabled = true;
	if(!init){
		getPer(min,max);
	}
}
//增加分页按钮
let addPag = function (count){
    let liHtml = "<li>\n" +
        "                      <a href=\"#\" onclick='back_()' aria-label=\"Previous\">\n" +
        "                        <span aria-hidden=\"true\">&laquo;</span>\n" +
        "                      </a>\n" +
        "                    </li>";
    //用于计数的变量
    let lenth = 1;
    let htmlElement = document.getElementById("paginationUlId");
    for (let i = 0; i < count; i++) {
        if (i % 10 === 0){
            liHtml +=  "<li class=\"lis\" id='button{number}'><a href=\"#\" onclick='goto({number})'>".format({"number":lenth})+ lenth++ +"</a></li>";
        }
    }
    liHtml += "<li>\n" +
        "                      <a href=\"#\" aria-label=\"Next\" onclick='next()'>\n" +
        "                        <span aria-hidden=\"true\">&raquo;</span>\n" +
        "                      </a>\n" +
        "                    </li>";
    htmlElement.innerHTML = liHtml;
}
let ispop_add = false;
let ispop_change = false;
let beforeformhtml = null;
let beforeformhtml_change = null;
//当添加用户模态被第一次调用
function addModal(){
	ispop_add = false;
}
//添加用户数据部分当模态被关闭时
function closeModal(){
	if(ispop_add){
		document.getElementById("form_add").innerHTML = beforeformhtml;
	}
}
//修改用户数据部分当模态被关闭时
function closeModal_change(){
	if(ispop_change){
		document.getElementById("form_change").innerHTML = beforeformhtml;
	}
}
//修改用户数据部分当模态被调用时
function changeModal(){
	var name = document.getElementById("line" + activeline + "name").innerText;
	var account = document.getElementById("line" + activeline + "account").innerText;
	var password = document.getElementById("line" + activeline + "password").innerText;
	var auth = document.getElementById("line" + activeline + "auth").innerText;
	document.getElementById("name_change").value = name;
	document.getElementById("username_change").value = account;
	document.getElementById("password_change").value = password;
	select_auth_change(auth);
}
//添加用户数据部分主函数
function add(){
	let addhtml = '<div class="alert alert-danger alert-dismissible fade in" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>警告</strong> {noin}</div>';
	let noin = "";
	if(!ispop_add){
		beforeformhtml = document.getElementById("form_add").innerHTML;
		ispop_add = true;
	}
	let name_add = document.getElementById("name").value;
	let username_add = document.getElementById("username").value;
	let password_add = document.getElementById("password").value;
	let cpassword_add = document.getElementById("confirm_password").value;
	auth_add = document.getElementById("line" + activeline + "auth").innerText;
	let canadd = true;
	if(name_add === ""){
		noin += "姓名,";
		canadd = false;
	}
	if(username_add === ""){
		noin += "用户名,";
		canadd = false;
	}
	if(password_add === ""){
		noin += "密码,";
		canadd = false;
	}
	if(cpassword_add != password_add){
		noin += "确认密码,";
		canadd = false;
	}
	if(auth_add === ""){
		noin += "用户权限组,";
		canadd = false;
	}
	if(!canadd){
		noin += "未填写或有误！"
		addhtml = addhtml.format({"noin":noin});
		document.getElementById("form_add").innerHTML = addhtml + beforeformhtml;
	}else{
		let url = baseserverurl + "/web/crud";
		$.post(url,JSON.stringify({"operationType":1,"sa_name":name_add,"sa_account":username_add,"sa_password":password_add,"sa_authority":auth_add}),add_callback,"json");
	}
}
//添加用户数据部分回调函数
function add_callback(data){
	$('#addModal').modal('hide');//关闭模态
	console.log(data);
}
//删除用户数据部分模态框被激活时函数
function removeclose(){
	document.getElementById("alertwaring").innerText = "警告：你正在进行高危操作（删除用户）如果你确定要删除，请在下方的输入框内输入要删除用户的姓名！";
}
//删除用户数据部分回调函数
function remove_callback(data){
	$('#removeModal').modal('hide');//关闭模态
	console.log(data);
}
//删除用户数据部分主函数
function remove(){
	let id = document.getElementById("line" + activeline + "id").innerText;
	let name = document.getElementById("line" + activeline + "name").innerText;
	if(document.getElementById("name_remove").value != name){
		document.getElementById("alertwaring").innerText = "\t警告：你正在进行高危操作（删除用户）如果你确定要删除，请在下方的输入框内输入要删除用户的姓名！\n 姓名错误！";
	}else{
		let url = baseserverurl + "/web/crud";
		$.post(url,JSON.stringify({"operationType":2,"sa_id":id}),remove_callback,"json");
	}
}
//更改用户数据部分回调
function change_callback(data){
	$('#changeModal').modal('hide');//关闭模态
	console.log(data);
}
//更改用户数据部分主函数
function change(){
	let addhtml = '<div class="alert alert-danger alert-dismissible fade in" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>警告</strong> {noin}</div>';
	let noin = "";
	if(!ispop_change){
		beforeformhtml_change = document.getElementById("form_change").innerHTML;
		ispop_change = true;
	}
	let name_add = document.getElementById("name_change").value;
	let username_add = document.getElementById("username_change").value;
	let password_add = document.getElementById("password_change").value;
	let id = document.getElementById("line" + activeline + "id").innerText;
	let canadd = true;
	if(name_add === ""){
		noin += "姓名,";
		canadd = false;
	}
	if(username_add === ""){
		noin += "用户名,";
		canadd = false;
	}
	if(password_add === ""){
		noin += "密码,";
		canadd = false;
	}
	if(auth_change === ""){
		noin += "用户权限组,";
		canadd = false;
	}
	if(!canadd){
		noin += "未填写！"
		addhtml = addhtml.format({"noin":noin});
		document.getElementById("form_change").innerHTML = addhtml + beforeformhtml_change;
	}else{
		let url = baseserverurl + "/web/crud";
		$.post(url,JSON.stringify({"operationType":3,"sa_id":id,"sa_name":name_add,"sa_account":username_add,"sa_password":password_add,"sa_authority":auth_change}),change_callback,"json");
	}
}