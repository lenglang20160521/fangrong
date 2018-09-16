import DTO.Breakers;
import DTO.Circuits;
import DTO.FangRong;
import DTO.Stations;

import java.util.ArrayList;
import java.util.List;

public class BB {
    public static void main(String[] args) {
//        String newToken = HttpClientUtil.getNewToken();
//        Integer stationId = 2140000003;
//        Integer circuitsId = 100000086;
//        Circuits circuits = HttpClientUtil.getCircuitCircuits(stationId, circuitsId, newToken);
//        if (circuits.getMsg() == null && circuits.getData().size() != 0) {
//            for (DTO.Circuits.DataBean circuit_1 : circuits.getData()) {
//                System.out.println(circuit_1.toString());
//            }
//        }
        FangRong fangRong = new FangRong();
        fangRong.setId(000);
        fangRong.setName("fangrong");
        StructureTree tree = new StructureTree(fangRong,2000);
        String newToken = HttpClientUtil.getNewToken();
        Stations stations = HttpClientUtil.getStations(newToken);
        for (DTO.Stations.DataBean station: stations.getData()){
            tree.addNode(station,tree.getObjectNode(fangRong));
        }


        tree.printPath();
//        FangRongTree frTree = new FangRongTree();
//        int treeDeep = tree.deep();
//        for (int m = 1; m <= treeDeep; m++){
//            List<StructureTree.Node> layerNodes = tree.getLayerNodes(m);
//            for (StructureTree.Node node : layerNodes){
//                Object nodeObj = node.data;
//                if (nodeObj instanceof DTO.FangRong){
//                    frTree.setId(((FangRong) nodeObj).getId());
//                    frTree.setName(((FangRong) nodeObj).getName());
//                }
//                if (nodeObj instanceof DTO.Stations.DataBean){
//                    frTree.addStation((DTO.Stations.DataBean) nodeObj);
//                }
//                if (nodeObj instanceof  DTO.Circuits.DataBean){
//
//                }
//           }
//        }

//        for(DTO.Stations.DataBean station : stationsList){
//            System.out.println(station.toString());
//        }

//        System.out.println(frTree.toJson());
    }
}
