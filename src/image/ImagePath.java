package image;

public class ImagePath {


	private static String imgPath;
	private static boolean imgPathGlobal;
	
	
	public static String getImgPath() {
		return imgPath;
	}
	
	public static boolean isGlobal(){
		return imgPathGlobal;
	}
	
	public static void setImgPath(String imgPath) {
		ImagePath.imgPath = imgPath;
	}
	
	public static void setImgPathGlobal(boolean isGlobal){
		imgPathGlobal = isGlobal;
	}
}
