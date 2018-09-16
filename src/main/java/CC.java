import DTO.Breakers;
import DTO.Circuits;
import DTO.Stations;
import DTO.Transformers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CC {
    public  static  void main(String[] args) {

        String newToken = HttpClientUtil.getNewToken();
//        Stations stations = HttpClientUtil.getStations(newToken);
//    Set<Object> fangRongNodeSet = new HashSet<>();
//        for (DTO.Stations.DataBean station: stations.getData()){
//        fangRongNodeSet.add(station); //添加电站节点
//        Integer stationId = station.getId();
//        Breakers breakers = HttpClientUtil.getStationBreakers(stationId,newToken);
//        System.out.println("电站 "+stationId+" 下断路器：");
//        for(DTO.Breakers.DataBean breaker: breakers.getData()){
//            System.out.println("    "+breaker.toString());
//        }
//    }
//    回路下变压器列表
        Integer stationId =  2140000001;
        Integer circuit = 100000017;
//        Transformers  Transformers = HttpClientUtil.getCircuitTransformers(stationId,circuit,newToken);
        Transformers  Transformers = HttpClientUtil.getStationTransformers(stationId,newToken);
        System.out.println(Transformers.getData().size());
        for(DTO.Transformers.DataBean transformer: Transformers.getData()){
            System.out.println(transformer.toString());
        }

}



}
