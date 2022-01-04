/**
 * 
 */
	// jQuery를 이용하여 가져오기
	
	function checkPW() {
            var a =  $('#pwd').val();
            var b =  $('#checkpw').val();

            if(a!=b) {
                document.getElementById("message").innerHTML="비밀번호가 일치하지 않습니다.";
                document.getElementById("message").style.color='red';
            } else {
                document.getElementById("message").innerHTML="비밀번호가 일치합니다."
                document.getElementById("message").style.color='blue';
            }
        }
        
	$(document).ready(function(){
		
		/*password확인에 값을 입력 받았을 떄 password와 일치하는지, 일치하지 않는지*/
		
		$('#member_add').click(function(){
			//alert("Add Connect");
			var flag = false;
			var id = $('#id').val();
			var pwd = $('#pwd').val();
			var name = $('#name').val();
			var nickname = $('#nickname').val();
			var birth = $('#birth').val();
			var tel = $('#tel').val();
			var email = $('#email').val();
			var address1 = $('#address1').val();
			var address2 = $('#address2').val();
			var address3 = $('#address3').val();
			
			//alert(id);
			$.ajax({
				data :  {"id" : id, "pwd" : pwd, "name" : name,"nickname" : nickname,"birth" : birth,"tel" : tel, "email" : email, "address1" : address1,
				"address2" : address2, "address3" : address3},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './addMember',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert(id+"님 회원가입이 완료되었습니다.");
						flag = true;
						window.location.assign('./login_form');
					} else {
						alert("다시 입력해주세요");
					}
				},
				error : function(data, textStatus){
					$('#message').text('error');
				},
				complete : function(data,textStatus){

				}
			});
			
			return flag;
		});
		
		$('#id_check').click(function(){
			var flag = false;
			var id = $('#id').val();
			$.ajax({
				data :  {"id" : id},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './idCheck',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert(id+"는 이미 사용중입니다.");
						flag = true;
					} else{
						alert(id+"는 사용할 수 있습니다.");
					}
				},
				error : function(data, textStatus){
					$('#message').text('error');					
				},
				complete : function(data,textStatus){

				}
			});			
			return flag;		
		});
		
		$('#email_check').click(function(){
			var flag = false;
			var email = $('#email').val();
			$.ajax({
				data :  {"email" : email},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './emailCheck',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert(email+"는 이미 사용중입니다.");
						flag = true;
					} else{
						alert(email+"는 사용할 수 있습니다.");
					}
				},
				error : function(data, textStatus){
					$('#message').text('error');					
				},
				complete : function(data,textStatus){

				}
			});
			
			return flag;
		
		});
		
		$('#nickName_check').click(function(){
			var flag = false;
			var nickname = $('#nickname').val();
			$.ajax({
				data :  {"nickname" : nickname},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './nickNameCheck',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert(nickname+"는 이미 사용중입니다.");
						flag = true;
					} else{
						alert(nickname+"는 사용할 수 있습니다.");
					}
				},
				error : function(data, textStatus){
					$('#message').text('error');					
				},
				complete : function(data,textStatus){
				}
			});			
			return flag;	
		});		
	});
	