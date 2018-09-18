import DTO.Stations;
import DTO.resultPoints;
import component.Station;
import config.YuanJingConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.Map;


import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

public class SendMsg implements Job {

    public static void sendMsg(String msg, MqttClient mqttClient) throws MqttException {

        //topicName topic名称，该topic名称与Thing所用的策略绑定，需要保证策略拥有对应topic的订阅或者发送权限
         String topic = YuanJingConfig.topic;
        MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
        mqttMessage.setQos(1);
        try {
            //订阅topic的消息，如果有client往该topic发送消息，client会收到并在回调方法中打印该消息
//            mqttClient.subscribe(topic);
            //发送消息到该topic
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        System.out.println("send => " + msg);
    }

    public  static void sendTask(MqttClient mqttClient){
        //        获取新的Token
        String newToken = HttpClientUtil.getNewToken();
//        获取权限内的所有站
        Stations stations = HttpClientUtil.getStations(newToken);
        for (Station station : stations.getStations()) {
            Integer stationId = station.getId();
//            System.out.println("-------------------" + stationId.toString() + "--------------------------------");
            Map<Integer, String> anaPointIdShortNameMaps = HttpClientUtil.getIdShortNameMaps(stationId, newToken);
            resultPoints resultPoints = HttpClientUtil.getResultPoints(stationId, newToken);
            resultPoints.setAnaPointIdShortNameMaps(anaPointIdShortNameMaps);
            resultPoints.createJsonMsgs();
            List<String> msgs = resultPoints.getJsonMsgs();
            try {
                for (String msg : msgs) {
//                System.out.println(msg);
                    sendMsg(msg,mqttClient);
                }
            }catch (MqttException e){
                e.printStackTrace();
            }
        }
////        关闭mqtt客户端
//        try{
//            mqttClient.close();
//        } catch (MqttException e){
//            e.printStackTrace();
//        }
//
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        MqttClient mqttClient =(MqttClient)context.getJobDetail().getJobDataMap().get("mqttClient");
        sendTask(mqttClient);
    }

    public static void main(String[] args){
        MqttClient mqttClient = MqttClientUtil.getMqttClient();
        sendTask(mqttClient);
}
}
