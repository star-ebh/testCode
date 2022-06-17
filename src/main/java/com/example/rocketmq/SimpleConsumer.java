package com.example.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author hkh
 * @version 1.0.0
 * @Description 消费者
 * @createTime 2022年04月29日 17:59:00
 */
public class SimpleConsumer {
    public static void main(String[] args) throws MQClientException {
        //消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_order");
        //消费者从NameServer拿到topic的queue所在的Broker地址，多个用分号隔开
        consumer.setNamesrvAddr("10.20.3.143:9876;10.20.3.144:9876;10.20.3.145:9876;10.20.3.146:9876");
        //设置Consumer第一次启动是从队列头部开始消费
        //如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //subscribe订阅的第一个参数就是topic,第二个参数为生产者发送时候的tags，*代表匹配所有消息，
        //想要接收具体消息时用||隔开，如"TagA||TagB||TagD"
        consumer.subscribe("ab_order", "*");
        //Consumer可以用两种模式启动，广播（Broadcast）和集群（Cluster），广播模式下，一条消息会发送给所有Consumer，
        //集群模式下消息只会发送给一个Consumer
        consumer.setMessageModel(MessageModel.BROADCASTING);
        //批量消费,每次拉取10条
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                //msgs是一个List，一般是Consumer先启动，所有每次都是一条数据
                //如果Producer先启动Consumer端后启动，会积压数据，此时setConsumeMessageBatchMaxSize会生效,
                //msgs的数据就是十条
                StringBuilder sb = new StringBuilder();
                sb.append("msgs条数：").append(msgs.size());
                MessageExt messageExt = msgs.get(0);
                //消息重发了三次
                if (messageExt.getReconsumeTimes() == 3) {
                    //todo 持久化消息记录表
                    //重试了三次不再重试了，直接签收掉
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }

                for (MessageExt msg : msgs) {
                    try {
                        String topic = msg.getTopic();
                        String messageBody = new String(msg.getBody(), StandardCharsets.UTF_8);
                        String tags = msg.getTags();
                        //todo 业务逻辑处理
                        sb.append("topic:").append(topic).append(",tags:").append(tags).append(",msg:").append(messageBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 重新消费
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                System.out.println(sb.toString());
                //签收，这句话告诉broker消费成功，可以更新offset了，也就是发送ack。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }
}
