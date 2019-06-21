package br.com.matematica.equacoes;

public class MultiplicaDuasEquacao {

	static Integer[] equacao1 = new Integer[50];
	static Integer[] equacao2 = new Integer[50];
	static Integer[] equacao_std = new Integer[50];

	public static final String DELIMITER = "x";
	public static String equacao_str1 = "-2x+4";
	public static String equacao_str2 = "13x2-2-7";


	public static void main(String[] args) {
		//inicializa os 3 arrays
		equacao1 = inicializarArray(equacao1);
		equacao2 = inicializarArray(equacao2);
		equacao_std = inicializarArray(equacao_std);

		antesDeTudo(equacao_str1); //alimenta os arrays e verifica o sinal
		antesDeTudo(equacao_str2);
		filtrarAlimentarArray(equacao_str1); //inicia o processo
		filtrarAlimentarArray(equacao_str2); //inicia o processo
		System.out.println("fim");
	}

	private static void antesDeTudo(String equacao_str) {

		String temp = equacao_str;

		if(!equacao_str.startsWith("-")){
			StringBuilder strb = new StringBuilder();
			strb.append("+");
			strb.append(equacao_str);
			equacao_str = strb.toString();
		}

		if(temp.equals(equacao_str1)) {
			equacao_str1 = equacao_str;
			equacao_std = equacao1;
		}

		if(temp.equals(equacao_str2)) {
			equacao_str2 = equacao_str;
			equacao_std = equacao2;
		}
	}

	private static Integer[] inicializarArray(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		return array;
	}

	private static void filtrarAlimentarArray(String value) {

		String temp = value;
		String replacePlus = value.replace("+", "|+");
		String replace = replacePlus.replace("-", "|-");
		String[] splitPlus = replace.split("\\|"); //se nao usar '\\' dá erro.

		for (String str : splitPlus) {
			if(!str.isEmpty()){
				alimentarArray(str, temp);
			}
		}
	}

	private static void  alimentarArray(String value, String equacao_value) {

		int valor = 0;
		if(value.contains(DELIMITER)){
			String[] split = value.trim().split(DELIMITER);
			if(split.length == 1) { //pega o valor com x elevado a 1
				int posicao = 1;
				valor = Integer.parseInt(split[0]);
				alimentarDeFormaIncremental(posicao, valor, equacao_value);
			}else { 
				int posicao = Integer.valueOf(split[1]);
				int cc = Integer.valueOf(split[0]);
				valor = equacao_std[posicao];
				valor = cc + valor;
				alimentarDeFormaIncremental(posicao, valor, equacao_value);
			}

		}else { //pega o valor no numero puro sem x
			int posicao = 0;
			valor = Integer.valueOf(value);
			alimentarDeFormaIncremental(posicao, valor, equacao_value);
		}
	}

	private static void alimentarDeFormaIncremental(int posicao, int valor, String equacao) {

		if(equacao.equals(equacao_str1)){
			equacao1[posicao] += valor;
		}
		if(equacao.equals(equacao_str2)){
			equacao2[posicao] += valor;
		}
	}

}
