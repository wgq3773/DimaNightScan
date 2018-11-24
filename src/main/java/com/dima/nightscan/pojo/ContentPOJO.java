package com.dima.nightscan.pojo;

public class ContentPOJO {

	// 图片地址
	private String imgString;
	// 文本
	private String txtString;
	// 视频地址
	private String videoString;
	
	public String getImgString() {
		return imgString;
	}
	public void setImgString(String imgString) {
		this.imgString = imgString;
	}
	public String getTxtString() {
		return txtString;
	}
	public void setTxtString(String txtString) {
		this.txtString = txtString;
	}
	public String getVideoString() {
		return videoString;
	}
	public void setVideoString(String videoString) {
		this.videoString = videoString;
	}
	@Override
	public String toString() {
		return "ContentPOJO [imgString=" + imgString + ", txtString=" + txtString + ", videoString=" + videoString
				+ "]";
	}
}
