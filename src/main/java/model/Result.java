package model;

//封装ajax返回结果
public class Result {
	private int code;//状态码
	private String description;//描述信息
	private Object data;//返回的数据
	private String nextAction;//下一步要跳转的链接
	
	public Result(int code, String description,Object data,String nextAction){
		this.code = code;
		this.description = description;
		this.data = data;
		this.nextAction = nextAction;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
