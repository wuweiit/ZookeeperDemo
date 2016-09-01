import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ServerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        System.out.println("my Listener");




    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
