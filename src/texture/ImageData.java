package texture;

public class ImageData {
	
	private byte[] data;
	private int width;
	private int height;
	
	ImageData(byte[] data, int w, int h){
		this.data = data;
		width = w;
		height = h;
	}

    public byte[] getPixel(int x, int y){
        
        int index = y * width + x * 4;
        return new byte[]{ byte[index], byte[index+1], byte[index+2], byte[index+3] };
    }
    
    byte[] getImageBytes(){
        return data;
    }
    
    public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
}
