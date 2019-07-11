package br.com.reinaldo.julianDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Regras {
	
	private static final String SEPARADOR = "\t";
	private static final String VAZIO = ".";
	private static final String PREFIXO_DIA = "[";
	private static final String SUFIXO_DIA = "]";
	private static final List<String> dias = new ArrayList<String>();
	
	/**
	 * Regras para criar o calendario
	 * @param param
	 */
	public static void criarCalendario(final String param, boolean isMesAtual, int diaHoje) {
		final Calendar calParam = Util.getCalendarFromStringParam(param);
		final int mesReferencia = calParam.get(Calendar.MONTH);
		final String mesExtenso = calParam.getDisplayName(Calendar.MONTH, Calendar.LONG, Util.LOCALE);
		final String anoExtenso = String.valueOf(calParam.get(Calendar.YEAR));
		
		StringBuilder semana = new StringBuilder();
		int diaSemana = 0;
		int dia = 1;
		boolean preencheuPrimeirosDias = false;
		// 33 porque quando o ultimo dia do mes nao eh sabado, o mes precisa mudar para inserir a ultima semana 
		while (dia < 33) {
			// Seta o dia do calendario para o valor da variavel dia (incremento de 1 a 32)
			calParam.set(Calendar.DAY_OF_MONTH, dia);
			// Se o mes mudou, imprime a ultima semana com os dados obtidos ate agora e sai do loop
			if (calParam.get(Calendar.MONTH) != mesReferencia) {
				dias.add(semana.toString());
				break; 
			}
			// Necessario saber se eh sabado para quebrar a linha. 
			// Tambem eh utilizado para calcular a quantidade de vazios na primeira semana
			diaSemana = calParam.get(Calendar.DAY_OF_WEEK);
			// Verifica se eh a primeira semana, se o dia comeca depois de domingo e insere a quantidade necessaria de vazios
			if (dia < 7 && ((diaSemana - dia) < 7 ) && !preencheuPrimeirosDias) {
				for (int i = 0; i < (diaSemana-1); i++) {
					semana.append(VAZIO);
					semana.append(SEPARADOR);
				}
				preencheuPrimeirosDias = true;
			}
			
			if (isMesAtual && (diaHoje == dia)) {
				semana.append((char)27 + "[31m");
			} 
			semana.append(PREFIXO_DIA);
			semana.append(dia);
			semana.append(SUFIXO_DIA);
			semana.append(calParam.get(Calendar.DAY_OF_YEAR));
			
			if (isMesAtual && (diaHoje == dia)) {
				semana.append((char)27 + "[0m");
				semana.append(" ");
			} else {
				semana.append(SEPARADOR);
			}
			
			
			// Se for sabado, finaliza a linha atual e comeca outra
			if (diaSemana == Calendar.SATURDAY) {
				dias.add(semana.toString());
				semana = null;
				semana = new StringBuilder();
			}
			dia++;
		}
		
		imprimirCalendario(mesExtenso, anoExtenso);
	}
	
	
	public static void criarCalendarioMesAtual() {
		final Calendar cal = Calendar.getInstance();
		final StringBuilder sb = new StringBuilder();
		int ano = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) + 1;
		sb.append(ano);
		if (mes < 10) {
			sb.append("0");
		}
		sb.append(mes);
		criarCalendario(sb.toString(), true, cal.get(Calendar.DAY_OF_MONTH));
		
	}
	
	/**
	 * Interno, sysout dos dias
	 */
	private static void imprimirCalendario(final String mes, final String ano) {
		Calendar cal  = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"), Util.LOCALE);
		System.out.println("\nHoje: " + cal.get(Calendar.DAY_OF_MONTH) + " de " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Util.LOCALE) 
				+ " de " + cal.get(Calendar.YEAR) + " / Dia Juliano: ["+ ((char)27 + "[31m") + cal.get(Calendar.DAY_OF_YEAR) + ((char)27 + "[0m") + "]");
		System.out.println("\nExibindo calendario para: " + ((char)27 + "[31m") + mes + " de " + ano + ((char)27 + "[0m"));
		System.out.println("\n\nDOM\tSEG\tTER\tQUA\tQUI\tSEX\tSAB");
		for (String atual : dias) {
			System.out.println(atual);
		}
		System.out.println("");
	}
	

}
