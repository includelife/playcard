package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 文件读写操作的常用方法
 * @author huzhp
 *
 */
public class FileUtil {
	
	/**
	 * 如果文件不存在，则创建；如果存在，则打开
	 * @param pro
	 * @param file
	 */
	// Properties加载文件信息
	public static void loadPro(Properties pro, File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			pro.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取系统时间
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getTimer() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
}
