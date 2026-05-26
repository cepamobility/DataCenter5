package presentation.translator.web.util;

import presentation.translator.web.util.UploadValidator.ColumnMetadata;
import presentation.translator.web.util.UploadValidator.Valid;
import business.SystemAccess;

public class MDPUploadValidator extends UploadValidatorCorporation {

	private ColumnMetadata[] COLUMNS;
	
	public MDPUploadValidator() {
		// TODO Auto-generated constructor stub
		COLUMNS=new ColumnMetadata[]{
				new ColumnMetadata(0,false, true, "generic.driver.driverdata.firstname", -1),
				new ColumnMetadata(1,false, true, "generic.driver.driverdata.lastname",-1),
				new ColumnMetadata(2,true, false, "generic.driver.driverdata.birthdate",-1),
				new ColumnMetadata(3,false, true, "generic.driver.driverdata.sex",-1),
				new ColumnMetadata(4,false, true, "generic.driver.driverdata.nationality",-1),
				new ColumnMetadata(5,false, true, "generic.driver.driverdata.city",-1),
				new ColumnMetadata(6,false, true, "generic.driver.driverdata.personaladdress",-1),
				new ColumnMetadata(7,false, true, "generic.driver.driverdata.corporationid",-1),
				new ColumnMetadata(8,false, true, "generic.driver.driverdata.startdate",-1),
				new ColumnMetadata(9,false, true, "generic.driver.driverdata.email",-1),
				new ColumnMetadata(10,false, true, "generic.driver.driverdata.phonenumber",-1),
				new ColumnMetadata(11,false, true, "generic.driver.driverdata.officeaddress",-1),
				new ColumnMetadata(12,false, true, "generic.driver.driverdata.drives",-1),
				new ColumnMetadata(13,false, true, "generic.driver.driverdata.employee",-1),
				new ColumnMetadata(14,false, true, "generic.driver.driverdata.contact",-1),
				new ColumnMetadata(15,false, true, "generic.driver.driverdata.bpsregistry",-1),
				new ColumnMetadata(16,false, true, "generic.driver.driverdata.licensenumber",-1),
				new ColumnMetadata(17,true, false, "generic.driver.driverdata.licenseget",-1),
				new ColumnMetadata(18,false, true, "generic.driver.driverdata.licensecategory",-1),
				new ColumnMetadata(19,false, true, "generic.driver.driverdata.vehicleallowed",-1)};
	}
	
	@Override
	public Valid validate(ColumnMetadata metadata, String value,SystemAccess sys) {
		switch(metadata.getColIndex()){
			case 0:
				return validateFirstName(metadata,value,sys);
			case 1:
				return validateLastName(metadata,value,sys);	
			case 2:
				return validiateBirthDate(metadata,value,sys);	
			case 3:
				return validateSex(metadata,value,sys);	
			case 4:
				return validateNationality(metadata,value,sys);	
			case 5:
				return validateCity(metadata,value,sys);	
			case 6:
				return validatePersonalAddress(metadata,value,sys);	
			case 7:
				return validateCorporationId(metadata,value,sys);	
			case 8:
				return validateInclusionDate(metadata,value,sys);	
			case 9:
				return validateEmail(metadata,value,sys);	
			case 10:
				return validatePhone(metadata,value,sys);	
			case 11:
				return validateOfficeAddress(metadata,value,sys);	
			case 12:
				return 	validateDrives(metadata,value,sys);	
			case 13:
				return validateEmployee(metadata,value,sys);	
			case 14:
				return validateContact(metadata,value,sys);	
			case 15:
				return validateBpsRegistry(metadata,value,sys);	
			case 16:
				return validateLicenseNumber(metadata,value,sys);	
			case 17:
				return validateLicenseGet(metadata,value,sys);	
			case 18:
				return validateLicenseCategory(metadata,value,sys);	
			case 19:
				return validateVehicleAllowed(metadata,value,sys);	
		}
		
		Valid val=new Valid();
		val.valid=false;
		val.message="Columna no identificada";
		
		return val;
	}

	 

	private Valid validateVehicleAllowed(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateLicenseCategory(ColumnMetadata metadata,
			String value, SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateLicenseGet(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateLicenseNumber(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateBpsRegistry(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateContact(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateEmployee(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateDrives(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateCountry(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateOfficeAddress(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validatePhone(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateEmail(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateInclusionDate(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateCorporationId(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validatePersonalAddress(ColumnMetadata metadata,
			String value, SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateCity(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateNationality(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateSex(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validiateBirthDate(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateLastName(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	private Valid validateFirstName(ColumnMetadata metadata, String value,
			SystemAccess sys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColumnMetadata[] getColumns() {
		// TODO Auto-generated method stub
		return COLUMNS;
	}
	
	

}
