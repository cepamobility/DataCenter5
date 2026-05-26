package business.alert.handler;

import java.io.Serializable;

import persistence.administration.corporation.tables.Country;
import business.alert.access.AlertSystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.alert.AlertData;

public class AlertHandlerFactory implements Serializable {
	
	/**
	 * 
	 * @param alertid
	 * @param parentType
	 * @param type
	 * @param to
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param system
	 * @return
	 */
	public AlertHandler getAlertHandler(AlertData alertData,AlertSystemAccess system){
		
		String alertType = alertData.getAlertType().getResourceBundleId();
		int corporationId = alertData.getCorporation().intValue();
		int alertId = alertData.getAlertId();
		
		
		if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_REPORT_DRIVER) &&
				corporationId == CorporationData.FORESTALORIENTAL){
			
			// Reportes FOSA Contratistas (Activo)
			try{
				system = new AlertSystemAccess("MICAFORESTAL"); // Usuario de Laura Capano
				return   new MailContractorAlertHandler(alertData,system);		
			}catch(Exception e){
				return null;
			}
			
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_REPORT_DRIVER) &&
				corporationId == CorporationData.WEYERHAEUSER){
			
			// Reportes Weyerhaeuser Contratistas (Activo)
			try{
				system = new AlertSystemAccess("LAURAWEYERHAEUSER"); // Usuario de Laura Capano .
				return   new MailContractorAlertHandler(alertData,system);		
			}catch(Exception e){
				return null;
			}
			
		}else if (
				alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LICENSE_EXPIRY) &&
				(
						(corporationId == CorporationData.BAYER && alertId == AlertConstants.BAYER_LICENSE_EXPIRY_30DAYS) 
						|| (corporationId == CorporationData.ALLERGAN && alertId == AlertConstants.ALLERGAN_LICENSE_EXPIRY_30DAYS)
				)
				) {
			
			// Vencimientos Libreta de conducir BAYER (Octubre 2010), 30 dias exactos
			try{
							
				int daysLeft = 30; // libretas que venzan en igual que 30 dias
				boolean onlyEquals = true;
				return new LicenseExpiryHandler(alertData,daysLeft,onlyEquals, null);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LICENSE_EXPIRY) &&
				corporationId == CorporationData.BAYER &&
				alertId == AlertConstants.BAYER_LICENSE_EXPIRY_7DAYS){
			
			// Vencimientos Libreta de conducir BAYER (Octubre 2010), 7 dias exactos
			try{
							
				int daysLeft = 7; // libretas que venzan en igual que 7 dias
				boolean onlyEquals = true;
				return new LicenseExpiryHandler(alertData,daysLeft,onlyEquals, null);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}	
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LICENSE_EXPIRY) &&
				corporationId == CorporationData.BAYER &&
				alertId == AlertConstants.BAYER_LICENSE_EXPIRY_1DAY_PASS){
			
			// Vencimientos Libreta de conducir BAYER (Octubre 2010), -1 dias exactos
			try{
							
				int daysLeft = -1; // libretas que venzan en igual que -1 dias (AYER)
				boolean onlyEquals = true;
				return new LicenseExpiryHandler(alertData,daysLeft,onlyEquals,null);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}		
		
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LICENSE_EXPIRY) &&
					corporationId == CorporationData.NALCO &&
					alertId == AlertConstants.NALCO_LICENSE_EXPIRY){
				
				// Vencimientos Libreta de conducir NALCO MEXICO (Febrero 2011), 30 dias exactos
				try{
								
					int daysLeft = 30; // libretas que venzan en igual que 30 dias
					boolean onlyEquals = false;
					int countryId = Country.MEXICO; // Mexico 
					return new LicenseExpiryHandler(alertData,daysLeft,onlyEquals, countryId);
					
				}catch (Exception e){
					e.printStackTrace();
					return null;
				}
			
			
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LICENSE_EXPIRY) &&
				corporationId == CorporationData.CIMSA){
			
			// Vencimientos Libreta de conducir CIMSA (Junio 2010)
			try{
							
				int daysLeft = 30; // libretas que venzan en menos o igual que 30 dias
				boolean onlyEquals = false;
				return new LicenseExpiryHandler(alertData,daysLeft,onlyEquals,null);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
			
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_LOWPOINTS) &&
				corporationId == CorporationData.CIMSA){
			
			// Puntos Bajos en los Drivers de CIMSA (Junio 2010) u otra empresa
			try{
							
				int maxPoints = 10; // conductores con menos o igual a 10 puntos.
				return new LowPointsHandler(alertData,maxPoints);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
			
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_REMINDER_FLEET) &&
				corporationId == CorporationData.MONSANTO) {
			
			// Recordatorios Monsanto:
			// - Argentina (Junio 2010)
			// - Brasil (Junio 2010)
			// - Paraguay (Agosto 2011)
			try{
				
				return new RememberAlertHandler(alertData);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_TRAVEL) &&
				corporationId == CorporationData.ANGLO){
			
			// Viajes para el día de hoy en ANGLO: (Agosto 2010)
			try{
				return new TravelAlertHandler(alertData);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			
		} else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_REMINDER_FLEET) &&
				corporationId == CorporationData.LILLY) {
			
			// Recordatorios Lilly (Agosto 2011)
			// - Ingles: Flotas Jamaica, Curacao, Bahamas, Barbados, Trinidad y Tobago
			// - Español sin odometro: Guatemala, El Salvador, Honduras, Costa Rica, Panama, Rep Dominicana
			// - Español con odometro: Colombia, Peru y Venezuela
			try{
				return new RememberAlertHandler(alertData);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}else if (alertType.equalsIgnoreCase(AlertConstants.ALERTTYPE_CEPA_TIPS) &&
				corporationId == CorporationData.BAYER){
			
			// Alertas de CEPA Tips Bayer
			try{
				return new CepaTipsAlertHandler(alertData);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
			
		return  new ScheduledAlertHandler(alertData);
		
	}

}
