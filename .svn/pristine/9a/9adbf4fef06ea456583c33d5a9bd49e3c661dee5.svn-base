package presentation.translator.web.user;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.driver.DriverReportFacade;

import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.MailSender;

public class FleetDriverUserCreateAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm createForm, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm createData = (DynaActionForm) createForm;
        
        String driverId = createData.getString("driver");
        String language = createData.getString("language");
        	
        DriverReportFacade driverReport = sys.getDriverReportFacade();
        DriverData driver = driverReport.findDriverByPrimaryKey(new Integer(driverId),sys.getUserSessionData());
        String user = sys.getUserABMFacade().userFleetDriverCreate(driver,language,sys.getUserSessionData());
/*        
        MailSender sender = new MailSender();
        String mail = getMailBrazil(user,language);
        String title = getTitleBrazil(language);
        sender.send(driver.getEmail(),"smtp.cepasafedrive.com","CEPA Data Center","datacenter@cepasafedrive.com","$Dc&28a$",title,mail);
*/        
        return mapping.findForward("next");
                
    }

    private String getTitleBrazil(String language) {
    	if(language.equals("es")) {
    		return "Ingreso de odometro en Data Center";
    	}else {
            return "Atenção Novo Ingresso de Quilometragem - Data Center";
    	}
    }

    public String getMailBrazil(String user,String language) {
        String mail = "";
        if(language.equals("es")) {
	        mail = "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
	        "<span style='font-family:Arial;'> "+
	        "Estimado Usuario: "+
	        "</span> "+
	        "</p> "+
	        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
	        "<span style='font-family:Arial;'> "+
	        "Ahora usted puede ingresar su odometro. "+
	        "</span> "+
	        "</p> "+
	        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
	        "<span style='font-family:Arial;'> "+
	        "<b>Para esto debe hacer click aqui:</b>  <a href='https://www.cepadc.com/datacenter/home.jsp?login="+user+"'>aqui</a> "+
	        "</span> "+
	        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
	        "</p> "+
	        "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
	        "<span style='font-family:Arial;'> "+
	        "Atentamente "+
	        "</span> "+
	        "</p> "+
	        "--  "+
	        "<br> "+
	        "<br> "+
	        "<span style='font-size:10.0pt;font-family:Arial;color:navy'>.................................................</span> "+
	        "<br> "+
	        "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Simone Cuba</span></b></em> "+
	        "<br> "+
	        "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Dpto. Data Center</span></b></em> "+
	        "<br> "+
	        "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:blue;mso-ansi-language: "+
	        "EN-US'>CEPA BR</span></b></em> "+
	        "<br> "+
	        "<strong> "+
	        "<span lang=EN-US style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language: "+
	        "EN-US'>Telefone:</span></strong><span class=grame><b><span lang=EN-US "+
	        "style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp; "+
	        "(</span></b></span> "+
	        "<strong> "+
	        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>55 12<span class=GramE>)&nbsp;&nbsp;2139 1979</span><br> "+
	        "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1960</span> "+
	        "</strong> "+
	        "<span lang=EN-US style='mso-ansi-language:EN-US'></span> "+
	        "<br> "+
	        "<strong><span "+
	        "lang=EN-US style='font-size:7.5pt;font-family:Verdana;mso-ansi-language:EN-US; "+
	        "font-weight:normal'><a href='mailto:gmarini@cepasafedrive.com'>gmarini@cepasafedrive.com</a></span></strong><span "+
	        "lang=EN-US style='font-size:10.0pt;font-family:Arial;mso-ansi-language:EN-US'> </span><span "+
	        "lang=EN-US style='mso-ansi-language:EN-US'></span> "+
	
	        "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
	         "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
	         "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
	          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
	          "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
	          "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
	          "</td> "+
	          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
	          "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
	          "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
	          "</td> "+
	          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
	          "<div class=MsoNormal align=center style='text-align:center'><span "+
	          "style='font-size:7.5pt'> "+
	          "<hr size=2 width='100%' align=center> "+
	          "</span></div> "+
	          "<div align=center> "+
	          "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
	          " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
	           "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
	           "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
	            "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
	            "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
	            "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
	            "The information contained in this e-mail and any accompanying documents is "+
	            "confidential, may be privileged. It is intended solely for the person "+
	            "and/or entity to whom it is addressed (i.e. those identified in the "+
	            "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
	            "the property of CEPA International LLC, its group companies, or clients. "+
	            "Unauthorized review, use, disclosure, or copying of this communication, or "+
	            "any part thereof, is strictly prohibited and may be unlawful. If you have "+
	            "received this e-mail in error, please return it and any attachment to the "+
	            "sender and delete the e-mail and attachments and any copy from your system. "+
	            "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
	            "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
	            "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
	            "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
	            "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
	            "<p align=center style='text-align:center'>&nbsp;</p> "+
	            "</td> "+
	           "</tr> "+
	          "</table> "+
	          "</div> "+
	          "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
	          "</td> "+
	         "</tr> "+
	        "</table> ";
        }else {
        	mail = "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
            "<span style='font-family:Arial;'> "+
            "Caro Usu&aacute;rio: "+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "Atrav&eacute;s do Data Center voc&ecirc; j&aacute; reporta os eventos com os ve&iacute;culos e agora tamb&eacute;m ingressar&aacute; sua Quilometragem Mensalmente com facilidade. "+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "O sistema estar&aacute; dispon&iacute;vel para ingressar a Quilometragem Mensalmente at&eacute; o dia 08."+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "<b>Para isso basta clicar no link:</b>  <a href='https://www.cepadc.com/datacenter/home.jsp?login="+user+"'>aqui</a> "+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "Ap&oacute;s clicar sobre o link, voc&ecirc; ser&aacute; direcionado ao Data Center para ingressar os dados da sua quilometragem."+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "Siga os passos abaixo:"+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- No Data Center clicar sobre a placa do seu ve&iacute;culo;"+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- No campo \"Od&ocirc;metro Atual\"  ingressar o od&ocirc;metro atual do seu ve&iacute;culo (neste campo ingressar os dados da quilometragem acumulada percorrida pelo ve&iacute;culo) sem pontos e virgulas;"+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- No campo \"N&ordm; de Acidentes\" ingressar o n&uactue;mero total de acidentes ocorridos no m&ecirc;s;"+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- No campo \"N&ordm; de Incidentes\" ingressar o n&uactue;mero total de incidentes ocorridos no m&ecirc;s;"+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- Para finalizar clicar em \"Aceitar\""+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "- O Data Center exibir&aacute; uma mensagem confirmando o ingresso dos dados."+
            "</span> "+
            "</p> "+
            "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'> "+
            "<span style='font-family:Arial;'> "+
            "Atentamente "+
            "</span> "+
            "</p> "+
            "--  "+
            "<br> "+
            "<br> "+
            "<span style='font-size:10.0pt;font-family:Arial;color:navy'>.................................................</span> "+
            "<br> "+
            "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Simone Cuba</span></b></em> "+
            "<br> "+
            "<em><b><span style='font-size:10.0pt;font-family:Verdana;color:navy'>Dpto. Data Center</span></b></em> "+
            "<br> "+
            "<em><b><span lang=EN-US style='font-size:10.0pt;font-family:Verdana;color:blue;mso-ansi-language: "+
            "EN-US'>CEPA BR</span></b></em> "+
            "<br> "+
            "<strong> "+
            "<span lang=EN-US style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language: "+
            "EN-US'>Telefone:</span></strong><span class=grame><b><span lang=EN-US "+
            "style='font-size:7.5pt;font-family:Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp; "+
            "(</span></b></span> "+
            "<strong> "+
            "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>55 12<span class=GramE>)&nbsp;&nbsp;2139 1979</span><br> "+
            "<span lang=EN-US style='font-size:7.5pt;font-family: Verdana;color:navy;mso-ansi-language:EN-US'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1960</span> "+
            "</strong> "+
            "<span lang=EN-US style='mso-ansi-language:EN-US'></span> "+
            "<br> "+
            "<strong><span "+
            "lang=EN-US style='font-size:7.5pt;font-family:Verdana;mso-ansi-language:EN-US; "+
            "font-weight:normal'><a href='mailto:gmarini@cepasafedrive.com'>gmarini@cepasafedrive.com</a></span></strong><span "+
            "lang=EN-US style='font-size:10.0pt;font-family:Arial;mso-ansi-language:EN-US'> </span><span "+
            "lang=EN-US style='mso-ansi-language:EN-US'></span> "+

            "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
             "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
             "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
              "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
              "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
              "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
              "</td> "+
              "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
              "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
              "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
              "</td> "+
              "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
              "<div class=MsoNormal align=center style='text-align:center'><span "+
              "style='font-size:7.5pt'> "+
              "<hr size=2 width='100%' align=center> "+
              "</span></div> "+
              "<div align=center> "+
              "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
              " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
               "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
               "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
                "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
                "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
                "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
                "The information contained in this e-mail and any accompanying documents is "+
                "confidential, may be privileged. It is intended solely for the person "+
                "and/or entity to whom it is addressed (i.e. those identified in the "+
                "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
                "the property of CEPA International LLC, its group companies, or clients. "+
                "Unauthorized review, use, disclosure, or copying of this communication, or "+
                "any part thereof, is strictly prohibited and may be unlawful. If you have "+
                "received this e-mail in error, please return it and any attachment to the "+
                "sender and delete the e-mail and attachments and any copy from your system. "+
                "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
                "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
                "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
                "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
                "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
                "<p align=center style='text-align:center'>&nbsp;</p> "+
                "</td> "+
               "</tr> "+
              "</table> "+
              "</div> "+
              "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
              "</td> "+
             "</tr> "+
            "</table> ";

        }
        return mail;
    }
}