package com.palmcommerce.funds.protocol.impl.t2p;

import com.palmcommerce.funds.protocol.ProtocolClassMetaType;
import com.palmcommerce.funds.protocol.ProtocolClassMetaType.TRANSFER_MODE;
import com.palmcommerce.funds.protocol.ProtocolDriverManager;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType;
import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.covertor.exception.ProtocolConvertorException;
import com.palmcommerce.funds.protocol.impl.BasicProtocol;
import com.palmcommerce.funds.protocol.impl.StateMessage;


/**
 * 6.2.8  批量汇付接口
 * 6.2.8.1 业务描述：
从福彩中心发起同步交易或异步交易，可随时向银行发起批量汇付。
福彩中心生成批量汇付的文件，并放到福彩中心的前置机的sftp上，
并发送批量汇付请求，银行收到请求，根据文件名，从约定的sftp上下载文件，
如果银行下载成功，并验证完成后，返回“成功”或“失败”应答信息；
对帐文件的格式与退款的格式完全一样。
 * @author lottery
 *
 */
@ProtocolClassMetaType(mode=TRANSFER_MODE.DELAY_PROXY)
public class T250008 extends BasicProtocol {
	

	public T250008(String transcode, String from, String to) {
		super(transcode, from, to);
		// TODO Auto-generated constructor stub
	}



	public Request request;
	public Response response;
	
	/**
	 * 文件名称	字符		Y	文件名
文件大小	字符		Y	字节数
账务日期	字符	格式为：YYYYMMDD	Y	格式为：YYYYMMDD

	 * @author lottery
	 *
	 */
	public static class Request{
		@ProtocolElementMetaType(sequenceId=0,elementType=ProtocolElementType.STRING)
		String fileName;
		@ProtocolElementMetaType(sequenceId=1,elementType=ProtocolElementType.STRING)
		String fileSize;
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.DATETIME,dateTimeFormat="yyyyMMdd")
		String accountTime;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileSize() {
			return fileSize;
		}
		public void setFileSize(String fileSize) {
			this.fileSize = fileSize;
		}
		public String getAccountTime() {
			return accountTime;
		}
		public void setAccountTime(String accountTime) {
			this.accountTime = accountTime;
		}
		@Override
		public String toString() {
			return "Request [fileName=" + fileName + ", fileSize=" + fileSize
					+ ", accountTime=" + accountTime + ", toString()="
					+ super.toString() + "]";
		}
		
	}
	
	/**
	 * 状态码	字符	4位	Y	0000 交易成功，见附录B
状态码描述	字符	最长30位	Y	对状态码的文字说明信息
校验文件大小	字符		Y	资金归集管理平台处理后原样返回

	 * @author lottery
	 *
	 */
	public static class Response extends StateMessage{
		@ProtocolElementMetaType(sequenceId=2,elementType=ProtocolElementType.STRING)
		String checkFileSize;

		public String getCheckFileSize() {
			return checkFileSize;
		}

		public void setCheckFileSize(String checkFileSize) {
			this.checkFileSize = checkFileSize;
		}

		@Override
		public String toString() {
			return "Response [checkFileSize=" + checkFileSize + ", toString()="
					+ super.toString() + "]";
		}
		
	}
	
	public T250008() {
	}

	public T250008(String transcode, String from, String to, String packet,
			boolean isRequest) {
		super(transcode, from, to, packet, isRequest);
	}


	public T250008(String transcode, String from, String to, String request,
			String response) {
		super(transcode, from, to, request, response);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "T250008 [request=" + request + ", response=" + response + "]";
	}
	
	
	@Override
	public void setRequestObject(Object obj) {
		// TODO Auto-generated method stub
		this.request=(Request) obj;
		
	}





	@Override
	public void setResponseObject(Object obj) {
		// TODO Auto-generated method stub
		this.response=(Response)obj;
	}





	@Override
	public Object getRequestObject() {
		// TODO Auto-generated method stub
		return this.request;
	}





	@Override
	public Object getResponseObject() {
		// TODO Auto-generated method stub
		return this.response;
	}



	@Override
	public Class<?> getRequestClazz() {
		// TODO Auto-generated method stub
		return Request.class;
	}



	@Override
	public Class<?> getResponseClazz() {
		// TODO Auto-generated method stub
		return Response.class;
	}


}
