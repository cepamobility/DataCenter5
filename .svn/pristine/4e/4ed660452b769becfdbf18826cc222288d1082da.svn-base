package communication.util.tables;

import java.util.HashMap;
import java.util.Iterator;

import communication.language.Text;

public class ResumeItem {
	
	private String key;
	private HashMap<String, ResumeItem> list;
	private int count;

	public ResumeItem(String key) {
		this.key = key;
		count = 0;
	}
	
	public void addData(int index, Object[] data) {
		count += ((Integer)data[data.length-1]).intValue();
		if(index < data.length-1) {
			if(list == null)
				list = new HashMap<String, ResumeItem>();
			String key = (String)data[index];
			if(!list.containsKey(key)) {
				list.put(key, new ResumeItem(key));
			}
			list.get(key).addData(index+1, data);
		}
	}
	
	public String print(String pre, int deep, String type, Text messages, int top, String form) {
		String s = "";
		if(list != null) {
			int len = list.size();
			if(deep == 1) {
				s ="<div id='"+type+pre+"' class='style"+deep+"'>" +
						"<div style='float:left; align:left;'>"+
							"<a href=javascript:openClose('"+type+pre+"','"+type+"img"+pre+"',"+len+",'B')>" +
								"<img id='"+type+"img"+pre+"' width='15px;' src='/datacenter/menu/imgs/Bmas.png'>" +
							"</a>"+
							"<span class='texto1'>";
				s += (deep <= top) ? messages.getText(key) : key;
				s += ": <b>"+count+"</b></span>" +
						"</div>" +
						"<div style='float:right; align:right;'>"+
							"<a href='javascript:document.forms[\""+form+"\"].submit();'>"+
								"<img width='15px;' src='/datacenter/menu/imgs/external-link-on.png'>"+
							"</a>"+
						"</div>"+
					"</div>\n";
			}else {
				s ="<div id='"+type+pre+"' class='style"+deep+"'><a href=javascript:openClose('"+type+pre+"','"+type+"img"+pre+"',"+len+",'')><img id='"+type+"img"+pre+"' width='13px;' src='/datacenter/menu/imgs/mas.png'></a>";
				s += "<span class='texto2'>";
				s += (deep <= top) ? messages.getText(key) : key;
				s += ": <b>"+count+"</b></span></div>\n";
			}
			Iterator<ResumeItem> it = list.values().iterator();
			int index = 1;
			deep++;
			while(it.hasNext()) {
				s += it.next().print(pre+"_"+index, deep, type, messages, top, form);
				index++;
			}
		}else {
			s ="<div id='"+type+pre+"' class='style"+deep+"'>";
			s += "<span class='texto2'>";
			s += (deep <= top) ? messages.getText(key) : key;
			s += ": <b>"+count+"</b></span></div>\n";
		}
		return s;
	}
}
