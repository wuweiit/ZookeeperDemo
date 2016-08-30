package dao;
/**
 *  
 *  吴伟 版权所有
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 用户数据库操作对象测试
 * @author marker
 * @date 2015-08-01 上午9:47:26
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
//@Transactional(isolation=Isolation.READ_UNCOMMITTED) /* 配置未提交读事务 */ 
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {
		"classpath:config/spring/spring-kafka.xml"
}
)
public class DubboConsumerTest {

	@Autowired
	KafkaTemplate kafkaTemplate;

	@Autowired
	MessageChannel messageChannel;

    /**
     *
     * @throws Exception
     */
	@Test public void test() throws Exception{



		messageChannel.s.

		for(int i=0; i<10000; i++){
			kafkaTemplate.send("foo","hahahhahahha"+i);

		}



	}
}
 