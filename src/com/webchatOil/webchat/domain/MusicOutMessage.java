package com.derrick.domain;

 /**
  * 
 * @ClassName: MusicOutMessage 
 * @Description: TODO(输出音乐消息) 
 * @author derrick_hoh@163.com  
 * @date 2015年1月21日 下午2:44:16 
 *
  */
public class MusicOutMessage extends OutMessage {
	private String	MsgType	= "music";
	private String	MusicUrl;
	private String	HQMusicUrl;

	public String getMsgType() {
		return MsgType;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
}
