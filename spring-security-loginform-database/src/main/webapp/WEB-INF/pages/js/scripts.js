

	function showStuff() {
		document.getElementById("hid").style.visibility = "hidden";
		document.getElementById('myP').style.display = "block";
    }

	$(function() {
			//validaciones
			$('.crearForm').bootstrapValidator({
			container: '.messages',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            name: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.name"/>'},


			            regexp: {
			                regexp: /^[a-zA-Z]+$/,
			                message: 'The username can only consist of alphabetical, number, dot and underscore'
			            }
	                },
	            },
	            lastname: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.lastname"/>'}
	                },
		            regexp: {
		                regexp: /^[a-zA-Z]+$/,
		                message: 'The username can only consist of alphabetical, number, dot and underscore'
		                
		                
		            }
	            },
	            mail: {
	                validators: {
	                    emailAddress: {message: '<fmt:message key="myhours.menu3.error.mailformat"/>'}
	                }
	            },
	            user: {
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.user"/>'},
	                }
	            },
	            password: { 
	                validators: {
	                    notEmpty: {message: '<fmt:message key="myhours.menu3.error.password"/>'},
	                    stringLength: {
	                        min: 6,
	                        message: '<fmt:message key="myhours.menu3.error.passwordlength"/>'
	                    },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: '<fmt:message key="myhours.menu3.error.passwordidentical"/>'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                	notEmpty: {message: '<fmt:message key="myhours.menu3.error.password"/>'},
	                    identical: {
	                        field: 'password',
	                        message: '<fmt:message key="myhours.menu3.error.passwordidentical"/>'
	                    }
	                }
	            },
	            
	            permissions:{
	            	validators: {
	            		choice: {
	            			min:1,
	            			max:3,
	            			message: '<fmt:message key="myhours.menu3.error.rol"/>'
	            			
	            		}
	            	}
	            	
	            }
	        }
	    });
			
		$("#datepicker").datepicker( {
		 	format: "yyyy",
		    viewMode: "years", 
		    minViewMode: "years"
		}).on('changeDate', function(e){
		    $(this).datepicker('hide');
			 var seleccionPais = ($('select[name=dlpais]').val());
	 		if ( seleccionPais !='0'){
	    		findHoliday();
	 		}
		});
						

	    var lastTab = localStorage.getItem('lastTab');
	    if (lastTab) {
	        $('[href="' + lastTab + '"]').tab('show');
	    };
	    
	 		

		loadUsers();
		loadCountry();
	});
	
	//limpiar pagina
	$(".modal").on('hidden.bs.modal', function(e) {
		$(document).find("input,textarea,select").val('').end();
		$('input:checkbox').removeAttr('checked');
		$(".form-group").removeClass('has-success');
		$(".form-group").removeClass('has-error');
		$(".glyphicon").removeClass('glyphicon-ok');
		$(".glyphicon").removeClass('glyphicon-remove');
		$("#DLState").val("0");
		$("#dropdownCountry").val("0");
		$("#showHoliday").val("0");
		
        $('.messages').find('small.help-block').hide();
        $('.messages').find('i.form-control-feedback').hide(); 
        
		$("#delUser").prop('disabled', false);
		$("#DLState").prop('disabled', false);
		$(".modUser").prop('disabled', false);
		
		
		$(".modUser").attr('readonly', true); 			

		
		
		$("#modifRadio").show();
		$("#deleteUser").show();
			
		clearForm();
		loadUsers();

	});	
	
	
	//validaciones
	$('#newHolidayJ').bootstrapValidator({
    container: '#messages',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields:{
   		holidayDate: {
           validators: {
               notEmpty: {message: '<fmt:message key="myhours.menu3.error.holidayrequired "/>'}
           }
       	},
        description: {
       	   validators:{
       				 regexp:{
     					regexp: /^[A-Z0-9 a-z ñ]*$/,
		        		message: 'The username can consist of alphabetical and numbers'
      		 		 }  
     	   }
        }
   	}
       
	});
	  
	//mantener tab
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        localStorage.setItem('lastTab', $(this).attr('href'));
        $("#info").remove();
        $("#error").remove();
    });
	
	
	
    function deleteHoliday(){
        var seleccion = ($('select[name=mHoliday]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'holiday':seleccion},
			url : "deleteHoliday.html",
			success : function(data) {
			alert("Se elimino correctamente!");
				findHoliday();
				$("#showHoliday").empty();
 				//document.getElementById('showHoliday').style.display = "none";
 				$("#showHoliday").hide();
 			},
 		});
    };
    
	$("#dropdownCountry").change(function() {
	 var seleccionDate = ($('select[name=holidayDate]').val());
	 	
 		if ( seleccionDate !='0'){
			findHoliday ();
 		}
	});
	
	function findHoliday (){
		var seleccionPais = ($('select[name=dlpais]').val());
		var year= $("#datepicker").val();	
		$("#showHoliday").html('');
			 $.ajax({
				type : "GET",	
				contentType :"application/json;charset=ISO-8859-1",
				url : "holidays.html",
				data : {"seleccionPais":seleccionPais, "year":year},
				dataType : 'json',
				success : function(data) {
					console.log("SUCCESS: ", data);
					JsonListF = data;
					var listItemsF = "";					
					for ( var i in data) {
						listItemsF += "<option value='" + data[i].id + "'>"+ data[i].holiday + "</option>";
					}
					console.log(listItemsF);
					$("#showHoliday").append(listItemsF);
					 if(listItemsF != "")
							document.getElementById('showHoliday').style.display = "inline";
						 else
							 document.getElementById('showHoliday').style.display = "none";
				},
	              error:function(request,status,error){
	              console.log(request.responseText);
	              }
			});
    }; 

	function formSubmit() {
		localStorage.clear();
		document.getElementById("logoutForm").submit();
	};
	
	function clearForm(){
		document.getElementById("crearForm").reset();
		document.getElementById("modifyForm").reset();
		$('input:checkbox').removeAttr('checked');
		$("#showHoliday").hide();
	};
	
	function deleteUser(){
		var seleccion = ($('select[name=dlstate]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'user':seleccion},
			url : "deleteUser.html",
			success : function(data) {
				loadUsers();
				$("#userinfo").html(data);
			},
		});
	};
	
	function loadCountry(){
	    $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "countries.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {	
				console.log("SUCCESS: ", data);	
				JsonCountry = data;
				var listItemsC= "<option value='0'>Seleccionar un pais</option>";
	        for (var i in data){
	        	listItemsC+= "<option value='" + data[i].id + "'>" + data[i].country + "</option>";
	        }
	        $("#dropdownCountry").html(listItemsC);
	        $("#dropdownCountry").find('[value=0]').hide();
			},
		});
	};
	
	function loadUsers(){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "listUsers.html",
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);	
				JsonList = data;
				var listItems = "<option value='0'>Select user</option>";
	        for (var i in data){
	        	listItems+= "<option value='" + data[i].id + "'>" + data[i].lastname +", " + data[i].name + "</option>";
	        }
	        $("#DLState").html(listItems);
	        $("#DLState").find('[value=0]').hide();
			},
		});
	};
	
	$("#linkUser").click(function(){
		var username = $.trim($("#username").text());

		$("#modifRadio").hide();
		$("#deleteUser").hide();
		
		$("#delUser").prop('disabled', true);
		$("#DLState").prop('disabled', true);
		$(".modUser").prop('disabled', false);
		$(".changeUser").removeAttr("readonly");
		$('select[name=dlstate]').find('option:contains('+ username +')').attr("selected", true).change();
	});

	$("#modUser").click(function(){
		
		$("#DLState").prop('disabled', false);
		$(".modUser").prop('disabled', true);
		$(".modUser").removeAttr("readonly");
		$('select[name=dlstate]').find('option:contains('+ username +')').attr("selected", true).change();
	});
		
	$("#DLState").change(function() {
		
		clearForm();
		$(".modUser").prop('disabled', false);
		
		var seleccion = ($('select[name=dlstate]').val());
		for (i in JsonList) {
			if (JsonList[i].id == seleccion) {
				$('input[name="id"]').val(JsonList[i].id);
				$('input[name="name"]').val(JsonList[i].name);
				$('input[name="lastname"]').val(JsonList[i].lastname);
				$('input[name="user"]').val(JsonList[i].user);
				$('input[name="mail"]').val(JsonList[i].mail);
				$('input[name="password"]').val(JsonList[i].password);
				$('input[name="confirmPassword"]').val(JsonList[i].password);
				var JrolList = JsonList[i].rol;
				for (var j in JrolList) {
					var rol = JrolList[j];
					document.getElementById(rol.rol).checked = true;
				}
				
			}
		}
	});
	
	
	function deleteUser(){
		var seleccion = ($('select[name=dlstate]').val());
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data: {'user':seleccion},
			url : "deleteUser.html",
			success : function(data) {
				loadUsers();
				
			},
		});
	};
	
