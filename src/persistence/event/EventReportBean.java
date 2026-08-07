package persistence.event;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.jboss.logging.Logger;

import persistence.event.tables.Event;
import persistence.event.tables.EventAbstract;
import persistence.event.tables.EventDamage;
import persistence.event.tables.Fine;
import persistence.event.tables.Reparation;
import persistence.event.tables.ReparationComment;
import persistence.event.tables.ReparationItem;
import persistence.event.tables.Travel;
import persistence.hibernate.HibernateUtil;
import persistence.log.LoggerFormatting;
import persistence.log.event.EventLog;
import persistence.travel.tables.CheckPoint;
import persistence.util.ExceptionHandler;
import persistence.util.Util;
import persistence.util.UtilityQuery;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.report.DriverHistoryDataType;
import communication.driver.tables.DriverData;
import communication.event.tables.DetailedReportEventAttributes;
import communication.event.tables.EventDataType;
import communication.event.tables.EventListData;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.FineDataType;
import communication.event.tables.ReparationData;
import communication.event.tables.SimpleReportEventAttributes;
import communication.event.util.EventByPeriodData;
import communication.event.util.EventSeverityData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.util.EventCostData;
import communication.fleet.util.EventInjuriesData;
import communication.fleet.util.EventTypeData;
import communication.fleet.util.FineTypeData;
import communication.language.Text;
import communication.travel.tables.TravelDataType;
import communication.util.ListResult;
import communication.util.LogActions;
import communication.util.tables.ResumeItem;

/**
 * @ejb.bean name="EventReport" display-name="Name for EventReport"
 *           description="Description for EventReport"
 *           jndi-name="ejb/EventReport" type="Stateless"
 *
 */
public abstract class EventReportBean implements SessionBean {

	/**
	 * Logger for this class
	 */
	private Logger logger = Logger.getLogger(EventReportBean.class);
	private LoggerFormatting logFormatting = new LoggerFormatting();

	private ExceptionHandler handler = new ExceptionHandler(EventReportBean.class);

	/**
	 * @ejb.create-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventedit,eventview,driveredit,driverviewowndata,drivereventview,drivereventedit,driverviewowndata,query,viewrecomendation,fleetinsertdata,dpachecklist"
	 * @throws CreateException
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator,drivereventview"
	 */
	public ListResult findList(UserSessionData userSessionData, String name,
			int type, int status, int country, int folioNumber, int first,
			int count) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			String queryStr = "SELECT COUNT(*) FROM genericevents ge ";
			switch (type) {
			case EventDataType.ACCIDENT:
			case EventDataType.INCIDENT:
			case EventDataType.NEARMISS:
				queryStr += "JOIN events e ON ge.id = e.id ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;

			case EventDataType.FINE:
				queryStr += "JOIN fines f ON ge.id = f.id ";
				break;

			default:
				if (folioNumber != 0) {
					// CIMSA
					queryStr += "JOIN events e ON ge.id = e.id ";
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}
				break;
			}

			if (userSessionData.isDriver() || (name != null)) {
				queryStr += "JOIN drivers d ON ge.driver = d.id ";
			}

			queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("ge",
					userSessionData);

			if (userSessionData.isDriver()) {
				queryStr += " AND ge.driver = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId()) + " ";
			} else {
				if (name != null) {
					queryStr += " AND (LOWER(d.firstName) LIKE '%"
							+ name.toLowerCase()
							+ "%' OR LOWER(d.lastName) LIKE '%"
							+ name.toLowerCase() + "%') ";
				}
			}
			if (status != 0) {
				queryStr += " AND ge.status = " + status + " ";
			}
			if (country != 0) {
				queryStr += " AND ge.country = " + country + " ";
			}

			switch (type) {
			case EventDataType.INCIDENT:
				queryStr += " AND e.incident IS NOT null ";
				break;
			case EventDataType.ACCIDENT:
				queryStr += " AND e.accident IS NOT null ";
				break;
			case EventDataType.NEARMISS:
				queryStr += " AND e.nearmiss IS NOT null ";
				break;
			}

			Query query = session.createSQLQuery(queryStr).addScalar("count",
					Hibernate.INTEGER);

			List userColl = query.list();
			Iterator iter = userColl.iterator();
			Integer total = new Integer(0);
			if (iter.hasNext()) {
				total = (Integer) iter.next();
			}

			boolean contractor = false;
			String divorg = "";
			if (userSessionData.getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL) {
				divorg = "1";
				contractor = true;
			}
			if (userSessionData.getFilter().getCorporationId().intValue() == CorporationData.KLABIN) {
				divorg = "2";
				contractor = true;
			}
			switch (type) {
			case EventDataType.ACCIDENT:
			case EventDataType.INCIDENT:
			case EventDataType.NEARMISS:
				queryStr = "SELECT ge.id AS id,d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "JOIN events e ON ge.id = e.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
				break;
			case EventDataType.FINE:
				queryStr = "SELECT ge.id AS id,d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "JOIN fines f ON ge.id = f.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
				break;
			default:

				// No ingresaron tipo:

				queryStr = "SELECT ge.id AS id,e.accident AS accident,e.incident AS incident,f.id AS fine,e.nearmiss AS nearmiss,"
						+ "d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country  ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "LEFT JOIN events e ON ge.id = e.id "
						+ "LEFT JOIN fines f ON ge.id = f.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
			}

			queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("ge",
					userSessionData);

			if (userSessionData.isDriver()) {
				queryStr += " AND d.id = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId());
			} else {
				if (name != null) {
					queryStr += " AND (LOWER(d.firstName) LIKE '%"
							+ name.toLowerCase()
							+ "%' OR LOWER(d.lastName) LIKE '%"
							+ name.toLowerCase() + "%') ";
				}
			}
			if (status != 0) {
				queryStr += " AND ge.status = " + status + " ";
			}
			if (country != 0) {
				queryStr += " AND ge.country = " + country + " ";
			}

			switch (type) {
			case EventDataType.INCIDENT:
				queryStr += " AND e.incident IS NOT null ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.ACCIDENT:
				queryStr += " AND e.accident IS NOT null ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.NEARMISS:
				queryStr += " AND e.nearmiss IS NOT null ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.FINE:
				break;

			default:
				// Si no ingresaro tipo igual puedo buscar por folio
				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}
				break;
			}

			queryStr += " ORDER by ge.date DESC, ge.id ";

			SQLQuery sqlQuery = session.createSQLQuery(queryStr).addScalar(
					"id", Hibernate.INTEGER);
			if (type == 0) {
				sqlQuery.addScalar("accident", Hibernate.STRING)
						.addScalar("incident", Hibernate.STRING)
						.addScalar("fine", Hibernate.INTEGER)
						.addScalar("nearmiss", Hibernate.STRING);
			}
			sqlQuery.addScalar("name", Hibernate.STRING)
					.addScalar("lastname", Hibernate.STRING)
					.addScalar("date", Hibernate.DATE)
					.addScalar("status", Hibernate.INTEGER)
					.addScalar("country", Hibernate.STRING);
			if (contractor) {
				sqlQuery.addScalar("contractor", Hibernate.STRING);
			}
			if (count != -1) {
				if (total.intValue() < first) {
					if (total.intValue() % count == 0) {
						first = total.intValue() - count;
					} else {
						first = total.intValue() - (total.intValue() % count);
					}
				}
				sqlQuery.setFirstResult(first);
				sqlQuery.setMaxResults(count);
			}
			userColl = sqlQuery.list();
			Collection result = new LinkedList();
			iter = userColl.iterator();
			while (iter.hasNext()) {
				Object[] event = (Object[]) iter.next();
				EventListData eventData = null;
				if (type == 0) {
					int eventType = EventDataType.ACCIDENT;
					if (event[2] != null) {
						eventType = EventDataType.INCIDENT;
					} else if (event[3] != null) {
						eventType = EventDataType.FINE;
					} else if (event[4] != null) {
						eventType = EventDataType.NEARMISS;
					}
					eventData = new EventListData((Integer) event[0],
							(String) event[5], (String) event[6],
							(Date) event[7], eventType, (Integer) event[8],
							(String) event[9]);
					if (contractor) {
						eventData.setDivOrg((String) event[10]);
					}
				} else {
					eventData = new EventListData((Integer) event[0],
							(String) event[1], (String) event[2],
							(Date) event[3], type, (Integer) event[4],
							(String) event[5]);
					if (contractor) {
						eventData.setDivOrg((String) event[6]);
					}
				}
				result.add(eventData);
			}

			ListResult returnListResult = new ListResult(result,
					total.intValue());

			return returnListResult;
		} catch (Exception e) {
			Object[] p = new Object[6];
			p[0] = name;
			p[1] = type;
			p[2] = status;
			p[3] = country;
			p[4] = first;
			p[5] = count;
			handler.getException(e, "findList", userSessionData, p);

		}
		return null;
	}

	/**
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator,drivereventview"
	 */
	public ListResult findListDPA(UserSessionData userSessionData, String name,
			int type, int status, int country, int folioNumber, int first,
			int count) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			String queryStr = "SELECT COUNT(*) FROM genericevents ge ";
			switch (type) {
			case EventDataType.ACCIDENT:
			case EventDataType.INCIDENT:
			case EventDataType.NEARMISS:
				queryStr += "JOIN events e ON ge.id = e.id ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;

			case EventDataType.FINE:
				queryStr += "JOIN fines f ON ge.id = f.id ";
				break;

			default:
				if (folioNumber != 0) {
					// CIMSA
					queryStr += "JOIN events e ON ge.id = e.id ";
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}
				break;
			}

			if (userSessionData.isDriver() || (name != null)) {
				queryStr += "JOIN drivers d ON ge.driver = d.id ";
			}

			queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("ge",
					userSessionData);

			if (userSessionData.isDriver()) {
				queryStr += " AND ge.driver = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId()) + " ";
			} else {
				if (name != null) {
					queryStr += " AND (LOWER(d.firstName) LIKE '%"
							+ name.toLowerCase()
							+ "%' OR LOWER(d.lastName) LIKE '%"
							+ name.toLowerCase() + "%') ";
				}
			}
			if (status != 0) {
				queryStr += " AND ge.status = " + status + " ";
			}
			if (country != 0) {
				queryStr += " AND ge.country = " + country + " ";
			}

			switch (type) {
			case EventDataType.INCIDENT:
				queryStr += " AND e.collision = 'datatype.collisionclasification.incident' ";
				break;
			case EventDataType.ACCIDENT:
				queryStr += " AND e.collision != 'datatype.collisionclasification.incident' ";
				break;
			case EventDataType.NEARMISS:
				queryStr += " AND e.nearmiss IS NOT null ";
				break;
			}

			Query query = session.createSQLQuery(queryStr).addScalar("count",
					Hibernate.INTEGER);

			List userColl = query.list();
			Iterator iter = userColl.iterator();
			Integer total = new Integer(0);
			if (iter.hasNext()) {
				total = (Integer) iter.next();
			}

			boolean contractor = false;
			String divorg = "";
			if (userSessionData.getFilter().getCorporationId().intValue() == CorporationData.FORESTALORIENTAL) {
				divorg = "1";
				contractor = true;
			}
			if (userSessionData.getFilter().getCorporationId().intValue() == CorporationData.KLABIN) {
				divorg = "2";
				contractor = true;
			}
			switch (type) {
			case EventDataType.ACCIDENT:
			case EventDataType.INCIDENT:
			case EventDataType.NEARMISS:
				queryStr = "SELECT ge.id AS id,d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "JOIN events e ON ge.id = e.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
				break;
			case EventDataType.FINE:
				queryStr = "SELECT ge.id AS id,d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "JOIN fines f ON ge.id = f.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
				break;
			default:

				// No ingresaron tipo:

				queryStr = "SELECT ge.id AS id,e.accident AS accident,e.incident AS incident,f.id AS fine,e.nearmiss AS nearmiss,e.collision AS collision,"
						+ "d.firstname AS name,d.lastname AS lastname,ge.date AS date,ge.status AS status,c.resourcekey AS country  ";
				if (contractor) {
					queryStr += ", div.resourcekey AS contractor ";
				}
				queryStr += "FROM genericevents ge "
						+ "LEFT JOIN events e ON ge.id = e.id "
						+ "LEFT JOIN fines f ON ge.id = f.id "
						+ "JOIN drivers d ON ge.driver = d.id "
						+ "JOIN country c ON ge.country = c.id ";
				if (contractor) {
					queryStr += "JOIN divorgitemlevel1s div ON ge.divorg"
							+ divorg + " = div.id ";
				}
			}

			queryStr += UtilityQuery.corporationDivisionsRestictionsSQL("ge",
					userSessionData);

			if (userSessionData.isDriver()) {
				queryStr += " AND d.id = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId());
			} else {
				if (name != null) {
					queryStr += " AND (LOWER(d.firstName) LIKE '%"
							+ name.toLowerCase()
							+ "%' OR LOWER(d.lastName) LIKE '%"
							+ name.toLowerCase() + "%') ";
				}
			}
			if (status != 0) {
				queryStr += " AND ge.status = " + status + " ";
			}
			if (country != 0) {
				queryStr += " AND ge.country = " + country + " ";
			}

			switch (type) {
			case EventDataType.INCIDENT:
				queryStr += " AND e.collision = 'datatype.collisionclasification.incident' ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.ACCIDENT:
				queryStr += " AND e.collision != 'datatype.collisionclasification.incident' ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.NEARMISS:
				queryStr += " AND e.nearmiss IS NOT null ";

				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}

				break;
			case EventDataType.FINE:
				break;

			default:
				// Si no ingresaro tipo igual puedo buscar por folio
				if (folioNumber != 0) {
					// CIMSA
					queryStr += " AND e.folioNumber = " + folioNumber + " ";
				}
				break;
			}

			queryStr += " ORDER by ge.date DESC ";

			SQLQuery sqlQuery = session.createSQLQuery(queryStr).addScalar(
					"id", Hibernate.INTEGER);
			if (type == 0) {
				sqlQuery.addScalar("accident", Hibernate.STRING)
						.addScalar("incident", Hibernate.STRING)
						.addScalar("fine", Hibernate.INTEGER)
						.addScalar("nearmiss", Hibernate.STRING)
						.addScalar("collision", Hibernate.STRING);
			}
			sqlQuery.addScalar("name", Hibernate.STRING)
					.addScalar("lastname", Hibernate.STRING)
					.addScalar("date", Hibernate.DATE)
					.addScalar("status", Hibernate.INTEGER)
					.addScalar("country", Hibernate.STRING);
			if (contractor) {
				sqlQuery.addScalar("contractor", Hibernate.STRING);
			}
			if (count != -1) {
				if (total.intValue() < first) {
					if (total.intValue() % count == 0) {
						first = total.intValue() - count;
					} else {
						first = total.intValue() - (total.intValue() % count);
					}
				}
				sqlQuery.setFirstResult(first);
				sqlQuery.setMaxResults(count);
			}
			userColl = sqlQuery.list();
			Collection result = new LinkedList();
			iter = userColl.iterator();
			while (iter.hasNext()) {
				Object[] event = (Object[]) iter.next();
				EventListData eventData = null;
				if (type == 0) {
					int eventType = EventDataType.ACCIDENT;
					if (event[5]
							.equals("datatype.collisionclasification.incident")) {
						eventType = EventDataType.INCIDENT;
					} else if (event[3] != null) {
						eventType = EventDataType.FINE;
					} else if (event[4] != null) {
						eventType = EventDataType.NEARMISS;
					}
					eventData = new EventListData((Integer) event[0],
							(String) event[6], (String) event[7],
							(Date) event[8], eventType, (Integer) event[9],
							(String) event[10]);
					if (contractor) {
						eventData.setDivOrg((String) event[10]);
					}
				} else {
					eventData = new EventListData((Integer) event[0],
							(String) event[1], (String) event[2],
							(Date) event[3], type, (Integer) event[4],
							(String) event[5]);
					if (contractor) {
						eventData.setDivOrg((String) event[6]);
					}
				}
				result.add(eventData);
			}

			ListResult returnListResult = new ListResult(result,
					total.intValue());

			return returnListResult;
		} catch (Exception e) {
			Object[] p = new Object[6];
			p[0] = name;
			p[1] = type;
			p[2] = status;
			p[3] = country;
			p[4] = first;
			p[5] = count;
			handler.getException(e, "findList", userSessionData, p);

		}
		return null;
	}

	/**
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @throws DCException
	 * @param vehicleId
	 * @param userSessionData
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator,drivereventview"
	 */
	public Collection getEventsByVehicle(Integer vehicleId,
			UserSessionData userSessionData) throws Exception {

		Session session = null;

		try {
			session = HibernateUtil.currentSession();

			String queryStr = "select event from EventAbstract as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData)
					+ "and event.vehicle.id = :vehicleId ";
			Query query = session.createQuery(queryStr);

			query.setParameter("vehicleId", vehicleId);
			Collection result = new LinkedList();
			Iterator iter = query.list().iterator();
			while (iter.hasNext()) {
				EventAbstract event = (EventAbstract) iter.next();
				result.add(event.getMainAttributes());
			}
			return result;
		} catch (Exception e) {
			Object[] parameters = new Object[1];
			parameters[0] = vehicleId;
			handler.getException(e, "getEventsByVehicle", userSessionData,
					parameters);
		}
		return null;

	}

	/**
	 * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
	 * @ejb.interface-method
	 * @param id
	 * @param userSessionData
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 */
	public EventDataType findEventByPrimaryKey(Integer id,
			UserSessionData userSessionData) throws Exception {

		Session session = null;
		EventDataType data = null;

		try {
			session = HibernateUtil.currentSession();
			Event event;
			String queryStr = "select event from EventAbstract as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData) + "and event.id = :id ";

			if (userSessionData.isDriver()) {
				queryStr += " and event.driver.id = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId());
			}

			Query query = session.createQuery(queryStr);

			query.setParameter("id", id);
			Object result = query.uniqueResult();
			if (result != null) {
				event = (Event) result;
				data = event.getEventData();

				// if (userSessionData.isForest()){
				/*
				 * Recorro la lista de damages para que no haya Lazy
				 * Inicialization
				 */
				Set set = event.getEventDamages();
				if (set != null) {
					Iterator it = set.iterator();
					String str = "";
					while (it.hasNext()) {
						EventDamage ed = (EventDamage) it.next();
						str = ed.getDamage().toString() + ed.getId().toString();
					}
				}
				// }
			}

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.getException(e, "findEventByPrimaryKey", userSessionData, p);
		}
		return data;
	}

	/**
	 * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
	 * @ejb.interface-method
	 * @param id
	 * @param userSessionData
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 */
	public FineDataType findFineByPrimaryKey(Integer id,
			UserSessionData userSessionData) throws Exception {

		Session session = null;
		FineDataType data = null;

		try {
			session = HibernateUtil.currentSession();
			String queryStr = "select event from EventAbstract as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData) + "and event.id = :id ";

			if (userSessionData.isDriver()) {
				queryStr += " and event.driver.id = "
						+ String.valueOf(userSessionData.getDriverData()
								.getId());
			}

			Query query = session.createQuery(queryStr);

			query.setParameter("id", id);
			Object result = query.uniqueResult();
			if (result != null) {
				Fine event = (Fine) result;
				data = event.getFineData();
			}
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.getException(e, "findFineByPrimaryKey", userSessionData, p);
		}
		return data;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driveredit,driverviewowndata"
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
	public Collection findEventByDriver(UserSessionData userSessionData,
			Integer driverId) throws Exception {

		try {

			Session session = HibernateUtil.currentSession();

			String queryStr = "select event from EventAbstract as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData);

			queryStr += " and event.driver.id = (:driver))";

			Query query = session.createQuery(queryStr);
			if (userSessionData.isDriver()) {
				query.setParameter("driver", userSessionData.getDriverData()
						.getId());
			} else {
				query.setParameter("driver", driverId);
			}

			Collection result = new LinkedList();
			Iterator iter = query.list().iterator();
			while (iter.hasNext()) {
				EventAbstract event = (EventAbstract) iter.next();
				result.add(event.getMainAttributes());
			}

			return result;
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = driverId;
			handler.getException(e, "findEventByDriver", userSessionData, p);
		}
		return null;
	}

	/**
	 * Devuelve la multa dado el id del conductor y la fecha Si no existe
	 * devuelve null.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driveredit,driverviewowndata,eventupload"
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
	public boolean existFine(FineDataType fine, UserSessionData userSessionData)
			throws Exception {
		try {

			Session session = HibernateUtil.currentSession();

			String queryStr = "select count(*) AS c from genericevents ge join fines f on f.id = ge.id where ge.corporation = "
					+ userSessionData.getCorporationId();
			queryStr += " and ge.driver = " + fine.getDriver().getId();
			queryStr += " and ge.date = " + Util.formatSQL(fine.getDate());
			queryStr += " and date_trunc('MINUTE',ge.time) = "
					+ Util.getHour(fine.getHour());
			queryStr += " and f.qualify = '"
					+ fine.getQualify().getResourceBundleId() + "'";

			Query query = session.createSQLQuery(queryStr).addScalar("c",
					Hibernate.INTEGER);
			Integer c = (Integer) query.uniqueResult();
			return c.intValue() > 0;

		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = fine;
			handler.getException(e, "existEvent", userSessionData, p);
		}
		return false;
	}

	/**
	 * Devuelve el evento dado el id del conductor y la fecha Si no existe
	 * devuelve null.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driveredit,driverviewowndata,eventupload"
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 */
	public EventMainAttributes existEvent(UserSessionData userSessionData,
			Integer driverId, Date date) throws Exception {
		try {

			Session session = HibernateUtil.currentSession();

			String queryStr = "select event from EventAbstract as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData);

			queryStr += " and event.driver.id = (:driver) and event.date = (:date) ";

			Query query = session.createQuery(queryStr);
			query.setParameter("driver", driverId);
			query.setParameter("date", date);

			Collection result = new LinkedList();
			List list = query.list();
			if (list != null && list.size() > 0) {
				Iterator iter = list.iterator();
				EventAbstract event = (EventAbstract) iter.next();
				return event.getMainAttributes();
			}

			return null;

		} catch (Exception e) {
			Object[] p = { driverId };
			handler.getException(e, "existEvent", userSessionData, p);
		}
		return null;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,drivercreateevent,eventsupervise,eventupdate"
	 * @return Asigned Primery Key
	 */
	public boolean existEvent(Integer event, Integer driver, Integer vehicle,
			String plate, Date date, String type, int eventType,
			UserSessionData userSessionData) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			String sql = "SELECT COUNT(*) AS c FROM genericevents ge ";
			sql += (eventType == 3) ? "JOIN fines f ON f.id = ge.id "
					: "JOIN events e ON e.id = ge.id ";
			sql += "WHERE ge.driver = " + driver;
			sql += (vehicle != null) ? " AND ge.vehicle = " + vehicle
					: " AND ge.plate = '" + plate + "'";
			sql += " AND date_trunc('day', ge.date) = " + Util.formatSQL(date);
			switch (eventType) {
			case 0:
				sql += " AND e.accident = '" + type + "' ";
				break;
			case 1:
				sql += " AND e.incident = '" + type + "' ";
				break;
			case 2:
				sql += " AND e.nearmiss = '" + type + "' ";
				break;
			case 3:
				sql += " AND f.qualify = '" + type + "' ";
				break;
			}
			if (event != null)
				sql += " AND ge.id != " + event;
			SQLQuery query = session.createSQLQuery(sql).addScalar("c",
					Hibernate.INTEGER);
			return ((Integer) query.uniqueResult()).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			Object[] p = { event, driver, vehicle, plate, date, type, eventType };
			handler.getException(e, "existEvent", userSessionData, p);
		}
		return false;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public ListResult getNewReportEvents(boolean accidents, boolean incidents,
			boolean fines, boolean nearmiss, Integer fromMonth,
			Integer fromYear, Integer toMonth, Integer toYear,
			Integer[][] divOrgs, Integer[][] divGeos, String working,
			int first, int count, UserSessionData userSessionData,
			boolean detailed, String record, int driverStatus) throws Exception {
		ListResult result = new ListResult(new LinkedList(), 0);
		try {
			Session session = HibernateUtil.currentSession();
			StringBuffer query1 = new StringBuffer();
			if (accidents || incidents || nearmiss) {
				query1.append("SELECT ge.id,ge.date,1 AS type FROM genericevents ge JOIN events e ON ge.id = e.id");
				if (driverStatus != DriverData.ALL) {
					query1.append(" JOIN drivers d ON ge.driver = d.id");
				}
				query1.append(UtilityQuery.corporationRestrictionsSQL(divOrgs,
						divGeos[0], "ge", userSessionData));
				if (!(accidents && incidents && nearmiss)) {
					if (userSessionData.getCorporationId() == CorporationData.DPA) {
						if (!(accidents && incidents)) {
							if (accidents) {
								query1.append("AND e.collision != 'datatype.collisionclasification.incident' ");
							}
							if (incidents) {
								query1.append("AND e.collision = 'datatype.collisionclasification.incident' ");
							}
						}
					} else {
						query1.append("AND (");
						if (accidents) {
							query1.append("e.accident IS NOT NULL");
						}
						if (incidents) {
							if (accidents) {
								query1.append(" OR ");
							}
							query1.append("e.incident IS NOT NULL");
						}
						if (nearmiss) {
							if (accidents || incidents) {
								query1.append(" OR ");
							}
							query1.append("e.nearmiss IS NOT NULL");
						}
						query1.append(") ");
					}
				}
				if (working.length() > 0) {
					query1.append(" AND ge.working in (").append(working)
							.append(") ");
				}
				query1.append(getParameters(record, driverStatus, fromMonth,
						fromYear, toMonth, toYear));
			}
			if (fines) {
				if (accidents || incidents || nearmiss) {
					query1.append("UNION (");
				}
				query1.append("SELECT ge.id,ge.date,2 AS type FROM genericevents ge JOIN fines f ON ge.id = f.id");
				if (driverStatus != DriverData.ALL) {
					query1.append(" JOIN drivers d ON ge.driver = d.id");
				}
				query1.append(UtilityQuery.corporationRestrictionsSQL(divOrgs,
						divGeos[0], "ge", userSessionData));
				query1.append(getParameters(record, driverStatus, fromMonth,
						fromYear, toMonth, toYear));
				if (accidents || incidents || nearmiss) {
					query1.append(")");
				}
			}
			query1.append(" ORDER BY date DESC");

			Query queryIds = session.createSQLQuery(query1.toString())
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("date", Hibernate.DATE)
					.addScalar("type", Hibernate.INTEGER);
			Collection ids = queryIds.list();

			result.setTotal(ids.size());
			StringBuffer eventStr = new StringBuffer();
			StringBuffer fineStr = new StringBuffer();

			Iterator it = ids.iterator();
			int index = 1;
			while (it.hasNext()) {
				Object[] data = (Object[]) it.next();
				if (index > first && (count == -1 || index <= first + count)) {
					if (((Integer) data[2]).intValue() == 1) {
						if (eventStr.length() > 0) {
							eventStr.append(",");
						}
						eventStr.append(data[0]);
					} else {
						if (fineStr.length() > 0) {
							fineStr.append(",");
						}
						fineStr.append(data[0]);
					}
				}
				index++;
			}

			String[] s = (userSessionData.getCorporationId() == CorporationData.MONSANTO) ? userSessionData
					.getFilter().getFields("ge", 5) : userSessionData
					.getFilter().getFields("ge");
			if (eventStr.length() > 0) {
				result.getElements().addAll(
						getEventLines(eventStr.toString(), detailed, s,
								session, userSessionData));
			}
			if (fineStr.length() > 0) {
				result.getElements().addAll(
						getFineLines(fineStr.toString(), detailed, s, session,
								userSessionData));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	private Collection getEventLines(String ids, boolean detailed, String[] s,
			Session session, UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select ge.id as eventid, driver.id as driver_,ge.date as eventdate,driver.firstname as name, driver.lastname as lname,pais.resourcekey as country, ");
		queryStr.append(" event.accident,event.incident,event.nearmiss,ge.recomendation,event.story,ge.nonrecordable, event.superivisedcollision, event.superivisedaccident, event.superivisedincident");

		if (detailed) {
			queryStr.append(" ,ge.time as time,event.damage as damage,event.zone as zone,ge.working as working,event.policereport as policereport,event.collision as collision,event.handle as handle,event.hitrun as hitrun,event.principalFactor as pfactor,");
			queryStr.append(" event.collisionAngle as cangle,event.maxSpeed as mspeed,event.collisionSpeed as cspeed,event.vehicleSpeed as vspeed,event.reportedSpeed as rspeed,event.speedUnit as speedunit,event.selfbelt as selfbelt,");
			queryStr.append(" event.alcohol as alcohol,event.otherVehicles as ovehicles,event.cellular as cellular,event.way as way,event.direction as direction,event.control as control,ge.state as state,event.condition as condition,");
			queryStr.append(" event.climate as climate,event.lighting as lighting,event.cost as cost,event.driverstate as driverstate,event.dayslost as daylost,event.otherpartydayslost as otherpartydayslost,event.safefunccount as safefunccount,");
			queryStr.append(" event.safeothercount as safeothercount,event.injuredfunccount as injuredfunccount,event.injuredothercount as injuredothercount,event.fatalitiesfunccount as fatalitiesfunccount,event.fatalitiesothercount as fatalitiesothercount,event.pavement as pavement,");
			queryStr.append(" ge.plate as plate,ge.model as model,ge.trade as trade,ge.year as year,driver.birthDate as birthDate,driver.inclusionDate as inclusionDate,driver.licenseCategory as licenseCategory,driver.licenseExpiry as licenseExpiry,ge.companycar as companycar,");
			queryStr.append(" vehicle.matricula as matricula,vehicle.model as vmodel,vehicle.trade as vtrade,vehicle.year as vyear,ge.vehicle as vehicleId,ge.supervisionobservations as supobs,ge.city as city,ge.evitability as evitability, event.workinghours as workinghours, event.severity as severity, ");
			queryStr.append(" driver.corporationId as corporationId ");

			// CIMSA:
			queryStr.append(" ,event.realCost as realCost , event.companyAmount as companyAmount, event.driverAmount as driverAmount, event.thirdAmount as thirdAmount,event.whodrives as whodrives ");

			// Monsanto
			queryStr.append(" ,vehicle.type as vehicleType ");
			queryStr.append(" , driver.assignedVehicle as assignedVehicle ");

			queryStr.append(" , ge.points as points, ge.expiry as expiry, event.sapnumber as sapnumber, event.doublecabin as doublecabin, event.clasification1 as clasification1, event.clasification2 as clasification2, event.clasification3 as clasification3, event.clasification4 as clasification4, event.clasificationText as clasificationText, event.clasificationcepa1 as clasificationcepa1, event.clasificationcepa2 as clasificationcepa2, event.clasificationcepa3 as clasificationcepa3, event.clasificationcepa4 as clasificationcepa4, event.clasificationcepaText as clasificationcepaText");
			queryStr.append(",event.extracost1, event.extracost2, event.extracost3, event.alcoholbreath, event.stoppedvehicle, event.charged");
			queryStr.append(s[0]);

			queryStr.append(
					" from genericevents as ge left join vehicles as vehicle on ge.vehicle=vehicle.id,events as event, drivers as driver")
					.append(s[1]).append(",country pais ");
		} else {
			queryStr.append(",ge.supervisionobservations as supobs, event.extracost1, event.extracost2, event.extracost3, event.collision as collision,ge.evitability as evitability, event.clasification1 as clasification1, event.clasification2 as clasification2, event.clasification3 as clasification3, event.clasification4 as clasification4, event.clasificationText as clasificationText, event.clasificationcepa1 as clasificationcepa1, event.clasificationcepa2 as clasificationcepa2, event.clasificationcepa3 as clasificationcepa3, event.clasificationcepa4 as clasificationcepa4, event.clasificationcepaText as clasificationcepaText ");
			queryStr.append(s[0]);
			queryStr.append(
					" from genericevents as ge,events as event, drivers as driver")
					.append(s[1]).append(", country pais ");
		}

		queryStr.append(" where ge.id=event.id");
		queryStr.append(" and driver.id=ge.driver");
		queryStr.append(s[2]);
		queryStr.append(" and ge.country=pais.id ");

		queryStr.append(" and ge.id IN (" + ids + ") ORDER BY date DESC");
		SQLQuery query = session.createSQLQuery(queryStr.toString());
		StringTokenizer token = new StringTokenizer(s[3], ",");

		query = session.createSQLQuery(queryStr.toString());
		query.addScalar("eventid", Hibernate.INTEGER);
		query.addScalar("driver_", Hibernate.INTEGER);
		query.addScalar("eventdate", Hibernate.DATE);
		query.addScalar("name", Hibernate.STRING);
		query.addScalar("lname", Hibernate.STRING);
		query.addScalar("country", Hibernate.STRING);
		query.addScalar("accident", Hibernate.STRING);
		query.addScalar("incident", Hibernate.STRING);
		query.addScalar("nearmiss", Hibernate.STRING);
		query.addScalar("recomendation", Hibernate.STRING);
		query.addScalar("story", Hibernate.STRING);
		query.addScalar("nonrecordable", Hibernate.BOOLEAN);
		query.addScalar("superivisedcollision", Hibernate.STRING);
		query.addScalar("superivisedaccident", Hibernate.STRING);
		query.addScalar("superivisedincident", Hibernate.STRING);


		if (!detailed) {
			query.addScalar("supobs", Hibernate.STRING);
			query.addScalar("extracost1", Hibernate.INTEGER);
			query.addScalar("extracost2", Hibernate.INTEGER);
			query.addScalar("extracost3", Hibernate.INTEGER);
			query.addScalar("collision", Hibernate.STRING);
			query.addScalar("evitability", Hibernate.STRING);

			//Nueva clasificacion
			query.addScalar("clasification1", Hibernate.STRING);
			query.addScalar("clasification2", Hibernate.STRING);
			query.addScalar("clasification3", Hibernate.STRING);
			query.addScalar("clasification4", Hibernate.STRING);
			query.addScalar("clasificationText", Hibernate.STRING);

			query.addScalar("clasificationcepa1", Hibernate.STRING);
			query.addScalar("clasificationcepa2", Hibernate.STRING);
			query.addScalar("clasificationcepa3", Hibernate.STRING);
			query.addScalar("clasificationcepa4", Hibernate.STRING);
			query.addScalar("clasificationcepaText", Hibernate.STRING);

		} else {
			query.addScalar("time", Hibernate.TIME);
			query.addScalar("damage", Hibernate.BOOLEAN);
			query.addScalar("zone", Hibernate.STRING);
			query.addScalar("working", Hibernate.STRING);
			query.addScalar("policereport", Hibernate.STRING);
			query.addScalar("collision", Hibernate.STRING);
			query.addScalar("handle", Hibernate.STRING);
			query.addScalar("hitrun", Hibernate.STRING);
			query.addScalar("pfactor", Hibernate.STRING);
			query.addScalar("cangle", Hibernate.STRING);
			query.addScalar("mspeed", Hibernate.INTEGER);
			query.addScalar("cspeed", Hibernate.INTEGER);
			query.addScalar("vspeed", Hibernate.INTEGER);
			query.addScalar("rspeed", Hibernate.INTEGER);
			query.addScalar("speedunit", Hibernate.STRING);
			query.addScalar("selfbelt", Hibernate.STRING);
			query.addScalar("alcohol", Hibernate.STRING);
			query.addScalar("ovehicles", Hibernate.STRING);
			query.addScalar("cellular", Hibernate.STRING);
			query.addScalar("way", Hibernate.STRING);
			query.addScalar("direction", Hibernate.STRING);
			query.addScalar("control", Hibernate.STRING);
			query.addScalar("state", Hibernate.STRING);
			query.addScalar("condition", Hibernate.STRING);
			query.addScalar("climate", Hibernate.STRING);
			query.addScalar("lighting", Hibernate.STRING);
			query.addScalar("cost", Hibernate.INTEGER);
			query.addScalar("driverstate", Hibernate.STRING);
			query.addScalar("daylost", Hibernate.INTEGER);
			query.addScalar("otherpartydayslost", Hibernate.INTEGER);
			query.addScalar("safefunccount", Hibernate.INTEGER);
			query.addScalar("safeothercount", Hibernate.INTEGER);
			query.addScalar("injuredfunccount", Hibernate.INTEGER);
			query.addScalar("injuredothercount", Hibernate.INTEGER);
			query.addScalar("fatalitiesfunccount", Hibernate.INTEGER);
			query.addScalar("fatalitiesothercount", Hibernate.INTEGER);
			query.addScalar("pavement", Hibernate.STRING);
			query.addScalar("plate", Hibernate.STRING);
			query.addScalar("model", Hibernate.STRING);
			query.addScalar("trade", Hibernate.STRING);
			query.addScalar("year", Hibernate.STRING);
			query.addScalar("birthDate", Hibernate.DATE);
			query.addScalar("inclusionDate", Hibernate.DATE);
			query.addScalar("licenseCategory", Hibernate.STRING);
			query.addScalar("licenseExpiry", Hibernate.DATE);
			query.addScalar("companycar", Hibernate.BOOLEAN);
			query.addScalar("matricula", Hibernate.STRING);
			query.addScalar("vmodel", Hibernate.STRING);
			query.addScalar("vtrade", Hibernate.STRING);
			query.addScalar("vyear", Hibernate.STRING);
			query.addScalar("vehicleId", Hibernate.INTEGER);
			query.addScalar("supobs", Hibernate.STRING);
			query.addScalar("city", Hibernate.STRING);
			query.addScalar("evitability", Hibernate.STRING);
			query.addScalar("workinghours", Hibernate.INTEGER);
			query.addScalar("severity", Hibernate.INTEGER);
			query.addScalar("corporationId", Hibernate.STRING);

			// Pedido por CIMSA:
			query.addScalar("realCost", Hibernate.BOOLEAN);
			query.addScalar("companyAmount", Hibernate.INTEGER);
			query.addScalar("driverAmount", Hibernate.INTEGER);
			query.addScalar("thirdAmount", Hibernate.INTEGER);
			query.addScalar("whodrives", Hibernate.STRING);

			// Monsanto
			query.addScalar("vehicleType", Hibernate.STRING);
			query.addScalar("assignedVehicle", Hibernate.STRING);
			query.addScalar("points", Hibernate.INTEGER);
			query.addScalar("expiry", Hibernate.DATE);

			query.addScalar("sapnumber", Hibernate.INTEGER);
			query.addScalar("doublecabin", Hibernate.BOOLEAN);


			//Nueva clasificacion
			query.addScalar("clasification1", Hibernate.STRING);
			query.addScalar("clasification2", Hibernate.STRING);
			query.addScalar("clasification3", Hibernate.STRING);
			query.addScalar("clasification4", Hibernate.STRING);
			query.addScalar("clasificationText", Hibernate.STRING);

			query.addScalar("clasificationcepa1", Hibernate.STRING);
			query.addScalar("clasificationcepa2", Hibernate.STRING);
			query.addScalar("clasificationcepa3", Hibernate.STRING);
			query.addScalar("clasificationcepa4", Hibernate.STRING);
			query.addScalar("clasificationcepaText", Hibernate.STRING);

			query.addScalar("extracost1", Hibernate.INTEGER);
			query.addScalar("extracost2", Hibernate.INTEGER);
			query.addScalar("extracost3", Hibernate.INTEGER);
			query.addScalar("alcoholbreath", Hibernate.STRING);
			query.addScalar("stoppedvehicle", Hibernate.STRING);
			query.addScalar("charged", Hibernate.STRING);

		}

		while (token.hasMoreTokens()) {
			query.addScalar(token.nextToken(), Hibernate.STRING);
		}

		Iterator it = query.list().iterator();
		while (it.hasNext()) {
			if (!detailed)
				result.add(new SimpleReportEventAttributes(
						(Object[]) it.next(), EventDataType.ACCIDENT, true,
						userSessionData.getCorporationId()));
			else {
				Object[] data = (Object[]) it.next();
				DetailedReportEventAttributes aux = new DetailedReportEventAttributes(
						data, EventDataType.ACCIDENT,
						userSessionData.getCorporationId());
				/*
				 * if(userSessionData.getFilter().getCorporationId()==
				 * CorporationData.BAYER){ EventDataType event =
				 * findEventByPrimaryKey((Integer)data[0],userSessionData );
				 * aux.setSapNumber(event.getSapNumber()); }
				 */
				result.add(aux);
			}
		}
		return result;
	}

	private Collection getFineLines(String ids, boolean detailed, String[] s,
			Session session, UserSessionData userSessionData) throws Exception {
		Collection result = new LinkedList();
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select ge.id as eventid, driver.id as driver_,ge.date as eventdate,driver.firstname as name, driver.lastname as lname,pais.resourcekey as country, ");
		queryStr.append(" ge.recomendation as recomendation_,fine.qualify as qualify,fine.type as type,fine.observations as observations,ge.nonrecordable");

		if (detailed) {
			queryStr.append(" ,ge.time as time,ge.state as state,ge.plate as plate,ge.model as model,ge.trade as trade,ge.year as year,driver.birthDate as birthDate,driver.inclusionDate as inclusionDate,driver.licenseCategory as licenseCategory,driver.licenseExpiry as licenseExpiry,");
			queryStr.append(" ge.companycar as companycar,vehicle.matricula as matricula,vehicle.model as vmodel,vehicle.trade as vtrade,vehicle.year as vyear,fine.cost as fcost,ge.vehicle as vehicleId,ge.supervisionobservations as supobs, ge.city as city,ge.evitability as evitability, ");
			queryStr.append(" driver.corporationId as corporationId, vehicle.type as vehicleType, driver.assignedVehicle as assignedVehicle,fine.infringementNotice  as finnot, ge.points as points, fine.article as article, ge.expiry as expiry");
			queryStr.append(s[0]);
			queryStr.append(
					" from genericevents as ge left join vehicles as vehicle on ge.vehicle=vehicle.id,fines as fine, drivers as driver")
					.append(s[1]).append(", country pais ");
		} else {
			queryStr.append(",ge.supervisionobservations as supobs ");
			queryStr.append(s[0]);
			queryStr.append(
					" from genericevents as ge,fines as fine, drivers as driver")
					.append(s[1]).append(", country pais ");
		}
		queryStr.append(" where ge.id=fine.id");
		queryStr.append(" and driver.id=ge.driver");
		queryStr.append(s[2]);
		queryStr.append(" and ge.country=pais.id ");

		queryStr.append(" and ge.id IN (" + ids + ") ORDER BY date DESC");

		SQLQuery query = session.createSQLQuery(queryStr.toString());

		StringTokenizer token = new StringTokenizer(s[3], ",");
		query.addScalar("eventid", Hibernate.INTEGER);
		query.addScalar("driver_", Hibernate.INTEGER);
		query.addScalar("eventdate", Hibernate.DATE);
		query.addScalar("name", Hibernate.STRING);
		query.addScalar("lname", Hibernate.STRING);
		query.addScalar("country", Hibernate.STRING);
		query.addScalar("recomendation_", Hibernate.STRING);
		query.addScalar("qualify", Hibernate.STRING);
		query.addScalar("type", Hibernate.STRING);
		query.addScalar("observations", Hibernate.STRING);
		query.addScalar("nonrecordable", Hibernate.BOOLEAN);
		if (!detailed) {
			query.addScalar("supobs", Hibernate.STRING);
		} else {
			query.addScalar("time", Hibernate.TIME);
			query.addScalar("state", Hibernate.STRING);
			query.addScalar("plate", Hibernate.STRING);
			query.addScalar("model", Hibernate.STRING);
			query.addScalar("trade", Hibernate.STRING);
			query.addScalar("year", Hibernate.STRING);
			query.addScalar("birthDate", Hibernate.DATE);
			query.addScalar("inclusionDate", Hibernate.DATE);
			query.addScalar("licenseCategory", Hibernate.STRING);
			query.addScalar("licenseExpiry", Hibernate.DATE);
			query.addScalar("companycar", Hibernate.BOOLEAN);
			query.addScalar("matricula", Hibernate.STRING);
			query.addScalar("vmodel", Hibernate.STRING);
			query.addScalar("vtrade", Hibernate.STRING);
			query.addScalar("vyear", Hibernate.STRING);
			query.addScalar("fcost", Hibernate.INTEGER);
			query.addScalar("vehicleId", Hibernate.INTEGER);
			query.addScalar("city", Hibernate.STRING);
			query.addScalar("evitability", Hibernate.STRING);
			query.addScalar("corporationId", Hibernate.STRING);
			query.addScalar("vehicleType", Hibernate.STRING); // monsanto
			query.addScalar("assignedVehicle", Hibernate.STRING); // monsanto
			query.addScalar("finnot", Hibernate.STRING); // monsanto
			query.addScalar("points", Hibernate.INTEGER);
			query.addScalar("article", Hibernate.STRING); // monsanto
			query.addScalar("expiry", Hibernate.DATE); // monsanto
		}
		while (token.hasMoreTokens()) {
			query.addScalar(token.nextToken(), Hibernate.STRING);
		}

		Iterator it = query.list().iterator();
		while (it.hasNext()) {
			if (!detailed)
				result.add(new SimpleReportEventAttributes(
						(Object[]) it.next(), EventDataType.FINE, true,
						userSessionData.getCorporationId()));
			else
				result.add(new DetailedReportEventAttributes((Object[]) it
						.next(), EventDataType.FINE, userSessionData
						.getCorporationId()));
		}
		return result;
	}

	private String getParameters(String record, int driverStatus,
			Integer fromMonth, Integer fromYear, Integer toMonth, Integer toYear) {
		StringBuffer query1 = new StringBuffer();
		if (record.equals("nrecordable"))
			query1.append(" AND ge.nonrecordable = true");
		else if (record.equals("recordable"))
			query1.append(" AND ge.nonrecordable = false");
		query1.append(" AND ge.status <> " + EventDataType.PENDING_SUPERVISION);
		query1.append(" AND (EXTRACT(YEAR FROM ge.date) = '"
				+ String.valueOf(fromYear)
				+ "' AND EXTRACT(MONTH FROM ge.date) >= '" + fromMonth
				+ "' OR EXTRACT(YEAR FROM ge.date) > '"
				+ String.valueOf(fromYear) + "') ");
		query1.append(" AND (EXTRACT(YEAR FROM ge.date) = '"
				+ String.valueOf(toYear)
				+ "' AND EXTRACT(MONTH FROM ge.date) <= '" + toMonth
				+ "' OR EXTRACT(YEAR FROM ge.date) < '"
				+ String.valueOf(toYear) + "') ");

		if (driverStatus != DriverData.ALL) {
			if (driverStatus == DriverData.ACTIVE) {
				query1.append(" AND d.exclusionDate IS NULL ");
			} else {
				query1.append(" AND d.exclusionDate IS NOT NULL ");
			}
		}

		return query1.toString();
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public ListResult getReportEvents(boolean accidents, boolean incidents,
			boolean fines, Integer fromMonth, Integer fromYear,
			Integer toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, int first, int count,
			UserSessionData userSessionData, boolean detailed, String record,
			String driverStatus) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			StringBuffer queryStr = new StringBuffer("");
			StringBuffer queryStrTotal = new StringBuffer("");
			int totals = 0;
			String[] s = userSessionData.getFilter().getFields("ge");

			if ((fines) && (accidents) && (incidents)) {

				// Multas, Accidentes e Incidentes:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from genericevents as ge left join drivers as driver on ge.driver=driver.id");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append("  order by ge.date DESC");

			} else if ((accidents) && (fines)) {

				// Accidentes y Multas
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.incident is not null)) as ge left join drivers as driver on ge.driver=driver.id)");
				queryStrTotal
						.append(" LEFT OUTER JOIN events as event ON ge.id=event.id) LEFT OUTER JOIN fines as fine ON ge.id=fine.id ");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append("  order by ge.date DESC");

			} else if ((incidents) && (fines)) {

				// Incidentes y Multas:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.accident is not null)) as ge  left join drivers as driver on ge.driver=driver.id)");
				queryStrTotal
						.append(" LEFT OUTER JOIN events as event ON ge.id=event.id) LEFT OUTER JOIN fines as fine ON ge.id=fine.id ");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append(" order by ge.date DESC");

			} else if ((accidents) && (incidents)) {

				// Accidentes e Incidentes:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (genericevents as ge left join drivers as driver on ge.driver=driver.id),events as event");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal.append(" and event.id=ge.id");
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append(" and event.id=ge.id order by ge.date DESC");

			} else if (accidents) {

				// Solo accidentes:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (genericevents as ge left join drivers as driver on ge.driver=driver.id) ,events as event");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal
						.append(" and event.id=ge.id and event.accident is not null");
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append(" and event.id=ge.id and event.accident is not null order by ge.date DESC");

			} else if (incidents) {

				// Solo incidentes:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (genericevents as ge left join drivers as driver on ge.driver=driver.id),events as event");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal
						.append(" and event.id=ge.id and event.incident is not null");
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append(" and event.id=ge.id and event.incident is not null order by ge.date DESC");

			} else if (fines) {

				// Solo Multas:
				String divisionsRestictions = UtilityQuery
						.corporationDivisionsRestictionsSQL("ge",
								userSessionData);
				queryStrTotal
						.append("select count(*) as countevents from (genericevents as ge left join drivers as driver on ge.driver=driver.id),fines as fine");
				queryStrTotal.append(divisionsRestictions);
				queryStrTotal.append(" and fine.id=ge.id ");
				queryStrTotal = getReportEventsQuery(queryStrTotal, "ge",
						fromMonth, fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, false, false, false, detailed, record,
						s, driverStatus);
				queryStr = getReportEventsQuery(queryStr, "ge", fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						userSessionData, accidents, incidents, fines, detailed,
						record, s, driverStatus);
				queryStr.append(" order by ge.date DESC");
			} else {
				ListResult result = new ListResult(null, 0);
				return result;
			}

			Query query1 = session.createSQLQuery(queryStrTotal.toString())
					.addScalar("countevents", Hibernate.INTEGER);
			totals = ((Integer) query1.uniqueResult()).intValue();

			Query query = getDetailedQuery(queryStr.toString(), accidents,
					incidents, fines, session, detailed, s);

			if (count != -1) {
				query.setFirstResult(first);
				query.setMaxResults(count);
			}
			Collection result = new LinkedList();
			Iterator iter = query.list().iterator();
			while (iter.hasNext()) {
				Object[] row = (Object[]) iter.next();
				StringTokenizer token = new StringTokenizer(s[3], ",");
				String[] divOrgLabels = new String[token.countTokens()];
				int length = token.countTokens();
				for (int i = 0; i < length; i++) {
					divOrgLabels[i] = (String) row[i + 5];
				}
				SimpleReportEventAttributes eventAttributes;
				if (!detailed) {
					if (!fines) {
						eventAttributes = new SimpleReportEventAttributes(
								((Integer) row[0]).intValue(),
								((Integer) row[1]).intValue(), (Date) row[2],
								(String) row[3], (String) row[4], divOrgLabels,
								(String) row[length + 5],
								(String) row[length + 6],
								(String) row[length + 7],
								(String) row[length + 8],
								(String) row[length + 9], null, null, null,
								((Boolean) row[length + 10]).booleanValue(),
								(String) row[length + 11]);
					} else if ((accidents || incidents) && (fines)) {
						eventAttributes = new SimpleReportEventAttributes(
								((Integer) row[0]).intValue(),
								((Integer) row[1]).intValue(), (Date) row[2],
								(String) row[3], (String) row[4], divOrgLabels,
								(String) row[length + 5],
								(String) row[length + 6],
								(String) row[length + 7],
								(String) row[length + 8],
								(String) row[length + 9],
								(String) row[length + 10],
								(String) row[length + 11],
								(String) row[length + 12],
								((Boolean) row[length + 13]).booleanValue(),
								(String) row[length + 14]);
					} else {
						eventAttributes = new SimpleReportEventAttributes(
								((Integer) row[0]).intValue(),
								((Integer) row[1]).intValue(), (Date) row[2],
								(String) row[3], (String) row[4], divOrgLabels,
								(String) row[length + 5], null, null,
								(String) row[length + 6], null,
								(String) row[length + 7],
								(String) row[length + 8],
								(String) row[length + 9],
								((Boolean) row[length + 10]).booleanValue(),
								(String) row[length + 11]);
					}
				} else {
					eventAttributes = new DetailedReportEventAttributes(row,
							divOrgLabels, length, accidents, incidents, fines);
				}
				result.add(eventAttributes);
			}
			return new ListResult(result, totals);

		} catch (Exception e) {

			Object[] p = new Object[14];
			p[0] = accidents;
			p[1] = incidents;
			p[2] = fines;
			p[3] = fromMonth;
			p[4] = fromYear;
			p[5] = toMonth;
			p[6] = toYear;
			p[7] = divOrgs;
			p[8] = divGeos;
			p[9] = first;
			p[10] = count;
			p[11] = detailed;
			p[12] = record;
			p[13] = driverStatus;
			handler.getException(e, "getReportEvents", userSessionData, p);
		}
		return null;
	}

	public StringBuffer getReportEventsQuery(StringBuffer queryStr,
			String event, Integer fromMonth, Integer fromYear, Integer toMonth,
			Integer toYear, Integer[][] divOrgs, Integer[][] divGeos,
			UserSessionData userSessionData, boolean accidents,
			boolean incidents, boolean fines, boolean detailed, String record,
			String[] s, String driverStatus) {

		if (accidents || incidents || fines) {
			if (!fines) {

				// Solo accidentes e incidentes:
				queryStr.append(
						"select ge.id as eventid, driver.id as driver_,ge.date as eventdate,driver.firstname as name, driver.lastname as lname")
						.append(s[0]).append(",pais.resourcekey as country, ");
				queryStr.append(" event.accident as accident_,event.incident as incident_,ge.recomendation as recomendation_,event.story as story_,ge.nonrecordable");

				if (detailed) {
					queryStr.append(" ,ge.time as time,event.damage as damage,event.zone as zone,ge.working as working,event.policereport as policereport,event.collision as collision,event.handle as handle,event.hitrun as hitrun,event.principalFactor as pfactor,");
					queryStr.append(" event.collisionAngle as cangle,event.maxSpeed as mspeed,event.collisionSpeed as cspeed,event.vehicleSpeed as vspeed,event.reportedSpeed as rspeed,event.speedUnit as speedunit,event.selfbelt as selfbelt,");
					queryStr.append(" event.alcohol as alcohol,event.otherVehicles as ovehicles,event.cellular as cellular,event.way as way,event.direction as direction,event.control as control,ge.state as state,event.condition as condition,");
					queryStr.append(" event.climate as climate,event.lighting as lighting,event.cost as cost,event.driverstate as driverstate,event.dayslost as daylost,event.otherpartydayslost as otherpartydayslost,event.safefunccount as safefunccount,");
					queryStr.append(" event.safeothercount as safeothercount,event.injuredfunccount as injuredfunccount,event.injuredothercount as injuredothercount,event.fatalitiesfunccount as fatalitiesfunccount,event.fatalitiesothercount as fatalitiesothercount,event.pavement as pavement,");
					queryStr.append(" ge.plate as plate,ge.model as model,ge.trade as trade,ge.year as year,driver.birthDate as birthDate,driver.inclusionDate as inclusionDate,driver.licenseCategory as licenseCategory,driver.licenseExpiry as licenseExpiry,ge.companycar as companycar,");
					queryStr.append(" vehicle.matricula as matricula,vehicle.model as vmodel,vehicle.trade as vtrade,vehicle.year as vyear,ge.vehicle as vehicleId,ge.supervisionobservations as supobs,ge.city as city,ge.evitability as evitability, event.workinghours as workinghours, event.severity as severity, ");
					queryStr.append(" driver.corporationId as corporationId ");

					// CIMSA:
					queryStr.append(" ,event.realCost as realCost , event.companyAmount as companyAmount, event.driverAmount as driverAmount, event.thirdAmount as thirdAmount,event.whodrives as whodrives,event.doublecabin as doublecabin ");

					queryStr.append(
							" from genericevents as ge left join vehicles as vehicle on ge.vehicle=vehicle.id,events as event, drivers as driver")
							.append(s[1]).append(",country pais ");
				} else
					queryStr.append(
							",ge.supervisionobservations as supobs from genericevents as ge,events as event, drivers as driver")
							.append(s[1]).append(", country pais ");
				queryStr.append(UtilityQuery
						.corporationDivisionsRestictionsSQL(event,
								userSessionData));

			} else if ((accidents || incidents) && (fines)) {

				// Acidentes o incidentes y multas:

				queryStr.append(
						"select ge.id as eventid, driver.id as driver_,ge.date as eventdate,driver.firstname as name, driver.lastname as lname")
						.append(s[0]).append(",pais.resourcekey as country, ");
				queryStr.append(" event.accident as accident_,event.incident as incident_,ge.recomendation as recomendation_,event.story as story_,fine.qualify as qualify,fine.type as type,fine.observations as observations,ge.nonrecordable");

				if (detailed) {
					queryStr.append(" ,ge.time as time,event.damage as damage,event.zone as zone,ge.working as working,event.policereport as policereport,event.collision as collision,event.handle as handle,event.hitrun as hitrun,event.principalFactor as pfactor,");
					queryStr.append(" event.collisionAngle as cangle,event.maxSpeed as mspeed,event.collisionSpeed as cspeed,event.vehicleSpeed as vspeed,event.reportedSpeed as rspeed,event.speedUnit as speedunit,event.selfbelt as selfbelt,");
					queryStr.append(" event.alcohol as alcohol,event.otherVehicles as ovehicles,event.cellular as cellular,event.way as way,event.direction as direction,event.control as control,ge.state as state,event.condition as condition,");
					queryStr.append(" event.climate as climate,event.lighting as lighting,event.cost as cost,event.driverstate as driverstate,event.dayslost as daylost,event.otherpartydayslost as otherpartydayslost,event.safefunccount as safefunccount,");
					queryStr.append(" event.safeothercount as safeothercount,event.injuredfunccount as injuredfunccount,event.injuredothercount as injuredothercount,event.fatalitiesfunccount as fatalitiesfunccount,event.fatalitiesothercount as fatalitiesothercount,");
					queryStr.append(" event.pavement as pavement,ge.plate as plate,ge.model as model,ge.trade as trade,ge.year as year,driver.birthDate as birthDate,driver.inclusionDate as inclusionDate,driver.licenseCategory as licenseCategory,driver.");
					queryStr.append(" licenseExpiry as licenseExpiry,ge.companycar as companycar,vehicle.matricula as matricula,vehicle.model as vmodel,vehicle.trade as vtrade,vehicle.year as vyear, fine.cost as fcost,ge.vehicle as vehicleId,");
					queryStr.append(" ge.supervisionobservations as supobs,ge.city as city,ge.evitability as evitability, ");
					queryStr.append(" driver.corporationId as corporationId ");

					// CIMSA:
					queryStr.append(" ,event.realCost as realCost , event.companyAmount as companyAmount, event.driverAmount as driverAmount, event.thirdAmount as thirdAmount,event.whodrives as whodrives,event.doublecabin as doublecabin ");

					if (accidents && !incidents)
						queryStr.append(" from (((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.incident is not null)) as ge LEFT OUTER JOIN events as event ON ge.id=event.id) left join vehicles as vehicle on ge.vehicle=vehicle.id) ");
					else if (!accidents && incidents)
						queryStr.append(" from (((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.accident is not null)) as ge LEFT OUTER JOIN events as event ON ge.id=event.id) left join vehicles as vehicle on ge.vehicle=vehicle.id) ");
					else
						queryStr.append(" from ((genericevents as ge LEFT OUTER JOIN events as event ON ge.id=event.id) left join vehicles as vehicle on ge.vehicle=vehicle.id) ");

				} else {
					if (accidents && !incidents)
						queryStr.append(",ge.supervisionobservations as supobs from ((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.incident is not null)) as ge LEFT OUTER JOIN events as event ON ge.id=event.id)");
					else if (!accidents && incidents)
						queryStr.append(",ge.supervisionobservations as supobs from ((select * from genericevents where genericevents.id not in (select genericevents.id from genericevents,events where events.id=genericevents.id and events.accident is not null)) as ge LEFT OUTER JOIN events as event ON ge.id=event.id)");
					else
						queryStr.append(",ge.supervisionobservations as supobs from (genericevents as ge LEFT OUTER JOIN events as event ON ge.id=event.id)");
				}
				queryStr.append(
						" LEFT OUTER JOIN fines as fine ON ge.id=fine.id, drivers as driver")
						.append(s[1]).append(", country pais ");
				queryStr.append(UtilityQuery
						.corporationDivisionsRestictionsSQL(event,
								userSessionData));

			} else {

				// Solo multas:
				queryStr.append(
						"select ge.id as eventid, driver.id as driver_,ge.date as eventdate,driver.firstname as name, driver.lastname as lname")
						.append(s[0]).append(",pais.resourcekey as country, ");
				queryStr.append(" ge.recomendation as recomendation_,fine.qualify as qualify,fine.type as type,fine.observations as observations,ge.nonrecordable");

				if (detailed) {
					queryStr.append(" ,ge.time as time,ge.state as state,ge.plate as plate,ge.model as model,ge.trade as trade,ge.year as year,driver.birthDate as birthDate,driver.inclusionDate as inclusionDate,driver.licenseCategory as licenseCategory,driver.licenseExpiry as licenseExpiry,");
					queryStr.append(" ge.companycar as companycar,vehicle.matricula as matricula,vehicle.model as vmodel,vehicle.trade as vtrade,vehicle.year as vyear,fine.cost as fcost,ge.vehicle as vehicleId,ge.supervisionobservations as supobs, ge.city as city,ge.evitability as evitability, ");
					queryStr.append(" driver.corporationId as corporationId ");
					queryStr.append(
							" from genericevents as ge left join vehicles as vehicle on ge.vehicle=vehicle.id,fines as fine, drivers as driver")
							.append(s[1]).append(", country pais ");
				} else
					queryStr.append(
							",ge.supervisionobservations as supobs from genericevents as ge,fines as fine, drivers as driver")
							.append(s[1]).append(", country pais ");
				queryStr.append(UtilityQuery
						.corporationDivisionsRestictionsSQL(event,
								userSessionData));
				queryStr.append(" and ge.id=fine.id");
			}
			queryStr.append(" and driver.id=" + event + ".driver");
			queryStr.append(s[2]);
			queryStr.append(" and " + event + ".country=pais.id ");

		}
		switch (Integer.parseInt(driverStatus)) {
		case DriverData.ACTIVE:
			queryStr.append(" and driver.exclusionDate is null ");
			break;
		case DriverData.EXCLUDED:
			queryStr.append(" and driver.exclusionDate is not null ");
			break;
		}
		if (record.equals("nrecordable"))
			queryStr.append(" and " + event + ".nonrecordable = true");
		else if (record.equals("recordable"))
			queryStr.append(" and " + event + ".nonrecordable = false");
		queryStr.append(" and " + event + ".status <> 1");
		queryStr.append(" and (Extract(year from " + event + ".date) = '"
				+ String.valueOf(fromYear) + "' AND Extract(month from "
				+ event + ".date) >= '" + fromMonth + "' OR Extract(year from "
				+ event + ".date) > '" + String.valueOf(fromYear) + "') ");
		queryStr.append(" and (Extract(year from " + event + ".date) = '"
				+ String.valueOf(toYear) + "' AND Extract(month from " + event
				+ ".date) <= '" + toMonth + "' OR Extract(year from " + event
				+ ".date) < '" + String.valueOf(toYear) + "') ");

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and " + event + ".divOrg"
							+ String.valueOf(j + 1) + " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					queryStr.append(divOrgStr);
				}
			}
		}

		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr.append("and " + event + ".country in (");
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr.append(divGeos[0][i]);
					queryStr.append((i == divGeos[0].length - 1) ? ")" : ",");
				}
			}
			if (divGeos[1] != null && divGeos[1].length > 0) {
				queryStr.append("and " + event + ".geoLevel1 in (");
				for (int i = 0; i < divGeos[1].length; i++) {
					queryStr.append(divGeos[1][i]);
					queryStr.append((i == divGeos[1].length - 1) ? ")" : ",");
				}
			}
		}

		return queryStr;
	}

	/**
	 *
	 * @param queryStr
	 * @param accidents
	 * @param incidents
	 * @param fines
	 * @param session
	 * @param detailed
	 * @param s
	 * @return
	 */
	public Query getDetailedQuery(String queryStr, boolean accidents,
			boolean incidents, boolean fines, Session session,
			boolean detailed, String[] s) {

		SQLQuery query;
		StringTokenizer token = new StringTokenizer(s[3], ",");

		if (!fines) {

			// Accidentes e Incidentes (No multas)

			if (!detailed) {

				// Consulta de eventos resumida:

				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("accident_", Hibernate.STRING)
						.addScalar("incident_", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("story_", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("supobs", Hibernate.STRING);

				return query;

			} else {

				// Si es la consulta detallada de eventos:
				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("accident_", Hibernate.STRING)
						.addScalar("incident_", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("story_", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("time", Hibernate.TIME)
						.addScalar("damage", Hibernate.BOOLEAN)
						.addScalar("zone", Hibernate.STRING)
						.addScalar("working", Hibernate.STRING)
						.addScalar("policereport", Hibernate.STRING)
						.addScalar("collision", Hibernate.STRING)
						.addScalar("handle", Hibernate.STRING)
						.addScalar("hitrun", Hibernate.STRING)
						.addScalar("pfactor", Hibernate.STRING)
						.addScalar("cangle", Hibernate.STRING)
						.addScalar("mspeed", Hibernate.INTEGER)
						.addScalar("cspeed", Hibernate.INTEGER)
						.addScalar("vspeed", Hibernate.INTEGER)
						.addScalar("rspeed", Hibernate.INTEGER)
						.addScalar("speedunit", Hibernate.STRING)
						.addScalar("selfbelt", Hibernate.STRING)
						.addScalar("alcohol", Hibernate.STRING)
						.addScalar("ovehicles", Hibernate.STRING)
						.addScalar("cellular", Hibernate.STRING)
						.addScalar("way", Hibernate.STRING)
						.addScalar("direction", Hibernate.STRING)
						.addScalar("control", Hibernate.STRING)
						.addScalar("state", Hibernate.STRING)
						.addScalar("condition", Hibernate.STRING)
						.addScalar("climate", Hibernate.STRING)
						.addScalar("lighting", Hibernate.STRING)
						.addScalar("cost", Hibernate.INTEGER)
						.addScalar("driverstate", Hibernate.STRING)
						.addScalar("daylost", Hibernate.INTEGER)
						.addScalar("otherpartydayslost", Hibernate.INTEGER)
						.addScalar("safefunccount", Hibernate.INTEGER)
						.addScalar("safeothercount", Hibernate.INTEGER)
						.addScalar("injuredfunccount", Hibernate.INTEGER)
						.addScalar("injuredothercount", Hibernate.INTEGER)
						.addScalar("fatalitiesfunccount", Hibernate.INTEGER)
						.addScalar("fatalitiesothercount", Hibernate.INTEGER)
						.addScalar("pavement", Hibernate.STRING)
						.addScalar("plate", Hibernate.STRING)
						.addScalar("model", Hibernate.STRING)
						.addScalar("trade", Hibernate.STRING)
						.addScalar("year", Hibernate.STRING)
						.addScalar("birthDate", Hibernate.DATE)
						.addScalar("inclusionDate", Hibernate.DATE)
						.addScalar("licenseCategory", Hibernate.STRING)
						.addScalar("licenseExpiry", Hibernate.DATE)
						.addScalar("companycar", Hibernate.BOOLEAN)
						.addScalar("matricula", Hibernate.STRING)
						.addScalar("vmodel", Hibernate.STRING)
						.addScalar("vtrade", Hibernate.STRING)
						.addScalar("vyear", Hibernate.STRING)
						.addScalar("vehicleId", Hibernate.INTEGER)
						.addScalar("supobs", Hibernate.STRING)
						.addScalar("city", Hibernate.STRING)
						.addScalar("evitability", Hibernate.STRING)
						.addScalar("workinghours", Hibernate.INTEGER)
						.addScalar("severity", Hibernate.INTEGER)
						.addScalar("corporationId", Hibernate.STRING)

						// Pedido por CIMSA:
						.addScalar("realCost", Hibernate.BOOLEAN)
						.addScalar("companyAmount", Hibernate.INTEGER)
						.addScalar("driverAmount", Hibernate.INTEGER)
						.addScalar("thirdAmount", Hibernate.INTEGER)
						.addScalar("whodrives", Hibernate.STRING)
						.addScalar("doublecabin", Hibernate.BOOLEAN);

				return query;
			}

		} else if ((accidents || incidents) && (fines)) {
			if (!detailed) {
				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("accident_", Hibernate.STRING)
						.addScalar("incident_", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("story_", Hibernate.STRING)
						.addScalar("qualify", Hibernate.STRING)
						.addScalar("type", Hibernate.STRING)
						.addScalar("observations", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("supobs", Hibernate.STRING);
				return query;
			} else {
				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("accident_", Hibernate.STRING)
						.addScalar("incident_", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("story_", Hibernate.STRING)
						.addScalar("qualify", Hibernate.STRING)
						.addScalar("type", Hibernate.STRING)
						.addScalar("observations", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("time", Hibernate.TIME)
						.addScalar("damage", Hibernate.BOOLEAN)
						.addScalar("zone", Hibernate.STRING)
						.addScalar("working", Hibernate.STRING)
						.addScalar("policereport", Hibernate.STRING)
						.addScalar("collision", Hibernate.STRING)
						.addScalar("handle", Hibernate.STRING)
						.addScalar("hitrun", Hibernate.STRING)
						.addScalar("pfactor", Hibernate.STRING)
						.addScalar("cangle", Hibernate.STRING)
						.addScalar("mspeed", Hibernate.INTEGER)
						.addScalar("cspeed", Hibernate.INTEGER)
						.addScalar("vspeed", Hibernate.INTEGER)
						.addScalar("rspeed", Hibernate.INTEGER)
						.addScalar("speedunit", Hibernate.STRING)
						.addScalar("selfbelt", Hibernate.STRING)
						.addScalar("alcohol", Hibernate.STRING)
						.addScalar("ovehicles", Hibernate.STRING)
						.addScalar("cellular", Hibernate.STRING)
						.addScalar("way", Hibernate.STRING)
						.addScalar("direction", Hibernate.STRING)
						.addScalar("control", Hibernate.STRING)
						.addScalar("state", Hibernate.STRING)
						.addScalar("condition", Hibernate.STRING)
						.addScalar("climate", Hibernate.STRING)
						.addScalar("lighting", Hibernate.STRING)
						.addScalar("cost", Hibernate.INTEGER)
						.addScalar("driverstate", Hibernate.STRING)
						.addScalar("daylost", Hibernate.INTEGER)
						.addScalar("otherpartydayslost", Hibernate.INTEGER)
						.addScalar("safefunccount", Hibernate.INTEGER)
						.addScalar("safeothercount", Hibernate.INTEGER)
						.addScalar("injuredfunccount", Hibernate.INTEGER)
						.addScalar("injuredothercount", Hibernate.INTEGER)
						.addScalar("fatalitiesfunccount", Hibernate.INTEGER)
						.addScalar("fatalitiesothercount", Hibernate.INTEGER)
						.addScalar("pavement", Hibernate.STRING)
						.addScalar("plate", Hibernate.STRING)
						.addScalar("model", Hibernate.STRING)
						.addScalar("trade", Hibernate.STRING)
						.addScalar("year", Hibernate.STRING)
						.addScalar("birthDate", Hibernate.DATE)
						.addScalar("inclusionDate", Hibernate.DATE)
						.addScalar("licenseCategory", Hibernate.STRING)
						.addScalar("licenseExpiry", Hibernate.DATE)
						.addScalar("companycar", Hibernate.BOOLEAN)
						.addScalar("matricula", Hibernate.STRING)
						.addScalar("vmodel", Hibernate.STRING)
						.addScalar("vtrade", Hibernate.STRING)
						.addScalar("vyear", Hibernate.STRING)
						.addScalar("fcost", Hibernate.INTEGER)
						.addScalar("vehicleId", Hibernate.INTEGER)
						.addScalar("supobs", Hibernate.STRING)
						.addScalar("city", Hibernate.STRING)
						.addScalar("evitability", Hibernate.STRING)
						.addScalar("corporationId", Hibernate.STRING)

						// Pedido por CIMSA:
						.addScalar("realCost", Hibernate.BOOLEAN)
						.addScalar("companyAmount", Hibernate.INTEGER)
						.addScalar("driverAmount", Hibernate.INTEGER)
						.addScalar("thirdAmount", Hibernate.INTEGER)
						.addScalar("whodrives", Hibernate.STRING)
						.addScalar("doublecabin", Hibernate.BOOLEAN);
				return query;
			}
		} else {

			// Solo Multas:

			if (!detailed) {
				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("qualify", Hibernate.STRING)
						.addScalar("type", Hibernate.STRING)
						.addScalar("observations", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("supobs", Hibernate.STRING);
				return query;
			} else {
				query = session.createSQLQuery(queryStr.toString())
						.addScalar("eventid", Hibernate.INTEGER)
						.addScalar("driver_", Hibernate.INTEGER)
						.addScalar("eventdate", Hibernate.DATE)
						.addScalar("name", Hibernate.STRING)
						.addScalar("lname", Hibernate.STRING);
				while (token.hasMoreTokens()) {
					query.addScalar(token.nextToken(), Hibernate.STRING);
				}
				query.addScalar("country", Hibernate.STRING)
						.addScalar("recomendation_", Hibernate.STRING)
						.addScalar("qualify", Hibernate.STRING)
						.addScalar("type", Hibernate.STRING)
						.addScalar("observations", Hibernate.STRING)
						.addScalar("nonrecordable", Hibernate.BOOLEAN)
						.addScalar("time", Hibernate.TIME)
						.addScalar("state", Hibernate.STRING)
						.addScalar("plate", Hibernate.STRING)
						.addScalar("model", Hibernate.STRING)
						.addScalar("trade", Hibernate.STRING)
						.addScalar("year", Hibernate.STRING)
						.addScalar("birthDate", Hibernate.DATE)
						.addScalar("inclusionDate", Hibernate.DATE)
						.addScalar("licenseCategory", Hibernate.STRING)
						.addScalar("licenseExpiry", Hibernate.DATE)
						.addScalar("companycar", Hibernate.BOOLEAN)
						.addScalar("matricula", Hibernate.STRING)
						.addScalar("vmodel", Hibernate.STRING)
						.addScalar("vtrade", Hibernate.STRING)
						.addScalar("vyear", Hibernate.STRING)
						.addScalar("fcost", Hibernate.INTEGER)
						.addScalar("vehicleId", Hibernate.INTEGER)
						.addScalar("supobs", Hibernate.STRING)
						.addScalar("city", Hibernate.STRING)
						.addScalar("evitability", Hibernate.STRING)
						.addScalar("corporationId", Hibernate.STRING);
				return query;
			}
		}
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driverviewowndata,driverview,hrdbrasilquery,hrdphrdquery"
	 */
	public Collection getDriverHistoryData(String events, Integer driverId,
			UserSessionData userSessionData) throws Exception {
		try {
			Collection aux = this.getDriverViewData(events, driverId,
					userSessionData);
			Iterator it = aux.iterator();
			String date, type, clasification, result;
			Collection finalResult = new LinkedList();
			while (it.hasNext()) {
				EventDataType event = (EventDataType) it.next();
				if (event.getDate() == null) {
					date = "";
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(event.getDate());
					date = calendar.get(Calendar.DAY_OF_MONTH) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.YEAR);
				}
				if (event.getAccident() != null && event.getIncident() != null) {
					type = "generic.event.fine";
					clasification = event.getIncident().getResourceBundleId();
					result = event.getAccident().getResourceBundleId();
				} else if (event.getIncident() != null) {
					type = "generic.event.incident";
					clasification = event.getIncident().getResourceBundleId();
					if (event.isDamage()) {
						result = "query.accidenttype.damage";
					} else {
						result = "driver.history.withoutconsequences";
					}
				} else {
					type = "generic.event.accident";
					clasification = event.getAccident().getResourceBundleId();
					if ((event.getDriverstate() != null && event
							.getDriverstate().equals(
									"datatype.driverstate.dead"))
							|| (event.getFatalitiesfunccount() != null && event
									.getFatalitiesfunccount().intValue() != 0)
							|| (event.getFatalitiesothercount() != null && event
									.getFatalitiesothercount().intValue() != 0)) {
						result = "event.eventdata.functionaries.state.dead";
					} else if ((event.getDriverstate() != null && event
							.getDriverstate().equals(
									"datatype.driverstate.injured"))
							|| (event.getInjuredfunccount() != null && event
									.getInjuredfunccount().intValue() != 0)
							|| (event.getInjuredothercount() != null && event
									.getInjuredothercount().intValue() != 0)) {
						result = "event.eventdata.functionaries.state.injured";
					} else if (event.isDamage()) {
						result = "query.accidenttype.damage";
					} else {
						result = "driver.history.withoutconsequences";
					}
				}
				DriverHistoryDataType dhdt = new DriverHistoryDataType(date,
						type, clasification, result);
				finalResult.add(dhdt);
			}
			return finalResult;
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = events;
			p[1] = driverId;
			handler.getException(e, "getDriverHistoryData", userSessionData, p);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driverviewowndata,driverview,hrdbrasilquery,hrdphrdquery"
	 */
	public Collection getDriverViewData(String events, Integer driverId,
			UserSessionData userSessionData) throws Exception {
		try {

			Session session = HibernateUtil.currentSession();
			String queryStr;
			queryStr = null;
			if (events.equals("FINE")) {
				/**
				 * FUERON CAMBIADAS LAS RESTRICCIONES PARA PODER VER EL
				 * HISTORICO COMPLETO DEL CONDUCTOR A VER...
				 */
				// queryStr = "select event from Fine as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData);
				queryStr = "select event from Fine as event where event.corpCountry.pk.corporation.id = "
						+ userSessionData.getFilter().getCorporationId();
			} else if (events.equals("ACCIDENT")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.accident is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ userSessionData.getFilter().getCorporationId()
						+ " and event.accident is not null";
			} else if (events.equals("INCIDENT")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.incident is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ userSessionData.getFilter().getCorporationId()
						+ " and event.incident is not null";
			} else if (events.equals("NEARMISS")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.nearmiss is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ userSessionData.getFilter().getCorporationId()
						+ " and event.nearmiss is not null";
			} else {
				Collection result = new LinkedList();
				return result;
			}
			queryStr += " and event.status <> 1 ";
			queryStr += " and event.driver.id = " + driverId.toString();
			Query query = session.createQuery(queryStr);

			Collection result = new LinkedList();
			Iterator iter = query.list().iterator();
			while (iter.hasNext()) {
				if (events == "FINE") {
					Fine event = (Fine) iter.next();
					result.add(event.getEventData());
				} else if ((events == "ACCIDENT") || (events == "INCIDENT")
						|| (events == "NEARMISS")) {
					Event event = (Event) iter.next();
					result.add(event.getEventData());
				}
			}

			return result;
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = events;
			p[1] = driverId;
			handler.getException(e, "getDriverViewData", userSessionData, p);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driverviewowndata,driverview,hrdbrasilquery,hrdphrdquery"
	 */
	public Collection getDriverViewDataForestales(String events,
			Integer driverId, int index, UserSessionData userSessionData)
			throws Exception {
		try {

			Session session = HibernateUtil.currentSession();
			String queryStr;
			queryStr = null;
			int[] corporations = new int[] { CorporationData.FORESTALORIENTAL,
					CorporationData.WEYERHAEUSER,
					CorporationData.MONTES_DE_PLATA };
			if (events.equals("FINE")) {
				/**
				 * FUERON CAMBIADAS LAS RESTRICCIONES PARA PODER VER EL
				 * HISTORICO COMPLETO DEL CONDUCTOR A VER...
				 */
				// queryStr = "select event from Fine as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData);
				queryStr = "select event from Fine as event where event.corpCountry.pk.corporation.id = "
						+ corporations[index];
			} else if (events.equals("ACCIDENT")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.accident is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ corporations[index]
						+ " and event.accident is not null";
			} else if (events.equals("INCIDENT")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.incident is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ corporations[index]
						+ " and event.incident is not null";
			} else if (events.equals("NEARMISS")) {
				// queryStr = "select event from Event as event "+
				// UtilityQuery.corporationDivisionsRestictions("event",userSessionData)+" and event.nearmiss is not null";
				queryStr = "select event from Event as event where event.corpCountry.pk.corporation.id = "
						+ corporations[index]
						+ " and event.nearmiss is not null";
			} else {
				Collection result = new LinkedList();
				return result;
			}
			queryStr += " and event.status <> 1 ";
			queryStr += " and event.driver.id = " + driverId.toString();
			Query query = session.createQuery(queryStr);

			Collection result = new LinkedList();
			Iterator iter = query.list().iterator();
			while (iter.hasNext()) {
				if (events == "FINE") {
					Fine event = (Fine) iter.next();
					result.add(event.getEventData());
				} else if ((events == "ACCIDENT") || (events == "INCIDENT")
						|| (events == "NEARMISS")) {
					Event event = (Event) iter.next();
					result.add(event.getEventData());
				}
			}

			return result;
		} catch (Exception e) {
			Object[] p = new Object[2];
			p[0] = events;
			p[1] = driverId;
			handler.getException(e, "getDriverViewData", userSessionData, p);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public Collection getObservations(Integer eventId,
			UserSessionData userSessionData) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			String queryStr = "select log from EventLog as log "
					+ " where log.pk.event = (:event) order by log.pk.date ASC ";

			Query query = session.createQuery(queryStr);
			query.setParameter("event", eventId);

			List userColl = query.list();
			List result = new LinkedList();
			Iterator iter = userColl.iterator();
			while (iter.hasNext()) {
				EventLog log = (EventLog) iter.next();
				result.add(log.getEventObservations());
			}

			return result;
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = eventId;
			handler.getException(e, "getObservations", userSessionData, p);
		}
		return null;
	}

	/**
	 *
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public Collection getEventCount(String fromMonth, Integer fromYear,
			String toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, String vehicleType,
			UserSessionData userSessionData, int type, int order1, int order2,
			int order3, int order4) throws Exception {

		try {
			Query query;
			Session session = HibernateUtil.currentSession();
			if (type == EventDataType.FINE) {
				query = session.createQuery(getEventFinesCountQuery(fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						vehicleType, userSessionData, type, order1, order2,
						order3, order4));
			} else {
				query = session.createQuery(getEventCountQuery(fromMonth,
						fromYear, toMonth, toYear, divOrgs, divGeos,
						vehicleType, userSessionData, type, order1, order2,
						order3, order4));
			}
			return query.list();

		} catch (Exception e) {
			Object[] p = new Object[12];
			p[0] = fromMonth;
			p[1] = fromYear;
			p[2] = toMonth;
			p[3] = toYear;
			p[4] = divOrgs;
			p[5] = divGeos;
			p[6] = vehicleType;
			p[7] = type;
			p[8] = order1;
			p[9] = order2;
			p[10] = order3;
			p[11] = order4;
			handler.getException(e, " getEventCount", userSessionData, p);
		}
		return null;
	}

	public String getEventCountQuery(String fromMonth, Integer fromYear,
			String toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, String vehicleType,
			UserSessionData userSessionData, int type, int order1, int order2,
			int order3, int order4) {

		StringBuffer queryStr = new StringBuffer(
				"SELECT COUNT(*),SUM(event.cost)");
		String filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order1,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order2,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order3,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order4,
							1));
		}

		queryStr.append(" from Event as event ").append(
				UtilityQuery.corporationDivisionsRestictions("event",
						userSessionData));

		queryStr.append(" and (year(event.date) = ")
				.append(String.valueOf(fromYear))
				.append(" AND month(event.date) >= ").append(fromMonth)
				.append(" OR year(event.date) > ")
				.append(String.valueOf(fromYear) + ") ");
		queryStr.append(" and (year(event.date) = ")
				.append(String.valueOf(toYear))
				.append(" AND month(event.date) <= ").append(toMonth)
				.append(" OR year(event.date) < ")
				.append(String.valueOf(toYear) + ") ");

		if (!Util.empty(vehicleType)) {
			queryStr.append(
					" and (event.vehicle is null OR (event.vehicle is not null AND event.vehicle.type = '")
					.append(vehicleType + "'))");
		}

		switch (type) {
		case EventDataType.ACCIDENT:
			queryStr.append(" and event.accident is not null ");
			break;
		case EventDataType.INCIDENT:
			queryStr.append(" and event.incident is not null ");
			break;
		case EventDataType.NEARMISS:
			queryStr.append(" and event.nearmiss is not null ");
			break;
		}

		/* Modificacion de DivOrgs para Performance */
		/*
		 * if(divOrgs != null && divOrgs.length > 0) { for(int j = 0; j <
		 * divOrgs.length; j++) { if(divOrgs[j] != null && divOrgs[j].length >
		 * 0) {
		 * queryStr.append(" and event.divOrg"+String.valueOf(j+1)+" in (");
		 * for(int i = 0; i < divOrgs[j].length; i++) {
		 * queryStr.append(divOrgs[j][i]); queryStr.append((i ==
		 * divOrgs[j].length-1) ? ") " : ","); } } } }
		 */

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos

						// divOrgStr += divOrgs[j][i];
						// divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					// System.out.println(divOrgStr);
					queryStr.append(divOrgStr);
				}
			}
		}
		/* Modificacion de DivOrgs para Performance */

		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr.append(" and event.corpCountry.pk.country in (");
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr.append(divGeos[0][i]);
					queryStr.append((i == divGeos[0].length - 1) ? ")" : ",");
				}
			}
		}

		queryStr.append(" group by ");
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr.append("event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order1,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order2,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order3,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr.append(",event.")
					.append(filter)
					.append(",event.")
					.append(userSessionData.getFilter().getReportFilter(order4,
							1));
		}
		// queryStr.append(",event.plate,event.vehicle.id");
		return queryStr.toString();
	}

	/**
	 *
	 * @param fromMonth
	 * @param fromYear
	 * @param toMonth
	 * @param toYear
	 * @param divOrgs
	 * @param divGeos
	 * @param vehicleType
	 * @param userSessionData
	 * @param type
	 * @param order1
	 * @param order2
	 * @param order3
	 * @param order4
	 * @return
	 */
	public String getEventFinesCountQuery(String fromMonth, Integer fromYear,
			String toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, String vehicleType,
			UserSessionData userSessionData, int type, int order1, int order2,
			int order3, int order4) {

		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT COUNT(*),SUM(fine.cost)");
		String filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order1,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order2,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order3,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order4,
							1));
		}

		queryStr.append(" from Fine as fine "
				+ UtilityQuery.corporationDivisionsRestictions("fine",
						userSessionData));

		queryStr.append(" and (year(fine.date) = " + String.valueOf(fromYear)
				+ " AND month(fine.date) >= " + fromMonth
				+ " OR year(fine.date) > " + String.valueOf(fromYear) + ")");
		queryStr.append(" and (year(fine.date) = " + String.valueOf(toYear)
				+ " AND month(fine.date) <= " + toMonth
				+ " OR year(fine.date) < " + String.valueOf(toYear) + ")");

		if (!Util.empty(vehicleType)) {
			queryStr.append(" and (fine.vehicle is null OR (fine.vehicle is not null AND fine.vehicle.type = '"
					+ vehicleType + "'))");
		}

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and fine.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					System.out.println(divOrgStr);
					queryStr.append(divOrgStr);
				}
			}
		}

		/* Fin Modificacion DivOrgs */
		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr.append(" and fine.corpCountry.pk.country in (");
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr.append(divGeos[0][i]);
					queryStr.append((i == divGeos[0].length - 1) ? ")" : ",");
				}
			}
		}

		queryStr.append(" group by ");
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr.append("fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order1,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order2,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order3,
							1));
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr.append(",fine.")
					.append(filter)
					.append(",fine.")
					.append(userSessionData.getFilter().getReportFilter(order4,
							1));
		}

		return queryStr.toString();
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public Collection getReportFine(String fromMonth, Integer fromYear,
			String toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, UserSessionData userSessionData, int type)
			throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Query query = session.createQuery(getFineQuery(fromMonth, fromYear,
					toMonth, toYear, divOrgs, divGeos, userSessionData, type));
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(logFormatting.formattingText(e,
					"findList(String,UserSessionData,int,int,int)",
					"Exception", userSessionData.getFilter().getLoginName()), e);
			throw new DCException(e);
		}
	}

	/**
	 *
	 * @param fromMonth
	 * @param fromYear
	 * @param toMonth
	 * @param toYear
	 * @param divOrgs
	 * @param divGeos
	 * @param userSessionData
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private String getFineQuery(String fromMonth, Integer fromYear,
			String toMonth, Integer toYear, Integer[][] divOrgs,
			Integer[][] divGeos, UserSessionData userSessionData, int type)
			throws Exception {

		String queryType = (type == 1) ? "event.qualify.resourceBundleId"
				: "event.type.resourceBundleId";

		String queryStr = "select count(*),sum(event.cost)," + queryType;
		queryStr += ",event.divOrg3.id, event.divOrg3.resourceKey";
		queryStr += ",event.divOrg2.id,event.divOrg2.resourceKey";
		queryStr += ",event.divOrg2.divOrgItemLevel2.id,event.divOrg2.divOrgItemLevel2.resourceKey";
		queryStr += " from Fine as event "
				+ UtilityQuery.corporationDivisionsRestictions("event",
						userSessionData);

		queryStr += " and (year(event.date) = " + String.valueOf(fromYear)
				+ " AND month(event.date) >= " + fromMonth
				+ " OR year(event.date) > " + String.valueOf(fromYear) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(toYear)
				+ " AND month(event.date) <= " + toMonth
				+ " OR (year(event.date) < " + String.valueOf(toYear) + ") ";

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					System.out.println(divOrgStr);
					queryStr += divOrgStr;
				}
			}
		}

		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr += " and event.corpCountry.pk.country in (";
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr += divGeos[0][i];
					queryStr += (i == divGeos[0].length - 1) ? ")" : ",";
				}
			}
		}
		queryStr += " group by event.divOrg3.id, event.divOrg3.resourceKey,";
		queryStr += "event.divOrg2.id,event.divOrg2.resourceKey";
		queryStr += ",event.divOrg2.divOrgItemLevel2.id,event.divOrg2.divOrgItemLevel2.resourceKey,"
				+ queryType;

		return queryStr;

	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "query,administrator"
	 */
	public Collection getEventCountByType(String frommonth, Integer fromyear,
			String tomonth, Integer toyear, Integer[][] divOrgs,
			Integer[] divGeos, String events, UserSessionData userSessionData,
			int type, String record, int order1, int order2, int order3,
			int order4, Text messages) throws Exception {
		Collection elements = new LinkedList();
		try {
			Session session = HibernateUtil.currentSession();
			String event = "";
			switch (type) {
			case EventDataType.ACCIDENT:
				event = "accident";
				break;
			case EventDataType.INCIDENT:
				event = "incident";
				break;
			case EventDataType.NEARMISS:
				event = "nearmiss";
				break;
			}
			List list = null;
			if (type == EventDataType.INCIDENT) {
				Query query = session.createQuery(getEventTypeQuery(frommonth,
						fromyear, tomonth, toyear, divOrgs, divGeos, events,
						userSessionData, order1, order2, order3, order4,
						"incident", "f"));
				list = query.list();
				query = session.createQuery(getEventTypeQuery(frommonth,
						fromyear, tomonth, toyear, divOrgs, divGeos, events,
						userSessionData, order1, order2, order3, order4,
						"accident", "t"));
				list.addAll(query.list());
			} else {
				Query query = session.createQuery(getEventTypeQuery(frommonth,
						fromyear, tomonth, toyear, divOrgs, divGeos, events,
						userSessionData, order1, order2, order3, order4, event,
						"f"));
				list = query.list();
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object[] data = (Object[]) it.next();
				if (userSessionData.getCorporationId() != CorporationData.MONSANTO) {
					if (type == EventDataType.INCIDENT
							&& ((String) data[1])
									.startsWith("datatype.accident")) {
						data[1] = "event.accident.nonrecordable";
					}
				}
				if (((Boolean) data[data.length - 1]).booleanValue()) {
					data[1] = "event.accident.nonrecordable";
				}
				EventTypeData eventType = new EventTypeData(data, event,
						userSessionData.getFilter().getCorporationId()
								.intValue(), messages, order1, order2, order3,
						order4);
				Iterator elementIt = elements.iterator();
				boolean find = false;
				while (elementIt.hasNext() && !find) {
					EventTypeData element = (EventTypeData) elementIt.next();
					if (element.equalData(eventType)) {
						find = true;
						element.addData(eventType);
					}
				}
				if (!find) {
					elements.add(eventType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DCException(e);
		}
		return elements;
	}

	/**
	 * @param frommonth
	 * @param fromyear
	 * @param tomonth
	 * @param toyear
	 * @param divOrgs
	 * @param divGeos
	 * @param events
	 * @param userSessionData
	 * @param type
	 * @param order1
	 * @param order2
	 * @param order3
	 * @param order4
	 * @param event
	 * @return
	 */
	private String getEventTypeQuery(String frommonth, Integer fromyear,
			String tomonth, Integer toyear, Integer[][] divOrgs,
			Integer[] divGeos, String events, UserSessionData userSessionData,
			int order1, int order2, int order3, int order4, String event,
			String recordable) {
		String queryStr = "SELECT event.cost, event."
				+ event
				+ ".resourceBundleId,event.collision.resourceBundleId, "
				+ "event.damage, event.driverstate, event.fatalitiesothercount, "
				+ "event.injuredothercount, event.fatalitiesfunccount, event.injuredfunccount ";
		if (order1 != 4) {
			String filter = userSessionData.getFilter().getReportFilter(order1,
					0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order1, 1);
			}
		}
		if (order2 != 4) {
			String filter = userSessionData.getFilter().getReportFilter(order2,
					0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order2, 1);
			}
		}
		if (order3 != 4) {
			String filter = userSessionData.getFilter().getReportFilter(order3,
					0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order3, 1);
			}
		}
		if (order4 != 4) {
			String filter = userSessionData.getFilter().getReportFilter(order4,
					0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order4, 1);
			}
		}
		queryStr += ",event.nonrecordable FROM Event AS event ";
		queryStr += " WHERE event.corpCountry.pk.corporation.id = "
				+ userSessionData.getFilter().getCorporationId()
				+ " and (year(event.date) = " + String.valueOf(fromyear)
				+ " AND month(event.date) > "
				+ (Integer.parseInt(frommonth) - 1) + " OR year(event.date) > "
				+ String.valueOf(fromyear) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(toyear)
				+ " AND month(event.date) < " + (Integer.parseInt(tomonth) + 1)
				+ " OR year(event.date) < " + String.valueOf(toyear) + ") ";
		queryStr += " and event." + event + " is not null ";
		if (recordable.equals("f")) {
			queryStr += " and event." + event + " in (" + events + ")";
		}
		if (event.equals("accident")) {
			queryStr += " and event.nonrecordable = '" + recordable + "' ";
		}

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					System.out.println(divOrgStr);
					queryStr += divOrgStr;
				}
			}
		}

		if (divGeos != null) {
			if (divGeos.length > 0) {
				queryStr += "and event.corpCountry.pk.country in (";
				for (int i = 0; i < divGeos.length; i++) {
					queryStr += divGeos[i];
					queryStr += (i == divGeos.length - 1) ? ")" : ",";
				}
			}
		}
		return queryStr;
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "query,administrator"
	 */
	public Collection getReportEventType(String frommonth, Integer fromyear,
			String tomonth, Integer toyear, Integer[][] divOrgs,
			Integer[][] divGeos, String events,
			UserSessionData userSessionData, int type, String record)
			throws Exception {
		try {

			Session session = HibernateUtil.currentSession();

			String event = (type == EventDataType.ACCIDENT) ? "accident"
					: "incident";
			String queryStr = "select count(*), sum(event.cost), event."
					+ event
					+ ".resourceBundleId, "
					+ "event.damage, event.driverstate, sum(event.fatalitiesothercount), "
					+ "sum(event.injuredothercount), sum(event.fatalitiesfunccount), sum(event.injuredfunccount),year(event.date),month(event.date)"
					+ " from Event as event "
					+ UtilityQuery.corporationDivisionsRestictions("event",
							userSessionData);

			queryStr += " and (year(event.date) = " + String.valueOf(fromyear)
					+ " AND month(event.date) > "
					+ (Integer.parseInt(frommonth) - 1)
					+ " OR year(event.date) > " + String.valueOf(fromyear)
					+ ") ";
			queryStr += " and (year(event.date) = " + String.valueOf(toyear)
					+ " AND month(event.date) < "
					+ (Integer.parseInt(tomonth) + 1)
					+ " OR year(event.date) < " + String.valueOf(toyear) + ") ";
			queryStr += " and event." + event + " is not null ";
			queryStr += " and event." + event + " in (" + events + ")";
			if (record.equals("nrecordable"))
				queryStr += " and event.nonrecordable = true ";
			else if (record.equals("recordable"))
				queryStr += " and event.nonrecordable = false ";

			HashSet set = new HashSet();
			String divOrgStr = "";
			if (divOrgs != null && divOrgs.length > 0) {
				for (int j = 0; j < divOrgs.length; j++) {
					if (divOrgs[j] != null && divOrgs[j].length > 0) {
						divOrgStr = "";
						divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
								+ " in (";
						for (int i = 0; i < divOrgs[j].length; i++) {
							set.add(divOrgs[j][i]); // Para evitar repetidos
						}
						Iterator it = set.iterator();
						int i = 0;
						while (it.hasNext()) {
							String div = ((Integer) it.next()).toString();
							divOrgStr += div;
							divOrgStr += (i == set.size() - 1) ? ") " : ",";
							i++;
						}
						System.out.println(divOrgStr);
						queryStr += divOrgStr;
					}
				}
			}

			if (divGeos != null) {
				if (divGeos[0] != null && divGeos[0].length > 0) {
					queryStr += "and event.corpCountry.pk.country in (";
					for (int i = 0; i < divGeos[0].length; i++) {
						queryStr += divGeos[0][i];
						queryStr += (i == divGeos[0].length - 1) ? ")" : ",";
					}
				}
			}
			queryStr += "group by event."
					+ event
					+ ".resourceBundleId, "
					+ "event.damage, event.driverstate,year(event.date),month(event.date)";
			Query query = session.createQuery(queryStr);
			return query.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(logFormatting.formattingText(e,
					"findList(String,UserSessionData,int,int,int)",
					"Exception", userSessionData.getFilter().getLoginName()), e);
			throw new DCException(e);
		}
	}

	/**
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator,query"
	 */
	public Collection getReportFineType(String frommonth, Integer fromyear,
			String tomonth, Integer toyear, Integer[][] divOrgs,
			Integer[] divGeos, String fines, UserSessionData userSessionData,
			int order1, int order2, int order3, int order4, Text messages)
			throws Exception {
		Collection elements = new LinkedList();
		try {

			Session session = HibernateUtil.currentSession();

			String queryStr = "SELECT COUNT(*), SUM(event.cost), event.qualify.resourceBundleId, event.type.resourceBundleId ";
			if (order1 != 4) {
				String filter = userSessionData.getFilter().getReportFilter(
						order1, 0);
				if (filter != null) {
					queryStr += ",event."
							+ filter
							+ ",event."
							+ userSessionData.getFilter().getReportFilter(
									order1, 1);
				}
			}
			if (order2 != 4) {
				String filter = userSessionData.getFilter().getReportFilter(
						order2, 0);
				if (filter != null) {
					queryStr += ",event."
							+ filter
							+ ",event."
							+ userSessionData.getFilter().getReportFilter(
									order2, 1);
				}
			}
			if (order3 != 4) {
				String filter = userSessionData.getFilter().getReportFilter(
						order3, 0);
				if (filter != null) {
					queryStr += ",event."
							+ filter
							+ ",event."
							+ userSessionData.getFilter().getReportFilter(
									order3, 1);
				}
			}
			if (order4 != 4) {
				String filter = userSessionData.getFilter().getReportFilter(
						order4, 0);
				if (filter != null) {
					queryStr += ",event."
							+ filter
							+ ",event."
							+ userSessionData.getFilter().getReportFilter(
									order4, 1);
				}
			}
			queryStr += " FROM Fine AS event ";

			queryStr += " WHERE event.status<>1 AND event.corpCountry.pk.corporation.id = "
					+ userSessionData.getFilter().getCorporationId();
			queryStr += " AND (year(event.date) = " + String.valueOf(fromyear)
					+ " AND month(event.date) >= " + frommonth
					+ " OR year(event.date) > " + String.valueOf(fromyear)
					+ ") ";
			queryStr += " AND (year(event.date) = " + String.valueOf(toyear)
					+ " AND month(event.date) <= " + tomonth
					+ " OR year(event.date) < " + String.valueOf(toyear) + ") ";
			queryStr += " AND event.qualify IN (" + fines + ") ";

			HashSet set = new HashSet();
			String divOrgStr = "";
			if (divOrgs != null && divOrgs.length > 0) {
				for (int j = 0; j < divOrgs.length; j++) {
					if (divOrgs[j] != null && divOrgs[j].length > 0) {
						divOrgStr = "";
						divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
								+ " in (";
						for (int i = 0; i < divOrgs[j].length; i++) {
							set.add(divOrgs[j][i]); // Para evitar repetidos
						}

						if (set != null && set.size() > 0) {
							Iterator it = set.iterator();
							int i = 0;
							while (it.hasNext()) {
								String div = ((Integer) it.next()).toString();
								divOrgStr += div;
								divOrgStr += (i == set.size() - 1) ? ") " : ",";
								i++;
							}
						}

						System.out.println(divOrgStr);
						queryStr += divOrgStr;
					}
				}
			}

			if (divGeos != null) {
				if (divGeos.length > 0) {
					queryStr += "AND event.corpCountry.pk.country IN (";
					for (int i = 0; i < divGeos.length; i++) {
						queryStr += divGeos[i];
						queryStr += (i == divGeos.length - 1) ? ")" : ",";
					}
				}
			}
			queryStr += " GROUP BY event.qualify.resourceBundleId, event.type.resourceBundleId";
			String filter = userSessionData.getFilter().getReportFilter(order1,
					0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order1, 1);
			}
			filter = userSessionData.getFilter().getReportFilter(order2, 0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order2, 1);
			}
			filter = userSessionData.getFilter().getReportFilter(order3, 0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order3, 1);
			}
			filter = userSessionData.getFilter().getReportFilter(order4, 0);
			if (filter != null) {
				queryStr += ",event."
						+ filter
						+ ",event."
						+ userSessionData.getFilter()
								.getReportFilter(order4, 1);
			}

			Query query = session.createQuery(queryStr);
			List list = query.list();

			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					Object[] data = (Object[]) it.next();
					int count = (data[0] == null) ? 0 : ((Long) data[0])
							.intValue();
					int cost = (data[1] == null) ? 0 : ((Long) data[1])
							.intValue();
					String eventType = (String) data[2];
					String fineType = (String) data[3];
					Integer divOrg1Id = (data.length > 4) ? (Integer) data[4]
							: null;
					String divOrg1Name = (data.length > 5) ? (String) data[5]
							: null;
					if (order1 == 3) {
						divOrg1Name = messages.getText(divOrg1Name);
					}
					Integer divOrg2Id = (data.length > 6) ? (Integer) data[6]
							: null;
					String divOrg2Name = (data.length > 7) ? (String) data[7]
							: null;
					if (order2 == 3) {
						divOrg2Name = messages.getText(divOrg2Name);
					}
					Integer divOrg3Id = (data.length > 8) ? (Integer) data[8]
							: null;
					String divOrg3Name = (data.length > 9) ? (String) data[9]
							: null;
					if (order3 == 3) {
						divOrg3Name = messages.getText(divOrg3Name);
					}
					Integer divOrg4Id = (data.length > 10) ? (Integer) data[10]
							: null;
					String divOrg4Name = (data.length > 11) ? (String) data[11]
							: null;
					if (order4 == 3) {
						divOrg4Name = messages.getText(divOrg4Name);
					}
					FineTypeData fine = new FineTypeData(count, cost,
							eventType, fineType, divOrg1Id, divOrg1Name,
							divOrg2Id, divOrg2Name, divOrg3Id, divOrg3Name,
							divOrg4Id, divOrg4Name);
					Iterator elementIt = elements.iterator();
					boolean find = false;
					while (elementIt.hasNext() && !find) {
						FineTypeData element = (FineTypeData) elementIt.next();
						if (element.equalData(fine)) {
							find = true;
							element.addData(fine);
						}
					}
					if (!find) {
						elements.add(fine);
					}
				}
			}

		} catch (Exception e) {
			logger.error(logFormatting.formattingText(e,
					"findList(String,UserSessionData,int,int,int)",
					"Exception", userSessionData.getFilter().getLoginName()), e);
			throw new DCException(e);
		}
		return elements;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection getReportEventsSeverity(String fromMonth,
			Integer fromYear, String toMonth, Integer toYear,
			Integer[][] divOrgs, Integer[][] divGeos,
			UserSessionData userSessionData, int type, int order1, int order2,
			int order3, int order4, Text messages) throws Exception {

		Collection elements = new LinkedList();
		Session session = HibernateUtil.currentSession();

		String queryStr = "SELECT count(*), event.severity";

		String filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		queryStr += " FROM Event as event ";
		queryStr += UtilityQuery.corporationDivisionsRestictions("event",
				userSessionData);
		queryStr += " AND (event.accident IS NOT NULL) ";

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " AND event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					queryStr += divOrgStr;
				}
			}
		}

		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr += " AND event.corpCountry.pk.country IN (";
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr += divGeos[0][i];
					queryStr += (i == divGeos[0].length - 1) ? ")" : ",";
				}
			}
		}
		queryStr += " AND (year(event.date) = " + String.valueOf(fromYear)
				+ " AND month(event.date) >= " + fromMonth
				+ " OR year(event.date) > " + String.valueOf(fromYear) + ") ";
		queryStr += " AND (year(event.date) = " + String.valueOf(toYear)
				+ " AND month(event.date) <= " + toMonth
				+ " OR year(event.date) < " + String.valueOf(toYear) + ") ";
		queryStr += "group by event.severity ";
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		queryStr += "order by ";
		boolean coma = false;
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
			coma = true;
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			if (coma) {
				queryStr += ",";
			} else {
				coma = true;
			}
			queryStr += "event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			if (coma) {
				queryStr += ",";
			} else {
				coma = true;
			}
			queryStr += "event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			if (coma) {
				queryStr += ",";
			} else {
				coma = true;
			}
			queryStr += "event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		if (coma) {
			queryStr += ",";
		}
		queryStr += "event.severity";
		Query query = session.createQuery(queryStr);
		Iterator it = query.list().iterator();
		while (it.hasNext()) {
			Object[] dataObj = (Object[]) it.next();
			EventSeverityData data = new EventSeverityData(dataObj, order1,
					order2, order3, order4, messages);
			Iterator itElements = elements.iterator();
			boolean found = false;
			while (itElements.hasNext() && !found) {
				EventSeverityData element = (EventSeverityData) itElements
						.next();
				if (element.equalData(data)) {
					element.addData(dataObj);
					found = true;
				}
			}
			if (!found) {
				elements.add(data);
			}
		}
		return elements;

	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection getReportEventsByPeriod(String fromMonth,
			Integer fromYear, String toMonth, Integer toYear,
			Integer[][] divOrgs, Integer[][] divGeos,
			UserSessionData userSessionData, int type, int order1, int order2,
			int order3, int order4, Text messages) throws Exception {

		Collection elements = new LinkedList();
		Session session = HibernateUtil.currentSession();

		String from = " from Event as event ";
		String where = " ";
		switch (type) {
		case EventDataType.ACCIDENT:
			where = (userSessionData.getCorporationId() == CorporationData.DPA) ? " and event.collision != 'datatype.collisionclasification.incident' "
					: " and (event.accident is not null and event.nonrecordable = false) ";
			break;
		case EventDataType.INCIDENT:
			where = (userSessionData.getCorporationId() == CorporationData.DPA) ? " and event.collision = 'datatype.collisionclasification.incident' "
					: " and (event.incident is not null or event.nonrecordable = true) ";
			break;
		case EventDataType.NEARMISS:
			where = " and event.nearmiss is not null ";
			break;
		case EventDataType.FINE:
			from = " from Fine as event ";
			break;
		}
		String queryStr = "select count(*), month(event.date), year(event.date)";

		String filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		queryStr += from
				+ UtilityQuery.corporationDivisionsRestictions("event",
						userSessionData);

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					System.out.println(divOrgStr);
					queryStr += divOrgStr;
				}
			}
		}

		if (divGeos != null) {
			if (divGeos[0] != null && divGeos[0].length > 0) {
				queryStr += "and event.corpCountry.pk.country in (";
				for (int i = 0; i < divGeos[0].length; i++) {
					queryStr += divGeos[0][i];
					queryStr += (i == divGeos[0].length - 1) ? ")" : ",";
				}
			}
		}
		queryStr += " and (year(event.date) = " + String.valueOf(fromYear)
				+ " AND month(event.date) >= " + fromMonth
				+ " OR year(event.date) > " + String.valueOf(fromYear) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(toYear)
				+ " AND month(event.date) <= " + toMonth
				+ " OR year(event.date) < " + String.valueOf(toYear) + ") ";
		queryStr += where;
		queryStr += "group by month(event.date), year(event.date) ";
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		Query query = session.createQuery(queryStr);
		Iterator it = query.list().iterator();
		while (it.hasNext()) {
			Object[] dataObj = (Object[]) it.next();
			EventByPeriodData data = new EventByPeriodData(dataObj,
					Integer.parseInt(fromMonth), fromYear.intValue(),
					Integer.parseInt(toMonth), toYear.intValue(),
					String.valueOf(type), order1, order2, order3, order4,
					messages);
			Iterator itElements = elements.iterator();
			boolean found = false;
			while (itElements.hasNext() && !found) {
				EventByPeriodData element = (EventByPeriodData) itElements
						.next();
				if (element.equalData(data)) {
					element.addMonth(data);
					found = true;
				}
			}
			if (!found) {
				elements.add(data);
			}
		}
		return elements;

	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection getEventTotalCost(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, String fleets,
			UserSessionData userSessionData) throws Exception {
		Collection costs = new LinkedList();
		try {
			Session session = HibernateUtil.currentSession();
			Iterator it = session
					.createQuery(
							getTotalCostQuery(periodFrom, yearFrom, periodTo,
									yearTo, fleets, EventDataType.ACCIDENT,
									userSessionData)).list().iterator();
			while (it.hasNext()) {
				costs.add(new EventCostData((Object[]) it.next(),
						EventDataType.ACCIDENT));
			}
			it = session
					.createQuery(
							getTotalCostQuery(periodFrom, yearFrom, periodTo,
									yearTo, fleets, EventDataType.INCIDENT,
									userSessionData)).list().iterator();
			while (it.hasNext()) {
				EventCostData cost = new EventCostData((Object[]) it.next(),
						EventDataType.INCIDENT);
				Iterator itCosts = costs.iterator();
				boolean find = false;
				while (!find && itCosts.hasNext()) {
					EventCostData costData = (EventCostData) itCosts.next();
					if (costData.equalDivisions(cost)) {
						costData.setIncidentCost(cost.getIncidentCost());
						find = true;
					}
				}
				if (!find) {
					costs.add(cost);
				}
			}
			it = session
					.createQuery(
							getTotalCostQuery(periodFrom, yearFrom, periodTo,
									yearTo, fleets, EventDataType.NEARMISS,
									userSessionData)).list().iterator();
			while (it.hasNext()) {
				EventCostData cost = new EventCostData((Object[]) it.next(),
						EventDataType.NEARMISS);
				Iterator itCosts = costs.iterator();
				boolean find = false;
				while (!find && itCosts.hasNext()) {
					EventCostData costData = (EventCostData) itCosts.next();
					if (costData.equalDivisions(cost)) {
						costData.setNearmissCost(cost.getNearmissCost());
						find = true;
					}
				}
				if (!find) {
					costs.add(cost);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costs;
	}

	/**
	 * @param periodFrom
	 * @param yearFrom
	 * @param periodTo
	 * @param yearTo
	 * @param fleets
	 * @param type
	 * @return
	 */
	private String getTotalCostQuery(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, String fleets, int type,
			UserSessionData userSessionData) {
		String queryStr = "select sum(event.cost)";
		String filter = userSessionData.getFilter().getString(0, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(1, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(2, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		queryStr += " from Event event";
		queryStr += " where (year(event.date) = " + String.valueOf(yearFrom)
				+ " AND month(event.date) >= " + periodFrom
				+ " OR year(event.date) > " + String.valueOf(yearFrom) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(yearTo)
				+ " AND month(event.date) <= " + periodTo
				+ " OR year(event.date) < " + String.valueOf(yearTo) + ") ";
		// queryStr +=
		// " and event.divOrg1 in (select division.divOrg11 from  FleetDivision division where division.fleet.id in ("+fleets+")) ";
		queryStr += " and event.divOrg2 in (select division.divOrg21 from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		queryStr += " and event.divOrg3 in (select division.divOrg31 from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		queryStr += " and event.corpCountry.pk.country in (select division.country from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		if (type == EventDataType.ACCIDENT) {
			queryStr += " and event.accident is not null ";
		} else {
			queryStr += " and event.incident is not null ";
		}
		queryStr += "group by ";
		filter = userSessionData.getFilter().getString(0, 0);
		if (filter != null) {
			queryStr += "event." + filter;
		}
		filter = userSessionData.getFilter().getString(1, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(2, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		return queryStr;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection getEventCost(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, String fleets,
			UserSessionData userSessionData) throws Exception {

		Collection costs = new LinkedList();
		Iterator it = null;
		List list = null;
		EventCostData cost = null;
		EventCostData costData = null;
		boolean find = false;
		Iterator itCosts = null;
		Object[] data = null;

		try {
			Session session = HibernateUtil.currentSession();
			list = session.createQuery(
					getCostQuery(periodFrom, yearFrom, periodTo, yearTo,
							fleets, EventDataType.ACCIDENT, userSessionData))
					.list();
			if (list != null) {
				it = list.iterator();
				while (it.hasNext()) {
					data = (Object[]) it.next();
					costs.add(new EventCostData(data, EventDataType.ACCIDENT));
				}
			}

			list = session.createQuery(
					getCostQuery(periodFrom, yearFrom, periodTo, yearTo,
							fleets, EventDataType.INCIDENT, userSessionData))
					.list();
			if (list != null) {
				it = list.iterator();
				while (it.hasNext()) {
					cost = new EventCostData((Object[]) it.next(),
							EventDataType.INCIDENT);
					itCosts = costs.iterator();
					find = false;
					while (!find && itCosts.hasNext()) {
						costData = (EventCostData) itCosts.next();
						if (costData.equalDivisions(cost)) {
							costData.setIncidentCost(cost.getIncidentCost());
							find = true;
						}
					}
					if (!find) {
						costs.add(cost);
					}
				}
			}

			list = session.createQuery(
					getCostQuery(periodFrom, yearFrom, periodTo, yearTo,
							fleets, EventDataType.NEARMISS, userSessionData))
					.list();
			if (list != null) {
				it = list.iterator();
				while (it.hasNext()) {
					cost = new EventCostData((Object[]) it.next(),
							EventDataType.NEARMISS);
					itCosts = costs.iterator();
					find = false;
					while (!find && itCosts.hasNext()) {
						costData = (EventCostData) itCosts.next();
						if (costData.equalDivisions(cost)) {
							costData.setNearmissCost(cost.getNearmissCost());
							find = true;
						}
					}
					if (!find) {
						costs.add(cost);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costs;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "administrator,query"
	 */
	public Collection getInjuriesCount(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, int order1, int order2,
			int order3, int order4, Integer[][] divOrgs, Integer[] divGeos,
			UserSessionData userSessionData) throws Exception {
		Collection count = new LinkedList();
		try {
			Session session = HibernateUtil.currentSession();
			Iterator it = session
					.createQuery(
							getInjuriesQuery(periodFrom, yearFrom, periodTo,
									yearTo, order1, order2, order3, order4,
									divOrgs, divGeos, userSessionData)).list()
					.iterator();
			while (it.hasNext()) {
				Object[] data2 = (Object[]) it.next();
				count.add(new EventInjuriesData(data2, true));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * @param periodFrom
	 * @param yearFrom
	 * @param periodTo
	 * @param yearTo
	 * @param fleets
	 * @param type
	 * @return
	 */
	private String getCostQuery(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, String fleets, int type,
			UserSessionData userSessionData) {

		String queryStr = "select sum(event.cost)";
		String filter = userSessionData.getFilter().getString(0, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(1, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(2, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		queryStr += ",month(event.date),year(event.date) ";
		queryStr += "from Event event";

		queryStr += " where event.corpCountry.pk.corporation.id = "
				+ String.valueOf(userSessionData.getFilter().getCorporationId());
		queryStr += " and (year(event.date) = " + String.valueOf(yearFrom)
				+ " AND month(event.date) > "
				+ (Integer.parseInt(periodFrom) - 1)
				+ " OR year(event.date) > " + String.valueOf(yearFrom) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(yearTo)
				+ " AND month(event.date) < "
				+ (Integer.parseInt(periodTo) + 1) + " OR year(event.date) < "
				+ String.valueOf(yearTo) + ") ";
		queryStr += " and event.divOrg1 in (select division.divOrg11 from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		queryStr += " and event.divOrg2 in (select division.divOrg21 from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		queryStr += " and event.divOrg3 in (select division.divOrg31 from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		queryStr += " and event.corpCountry.pk.country in (select division.country from  FleetDivision division where division.fleet.id in ("
				+ fleets + ")) ";
		if (type == EventDataType.ACCIDENT) {
			queryStr += " and event.accident is not null ";
		} else if (type == EventDataType.INCIDENT) {
			queryStr += " and event.incident is not null ";
		} else {
			queryStr += " and event.nearmiss is not null ";
		}
		queryStr += "group by ";
		filter = userSessionData.getFilter().getString(0, 0);
		if (filter != null) {
			queryStr += "event." + filter;
		}
		filter = userSessionData.getFilter().getString(1, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		filter = userSessionData.getFilter().getString(2, 0);
		if (filter != null) {
			queryStr += ",event." + filter;
		}
		queryStr += ",month(event.date),year(event.date) ";
		System.out.println("DEBUG query: " + queryStr);
		return queryStr;
	}

	/**
	 * @param periodFrom
	 * @param yearFrom
	 * @param periodTo
	 * @param yearTo
	 * @param fleets
	 * @param type
	 * @return
	 */
	private String getInjuriesQuery(String periodFrom, Integer yearFrom,
			String periodTo, Integer yearTo, int order1, int order2,
			int order3, int order4, Integer[][] divOrgs, Integer[] divGeos,
			UserSessionData userSessionData) {
		String filter = null;
		String queryStr = "select count(*)";
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		queryStr += " from Event event ";

		queryStr += " where event.corpCountry.pk.corporation.id = "
				+ String.valueOf(userSessionData.getFilter().getCorporationId());
		queryStr += " and (year(event.date) = " + String.valueOf(yearFrom)
				+ " AND month(event.date) > "
				+ (Integer.parseInt(periodFrom) - 1)
				+ " OR year(event.date) > " + String.valueOf(yearFrom) + ") ";
		queryStr += " and (year(event.date) = " + String.valueOf(yearTo)
				+ " AND month(event.date) < "
				+ (Integer.parseInt(periodTo) + 1) + " OR year(event.date) < "
				+ String.valueOf(yearTo) + ") ";
		queryStr += " and event.status<>1 ";
		queryStr += " and event.nonrecordable = false ";
		if (divGeos.length > 0) {
			queryStr += " and event.corpCountry.pk.country in (";
			for (int i = 0; i < divGeos.length; i++) {
				queryStr += (i == 0) ? String.valueOf(divGeos[i]) : ","
						+ String.valueOf(divGeos[i]);
			}
			queryStr += ") ";
		}

		HashSet set = new HashSet();
		String divOrgStr = "";
		if (divOrgs != null && divOrgs.length > 0) {
			for (int j = 0; j < divOrgs.length; j++) {
				if (divOrgs[j] != null && divOrgs[j].length > 0) {
					divOrgStr = "";
					divOrgStr = " and event.divOrg" + String.valueOf(j + 1)
							+ " in (";
					for (int i = 0; i < divOrgs[j].length; i++) {
						set.add(divOrgs[j][i]); // Para evitar repetidos

						// divOrgStr += divOrgs[j][i];
						// divOrgStr += (i == divOrgs[j].length-1) ? ") " : ",";
					}
					Iterator it = set.iterator();
					int i = 0;
					while (it.hasNext()) {
						String div = ((Integer) it.next()).toString();
						divOrgStr += div;
						divOrgStr += (i == set.size() - 1) ? ") " : ",";
						i++;
					}
					System.out.println(divOrgStr);
					queryStr += divOrgStr;
				}
			}
		}

		queryStr += " and event.accident is not null ";
		queryStr += " and (event.injuredfunccount >0 OR event.fatalitiesfunccount >0 OR event.driverstate<>'datatype.driverstate.safe') ";

		queryStr += "group by ";
		filter = userSessionData.getFilter().getReportFilter(order1, 0);
		if (filter != null) {
			queryStr += " event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order1, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order2, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order2, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order3, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order3, 1);
		}
		filter = userSessionData.getFilter().getReportFilter(order4, 0);
		if (filter != null) {
			queryStr += ",event." + filter + ",event."
					+ userSessionData.getFilter().getReportFilter(order4, 1);
		}
		return queryStr;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "eventview,administrator,driveredit,driverviewowndata"
	 * @throws Exception
	 */
	public Integer getEventsPoints(Integer driver,
			UserSessionData userSessionData) throws Exception {
		int points = 0;
		try {
			Session session = HibernateUtil.currentSession();
			Query query = session.createSQLQuery(
					"SELECT points FROM genericevents ge  "
							+ "WHERE ge.driver = " + String.valueOf(driver))
					.addScalar("points", Hibernate.INTEGER);
			List list = query.list();
			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					try {
						points += ((Integer) it.next()).intValue();
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			Object[] p = { driver };
			handler.getException(e, "getEventsPoints", userSessionData, p);
		}
		return new Integer(points);
	}

	/**
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 * @throws CommunicationProblemException
	 * @ejb.interface-method
	 * @ejb.permission role-name =
	 *                 "administrator,eventcreate,vehicleview,drivervehicleview,driverodometer"
	 */
	public ListResult findTravelList(String value, int status,
			UserSessionData userRequest, int first, int count) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			String queryStr = "select count(*) from Travel as travel "
					+ UtilityQuery.corporationDivisionsRestictions(
							"travel.driver", userRequest);

			Query query = getQuery(session, queryStr, value, status,
					userRequest, null);
			Iterator iter = query.list().iterator();
			Integer total = (iter.hasNext()) ? new Integer(
					((Long) iter.next()).intValue()) : new Integer(0);

			queryStr = "select travel from Travel as travel "
					+ UtilityQuery.corporationDivisionsRestictions(
							"travel.driver", userRequest);

			String orderby = " firstName, lastName asc ";
			query = getQuery(session, queryStr, value, status, userRequest,
					orderby);
			if (count != -1) {
				if (total.intValue() < first) {
					first = (total.intValue() % count == 0) ? total.intValue()
							- count : total.intValue()
							- (total.intValue() % count);
				}
			}
			query.setFirstResult(first);
			if (count != -1) {
				query.setMaxResults(count);
			}

			Collection<TravelDataType> result = new LinkedList<TravelDataType>();
			iter = query.list().iterator();
			while (iter.hasNext()) {
				result.add(((Travel) iter.next()).getData());
			}
			return new ListResult(result, total.intValue());
		} catch (Exception e) {
			Object[] parameters = { value, first, count };
			handler.getException(e, "findList", userRequest, parameters);
		}
		return null;
	}

	private Query getQuery(Session session, String queryStr, String value,
			int status, UserSessionData userRequest, String orderby)
			throws Exception {
		try {
			if (value == null) {
				throw new InvalidDataException("getQuery", "value = null");
			}
			if (userRequest == null) {
				throw new DeslogedException("getQuery", "session = null");
			}

			queryStr += " and (lower(travel.driver.firstName) like lower(:value) OR lower(travel.driver.firstName) like lower(:value))";
			value = "%" + value + "%";
			if (status != 0) {
				queryStr += " and travel.state = :state ";
			}

			Query query = session.createQuery(queryStr);
			if (value.endsWith("%"))
				query.setParameter("value", value);
			else
				query.setParameter("value", new Integer(value));
			if (status != 0) {
				query.setParameter("state", new Integer(status));
			}

			return query;
		} catch (Exception e) {
			Object[] parameters = { queryStr, value };
			handler.getException(e, "getQuery", userRequest, parameters);
		}
		return null;
	}

	/**
	 * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
	 * @ejb.interface-method
	 * @param id
	 * @param userSessionData
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 */
	public TravelDataType findTravelByPrimaryKey(Integer id,
			UserSessionData userSessionData) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Travel travel = (Travel) session.load(Travel.class, id);
			if (travel != null) {
				return travel.getData();
			}
		} catch (Exception e) {
			Object[] parameters = { id };
			handler.getException(e, "findTravelByPrimaryKey", userSessionData,
					parameters);
		}
		return null;
	}

	/**
	 * @ejb.permission role-name = "administrator,eventedit,drivereventedit"
	 * @ejb.interface-method
	 * @param id
	 * @param userSessionData
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 */
	public Collection getTravelCheckPoints(Integer travel,
			UserSessionData userSessionData) throws Exception {
		Collection checks = new LinkedList();
		try {
			Session session = HibernateUtil.currentSession();
			Query q = session
					.createQuery("SELECT chp FROM CheckPoint chp WHERE chp.travel.id = "
							+ String.valueOf(travel));
			Iterator it = q.list().iterator();
			while (it.hasNext()) {
				checks.add(((CheckPoint) it.next()).getData());
			}
		} catch (Exception e) {
			Object[] parameters = { travel };
			handler.getException(e, "findTravelByPrimaryKey", userSessionData,
					parameters);
		}
		return checks;
	}

	/**
	 * @ejb.permission role-name = "administrator,viewrecomendation"
	 * @ejb.interface-method
	 */
	public EventDataType getEventRecomendation(Integer id,
			UserSessionData userSessionData) throws Exception {
		Session session = null;
		EventDataType data = null;

		try {
			session = HibernateUtil.currentSession();
			Event event;
			String queryStr = "select event from EventAbstract as event where event.id = :id ";

			Query query = session.createQuery(queryStr);

			query.setParameter("id", id);
			Object result = query.uniqueResult();
			if (result != null) {
				event = (Event) result;
				data = event.getEventData();
			}
		} catch (Exception e) {
			Object[] p = new Object[1];
			p[0] = id;
			handler.getException(e, "findEventByPrimaryKey", userSessionData, p);
		}
		return data;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "eventview,administrator"
	 */
	public Collection getEventEvitability(Integer driver,
			UserSessionData userSessionData) throws Exception {
		Collection list = new LinkedList();
		try {
			Session session = HibernateUtil.currentSession();
			Event event;
			String queryStr = "SELECT evitability, count(*) AS c FROM genericevents ge JOIN events e ON e.id = ge.id WHERE e.accident IS NOT NULL AND driver="
					+ driver
					+ " AND status = "
					+ EventDataType.SUPERVISED
					+ " GROUP BY evitability";
			Query query = session.createSQLQuery(queryStr)
					.addScalar("evitability", Hibernate.STRING)
					.addScalar("c", Hibernate.INTEGER);
			return query.list();
		} catch (Exception e) {
			Object[] p = { driver };
			handler.getException(e, "getEventEvitability", userSessionData, p);
		}
		return list;
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission role-name = "fleetinsertdata,administrator"
	 */
	public HashMap<String, int[]> getInsertedEvents(Integer fleet,
			Integer period, Integer year, boolean quarter,
			UserSessionData userSessionData) throws Exception {
		HashMap<String, int[]> map = new HashMap<String, int[]>();
		try {
			Session session = HibernateUtil.currentSession();
			String[] fleetKeys = UtilityQuery.getCorporationKey("ge",userSessionData.getCorporationId());
			String[] types = (userSessionData
					.isCorporation(CorporationData.MONSANTO)) ? new String[] {
					"accident", "incident", "nearmiss" } : new String[] {
					"accident", "incident" };
			String months = String.valueOf(period);
			if (quarter) {
				for (int i = period - 1; i > period - 3; i--)
					months = String.valueOf(i) + "," + months;
			}
			for (int i = 0; i < types.length; i++) {
				String sql = "SELECT " + fleetKeys[0] + ",count(*) AS c"
						+ " FROM genericevents ge " + fleetKeys[1]
						+ " JOIN events e ON e.id = ge.id ";
				sql += " WHERE " + types[i] + " IS NOT NULL ";
				if (i == 0
						&& userSessionData
								.isCorporation(CorporationData.MONSANTO)) {
					sql += "AND (ge.working = 't' OR ge.working = 'generic.messages.yes') ";
				}
				sql += " AND ge.corporation = "
						+ userSessionData.getCorporationId()
						+ " AND EXTRACT(month FROM date) IN ("
						+ months
						+ ")"
						+ " AND EXTRACT(year FROM date) = "
						+ year
						+ " AND country IN (SELECT country FROM fleetdivisions WHERE fleet = "
						+ fleet + ")" + " AND status = "
						+ EventDataType.SUPERVISED + " GROUP BY " + fleetKeys[0];
				SQLQuery query = session.createSQLQuery(sql);
				StringTokenizer tokenizer = new StringTokenizer(fleetKeys[2], ",");
				int count = 0;
				while (tokenizer.hasMoreTokens()) {
					query.addScalar(tokenizer.nextToken(), Hibernate.INTEGER);
					count++;
				}
				query.addScalar("c", Hibernate.INTEGER);
				Iterator it = query.list().iterator();
				while (it.hasNext()) {
					Object[] data = (Object[]) it.next();
					String key = "";
					for (int j = 0; j < count; j++) {
						key += String.valueOf(data[j]);
						if (j < count - 1) {
							key += ";";
						}
					}
					if (map.containsKey(key)) {
						map.get(key)[i] = ((Integer) data[count]).intValue();
					} else {
						int[] values = (userSessionData
								.isCorporation(CorporationData.MONSANTO)) ? new int[3]
								: new int[2];
						values[i] = ((Integer) data[count]).intValue();
						map.put(key, values);
					}
				}
			}
		} catch (Exception e) {
			Object[] p = { fleet, period, year };
			handler.getException(e, "getEventEvitability", userSessionData, p);
		}
		return map;
	}

	/**
	 * @throws Exception
	 * @ejb.permission role-name ="administrator,query"
	 * @ejb.interface-method
	 */
	public Collection getFinePoints(Date from, Date to, Integer[][] divOrgs, Integer[][] divGeos, String types, int status, UserSessionData userSessionData) throws Exception {
		try {
			String driverFilter = "";
			String eventFilter = "";
			if (from != null)
				eventFilter += " AND date_trunc('day', ge.date) >= " + Util.formatSQL(from, 0);
			if (to != null)
				eventFilter += " AND date_trunc('day', ge.date) < " + Util.formatSQL(to, 1);
			switch (status) {
				case DriverData.ACTIVE:
					driverFilter += " AND d.exclusiondate IS NULL ";
					break;
				case DriverData.EXCLUDED:
					driverFilter += " AND d.exclusiondate IS NOT NULL ";
					break;
			}

			String[] s = userSessionData.getFilter().getFields("d");
			String sql = "SELECT d.id, d.firstname, d.lastname, d.director, SUM(ge.points) AS points, COUNT(*) AS count, c.resourcekey AS countryname"
					+ s[0]
					+ " FROM drivers d, country c, genericevents ge, fines f "
					+ s[1];
			sql += UtilityQuery.corporationRestrictionsSQL(divOrgs, divGeos[0], "d", userSessionData);
			sql += " AND d.country = c.id AND ge.driver = d.id AND ge.id = f.id " + s[2];
			sql += driverFilter;
			sql += eventFilter;
			if (types != null) {
				sql += " AND f.type IN (" + types + ")";
			}
			sql += " GROUP BY d.id, firstname, lastname, director, countryname";
			StringTokenizer token = new StringTokenizer(s[3], ",");
			while (token.hasMoreTokens()) {
				sql += ", " + token.nextToken();
			}
			sql += " UNION (SELECT d.id, d.firstname, d.lastname, d.director, 0 AS points, 0 AS count, c.resourcekey AS countryname"
					+ s[0] + " FROM drivers d, country c" + s[1];
			sql += UtilityQuery.corporationRestrictionsSQL(divOrgs, divGeos[0], "d", userSessionData);
			sql += " AND d.country = c.id " + s[2];
			sql += driverFilter;
			sql += "AND d.id NOT IN (SELECT driver FROM genericevents ge JOIN fines f ON f.id = ge.id ";
			sql += UtilityQuery.corporationRestrictionsSQL(divOrgs, divGeos[0], "ge", userSessionData);
			sql += eventFilter+")";
			sql += ")";
			sql += " ORDER BY firstname,lastname";
			SQLQuery query = HibernateUtil.currentSession().createSQLQuery(sql);
			query.addScalar("id", Hibernate.INTEGER);
			query.addScalar("firstname", Hibernate.STRING);
			query.addScalar("lastname", Hibernate.STRING);
			query.addScalar("director", Hibernate.BOOLEAN);
			query.addScalar("points", Hibernate.INTEGER);
			query.addScalar("count", Hibernate.INTEGER);
			query.addScalar("countryname", Hibernate.STRING);
			token = new StringTokenizer(s[3], ",");
			while (token.hasMoreTokens()) {
				query.addScalar(token.nextToken(), Hibernate.STRING);
			}
			return query.list();
		} catch (Exception e) {
			Object[] p = { from, to, divOrgs, divGeos, status, types };
			handler.getException(e, "getFinePoints", userSessionData, null);
		}
		return null;
	}

	/**
	 * @throws Exception
	 * @ejb.permission role-name ="administrator,query"
	 * @ejb.interface-method
	 */
	public Collection getEcolabFines(Integer year, Integer dpto, Integer distrit, UserSessionData userSessionData) throws Exception {
		try {
			String sql = "SELECT d.id,f.type,f.qualify, EXTRACT(month FROM date) AS month,date,time,v.matricula,plate " +
					"FROM genericevents ge " +
					"JOIN fines f ON f.id = ge.id " +
					"JOIN drivers d ON d.id = ge.driver " +
					"LEFT JOIN vehicles v ON v.id = ge.vehicle " +
					"WHERE d.corporation = "+userSessionData.getCorporationId()+
/*Cambio ecolab dic*/					"AND d.exclusiondate IS NULL AND date >= '"+String.valueOf(year-1)+"-12-01' AND date < '"+String.valueOf(year)+"-12-01'";
/*Cambio ecolab ene					" AND date >= '"+String.valueOf(year)+"-01-01' AND date < '"+String.valueOf(year+1)+"-01-01'"+*/
			if(dpto != null)
				sql += " AND d.divorg1 = "+dpto;
			if(distrit != null)
				sql += " AND d.divorg2 = "+distrit;
			SQLQuery query = HibernateUtil.currentSession().createSQLQuery(sql);
			query.addScalar("id", Hibernate.INTEGER);
			query.addScalar("type", Hibernate.STRING);
			query.addScalar("qualify", Hibernate.STRING);
			query.addScalar("month", Hibernate.INTEGER);
			query.addScalar("date", Hibernate.DATE);
			query.addScalar("time", Hibernate.TIME);
			query.addScalar("matricula", Hibernate.STRING);
			query.addScalar("plate", Hibernate.STRING);
			return query.list();
		} catch (Exception e) {
			Object[] p = {year,dpto};
			handler.getException(e, "getEcolabFines", userSessionData, null);
		}
		return null;

	}

	/**
	 * @throws Exception
	 * @ejb.permission role-name ="administrator,query"
	 * @ejb.interface-method
	 */
	public Collection getEcolabEvitableAccidents(Integer year, Integer dpto, Integer distrit, UserSessionData userSessionData) throws Exception {
		try {
			String sql = "SELECT DISTINCT d.id,EXTRACT(month FROM date) AS month, COUNT(*) AS c " +
					"FROM genericevents ge " +
					"JOIN events e ON e.id = ge.id " +
					"JOIN drivers d ON d.id = ge.driver " +
					"WHERE d.corporation = "+userSessionData.getCorporationId()+
					" AND e.accident IS NOT NULL AND ge.evitability = 'generic.messages.yes' " +
/*Cambio ecolab dic*/					"AND d.exclusiondate IS NULL  AND date >= '"+String.valueOf(year-1)+"-12-01' AND date < '"+String.valueOf(year)+"-12-01'";
/*Cambio ecolab ene					" AND date >= '"+String.valueOf(year)+"-01-01' AND date < '"+String.valueOf(year+1)+"-01-01'"+*/
			if(dpto != null)
				sql += " AND d.divorg1 = "+dpto;
			if(distrit != null)
				sql += " AND d.divorg2 = "+distrit;
			sql += " GROUP BY d.id, month";
			SQLQuery query = HibernateUtil.currentSession().createSQLQuery(sql);
			query.addScalar("id", Hibernate.INTEGER);
			query.addScalar("month", Hibernate.INTEGER);
			query.addScalar("c", Hibernate.INTEGER);
			return query.list();
		} catch (Exception e) {
			Object[] p = {year,dpto};
			handler.getException(e, "getEcolabEvitableAccidents", userSessionData, null);
		}
		return null;

	}

  	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query"
     * @throws Exception
   	 */
	public ResumeItem getEventResume(UserSessionData userSessionData)  throws Exception {
		Calendar c = Calendar.getInstance();
		ResumeItem item = new ResumeItem("query.resume.events");
		try {
			String where = "WHERE ge.corporation = "+userSessionData.getCorporationId()+" AND ge.date >= '"+c.get(Calendar.YEAR)+"-01-01' ";
			String sql = "SELECT c.resourcekey AS country, 'generic.event.fine' AS type, f.qualify AS clasification, COUNT(*) AS count " +
					"FROM genericevents ge " +
					"JOIN country c ON c.id = ge.country " +
					"JOIN fines f ON f.id = ge.id "+
					where+
					"GROUP BY resourcekey, qualify " +
					"UNION (" +
					"SELECT c.resourcekey, e.clasification1, e.clasification4, COUNT(*)	" +
					"FROM genericevents ge " +
					"JOIN country c ON c.id = ge.country " +
					"JOIN events e ON e.id = ge.id "+
					where+"AND e.clasification1 = 'general.eventdata.accidentclasification2' " +
					"GROUP BY resourcekey, clasification1, clasification4) " +
					"UNION (" +
					"SELECT c.resourcekey, e.clasification1, e.clasification3, COUNT(*)	" +
					"FROM genericevents ge " +
					"JOIN country c ON c.id = ge.country " +
					"JOIN events e ON e.id = ge.id "+
					where+"AND e.clasification1 = 'general.eventdata.accidentclasification1' " +
					"GROUP BY resourcekey, clasification1, clasification3)";
	        Session session = HibernateUtil.currentSession();

	        SQLQuery q = session.createSQLQuery(sql);
	        q.addScalar("country", Hibernate.STRING);
	        q.addScalar("type", Hibernate.STRING);
	        q.addScalar("clasification", Hibernate.STRING);
	        q.addScalar("count", Hibernate.INTEGER);
	        Iterator it = q.list().iterator();
	        while(it.hasNext()) {
	        	item.addData(0,(Object[]) it.next());
	        }
        } catch(Exception e){
			Object[] p = {};
			handler.getException(e, "getDriverResume", userSessionData, p);
        }
		return item;
	}

  	/**
   	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,eventedit"
     * @throws Exception
   	 */
	public Collection<Object[]> getFiles(Integer event, UserSessionData userSessionData) throws Exception {
		Collection<Object[]> files = new LinkedList<Object[]>();
		try {
	        Session session = HibernateUtil.currentSession();

	        SQLQuery q = session.createSQLQuery("SELECT id, name, filetype FROM eventfiles WHERE event = "+event+" ORDER BY id");
	        q.addScalar("id", Hibernate.INTEGER);
	        q.addScalar("name", Hibernate.STRING);
	        q.addScalar("filetype", Hibernate.INTEGER);
	        files.addAll(q.list());
        } catch(Exception e){
			Object[] p = {};
			handler.getException(e, "getFiles", userSessionData, p);
        }
		return files;
	}
	
	/**
     * Returns menu options.
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,query,reparations,dpachecklist"
	 * @throws Exception
	 */
	public ReparationData findReparationByPrimaryKey(Integer reparationId, UserSessionData userSessionData) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();
			Reparation reparation = (Reparation) session.load(Reparation.class, reparationId);
			if(reparation != null) {
				ReparationData reparationData = reparation.getData();
				Query q = session.createQuery("SELECT item FROM ReparationItem item WHERE item.reparation = "+reparation.getId());
				Iterator it = q.iterate();
				while(it.hasNext()) {
					reparationData.getItems().add(((ReparationItem)it.next()).getData());
				}
				q = session.createQuery("SELECT comment FROM ReparationComment comment WHERE comment.reparation = "+reparation.getId());
				it = q.iterate();
				while(it.hasNext()) {
					ReparationComment comment = (ReparationComment)it.next();
					reparationData.addComment(comment.getMaintenance1(), comment.getComment());
				}
				return reparationData;
			}
		} catch(Exception e){
			Object[] p = {};
			handler.getException(e, "findReparationByPrimaryKey", userSessionData, p);
        }
		return null;
	}
}
