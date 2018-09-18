package component;

public class Breaker extends AbstractObjectComponent implements ObjectComponent {

    private int station;
    private int circuit;
    private int voltCode;

    @Override
    public  String toString(){
        return "id: "+String.valueOf(id)+"  "+"name: "+name+"   "+"voltCode: "+String.valueOf(voltCode)
                +"   "+"station: "+String.valueOf(station)+"   "+"circuit: "+String.valueOf(circuit);
    }

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof Breaker) {
            Breaker breaker = (Breaker) obj;
            boolean is = true;
            if (id != breaker.getId()){
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

}
