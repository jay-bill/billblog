package com.jaybill.billblog.image;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩
 * @author jaybill
 *
 */
@SuppressWarnings("restriction")
public class CompressImg {

	private File file = null; // 文件对象
	private String inputDir; // 输入图路径
	private String outputDir; // 输出图路径
	private String inputFileName; // 输入图文件名
	private String outputFileName; // 输出图文件名
	private int outputWidth = 100; // 默认输出图片宽
	private int outputHeight = 100; // 默认输出图片高
	private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	/**
	 * 如果目录不是以/或者\结尾，结尾处加入/
	 * 
	 * @param dir
	 * @return
	 */
	private String getDir(String dir) {
		if (!(dir.endsWith("/") || dir.endsWith("\\"))) {
			dir = dir + "/";
		}
		return dir;
	}

	public CompressImg() { // 初始化变量
		inputDir = "";
		outputDir = "";
		inputFileName = "";
		outputFileName = "";
		outputWidth = 100;
		outputHeight = 100;
	}

	public void setInputDir(String inputDir) {

		this.inputDir = getDir(inputDir);
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = getDir(outputDir);
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public void setWidthAndHeight(int width, int height) {
		this.outputWidth = width;
		this.outputHeight = height;
	}

	/*
	 * 获得图片大小 传入参数 String path ：图片路径
	 */
	public long getPicSize(String path) {
		file = new File(path);
		return file.length();
	}

	// 图片处理
	public String compressPic() {
		try {
			// 获得源文件
			file = new File(inputDir + inputFileName);
			if (!file.exists()) {
				return "file is not exist";
			}
			String fileName = file.getName();
			//获取图片格式
			String suffixStr = StringUtils.split(fileName, ".")[1].trim();
			if(suffixStr.equals("jpg") || suffixStr.equals("jpeg") || suffixStr.equals("png") || suffixStr.equals("gif")||
					suffixStr.equals("tiff")||suffixStr.equals("pcx")||suffixStr.equals("psd")||suffixStr.equals("exif")||
					suffixStr.equals("fpx")||suffixStr.equals("svg")||suffixStr.equals("cdr")||suffixStr.equals("pcd")||
					suffixStr.equals("dxf")||suffixStr.equals("ufo")||suffixStr.equals("eps")||suffixStr.equals("ai")||
					suffixStr.equals("raw")) {
				Image img = ImageIO.read(file);
				//如果图片宽高都小于200
				if(img.getWidth(null)<200&&img.getHeight(null)<200)
					return "no";
				// 判断图片格式是否正确
				if (img.getWidth(null) == -1) {
					return "no";
				} else {
					int newWidth;
					int newHeight;
					// 判断是否是等比缩放
					if (this.proportion == true) {
						// 为等比缩放计算输出的图片宽度及高度
						double rate1 = ((double) img.getWidth(null))
								/ (double) outputWidth + 0.1;
						double rate2 = ((double) img.getHeight(null))
								/ (double) outputHeight + 0.1;
						// 根据缩放比率大的进行缩放控制
						double rate = rate1 > rate2 ? rate1 : rate2;
						newWidth = (int) (((double) img.getWidth(null)) / rate);
						newHeight = (int) (((double) img.getHeight(null)) / rate);
					} else {
						newWidth = outputWidth; // 输出的图片宽度
						newHeight = outputHeight; // 输出的图片高度
					}
					BufferedImage tag = new BufferedImage((int) newWidth,
							(int) newHeight, BufferedImage.TYPE_INT_RGB);

					/*
					 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好
					 * 但速度慢
					 */
					tag.getGraphics().drawImage(
							img.getScaledInstance(newWidth, newHeight,
									Image.SCALE_SMOOTH), 0, 0, null);
					FileOutputStream out = new FileOutputStream(outputDir
							+ outputFileName);
					// JPEGImageEncoder可适用于其他图片类型的转换
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					encoder.encode(tag);
					out.close();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "ok";
	}

	public String compressPic(String inputDir, String outputDir,
			String inputFileName, String outputFileName, int width, int height,
			boolean gp) {
		this.setInputDir(inputDir);
		this.setOutputDir(outputDir);
		// 输入图文件名
		this.inputFileName = inputFileName;
		// 输出图文件名
		this.outputFileName = outputFileName;
		// 设置图片长宽
		setWidthAndHeight(width, height);
		// 是否是等比缩放 标记
		this.proportion = gp;
		return compressPic();
	}

	/**
	 * 复制文件
	 * nio
	 * @throws IOException 
	 */
	public void copyImages(File [] fileList) throws IOException{		
		for(int i=0;i<fileList.length;i++){
			String name1 = fileList[i].getName();
			String [] names = StringUtils.split(name1,".");
			StringBuilder sb = new StringBuilder(names[0]);
			sb.append("big");
			sb.append("."+names[1]);
			FileChannel inChannel = new FileInputStream(fileList[i]).getChannel();
	        FileChannel outChannel = new FileOutputStream(outputDir+"/"+sb.toString()).getChannel();

	        try {
	            //从inChannel文件中读出count bytes ，并写入outChannel文件。
	            inChannel.transferTo(0, inChannel.size(), outChannel);
	        } finally {

	        if (inChannel != null)
	            inChannel.close();
	        if (outChannel != null)
	            outChannel.close();
	        }
		}
	}
	
	/**
	 * 
	 * @param arg
	 *            arg[0] 源图片所在目录 arg[1] 压缩目录 arg[2] 输出图片宽度 arg[3] 输出图片高度
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] arg) throws IOException {
		CompressImg mypic = new CompressImg();
		String inputDir = "E:/eclipse_workplace/billblog-manager/billblog-manager-controller/src/main/webapp/userImages";
		String outputDir = inputDir;
		Integer width = Integer.parseInt("200");
		Integer height = Integer.parseInt("200");
		File file = new File(inputDir);
		File[] fileList = file.listFiles();
		for (int i = 122; i < fileList.length; i=i+2) {
			System.out.println(i+"......"+fileList[i].getName());
			mypic.compressPic(inputDir, outputDir, fileList[i].getName(),
								fileList[i].getName(), width, height, false);			
		}	 	
	}
}