package component;


public class Circuit extends AbstractObjectComponent implements ObjectComponent{

    private int station;
    private int voltCode;

    @Override
    public String toString(){
        return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"voltCode: "+String.valueOf(voltCode)
                +"   "+"station: "+String.valueOf(station);
    }

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof Circuit) {
            Circuit circuit = (Circuit) obj;
            boolean is = true;
            if (id != circuit.getId()){
                is = false;
            }
            return is;
        }
        return false;
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
