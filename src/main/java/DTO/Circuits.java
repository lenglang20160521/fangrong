package DTO;

import com.alibaba.fastjson.annotation.JSONField;
import component.Circuit;

import java.util.ArrayList;
import java.util.List;

public class Circuits {

    /**
     * code : 0
     * data : [{"id":0,"name":"string","station":0,"voltCode":0}]
     * msg : string
     * req : string
     */

    private int code;
    private String msg;
    private String req;
    private List<Circuit> circuits;

    @JSONField(name = "data")
    public List<Circuit> getCircuits() {
        return circuits;
    }

    @JSONField(name = "data")
    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
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
