
 $(document).ready(function(){
    $('#login').click(function(){
        var inputID = $('#id').val();
        var inputPWD = $('#pw').val();
        var flag = false;

        if (inputID == "" || inputPWD == "")
        {
            alert('Please check your inputs');
        } else{       
	        $.ajax({
				data :  {"id" : inputID, "pwd" : inputPWD },
				type : "post",
				dataType: "text",
				async: false,
				url : './login_check',     						
				success : function(data, textStatus){
		            if (eval(data)==true){
						flag = true;
		                alert(inputID + "님 반갑습니다.");
		                window.location.assign('./mainPage');
		            } else{
		                alert("틀렸어어요 다시보내주세요");
		            }
				},
				error : function(data, textStatus){
					$('#message').text('error');
				},
				complete : function(data,textStatus){
				}
			});
		}
		return flag;
	})
});