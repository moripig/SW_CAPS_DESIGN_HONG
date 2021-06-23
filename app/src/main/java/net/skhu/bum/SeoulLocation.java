package net.skhu.bum;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SeoulLocation {

    private List<SeoulInfo> seoul = new ArrayList<SeoulInfo>();

    public List<SeoulInfo> getSeoulInfo() {
        return seoul;
    }
    public void start() {
        String url = "http://openapi.seoul.go.kr:8088/6f7741686e766c64313131697a586b67/xml/SdeTlSccoSigW/1/25/";
        OpenAPI location = new OpenAPI(url);
        location.execute();

    }


    public class OpenAPI extends AsyncTask<Void, Void, String> {

        private String url;


        public OpenAPI(String url) {

            this.url = url;
        }

        @Override
        protected String doInBackground(Void... params) {

            // parsing할 url 지정(API 키 포함해서)

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(url);
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            }

            // root tag
            doc.getDocumentElement().normalize();

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("row");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    SeoulInfo gu = new SeoulInfo();
                    gu.setXYNameGu(getTagValue("LAT",eElement),getTagValue("LNG",eElement),getTagValue("SIG_KOR_NM",eElement));
                    seoul.add(temp,gu);
                }	// if end
            }	// for end
            super.cancel(true);
            return null;
        }
        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
        }
    }

    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
