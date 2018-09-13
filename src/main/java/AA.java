import DTO.Breakers;
import DTO.Points;
import DTO.Stations;

public class AA {
    public  static  void main(String[] args) {

        String root = "fangrong";
        StructureTree<Object> StructureTree = new StructureTree<>(root, 20000);
        String newToken = HttpClientUtil.getNewToken();
        Stations stations = HttpClientUtil.getStations(newToken);
        for (DTO.Stations.DataBean station: stations.getData()){
            StructureTree.addNode(station,StructureTree.root());
            Integer stationId = station.getId();

            StructureTree.Node parentNode = StructureTree.getObjectNode(station);
            //            遍历挂载ana节点
            Points stationAnaPoints = HttpClientUtil.getStationAnaPoints(stationId,newToken);
            for (DTO.Points.DataBean stationAnaPoint: stationAnaPoints.getData()){
                StructureTree.addNode(stationAnaPoint,parentNode);
            }
            // 遍历挂载acc节点
            Points stationAccPoints = HttpClientUtil.getStationAccPoints(stationId,newToken);
            for (DTO.Points.DataBean stationAccPoint: stationAccPoints.getData()){
                StructureTree.addNode(stationAccPoint,parentNode);
            }
            //遍历挂载断路器
            Breakers stationBreakers = HttpClientUtil.getStationBreakers(stationId,newToken);
            for (DTO.Breakers.DataBean stationBreaker: stationBreakers.getData()){
                StructureTree.addNode(stationBreaker,parentNode);
            }
        }
        StructureTree.printNode();
    }
}
