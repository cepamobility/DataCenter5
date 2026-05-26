package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeConstants;
import communication.datatype.tables.TypeTradeDataType;
import communication.security.SecurityConstants;
import communication.util.ExcelGenerator;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleOptionalInfoData;

/**
 * @author fcarriquiry
 */
public class UploadUpdateVehicleMDPAction extends AbstractAction {

	HttpSession session=null;
    SystemAccess sys = null;
    ArrayList<ArrayList<String>> vehiclesNotExist=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> platformsNotExist=new ArrayList<ArrayList<String>>();
    ArrayList<String> platforms=new ArrayList<String>();
    ArrayList<String> vehicles=new ArrayList<String>();
    
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        session = request.getSession();
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm formDyna=(DynaActionForm) form;
        
        if(formDyna.get("step").equals("1")){
        	
        	return step1( mapping,  formDyna,  request,  response);
        }else
    	if(formDyna.get("step").equals("dowloadNotExistVehicles")){
    		return dowloadNotExistVehicles(mapping, form,	request, response);
    	}else
    	if(formDyna.get("step").equals("dowloadNotExistPlatforms")){
    		return  dowloadNotExistPlatforms(mapping, form,	request, response);
    	}
    	else
    	if(formDyna.get("step").equals("dowloadTemplate")){
    		return  dowloadTemplate(mapping, form,	request, response);
    	}else
    	if(formDyna.get("step").equals("dowloadVehiclesInDCNotInExcel")){
    		return  dowloadVeiclesInDCNotInExcel(mapping, form,	request, response);
    	}else
    	if(formDyna.get("step").equals("dowloadPlatformInDCNotInExcel")){
    		return  dowloadPlatformInDCNotInExcel(mapping, form,	request, response);
    	}
                
        
	    return null;
	}

	private ActionForward dowloadPlatformInDCNotInExcel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception {

		response.setHeader("Content-Type", "application/xls");
   	 	response.setHeader("Content-Disposition", "filename=\"PlataformasEnDCNoEnExcel.xls\"");
		
   	 	ArrayList<ArrayList<String>> book=new ArrayList<ArrayList<String>>();
	 	
	 	ArrayList<String> header=new ArrayList<String>();
   	 	header.add("CONTRATISTA");
   	 	header.add("MATRICULA");
	 	header.add("MARCA");
	 	header.add("MODELO");
	 	header.add("AÑO");
	 	book.add(header);
		
	 	if(platforms.size()>0){
	 		
	 	}
	 	ArrayList<TrailerData> trailers=sys.getVehicleReportFacade().getTrailersNonIn(platforms, sys.getUserSessionData());
	 	Iterator<TrailerData> iter=trailers.iterator();
	 	
	 	while(iter.hasNext()){
	 		TrailerData trData=iter.next();
	 		ArrayList<String> row=new ArrayList<String>();
	 		Iterator it = trData.getDivOrgsIterator();
			while(it.hasNext()) {
			    DivOrgData divOrgData = (DivOrgData)it.next();
			    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			    row.add(item1.getName());
			}
	 		row.add(trData.getPlate());
	 		row.add(sys.getText().getText(trData.getTrade().getResourceBundleId()));
	 		row.add(trData.getModel());
	 		row.add(trData.getYear()+"");
	 		book.add(row);
	 	}
	 	
		ExcelGenerator.generatorXLS(book, "Plataformas", response.getOutputStream());
		return null;
	}

	private ActionForward dowloadVeiclesInDCNotInExcel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception{

		response.setHeader("Content-Type", "application/xls");
   	 	response.setHeader("Content-Disposition", "filename=\"VehiculosEnDCNoEnExcel.xls\"");
		
   	 ArrayList<ArrayList<String>> book=new ArrayList<ArrayList<String>>();
	 	
	 	ArrayList<String> header=new ArrayList<String>();
	 	header.add("CONTRATISTA");
	 	header.add("MATRICULA");
	 	header.add("MARCA");
	 	header.add("MODELO");
	 	header.add("AÑO");
	 	book.add(header);
		
	 	ArrayList<VehicleData> trailers=sys.getVehicleReportFacade().getVehiclesNonIn(vehicles, sys.getUserSessionData());
	 	Iterator<VehicleData> iter=trailers.iterator();
	 	
	 	while(iter.hasNext()){
	 		VehicleData vData=iter.next();
	 		ArrayList<String> row=new ArrayList<String>();
	 		Iterator it = vData.getDivOrgsIterator();
			while(it.hasNext()) {
			    DivOrgData divOrgData = (DivOrgData)it.next();
			    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			    row.add(item1.getName());
			}
	 		row.add(vData.getMatricula());
	 		row.add(sys.getText().getText(vData.getTrade().getResourceBundleId()));
	 		row.add(vData.getModel());
	 		row.add(vData.getYear()+"");
	 		book.add(row);
	 	}
	 	
		ExcelGenerator.generatorXLS(book, "Vehiculos", response.getOutputStream());
		
		return null;
	}

	private ActionForward dowloadTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		File file=new File(SecurityConstants.DATACENTER5_WAR_DIRECTORY+"/mdp/plantillas/PlantillaActualizacionVehiculosYPlataformasMDP .xls");
		response.setHeader("Content-Type", "application/xls");
   	 	response.setHeader("Content-Disposition", "filename=\"PlantillaActualizacionVehiculosYPlataformasMDP.xls\"");
		
   	 	Util.fileToOutputStream(file, response.getOutputStream());
		
		return null;
	}

	private ActionForward dowloadNotExistPlatforms(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Type", "application/xls");
   	 	response.setHeader("Content-Disposition", "filename=\"PlataformasQueNoExisten.xls\"");
		
   	 	ArrayList<String> header=new ArrayList<String>();
		header.add("MATRICULA");
	 	header.add("MARCA");
	 	header.add("MODELO");
		header.add("SEMIRREMOLQUE");
		header.add("ZORRA");
	 	header.add("AÑO");
	 	header.add("SUSPENSION NEUMATICA (Si/No)");
	 	header.add("ABS (Si/No)");
	 	header.add("CONTROL DE PESO (Si/No)");
	 
	 	platformsNotExist.add(0,header);
	 	
		ExcelGenerator.generatorXLS(platformsNotExist, "Plataformas", response.getOutputStream());
		
		return null;
	}

	private ActionForward dowloadNotExistVehicles(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		response.setHeader("Content-Type", "application/xls");
   	 	response.setHeader("Content-Disposition", "filename=\"VehiculosQueNoExisten.xls\"");
   	 	
		ArrayList<String> header=new ArrayList<String>();
   		header.add("MATRICULA");
   	 	header.add("TRANSPORTISTA");
   	 	header.add("CONDUCTOR DOBLE/SIMPLE");
   	 	header.add("SOPT/LARGO PLAZO");
   	 	header.add("MARCA");
   	 	header.add("MODELO");
   	 	header.add("AÑO");
   	 	header.add("HP");
   	 	header.add("TRACTOR EURO 3 O SUPERIOR (SI/NO)");
   	 	header.add("SUSPENSION NEUMATICA (Si/No)");
   	 	header.add("ABS (Si/No)");
   	 	header.add("CONTROL DE PESO (Si/No)");
   	 
   	 	vehiclesNotExist.add(0,header);
   	 	
		ExcelGenerator.generatorXLS(vehiclesNotExist, "Vehiculos", response.getOutputStream());
		
		
		return null;
	}

	private ActionForward step1(ActionMapping mapping, DynaActionForm form,	HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
		FormFile file=(FormFile)form.get("file");
		System.out.println("FILE:"+file.getFileName()+" SIZE:"+file.getFileSize());
		vehiclesNotExist.clear();
		platformsNotExist.clear();
		platforms.clear();
		vehicles.clear();
		
		try {
			
			ArrayList<ArrayList<String>> rows= Util.completeRows(Util.xlsInputStreamToArray(file.getInputStream()));
			ArrayList<String> messages=new ArrayList<String>();
			int line=1;
			int startLine=1;
			int startLine2=1;
			boolean startData=false;
			ArrayList<ArrayList<String>> dataRows=new ArrayList<ArrayList<String>>();
			for(ArrayList<String> row:rows){
				if(startData){
					if(row.get(1)!=null && row.get(1).trim().length()>0){
						dataRows.add(row);	
					}
				}
				String d="";
				for(String r:row){
					d+=","+r;
					if(r.toUpperCase().contains("TRANSPORTISTA")){
						startLine=line+1;
					}
				}
				System.out.println(line+":"+d);	
				line++;
				if(startLine>1){
					startData=true;
				}
			}
			System.out.println("Primer linea de datos "+startLine);
			
			//Inicio del control de datos
			boolean ok=true;
			startLine2=startLine;
			for(ArrayList<String> row:dataRows){
				//Vehículo
				String vehiclePlate=row.get(1);
				if(vehiclePlate!=null){
					vehiclePlate=vehiclePlate.replaceAll(" ","");
				}
				String vehicleContractor=row.get(2);
				String vehicleMode=row.get(3);
				if(vehicleMode!=null){
					vehicleMode=vehicleMode.trim().toUpperCase();
				}

				String vehicleSL=row.get(4);
				String vehicleTrade=row.get(5);
				String vehicleModel=row.get(6);
				String vehicleYear=row.get(7);
				if(vehicleYear!=null && vehicleYear.contains(".")){
					vehicleYear=vehicleYear.replace(".0", "");
				}
				
				String vehicleHP=row.get(8);
				if(vehicleHP!=null && vehicleHP.contains(".")){
					vehicleHP=vehicleHP.replace(".0", "");
				}
				String vehicleEuro3=row.get(9);
				String vehicleSuspension=row.get(10);
				String vehicleAbs=row.get(11);
				String vehicleWeightControl=row.get(12);
				
				ok=controlVehicle(startLine,messages,vehiclePlate,vehicleContractor,vehicleMode,vehicleSL,vehicleTrade,vehicleModel,vehicleYear,vehicleHP,vehicleEuro3,vehicleSuspension,vehicleAbs,vehicleWeightControl) && ok;
				
				//Plataforma
				String platformPlate=row.get(13);
				if(platformPlate!=null){
					platformPlate=platformPlate.replaceAll(" ","");
				}
				String platformTrade=row.get(14);
				String platformMode=row.get(15);
				String platformSemi=row.get(16);
				String platformZorra=row.get(17);
				String platformYear=row.get(18);
				if(platformYear!=null && platformYear.contains(".")){
					platformYear=platformYear.replace(".0", "");
				}
				
				String platformSusp=row.get(19);
				String platformAbs=row.get(20);
				String platformWeightControl=row.get(21);
				
				ok=controlPlatform(startLine,messages,platformPlate,platformTrade,platformMode,platformSemi,platformZorra,platformYear,platformSusp,platformAbs,platformWeightControl) && ok;
				
				startLine++;
			}
			
			if(ok){
				//Quito las plataformas asociadas
				for(ArrayList<String> row:dataRows){
					//Vehículo
					String vehiclePlate=row.get(1);
					if(vehiclePlate.contains(" ")){
						vehiclePlate=vehiclePlate.replaceAll(" ", "");
					}
					VehicleData data=sys.getVehicleReportFacade().findVehicleByPlate(vehiclePlate, sys.getUserSessionData());
					Collection<TrailerMainData> col= sys.getVehicleReportFacade().getVehicleTrailers(data.getId(), sys.getUserSessionData());
					Collection colTrIds=new ArrayList<String>();
					for(TrailerMainData trData:col){
						colTrIds.add(trData.getId().toString());
					}
					sys.getVehicleABMFacade().desassociateTrailer(data.getId(), colTrIds, userSessionData);
				}
				
				for(ArrayList<String> row:dataRows){
					//Vehículo
					String vehiclePlate=row.get(1);
					if(vehiclePlate!=null){
						vehiclePlate=vehiclePlate.replaceAll(" ","");
					}
					String vehicleContractor=row.get(2);
					String vehicleMode=row.get(3);
					if(vehicleMode!=null){
						vehicleMode=vehicleMode.trim().toUpperCase();
					}
					String vehicleSL=row.get(4);
					String vehicleTrade=row.get(5);
					String vehicleModel=row.get(6);
					String vehicleYear=row.get(7);
					if(vehicleYear!=null && vehicleYear.contains(".")){
						vehicleYear=vehicleYear.replace(".0", "");
					}
					String vehicleHP=row.get(8);
					if(vehicleHP!=null && vehicleHP.contains(".")){
						vehicleHP=vehicleHP.replace(".0", "");
					}
					String vehicleEuro3=row.get(9);
					String vehicleSuspension=row.get(10);
					String vehicleAbs=row.get(11);
					String vehicleWeightControl=row.get(12);
					
					updateVehicle(startLine2,messages,vehiclePlate,vehicleContractor,vehicleMode,vehicleSL,vehicleTrade,vehicleModel,vehicleYear,vehicleHP,vehicleEuro3,vehicleSuspension,vehicleAbs,vehicleWeightControl);
					
					VehicleData data=sys.getVehicleReportFacade().findVehicleByPlate(vehiclePlate, sys.getUserSessionData());
					
					//Plataforma
					String platformPlate=row.get(13);
					if(platformPlate!=null){
						platformPlate=platformPlate.replaceAll(" ","");
					}
					String platformTrade=row.get(14);
					String platformMode=row.get(15);
					String platformSemi=row.get(16);
					String platformZorra=row.get(17);
					String platformYear=row.get(18);
					if(platformYear!=null && platformYear.contains(".")){
						platformYear=platformYear.replace(".0", "");
					}
					String platformSusp=row.get(19);
					String platformAbs=row.get(20);
					String platformWeightControl=row.get(21);
					Integer divItemIdCobtractor=0;
					if(vehicleContractor!=null && vehicleContractor.trim().length()>0){
						divItemIdCobtractor=sys.getUserReportFacade().getDivOrgItemLevel1ID(vehicleContractor, 456435, sys.getUserSessionData());
						
					}
					
					TrailerData trData=updatePlatform(startLine2,messages,divItemIdCobtractor,platformPlate,platformTrade,platformMode,platformSemi,platformZorra,platformYear,platformSusp,platformAbs,platformWeightControl);
					Collection colTrIds=new ArrayList<String>();
					colTrIds.add(trData.getId().toString());
					
					sys.getVehicleABMFacade().associateTrailer(data.getId(),colTrIds , userSessionData);
					
					startLine2++;
				}
				
				request.setAttribute("updatesuccess", "updatesuccess");
			}
			
			request.setAttribute("messages", messages);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return mapping.findForward("step2");
	}

	
	
	 

	private TrailerData updatePlatform(int startLine2, ArrayList<String> messages,Integer contractorId,
			String platformPlate, String platformTrade, String platformMode,
			String platformSemi, String platformZorra, String platformYear,
			String platformSusp, String platformAbs,
			String platformWeightControl)throws  Exception {
		
		ArrayList<TrailerData> dataCol= sys.getVehicleReportFacade().findTrailer(platformPlate, sys.getUserSessionData());
		TrailerData data=dataCol.iterator().next();
		
		if(platformTrade!=null && platformTrade.trim().length()>0){
			Collection<TypeTradeDataType> col =	 sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData());
			boolean esta=false;
			for(TypeTradeDataType type:col  ){
				if(sys.getText().getText(type.getResourceBundleId()).equalsIgnoreCase(platformTrade.trim())){
					data.setTrade(type);
					break;
				}
			}
		}
		
		if(contractorId!=null && contractorId>0){
			Iterator it = data.getDivOrgsIterator();
			while(it.hasNext()) {
			    DivOrgData divOrgData = (DivOrgData)it.next();
			    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			    item1.setId(contractorId);
			    
			}
		}
		
		if(platformMode!=null && platformMode.trim().length()>0){
			data.setModel(platformMode);
		}
		if(platformSemi!=null && platformSemi.trim().length()>0){
			data.setType("Semi");
		}
		if(platformZorra!=null && platformZorra.trim().length()>0){
			data.setType("Zorra");
		}
		if(platformYear!=null && platformYear.trim().length()>0){
			data.setYear(Integer.valueOf(platformYear));
		}
		if(platformSusp!=null && platformSusp.trim().length()>0){
			if(platformSusp.trim().equalsIgnoreCase("si")){
				data.setSuspension(true);
			}else
			if(platformSusp.trim().equalsIgnoreCase("no")){
				data.setSuspension(false);
			}
		}
		if(platformAbs!=null && platformAbs.trim().length()>0){
			if(platformAbs.trim().equalsIgnoreCase("si")){
				data.setAbs(true);
			}else
			if(platformAbs.trim().equalsIgnoreCase("no")){
				data.setAbs(false);
			}
		}
		if(platformWeightControl!=null && platformWeightControl.trim().length()>0){
			if(platformWeightControl.trim().equalsIgnoreCase("si")){
				data.setWeightControl(true);
			}else
			if(platformAbs.trim().equalsIgnoreCase("no")){
				data.setWeightControl(false);
			}
		}
		
		data.setExclusionDate(null);
		
		sys.getVehicleABMFacade().updateTrailer(data, sys.getUserSessionData());
		
		return data;
	}

	private void updateVehicle(int startLine2, ArrayList<String> messages,
			String vehiclePlate, String vehicleContractor, String vehicleMode,
			String vehicleSL, String vehicleTrade, String vehicleModel,
			String vehicleYear, String vehicleHP, String vehicleEuro3,
			String vehicleSuspension, String vehicleAbs,
			String vehicleWeightControl)throws  Exception {
		
		VehicleData data=sys.getVehicleReportFacade().findVehicleByPlate(vehiclePlate, sys.getUserSessionData());
		
		if(data.getOptionalInfo()==null){
			data.setOptionalInfo(new VehicleOptionalInfoData());
		}
		
		if(vehicleContractor!=null && vehicleContractor.trim().length()>0){
			Iterator it = data.getDivOrgsIterator();
			while(it.hasNext()) {
			    DivOrgData divOrgData = (DivOrgData)it.next();
			    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			    item1.setId(sys.getUserReportFacade().getDivOrgItemLevel1ID(vehicleContractor, 456435, sys.getUserSessionData()));
			    
			}
		}
		
		if(vehicleMode!=null && vehicleMode.trim().length()>0){
			data.getOptionalInfo().setTachograph(vehicleMode);
		}
		if(vehicleSL!=null && vehicleSL.trim().length()>0){
			data.getOptionalInfo().setSl(vehicleSL.trim());
		}
		if(vehicleTrade!=null && vehicleTrade.trim().length()>0){
			Collection<TypeTradeDataType> col =	 sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData());
			boolean esta=false;
			for(TypeTradeDataType type:col  ){
				if(sys.getText().getText(type.getResourceBundleId()).equalsIgnoreCase(vehicleTrade)){
					data.setTrade(type);
					break;
				}
			}
			
		}
		if(vehicleSL!=null && vehicleSL.trim().length()>0){
			data.setCostCenter(vehicleSL.toUpperCase());
		}
		if(vehicleModel!=null && vehicleModel.trim().length()>0){
			data.setModel(vehicleModel);
		}
		if(vehicleYear!=null && vehicleYear.trim().length()>0){
			data.setYear(Integer.valueOf(vehicleYear));
		}
		if(vehicleHP!=null && vehicleHP.trim().length()>0){
			data.setHp(Integer.valueOf(vehicleHP));
		}
		if(vehicleEuro3!=null && vehicleEuro3.trim().length()>0){
			if(vehicleEuro3.trim().equalsIgnoreCase("si")){
				data.getOptionalInfo().setRenavan("generic.messages.yes");
			}else
			if(vehicleEuro3.trim().equalsIgnoreCase("no")){
				data.getOptionalInfo().setRenavan("generic.messages.no");
			}
		}
		if(vehicleSuspension!=null && vehicleSuspension.trim().length()>0){
			if(vehicleSuspension.trim().equalsIgnoreCase("si")){
				data.setSuspension(true);
			}else
			if(vehicleSuspension.trim().equalsIgnoreCase("no")){
				data.setSuspension(false);
			}
		}
		if(vehicleAbs!=null && vehicleAbs.trim().length()>0){
			if(vehicleAbs.trim().equalsIgnoreCase("si")){
				data.setAbs(true);
			}else
			if(vehicleAbs.trim().equalsIgnoreCase("no")){
				data.setAbs(false);
			}
		}
		if(vehicleWeightControl!=null && vehicleWeightControl.trim().length()>0){
			if(vehicleWeightControl.trim().equalsIgnoreCase("si")){
				data.getOptionalInfo().setMonitoringSystem("generic.messages.yes");
			}else
			if(vehicleWeightControl.trim().equalsIgnoreCase("no")){
				data.getOptionalInfo().setMonitoringSystem("generic.messages.no");
			}
		}
		
		data.setExclusionDate(null);
		
		sys.getVehicleABMFacade().vehicleUpdate(data, "Auto. Update, User: "+sys.getUserSessionData().getFullName(), sys.getUserSessionData());
	}

	private boolean controlPlatform(int line, ArrayList<String> messages,
			String platformPlate, String platformTrade, String platformMode,
			String platformSemi, String platformZorra, String platformYear,
			String platformSusp, String platformAbs,
			String platformWeightControl) throws  Exception{

		ArrayList<TrailerData> list= sys.getVehicleReportFacade().findTrailer(platformPlate, sys.getUserSessionData());
		ArrayList<String> row=new ArrayList<String>();
		row.add(platformPlate);
		row.add(platformTrade);
		row.add(platformMode);
		row.add(platformSemi);
		row.add(platformZorra);
		row.add(platformYear);
		row.add(platformWeightControl);
	 	
		if(list.size()==0){
			platformsNotExist.add(row);
			messages.add("Fila:"+line+". No existe la plataforma con matricula "+platformPlate);
			return false;
		}
		if(list.size()>1){
			row.add(0,"DUPLICADA!!!!");
			platformsNotExist.add(row);
			messages.add("Fila:"+line+". Plataforma con matricula "+platformPlate+" está duplicada no esposible actualizar");
			return false;
		}
		platforms.add(platformPlate);
		
		if(platformYear!=null && platformYear.trim().length()>0){
			try{
				
				Integer.valueOf(platformYear);
			}catch(Exception ex){
				messages.add("Fila:"+line+". El año de la plataforma debe ser un número o vacio, se ingresó "+platformYear);
				return false;
			}
		}
		if(platformSemi!=null && platformSemi.trim().length()>0 && platformZorra!=null && platformZorra.trim().length()>0){
			messages.add("Fila:"+line+". Solo se puede indicar semirremolque o zorra pero no los dos");
			return false;
		}
		
		if(platformTrade!=null && platformTrade.trim().length()>0){
			Collection<TypeTradeDataType> col =	 sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData());
			boolean esta=false;
			for(TypeTradeDataType type:col  ){
				if(sys.getText().getText(type.getResourceBundleId()).equalsIgnoreCase(platformTrade.trim())){
					esta=true;
					break;
				}
			}
			if(!esta){
				messages.add("Fila:"+line+". La marca de la plataforma es inválida, el nombre debe coincidir exactamente con el del sistema, se ingresó "+platformTrade);
				return false;
			}
		}
		
		
		
		return true;
	}

	private boolean controlVehicle(int line, ArrayList<String> messages,
			String vehiclePlate, String vehicleContractor, String vehicleMode,
			String vehicleSL, String vehicleTrade, String vehicleModel,
			String vehicleYear,  String vehicleHP,
			String vehicleEuro3, String vehicleSuspension, String vehicleAbs,
			String vehicleWeightControl) throws  Exception {
		
		VehicleData data=sys.getVehicleReportFacade().findVehicleByPlate(vehiclePlate, sys.getUserSessionData());
		ArrayList<String> row=new ArrayList<String>();
		row.add(vehiclePlate);
		row.add(vehicleContractor);
		row.add(vehicleMode);
		row.add(vehicleSL);
		row.add(vehicleTrade);
		row.add(vehicleModel);
		row.add(vehicleYear);
		row.add(vehicleHP);
		row.add(vehicleEuro3);
		row.add(vehicleSuspension);
		row.add(vehicleAbs);
		row.add(vehicleWeightControl);
		
		
		if(data==null){
			vehiclesNotExist.add(row);
			messages.add("Fila:"+line+". No existe el vehículo con matricula "+vehiclePlate);
			return false;
		}else{
			vehicles.add(vehiclePlate);
		}
		if(vehicleYear!=null && vehicleYear.trim().length()>0){
			try{
				
				Integer.valueOf(vehicleYear);
			}catch(Exception ex){
				messages.add("Fila:"+line+". El año del vehículo debe ser un número o vacio, se ingresó "+vehicleYear);
				return false;
			}
		}
		
		if(vehicleHP!=null && vehicleHP.trim().length()>0){
			try{
				Integer.valueOf(vehicleHP);
			}catch(Exception ex){
				messages.add("Fila:"+line+". El valor de HP del vehículo debe ser un número o vacio, se ingresó "+vehicleHP);
				return false;
			}
		}
		if(vehicleMode!=null && vehicleMode.trim().length()>0){
			if(!(vehicleMode.equalsIgnoreCase("doble") ||vehicleMode.equalsIgnoreCase("simple"))){
				messages.add("Fila:"+line+". El valor de  \"Conductor Doble/Simple\" del vehículo debe ser \"DOBLE\" o \"SIMPLE\" o vacio, se ingresó "+vehicleMode);
				return false;
			}
		}
		
		if(vehicleContractor!=null && vehicleContractor.trim().length()>0){
			Integer divItemId=sys.getUserReportFacade().getDivOrgItemLevel1ID(vehicleContractor, 456435, sys.getUserSessionData());
			if(divItemId==null){
				messages.add("Fila:"+line+". Transportista inválido, el nombre debe coincidir exactamente con el del sistema, se ingresó "+vehicleContractor);
				return false;
			}
		}
		
		if(vehicleSL!=null && vehicleSL.trim().length()>0){
			vehicleSL=vehicleSL.trim();
			if(!(vehicleSL.equalsIgnoreCase("LARGO PLAZO") || vehicleSL.equalsIgnoreCase("SPOT"))){
				messages.add("Fila:"+line+". El campo S/L tiene que ser SPOT o LARGO PLAZO o vacio, se ingresó "+vehicleSL);
				return false;
			}
		}
				
		if(vehicleTrade!=null && vehicleTrade.trim().length()>0){
			Collection<TypeTradeDataType> col =	 sys.getDataTypeReportFacade().findAll(DataTypeConstants.TYPETRADE, sys.getUserSessionData());
			boolean esta=false;
			for(TypeTradeDataType type:col  ){
				if(sys.getText().getText(type.getResourceBundleId()).equalsIgnoreCase(vehicleTrade.trim())){
					esta=true;
					break;
				}
			}
			if(!esta){
				messages.add("Fila:"+line+". La marca del vehículo es inválida, el nombre debe coincidir exactamente con el del sistema, se ingresó "+vehicleTrade);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,ActionForm form) {
		return null;
	}

	 
}
