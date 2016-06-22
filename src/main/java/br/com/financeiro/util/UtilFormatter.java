package br.com.financeiro.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class UtilFormatter{

	static Locale ptBR = new Locale("pt", "BR");
	
	public static String formatReal(String source) {
		NumberFormat moedaFormat = 
				  NumberFormat.getCurrencyInstance(ptBR);
		return moedaFormat.format(new BigDecimal(source));
	}

	public static String formatDate(String source){
		DateFormat dateFormat = 
				  DateFormat.getDateInstance(DateFormat.FULL, ptBR);
		return dateFormat.format(new Date());
	}
}
