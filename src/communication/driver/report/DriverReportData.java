/**
 * Created on 03-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import communication.administration.corporation.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

public class DriverReportData extends AbstractTableModel implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private int total;
	private DriverReportDataType[] attributes;
	private boolean color = true;
	private Text messages;

	private Hashtable points;

	public DriverReportData() {
		total = 0;
		attributes = new DriverReportDataType[0];

	}

	public DriverReportData(ListResult reportDrivers) {
		total = reportDrivers.getTotal();
		Collection elements = reportDrivers.getElements();
		Collections.sort((List) elements);
		attributes = (DriverReportDataType[]) elements
				.toArray(new DriverReportDataType[0]);
	}

	public int getRowCount() {
		return attributes.length;
	}

	public int getColumnCount() {
		return 35;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.valueOf(attributes[rowIndex].getId());
		case 1:
			return attributes[rowIndex].getFirstname();
		case 2:
			return attributes[rowIndex].getLastname();
		case 3:
			color = !color;
			return new Boolean(color);
		case 4:
			return messages.getText(attributes[rowIndex].getCountry());
		case 5:
			return Util.formatDate(attributes[rowIndex].getBirthdate(),
					messages);
		case 6:
			return Util.formatDate(attributes[rowIndex].getExclusiondate(),
					messages);
		case 7:
			return messages.getText(attributes[rowIndex].getContract());
		case 8:
			return attributes[rowIndex].getDivorg11();
		case 9:
			return attributes[rowIndex].getDivorg12();
		case 10:
			return attributes[rowIndex].getDivorg13();
		case 11:
			return attributes[rowIndex].getDivorg21();
		case 12:
			return attributes[rowIndex].getDivorg22();
		case 13:
			return attributes[rowIndex].getDivorg23();
		case 14:
			return attributes[rowIndex].getDivorg31();
		case 15:
			return attributes[rowIndex].getDivorg32();
		case 16:
			return attributes[rowIndex].getDivorg33();
		case 17:
			return attributes[rowIndex].getDivgeo1();
		case 18:
			return attributes[rowIndex].getDivgeo2();
		case 19:
			return attributes[rowIndex].getDivgeo3();
		case 20:
			return new Integer(total);
		case 21:
			return messages.getText(attributes[rowIndex].getCountry());
		case 22:
			return Util
					.formatDate(attributes[rowIndex].getTestDate(), messages);
		case 23:
			if (attributes[rowIndex].isApt()) {
				return messages.getText("generic.messages.yes");
			} else {
				return messages.getText("generic.messages.no");
			}
		case 24:
			return Util.formatDate(attributes[rowIndex].getExpiry(), messages);
		case 25:
			return attributes[rowIndex].getBpsRegistry();
		case 26:
			return Util.formatDate(attributes[rowIndex].getLastControl(),
					messages);
		case 27:
			if (attributes[rowIndex].getStatus() != AlcoholControlReportDataType.NEVER) {
				if (attributes[rowIndex].getResult().intValue() == DriverData.POSITIVE) {
					return messages.getText("generic.messages.positive");
				} else {
					return messages.getText("generic.messages.negative");
				}
			}
			return null;
		case 28:
			switch (attributes[rowIndex].getStatus()) {
			case AlcoholControlReportDataType.NEVER:
				return messages.getText("driver.alcoholcontrol.neverdone");
			case AlcoholControlReportDataType.EXPIRED:
				return messages.getText("driver.alcoholcontrol.expired");
			default:
				return messages.getText("driver.alcoholcontrol.effective");
			}
		case 29:
			if (attributes[rowIndex].isAnyPositive()) {
				return messages.getText("generic.messages.yes");
			} else {
				return messages.getText("generic.messages.no");
			}
		case 30:
			if (points != null) {
				if (points.containsKey(attributes[rowIndex].getId())) {
					return String.valueOf(points.get(attributes[rowIndex]
							.getId()));
				}
			}
			return "25";
		case 31:
			if (attributes[rowIndex].getAssignedVehicle() == null) {
				return "---";
			} else {
				return messages.getText(attributes[rowIndex]
						.getAssignedVehicle());
			}
		case 32:
			return (attributes[rowIndex].isDirector()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
		case 33:
			return attributes[rowIndex].getCorporationId();
		case 34:
			return (attributes[rowIndex].isTemporal()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no");
		}
		return null;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "id";
		case 1:
			return "name";
		case 2:
			return "lastname";
		case 3:
			return "color";
		case 4:
			return "country";
		case 5:
			return "birthdate";
		case 6:
			return "exclusiondate";
		case 7:
			return "contract";
		case 8:
			return "divorg11";
		case 9:
			return "divorg12";
		case 10:
			return "divorg13";
		case 11:
			return "divorg21";
		case 12:
			return "divorg22";
		case 13:
			return "divorg23";
		case 14:
			return "divorg31";
		case 15:
			return "divorg32";
		case 16:
			return "divorg33";
		case 17:
			return "divgeo1";
		case 18:
			return "divgeo2";
		case 19:
			return "divgeo3";
		case 20:
			return "total";
		case 21:
			return "country";
		case 22:
			return "test";
		case 23:
			return "apt";
		case 24:
			return "expiry";
		case 25:
			return "bps";
		case 26:
			return "lastControl";
		case 27:
			return "result";
		case 28:
			return "status";
		case 29:
			return "anypositive";
		case 30:
			return "points";
		case 31:
			return "assignedVehicle";
		case 32:
			return "director";
		case 33:
			return "corporationId";
		case 34:
			return "temporal";
		}
		return null;
	}

	public void setMessages(Text messages) {
		this.messages = messages;
	}

	public int getTotal() {
		return total;
	}

	public void setPoints(Hashtable driverPoints) {
		points = driverPoints;
	}

	public Hashtable getPoints() {
		return points;
	}

}
