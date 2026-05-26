package presentation.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.DefaultResourceBundleFactory;
import org.jfree.report.EmptyReportException;
import org.jfree.report.JFreeReport;
import org.jfree.report.ReportProcessingException;
import org.jfree.report.modules.output.pageable.base.OutputTargetException;
import org.jfree.report.modules.output.pageable.base.PageableReportProcessor;
import org.jfree.report.modules.output.pageable.pdf.PDFOutputTarget;
import org.jfree.report.modules.output.table.html.HtmlProcessor;
import org.jfree.report.modules.output.table.html.StreamHtmlFilesystem;
import org.jfree.report.modules.output.table.xls.ExcelProcessor;

import communication.alert.AlertConstants;
import communication.util.LastSearch;

public class ReportUtil {

	
	
    public ReportUtil() {
    }
    
    /**
     * @param mapping
     * @param response
     * @param report
     * @param outputFormat
     * @return
     * @throws IOException
     * @throws OutputTargetException
     * @throws ReportProcessingException
     * @throws EmptyReportException
     */
    public ActionForward executeReport(ActionMapping mapping, HttpServletResponse response, 
    		JFreeReport report, String outputFormat,HttpSession session, String name, boolean save) throws Exception {
    	
    	if (report != null){
    		report.setResourceBundleFactory(new DefaultResourceBundleFactory(new Locale("en","US")));
    	}
    	
        try{
        	
        	if (outputFormat.equals("PDFFile")) {
		       	
        		
	    		// PDF Report        			
        		File f = new File(name);	
        		FileOutputStream out = new FileOutputStream(f);
	    		
	    		final PDFOutputTarget target = new PDFOutputTarget(out);
	    		target.configure(report.getReportConfiguration());
	    		
	    		final PageableReportProcessor proc = new PageableReportProcessor(report);
	    		proc.setOutputTarget(target);
	    		target.open(); 
	    		proc.processReport(); // <----
	    		target.close();
	    		out.flush();
	    		out.close();        	
	    		
	    		// return mapping.findForward("report");
	    	
	    		return null;
        	}else if (outputFormat.equals("PDF")) {
    		       	
    		
	    		// PDF Report        			
	    		final ServletOutputStream out = response.getOutputStream();
	    		response.setHeader("Content-Type", "application/pdf");
	    		response.setHeader("Content-Disposition","inline; filename=\""+name+".pdf\"");
	    		
	    		final PDFOutputTarget target = new PDFOutputTarget(out);
	    		target.configure(report.getReportConfiguration());
	    		
	    		final PageableReportProcessor proc = new PageableReportProcessor(report);
	    		proc.setOutputTarget(target);
	    		target.open(); 
	    		proc.processReport(); // <----
	    		target.close();
	    		out.flush();
	    		out.close();        	
	    		
	    		// return mapping.findForward("report");
	    	
	    		return null;
            
        	}else if (outputFormat.equals("XLS")) {
        	
	        	//Excel Report            
	        	final ServletOutputStream out = response.getOutputStream();
	            response.setHeader("Content-Type", "application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "inline; filename=\""+name+".xls" + "\"");
	            final ExcelProcessor processor = new ExcelProcessor(report);
	            
	            processor.setOutputStream(out);
	            processor.processReport();
	            out.flush();
	            out.close();
	            return null;
	                
	        }else {
	        	// HTML Report            	
	            final HtmlProcessor processor = new HtmlProcessor(report);
	            ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
	            processor.setFilesystem(new StreamHtmlFilesystem(ous));
	            processor.processReport();  
	            
	            String html = textToHtml(ous.toString());
                byte[] bytes = html.getBytes();
                	            
	            session.setAttribute("resp",bytes);
	            session.setAttribute("arrayGraficas",null);
	            session.setAttribute("tituloReporte", null);
	            session.setAttribute("subTituloReporte",null);
	            session.setAttribute("conLogos",null);
	            if(save) {
	                session.setAttribute("lastQuery",new LastSearch(bytes));
	            }
	            ous.flush();
	            ous.close();
	            return mapping.findForward("report");   
	            
	        }
        }catch(Exception e){
        	e.printStackTrace();
        	throw e;
        }
    }
    /**
     * @param mapping
     * @param response
     * @param report
     * @param outputFormat
     * @return
     * @throws IOException
     * @throws OutputTargetException
     * @throws ReportProcessingException
     * @throws EmptyReportException
     */
    public ActionForward executeReport(ActionMapping mapping, HttpServletResponse response, 
    		JFreeReport report, String outputFormat,HttpSession session, 
    		String name, ArrayList arrayGraficas, boolean save) throws Exception {        
    	if (outputFormat.equals("PDF")) {
    		// PDF Report
            final ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Disposition","inline; filename=\""+name+".pdf\"");
            PDFOutputTarget target = new PDFOutputTarget(out);
            target.configure(report.getReportConfiguration());
            target.open();
            PageableReportProcessor proc = new PageableReportProcessor(report);
            proc.setOutputTarget(target);
            proc.processReport();
            target.close();
            out.flush();
            out.close();
            return null;
        }else {
            if (outputFormat.equals("XLS")) {
            	// Excel Report
                final ServletOutputStream out = response.getOutputStream();
                response.setHeader("Content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=\""+name+".xls" + "\"");
                final ExcelProcessor processor = new ExcelProcessor(report);
                processor.setOutputStream(out);
                processor.processReport();
                out.flush();
                out.close();
                return null;
            } else {
            	// HTML Report            	
                final HtmlProcessor processor = new HtmlProcessor(report);
                for(int i = 0; i <= report.getData().getColumnCount() - 1; i++)
                {
//                	System.out.println("Columna :"+i+" Nombre:"+report.getData().getColumnName(i));
                }
                ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
                processor.setFilesystem(new StreamHtmlFilesystem(ous));
                processor.processReport();                
                
                
                String html = textToHtml(ous.toString());
                byte[] bytes = html.getBytes();
                
                session.setAttribute("resp",bytes);
                session.setAttribute("arrayGraficas", arrayGraficas);                
                session.setAttribute("tituloReporte", null);
                session.setAttribute("subTituloReporte",null);
                session.setAttribute("conLogos",null);            
                if(save) {
                    session.setAttribute("lastQuery",new LastSearch(bytes,arrayGraficas));
                }
                return mapping.findForward("report");    
            }
        }
    }    	    

    /**
     * @param mapping
     * @param response
     * @param report
     * @param outputFormat
     * @return
     * @throws IOException
     * @throws OutputTargetException
     * @throws ReportProcessingException
     * @throws EmptyReportException
     */
    public ActionForward executeReport(ActionMapping mapping, HttpServletResponse response, 
    		JFreeReport report, String outputFormat,HttpSession session, String name, 
    		String titulo, String subtitulo, boolean conLogos) throws Exception {  

    	if (outputFormat.equals("PDF")) {
    		
    		try{
	    		// PDF Report
	            final ServletOutputStream out = response.getOutputStream();
	            response.setHeader("Content-Type", "application/pdf");
	            response.setHeader("Content-Disposition","inline; filename=\""+name+".pdf\"");           
	            
	            PDFOutputTarget target = new PDFOutputTarget(out);
	            target.configure(report.getReportConfiguration());
	            target.open();
	            PageableReportProcessor proc = new PageableReportProcessor(report);            
	            proc.setOutputTarget(target);
	            proc.processReport();
	            target.close();
	            out.flush();
	            out.close();
    		}catch(Exception e){
    			e.printStackTrace();
    			//throw e;
    		}
    		
	        return null;
            
        }else {
            if (outputFormat.equals("XLS")) {
            	// Excel Report
                final ServletOutputStream out = response.getOutputStream();
                response.setHeader("Content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=\""+name+".xls" + "\"");
                final ExcelProcessor processor = new ExcelProcessor(report);
                processor.setOutputStream(out);
                processor.processReport();
                out.flush();
                out.close();
                return null;
            } else {
            	// HTML Report            	
                final HtmlProcessor processor = new HtmlProcessor(report);
                ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
                processor.setFilesystem(new StreamHtmlFilesystem(ous));
                processor.processReport();     
                
                String html = textToHtml(ous.toString());
                byte[] bytes = html.getBytes();
                
                session.setAttribute("resp",bytes);
                session.setAttribute("tituloReporte", textToHtml(titulo));
                session.setAttribute("subTituloReporte",textToHtml(subtitulo));
                session.setAttribute("conLogos",(Boolean.toString(conLogos)).trim());
                session.setAttribute("arrayGraficas", null);
                
                return mapping.findForward("report");    
            }
        }
    } 
    
    
    /* Convierte un string con tildes y simbolos raros a html con codificaciÃ³n */
    public static String textToHtml(String s)
    {
    	StringBuffer aux = new StringBuffer();
    	int count = 0;
    	while (count < s.length())
    	{
    		// Por mÃ¡s codigos referise a:
    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		switch (s.charAt(count)){
    			case (char)225: aux.append("&aacute;"); //'Ã¡' 
    				break;
    			case (char)233: aux.append("&eacute;"); //'Ã©' 
    				break;
    			case (char)237: aux.append("&iacute;"); //'Ã­'
    				break;
    			case (char)243: aux.append("&oacute;"); // 'Ã³'
    				break;
    			case (char)250: aux.append("&uacute;"); //'Ãº'
    				break;    			
    			case (char)231: aux.append("&ccedil;"); // 'Ã§'
    				break;
    			case (char)228: aux.append("&auml;"); // 'Ã£'
    				break;
    			case (char)227: aux.append("&atilde;");
    				break;
    			case (char)226: aux.append("&acirc;");
    				break;
    			case (char)229: aux.append("&aring;");
    				break;
    			case (char)196: aux.append("&Auml;");
    				break;
    			case (char)230: aux.append("&aelig;");
    				break;
    			// Masyusculas
    			case (char)193: aux.append("&Aacute;"); //Ã�
    				break;
    			case (char)201: aux.append("&Eacute;");	//Ã‰
    			    break;
    			case (char)205: aux.append("&Iacute;"); //Ã�
    			    break;
    			case (char)211: aux.append("&Oacute;"); //Ã“
    				break;
    			case (char)218: aux.append("&Uacute;"); //Ãš
    				break;
    			case (char)199: aux.append("&Ccedil;"); //Ã‡
    				break;
    			case (char)195: aux.append("&Atilde;"); //Ãƒ
    				break;
    			default: aux.append(String.valueOf(s.charAt(count)));
    				break;
   	    		// Por mÃ¡s codigos referise a:
   	    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		}
    		count ++;
    	}
    	return aux.toString();
    	
    }

    /**
     * @param mapping
     * @param response
     * @param report
     * @param outputFormat
     * @return
     * @throws IOException
     * @throws OutputTargetException
     * @throws ReportProcessingException
     * @throws EmptyReportException
     */
    public byte[] executeReport(HttpServletResponse response,JFreeReport report, String outputFormat,
    		HttpSession session, String name, ArrayList arrayGraficas) throws Exception {        
        if (outputFormat.equals("PDF")) {
            // PDF Report
            final ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Disposition","inline; filename=\""+name+".pdf\"");
            PDFOutputTarget target = new PDFOutputTarget(out);
            target.configure(report.getReportConfiguration());
            target.open();
            PageableReportProcessor proc = new PageableReportProcessor(report);
            proc.setOutputTarget(target);
            proc.processReport();
            target.close();
            out.flush();
            out.close();
            return null;
        }else {
            if (outputFormat.equals("XLS")) {
                // Excel Report
                final ServletOutputStream out = response.getOutputStream();
                response.setHeader("Content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=\""+name+".xls" + "\"");
                final ExcelProcessor processor = new ExcelProcessor(report);
                processor.setOutputStream(out);
                processor.processReport();
                out.flush();
                out.close();
                return null;
            } else {
                // HTML Report              
                final HtmlProcessor processor = new HtmlProcessor(report);
                ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
                processor.setFilesystem(new StreamHtmlFilesystem(ous));
                processor.processReport(); 
                //session.setAttribute("resp",ous.toByteArray());
                session.setAttribute("tituloReporte", null);
                session.setAttribute("subTituloReporte",null);
                session.setAttribute("conLogos",null);
                session.setAttribute("arrayGraficas", arrayGraficas);
                
                String html = textToHtml(ous.toString());
                byte[] bytes = html.getBytes();
                ous.flush();
	            ous.close();
                return bytes;                
            }
        }
    }           
    

    /**
     * Prueba Reportes PDF anidados Gustavo
     * @param mapping
     * @param response
     * @param reports
     * @param outputFormat
     * @param session
     * @param name
     * @param arrayGraficas
     * @return
     * @throws Exception
     */
    public ActionForward executeReport(ActionMapping mapping, HttpServletResponse response, 
    		Collection reports, String outputFormat,HttpSession session, 
    		String name) throws Exception {        
    	

    	JFreeReport report;
    	PageableReportProcessor [] procs;
    	int count = 0;
    	if (outputFormat.equals("PDF")) {
    		// PDF Report
            final ServletOutputStream out = response.getOutputStream();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Disposition","inline; filename=\""+name+".pdf\"");
            PDFOutputTarget target = new PDFOutputTarget(out);
            procs = new PageableReportProcessor[reports.size()];
            Iterator it = reports.iterator();            
            while(it.hasNext()){
            	report = (JFreeReport)it.next();            
            	target.configure(report.getReportConfiguration());
            	if (!target.isOpen()){
            		target.open();
            	}
             	procs[count] = new PageableReportProcessor(report);             	
            	procs[count].setOutputTarget(target);
            	procs[count].processReport();
            	count++;
            }
        	target.close();
            out.flush();
            out.close();
            return null;
    	}
    	return null;
    }

    public byte[] getHtml(JFreeReport report) throws Exception {
        final HtmlProcessor processor = new HtmlProcessor(report);
        ByteArrayOutputStream ous = new ByteArrayOutputStream(); 
        processor.setFilesystem(new StreamHtmlFilesystem(ous));
        processor.processReport();                
        return ous.toByteArray();
    }
}

