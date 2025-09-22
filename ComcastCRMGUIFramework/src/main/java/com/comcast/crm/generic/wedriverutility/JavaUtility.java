package com.comcast.crm.generic.wedriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNo() {
		Random random= new Random();
	int randomno= random.nextInt(5000);
	return randomno;
	}
	
	public String getSystemDateYYYYMMDD() {
		Date dateobj= new Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
			String actDate = sim.format(dateobj);
			return actDate;
	}
			
	public String getRequiredDateYYYMMDD(int days) {
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal= sim.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			String reqdate = sim.format(cal.getTime());
			return reqdate;
		
	}

		
		
	}

