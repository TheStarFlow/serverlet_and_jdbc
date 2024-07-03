import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/nice")
public class SecondHttpHandler extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println(this.getClass().getSimpleName() + "执行初始化");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        System.out.println("Second 客户端URL:" + url);

        String queryString = request.getQueryString();
        System.out.println("Second 获取参数:" + URLDecoder.decode(queryString, StandardCharsets.UTF_8));

        String name = request.getParameter("name");
        System.out.println("Second 获取的参数是：" + URLDecoder.decode(name, StandardCharsets.UTF_8));

        //请求转发后为同一个 request 对象，可以拿到请求作用域中的对象
        Object hello = request.getAttribute("Hello");
        if (hello != null) {
            System.out.println("Second 请求转发后拿到作用域对象:" + hello);
        }
        Object intValue = request.getAttribute("1");
        if (hello != null) {
            System.out.println("Second 请求转发后拿到作用域对象:" + intValue);
        }

        //设定指定编码方式
        // response.setCharacterEncoding("UTF-8");
        //响应对象 二选一，不能同时使用
        //response.getWriter().write("<h1>Hello world~</h1>");
        // 可以写一切的字节流
        response.getOutputStream().write("<h1>Hello world ！！！</h1>".getBytes());


        //设置Cookie,一服务端像存放客户端存放数据的行为，可以设置过期时间，可以基于内存或者基于时间过期
        Cookie cookie = new Cookie("name","Zaine");
        //cookie 作用域数据
        cookie.setAttribute("heihei","haha");
        //负整数表示不存储 cookie,该 cookie 只在内存中存活，关闭浏览器重开就没了
        // 0 表示要删除这个cookie 如果客户端有的话
        // 大于0时表示存储的秒数
        cookie.setMaxAge(30);
        // / 项目下都可以获取cookie  /handler 这个路径下才可以获取cookie
        cookie.setPath("/handler");
        response.addCookie(cookie);

        //回话 客户端和服务器建立的会话
        HttpSession session = request.getSession();
        //基于session 也可以有自己的生命周期作用域数据
        session.setAttribute("fine","im fine");
        //是不是新建立的会话
        boolean isNew = session.isNew();
        System.out.println("isNew session :" + isNew);
        //默认存活时间是 30 min 设置是基于 秒为单位的
        session.setMaxInactiveInterval(10);


        ServletContext servletContext = request.getServletContext();
        //获取之前设置的 servletContext 数据
        Object hello1 = servletContext.getAttribute("Hello");
        if (hello1!=null){
            System.out.println("从接口转发获取的数据：" + hello1);
        }


        //重定向，客户端浏览器重新发请求，地址栏会变化，是重新发起一个新的请求
       // response.sendRedirect("index.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        //也可以在这里处理请求
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //也可以在这里处理请求

    }
}
