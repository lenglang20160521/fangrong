package component;

public class FangRong extends AbstractObjectComponent implements ObjectComponent {

    @Override
    public String toString(){
        return "id: "+String.valueOf(id)+"  "+"name: "+name;
    }

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof FangRong) {
            FangRong fangRong = (FangRong) obj;
            boolean is = true;
            if (id != fangRong.getId()){
                is = false;
            }
            return is;
        }
        return false;
    }

}
