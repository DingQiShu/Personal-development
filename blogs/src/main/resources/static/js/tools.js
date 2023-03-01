// 获取当前 url 中某个参数的方法
function getURLParam(key){
    var params = location.search;
    if(params.indexOf("?")>=0){
        params = params.substring(1);
        var paramArr = params.split('&');
        for(var i=0;i<paramArr.length;i++){
            var namevalues = paramArr[i].split("=");
            if(namevalues[0]==key){
                return namevalues[1];
            }
        }
    }else{
        return "";
    }
}
// 检查当前用户是否登录
function checkLogin() {
    jQuery.ajax({
        url:"/user/get_user",
        type:"get",
        success: function(result) {
            
        },
        error: function(err) {
            if (err.status == 401) {
                alert("当前用户未登录，你即将跳转到登录页面");
                location.href = "login.html";
            }
        }
    })
}