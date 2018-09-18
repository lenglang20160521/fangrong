package DTO;

import com.alibaba.fastjson.annotation.JSONField;
import component.Breaker;

import java.util.List;

public class Breakers {

    /**
     * code : 0
     * data : [{"id":0,"name":"string","station":0,"circuit":0,"voltCode":0}]
     * msg : string
     * req : string
     */

    private int code;
    private String msg;
    private String req;
    private  List<Breaker> breakers;


    @JSONField(name = "data")
    public List<Breaker> getBreakers() {
        return breakers;
    }

    @JSONField(name = "data")
    public void setBreakers(List<Breaker> breakers) {
        this.breakers = breakers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }


}
