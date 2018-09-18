import DTO.*;
import component.*;

import java.util.*;

public class GetFangRongTree {

    //判断某条回路是否有子回路
    public  static  Boolean isHaveChildCircuits(Integer mystationId,Integer mycircuitId, String token){
        Circuits circuits = HttpClientUtil.getCircuitCircuits(mystationId, mycircuitId, token);
        int a = circuits.getCircuits().size();
        if (a != 0){
            return true;
        }else {
            return false;
        }
    }

    public  static  Boolean isInSet(Object node,Set<Object> set){
        Boolean is = null;
        for (Object k: set){
            if (k.equals(node)){
                is = true;
                break;
            }else {
                is = false;
            }
        }
        return  is;
    }

    public static Set<Circuit> getDirectChildCircuits(Integer stationId, Set<Circuit> branchCircuitChildCircuits,String newToken){
        Set<Circuit> directChildCircuits = new HashSet<>();

        Set<Object> newBranchCircuitChildCircuits = new HashSet<>(branchCircuitChildCircuits);
//
        Circuits stationCircuits = HttpClientUtil.getStationCircuits(stationId,newToken);
        if (stationCircuits.getMsg() == null && stationCircuits.getCircuits().size() != 0){
            for (Circuit stationCircuit: stationCircuits.getCircuits()){
                if ( !isInSet(stationCircuit,newBranchCircuitChildCircuits)){
                    directChildCircuits.add(stationCircuit);
                };
            }
        }
        return directChildCircuits;
    }

    public static void getNewTree(StructureTree tree,Map<Circuit,Set<Circuit>> circuitChildCircuitMap,List<Circuit> allCircuits){

        Integer n = allCircuits.size();

        while (n !=0 ) {
            List<Object> objectsList = tree.getObjectsList();
            for (Object node : objectsList) {
                if (node instanceof Circuit) {
                    Circuit circuit = (Circuit) node;
                    System.out.println("删除"+circuit.toString());
                    System.out.println("删除前"+n.toString());
                    n--;
                    allCircuits.remove(circuit);
                    System.out.println("删除后"+n.toString());
                    Set<Circuit> childCircuits = circuitChildCircuitMap.get(circuit);
                    System.out.println(childCircuits);
                    if (childCircuits != null && childCircuits.size() != 0) {
                        StructureTree.Node parentNode = tree.getObjectNode(circuit);
                        for (Circuit childCircuit : childCircuits) {
                            System.out.println("挂载节点"+childCircuit.getId()+"   到  "+circuit.getId());
                            tree.addNode(childCircuit, parentNode);
                        }
                    }
                }
            }
            System.out.println(allCircuits.size());
        }
    }

    public  static  void main(String[] args) {
        FangRong fangRong = new FangRong();
        fangRong.setId(000);
        fangRong.setName("fangrong");
        StructureTree tree = new StructureTree(fangRong,2000);
        String newToken = HttpClientUtil.getNewToken();
        Stations stations = HttpClientUtil.getStations(newToken);
        for (Station station: stations.getStations()){
            tree.addNode(station,tree.getObjectNode(fangRong));
        }

        List<Circuit> allCircuits = new ArrayList<>();
        for (Station station: stations.getStations()){
            Circuits stationCircuits = HttpClientUtil.getStationCircuits(station.getId(),newToken);
            if (stationCircuits.getMsg() == null && stationCircuits.getCircuits().size() != 0){
                for (Circuit stationCircuit: stationCircuits.getCircuits()){
                    allCircuits.add(stationCircuit);
                }
            }
        }

//        System.out.println("系统中所有回路：");
//        Integer n=1;
//        for (Circuit x: allCircuits){
//            System.out.println(n.toString()+"   "+x.toString());
//            n++;
//        }
        //回路---子回路 映射
        Map<Circuit,Set<Circuit>> circuitChildCircuitMap = new HashMap<>();
        for (Circuit circuit: allCircuits){
            Set<Circuit> childCircuits = new HashSet<>();
            Integer stationId = circuit.getStation();
            Boolean isHaveChildCircuits = isHaveChildCircuits(stationId,circuit.getId(),newToken);
            if(isHaveChildCircuits){
                Circuits circuits = HttpClientUtil.getCircuitCircuits(stationId, circuit.getId(), newToken);
                if (circuits.getMsg() == null && circuits.getCircuits().size() != 0){
                    for (Circuit childCircuit: circuits.getCircuits()){
                        childCircuits.add(childCircuit);
                    }
                }
            }
            circuitChildCircuitMap.put(circuit,childCircuits);
        }

//        for (Map.Entry<Circuit,Set<Circuit>> entry : circuitChildCircuitMap.entrySet()){
//            System.out.println(entry.getKey().toString()+" 的子回路为：");
//            for(Circuit q: entry.getValue()){
//                System.out.println("   "+q.toString());
//            }
//        }
        //获取分支回路（存在子回路的回路）
        Set<Circuit> branchCircuits = new HashSet<>();
        for (Map.Entry<Circuit,Set<Circuit>> entry: circuitChildCircuitMap.entrySet()){
            if(entry.getValue().size() != 0){
                branchCircuits.add(entry.getKey());
            }
        }

//        System.out.println("系统中所有存在子回路的分支回路:");
//        Integer k=1;
//        for(Circuit branchCircuit: branchCircuits){
//            System.out.println(k.toString()+"   "+branchCircuit.toString());
//            k++;
//        }
        //获取分支回路的子回路，也就是 父节点是 回路的 回路。
        Set<Circuit> branchCircuitChildCircuits = new HashSet<>();
        for (Circuit branchCircuit: branchCircuits ){
            branchCircuitChildCircuits.addAll(circuitChildCircuitMap.get(branchCircuit));
        }

//        System.out.println("系统中所有的父节点为回路的回路：");
//        Integer m=1;
//        for (Circuit branchCircuitChildCircuit: branchCircuitChildCircuits){
//            System.out.println(m.toString()+"   "+branchCircuitChildCircuit.toString());
//            m++;
//        }

        //挂载电站下的直接子回路
        for (Station station: stations.getStations()){
            Integer stationId = station.getId();
            Set<Circuit> directChildCircuits = getDirectChildCircuits(stationId, branchCircuitChildCircuits,newToken);
//            System.out.println("站 "+stationId.toString()+" 的直接子回路：");
            for(Circuit directChildCircuit: directChildCircuits){
//                System.out.println("    "+directChildCircuit.toString());
                tree.addNode(directChildCircuit,tree.getObjectNode(station));
            }
        }

        //挂载子回路
        Map<Integer,Set<Circuit>> pp = new HashMap<>();
        for (Map.Entry<Circuit,Set<Circuit>> entry: circuitChildCircuitMap.entrySet()){
            Integer id = entry.getKey().getId();
            pp.put(id,entry.getValue());
        }

        Boolean tag =true;
        while (tag) {
            int treeDeep_1 = tree.deep();
            List<StructureTree.Node> allLeafNode = tree.getAllLeafNode();
            for (StructureTree.Node leafNode : allLeafNode) {
//            System.out.println(leafNode.toString());
                Object dataObj = leafNode.data;
                if (dataObj instanceof Circuit) {
                    Set<Circuit> aa = pp.get(((Circuit) dataObj).getId());
                    if (aa == null | aa.size() == 0) {
//                        System.out.println("节点"+leafNode.toString()+"无子节点");
                    } else {
//                        System.out.println("节点"+leafNode.toString()+"的子节点为：");
                        for (Circuit x : aa) {
//                            System.out.println("    " + x.toString());
                            tree.addNode(x, leafNode);
                        }
                    }
                }
            }
            int treeDeep_2 = tree.deep();
//            System.out.println("treeDeep_1: "+String.valueOf(treeDeep_1));
//            System.out.println("treeDeep_2: "+String.valueOf(treeDeep_2));
            if (treeDeep_1 == treeDeep_2){
                tag = false;
            }
        }

        //添加断路器和变压器
        List<StructureTree.Node> allNodes = tree.getAllNodes();
        for (StructureTree.Node node : allNodes) {
//            System.out.println(leafNode.toString());
            Object nodeObj = node.data;
            if (nodeObj instanceof Circuit) {
                Integer stationId = ((Circuit) nodeObj).getStation();
                Integer circuitId = ((Circuit) nodeObj).getId();
                Breakers breakers = HttpClientUtil.getCircuitBreakers(stationId,circuitId,newToken);
                if (breakers.getMsg() == null && breakers.getBreakers().size() != 0) {
                    for (Breaker breaker : breakers.getBreakers()) {
//                    System.out.println(breaker.toString());
                        tree.addNode(breaker, node);
                    }
                }
                Transformers Transformers = HttpClientUtil.getCircuitTransformers(stationId,circuitId,newToken);
                if (Transformers.getMsg() == null && Transformers.getTransformers().size() != 0) {
                    for (Transformer transformer: Transformers.getTransformers()) {
//                    System.out.println(breaker.toString());
                        tree.addNode(transformer, node);
                    }
                }
            }
        }

        //输出路径
        tree.printPath();
//       //输出json
//        FangRongTree frTree = new FangRongTree();
//        int treeDeep = tree.deep();
//        for (int m = 1; m <= treeDeep; m++){
//            List<StructureTree.Node> layerNodes = tree.getLayerNodes(m);
//            for (StructureTree.Node node : layerNodes){
//                Object nodeObj = node.data;
//                //根节点
//                if (nodeObj instanceof FangRong){
//                    frTree.setId(((FangRong) nodeObj).getId());
//                    frTree.setName(((FangRong) nodeObj).getName());
//                }
//                //电站
//                if (nodeObj instanceof Station){
//                    frTree.addStation((Station) nodeObj);
//                }
//                //回路
//                if (nodeObj instanceof  Circuit){
//                   Object parnetNodeObj = tree.parent(node).data;
//                    if (parnetNodeObj instanceof Station){
//                        ((Station) parnetNodeObj).addCircuits((Circuit)nodeObj);
//                    }
//                    if (parnetNodeObj instanceof Circuit){
//                         ((Circuit) parnetNodeObj).addCircuits((Circuit)nodeObj);
//                    }
//                }
//                //断路器
//                if (nodeObj instanceof  Breaker){
//                    Object parnetNodeObj = tree.parent(node).data;
//                    if (parnetNodeObj instanceof Circuit){
//                        ((Circuit) parnetNodeObj).addBreakers((Breaker) nodeObj);
//                    }
//                }
//                //变压器
//                if (nodeObj instanceof  Transformer){
//                    Object parnetNodeObj = tree.parent(node).data;
//                    if (parnetNodeObj instanceof Circuit){
//                        ((Circuit) parnetNodeObj).addTransformers((Transformer) nodeObj);
//                    }
//                }
//            }
//        }
//        System.out.println(frTree.toJson());
    }
}
