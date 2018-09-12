import DTO.resultPoints;
import config.YuanJingConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.util.List;
import java.util.Map;


import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

public class MQTTSendMsg {

    public static void sendMsg(String msg) throws MqttException {

        //broker 地址,tcp协议端口统一为11883
        String brokerHost = YuanJingConfig.brokerHost;

        //设备名称
        String userName = YuanJingConfig.userName;

        //设备秘钥
        String password = YuanJingConfig.password;

        //topicName topic名称，该topic名称与Thing所用的策略绑定，需要保证策略拥有对应topic的订阅或者发送权限
        final String topic = YuanJingConfig.topic;

        //连接client名称，可以用户自定义，只需要保证用户所用的名称不重复即可
        String clientId = YuanJingConfig.clientId;

        MqttClient mqttClient = new MqttClient(brokerHost, clientId,
                new MqttDefaultFilePersistence());

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

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        mqttClient.connect(connOpts);

        MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
        mqttMessage.setQos(1);
        try {
            //订阅topic的消息，如果有client往该topic发送消息，client会收到并在回调方法中打印该消息
            mqttClient.subscribe(topic);
            //发送消息到该topic
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        System.out.println("send => " + msg);
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
            try {
                for (String msg : msgs) {
//                System.out.println(msg);
                    sendMsg(msg);
                }
            }catch (MqttException e){
                e.printStackTrace();
            }

        }
}
}
