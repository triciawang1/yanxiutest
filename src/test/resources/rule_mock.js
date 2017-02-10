module.exports = {

    summary:function(){
        return "replace response data by local response now";
    },
    
    //mark if use local response
    shouldUseLocalResponse : function(req,reqBody){
//var uris=new Array(/initialize/,/login.json/,/getEditUserInfo/,/trainlist/,/examine/,/noticeList/,/briefList/,/taskList/);
var uris=new Array(/login.json/,/getEditUserInfo/,/trainlist/,/examine/,/noticeList/,/briefList/,/taskList/,/homeworkList/,/myCourseList/,/checkedMobileUser/,/courselist/);
for(var i = 0, l = uris.length; i < l; i++) 
		{
 if(uris[i].test(req.url)){
	 console.log(uris[i])
            req.replaceLocalFile = i;
            return true;
        }
}

    

            return false;
    },

    dealLocalResponse : function(req,reqBody,callback){
		request = require('request-json');
        var client = request.createClient('http://127.0.0.1:8789');
		
//var uris=new Array("/initialize","/login.json","/getEditUserInfo","/trainlist","/examine","/noticeList","/briefList","/taskList");
var uris=new Array("/login.json","/getEditUserInfo","/trainlist","/examine","/noticeList","/briefList","/taskList","/homeworkList","/myCourseList","/checkedMobileUser","/courselist");

        client.get(uris[req.replaceLocalFile], function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );


    }
};