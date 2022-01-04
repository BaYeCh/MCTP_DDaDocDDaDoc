$(document).ready(function(){
		
		/*password확인에 값을 입력 받았을 떄 password와 일치하는지, 일치하지 않는지*/
		
		$('#closed_add').click(function(){
			//alert("Add Connect");
			var name = $('#title').val();
			var addr = $('#addr').val();
			var start = $('#time1').val();
			var end = $('#time2').val();
			var writer = $('#writer').val();
			var flag= false;
			
			$.ajax({
				data :  {"hospitalName" : name, "hospitalAddr" : addr, "closedStart" : start,"closedEnd" : end,"writerId" : writer},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './closed_register',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert("등록되었습니다.");
						flag = true;
						window.location.assign('./myList');
					} else {
						alert("이미 있는 내용입니다.");
					}
				},
				error : function(data, textStatus){
				},
				complete : function(data,textStatus){

				}
			});
			
			return flag;
		});   
		
		$('#change').click(function(){
			//alert("Add Connect");
			var closedId = $("#closedId").text();
			var name = $('#name').val();
			var addr = $('#addr').val();
			var start = $('#time1').val();
			var end = $('#time2').val();
			var flag = false;
			
			$.ajax({
				data :  {"closedId":closedId, "hospitalName" : name, "hospitalAddr" : addr, "closedStart" : start,"closedEnd" : end},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './closed_change',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert("수정되었습니다.");
						flag = true;
						window.location.assign('./myList');
					} else {
						alert("다시 입력해주세요");
					}
				},
				error : function(data, textStatus){

				},
				complete : function(data,textStatus){

				}
			});
			
			return flag;
		});   
		
		$('#delete').click(function(){
			//alert("Add Connect");
			var closedId = $("#closedId").text();
			var flag = false;
			
			$.ajax({
				data :  {"closedId":closedId},//JSON방식
				type : "post",
				dataType: "text",
				async: false,
				url : './closed_delete',
				success : function(data, textStatus){
					if(eval(data)==true){
						alert("삭제되었습니다.");
						flag = true;
						window.location.assign('./myList');
					} else {
						alert("다시 입력해주세요");
					}
				},
				error : function(data, textStatus){

				},
				complete : function(data,textStatus){

				}
			});
			
			return flag;
		});   
 });