package communication.activity.tables;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.language.Text;

public class ActivityList {

	private Collection<Activity> values;
	private Text messages;

	public ActivityList(Collection activityList, Text messages) {
		values = new LinkedList<ActivityList.Activity>();
		Iterator it = activityList.iterator();
		while(it.hasNext()) {
			values.add(new Activity((String)it.next()));
		}
		this.messages = messages;
	}
	
	class Activity implements Comparable {
		
		private String text;

		public Activity(String text) {
			this.text = text;
		}
		
		public int compareTo(Object o) {
			return messages.getText(text).toLowerCase().compareTo(messages.getText(((Activity)o).text).toLowerCase());
		}
	}

	public String[] getArray() {
		Collections.sort((List)values);
		String[] s = new String[values.size()];
		Iterator it = values.iterator();
		int index =  0;
		while(it.hasNext()) {
			s[index] = ((Activity)it.next()).text;
			index++;
		}
		return s;
	}
}
