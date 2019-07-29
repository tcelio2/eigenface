package br.com.imagem.prototipos.prototipo2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FotoUtils {


	public static void remontarFoto(double[][] foto2) {
		
		int width = foto2.length;
		int height = foto2[0].length;
		
		int m = 0;
	//	int width = TAMANHO;
	//	int height = TAMANHO;
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		
		double[][] foto = renormalizePhoto(foto2);
		
		
		for (int k = 0; k < width; k++) {
			for (int k2 = 0; k2 < height; k2++) {
				int a2 = ((int)foto[k][k2]>>24) & 0xff; //alpha
				int r2 = ((int)foto[k][k2]>>16) & 0xff; //red
				int g2 = ((int)foto[k][k2]>>8) & 0xff; //green
				int b2 =  ((int)foto[k][k2]) & 0x0ff; //blue
				int bbb =  (a2<<24) | (r2<<16) | (g2<<8) | b2; //red
				System.out.println(bbb+"--");
				image.setRGB(k, k2, bbb);
			}
			
		}
		File f2 = null;
		f2 = new File("/home/hal9000/Pictures/ReconhecimentoFacial/editada/treeArray.jpg");
		try {
			ImageIO.write(image, "jpg", f2);
		} catch (IOException e) {
			System.out.println("Erro ao criar:" + e.getCause());		
		}
	}
	
	public static double[][] renormalizePhoto(double[][] foto) {
		int counter = 0;
		double[][] fotoReturn = new double[TAMANHO][TAMANHO];
		for (int k = 0; k < TAMANHO; k++) {
			for (int k2 = 0; k2 < TAMANHO; k2++) {
				
				fotoReturn[k][k2] = foto[counter][0];
				counter++;
			}
			
		}
		return fotoReturn;
	}
	
	
}
