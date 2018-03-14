/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 19, 2013
 *
 */
package com.palmcommerce.funds.ca.client;

import java.util.Date;

/**
 * @author sparrow
 *
 */
public class Cacrts {
	
	String content;
	String nodecode;
	Date modifytime;
	
	long id;
	int version;
	
	boolean iscsr;



	/**
	 * @return the iscsr
	 */
	public boolean isIscsr() {
		return iscsr;
	}

	/**
	 * @param iscsr the iscsr to set
	 */
	public void setIscsr(boolean iscsr) {
		this.iscsr = iscsr;
	}

	/**
	 * 
	 */
	public Cacrts() {
		// TODO Auto-generated constructor stub
	}

	

	

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the nodecode
	 */
	public String getNodecode() {
		return nodecode;
	}

	/**
	 * @param nodecode the nodecode to set
	 */
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}

	/**
	 * @return the modifytime
	 */
	public Date getModifytime() {
		return modifytime;
	}

	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cacrts [file=" + content + ", nodecode=" + nodecode
				+ ", modifytime=" + modifytime + ", id=" + id + ", version="
				+ version + "]";
	}
	
	

}
