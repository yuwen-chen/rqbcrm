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
                    	<input id="isEdit" name="isEdit" class="form-control" type="hidden" value="${isEdit}">
                    	<#if isEdit>
                    		<form class="form-horizontal m-t" id="editform" method="post" action="${ctx!}/admin/member/edit">
	                        	<div class="form-group">
		   							<label class="col-sm-3 control-label">app平台：</label>
	                                <div class="col-sm-8">
                                		<#list appPlatformList! as appPlatform> 
	                        				<#if appPlatform.type == member.appPlatform>
	                        					<input id="appPlatformDesc" name="appPlatformDesc" class="form-control" type="text" value="${appPlatform.appName!}" <#if member?exists> readonly="readonly"</#if> >
	                        					<input id="appPlatform" name="appPlatform" class="form-control" type="hidden" value="${member.appPlatform!}" <#if member?exists> readonly="readonly"</#if> >
	                        					<input id="memberTable" name="memberTable" class="form-control" type="hidden" value="${member.appPlatform}" <#if member?exists> readonly="readonly"</#if> >
	                        				</#if>
										</#list>
                                	</div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">会员ID：</label>
	                                <div class="col-sm-8">
	                                    <input id="id" name="id" class="form-control" type="text" value="${member.id}" <#if member?exists> readonly="readonly"</#if> >
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">手机号：</label>
	                                <div class="col-sm-8">
	                                    <input id="phone" name="phone" class="form-control" type="text" value="${member.phone}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">真实姓名：</label>
	                                <div class="col-sm-8">
	                                    <input id="realName" name="realName" class="form-control" type="text" value="${member.realName}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">证件类型：</label>
	                                <div class="col-sm-8">
										<select name="identityType" class="form-control" <#if member?exists> disabled="disabled"</#if>>
	                                		<#list identityTypeList as identityType> 
		                        				<option value="${identityType.code}" <#if member.identityType == identityType.code>selected="selected"</#if>>${identityType.value}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">证件号：</label>
	                                <div class="col-sm-8">
	                                    <input id="identityNo" name="identityNo" class="form-control" type="text" value="${member.identityNo}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">性别：</label>
	                                <div class="col-sm-8">
	                                	<select name="sex" class="form-control" <#if member?exists> disabled="disabled"</#if>>
		                                	<#list sexList as sex> 
		                        				<option value = ${sex.code} <#if member.sex == sex.code>selected="selected"</#if>>${sex.value!}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">地址：</label>
	                                <div class="col-sm-8">
	                                    <input id="address" name="address" class="form-control" type="text" value="${member.address}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">用户状态：</label>
	                                <div class="col-sm-8">
	                                	<select name="userStatus" class="form-control" <#if member?exists> disabled="disabled"</#if>>
	                                		<#list statusList as status> 
		                        				<option value = ${status.code} <#if member.userStatus == status.code>selected="selected"</#if>>${status.value!}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">电子邮件：</label>
	                                <div class="col-sm-8">
	                                    <input id="email" name="email" class="form-control" value="${member.email}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">理财等级：</label>
	                                <div class="col-sm-8">
	                                    <input id="financialLevel" name="financialLevel" class="form-control" onkeyup="value=value.replace(/\D|^0/g,'')" placeholder="请输入数字0-5" value="${member.financialLevel}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">注册日期：</label>
	                                <div class="col-sm-8">
	                                    <input id="registerDate" name="registerDate" class="laydate-icon form-control layer-date" value="${member.registerDate}" <#if member?exists> readonly="readonly"</#if>>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">工作人员编号：</label>
	                                <div class="col-sm-8">
	                                	<!--<input id="staffNo" name="staffNo" class="form-control" value="${member.staffNo}" readonly="readonly">-->
	                                	<select name="staffNo" class="form-control">
	                                		<!--<#assign c= ["G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]> 
											<#list c as i> 
												<option value="${i}" <#if member.staffNo == "${i}">selected="selected"</#if>>${i}</option>
											</#list>-->
											<#list staffList as staff> 
												<option value="${staff.staffNo}" <#if member.staffNo == "${staff.staffNo}">selected="selected"</#if>>${staff.staffNo}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <div class="col-sm-8 col-sm-offset-3">
	                                    <button class="btn btn-primary" type="submit">提交</button>
	                                </div>
	                            </div>
	                        </form>
                    		<#else>
	                        <form class="form-horizontal m-t" id="addform" method="post" action="${ctx!}/admin/member/add">
	                        	<input id="appPlatform" name="appPlatform" class="form-control" type="hidden" value="">
	                        	<div class="form-group">
		                        	<#if appPlatform ??>
		                        		<input type="hidden" id="memberTable" name="memberTable"">
			   							<#else>
			   							<label class="col-sm-3 control-label">app平台：</label>
		                                <div class="col-sm-8">
		                                	<select id= "memberTable" name="memberTable" class="form-control">
		                                		<#list appPlatformList as appPlatform> 
			                        				<option value="${appPlatform.type}">${appPlatform.appName}</option>
												</#list>
		                                	</select>
	                                	</div>
		                        	</#if>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">会员ID：</label>
	                                <div class="col-sm-8">
	                                    <input id="id" name="id" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">手机号：</label>
	                                <div class="col-sm-8">
	                                    <input id="phone" name="phone" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">真实姓名：</label>
	                                <div class="col-sm-8">
	                                    <input id="realName" name="realName" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">证件类型：</label>
	                                <div class="col-sm-8">
	                                	<select name="identityType" class="form-control">
	                                		<#list identityTypeList as identityType> 
		                        				<option value="${identityType.code}">${identityType.value}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">证件号：</label>
	                                <div class="col-sm-8">
	                                    <input id="identityNo" name="identityNo" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">性别：</label>
	                                <div class="col-sm-8">
	                                	<select name="sex" class="form-control">
	                                		<#list sexList as sex> 
		                        				<option value = ${sex.code}>${sex.value!}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">地址：</label>
	                                <div class="col-sm-8">
	                                    <input id="address" name="address" class="form-control" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">用户状态：</label>
	                                <div class="col-sm-8">
	                                	<select name="userStatus" class="form-control">
	                                		<#list statusList as status> 
		                        				<option value = ${status.code}>${status.value!}</option>
											</#list>
	                                	</select>
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">电子邮件：</label>
	                                <div class="col-sm-8">
	                                    <input id="email" name="email" class="form-control">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">理财等级：</label>
	                                <div class="col-sm-8">
	                                    <input id="financialLevel" name="financialLevel" class="form-control" onkeyup="value=value.replace(/\D|^0/g,'')" placeholder="请输入数字0-5">
	                                </div>
	                            </div>
	
	                            <div class="form-group">
	                                <label class="col-sm-3 control-label">注册日期：</label>
	                                <div class="col-sm-8">
	                                    <input id="registerDate" name="registerDate" class="laydate-icon form-control layer-date">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <div class="col-sm-8 col-sm-offset-3">
	                                    <button class="btn btn-primary" type="submit">提交</button>
	                                </div>
	                            </div>
	                        </form>
                    	</#if>
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
    	var isEdit = $("#isEdit").val();
	    if(isEdit == ""){
	    	$("#memberTable").val(parent.$("#appType").val());
		    $("#appPlatform").val(parent.$("#appType").val());
		    $("#memberTable").change(function(){
				$("#appPlatform").val($(this).val());
			});
	    }
	    
	  	/**
		 * 调用日期控件
		 * 
		 */
	  	//laydate.skin('black');  
	    laydate({
	        elem: '#registerDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus', //响应事件。如果没有传入event，则按照默认的click
	        type: 'month',
	        //isdate: true, //是否开启时间选择
	        isclear: true, //是否显示清空
	        istoday: true, //是否显示今天
	        issure: true, //是否显示确认
	        festival: true, //是否显示节日
	        format: 'YYYYMM', //日期格式
	        min: '1900-01-01 00:00:00', //最小日期
	        max: '2099-12-31 23:59:59', //最大日期
	        //start: '2014-6-15 23:00:00',    //开始日期
	        fixed: false, //是否固定在可视区域
	        zIndex: 99999999, //css z-index
	        choose: function(datas){ //选择日期完毕的回调  
		        $("#registerDate").val(datas.slice(2));  
		    }
	    });
	    
	    /**
		 * 新增会员
		 * 
		 */
	    $("#addform").validate({
    	    rules: validateForm(),
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/member/add",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.$("#appType").val($("#memberTable").val());
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    	
    	/**
		 * 修改会员
		 * 
		 */
    	$("#editform").validate({
    	    rules: validateForm(),
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/member/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.$("#appType").val($("#memberTable").val());
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    	
    	/**
		 * 表单校验
		 * 
		 */
    	function validateForm(){
    		return {
    			memberTable: {
    	        required: true,
    	      },
    			id: {
    	        required: true,
    	      },
    	    	phone: {
    	        required: true,
    	        minlength: 11,
    	    	maxlength: 11
    	      },
    	      	realName: {
    	        required: true,
    	      },
    	      	identityType: {
    	        required: true
    	      },
    	      	identityNo: {
    	        required: true
    	      },
    	      	sex: {
    	        required: true
    	      },
    	      	address: {
    	        required: true,
    	        maxlength: 300
    	      },
    	      	userStatus: {
    	        required: true
    	      },
    	      	email: {
    	      	email:true,
    	        required: true
    	      },
    	      	financialLevel: {
    	        required: true
    	      },
    	      	registerDate: {
    	        required: true,
    	        maxlength: 4
    	      }
    	    }
    	}
    	
    });
    </script>

</body>

</html>
