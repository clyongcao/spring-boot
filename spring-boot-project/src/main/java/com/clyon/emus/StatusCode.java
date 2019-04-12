package com.clyon.emus;

/**
 * @author caoxuyang
 */
public enum StatusCode {
	
	 /******* 通用异常*****/
	CODE_200(200, "操作成功"),
	CODE_300(300, "操作失败"), 
	CODE_301(301, "非登录用户操作,imei号不能为空"),
	CODE_302(302, "亲不要频繁操作"),
	CODE_500(500, "服务器忙，请稍后再试！"),
	CODE_702(702, "保存失败"),
	CODE_703(703, "删除失败"),
	
    /******* 10xxxx Redis异常*****/
    CODE_100001(100001, "请传入键或值"),
   
	
	/******* 20xxxx Elasticsearch异常*****/
	CODE_200001(200001, "");

	//成员变量
	private int code;
	private String remark;
	
	//构造器
	StatusCode(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public int value() {
        return this.code;
    }

    public String remark() {
        return this.remark;
    }

    @Override
    public String toString() {
        return this.code + "";
    }
	
}
