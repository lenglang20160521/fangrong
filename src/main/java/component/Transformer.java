package component;

public class Transformer extends AbstractObjectComponent implements ObjectComponent {

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

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof Transformer) {
            Transformer transformer = (Transformer) obj;
            boolean is = true;
            if (id != transformer.getId()){
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
