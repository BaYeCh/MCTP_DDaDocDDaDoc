
 $(document).ready(function(){
    $('#findId').click(function(){
        var name = $('#name').val();
        var email = $('#email').val();
        var birth = $('#birth').val();
        var flag = false;
        if (name == "" || email == "")
        {
            alert('Please check your inputs');
        } else{       
	        $.ajax({
				data :  {"name" : name, "email" : email, "birth" : birth },
				type : "post",
				dataType: "text",
				async: false,
				url : './findId',     						
				success : function(data, textStatus){
		            if (data){
						flag = true;
		                window.location.assign('./result');  
		            } else{
		                alert("정보가 일치하지 않습니다.")
		            }
				},
				error : function(data, textStatus){
				},
				complete : function(data,textStatus){
				}
			});
		}
		return flag;
	})
	
	$('#findPwd').click(function(){
        var id = $('#id').val();
        var email = $('#email').val();
        var birth = $('#birth').val();
        var flag = false;

        if (id == "" || email == "" || birth == "")
        {
            alert('Please check your inputs');
        } else{       
	        $.ajax({
				data :  {"id" : id, "email" : email, "birth" : birth},
				type : "post",
				dataType: "text",
				async: false,
				url : './findPwd',     						
				success : function(data, textStatus){
		            if (data){
						flag = true;
		                window.location.assign('./result');  
		            } else{
		                alert("정보가 일치하지 않습니다.")
		            }
				},
				error : function(data, textStatus){
				},
				complete : function(data,textStatus){
				}
			});
		}
		return flag;
	})
});