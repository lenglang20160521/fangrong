package DTO;

import com.alibaba.fastjson.annotation.JSONField;
import component.Station;

import java.util.ArrayList;
import java.util.List;

public class Stations {

    /**
     * code : 0
     * data : [{"id":2140000001,"voltCode":4,"transformerNum":2,"transformerCapacity":2000},{"id":2140000002,"voltCode":4,"transformerNum":1,"transformerCapacity":1600},{"id":2140000003,"voltCode":10,"transformerNum":3,"transformerCapacity":5350}]
     */

    private int code;
    private List<Station> stations;
    private  String msg;
    private  String req;


    @JSONField(name = "data")
    public List<Station> getStations() {
        return stations;
    }

    @JSONField(name = "data")
    public void setStations(List<Station> stations) {
        this.stations = stations;
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
