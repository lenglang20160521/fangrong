import DTO.*;


import java.sql.SQLOutput;
import java.util.*;

public class AA {




    public  static  void  printCircuit(Integer mystationId,Integer mycircuitId, String token,Boolean tag) {
        if (tag) {
            Circuits circuits = HttpClientUtil.getCircuitCircuits(mystationId, mycircuitId, token);
            int a = circuits.getData().size();
            if (a != 0){
                for (DTO.Circuits.DataBean circuit : circuits.getData()){
                    Integer myNewCircuitId = circuit.getId();
                    tag = true;
                    AA.printCircuit(mystationId,myNewCircuitId, token,tag);
                }
            }else {
                return;
            }
        } else {
            return;
        }
    }

    //判断某条回路是否有子回路
    public  static  Boolean isHaveChildCircuits(Integer mystationId,Integer mycircuitId, String token){
        Circuits circuits = HttpClientUtil.getCircuitCircuits(mystationId, mycircuitId, token);
//        System.out.println(circuits.getData());
        int a = circuits.getData().size();
        if (a != 0){
            return true;
        }else {
            return false;
        }
    }

    public static Map<Object,Set<Object>> put(Integer stationId, Circuits.DataBean circuit, String token,Map<Object,Set<Object>> allNodes){
        Set<java.lang.Object> circuitNodeSet = new HashSet<>();
        Integer circuitId = circuit.getId();
        Circuits circuits = HttpClientUtil.getCircuitCircuits(stationId, circuitId, token); //查出子回路
        if (circuits.getMsg() == null && circuits.getData().size() != 0){
            for (DTO.Circuits.DataBean circuit_1: circuits.getData()){
                circuitNodeSet.add(circuit_1);
            }
            allNodes.put(circuit,circuitNodeSet);
       }
       return  allNodes;
    }

    public  static void PPP(Integer stationId, String token,List<DTO.Circuits.DataBean> notYeNode,Map<Object,Set<Object>> allNodes) {

        while (notYeNode.size() != 0) {
            DTO.Circuits.DataBean circuitNode = notYeNode.get(0);
            Boolean isHaveChildCircuits = isHaveChildCircuits(stationId, circuitNode.getId(), token); //是否存在子回路
            if (isHaveChildCircuits) {
                Set<Object> circuitNodeSet = new HashSet<>();
                Circuits circuits = HttpClientUtil.getCircuitCircuits(stationId, circuitNode.getId(), token);
                if (circuits.getMsg() == null && circuits.getData().size() != 0) {
                    for (DTO.Circuits.DataBean circuit_1 : circuits.getData()) {
                        circuitNodeSet.add(circuit_1);
                    }
                }
                allNodes.put(circuitNode, circuitNodeSet);
                notYeNode.remove(0);
            } else {
                notYeNode.remove(0);
            }
        }
        System.out.println(stationId.toString()+"完成。");
    }

    /*public static Map<Object,Set<Object>> gg(Object n,Map<Object,Set<Object>> allNodes,Map<Object,Set<Object>> newAllNodes,Set<Object> kSet){
        Set<Object> myVSet = allNodes.get(n);
        newAllNodes.put(n,myVSet);
        for (Object v: myVSet){
            if(isInSet(v,kSet)){
                return gg(v, allNodes, newAllNodes, kSet);
            }else {
                return newAllNodes;
            }
        }

    }*/
   /*public  static  Map<Object,Set<Object>> getNewAllNodes(Map<Object,Set<Object>> allNodes) {
       Map<Object, Set<Object>> newAllNodes = new HashMap<>();
       newAllNodes.put("fangrong", allNodes.get("fangrong"));
       Set<Object> kSet = new HashSet<>();
       Set<Object> vSet = new HashSet<>();
       for (Map.Entry<Object, Set<Object>> entry : allNodes.entrySet()) {
           if (entry.getKey() instanceof DTO.Stations.DataBean) {
               newAllNodes.put(entry.getKey(), entry.getValue());
               kSet.add(entry.getKey());
               vSet.addAll(entry.getValue());
           }
       }
       Set<Object> rootSets = new HashSet<>();
       for (Object k : kSet) {
           if (!isInSet(k, vSet)) {
               rootSets.add(k);
               newAllNodes.put(k, allNodes.get(k));
           }
       }


       for (Object r : rootSets) {
//           Set<Object> aa = allNodes.get(r);
           for (Object v : allNodes.get(r)) {
               while (isInSet(v, kSet)) {

               }
           }
       }
//       for (Map.Entry<Object,Set<Object>> entry: allNodes.entrySet()){
//           if (entry.getKey() instanceof DTO.Circuits.DataBean){
//
//           }
//       }
//       StructureTree tree = new StructureTree(2000);
   }*/

    public  static  Map<Object,Set<Object>> getNodeParentsMap(Map<Object,Set<Object>> allNodes){

        Set<Object> allNotYeZiNode = allNodes.keySet();
        Map<Object,Set<Object>> newAllNodes = new HashMap<>();
        for (Object notYeZiNode: allNotYeZiNode){
            if(notYeZiNode instanceof DTO.Circuits.DataBean){
                for (Object c: allNodes.get(notYeZiNode)){
                    if(isInSet(c,allNotYeZiNode)){
                        Set<Object> aaa = allNodes.get(c);
                        allNodes.get(notYeZiNode).addAll(aaa);
                    }
                }
//                System.out.println("我是回路："+notYeZiNode.toString());
            }
        }


        Map<Object,Set<Object>> nodeParentsMap = new HashMap<>();
//        Map<Object,List<Object>> nodeParentsMap = new HashMap<>();
        Set<Object> allNodesInFangRong = allNodes.get("fangrong");

/*//        打印所有非叶子节点及它包含的所有子节点
        for (Map.Entry<Object,Set<Object>> entry: allNodes.entrySet()){
            System.out.println("非叶子节点： "+entry.getKey().toString());
            System.out.println("-----------包含的子节点：");
            Set<Object> childNodes = entry.getValue();
            for (Object m: childNodes){
                System.out.println("      "+m.toString());
            }
        }*/

        Integer n =0;
        for (Object node: allNodesInFangRong ){
            Set<Object> parentsSet = new HashSet<>();
//            List<Object> parentsList = new ArrayList<>();
            for (Object k: allNotYeZiNode){
//                System.out.println("allNotYeZiNode: "+k.toString());
                if (k.toString() == "fangrong"){
//                    parentsList.add(k);
                    parentsSet.add(k);
                }else {
                    Set<Object> set = allNodes.get(k);
//                    for (Object s: set){
//
//                    }
                    Boolean isIn = isInSet(node, set);
                    if (node.toString().equalsIgnoreCase("id: 100000119  name: 进线   voltCode: 2   station: 2140000003")){
                        System.out.println("***************************************************************");
                        System.out.println("119 : "+node.toString());
                        System.out.println("k: "+k.toString());
                        for (Object x: set){
                            System.out.println("  "+x.toString());
                        }
                        System.out.println(isIn);
                    }
                    if (isInSet(node, set)) {
//                        System.out.println(node.toString()+"的父节点"+k.toString());
                        parentsSet.add(k);
//                        parentsList.add(k);
                    }
                }
            }
            nodeParentsMap.put(node,parentsSet);
            n++;
//            System.out.println(n.toString()+"  "+node.toString());
//            nodeParentsMap.put(node,parentsList);
        }
        for (Map.Entry<Object,Set<Object>> entry :nodeParentsMap.entrySet()){
            System.out.println(entry.getKey().toString());
            System.out.println("------所有父节点：");
            for (Object e: entry.getValue()){
                System.out.println("#####"+e.toString());
            }
        }
        return nodeParentsMap;
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

    public static  Map<Object,Map<String,Object>> getNodeInfo(Map<Object,Set<Object>> allNodes){
//可能有错。要遍历“方融”下的所有节点。
        Map<Object,Set<Object>> nodeParentsMap = getNodeParentsMap(allNodes);
        Map<Object,Map<String,Object>> AAPlus = new HashMap<>();
        for (Map.Entry<Object,Set<Object>> entry :nodeParentsMap.entrySet()){
            Map<String,Object> info = new HashMap<>();
           Integer layer = entry.getValue().size();
//            System.out.println(layer.toString());
           info.put("layer",layer);
           info.put("mayBeParents",entry.getValue());
           AAPlus.put(entry.getKey(),info);
        }
        return AAPlus;
    }

    public  static  Map<Integer,Set<Object>> getLayerMaps(Map<Object,Set<Object>> allNodes){
        Map<Object,Set<Object>> nodeParentsMap = getNodeParentsMap(allNodes);
        Map<Integer,Set<Object>> layerMaps = new HashMap<>();
        Set<Integer> aa = new HashSet<>();
        for (Map.Entry<Object,Set<Object>> entry :nodeParentsMap.entrySet()){
            aa.add(entry.getValue().size());
        }

        Integer maxLayer = Collections.max(aa);
        for(int i =0; i <= maxLayer; i++){
            Set<Object> layerNodes = new HashSet<>();
            if (i == 0){
                layerNodes.add("fangrong");
            }else {
                for (Map.Entry<Object,Set<Object>> entry :nodeParentsMap.entrySet()){
                    if(entry.getValue().size() == i){
                        layerNodes.add(entry.getKey());
                    }
                }
            }
            layerMaps.put(i,layerNodes);
        }
        System.out.println("最大层级: "+maxLayer.toString());
        for (Map.Entry<Integer,Set<Object>> entry: layerMaps.entrySet()){
            System.out.println("第"+entry.getKey().toString()+"层：");
            for(Object k: entry.getValue()){
                System.out.println("------"+k.toString());
            }
        }
        return layerMaps;
    }

    public  static  Object getOnlyParent(Set<Object> mayBeParents,Set<Object> parentLayerNodes){
        Object parent = null;
        List<Object> parentList = new ArrayList<>();
        /*System.out.println("可能父节点：");
        for (Object q: mayBeParents){
            System.out.println("-----"+q.toString());
        }
        System.out.println("父层所有节点：");
        for (Object w: parentLayerNodes){
            System.out.println("*****"+w.toString());
        }*/
        if (mayBeParents.size() ==1 ){
           return new ArrayList<>(mayBeParents).get(0);
        } else {
            Set<java.lang.Object> newMayBeParentsSet = new HashSet<>();
            for (java.lang.Object p: mayBeParents){
                if(p != "fangrong"){
                    newMayBeParentsSet.add(p);
                }
            }

            for (Object pp: newMayBeParentsSet){
                if(isInSet(pp,parentLayerNodes)){
                    parentList.add(pp);
                }else {
                    System.out.println("所有可能的父节点都不在父层中，无法确定直接父节点");
                }
            }
        }

        if(parentList.size() == 1){
            parent =parentList.get(0);
        }
        if (parentList.size() > 1){
            System.out.println("匹配出"+String.valueOf(parentList.size())+"个父节点: ");
            for (Object e: parentList){
                System.out.println("    "+e.toString());
            }
        }
        return parent;
    }
    public  static  void main(String[] args) {
//        Map<String,Object> tagMap = new HashMap<>();
//        tagMap.put("id",null);
//        tagMap.put("ifRecursive",true);
        Map<Object,Set<Object>> allNodes = new HashMap<>();

        String newToken = HttpClientUtil.getNewToken();
        Stations stations = HttpClientUtil.getStations(newToken);
        Set<Object> fangRongNodeSet = new HashSet<>();
        for (DTO.Stations.DataBean station: stations.getData()){
            fangRongNodeSet.add(station); //添加电站节点
            Integer stationId = station.getId();
            //遍历获取所有回路
            Circuits stationCircuits = HttpClientUtil.getStationCircuits(stationId,newToken);
            if (stationCircuits.getMsg() == null && stationCircuits.getData().size() != 0){
                for (DTO.Circuits.DataBean stationCircuit: stationCircuits.getData()){
                    fangRongNodeSet.add(stationCircuit); //添加所有回路节点
                }
             }
        }
        allNodes.put("fangrong",fangRongNodeSet);

        //添加电站下所有回路
        for (DTO.Stations.DataBean station: stations.getData()){
            Set<Object> stationNodeSet = new HashSet<>();
            Integer stationId = station.getId();
            Circuits stationCircuits = HttpClientUtil.getStationCircuits(stationId,newToken);
            if (stationCircuits.getMsg() == null && stationCircuits.getData().size() != 0){
                for (DTO.Circuits.DataBean stationCircuit: stationCircuits.getData()){
                    stationNodeSet.add(stationCircuit);
                }
            }
            allNodes.put(station,stationNodeSet);
        }

        //递归添加子回路
        for (DTO.Stations.DataBean station: stations.getData()){
            Integer stationId = station.getId();
            Circuits stationCircuits = HttpClientUtil.getStationCircuits(stationId,newToken);
            List<DTO.Circuits.DataBean> notYeNode = new ArrayList<>();
            if (stationCircuits.getMsg() == null && stationCircuits.getData().size() != 0){
                for (DTO.Circuits.DataBean stationCircuit: stationCircuits.getData()){
                    notYeNode.add(stationCircuit);
                }
            }
            PPP(stationId,newToken, notYeNode,allNodes);
        }



//        System.out.println(allNodes.get("fangrong").toString());
        Map<Object,Map<String,Object>> nodeInfo = getNodeInfo(allNodes);
        //打印所有节点信息
//        for (Map.Entry<Object,Map<String,Object>> enetry: nodeInfo.entrySet()){
//            System.out.println(enetry.getKey().toString());
//        }
//        Map<Integer,Set<Object>> layerMaps = getLayerMaps(allNodes);
//
//        //获取直接父节点
//        for(Map.Entry<Object,Map<String,Object>> entry: nodeInfo.entrySet()){ //???不是遍历“方融下节点”
//            Object node = entry.getKey();
//            Integer layerN = (Integer) entry.getValue().get("layer");
//            System.out.println("我："+node.toString());
//            System.out.println("我的层级："+layerN.toString());
//            Set<Object> parentLayerNodes = layerMaps.get(layerN-1);
//            Set<Object> mayBeParents = (Set<Object>) entry.getValue().get("mayBeParents");
//            Object parent= getOnlyParent(mayBeParents, parentLayerNodes);
//             }
        }
 }

