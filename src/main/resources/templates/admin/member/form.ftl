<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>会员信息</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完整验证表单</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/user/edit">
                        	<input type="hidden" id="id" name="id" value="${user.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">会员ID：</label>
                                <div class="col-sm-8">
                                    <input id="id" name="id" class="form-control" type="text" value="${member.id}" <#if user?exists> readonly="readonly"</#if> >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" class="form-control" type="text" value="${member.phone}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">真实姓名：</label>
                                <div class="col-sm-8">
                                    <input id="realName" name="realName" class="form-control" type="text" value="${member.realName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">证件类型：</label>
                                <div class="col-sm-8">
                                	<select name="indentityType" class="form-control">
                                		<option value="01" <#if member.indentityType == "01">selected="selected"</#if>>身份证</option>
                                		<option value="02" <#if member.indentityType == "02">selected="selected"</#if>>因私护照</option>
                                		<option value="03" <#if member.indentityType == "03">selected="selected"</#if>>因公护照</option>
                                		<option value="04" <#if member.indentityType == "04">selected="selected"</#if>>香港永久性居民身份证</option>
                                		<option value="05" <#if member.indentityType == "05">selected="selected"</#if>>澳门永久性居民身份证</option>
                                		<option value="06" <#if member.indentityType == "06">selected="selected"</#if>>港澳居民来往内地通行证</option>
                                		<option value="07" <#if member.indentityType == "07">selected="selected"</#if>>台湾居民来往大陆通行证</option>
                                		<option value="08" <#if member.indentityType == "08">selected="selected"</#if>>外国人永久居住证</option>
                                		<option value="09" <#if member.indentityType == "09">selected="selected"</#if>>其他证件</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">证件号：</label>
                                <div class="col-sm-8">
                                    <input id="indentityNo" name="indentityNo" class="form-control" type="text" value="${member.indentityNo}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
                                	<select name="sex" class="form-control">
                                		<option value = 1 <#if member.sex == 1>selected="selected"</#if>>男</option>
                                		<option value = 2 <#if member.sex == 2>selected="selected"</#if>>女</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址：</label>
                                <div class="col-sm-8">
                                    <input id="address" name="address" class="form-control" type="text" value="${member.address}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户状态：</label>
                                <div class="col-sm-8">
                                	<select name="userStatus" class="form-control">
                                		<option value = "00" <#if member.userStatus == "00">selected="selected"</#if>>正常</option>
                                		<option value = "01" <#if member.userStatus == "01">selected="selected"</#if>>冻结</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电子邮件：</label>
                                <div class="col-sm-8">
                                    <input id="email" name="email" class="form-control" value="${member.email}">
                                </div>
                            </div>
                            <!--<div class="form-group">
                                <label class="col-sm-3 control-label">用户状态：</label>
                                <div class="col-sm-8">
                                	<select name="appPlatform" class="form-control">
                                		<option value = "A" <#if member.appPlatform == "A">selected="selected"</#if>>融侨宝</option>
                                		<option value = "B" <#if member.appPlatform == "B">selected="selected"</#if>>金管家</option>
                                		<option value = "C" <#if member.appPlatform == "C">selected="selected"</#if>>融侨普惠</option>
                                	</select>
                                </div>
                            </div>-->
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">理财等级：</label>
                                <div class="col-sm-8">
                                    <input id="financialLevel" name="financialLevel" class="form-control" onkeyup="value=value.replace(/\D|^0/g,'')" placeholder="请输入数字0-5" value="${member.financialLevel}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">注册日期：</label>
                                <div class="col-sm-8">
                                    <input id="registerDate" name="registerDate" class="form-control" value="${member.registerDate}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">工作人员编号：</label>
                                <div class="col-sm-8">
                                	<select name="staffNo" class="form-control">
                                		<option value="G" <#if user.staffNo == 0>selected="selected"</#if>>G</option>
                                		<option value="H" <#if user.staffNo == 1>selected="selected"</#if>>H</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
	  	//外部js调用
	    laydate({
	        elem: '#birthday', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	    });
	  	
	    $("#frm").validate({
    	    rules: {
    	    	userName: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	nickName: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	sex: {
    	        required: true
    	      },
    	      	birthday: {
    	      	date:true,
    	        required: true
    	      },
    	      	telephone: {
    	        required: true
    	      },
    	      	email: {
    	      	email:true,
    	        required: true
    	      },
    	      	address: {
    	        required: true,
    	        maxlength: 40
    	      },
    	      	locked: {
    	        required: true
    	      },
    	      	description: {
    	        required: true,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/user/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    </script>

</body>

</html>
