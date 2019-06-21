package br.com.matematica.equacoes;

import java.text.DecimalFormat;


/**
 * 
 * @author hal9000
 * Esta classe faz algo muito importante, que é pegar uma equacao 
 * qualquer gerada de uma matriz de uma imagem para criar 
 * a eigenvetctors 
 *
 */
public class SolucionadorEquacao {

    static Integer[] valor = new Integer[50];
    public static final String DELIMITER = "x";
    public static String potencia = "-2x3+4x2+13x2+13x7+5x2-2x+7x1-32+43";
    //public static String potencia = "1x2-5x+6";
    //public static String potencia = "1x2-9";
    //public static String potencia = "1x3+2x2-1x-2";
    //public static String potencia = "1x4+2x3+3x2-1";
   // public static String potencia = "1x5+4x4+3x3-2x2+1x+12";
    public static int maiorPotencia = 0;
    
    
    
    
    public static void main(String[] args) {	
        antesDeTudo(); //alimenta os arrays e verifica o sinal
        filtrarAlimentarArray(potencia); //inicia o processo
        descobrirRaizesEquacao();
    }
    
    private static void descobrirRaizesEquacao() {
		//Integer[] raiz = new Integer[10];
    	double menorValor = 10;
    	double indice = 0;
    	//parte positiva
    	double j = 10.0000;
    	while(j >= -10.000){
    		double somatoria = 0;
	    	for (int i = 0; i <= maiorPotencia; i++) {
	    		int a = valor[i];
	    		int b = i;
	    		double pow = 0;
	    		if(i == 0){
	    			somatoria += a;
	    		}else {
	    			pow = Math.pow(j, b);
	    			somatoria += pow * a;
	    		}
			}
//	    	if(somatoria == 0 || (somatoria > -1 && somatoria < 1)){
//	    		System.out.println("\n\nEncontrado raiz de "+potencia+":\n Valor:"+j+"\n Somatoria: "+somatoria);	    		
//	    	}
	    	if(Math.abs(somatoria) < menorValor){
	    		menorValor = Math.abs(somatoria);
	    		indice = j;
	    	}
	    	//System.out.println(somatoria);
	    	j -= 0.001;
    	}
    	//parte negativa
    	double k = -10.000;
    	while(k <= 10.000){
    		double somatoria = 0;
	    	for (int i = 0; i <= maiorPotencia; i++) {
	    		int a = valor[i];
	    		int b = i;
	    		double pow = 0;
	    		if(i == 0){
	    			somatoria += a;
	    		}else {
	    			pow = Math.pow(k, b);
	    			somatoria += pow * a;
	    		}
			}
//	    	if(somatoria == 0 || (somatoria > -1 && somatoria < 1)){
//	    		System.out.println("\n\nEncontrado raiz de "+potencia+":\n Valor:"+j+"\n Somatoria: "+somatoria);	    		
//	    	}
	    	if(Math.abs(somatoria) < menorValor){
	    		menorValor = Math.abs(somatoria);
	    		indice = k;
	    	}
	    	//System.out.println(somatoria);
	    	k += 0.001;
    	}
    	DecimalFormat df = new DecimalFormat("0.00000");
    	
    	System.out.println("\n\nEncontrado raiz de "+potencia+":\n Valor:"+indice+"\n Somatoria: "+df.format(menorValor));
    	//System.out.println("\n\nEncontrado raiz de "+potencia+":\n Valor:"+indice+"\n Somatoria: "+menorValor);
    
    }

	private static void antesDeTudo() {
        
        for (int i = 0; i <= 49; i++) {
            valor[i] = 0;
        }
        if(!potencia.startsWith("-")){
        	StringBuilder strb = new StringBuilder();
        	strb.append("+");
        	strb.append(potencia);
        	potencia = strb.toString();
        }
        
        potencia = potencia.replace(DELIMITER+"+", DELIMITER+"1+");
        potencia = potencia.replace(DELIMITER+"-", DELIMITER+"1-");
    }
    
       
    private static void filtrarAlimentarArray(String value) {
       
    	String replacePlus = value.replace("+", "|+");
        String replace = replacePlus.replace("-", "|-");
        String[] splitPlus = replace.split("\\|"); //se nao usar '\\' dá erro.

        for (String str : splitPlus) {
			if(!str.isEmpty()){
				alimentarArray(str);
			}
		}
    }

    
    private static void  alimentarArray(String value) {
        
        if(value.contains(DELIMITER)){
            String[] split = value.trim().split(DELIMITER);
            if(split.length == 1) { //pega o valor com x elevado a 1
                valor[1] += Integer.valueOf(split[0]);
                adicionarMaiorPotencia(valor[1]);
            }else { 
                int potencia = Integer.valueOf(split[1]);
                int cc = Integer.valueOf(split[0]);
                int valorAtual = valor[potencia];
                valor[potencia] = cc + valorAtual;
                adicionarMaiorPotencia(potencia);
            }
            
        }else { //pega o valor no numero puro sem x
            valor[0] += Integer.valueOf(value);
        }
    }
    
    private static void adicionarMaiorPotencia(int num) {
    	if(num > maiorPotencia)
    		maiorPotencia = num;
    }
    
    
}