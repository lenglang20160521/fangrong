package component;

import java.util.ArrayList;
import java.util.List;

public class Station extends AbstractObjectComponent implements ObjectComponent {

    private int voltCode;
    private int transformerNum;
    private int transformerCapacity;
    private  String voltCodeText;

    @Override
    public String toString(){
        return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"voltCode: "+String.valueOf(voltCode)
                +"   "+"transformerNum: "+String.valueOf(transformerNum)+"   "+"transformerCapacity: "
                +String.valueOf(transformerCapacity)+"   "+"voltCodeText: "+voltCodeText;
    }

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof Station) {
            Station station = (Station) obj;
            boolean is = true;
            if (id != station.getId()){
                is = false;
            }
            return is;
        }
        return false;
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
