$(document).ready(function(){
		let markers = new Array();
		let infoWindows = new Array();
		//초기 화면에서 띄울 맵 정보 (멀티캠퍼스 선릉점)
		var cityhall = new naver.maps.LatLng(37.5033046,127.050273),
		    map = new naver.maps.Map('map', {
		        center: cityhall.destinationPoint(0, 500),
		        zoom: 15
		    }),
		    marker = new naver.maps.Marker({
		        map: map,
		        position: cityhall
				
		    });
			
		var contentString = [
		        '<div class="iw_inner">',
		        '   <h3>멀티캠퍼스 선릉</h3>',
		        '   <p>서울특별시 강남구 선릉로 428 | 서울특별시 강남구 대치동 889-41<br />',
		        '       <a href="www.multicampus.com" target="_blank">www.multicampus.com/</a>',
		        '   </p>',
		        '</div>'
		    ].join('');
		
		var infoWindow = new naver.maps.InfoWindow({
		    content: contentString
		});
		//클릭하면 닫히게 만드는 로직
		naver.maps.Event.addListener(marker, "click", function(e) {
		    if (infoWindow.getMap()) {
		        infoWindow.close();
		    } else {
		        infoWindow.open(map, marker);
		    }
		});
		
		infoWindow.open(map, marker);

	
	
		$('#btn').click(function(){	
			var locale = 	$("#locale").val();
			var hp_subject=$('#hp_subject').val();
			var time=$('#time').val();
			var flag = false;
			var lang0;
			var lat0;
			let hp_lats = new Array();
			let hp_langs = new Array(); 
			if((locale=="") && (time!="")){
				alert("현위치에 내용을 입력해주세요.")
			} else if((locale!="") && (time=="")){
				alert("이용시간에 내용을 입력해주세요.")
			} else if((locale=="") && (time=="") ){
				alert("현위치와 이용시간에 내용을 입력해주세요.")
			} else{
			$.ajax({
		         type:"post",
		         url:"./web02",
		         data :{"locale":locale,"hp_subject":hp_subject,"time":time},
		         async: false,
			     success:function (data,textStatus){
			    	  resultText = JSON.parse(data);
			    	  //text = resultText.results[0].region.area1.name+","+resultText.results[1]
			    	  lang0 = resultText.addresses[0].x;
			    	  lat0 = resultText.addresses[0].y;
			    	  
			    	  var mapOptions = {
			    	  center: new naver.maps.LatLng(lat0, lang0),
							    zoom: 15
							};
			    	  var map = new naver.maps.Map('map', mapOptions);
			    	  var marker = new naver.maps.Marker({
					   				 position: new naver.maps.LatLng(lat0, lang0),
					   					 map: map,
									icon: { 
										url : './resources/imgs/pin_default.png',
										size: new naver.maps.Size(22, 35),
        								origin: new naver.maps.Point(0, 0),
        								anchor: new naver.maps.Point(11, 35)
										}								
			    	  });
			    	  var infoWindow = new naver.maps.InfoWindow({
						    content: '<div style="width:150px;text-align:center;padding:10px;"><b>현 위치</b></div>'
						});
						
						naver.maps.Event.addListener(marker, "click", function(e) {
						    if (infoWindow.getMap()) {
						        infoWindow.close();
						    } else {
						        infoWindow.open(map, marker);
						    }
		
						});
							infoWindow.open(map, marker);
			    	  $.ajax({
					         type:"post",
					         url:"./hospital",
					         data :{"lang":lang0,"lat":lat0,"hp_subject":hp_subject,"time":time},
					         async: false,
						     success:function (data,textStatus){
									// 부모 선택자에서 후손 선택자들 초기화 하는 코드
									$('.hospitalList div').html('');
									$('.pharmacyList div').html('');
									resultText = JSON.parse(data);
									if (resultText.length==0){
									alert("조건에 만족하는 병원이 없습니다.");
									} else {
						    	 
						    	 	for(var i=0; i<resultText.length; i++){
										$('#lang'+i).text(resultText[i].lang);
										$('#lat'+i).text(resultText[i].lat);
							    	  	$('#name'+i).text(i+1+". "+resultText[i].hp_name);
							    	  	$('#addr'+i).text(resultText[i].hp_addr);
							    	  	$('#subj'+i).text(resultText[i].hp_subject);
							    	  	$('#dutyTime'+i).text(resultText[i].hp_dutyTime1+ "~"+ resultText[i].hp_dutyTime2);
							    	  	$('#distance'+i).text(resultText[i].distance+'km');
							    	  	$('#message'+i).text(resultText[i].message);
										$('#link'+i).append("<a href='./hospital_detail?hpid="+resultText[i].hpid+"'>상세정보</a>");
										$('#pharmacy_btn'+i).append("<button class='pharmacy' id='pharmacy"+i+"'>근처 약국 표시</button>");
							    	  	hp_lats.push(resultText[i].lat);
										hp_langs.push(resultText[i].lang);
							    	  	var marker = new naver.maps.Marker({
							   				position: new naver.maps.LatLng(resultText[i].lat, resultText[i].lang),
							   					 map: map,
												icon: { // 병원 마커 이미지
												url : './resources/imgs/pin_hospital.png',
												size: new naver.maps.Size(30, 40),
		        								origin: new naver.maps.Point(0, 0),
		        								anchor: new naver.maps.Point(15, 40)
												}	
						   			 	 });
								
									 	var infoWindow = new naver.maps.InfoWindow({
	    								
										content : '<div style="width:150px;text-align:center;padding:10px;"><b>'+ resultText[i].hp_name +'</b></div>'
										});
										markers.push(marker);
										infoWindows.push(infoWindow);
						    	 	 }
										//2021.12.17 약국 찾기 버튼 누른 후에도 마커 클릭 시 병원 이름 나오게
						    	  function getClickHandler(seq) {										
								            return function(e) {  // 마커를 클릭하는 부분
								                var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
								                    infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다
								
								                if (infoWindow.getMap()) {
								                    infoWindow.close();
								                } else {
								                    infoWindow.open(map, marker); // 표출 
								                }
								    		}
								  }
								//alert(markers.length);
								//alert(infoWindows);
									for (var i=0; i<markers.length; i++) {
							    	//console.log(markers[i] , getClickHandler(i));
							        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
	    							}
								}
					   			  
						     },
						     error:function(data,textStatus){
						        alert("에러가 발생했습니다.");
						     },
						     complete:function(data,textStatus){
								
						     }
						  });
			     },
			     error:function(data,textStatus){
			        alert("에러가 발생했습니다.");
			     },
			     complete:function(data,textStatus){
			    	$('.pharmacy').click(function(){
				
					var mapOptions = {
			    	  center: new naver.maps.LatLng(lat0, lang0),
							    zoom: 15
							};
			    	  var map = new naver.maps.Map('map', mapOptions);
			    	  var marker1 = new naver.maps.Marker({
					   				 position: new naver.maps.LatLng(lat0, lang0),
					   					 map: map,
									icon: { //2021.12.17 현위치 색 변경 
										url : './resources/imgs/pin_default.png',
										size: new naver.maps.Size(22, 35),
        								origin: new naver.maps.Point(0, 0),
        								anchor: new naver.maps.Point(11, 35)
										}								
			    	  });
			    	  var infoWindow1 = new naver.maps.InfoWindow({
						    content: '<div style="width:150px;text-align:center;padding:10px;"><b>현 위치</b></div>'
						});
						
						naver.maps.Event.addListener(marker1, "click", function(e) {
						    if (infoWindow1.getMap()) {
						        infoWindow1.close();
						    } else {
						        infoWindow1.open(map, marker1);
						    }
		
						});
							infoWindow1.open(map, marker1);		
					var pharmacy = $(this);
					var div1 = pharmacy.parent().parent();
					var div2 = div1.children();
					var lang = div2.eq(0).text();
					var lat = div2.eq(1).text();
					var hospitalName = div2.eq(2).text();
					var time=$('#time').val();
					var flag = false;
					//새로 markers들을 넣는 배열
					let markers2 = new Array();
					let infoWindows2 = new Array();
					 var marker2 = new naver.maps.Marker({
					   				 position: new naver.maps.LatLng(lat, lang),
					   					 map: map,
									icon: { // 병원 마커 이미지
										url : './resources/imgs/pin_hospital.png',
										size: new naver.maps.Size(30, 40),
        								origin: new naver.maps.Point(0, 0),
        								anchor: new naver.maps.Point(15, 40)
										}								
			    	  });
			    	  var infoWindow2 = new naver.maps.InfoWindow({
						    content: '<div style="width:150px;text-align:center;padding:10px;"><b>'+hospitalName+'</b></div>'
						});
						
						naver.maps.Event.addListener(marker2, "click", function(e) {
						    if (infoWindow2.getMap()) {
						        infoWindow2.close();
						    } else {
						        infoWindow2.open(map, marker2);
						    }
		
						});
							infoWindow2.open(map, marker2);
					//alert(markers);
					$.ajax({
						type:"post",
						url:"./pharmacy",
						data :{"lang":lang,"lat":lat,"time":time},
						async: false,
						success:function (data,textStatus){
							//alert(data);
							$('.pharmacyList div').html('');
							
							resultText = JSON.parse(data);
							
							for(var i=0; i<resultText.length; i++){
					    	  	$('#phName'+i).text(i+1+". "+resultText[i].hp_name);
					    	  	$('#phAddr'+i).text(resultText[i].hp_addr);
					    	  	$('#phSubj'+i).text("약국");
					    	  	$('#phDutyTime'+i).text(resultText[i].hp_dutyTime1+ "~"+ resultText[i].hp_dutyTime2);
					    	  	$('#phDistance'+i).text(resultText[i].distance+'km');
					    	  	$('#phMessage'+i).text(resultText[i].message);
					    	  	$('#phLink'+i).append("<a href='./pharmacy_detail?hpid="+resultText[i].hpid+"'>상세정보</a>");
					    	  	var marker3 = new naver.maps.Marker({
				   				 position: new naver.maps.LatLng(resultText[i].lat, resultText[i].lang),
				   					 map: map,
									icon: { 
										url : './resources/imgs/pin_pharmacy.png',
										size: new naver.maps.Size(22, 35),
        								origin: new naver.maps.Point(0, 0),
        								anchor: new naver.maps.Point(11, 35)
										}	
				   			 	 });
							
							 	var infoWindow3 = new naver.maps.InfoWindow({
								
								content : '<div style="width:150px;text-align:center;padding:10px;"><b>'+ resultText[i].hp_name +'</b></div>'
								});
								markers2.push(marker3);
								infoWindows2.push(infoWindow3);
					    	  }
				   			
							
						},
						error:function(data,textStatus){
						    alert("에러가 발생했습니다.");
						},
						complete:function(data,textStatus){
							  function getClickHandler(seq) {
									
							            return function(e) {  // 마커를 클릭하는 부분
							                var marker3 = markers2[seq], // 클릭한 마커의 시퀀스로 찾는다.
							                    infoWindow3 = infoWindows2[seq]; // 클릭한 마커의 시퀀스로 찾는다
							
							                if (infoWindow3.getMap()) {
							                    infoWindow3.close();
							                } else {
							                    infoWindow3.open(map, marker3); // 표출 
							                }
							    		}
							    	}
							//alert(markers2.length);
							//alert(infoWindows);
							for (var i=0; i<markers2.length; i++) {
					    	//console.log(markers2[i] , getClickHandler2(i));
					        naver.maps.Event.addListener(markers2[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
							} 
						}
					});
					return flag;
		
				});
			   }
				
			});
			}
		return flag;		
	});
	
});
