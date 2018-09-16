package DTO;

import java.util.ArrayList;
import java.util.List;

public class Stations {

    /**
     * code : 0
     * data : [{"id":2140000001,"voltCode":4,"transformerNum":2,"transformerCapacity":2000},{"id":2140000002,"voltCode":4,"transformerNum":1,"transformerCapacity":1600},{"id":2140000003,"voltCode":10,"transformerNum":3,"transformerCapacity":5350}]
     */

    private int code;
    private List<DataBean> data;
    private  String msg;
    private  String req;


    public List<Integer> getStationIds(){

        List<Integer> ids = new ArrayList<>();
        for(DataBean point: data){
            ids.add(point.getId());
        }
        return  ids;
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

    public static class DataBean {
        /**
         * id : 2140000001
         * voltCode : 4
         * transformerNum : 2
         * transformerCapacity : 2000
         */

        private int id;
        private String name;
        private int voltCode;
        private int transformerNum;
        private int transformerCapacity;
        private  String voltCodeText;

        private List<DTO.Circuits.DataBean> circuits;

        public void  addCircuits(DTO.Circuits.DataBean circuit){
            if (this.circuits == null){
                this.circuits = new ArrayList<>();
                this.circuits.add(circuit);
            }else {
                this.circuits.add(circuit);
            }
        }


        @Override
        public String toString(){
            return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"voltCode: "+String.valueOf(voltCode)
                    +"   "+"transformerNum: "+String.valueOf(transformerNum)+"   "+"transformerCapacity: "
                    +String.valueOf(transformerCapacity)+"   "+"voltCodeText: "+voltCodeText;
        }


        @Override
        public  boolean equals(Object obj){
            if (obj instanceof DataBean ) {
                DataBean station = (DataBean) obj;
                boolean is = true;
                if (id != station.getId()){
                    is = false;
                }
                return is;
            }
            return false;
        }

        public List<Circuits.DataBean> getCircuits() {
            return circuits;
        }

        public void setCircuits(List<Circuits.DataBean> circuits) {
            this.circuits = circuits;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVoltCode() {
            return voltCode;
        }

        public void setVoltCode(int voltCode) {
            this.voltCode = voltCode;
        }

        public int getTransformerNum() {
            return transformerNum;
        }

        public void setTransformerNum(int transformerNum) {
            this.transformerNum = transformerNum;
        }

        public int getTransformerCapacity() {
            return transformerCapacity;
        }

        public void setTransformerCapacity(int transformerCapacity) {
            this.transformerCapacity = transformerCapacity;
        }
        public String getVoltCodeText() {
            return voltCodeText;
        }

        public void setVoltCodeText(String voltCodeText) {
            this.voltCodeText = voltCodeText;
        }

    }
}
