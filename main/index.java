public class Main {
	public static void main (String[] args){
		int rad = 1024;
		BufferedImage img = new BufferedImage(rad, rad, BufferedImage.TYPE_INT_RGB);
		
		int cX = img.getWidth() / 2;
		int cY = img.getHeight() / 2;
		int cR = (img.getWidth() / 2) * (img.getWidth() / 2);
		
		int rX = img.getWidth();
		int rY = img.getHeight() / 2;
		int rR = img.getWidth() * img.getWidth();
		
		int gX = 0;
		int gY = img.getHeight() / 2;
		int gR = img.getWidth() * img.getWidth();
		
		int bX = img.getWidth() / 2;
		int bY = img.getHeight();
		int bR = img.getWidth() * img.getWidth();
		
		for (int i = 0; i < img.getWidth(); i++){
			for (int j = 0; j < img.getHeight(); j++){
				int a = i - cX;
				int b = j - cY;
				
				int d = (a * a) + (b * b);
				if (d < cR){
					int rdx = i - rX;
					int rdy = j - rY;
					int rd = (rdx * rdx) + (rdy * rdy);
					int rv = (int) (255 - ((rd / (float) rR) * 256));
					
					int gdx = i - gX;
					int gdy = j - gY;
					int gd = (gdx * gdx) + (gdy * gdy);
					int gv = (int) (255 - ((gd / (float) gR) * 256));
					
					int bdx = i - bX;
					int bdy = j - bY;
					int bd = (bdx * bdx) + (bdy * bdy);
					int bv = (int) (255 - ((bd / (float) bR) * 256));
					
					Color c = new Color(rv, gv, bv);
					
					float hsv = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
					
					Color h = Color.getHSBColor(hsv[0], hsv[1], 1);
					
					img.setRGB(i, j, RGBtoHex(h));
				} else {
					img.setRGB(i, j, 0xFFFFFF);
				}
			}
		}
		
		try {
			ImageIO.write(img, "png", new File("colorwheel.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static int RGBtoHex(Color color){
		String hex = Integer.toHexString(color.getRGB() & 0xFFFFFF);
		if (hex.length() < 6){
			int pad = 6 - hex.length();
			String zeros = "";
			for (int i = 0; i < pad; i++){
				zeros = zeros.concat("0");
			}
			hex = zeros + hex;
		}
		hex = "#" + hex;
		return Integer.decode(hex);
	}
}
