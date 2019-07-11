package br.com.reinaldo.julianDate;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

	public static final String PATTERN = "yyyyMM";
	public static final Locale LOCALE = new Locale("pt", "BR"); 
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat(PATTERN, LOCALE);
	
	/**
	 * yyyyMM
	 * @return
	 */
	public static Date getDateFromStringParam(final String param) {
		try {
			return FORMAT.parse(param);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * yyyyMM
	 * @return
	 */
	public static Calendar getCalendarFromStringParam(final String param) {
		final Calendar cal = Calendar.getInstance(LOCALE);
		final Date data = getDateFromStringParam(param);
		if (data != null) {
			cal.setTime(data);
			return cal;
		}
		return null;
	}
	
	/** Textos da janela */
	public static final String JANELA_TITULO = "";
	public static final int JANELA_W = 400;
	public static final int JANELA_H = 400;
	public static final Dimension JANELA_DIMENSION = new Dimension(JANELA_W, JANELA_H);
	
	
	
}
