package com.thegame.server.common.utils;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author E103880
 */
public enum Periodicity {

	YEARLY(365,TimeUnit.DAYS,"yyyy",86400000),
	MONTHLY(30,TimeUnit.DAYS,"yyyy/MM",86400000),
	WEEKLY(7,TimeUnit.DAYS,"yyyy/MM/W",86400000),
	DAILY(1,TimeUnit.DAYS,"yyyy/MM/dd",86400000),
	HOURLY(1,TimeUnit.HOURS,"yyyy/MM/dd-HH",3600000),
	MINUTELY(1,TimeUnit.MINUTES,"yyyy/MM/dd-HH:mm",60000),
	SECONDLY(1,TimeUnit.SECONDS,"yyyy/MM/dd-HH:mm:ss",1000),
	;
	
	public final int amount;
	public final TimeUnit unit;
	public final String format;
	public final int millisecondsConverter;
	
	Periodicity(final int _amount,final TimeUnit _unit,final String _format,final int _millisecondsConverter){
		this.amount=_amount;
		this.unit=_unit;
		this.format=_format;
		this.millisecondsConverter=_millisecondsConverter;
	}

	public SimpleDateFormat getFormat() {
		return new SimpleDateFormat(this.format);
	}	
	public long getMilliseconds() {
		return ((long)this.amount)*((long)this.millisecondsConverter);
	}	
}
