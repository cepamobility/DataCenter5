package presentation.translator.web.util;

import java.util.ArrayList;
import java.util.Iterator;

import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;


public class UploadValidator {
	
	public static class ColumnMetadata{
		private boolean date;
		private boolean string;
		private String token;
		//Columna en el excel de entrada
		private int index=-1;
		//Columna en el array de metadata.
		private int colIndex=-1;
		
		
		public ColumnMetadata(int colIndex, boolean date,boolean string, String token,int index){
			this.colIndex=colIndex;
			this.date=date;
			this.string=string;
			this.token=token;
			this.index=index;
			
		}
		
		public int getColIndex() {
			return colIndex;
		}

		public void setColIndex(int colIndex) {
			this.colIndex = colIndex;
		}

		public boolean isDate() {
			return date;
		}
		public void setDate(boolean date) {
			this.date = date;
		}
		public boolean isString() {
			return string;
		}
		public void setString(boolean string) {
			this.string = string;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		
	}
	
	public static class Valid{
		public boolean valid;
		public String message;
		public Object value;
	}
	
	public ColumnMetadata[] columns;
	
	public UploadValidator(int corporationId){
		columns=factoryUploadValidator(corporationId).getColumns();
	}	
	
	public ArrayList<ArrayList<Valid>> validate(ColumnMetadata[] metadata,Integer corporationId,SystemAccess sys, ArrayList<ArrayList<String>> rows){
		ArrayList<ArrayList<Valid>> res=new ArrayList<ArrayList<Valid>>();
		Iterator<ArrayList<String>> iter=rows.iterator();
		iter.next();//Salteo cabezal
		while(iter.hasNext()){
			ArrayList<String> row=iter.next();
			ArrayList<Valid> valids=new ArrayList<UploadValidator.Valid>();
			int index=0;
			for(String val:row){
				int colIndex=getColumnMetadata(metadata,index);
				if(colIndex>=0){
					valids.add(validate(metadata,colIndex,corporationId,val,sys));	
				}
					
				index++;
			}
			res.add(valids);
		}
		
		return res;

	}
	
	private int getColumnMetadata(ColumnMetadata[] metadata,int index) {
		int i=0;
		for(ColumnMetadata col:metadata){
			if(col.getIndex()==index){
				return i;
			}
			i++;
		}
		return -1;
	}


	private Valid validate(ColumnMetadata[] metadata,int index, Integer corporationId,String value, SystemAccess sys){
		Valid val=new Valid();
		if(metadata[index].index>=0){
			UploadValidatorCorporation uploadValidatorCoproration=factoryUploadValidator(corporationId);
			
			return uploadValidatorCoproration.validate(metadata[index], value,sys);
		}else{
			val.valid=false;
			val.message="No se usa";
		}
		
		return val;
		
	}
	
	public UploadValidatorCorporation factoryUploadValidator(int corporationId){
		UploadValidatorCorporation uploadValidatorCoproration=null;
		switch (corporationId) {
			case CorporationData.JJ:
				uploadValidatorCoproration=new JJUploadValidator();
				break;
			case CorporationData.MONTES_DE_PLATA:
				uploadValidatorCoproration=new MDPUploadValidator();
				break;
		}
		return uploadValidatorCoproration;
	}

}
