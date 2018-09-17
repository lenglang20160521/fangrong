package config;

public class YuanJingConfig {
    //broker 地址,tcp协议端口统一为11883
    public  static  final  String brokerHost = "***************************8";
    //设备名称
    public  static  final String userName = "connect1";
    //设备秘钥
    public  static  final String password = "*************************";
    //topicName topic名称，该topic名称与Thing所用的策略绑定，需要保证策略拥有对应topic的订阅或者发送权限
    public  static  final String topic = "Master";
    //连接client名称，可以用户自定义，只需要保证用户所用的名称不重复即可
    public  static  final String clientId = "ene-lianYuan-sun";
//    设备的mdmId
public  static  final String object = "1bbb1e596f802000";
}
