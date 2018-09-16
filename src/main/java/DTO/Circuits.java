package DTO;

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
    private List<DataBean> data;


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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * name : string
         * station : 0
         * voltCode : 0
         */

        private int id;
        private String name;
        private int station;
        private int voltCode;
        private List<DataBean> childCircuits;

        private List<DTO.Breakers.DataBean> breakers;
        private List<DTO.Transformers.DataBean> transformers;


        public void  addBreakers(DTO.Breakers.DataBean breaker){
            if (this.breakers == null){
                this.breakers = new ArrayList<>();
                this.breakers.add(breaker);
            }else {
                this.breakers.add(breaker);
            }
        }

        public void  addTransformers(DTO.Transformers.DataBean transformer){
            if (this.transformers == null){
                this.transformers = new ArrayList<>();
                this.transformers.add(transformer);
            }else {
                this.transformers.add(transformer);
            }
        }

        public void  addCircuits(DTO.Circuits.DataBean circuit){
            if (this.childCircuits == null){
                this.childCircuits = new ArrayList<>();
                this.childCircuits.add(circuit);
            }else {
                this.childCircuits.add(circuit);
            }
        }

        public List<DataBean> getChildCircuits() {
            return childCircuits;
        }

        public void setChildCircuits(List<DataBean> childCircuits) {
            this.childCircuits = childCircuits;
        }

        @Override
        public String toString(){
            return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"voltCode: "+String.valueOf(voltCode)
                    +"   "+"station: "+String.valueOf(station);
        }

        @Override
        public  boolean equals(Object obj){
            if (obj instanceof DataBean ) {
                DataBean circuit = (DataBean) obj;
                boolean is = true;
                if (id != circuit.getId()){
                    is = false;
                }
                return is;
            }
            return false;
        }

        public List<Breakers.DataBean> getBreakers() {
            return breakers;
        }

        public void setBreakers(List<Breakers.DataBean> breakers) {
            this.breakers = breakers;
        }

        public List<Transformers.DataBean> getTransformers() {
            return transformers;
        }

        public void setTransformers(List<Transformers.DataBean> transformers) {
            this.transformers = transformers;
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

        public int getStation() {
            return station;
        }

        public void setStation(int station) {
            this.station = station;
        }

        public int getVoltCode() {
            return voltCode;
        }

        public void setVoltCode(int voltCode) {
            this.voltCode = voltCode;
        }
    }
}
