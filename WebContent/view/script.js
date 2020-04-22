const dom = (function () {
	
	var input, airportInput;
	
	const loginRegistrationFormArray = [
		{
			 name: 'page',
             type: 'hidden',
             value: 'registration'
		},	
		{
            type: 'submit',
            value: 'Зарегистрироваться'
        }		
	];	
	
	const loginGuestFormArray = [
		{
			name: 'page',
            type: 'hidden',
            value: 'login'
		},	
		{
            type: 'submit',
            value: 'Войти как гость'
        }		
	];	
	
	const registrationLoginFormArray = [
		{
			 name: 'page',
             type: 'hidden',
             value: 'login'
		},	
		{
            type: 'submit',
            value: 'Войти через существующий аккаунт'
        }		
	];	
	
    const loginFormArray = [
		{	
			label: 'Email',
            placeholder: 'Email',
            name: 'email',
            type: 'email'
		},
		{	
			label: 'Пароль',
            placeholder: 'Пароль',
            name: 'password',
            type: 'password'
		},
		{
			 name: 'page',
             type: 'hidden',
             value: 'login'
		},	
		{
            type: 'submit',
            value: 'Войти'
        }		
	];
    
    const registrationFormArray = [
		{	
			label: 'Email',
            placeholder: 'Email',
            name: 'email',
            type: 'email'
		},
		{	
			label: 'Пароль',
            placeholder: 'Пароль',
            name: 'password',
            type: 'password'
		},
		{	
			label: 'Повторите пароль',
            placeholder: 'Пароль',
            name: 'repeat_password',
            type: 'password'
		},
		{	
			label: 'Код администратора',
            placeholder: 'Код (не обязательно)',
            name: 'admin_code',
            type: 'password'
		},
		{
			 name: 'page',
             type: 'hidden',
             value: 'registration'
		},
		{
            type: 'submit',
            value: 'Зарегистрироваться и войти'
        }		
	];
	
    const flightIdBrigadeFormArray = [
		{	
			label: 'Номер рейса',
            placeholder: 'Id',
            name: 'flight_id_brigade',
            type: 'number',
            min: '1'
		},
		{
			name: 'page',
            type: 'hidden',
            value: 'brigade'
		},
		{
            type: 'submit',
            value: 'Подтвердить'
        }
	
	];
    
    const flightIdAirportsFormArray = [
		{	
			label: 'Номер рейса',
            placeholder: 'Id',
            name: 'flight_id_airports',
            type: 'number',
            min: '1'
		},
		{
		    name: 'page',
            type: 'hidden',
            value: 'airports'
		},
		{
            type: 'submit',
            value: 'Подтвердить'
        }
	
	];
	
	const choiceFormArray = [
		{
			type: 'radio',
			class: 'choice_radio',
			name: 'flight_action',
			label: 'Изменить аэропорт назначения рейса',
			value: 'change_airport',
            page: 'airports',
			idInput: {	
				placeholder: 'New airport id',
				id: 'input_airport_id',
				type: 'number',
	            name: 'airport_id',
	            min: '1'
			}
		},
		{
			type: 'radio',
			class: 'choice_radio',
			name: 'flight_action',
			label: 'Отменить рейс',
			value: 'cancel_flight'
		},
		{
            type: 'submit',
            value: 'Ok',
			class: 'choice_submit'
        }
	];
	
    function buildForm(form, type) {
        var formArray;
        switch(type) {
        case 'loginForm':
        	buildLoginForm(form);
        	return;
    	case 'registrationForm':
    		buildRegistrationForm(form);
    		return;
    	case 'loginRegistrationForm':
         	formArray = loginRegistrationFormArray;
         	break;
    	case 'registrationLoginForm':
         	formArray = registrationLoginFormArray;
         	break;
    	case 'loginGuestForm':
         	formArray = loginGuestFormArray;
         	break;
        case 'flightIdBrigadeForm':
        	formArray = flightIdBrigadeFormArray;
        	break;
        case 'flightIdAirportsForm':
        	formArray = flightIdAirportsFormArray;
        	break;
    	default:
    		formArray = choiceFormArray;
        }
		
		var div;
        for(const element in formArray) {
            switch (formArray[element].type) {
                case 'submit':
                    const submit = document.createElement('input');
                    submit.type = formArray[element].type;
                    submit.value = formArray[element].value;
					if(formArray[element].class == 'choice_submit'){
						submit.setAttribute('class', formArray[element].class);
					}				
                    form.appendChild(submit);					
                    break;
				case 'radio':		
					div = document.createElement('div');
					div.setAttribute('class', 'choice');
					
					const radio = document.createElement('input');	
					radio.type = formArray[element].type;
					radio.name = formArray[element].name;
					radio.value = formArray[element].value;
					radio.setAttribute('class', formArray[element].class);
					div.appendChild(radio);
					
					const radioLabel = document.createElement('label');
                    radioLabel.innerHTML = formArray[element].label;			
					div.appendChild(radioLabel);
					
					if(formArray[element].idInput != null){
						const hidden = document.createElement('input');
					    hidden.name = 'page';
					    hidden.type = 'hidden';
					    hidden.value = formArray[element].page;                
	                    div.appendChild(hidden);	
	                   
	                	const hidden2 = document.createElement('input');
					    hidden2.name = 'flight_id';
					    hidden2.type = 'hidden';
					    hidden2.value = document.getElementById('flight_id').innerHTML;         
	                    div.appendChild(hidden2);	
	                    
	                    const hidden3 = document.createElement('input');
					    hidden3.name = 'from_airport_id';
					    hidden3.type = 'hidden';
					    hidden3.value = document.getElementById('from_airport_id').innerHTML;                  
	                    div.appendChild(hidden3);	
						
						const idInput = formArray[element].idInput;
						airportInput = document.createElement('input');
						airportInput.placeholder = idInput.placeholder;
						airportInput.id = idInput.id;
						airportInput.required = true;
						airportInput.type = idInput.type;
						airportInput.name = idInput.name;
						airportInput.min = idInput.min;
						div.appendChild(airportInput);
						
						radio.setAttribute('checked', true);	
						radio.onchange = onAirportChecked;
					}
					else{
						radio.onchange = onDeleteChecked;
					}
					form.appendChild(div);
                    break;	
				case 'hidden':
					const hiddentInput = document.createElement('input');
					hiddentInput.name = formArray[element].name;         
					hiddentInput.type = formArray[element].type;
					hiddentInput.value = formArray[element].value;  
					form.appendChild(hiddentInput);
					break;
				default:
					const label = document.createElement('label');
					label.innerHTML = formArray[element].label;
					form.appendChild(label);
                    const input = document.createElement('input');
                    input.placeholder = formArray[element].placeholder;
                    input.name = formArray[element].name;
                	input.required = true;
                    input.type = formArray[element].type;
                    input.min = formArray[element].min;
                    form.appendChild(input);			
                    break;	
            }
        }
    }    
    
    function buildLoginForm(form){
    	const table = document.createElement('table');  
    	var i = 0;
		for(i; i < 2; i++) {
		 	const tr = document.createElement('tr');
		 	table.appendChild(tr);
	    	const td1 = document.createElement('td');
	    	const td2 = document.createElement('td');
	    	tr.appendChild(td1);
	    	tr.appendChild(td2);
	    	const label = document.createElement('label');
			label.innerHTML = loginFormArray[i].label;
			td1.appendChild(label);
			const input = document.createElement('input');
            input.placeholder = loginFormArray[i].placeholder;
            input.name = loginFormArray[i].name;         
            input.type = loginFormArray[i].type;
            input.required = true;
            td2.appendChild(input);
		}
		form.appendChild(table);
		const input = document.createElement('input');
		input.name = loginFormArray[i].name;         
		input.type = loginFormArray[i].type;
		input.value = loginFormArray[i].value;  
		form.appendChild(input);
		i++;
	    const submit = document.createElement('input');
        submit.type = loginFormArray[i].type;
        submit.value = loginFormArray[i].value;			
        form.appendChild(submit);						
    }
	
    function buildRegistrationForm(form){
    	const table = document.createElement('table');  
    	var i = 0;
		for(i; i < 4; i ++) {
		 	const tr = document.createElement('tr');
		 	table.appendChild(tr);
		 	const td1 = document.createElement('td');
		 	const td2 = document.createElement('td');
	    	tr.appendChild(td1);
	    	tr.appendChild(td2);
	    	const label = document.createElement('label');
			label.innerHTML = registrationFormArray[i].label;
			td1.appendChild(label);
			const input = document.createElement('input');
            input.placeholder = registrationFormArray[i].placeholder;
            input.name = registrationFormArray[i].name;         
            input.type = registrationFormArray[i].type;
            if(registrationFormArray[i].name != 'admin_code'){
          	  	input.required = true;
            }          
            if(registrationFormArray[i].name == 'password'){
            	input.onchange = validatePassword;
            	input.id = 'password';
            }           
            else if(registrationFormArray[i].name == 'repeat_password' ){
            	input.onkeyup = validatePassword;
            	input.id = 'repeat_password';
            }   
            td2.appendChild(input);
		}
		form.appendChild(table);	
		const input = document.createElement('input');
        input.name = registrationFormArray[i].name;         
        input.type = registrationFormArray[i].type;
    	input.value = registrationFormArray[i].value;  
    	form.appendChild(input);
		i++;
	    const submit = document.createElement('input');
        submit.type = registrationFormArray[i].type;
        submit.value = registrationFormArray[i].value;			
        form.appendChild(submit);				
    }
    
    function validatePassword(){   	
    	const password = document.getElementById("password"), 
    	repeat_password = document.getElementById("repeat_password");
    	
    	if(password.value != repeat_password.value) {
    		repeat_password.setCustomValidity("Passwords don't match");
    	} 
    	else{
    		repeat_password.setCustomValidity("");
    	}
    }
    
    function initPage() { 
    	const registrationLoginForm = document.getElementById('registration_login_form');
    	if (registrationLoginForm != null) {		
   			buildForm(registrationLoginForm, 'registrationLoginForm');
    	}	    	
    	
    	const loginRegistrationForm = document.getElementById('login_registration_form');
    	if (loginRegistrationForm != null) {		
   			buildForm(loginRegistrationForm, 'loginRegistrationForm');
    	}	    	
    	
    	const loginGuestForm = document.getElementById('login_guest_form');
    	if (loginGuestForm != null) {		
   			buildForm(loginGuestForm, 'loginGuestForm');
    	}	    	
    	
    	const registrationForm = document.getElementById('registration_form');
    	if (registrationForm != null) {		
   			buildForm(registrationForm, 'registrationForm');
   			return;
    	}	    	
       
    	const loginForm = document.getElementById('login_form');
    	if (loginForm != null) {		
   			buildForm(loginForm, 'loginForm');
   			return;
    	}	    	
    	
    	const flightIdBrigadeForm = document.getElementById('flight_id_brigade_form');
    	if (flightIdBrigadeForm != null) {
            buildForm(flightIdBrigadeForm, 'flightIdBrigadeForm');
            var input = document.getElementsByName("update");
            var inputList = Array.prototype.slice.call(input);
            inputList.forEach(brigadeAction);
            return;
        }
        
    	const flightIdAirportsForm = document.getElementById('flight_id_airports_form');
    	if (flightIdAirportsForm != null) {
            buildForm(flightIdAirportsForm, 'flightIdAirportsForm');
    	}
        
        const choiceForm = document.getElementById('choice_form');
        if (choiceForm != null) {		
			buildForm(choiceForm, 'choiceForm');
        }	
    }

    function brigadeAction(value, index, ar){
    	value.onclick = onUpdadeClicked;
    }
    
    function onUpdadeClicked(){
    	if(event.srcElement.value == 'Update'){
    		event.srcElement.value = 'Save';
    		
    		switch(event.srcElement.id){
    		case 'ipilot1':
    			document.getElementById('pilot1').contenteditable = true;
    			break;
    		case 'ipilot2':
    			document.getElementById('pilot2').contenteditable = true;
        		break;
    		case 'inavigator':
        		document.getElementById('navigator').contenteditable = true;
        		break;
    		case 'ioperator':
        		document.getElementById('operator').contenteditable = true;
        		break;
    		}
    		event.preventDefault();
    	}
    	else{
    		event.srcElement.contenteditable = false;
    		event.srcElement.value = 'Update';
    		
        	const form = event.srcElement.form;
        	const hidden = document.createElement('input');
        	hidden.name = 'brigade_id';
        	hidden.type = 'hidden';
        	hidden.value = document.getElementById('bId').innerHtml;
            form.appendChild(hidden);	
            
         	const hidden2 = document.createElement('input');
        	hidden2.type = 'hidden';
        	switch(event.srcElement.id){
        		case 'ipilot1':
        			hidden2.name = 'pilot1';
        			hidden2.value = document.getElementById('pilot1').innerHtml;
        			break;
        		case 'ipilot2':
            		hidden2.name = 'pilot2';
                    hidden2.value = document.getElementById('pilot2').innerHtml;
            		break;
        		case 'inavigator':
            		hidden2.name = 'navigator';
                    hidden2.value = document.getElementById('navigator').innerHtml;
            		break;
        		case 'ioperator':
            		hidden2.name = 'operator';
                    hidden2.value = document.getElementById('operator').innerHtml;
            		break;
        	}
    	}
    }
    
	function onAirportChecked() {
		var airportId = document.getElementById('input_airport_id');
		airportId.hidden = false;
		airportInput.required = true;
	}
	
	function onDeleteChecked() {
		var airportId = document.getElementById('input_airport_id');
		airportId.hidden = true;
		airportInput.value = '';
		airportInput.required = false;
	}
	
    return {
        initPage
    }

}());

dom.initPage();