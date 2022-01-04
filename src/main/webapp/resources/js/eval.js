$(document).ready(function(){
 	$('#evalRegister').click(function(){	
			var title = $("#title").val();
			var writer=$('#writer').val();
			var hosp_name=$('#hosp_name').val();
			var location=$('#location').val();
			var content=$('#content').val();
			var hpid= $("#hpid").val();
			var flag = false;
			//alert(time);
			$.ajax({
		         type:"post",
		         url:"./eval_register",
		         data :{"title":title,"writer":writer,"hosp_name":hosp_name,"location":location,"content":content,"hpid":hpid},
		         async: false,
			     success:function (data,textStatus){
					if(eval(data)){
						flag= true;
						alert("등록하였습니다.");
						window.location.assign("./hospital_detail?hpid="+hpid);
					}
				},
				 error:function(data,textStatus){
				    alert("에러가 발생했습니다.");
				},
				complete:function(data,textStatus){
					 
				}
		});
		return flag;
	});
	
	$('#evalRegister2').click(function(){	
			var title = $("#title").val();
			var writer=$('#writer').val();
			var hosp_name=$('#hosp_name').val();
			var location=$('#location').val();
			var content=$('#content').val();
			var hpid= $("#hpid").val();
			var flag = false;
			//alert(time);
			$.ajax({
		         type:"post",
		         url:"./eval_register",
		         data :{"title":title,"writer":writer,"hosp_name":hosp_name,"location":location,"content":content,"hpid":hpid},
		         async: false,
			     success:function (data,textStatus){
					if(eval(data)){
						flag= true;
						alert("등록하였습니다.");
						window.location.assign("./pharmacy_detail?hpid="+hpid);
					}
				},
				 error:function(data,textStatus){
				    alert("에러가 발생했습니다.");
				},
				complete:function(data,textStatus){
					 
				}
		});
		return flag;
	});
});