import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.beans.Encoder;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

@WebServlet("/handler")
public class MyHttpHandler extends HttpServlet {

    /**
     * serverlet 生命周期初始化
     */
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("生命周期初始化调用 init");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端完整URL，(从 http 开始到 ?前结束 )
        String url = request.getRequestURL().toString();
        System.out.println("客户端URL:" + url);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("部分URL:" + requestURL);
        request.setCharacterEncoding("UTF-8");
        String queryString = request.getQueryString();
        System.out.println("获取参数:" + queryString);
        String name = request.getParameter("name");
        System.out.println("获取的参数是："+ URLDecoder.decode(name, StandardCharsets.UTF_8));
        String authType = request.getAuthType();
        System.out.println("authType:" + authType);
        String protocol = request.getProtocol();
        System.out.println("协议版本:"+protocol);
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程:"+threadName);
        //设置基于 request 域对象为生命的作用域数据
        request.setAttribute("Hello","world");
        request.setAttribute("1","2");

        //session测试
        HttpSession session = request.getSession();
        Object fine = session.getAttribute("fine");
        System.out.println("获取session：" + fine);


        //servletContext对象，类似于安卓中的 Application，也可以作为一个作用域来存储数据
        ServletContext servletContext = request.getServletContext();
        //数据不移除的话整个服务器运行期间都会一直持有不释放
        servletContext.setAttribute("Hello","world");

        //请求转发,/nice获取到的是同一个请求
        request.getRequestDispatcher("/nice").forward(request,response);
        //转发后无效
        request.removeAttribute("1");


    }

    /**
     * serverlet 生命周期销毁
     */

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("生命周期初始化调用 destroy");

    }
}
