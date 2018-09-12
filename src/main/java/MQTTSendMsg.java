import DTO.resultPoints;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.List;
import java.util.Map;


import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

public class MQTTSendMsg {

    public static  void sendMsg(String msg){
        /**
         * 设置当前用户私有的 MQTT 的接入点。例如此处示意使用 XXX，实际使用请替换用户自己的接入点。接入点的获取方法是，在控制台创建 MQTT 实例，每个实例都会分配一个接入点域名。
         */
        final String broker ="tcp://dev.envisioncn.com:11883";
        /**
         * 该topic名称与Thing所用的策略绑定，需要保证策略拥有对应topic的订阅或者发送权限
         */
        final String topic ="Master";
        /**
         * MQTT 的 ClientID，可以用户自定义，只需要保证用户所用的名称不重复即可
         */
        final String clientId ="ene-lianYuan-sun";
        //设备名称
        final  String userName = "connect1";
        //设备秘钥
        final  String password = "hSxoKXgUUWe0n97gjTub+teaih5h78mvNHnDNLOAT46VGKrpjNQmG9AuOko=";

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(userName);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(password.toCharArray());
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(90);
            connOpts.setAutomaticReconnect(true);
            connOpts.setMqttVersion(MQTT_VERSION_3_1_1);

            sampleClient.setCallback(new MqttCallbackExtended() {
                public void connectComplete(boolean reconnect, String serverURI) {
                    System.out.println("connect success");
                    //连接成功，需要上传客户端所有的订阅关系
                }

                //client连接丢失
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                }
                //client收到消息
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }
                //发送的消息被正确接受并确认
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });

            MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
            mqttMessage.setQos(1);
            try {
                //订阅topic的消息，如果有client往该topic发送消息，client会收到并在回调方法中打印该消息
                sampleClient.subscribe(topic);
                //发送消息到该topic
                sampleClient.publish(topic, mqttMessage);
            } catch (MqttException e) {
                e.printStackTrace();
            }

            System.out.println("send => " + msg);

        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String newToken = HttpClientUtil.getNewToken();
//        获取某个站下所有测量点的名称
        List<Integer> stationsIds = HttpClientUtil.getStationIds(newToken);
        for (Integer stationId : stationsIds) {
//            System.out.println("-------------------" + stationId.toString() + "--------------------------------");
            Map<Integer, String> anaPointIdShortNameMaps = HttpClientUtil.getIdShortNameMaps(stationId, newToken);
            resultPoints resultPoints = HttpClientUtil.getResultPoints(stationId, newToken);
            resultPoints.setAnaPointIdShortNameMaps(anaPointIdShortNameMaps);
            resultPoints.createJsonMsgs();
            List<String> msgs = resultPoints.getJsonMsgs();
            for (String msg : msgs) {
//                System.out.println(msg);
                sendMsg(msg);
            }
        }
}
}
