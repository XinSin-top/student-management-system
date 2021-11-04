var html = null;
window.onload = function () {
    getPer();
}
var getPer = function (){
    const url = "/stu/person";
    $.get(url,null,res,"json");
}
var res =  function(data){
    // console.log(data["id"]);
    // data = JSON.parse(data);
    for (var i = 0; i < 10; i++) {
        html +=
            "<tr>" +
            "<td>" + data["id"] + "</td>" +
            "<td>" + data["name"] + "</td>" +
            "<td>" + data["account"] + "</td>" +
            "<td>" + data["password"] + "</td>" +
            "<td>" + data["lastLogin"] + "</td>" +
            "<td>" + data["auth"] + "</td>" +
            "</tr>";
    }
    document.querySelector(".tbody").innerHTML = html;
}