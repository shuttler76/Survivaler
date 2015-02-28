package graphics;

import texture.ImageData;
import texture.Texture;
import texture.TextureLoader;

public class Font {
	public Texture glyphs;
	public int[][] characterXBounds;
	public String alphabet;
	
	public Font(String alphabet, String source) throws Exception{
		this.alphabet = alphabet;
		ImageData data = TextureLoader.loadImageDataFromFile(source);
		int characterIndex = 0;
		characterXBounds = new int[alphabet.length()][2];
		byte[] seperationColor = data.getPixel(0, 0);
		boolean wasSepColorLastIter = true;
		
		for(int x = 0 ; x < data.getWidth() ; x++){
			byte[] pixel = data.getPixel(x, 0);
			for (int i = 0; i < 5; i++){
				if (i == 4) {
					if (!wasSepColorLastIter){
						characterXBounds[characterIndex][1] = x;
						characterIndex++;
					}
					wasSepColorLastIter = true;
				}
				else if(!(pixel[i]==seperationColor[i])){
					if (wasSepColorLastIter){
						characterXBounds[characterIndex][0] = x;
					}
					wasSepColorLastIter = false;
					break;
				}
			}
		}
		
		glyphs = TextureLoader.loadTextureFromImageData(data, false);
	}
	
	
}
