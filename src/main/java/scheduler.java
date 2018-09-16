import org.eclipse.paho.client.mqttv3.MqttClient;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class scheduler {
    public static void main(String[] args){
        try {
            //1.创建Scheduler的工厂
            SchedulerFactory sf = new StdSchedulerFactory();
            //2.从工厂中获取调度器实例
            Scheduler scheduler = sf.getScheduler();
            //任务运行的时间，SimpleSchedle类型触发器有效
            long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
            Date statTime = new Date(time);
            //3.创建Trigger
            //使用SimpleScheduleBuilder或者CronScheduleBuilder
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withDescription("")
                    .withIdentity("ramTrigger", "ramTriggerGroup")
                    //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(statTime)  //默认当前时间启动
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")) //30秒执行一次
                    .build();
            //4、定义一个JobDetail
            JobDetail job = JobBuilder.newJob(SendMsg.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job1", "group1") //定义name/group
                    .usingJobData("name", "quartz") //定义属性
                    .build();
//            传参
            MqttClient mqttClient = MqttClientUtil.getMqttClient();
            job.getJobDataMap().put("mqttClient",mqttClient);
            //5.注册任务和定时器
            scheduler.scheduleJob(job, trigger);
            //6.启动 调度器
            scheduler.start();
            System.out.println("启动时间 ： " + new Date());
        } catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
