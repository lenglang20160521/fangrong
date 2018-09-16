package DTO;

public class FangRong {
    private int id;
    private String name;

    @Override
    public  String toString(){
        return String.valueOf(id)+"   "+name;
    }

    @Override
    public  boolean equals(Object obj){
        if (obj instanceof FangRong ) {
            FangRong rootFangRong = (FangRong) obj;
            boolean is = true;
            if (id != rootFangRong.getId()){
                is = false;
            }
            return is;
        }
        return false;
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
}
