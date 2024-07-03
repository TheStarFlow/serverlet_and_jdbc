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
        System.out.println(this.getClass().getSimpleName() + "ִ�г�ʼ��");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        System.out.println("Second �ͻ���URL:" + url);

        String queryString = request.getQueryString();
        System.out.println("Second ��ȡ����:" + URLDecoder.decode(queryString, StandardCharsets.UTF_8));

        String name = request.getParameter("name");
        System.out.println("Second ��ȡ�Ĳ����ǣ�" + URLDecoder.decode(name, StandardCharsets.UTF_8));

        //����ת����Ϊͬһ�� request ���󣬿����õ������������еĶ���
        Object hello = request.getAttribute("Hello");
        if (hello != null) {
            System.out.println("Second ����ת�����õ����������:" + hello);
        }
        Object intValue = request.getAttribute("1");
        if (hello != null) {
            System.out.println("Second ����ת�����õ����������:" + intValue);
        }

        //�趨ָ�����뷽ʽ
        // response.setCharacterEncoding("UTF-8");
        //��Ӧ���� ��ѡһ������ͬʱʹ��
        //response.getWriter().write("<h1>Hello world~</h1>");
        // ����дһ�е��ֽ���
        response.getOutputStream().write("<h1>Hello world ������</h1>".getBytes());


        //����Cookie,һ��������ſͻ��˴�����ݵ���Ϊ���������ù���ʱ�䣬���Ի����ڴ���߻���ʱ�����
        Cookie cookie = new Cookie("name","Zaine");
        //cookie ����������
        cookie.setAttribute("heihei","haha");
        //��������ʾ���洢 cookie,�� cookie ֻ���ڴ��д��ر�������ؿ���û��
        // 0 ��ʾҪɾ�����cookie ����ͻ����еĻ�
        // ����0ʱ��ʾ�洢������
        cookie.setMaxAge(30);
        // / ��Ŀ�¶����Ի�ȡcookie  /handler ���·���²ſ��Ի�ȡcookie
        cookie.setPath("/handler");
        response.addCookie(cookie);

        //�ػ� �ͻ��˺ͷ����������ĻỰ
        HttpSession session = request.getSession();
        //����session Ҳ�������Լ���������������������
        session.setAttribute("fine","im fine");
        //�ǲ����½����ĻỰ
        boolean isNew = session.isNew();
        System.out.println("isNew session :" + isNew);
        //Ĭ�ϴ��ʱ���� 30 min �����ǻ��� ��Ϊ��λ��
        session.setMaxInactiveInterval(10);


        ServletContext servletContext = request.getServletContext();
        //��ȡ֮ǰ���õ� servletContext ����
        Object hello1 = servletContext.getAttribute("Hello");
        if (hello1!=null){
            System.out.println("�ӽӿ�ת����ȡ�����ݣ�" + hello1);
        }


        //�ض��򣬿ͻ�����������·����󣬵�ַ����仯�������·���һ���µ�����
       // response.sendRedirect("index.html");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        //Ҳ���������ﴦ������
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //Ҳ���������ﴦ������

    }
}
