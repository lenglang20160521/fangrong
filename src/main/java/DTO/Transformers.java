package DTO;

import java.util.List;

public class Transformers {

    /**
     * code : 0
     * data : [{"id":0,"name":"string","station":0,"circuit":0,"voltCode":0,"capacity":0}]
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
         * circuit : 0
         * voltCode : 0
         * capacity : 0
         */

        private int id;
        private String name;
        private int station;
        private int circuit;
        private int voltCode;
        private int capacity;

        @Override
        public String toString(){
            return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"station: "+String.valueOf(station)
                    +"   "+"circuit: "+String.valueOf(circuit)+"   "+"voltCode: "
                    +String.valueOf(voltCode)+"   "+"capacity: "+String.valueOf(capacity);
        }
//        private  Integer parentId;

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

        public int getCircuit() {
            return circuit;
        }

        public void setCircuit(int circuit) {
            this.circuit = circuit;
        }

        public int getVoltCode() {
            return voltCode;
        }

        public void setVoltCode(int voltCode) {
            this.voltCode = voltCode;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }
}
