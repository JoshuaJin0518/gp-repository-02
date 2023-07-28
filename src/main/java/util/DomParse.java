package util;

import bean.ZouJin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomParse {
    private static String path;



    public static <T> T getBean(Class<T> target) throws Exception {
        // 创建DocumentBuilderFactory实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // 创建DocumentBuilder实例
        DocumentBuilder builder = factory.newDocumentBuilder();

        // 解析XML文件，获取Document对象
        Document document = builder.parse("src/main/resources/"+path);

        // 获取根元素
        Element rootElement = document.getDocumentElement();

        // 获取元素列表
        NodeList beanList = rootElement.getElementsByTagName("bean");
        for (int i = 0; i < beanList.getLength(); i++) {
            Element item =(Element) beanList.item(i);
            String aClass = item.getAttribute("class");
           return (T)   Class.forName(aClass.trim()).getConstructor().newInstance();
        }
        return null;
    }




    public  String getPath() {
        return path;
    }

    public static void setPath(String path) {
        DomParse.path = path;
    }



}
