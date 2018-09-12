package DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Points {

    /**
     * code : 0
     * data : [{"id":0,"name":"string","objType":0,"objId":0,"measure":0,"interval":0,"station":0}]
     * msg : string
     * req : string
     */

    private int code;
    private String msg;
    private String req;
    private List<DataBean> data;
    private  Map<Integer,String> idNameMaps;


    public List<Integer> getPointIds(){
        List<Integer> ids = new ArrayList<>();
        for (DataBean point : data){
            ids.add(point.getId());
        }
        return ids;
    }

    public List<String> getPointNames(){
        List<String> Names = new ArrayList<>();
        for (DataBean point : data){
            Names.add(point.getMeasureName());
        }
        return Names;
    }

    public   Map<Integer,String> getIdNameMaps(){
        Map<Integer,String> idNameMaps = new HashMap<>();
        for (DataBean point : data){
            idNameMaps.put(point.id,point.getMeasureName());
        }
        return idNameMaps;
    }

    public   Map<Integer,String> getIdShortNameMaps(){
        Map<Integer,String> idShortNameMaps = new HashMap<>();
        for (DataBean point : data){
            idShortNameMaps.put(point.id,point.getMeasureShortName());
        }
        return idShortNameMaps;
    }

    public   Map<Integer,String> getIdAliasNameMaps(){
        Map<Integer,String> idAliasNameMaps = new HashMap<>();
        for (DataBean point : data){
            idAliasNameMaps.put(point.id,point.getMeasureAliasName());
        }
        return idAliasNameMaps;
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
         * objType : 0
         * objId : 0
         * measure : 0
         * interval : 0
         * station : 0
         */

        private int id;
        private String name;
        private int objType;
        private int objId;
        private int measure;
        private int interval;
        private int station;

        private static  Map<Integer,String> measureNameMap = new HashMap<>();
        private static  Map<Integer,String> measureShortNameMap = new HashMap<>();
        private static  Map<Integer,String> measureAliasNameMap = new HashMap<>();
        static {
            measureNameMap.put(33,"线电流Ib");
            measureNameMap.put(32,"线电流Ia");
            measureNameMap.put(34,"线电流Ic");
            measureNameMap.put(41,"AB线电压");
            measureNameMap.put(40,"C相电压");
            measureNameMap.put(39,"B相电压");
            measureNameMap.put(38,"A相电压");
            measureNameMap.put(43,"CA线电压");
            measureNameMap.put(42,"BC线电压");
            measureNameMap.put(56,"A相有功功率");
            measureNameMap.put(61,"C相无功功率");
            measureNameMap.put(55,"总视在功率");
            measureNameMap.put(60,"B相无功功率");
            measureNameMap.put(53,"总无功功率");
            measureNameMap.put(59,"A相无功功率");
            measureNameMap.put(64,"C相视在功率");
            measureNameMap.put(52,"总有功功率");
            measureNameMap.put(58,"C相有功功率");
            measureNameMap.put(63,"B相视在功率");
            measureNameMap.put(57,"B相有功功率");
            measureNameMap.put(62,"A相视在功率");
            measureNameMap.put(82,"无功功率需量");
            measureNameMap.put(81,"有功功率需量");
            measureNameMap.put(83,"视在功率需量");
            measureNameMap.put(88,"C相功率因数");
            measureNameMap.put(87,"B相功率因数");
            measureNameMap.put(86,"A相功率因数");
            measureNameMap.put(85,"总功率因数");
            measureNameMap.put(90,"A相温度");
            measureNameMap.put(92,"C相温度");
            measureNameMap.put(91,"B相温度");
            measureNameMap.put(207,"正向有功电度");
            measureNameMap.put(206,"总无功电度");
            measureNameMap.put(205,"总有功电度");
            measureNameMap.put(210,"反向无功电度");
            measureNameMap.put(209,"反向有功电度");
            measureNameMap.put(208,"正向无功电度");

            measureShortNameMap.put(33,"Meter.Ib");
            measureShortNameMap.put(32,"Meter.Ia");
            measureShortNameMap.put(34,"Meter.Ic");
            measureShortNameMap.put(41,"Meter.Uab");
            measureShortNameMap.put(40,"Meter.Uc");
            measureShortNameMap.put(39,"Meter.Ub");
            measureShortNameMap.put(38,"Meter.Ua");
            measureShortNameMap.put(43,"Meter.Uca");
            measureShortNameMap.put(42,"Meter.Ubc");
            measureShortNameMap.put(56,"Meter.Pa");
            measureShortNameMap.put(61,"Meter.Qc");
            measureShortNameMap.put(55,"Meter.Stotal");
            measureShortNameMap.put(60,"Meter.Qb");
            measureShortNameMap.put(53,"Meter.Qtotal");
            measureShortNameMap.put(59,"Meter.Qa");
            measureShortNameMap.put(64,"Meter.Sc");
            measureShortNameMap.put(52,"Meter.Ptotal");
            measureShortNameMap.put(58,"Meter.Pc");
            measureShortNameMap.put(63,"Meter.Sb");
            measureShortNameMap.put(57,"Meter.Pb");
            measureShortNameMap.put(62,"Meter.Sa");
            measureShortNameMap.put(82,"Meter.QD");
            measureShortNameMap.put(81,"Meter.PD");
            measureShortNameMap.put(83,"Meter.SD");
            measureShortNameMap.put(88,"Meter.PFc");
            measureShortNameMap.put(87,"Meter.PFb");
            measureShortNameMap.put(86,"Meter.PFa");
            measureShortNameMap.put(85,"Meter.PFtotal");
            measureShortNameMap.put(90,"DC.PhaseATemp");
            measureShortNameMap.put(92,"DC.PhaseCTemp");
            measureShortNameMap.put(91,"DC.PhaseBTemp");
            measureShortNameMap.put(207,"Meter.EpPositive");
            measureShortNameMap.put(206,"Meter.Eqtotal");
            measureShortNameMap.put(205,"Meter.Eptotal");
            measureShortNameMap.put(210,"Meter.EqNegative");
            measureShortNameMap.put(209,"Meter.EpNegative");
            measureShortNameMap.put(208,"Meter.EqPositive");

            measureAliasNameMap.put(33,"MeterIb");
            measureAliasNameMap.put(32,"MeterIa");
            measureAliasNameMap.put(34,"MeterIc");
            measureAliasNameMap.put(41,"MeterUab");
            measureAliasNameMap.put(40,"MeterUc");
            measureAliasNameMap.put(39,"MeterUb");
            measureAliasNameMap.put(38,"MeterUa");
            measureAliasNameMap.put(43,"MeterUca");
            measureAliasNameMap.put(42,"MeterUbc");
            measureAliasNameMap.put(56,"MeterPa");
            measureAliasNameMap.put(61,"MeterQc");
            measureAliasNameMap.put(55,"MeterStotal");
            measureAliasNameMap.put(60,"MeterQb");
            measureAliasNameMap.put(53,"MeterQtotal");
            measureAliasNameMap.put(59,"MeterQa");
            measureAliasNameMap.put(64,"MeterSc");
            measureAliasNameMap.put(52,"MeterPtotal");
            measureAliasNameMap.put(58,"MeterPc");
            measureAliasNameMap.put(63,"MeterSb");
            measureAliasNameMap.put(57,"MeterPb");
            measureAliasNameMap.put(62,"MeterSa");
            measureAliasNameMap.put(82,"MeterQD");
            measureAliasNameMap.put(81,"MeterPD");
            measureAliasNameMap.put(83,"MeterSD");
            measureAliasNameMap.put(88,"MeterPFc");
            measureAliasNameMap.put(87,"MeterPFb");
            measureAliasNameMap.put(86,"MeterPFa");
            measureAliasNameMap.put(85,"MeterPFtotal");
            measureAliasNameMap.put(90,"DCPhaseATemp");
            measureAliasNameMap.put(92,"DCPhaseCTemp");
            measureAliasNameMap.put(91,"DCPhaseBTemp");
            measureAliasNameMap.put(207,"MeterEpPositive");
            measureAliasNameMap.put(206,"MeterEqtotal");
            measureAliasNameMap.put(205,"MeterEptotal");
            measureAliasNameMap.put(210,"MeterEqNegative");
            measureAliasNameMap.put(209,"MeterEpNegative");
            measureAliasNameMap.put(208,"MeterEqPositive");
        }


        public String getMeasureName(){
            String measureName = measureNameMap.get(this.measure);
            if (null == measureName){
                System.out.println("无法识别measure码： "+String.valueOf(this.measure));
            }
            return  measureName;
        }

        public String getMeasureShortName(){
            String measureShortName = measureShortNameMap.get(this.measure);
            if (null == measureShortName){
                System.out.println("无法识别measure码： "+String.valueOf(this.measure));
            }
            return  measureShortName;
        }

        public String getMeasureAliasName(){
            String measureAliasName = measureAliasNameMap.get(this.measure);
            if (null == measureAliasName){
                System.out.println("无法识别measure码： "+String.valueOf(this.measure));
            }
            return  measureAliasName;
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

        public int getObjType() {
            return objType;
        }

        public void setObjType(int objType) {
            this.objType = objType;
        }

        public int getObjId() {
            return objId;
        }

        public void setObjId(int objId) {
            this.objId = objId;
        }

        public int getMeasure() {
            return measure;
        }

        public void setMeasure(int measure) {
            this.measure = measure;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getStation() {
            return station;
        }

        public void setStation(int station) {
            this.station = station;
        }
    }
}
