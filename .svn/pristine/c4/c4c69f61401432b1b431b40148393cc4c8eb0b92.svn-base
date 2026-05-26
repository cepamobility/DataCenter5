(function($) {

	$.widget('tadeouy.question', $.tadeouy.loggable, {
		options: {
			create: function($event, data){
				$(this).data('tadeouy-question').log('tadeouy.question created');
			}
		},

		_create: function(){
			var question = this;
			this.$question = this.element;
			this.id = this._getId();
			this.questionType = this.$question.data('type');
			this.foto = this.$question.data('foto');
			this.$input = this.$question.find('input, select, textarea');
			this.multiInputs = this.$input.length>1;
			this._initComplexQuestion();

			this.$error = $('<div class="error_text"></div>');
			this.$label = this.$question.find('label').eq(0);
			this.$label.after(this.$error);

			this.completed = this.$question.data('completed');
			this.valid = this.completed;

			if (this.completed) {
				this.$question.toggleClass('completed', this.completed);
				this.$question.toggleClass('valid', this.completed);
				this.$input.prop('disabled', true);
			}else{
				this.$input.bind('keyup change blur', function($event){
					question._validate();
				});
				
				$(window).bind('forcevalidate',
						function(){
							question._validate();
						}
				);
				
				this._validate();
			}
		},

		getAnswer: function() {
			var answerObject = {
				id: this.id,
				answer: this.getValue()
			};
			return answerObject;
		},

		waitingAnswer: function(direction){
			this.waiting = direction;
		},

		serverResponse: function(valid, error){
			this.waitingAnswer(false);
			this.completed = valid;
			this.$question.toggleClass('completed', valid);
			this.$question.toggleClass('error', !valid);
			this.showErrorText(error);
		},

		showErrorText: function(error){
			this.$error.text(error || '');
		},

		focus: function(){
			this.$input.first().focus();
		},

		autoConfirmType: function(){
			return (this.questionType===3 || this.questionType===4);
		},

		show: function(direction){
			this.$input.prop('disabled', !direction);
			if (this.questionType===6){
				// distance with units
				this.$unitsSelect.prop('disabled', !direction);
			}else if (this.questionType===7){
				// date with 'never'
				this.$neverCheckbox.prop('disabled', !direction);
			}
		},

		getValue: function() {
			if (this.multiInputs){
				if(this.questionType===16){
					return this._getValueType16();
				}
				if(this.questionType===15){
					return this._getValueType15();
				}
				if(this.questionType===22){
					//hora
					var values = [];
					var $inputs = this.$input;
					$inputs.each(function(index, element){
						if(values.length>0) values+="-";
						values+=$(element).val();
					});
					return values;
				}
				var type = this.$input.attr('type');
				var $checked = this.$input.filter(':checked');
				if (type==='checkbox'){
					var values = [];
					$checked.each(function(index, element){
						values.push($(element).val());
					});
					return values;
				}else if (type==='radio'){
					if (this.questionType===13){
						return this._getValueType13();
					}else if (this.questionType===14){
						return this._getValueType14();
					}else{
						return $checked.val();
					}
				} 
			}else{
				if (this.questionType===6){
					return this.$input.val() + this.$unitsSelect.val();
				}else if (this.questionType===2){
					// distance with units
					return this._getValueType2();
				}else if (this.questionType===7){
					return this._getValueType7();
				}else{
					return this.$input.val();
				}
			}
		},

		_getId: function() {
			return this.$question.attr('id').slice(2);
		},

		_hasError: function() {
			return this.$question.hasClass('error');
		},

		_validate: function(){
			this.valid = this._isValid();
			if (this.valid) {
				this.$question.toggleClass('error', false);
				this.showErrorText();
				this.$error.addClass('error_ok');
				this.$error.removeClass('error_text');
			}else{
				this.showErrorText("");//Aca puede ir un mensaje
				this.$error.removeClass('error_ok');
				this.$error.addClass('error_text');
			}
			this._trigger('validated', null, {question: this, $question: this.$question});
		},

		_initComplexQuestion: function(){
			var question = this;
			var initialValue = this.$input.attr('value');
			if (this.questionType===6){
				// distance with units
				var miles = {'en': 'miles', 'es': 'millas', 'pt': 'milhas'}[window.assessmentApp.lang];
				var kmts = {'en': 'kilometers', 'es': 'kil&oacute;metros', 'pt': 'quil&ocirc;metros'}[window.assessmentApp.lang];
				this.$unitsSelect = $('<select class="units"><option value="k">' + kmts + '</option><option value="m">' + miles + '</option></select>');
				this.$input.after(this.$unitsSelect);
				if(initialValue){
					this.$unitsSelect.val(initialValue.slice(-1));
					this.$input.val(initialValue.slice(0,-1));
				}else{
					if(window.assessmentApp.lang === 'en'){
						this.$unitsSelect.val('m');
					}
				}
			}else if (this.questionType===7){
				// date with 'never'
				var never = {'en': 'Never', 'es': 'Nunca', 'pt': 'Nunca'}[window.assessmentApp.lang];
				var $neverLabel = $('<label class="checkbox"><input class="never" type="checkbox">' + never + '</label>');
				this.$neverCheckbox = $neverLabel.find('input.never');
				this.$neverCheckbox.on('change', function($event, element){
					question._changeNeverCheckbox($event, element);
				});
				this.$input.after($neverLabel);
				if(initialValue === '0'){
					this.$input.val('');
					this.$neverCheckbox.attr('checked', true).change();
				}
			}
		},

		_isValid: function(){
			var valid;
			if (this.multiInputs){
				if (this.questionType===22){
					return true;
					                   
				}
				var type = this.$input.attr('type');
				if (type==='checkbox'){
					valid = true;
				}else if (type==='radio'){
					valid = this.$input.is(":checked");
				}
			}else{
				if (this.questionType===9){
					valid = this._validateEmail(this.$input.val());
				}else if (this.questionType===7){
					valid = !!this._getValueType7();
				}else{
					valid = !!this.$input.val();
				}
			}
			this.$question.toggleClass('valid', valid);
			return valid;
		},

		_getValueType2: function(){
			var plainValue = this.$input.val();
			return this._getIsoDateString(plainValue);
		},

		_getValueType7: function(){
			var never = this.$neverCheckbox.is(":checked");
			if (never){
				return '0';
			}else{
				var plainValue = this.$input.val();
				return this._getIsoDateString(plainValue);
			}
		},

		isNoSelected: function(){
			var $radioButtons = this.$input.filter('[type="radio"]');
			return ($radioButtons.eq(0).filter(':checked').length === 0 && $radioButtons.eq(1).filter(':checked').length === 1);
		},

		_getValueType13: function(){
			var $checked = this.$input.filter(':checked');
			var checkboxValue = $checked.val();
			var textValue = this.$input.filter('[type="text"]').val();
			return checkboxValue + '-' + textValue;
		},

		_getValueType14: function(){
			var $checked = this.$input.filter(':checked');
			var checkboxValue = $checked.val();
			var plainDateValue = this.$input.filter('[type="text"],[type="date"]').val();
			return checkboxValue + '-' + this._getIsoDateString(plainDateValue);
		},
		
		_getValueType15: function(){
			//Caso dos radio, un checkbox y comentario
			var $checked = this.$input.filter(':checked');
			var checkboxValue ="";
			$checked.each(
					function(){
						checkboxValue=checkboxValue+$(this).val()+"#";
					}
				);
			var textValue = this.$input.filter('[type="text"]').val();
			
			var ids=this.id;
			 $("#confirm_pb").show();
			 $('.loader').show();
			if(this.foto){
				var count=0;
				
				this.$input.filter('[tag="fotoSrc"]').each(
						function(){
							if($(this).attr('foto')){
								var imgSrcBase64=$(this).attr('fotoSrc');
								if(imgSrcBase64 && imgSrcBase64!="undefined"){
									 count++;
									 $("#confirm_pb").html("<strong>Uploading "+count+" image</strong>");
									 fd = new FormData();
									 xhr = new XMLHttpRequest();
									 fd.append('contents', imgSrcBase64);
									 fd.append('id', ids);
									 xhr.open('POST', 'Copy.do',false);
									 xhr.send(fd);
								
									
								}
							}
						}
				);
				
				this.$input.filter('[tag="fotoSrc2"]').each(
						function(){
							if($(this).attr('foto')){
								var imgSrcBase64=$(this).attr('fotoSrc');
								if(imgSrcBase64 && imgSrcBase64!="undefined"){
									 count++;
									 $("#confirm_pb").html("<strong>Uploading "+count+" image</strong>");
									 fd = new FormData();
									 xhr = new XMLHttpRequest();
									 fd.append('contents', imgSrcBase64);
									 fd.append('id', ids);
									 xhr.open('POST', 'Copy.do',false);
									 xhr.send(fd);
								
									
								}
							}
						}
				);
				
				
				 
				
			}
			 $("#confirm_pb").html("<strong>Uploading checklist information</strong>");
			return checkboxValue + textValue;
		},

		_getValueType16: function(){
			//Caso dos radio, un checkbox y comentario
			var $checked = this.$input.filter(':checked');
			var checkboxValue ="";
			$checked.each(
					function(){
						checkboxValue=checkboxValue+$(this).val()+"##";
					}
				);
			$checked = this.$input.filter('.select');
			$checked.each(
					function(){
						checkboxValue=checkboxValue+$(this).val()+"##";
					}
				);
			var ids=this.id;
			 $('.loader').show();
			if(this.foto){
				var count=0;
				this.$input.filter('[tag="fotoSrc"]').each(
						function(){
							if($(this).attr('foto')){
								var imgSrcBase64=$(this).attr('fotoSrc');
								if(imgSrcBase64 && imgSrcBase64!="undefined"){
									 count++;
									 fd = new FormData();
									 xhr = new XMLHttpRequest();
									 fd.append('contents', imgSrcBase64);
									 fd.append('id', ids);
									 xhr.open('POST', 'Copy.do',false);
									 xhr.send(fd);
								}
							}
						}
				);
				
			}
			
			this.$input.filter('[tag="fotoSrc2"]').each(
					function(){
						if($(this).attr('foto')){
							var imgSrcBase64=$(this).attr('fotoSrc');
							if(imgSrcBase64 && imgSrcBase64!="undefined"){
								 count++;
								 $("#confirm_pb").html("<strong>Uploading "+count+" image</strong>");
								 fd = new FormData();
								 xhr = new XMLHttpRequest();
								 fd.append('contents', imgSrcBase64);
								 fd.append('id', ids);
								 xhr.open('POST', 'Copy.do',false);
								 xhr.send(fd);
							
								
							}
						}
					}
			);
			
			
			
			var textValue = this.$input.filter('[type="text"]').val();
			return checkboxValue + textValue;
		},

		_getIsoDateString: function(plainValue){
			var day,month,year,isoDateString;
			var humanDateFormatParts = plainValue.split('/');
			if (humanDateFormatParts.length === 3){
				year = humanDateFormatParts[2];
				if (window.assessmentApp.lang === 'en'){
					month = humanDateFormatParts[0];
					day = humanDateFormatParts[1];
				}else{
					day = humanDateFormatParts[0];
					month = humanDateFormatParts[1];
				}
				isoDateString = year + '-' + month + '-' + day;
			}else{
				isoDateString = plainValue;
			}
			return isoDateString;
		},

		_changeNeverCheckbox: function($event, element){
			var never = this.$neverCheckbox.is(":checked");
			this.$input.prop('type', never ? 'text' : 'date');
			this.$input.val(never ? this.$neverCheckbox.parent().text() : '');
			this.$input.prop('disabled', never);
			this._validate();
		},

		_testComplete: function() {
			var testValidValue = this._getTestValidValue();
			this.$input.filter(':not("[type="checkbox"],[type="radio"]")').val(testValidValue);
			this._validate();
		},

		_getTestValidValue: function() {
			var testValidValue = 'Test';
			if (this.questionType===2 || this.questionType===7 || this.questionType===12 || this.questionType===14 ){
				testValidValue = {'en': '10/31/1976', 'es': '31/10/1976', 'pt': '31/10/1976'}[window.assessmentApp.lang];
			}
			if (this.questionType===3 || this.questionType===8 || this.questionType===13 || this.questionType===14 ){
				this.$input.eq(0).attr('checked', true);
			}else if (this.questionType===4 || this.questionType===10){
				// select
				testValidValue = this.$input.find('option').eq(1).attr('value');
			}else if (this.questionType===6){
				// distance with units
				testValidValue = 100;
			}else if (this.questionType===9){
				testValidValue = 'test@server.com';
			}
			return testValidValue;
		},

		_validateEmail: function(email){
			var re = /^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;
			return re.test(email);
		}

	});

})(jQuery);
