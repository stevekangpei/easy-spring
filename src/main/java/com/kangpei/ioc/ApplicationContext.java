package com.kangpei.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义一个容器
 */
public class ApplicationContext {

    private Map<String, Object>  singleObjects = new HashMap<String, Object>();

    public ApplicationContext(String location) throws Exception{
        loadBeans(location);
    }

    public void registerBean(String id, Object bean) {
        this.singleObjects.put(id, bean);
    }

    public Object getBean(String beanName) {

        Object bean = this.singleObjects.get(beanName);
        if (null == bean) {
            throw  new IllegalArgumentException("there is no bean with name " + beanName);
        }
        return bean;
    }

    private void  loadBeans(String location) throws Exception {

        // 先解析文件。
        InputStream in = new BufferedInputStream(new FileInputStream(location));
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(in);

        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        // 解析文件。
        for (int i=0; i< nodes.getLength(); i++) {
            Node item = nodes.item(i);
            if (item instanceof Element) {
                Element element = (Element) item;
                String id =element.getAttribute("id");
                String className = element.getAttribute("class");

                Class<?> clazz = Class.forName(className);

                // 创建bean实例
                Object instance = clazz.newInstance();
                NodeList propertyList = element.getElementsByTagName("property");

                for (int j =0; j < propertyList.getLength(); j++) {
                    Node node = propertyList.item(j);
                    if (node instanceof  Element) {
                        Element element1 = (Element) node;
                        String name = element1.getAttribute("name");
                        String value = element1.getAttribute("value");

                        Field fieldName = clazz.getDeclaredField(name);
                        fieldName.setAccessible(true);

                        if (value != null && value.length() > 0) {

                            fieldName.set(instance, value);
                        }else  { // 说明是ref属性
                            String ref = element1.getAttribute("ref");
                            if (null == ref || ref.length() ==0) {
                                throw  new IllegalArgumentException("reference property is null");
                            }
                            Field fieldRef = clazz.getDeclaredField(ref);
                            fieldRef.setAccessible(true);
                            fieldRef.set(instance, getBean(ref));

                        }
                    }
                }
                this.registerBean(id, instance);
            }
        }

    }

}
