package component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectComponent {

    int id;
    String name;



    private List<Object> childComponent;

    public String toJson(){
        return JSON.toJSONString(this,SerializerFeature.PrettyFormat);
    }

    public void addChildComponent(Object childComponent){
        if (this.childComponent == null){
            this.childComponent = new ArrayList<>();
            this.childComponent.add(childComponent);
        }else {
            this.childComponent.add(childComponent);
        }
    }

    @Override
    public abstract String toString();
    @Override
    public abstract boolean equals(Object obj);

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

    public List<Object> getChildComponent() {
        return childComponent;
    }

    public void setChildComponent(List<Object> childComponent) {
        this.childComponent = childComponent;
    }
}
