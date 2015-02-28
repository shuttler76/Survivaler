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
        
        int index = y * data.length + x * 4;
        return new byte[]{ data[index], data[index+1], data[index+2], data[index+3] };
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
