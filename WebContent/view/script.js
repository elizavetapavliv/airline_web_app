const dom = (function (language) {
	
	const locale = language == 'en'? en: ru;
	
	let en = {
		registration: 'Sign up',
		guest: 'Log in as a guest',
		signInExist: 'Sign in with an existing account',
		password: 'Password',
		signIn: 'Sign in',
		repeatPassword: 'Repeat password',
		adminCode: 'Administrator code',
		code: 'Code (optional)',
		signUp: 'Sign up and login',
		flightId: 'Flight id',
		sumbit: 'Submit',
		changeAirport: 'Change destination airport',
		airportId: 'New airport id',
		cancel: 'Cancel flight',
		match: "Passwords don't match",
		update: 'Update',
		save: 'Save'
	}
	
	let ru = {
		registration: 'Зарегистрироваться',
		guest: 'Войти как гость',
		signInExist: 'Войти через существующий аккаунт',
		password: 'Пароль',
		signIn: 'Войти',
		repeatPassword: 'Повторите пароль',
		adminCode: 'Код администратора',
		code: 'Код (не обязательно)',
		signUp: 'Зарегистрироваться и войти',
		flightId: 'Номер рейса',
		submit: 'Подтвердить'
		changeAirport: 'Изменить аэропорт назначения рейса',
		airportId: 'Номер нового аэропорта',
		cancel: 'Отменить рейс',
		match: 'Пароли не совпадают',
		update: 'Изменить',
		save: 'Сохранить'
	}
	
	var input, airportInput;
	
	const loginRegistrationFormArray = [
		{
			 name: 'page',
             type: 'hidden',
             value: 'registration'
		},	
		{
            type: 'submit',
            value: locale[registration]
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
            value: locale[guest]
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
            value: locale[signInExist]
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
			label: locale[password],
            placeholder: locale[password],
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
            value: locale[signIn]
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
			label: locale[password],
            placeholder: locale[password],
            name: 'password',
            type: 'password'
		},
		{	
			label: locale[repeatPassword],
            placeholder: locale[password],
            name: 'repeat_password',
            type: 'password'
		},
		{	
			label: locale[adminCode],
            placeholder: locale[code],
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
            value: locale[signUp]
        }		
	];
	
    const flightIdBrigadeFormArray = [
		{	
			label: locale[flightId],
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
            value: locale[submit]
        }
	];
    
    const flightIdAirportsFormArray = [
		{	
			label: locale[flightId],
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
            value: locale[submit],
        }
	
	];
	
	const choiceFormArray = [
		{
			type: 'radio',
			class: 'choice_radio',
			name: 'flight_action',
			label: locale[changeAirport],
			value: 'change_airport',
            page: 'airports',
			idInput: {	
				placeholder: locale[airportId],
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
			label: locale[cancel],
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
    		repeat_password.setCustomValidity(locale[match]);
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
    	if(event.srcElement.value == locale[update]){
    		event.srcElement.value = locale[save];
    		
    		switch(event.srcElement.id){
    		case 'ipilot1':
    			document.getElementById('pilot1').contentEditable = 'true';
    			break;
    		case 'ipilot2':
    			document.getElementById('pilot2').contentEditable = 'true';
        		break;
    		case 'inavigator':
        		document.getElementById('navigator').contentEditable = 'true';
        		break;
    		case 'ioperator':
        		document.getElementById('operator').contentEditable  = 'true';
        		break;
    		}
    		event.preventDefault();
    	}
    	else{
    		event.srcElement.value = locale[update];
    		
        	const form = event.srcElement.form;
        	const hidden = document.createElement('input');
        	hidden.name = 'brigade_id';
        	hidden.type = 'hidden';
        	hidden.value = document.getElementById('bId').innerText;
            form.appendChild(hidden);	
            
         	const hidden2 = document.createElement('input');
        	hidden2.type = 'hidden';
        	var td;
        	switch(event.srcElement.id){
        		case 'ipilot1':
        			td = document.getElementById('pilot1');
        			hidden2.name = 'pilot1';
        			break;
        		case 'ipilot2':
        			td = document.getElementById('pilot2');
            		hidden2.name = 'pilot2';
            		break;
        		case 'inavigator':
        			td = document.getElementById('navigator');
            		hidden2.name = 'navigator';                   
            		break;
        		case 'ioperator':
        			td = document.getElementById('operator');
            		hidden2.name = 'operator';
            		break;
        	}
        	td.contentEditable = 'false';
        	hidden2.value = td.innerText;
        	form.appendChild(hidden2);	
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