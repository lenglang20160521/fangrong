import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StructureTree<E> {

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes; // 使用一个Node[]数组来记录该树里的所有节点
    private int nodeNums;  // 记录树的节点数

    // 以指定根节点、指定treeSize创建树。 构造函数
    public StructureTree(E data, int treeSize){
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }

    //为指定节点添加子节点
    public void addNode(E data, Node parent){
        for (int i = 0; i < treeSize; i++){
            //找到数组中第一个为Null的元素,该元素保存新节点
            if (nodes[i] == null){
//                创建新节点，并用指定的数组保存它
                nodes[i] = new Node<>(data, pos(parent));
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("该树已满，无法添加新节点");
    }

    //返回根节点
    public Node<E> root() {
        return nodes[0];
    }

    // 返回指定节点（非根结点）的父节点
    public Node<E> parent(Node node) {
        return nodes[node.parent];
    }

    // 返回指定节点（非叶子节点）的所有子节点
    public List<Node<E>> children(Node parent){
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            // 如果当前节点的父节点的位置等于parent节点的位置
            if (nodes[i] != null && nodes[i].parent == pos(parent)){
                list.add(nodes[i]);
            }
        }
        return list;
    }
    //返回所有节点列表，包括根节点
    public List<Node<E>> getAllNodes(){
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            // 如果当前节点的父节点的位置等于parent节点的位置
            if (nodes[i] != null){
                list.add(nodes[i]);
            }
        }
        return list;
    }

//返回所有叶子节点
    public List<Node<E>> getAllLeafNode(){
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++){
            if(nodes[i] != null) {
                List<Node<E>> aa = children(nodes[i]);
                if (aa.size() == 0) {
                    list.add(nodes[i]);
                }
            }
        }
        return list;
    }

    // 返回该树的深度
    public int deep() {
        // 用于记录节点的最大深度
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++){
            // 初始化本节点的深度
            int def = 1;
            // m 记录当前节点的父节点的位置
            int m = nodes[i].parent;
            // 如果其父节点存在
            while (m != -1 && nodes[m] != null){
                // 向上继续搜索父节点
                m = nodes[m].parent;
                def++;
            }
            if (max < def){
                max = def;
            }
        }
        return max;
    }

    //返回给定深度层级上的节点
    public  List<Node<E>> getLayerNodes(int x){
        int treeDeep = deep();
        List<Node<E>> layerNodes = new ArrayList<>();
        if(x > treeDeep){
            System.out.println("查询深度 "+String.valueOf(x)+" 大于树本身的深度 "+String.valueOf(treeDeep)+"。");
            return layerNodes;
        }
        for (int i = 0; i < treeSize && nodes[i] != null; i++){
            // 初始化本节点的深度
            int def = 1;
            // m 记录当前节点的父节点的位置
            int m = nodes[i].parent;
            // 如果其父节点存在
            while (m != -1 && nodes[m] != null){
                // 向上继续搜索父节点
                m = nodes[m].parent;
                def++;
            }
            if (x == def){
                layerNodes.add(nodes[i]);
            }
        }
        return layerNodes;
    }
    // 返回包含指定值的节点号
    public int pos(Node node) {
         for (int i = 0; i < treeSize; i++) {
             // 找到指定节点
             if (nodes[i] == node) {
                 return i;
              }
         }
       return -1;
    }

    //返回指定对象的节点号
    public  Node getObjectNode(Object data){
        Node node = null;
        for (int i = 0; i < treeSize; i++){
            if (nodes[i].data.equals(data)){
                node = nodes[i];
                break;
            }
        }
        return node;
    }

//    遍历打印所有节点
    public  void printNode(){
        for (int i = 0; i < treeSize; i++){
            Node node = nodes[i];
            if (node != null){
                System.out.println(node.toString());
            } else {
//                System.out.println("已遍历完");
                break;
            }
        }
    }
// 返回树上面的所有对象
    public  List<Object> getObjectsList(){
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < treeSize; i++){
            Node node = nodes[i];
            if (node != null){
                objectList.add(node.data);
            }
        }
       return objectList;
    }

    //打印树的路径
    public void printPath(){
        if (deep() > 1) {
            List<Node<E>> allLeafNode = getAllLeafNode();
            for (Node node : allLeafNode) {
                List<Node<E>> pathList = new ArrayList<>();
                pathList.add(0,node);
                Node parentNode = nodes[node.parent];
                pathList.add(1,parentNode);
                Integer n = 2;
                while (parentNode.parent >= 0) {
                    parentNode = nodes[parentNode.parent];
                    pathList.add(n,parentNode);
                    n++;
                }

                for (int m = pathList.size()-1; m >= 0;m-- ){
                    if (m == 0){
                        System.out.println(pathList.get(m).data.toString());
                    }else {
                        System.out.println(pathList.get(m).data.toString());
                        System.out.println("|");
                        System.out.println("|");
                    }
                }
                System.out.println("*****************************************");
            }
        }else {
            System.out.println(nodes[0].toString());
        }
    }

//    public String toJson(){
//        FangRongTree frTree = new FangRongTree();
//        frTree.setId(000);
//        frTree.setName("fangrong");
//        for (int m = 1; m < deep(); m++){
//            List<Node<E>> layerNodes = getLayerNodes(m);
//            for (Node node : layerNodes){
//                if (node instanceof DTO.Stations.DataBean){
//
//                }
//            }
//        }
//        String jsonStr = JSON.toJSONString(children(nodes[0]));
//        return jsonStr;
//    }

    public  static  class  Node<T>{
        T data;
        int parent; //保存其父节点位置


//        构造函数
        public Node(){}
        public Node(T data){
            this.data = data;
        }
        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public String toString(){
            return  data.toString();
        }

//        public String toString(){
//            return "StructureTree$Node[data=" + data + ", parent=" + parent + "]";
//        }
    }
}
