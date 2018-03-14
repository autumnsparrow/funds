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
public class Cakeys {

	/**
	 * 
	 */
	public Cakeys() {
		// TODO Auto-generated constructor stub
	}

	 private String content;

	 

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

		private String nodecode;

	    private String passphase;

	  
	    private Date modifytime;
	    
	    long id;
	    int version;
	    
		
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
		 * @return the passphase
		 */
		public String getPassphase() {
			return passphase;
		}
		/**
		 * @param passphase the passphase to set
		 */
		public void setPassphase(String passphase) {
			this.passphase = passphase;
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
		 * @return the verison
		 */
		public int getVersion() {
			return version;
		}
		/**
		 * @param verison the verison to set
		 */
		public void setVersion(int verison) {
			this.version = verison;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Cakeys [file=" + content + ", nodecode=" + nodecode
					+ ", passphase=" + passphase + ", modifytime=" + modifytime
					+ ", id=" + id + ", verison=" + version + "]";
		}
	    
}
