package util;

import bean.ZouJin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
//        DomParse.setPath("example.xml");
//        ZouJin bean = DomParse.getBean(ZouJin.class);
//        if (bean != null) {
//            bean.setName("Fuck");
//            bean.setAge(18);
//        }
//        System.out.println(bean);
//        ZouJin bean = (ZouJin)domParse.getBean();
        try {
            // 创建DOM解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/main/resources/example.xml"); // 替换成您的配置文件路径

            // 获取根元素
            Element root = document.getDocumentElement();

            // 获取所有的bean元素
            NodeList beanElements = root.getElementsByTagName("bean");

            // 遍历bean元素
            for (int i = 0; i < beanElements.getLength(); i++) {
                Element beanElement = (Element) beanElements.item(i);

                // 获取class属性值
                String className = beanElement.getAttribute("class");

                // 获取name属性值
                String name = beanElement.getAttribute("name");

                // 使用反射创建对象
                Class<?> clazz = Class.forName(className);
                Object instance = clazz.getDeclaredConstructor().newInstance();

                // 获取constructor-arg元素
                Element constructorArgElement = (Element) beanElement.getElementsByTagName("constructor-arg").item(0);

                // 获取属性值
                String argName = constructorArgElement.getAttribute("name");

                // 获取value属性值
                String argValue = constructorArgElement.getAttribute("value");

                // 使用setter方法设置属性值
                Method setNameMethod = clazz.getMethod("set" + argName.substring(0, 1).toUpperCase() + argName.substring(1), String.class);
                setNameMethod.invoke(instance, name);

// 设置年龄属性
                if (argName.equals("age")) {
                    Method setAgeMethod = clazz.getMethod("set" + argName.substring(0, 1).toUpperCase() + argName.substring(1), Integer.class);
                    setAgeMethod.invoke(instance, Integer.parseInt(argValue));
                }
                // 打印对象信息
                System.out.println(instance.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
