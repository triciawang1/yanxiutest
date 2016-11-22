module.exports = {

    summary:function(){
        return "replace response data by local response now";
    },

    //mark if use local response
    shouldUseLocalResponse : function(req,reqBody){
     
				        if(/examine/.test(req.url)){
            req.replaceLocalFile = 3;
            return true;
        }

		 if(/resource/.test(req.url)){
            req.replaceLocalFile = 4;
            return true;
        }

            return false;
    },

    dealLocalResponse : function(req,reqBody,callback){
        if(req.replaceLocalFile==0){
            request = require('request-json');
            var client = request.createClient('http://127.0.0.1');
              client.get('/briefList', function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
        }


		        if(req.replaceLocalFile==1){
            request = require('request-json');
            var client = request.createClient('http://127.0.0.1');
              client.get('/login.json', function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
        }



		        if(req.replaceLocalFile==2){
            request = require('request-json');
            var client = request.createClient('http://127.0.0.1');
              client.get('/getEditUserInfo', function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
        }


				        if(req.replaceLocalFile==3){
            request = require('request-json');
            var client = request.createClient('http://127.0.0.1:8789');
              client.get('/examine', function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
        }


		
				        if(req.replaceLocalFile==4){
            request = require('request-json');
            var client = request.createClient('http://127.0.0.1');
              client.get('/resource', function(err, res, body)  //触发mock，到moco中获取mock响应数据
                {
                    console.log('the resp is ------------------------->',res.statusCode,res.headers,body);
                    var newDataStr=JSON.stringify(body);
                    callback(res.statusCode,res.headers,newDataStr); //mock数据返回给客户端
                }
            );
        }
    }
};