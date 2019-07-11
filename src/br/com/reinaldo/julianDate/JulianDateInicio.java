package br.com.reinaldo.julianDate;

import java.util.Date;

/**
 * Esse programa funciona apenas por console
 * recebe um ano e um mes como parametro e imprime o calendario Juliano fundido ao calendario normal
 * Formato do parametro yyyyMM
 * @author rs33196
 *
 */
public class JulianDateInicio {

	public static void main(String[] args) {
		if (!validarParametros(args)) {
			imprimirAjuda();
			return;
		} else if (isJanela(args)) {
			Janela j = new Janela();
			j.setVisible(true);
		} else {
			if (args != null && args.length > 0) {
				Regras.criarCalendario(args[0], false, 0);
			} else {
				Regras.criarCalendarioMesAtual();
			}
			
		}
	}
	
	
	/**
	 * Verifica se os parametros informados estao corretos
	 * @param args
	 * @return
	 */
	private static boolean validarParametros(String[] args) {
		if (args != null && args.length > 0) {
			final Date data = Util.getDateFromStringParam(args[0]);
			if (data == null) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isJanela(String[] args) {
		for (String atual : args) {
			if ("-j".equalsIgnoreCase(atual.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Imprime no console um texto de ajuda sobre como utilizar este programa
	 */
	private static void imprimirAjuda() {
		System.out.println("\n*************** JulianDate ***************\nImprime um calendario do mes atual mostrando o dia atual e o dia juliano de todo o mes.\n\n");
		System.out.println("Como usar:\nj6 -jar julianDate.jar yyyyMM\n\nO unico parametro necessario eh uma data no formato yyyyMM, \nsendo o ano e o mes que se quer visualizar o calendario.\n\n");
		System.out.println("Exemplo:\nj6 -jar julianDate.jar 201410\nDOM\tSEG\tTER\tQUA\tQUI\tSEX\tSAB\n.\t.\t.\t[1]274\t[2]275\t[3]276\t[4]277\n[5]278\t[6]279\t[7]280\t[8]281\t[9]282\t[10]283\t[11]284\n[12]285\t[13]286\t[14]287\t[15]288\t[16]289\t[17]290\t[18]291\n[19]292\t[20]293\t[21]294\t[22]295\t[23]296\t[24]297\t[25]298\n[26]299\t[27]300\t[28]301\t[29]302\t[30]303\t[31]304\n\n");
	}
}
