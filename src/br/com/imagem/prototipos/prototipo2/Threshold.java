package br.com.imagem.prototipos.prototipo2;

import java.io.File;
import java.util.List;

import Jama.Matrix;

public class Threshold {

	public static void criarListaThreshold(List<File> fotosList) {
		//precisamos do eigenfaces e da matriz
		//matrizAssinatura
				Matrix assinaturas                 = new Matrix(Prototipo2.matrizAssinatura); //10000x10
				Matrix matrix_eigenface_transposta = new Matrix(Prototipo2.eigenfaceTransposta);//10x10000
				Matrix matriz_peso = matrix_eigenface_transposta.times(assinaturas);//10x10
				int NUMERO_FOTOS = fotosList.size();
				
				for (int i = 1; i < NUMERO_FOTOS; i++) {
					double dist = 0;
					Matrix matrix1 = matriz_peso.getMatrix(0, NUMERO_FOTOS-1, i, i);
					Matrix matrix2 = matriz_peso.getMatrix(0, NUMERO_FOTOS-1, i-1, i-1);
					Matrix minus = matrix2.minus(matrix1);
					
					for (int j = 0; j < NUMERO_FOTOS; j++) {
						dist += Math.pow(minus.get(j, 0), 2);
					}
					double sqrt = Math.sqrt(dist);
					Prototipo2.listaDistanciasTreinamento.add(sqrt);
				}
				System.out.println("FIM DA FASE DE ENCONTRAR THRESHOLD==>"+Prototipo2.listaDistanciasTreinamento);
	}
	
	public static Double encontrarThresholdBaseadoNaLista() {
		Prototipo2.listaDistanciasTreinamento.forEach(c -> {
			if(c < Prototipo2.threshold) {
				Prototipo2.threshold = c;
				System.out.println("mudou");
			}
		});
		return Prototipo2.threshold * 0.8;
	}
	


}
