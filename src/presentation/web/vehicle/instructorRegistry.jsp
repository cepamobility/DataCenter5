<!doctype html>


<%@page language="java" 
	errorPage="../exception.jsp" 
	import="business.*"
%>
<%@page import="communication.administration.user.UserSessionData"%>
<%@page import="communication.language.Text"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="presentation.translator.web.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"
        prefix="html"
%>
<%
	SystemAccess sys = (SystemAccess)session.getAttribute("SystemAccess");
	UserSessionData userSessionData = sys.getUserSessionData();
	Text messages = sys.getText();      
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String dateString = sdf.format(date);
%>

<html lang="en">
	<head>
		<meta charset="iso-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=EDGE" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="../images/apple-touch-icon-57x57-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../images/apple-touch-icon-72x72-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/apple-touch-icon-114x114-precomposed.png" />
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../images/apple-touch-icon-144x144-precomposed.png" />
		<meta name="viewport" content="user-scalable=no, width=980" />
		<meta name="description" content="">
		<meta name="keywords" content="">
		<title>Registro de Actividad</title>
		<link rel="shortcut icon" type="../image/ico" href="../images/favicon.ico">
		<link rel="stylesheet" href="../styles/fonts/pontano_sans.css">
		<link rel="stylesheet" href="../styles/base.css">
		<style>
			.registryForm { overflow: hidden; border-radius: 15px; width: 100%; max-width: 1024px; background: white; margin: 100px auto; padding-bottom: 20px; box-sizing: border-box; }
			.registryHeader { display: flex; padding: 8px 16px; align-items: center; background: grey; min-height: fit-content; margin-bottom: 10px; }
			.registryHeader h1 { margin: 0; color: white; font-size: 22px; font-weight: 500; }
			.registryForm form { display: flex; flex-direction: column; gap: 20px; }
			.formContainer { display: flex; width: 100%; box-sizing: border-box; }
			.narrow { display: flex; flex-direction: column; gap: 15px; margin-inline: 15px; width: 100%; box-sizing: border-box; min-width: 0; }
			fieldset { border: none; padding: 0; margin: 0; min-width: 0; }
			form label { font-size: 1.4rem; font-weight: 500; color: rgb(36, 36, 36); }
			input[type="text"], input[type="date"], select, textarea {
			  width: 100%; box-sizing: border-box; min-width: 0;
			  border: 1px solid grey;
			  padding: 8px 10px; border: 0.5px solid grey;
			  border-radius: 10px; background: white;
			  color: rgb(36, 36, 36); font-size: 15px;
			}
			input[type="text"]:readonly { background: rgb(184, 184, 184); color: rgb(83, 83, 83); }
			textarea { height: 150px; resize: none; }
			.formButton { display: flex; align-items: center; gap: 20px; margin-inline: 15px; }
			.formButton input { height: 44px; border-radius: 10px; font-size: 15px; cursor: pointer; flex: 1; }
			.formButton input[type="button"] { background: grey; border: 0.5px solid grey; color: white; }
			@media (max-width: 1024px) { 
				form label {font-size: 2rem;}
				.formContainer { flex-direction: column; } 
				.registryForm {height: 100%} 
				.formButton{flex-direction: column;} 
				.narrow{width: auto;} 
				.formButton input[type="button"], .formButton input[type="submit"] {
					width: 100%;
					min-height: 70px;
					font-size: 1.8rem;
				}
				input[type="text"], input[type="date"], select, textarea {
					min-height: 70px;
					font-size: 1.8rem;
				}
				.registryHeader h1 {
					font-size: 2.2rem;
				}
			}
		</style>
	</head>
	<body>
		<header>
			<section class="grid_container">
				<h1 class="customer_logo" style="background-image: url('../menu/<%=Util.corporationLogo(sys.getUserSessionData()) %>');">DC</h1>
				<div class="toolbar">
					<div class="cepa_logo">CEPA Safe Drive</div>
					<span class="username"><%=sys.getUserSessionData().getFilter().getFirstName()+" "+sys.getUserSessionData().getFilter().getLastName() %></span>
					<span class="exit"><a href='../login.jsp'><%=messages.getText("generic.messages.exit") %></a></span>
				</div>
			</section>
		</header>
	
		<div class="registryForm">
		  <div class="registryHeader"><h1>Registro de actividad</h1></div>
		  <html:form action="/instructorActivity" onsubmit="return validateForm()">
		  	<html:hidden property="user" value="<%= userSessionData.getFilter().getLoginName() %>"/>
		    <div class="formContainer">
		      <fieldset class="narrow">
		        <label>Elige una empresa*</label>
		        <select name="corporation">
		          <option value="9">Forestal Oriental</option>
		          <option value="50">Montes del Plata</option>
		          <option value="3">Contratista UPM</option>
		          <option value="4">Contratista MDelP</option>
		        </select>
		      </fieldset>
		      <fieldset class="narrow">
		        <label>Instructor</label>
		        <input type="text" name="instructor" value="<%= userSessionData.getFullName() %>" readonly/>
		      </fieldset>
		    </div>
		    <div class="formContainer">
		      <fieldset class="narrow">
		        <label>Categoría*</label>
		        <select id="category" name="category" onchange="updateSubcat()">
		          <option value="">Seleccionar...</option>
		          <option value="1">Consulta técnica</option>
		          <option value="2">Consulta de caminería/ingreso/salida</option>
		          <option value="3">Consultas varias</option>
		        </select>
		      </fieldset>
		      <fieldset class="narrow">
		        <label>Subcategoría*</label>
		        <select id="subcat" name="subcat"><option value="">Primero elegí categoría</option></select>
		      </fieldset>
		    </div>
		    <div class="formContainer">
		      <fieldset class="narrow">
		        <label>Monte / Ubicación*</label>
		        <input type="text" name="location" placeholder="Ubicación"/>
		      </fieldset>
		      <fieldset class="narrow">
		        <label>Fecha*</label>
		        <input type="date" name="date" value="<%= dateString %>"/>
		      </fieldset>
		    </div>
		    <div class="formContainer">
		      <fieldset class="narrow">
		        <label>Canal de atención*</label>
		        <select name="channel">
		          <option value="1">WhatsApp</option>
		          <option value="2">Llamada telefónica</option>
		          <option value="3">Visita presencial</option>
		          <option value="4">Email</option>
		          <option value="5">Reunión virtual</option>
		        </select>
		      </fieldset>
		      <fieldset class="narrow">
		        <label>Solicitud / Referente*</label>
		        <input type="text" name="referent" placeholder="Nombre del cliente o referente"/>
		      </fieldset>
		    </div>
		    <div class="formContainer">
		      <fieldset class="narrow">
		        <label>Observación(opcional)</label>
		        <textarea name="observation" placeholder="Describí el caso, complejidad y valor aportado..."></textarea>
		      </fieldset>
		    </div>
		    <fieldset class="formButton">
		      <input type="submit" class="button" value="Guardar"/>
		      <input type="button" value="Cancelar" onClick="history.back()"/>
		    </fieldset>
		  </html:form>
		</div>
		
		<script>
			const SUBCATS = {
			  1: [["Falla computadora",1],["Plataforma de carga",2],["Sistema de seguridad",3],["Sistema de frenos",4],["Ejes",5],["Otros",6]],
			  2: [["Consulta de caminería/ingreso/salida",1]],
			  3: [["Consultas varias",1]]
			};
			function updateSubcat() {
			  const cat = document.getElementById("category").value;
			  const sel = document.getElementById("subcat");
			  sel.innerHTML = "";
			  const opts = SUBCATS[cat] || [];
			  if (!opts.length) { sel.innerHTML = '<option value="">Primero elegí categoría</option>'; return; }
			  opts.forEach(([name, val]) => { const o = document.createElement("option"); o.textContent = name; o.value = val; sel.appendChild(o); });
			}
	
			function validateForm() {
				const fields = [
					{ name: "corporation", label: "Empresa" },
			        { name: "subcat",      label: "Subcategoría" },
					{ name: "location",    label: "Monte / Ubicación" },
			        { name: "date",        label: "Fecha" },
			        { name: "channel",     label: "Canal de atención" },
			        { name: "referent",    label: "Solicitud / Referente" },
			        { name: "category",    label: "Categoría" },
				];

				for(const field of fields) {
					const el = document.querySelector(`[name="${field.name}"]`)
					if(!el || !el.value.trim()) {
						alert(`El campo "${field.label}" es obligatorio.`);
			            el?.focus();
			            return false;
					} 
				}

				return true;
			}
		</script>
	</body>
</html>