package com.jaybill.billblog.validate;

import java.io.File;

/**
 * 当验证码图片达到10张，清空verifyCode这个文件夹
 * @author jaybill
 *
 */
public class DeleteVerifyCodeUtils {

	/**
	 * 删除上一张验证码
	 * @param beforeImg 上张验证码图片的绝对路径
	 */
	public static void delBeforeVerifyCodeImgs(String beforeImg){
		//指向验证码
		File file = new File(beforeImg);
		//删除
		file.delete();
	}
}
