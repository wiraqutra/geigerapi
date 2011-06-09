package com.appspot.geigerapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GeigerapiServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
//		doPost(req,resp);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(GeigerData.class);
		String order = req.getParameter("order") == null ? "datetime" : req.getParameter("order");
		query.setOrdering(order);
		
		@SuppressWarnings("unchecked")
		List<GeigerData> dataList = (List<GeigerData>)query.execute();
		
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		for(GeigerData geigerData:dataList){
			String datetimeStr = GeigerData.DATE_FORMAT.format(geigerData.getDatetime());
			String label = geigerData.getLabel();
			String type = Integer.toString(geigerData.getValuetype());
			String latStr = Double.toString(geigerData.getLat());
			String lonStr = Double.toString(geigerData.getLon());
			String radioStr = Double.toString(geigerData.getRadiovalue());
			writer.println(datetimeStr+","+label+","+type+","+latStr+","+lonStr+","+radioStr);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		req.setCharacterEncoding("UTF-8");
		
		Date datetime = new Date();
		try {
			datetime = GeigerData.DATE_FORMAT.parse(req.getParameter("datetime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String label = req.getParameter("label");
		int valuetype = Integer.parseInt(req.getParameter("valuetype"));
		double radiovalue = Double.parseDouble(req.getParameter("radiovalue"));
		double lat = Double.parseDouble(req.getParameter("lat"));
		double lon = Double.parseDouble(req.getParameter("lon"));
		
		resp.setContentType("text/plain");
		resp.getWriter().println("datetime = "+datetime.toString());
		resp.getWriter().println("label = " + label);
		resp.getWriter().println("valuetype = " + valuetype);
		resp.getWriter().println("radiovalue = " + radiovalue);
		resp.getWriter().println("lat = " + lat);
		resp.getWriter().println("lon = " + lon);
		GeigerData mGeigerData = new GeigerData(datetime,label,valuetype,radiovalue,lat,lon);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(mGeigerData);
		}finally{
			pm.close();
		}
		
	}
	
}
