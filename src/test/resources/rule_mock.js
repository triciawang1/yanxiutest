
var urls = new Array("login.json","getEditUserInfo","trainlist","examine","noticeList","briefList","taskList","homeworkList","myCourseList","checkedMobileUser","courselist","actives","condition")

module.exports = {

    summary:function(){
        return "replace response data by local response now";
    },
    
    //mark if use local response
    shouldUseLocalResponse : function(req,reqBody){
//var uris=new Array(/initialize/,/login.json/,/getEditUserInfo/,/trainlist/,/examine/,/noticeList/,/briefList/,/taskList/);
//var uris=new Array(/login.json/,/getEditUserInfo/,/trainlist/,/examine/,/noticeList/,/briefList/,/taskList/,/homeworkList/,/myCourseList/,/checkedMobileUser/,/courselist/);

for(var i = 0, l = urls.length; i < l; i++) 
		{
			var urlPattern = new RegExp(urls[i]);
 if(urlPattern.test(req.url)){
	 console.log("use local response for "+urls[i])
         req.replaceLocalFile = i;
            return true;
        }
}

//    if(/actives/.test(req.url)){
	//	console.log("use logcal data for actives/");
		//req.replaceLocalFile = true;
		//return true;
	//}

            return false;
    },

    dealLocalResponse : function(req,reqBody,callback){
		request = require('request-json');
        var client = request.createClient('http://127.0.0.1:8789');
		
//var uris=new Array("/initialize","/login.json","/getEditUserInfo","/trainlist","/examine","/noticeList","/briefList","/taskList");
//var uris=new Array("/login.json","/getEditUserInfo","/trainlist","/examine","/noticeList","/briefList","/taskList","/homeworkList","/myCourseList","/checkedMobileUser","courselist");

        client.get(urls[req.replaceLocalFile], function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                   console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
			//console.log(fs.readFileSync(LOCAL_JSON).toString());
//if(req.replaceLocalFile){
	//callback(200,{"content-type":"application/json;charset=UTF-8"},fs.readFileSync(LOCAL_JSON).toString())
//}

    }
};