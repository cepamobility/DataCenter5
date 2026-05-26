package business.alert;

import java.io.Serializable;

/**
 * CLASE Obsoleta, no se usa mas, las alertas de odometros estan 
 * en RememberAlertHandler. !! Junio 2010
 * 
 *
 */
public class OdometerAlert implements Serializable{

//	public OdometerAlert() {
//		Calendar instance = Calendar.getInstance();
//		instance.add(Calendar.MONTH, -1);
//		String sql = "SELECT DISTINCT v.matricula,d.firstname,d.lastname,d.email,o.fleet " +
//				"FROM vehicles v " +
//				"JOIN drivers d on v.driver = d.id " +
//				"JOIN odometers o on o.vehicle = v.id " +
//				"WHERE o.fleet IN (9398,9400) " +
//				"AND v.id NOT IN (SELECT vehicle FROM odometers WHERE period = "
//				+String.valueOf(instance.get(Calendar.MONTH)+1)+" AND year = "
//				+instance.get(Calendar.MONTH)+" AND fleet IN (9398,9400))";
//		try {
//			MailSender sender = new MailSender();
//
//			Class.forName("org.postgresql.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter","postgres","pr0v1s0r1A");
//			Statement st = conn.createStatement();
//			ResultSet set = st.executeQuery(sql);
//			while(set.next()) {
//				String email = set.getString("email");
//				if(email != null && email.length() > 0) {
//					String plate = set.getString("matricula");
//					String driver = set.getString("firstname")+" "+set.getString("lastname");
//				
//					String text = (set.getInt("fleet") == 9398) ? getSpanishMail(plate,driver) : getPortugueseMail(plate,driver);
//					String title = (set.getInt("fleet") == 9398) ? "Falta odometro" : "Falta odometer";
//					
//					// TODO: Si no esta en servidor de produccion usar
//					// un email temporal !
//					email = "gbenaderet@cepasafedrive.com";
//					sender.send(email,"smtp.cepasafedrive.provisorio.ws","CEPA Data Center",
//							"datacenter@cepasafedrive.com","$Dc&28a$",title,text,AlertConstants.BCC);
//				}  
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private String getPortugueseMail(String plate, String driver) {
//		return null;
//	}
//
//	private String getSpanishMail(String plate, String driver) {
//        String text = "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
//        "<span style='font-family:Arial;'> "+
//        "Estimado Usuario: "+
//        "</span> "+
//        "</p> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "<span style='font-family:Arial;'> "+
//        "Su password de acceso al Data Center ha sido creado. "+
//        "</span> "+
//        "</p> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "<span style='font-family:Arial;'> "+
//        "<b>URL:</b>  <a href='https://www.cepadc.com'>https://www.cepadc.com</a> "+
//        "</span> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "</p> "+
//        "<span style='font-family:Arial;'> "+
//        "<b>Username:</b> "+driver+
//        "</span> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "</p> "+
//        "<span style='font-family:Arial;'> "+
//        "<b>Password: </b> "+plate+
//        "</span> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "</p> "+
//        "<span style='font-family:Arial;'> "+
//        "Le sugerimos que no copie y pegue el usuario y contrase&ntilde;a directamente sino que los escriba (a veces se agregan o quitan espacios que no permiten ingresar al sistema correctamente)."+
//        "</span> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "</p> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "</p> "+
//        "<span style='font-family:Arial;'> "+
//        "Online encontrar&aacute; un Manual de Usuario Data Center. "+
//        "</span> "+
//        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
//        "<span style='font-family:Arial;'> "+
//        "Atentamente "+
//        "</span> "+
//        "</p> "+
//        "--  "+
//        "<br> "+
//        "<br> "+
//        "<span style='font-size:10.0pt;font-family:Arial;color:navy'>.................................................</span> "+
//        "<br> "+
//        "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Emmanuel Devoto</span></b></em> "+
//        "<br> "+
//        "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Data Center Manager</span></b></em> "+
//        "<br> "+
//        "<em><b><span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'> DataCenter&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
//        "</span></b></em> "+
//        "<br> "+
//        "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:blue;mso-ansi-language: "+
//        "EN-US'>CEPA  International</span></b></em> "+
//        "<br> "+
//        "<strong> "+
//        "<span lang=EN-US style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language: "+
//        "EN-US'>Phone:</span></strong><span class=grame><b><span lang=EN-US "+
//        "style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp; "+
//        "(</span></b></span> "+
//        "<strong> "+
//        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>598<span class=GramE>)(</span>2)&nbsp;&nbsp;518 55 28 int 203</span><br> "+
//        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;518 55 29 int 203</span> "+
//        "</strong> "+
//        "<span lang=EN-US style='mso-ansi-language:EN-US'></span> "+
//        "<br> "+
//        "<strong><span "+
//        "lang=EN-US style='font-size:7.5pt;font-family:Verdana;mso-ansi-language:EN-US; "+
//        "font-weight:normal'><a href='mailto:edevoto@cepasafedrive.com'>edevoto@cepasafedrive.com</a></span></strong><span "+
//        "lang=EN-US style='font-size:10.0pt;font-family:Arial;mso-ansi-language:EN-US'> </span><span "+
//        "lang=EN-US style='mso-ansi-language:EN-US'></span> "+
//
//        "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
//         "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
//         "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
//          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
//          "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
//          "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
//          "</td> "+
//          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
//          "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
//          "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
//          "</td> "+
//          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
//          "<div class=MsoNormal align=center style='text-align:center'><span "+
//          "style='font-size:7.5pt'> "+
//          "<hr size=2 width='100%' align=center> "+
//          "</span></div> "+
//          "<div align=center> "+
//          "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
//          " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
//           "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
//           "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
//            "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
//            "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
//            "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
//            "The information contained in this e-mail and any accompanying documents is "+
//            "confidential, may be privileged. It is intended solely for the person "+
//            "and/or entity to whom it is addressed (i.e. those identified in the "+
//            "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
//            "the property of CEPA International LLC, its group companies, or clients. "+
//            "Unauthorized review, use, disclosure, or copying of this communication, or "+
//            "any part thereof, is strictly prohibited and may be unlawful. If you have "+
//            "received this e-mail in error, please return it and any attachment to the "+
//            "sender and delete the e-mail and attachments and any copy from your system. "+
//            "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
//            "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
//            "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
//            "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
//            "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
//            "<p align=center style='text-align:center'>&nbsp;</p> "+
//            "</td> "+
//           "</tr> "+
//          "</table> "+
//          "</div> "+
//          "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
//          "</td> "+
//         "</tr> "+
//        "</table> ";
//        return text;
//	}

}
