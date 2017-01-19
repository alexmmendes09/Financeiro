package br.com.financeiro.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	
	public static Integer mesAtual() {
		Date hoje  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		int mesAtual = Integer.parseInt(sdf.format(hoje));
		return mesAtual;
	}
	
	public static Integer anoAtual() {
		Date hoje  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		int anoAtual = Integer.parseInt(sdf.format(hoje));
		return anoAtual;
	}
	
	public static String mesAnoAtualTexto(){
		GregorianCalendar calendar = new GregorianCalendar();
		Calendar cal = Calendar.getInstance();
		String meses[] = {"Janeiro", "Fevereiro", 
	              "Março", "Abril", "Maio", "Junho", 
	              "Julho", "Agosto", "Setembro", "Outubro",
		      "Novembro", "Dezembro"};
		String mesAtual =  meses[calendar.get(GregorianCalendar.MONTH)];
		int anoAtual = cal.get(Calendar.YEAR);
		String mesAnoAtual = mesAtual+"/"+String.valueOf(anoAtual);
		return mesAnoAtual;
	}
}
