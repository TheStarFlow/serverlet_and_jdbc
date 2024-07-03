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
     * serverlet �������ڳ�ʼ��
     */
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("�������ڳ�ʼ������ init");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡ�ͻ�������URL��(�� http ��ʼ�� ?ǰ���� )
        String url = request.getRequestURL().toString();
        System.out.println("�ͻ���URL:" + url);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("����URL:" + requestURL);
        request.setCharacterEncoding("UTF-8");
        String queryString = request.getQueryString();
        System.out.println("��ȡ����:" + queryString);
        String name = request.getParameter("name");
        System.out.println("��ȡ�Ĳ����ǣ�"+ URLDecoder.decode(name, StandardCharsets.UTF_8));
        String authType = request.getAuthType();
        System.out.println("authType:" + authType);
        String protocol = request.getProtocol();
        System.out.println("Э��汾:"+protocol);
        String threadName = Thread.currentThread().getName();
        System.out.println("��ǰ�߳�:"+threadName);
        //���û��� request �����Ϊ����������������
        request.setAttribute("Hello","world");
        request.setAttribute("1","2");

        //session����
        HttpSession session = request.getSession();
        Object fine = session.getAttribute("fine");
        System.out.println("��ȡsession��" + fine);


        //servletContext���������ڰ�׿�е� Application��Ҳ������Ϊһ�����������洢����
        ServletContext servletContext = request.getServletContext();
        //���ݲ��Ƴ��Ļ����������������ڼ䶼��һֱ���в��ͷ�
        servletContext.setAttribute("Hello","world");

        //����ת��,/nice��ȡ������ͬһ������
        request.getRequestDispatcher("/nice").forward(request,response);
        //ת������Ч
        request.removeAttribute("1");


    }

    /**
     * serverlet ������������
     */

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("�������ڳ�ʼ������ destroy");

    }
}
