import config.YuanJingConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MqttClientUtil {

    public  static  MqttClient getMqttClient(){
        //broker 地址,tcp协议端口统一为11883
      String brokerHost = YuanJingConfig.brokerHost;
        //设备名称
      String userName = YuanJingConfig.userName;
        //设备秘钥
        String password = YuanJingConfig.password;
        //topicName topic名称，该topic名称与Thing所用的策略绑定，需要保证策略拥有对应topic的订阅或者发送权限
        String topic = YuanJingConfig.topic;
        //连接client名称，可以用户自定义，只需要保证用户所用的名称不重复即可
        String clientId = YuanJingConfig.clientId;

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());

        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient(brokerHost, clientId, new MqttDefaultFilePersistence());
            mqttClient.connect(connOpts);
         }catch(MqttException e){
              e.printStackTrace();
        }

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                //client连接丢失
                System.out.println("client lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //client收到消息
                System.out.println("rcv msg " + String.valueOf(message.getPayload()) + "from topic " + topic);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                //发送的消息被正确接受并确认
                System.out.println("send msg successfully");
            }
        });

        return mqttClient;
    }
}

