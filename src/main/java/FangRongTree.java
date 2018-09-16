import DTO.Stations;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

public class FangRongTree {
    private int id;
    private String name;
    private List<DTO.Stations.DataBean> stations;

    public String toJson(){
        String jsonStr = null;
        jsonStr = JSON.toJSONString(this,SerializerFeature.PrettyFormat);
        return jsonStr;
    }

    public void addStation(DTO.Stations.DataBean station){
        if (this.stations == null){
            this.stations = new ArrayList<>();
            this.stations.add(station);
        }else {
            this.stations.add(station);
        }
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

    public List<Stations.DataBean> getStations() {
        return stations;
    }

    public void setStations(List<Stations.DataBean> stations) {
        this.stations = stations;
    }


}
