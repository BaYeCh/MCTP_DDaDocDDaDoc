$(document).ready(function(){
    $('#update').click(function(){
        var id = $('#id').val();
        var pwd = $('#pwd').val();
        var tel = $('#tel').val();
        var name = $('#name').val();
        var email = $('#email').val();
        var birth = $('#birth').val();
        var nickname = $('#nickname').val();
        var address1 = $('#address1').val();
        var address2 = $('#address2').val();
        var address3 = $('#address3').val();
        var flag = false;
        if (name == "" || email == "")
        {
            alert('Please check your inputs');
        } else{       
	        $.ajax({
				data :  {"id" : id, "pwd" : pwd,"tel" : tel, "name" : name, "email" : email, "birth" : birth, "nickname" : nickname,
				"address1" : address1, "address2" : address2, "address3" : address3, "admin" : 0},
				type : "post",
				dataType: "text",
				async: false,
				url : '/ais/change',     						
				success : function(data, textStatus){
		            if (eval(data)==true){
						flag = true;
						alert("수정하였습니다."); //요거는 됨
		                window.location.assign('./mainPage');  // 요게 안됨  
		            } else{
		                alert("다시 입력해주세요.");
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
})