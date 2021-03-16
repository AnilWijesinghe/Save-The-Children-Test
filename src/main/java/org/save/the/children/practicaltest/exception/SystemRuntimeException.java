package org.save.the.children.practicaltest.exception;

public class SystemRuntimeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7965975382122807949L;
	private String status;
    private String code;
    private String message;
    private String messageDescription;

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}

	public SystemRuntimeException() {
        super();
    }

    public SystemRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public SystemRuntimeException(String status, String errcode, String errmsg) {
        super(errmsg);
        this.status = status;
        this.code = errcode;
        this.message = errmsg;
    }
    
    public SystemRuntimeException(String status, String errcode, String errmsg,String msgDescription) {
        super(errmsg);
        this.status = status;
        this.code = errcode;
        this.message = errmsg;
        this.messageDescription = msgDescription;
    }
   

}
