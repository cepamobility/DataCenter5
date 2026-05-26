package presentation.translator.web.util;

import presentation.translator.web.util.UploadValidator.ColumnMetadata;
import presentation.translator.web.util.UploadValidator.Valid;
import business.SystemAccess;

public  abstract class  UploadValidatorCorporation  {
	
	

	public abstract Valid validate(ColumnMetadata metadata,String value,SystemAccess sys);
	public abstract ColumnMetadata[] getColumns();
}
