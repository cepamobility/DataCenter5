package business.points;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;
import business.administration.corporation.CorporationReportFacade;

import communication.administration.corporation.tables.PointMoveData;
import communication.administration.corporation.tables.PointsDefinitionData;
import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;

public class PointManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static PointManager instance;
	
	private PointManager(){
		
	}
	
	public static synchronized PointManager getInstance(){
		if(instance==null){
			instance=new PointManager();
		}
		return instance;
	}
	
}

