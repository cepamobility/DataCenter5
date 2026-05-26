package business.alert.handler;

import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.Message;

public class NotScheduledAlertHandler extends AlertHandler {

	public NotScheduledAlertHandler(AlertData alertData) {
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue());
		
		constructMessage(new Message(AlertConstants.FROM_DC,
				AlertConstants.FROMNAME_DC,
				AlertConstants.FROMPASSWORD_DC,
				alertData.getTo(),alertData.getSubject(),alertData.getBody()));
	}

}
