package DTO;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import config.YuanJingConfig;

import java.util.*;

public class resultPoints {

    /**
     * code : 0
     * data : [{"id":100000001,"stamp":1531801800,"value":9.116365036231173,"status":0},{"id":100000002,"stamp":1531801800,"value":9.721664567077672,"status":0}]
     */

    private int code;
    private List<DataBean> data;
    private List<String> jsonMsgs;
    private  Map<Integer,String> anaPointIdShortNameMaps;

    public void createJsonMsgs(){
        jsonMsgs = new ArrayList<>();
        Table<Long, String, Double> dataTable = HashBasedTable.create();
        for (DataBean itemData : data){
            Integer timeStamp = itemData.getStamp();
            Long newTimeStamp = timeStamp.longValue()*1000; //毫秒时间戳
            Integer itemPointId = itemData.getId();
            String  itemShortName = anaPointIdShortNameMaps.get(itemPointId);
            Double value = itemData.getValue();
            dataTable.put(newTimeStamp,itemShortName,value);
        }

        Set<Long> allTimeStamps = dataTable.rowKeySet();
        for(Long onlyTimeStamps: allTimeStamps ){
            msg msg = new msg();
            String object = YuanJingConfig.object;
            msg.setObject(object);
            msg.setTimestamp(onlyTimeStamps);
            Map<String,Double> itemValueMap =  dataTable.row(onlyTimeStamps);
            msg.setItemValues(itemValueMap);
            jsonMsgs.add(msg.getJsonStrMsg());
        }

    }

    public  List<String> getJsonMsgs(){
        return this.jsonMsgs;
    }

    public Map<Integer, String> getAnaPointIdShortNameMaps() {
        return anaPointIdShortNameMaps;
    }

    public void setAnaPointIdShortNameMaps(Map<Integer, String> anaPointIdShortNameMaps) {
        this.anaPointIdShortNameMaps = anaPointIdShortNameMaps;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public  class  msg{
        private String object;
        private  Long timestamp;
        private Map<String,Double> itemValues;

        public  String getJsonStrMsg(){
            Map<String, Object> msgMap = new HashMap<>();
            msgMap.put("object",object);
            msgMap.put("timestamp",timestamp);
            msgMap.putAll(itemValues);
            String jsonStr = JSON.toJSONString(msgMap);
            return jsonStr;
        }

        public Map<String, Double> getItemValues() {
            return itemValues;
        }

        public void setItemValues(Map<String, Double> itemValues) {
            this.itemValues = itemValues;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class DataBean {
        /**
         * id : 100000001
         * stamp : 1531801800
         * value : 9.116365036231173
         * status : 0
         */

        private int id;
        private int stamp;
        private double value;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStamp() {
            return stamp;
        }

        public void setStamp(int stamp) {
            this.stamp = stamp;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
