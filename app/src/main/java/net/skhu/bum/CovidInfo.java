package net.skhu.bum;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class CovidInfo  {

    List<SeoulInfo> seoulInfo;
    public void start(List<SeoulInfo> seoulInfo) {
        String url = "http://openapi.seoul.go.kr:8088/6f7741686e766c64313131697a586b67/xml/TbCorona19CountStatusJCG/1/1/";
        this.seoulInfo = seoulInfo;
        OpenAPI covidAPI = new OpenAPI(url);
        covidAPI.execute();
    }
    public List<SeoulInfo> getSeoulInfo() {return seoulInfo; }
    public class OpenAPI extends AsyncTask<Void, Void, String> {

        private String url;

        public OpenAPI(String url) {

            this.url = url;
        }

        @Override
        protected String doInBackground(Void... params) {

            // parsing할 url 지정(API 키 포함해서)

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactoty.newDocumentBuilder();
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

                   Seoul seoul = new Seoul();
                    String[][] info = seoul.set();

                    for(int i=0; i<info[1].length;i++) {
                        for (int j = 0; j < seoulInfo.size(); j++) {
                            if(info[2][i].equals(seoulInfo.get(j).getNameGu())) {
                               SeoulInfo temp2 = seoulInfo.get(j);
                                temp2.setNameGu(seoulInfo.get(j).getNameGu());
                                temp2.setCovidInfo(getTagValue(info[0][i], eElement), getTagValue(info[1][i], eElement));
                                seoulInfo.set(j,temp2);
                                Log.d("OPEN_API", "이름 : " + seoulInfo.get(j).getNameGu());
                                Log.d("OPEN_API", "총합 : " + seoulInfo.get(j).getTotal());
                                Log.d("OPEN_API", "오늘 : " + seoulInfo.get(j).getToday());
                                Log.d("OPEN_API", "X : " + seoulInfo.get(j).getX());
                                Log.d("OPEN_API", "Y : " + seoulInfo.get(j).getY());
                                break;
                            }
                        }
                    }


                }	// for end
            }	// if end
            super.cancel(true);
            return null;
        }

        @Override
        protected void onPostExecute(String str) { super.onPostExecute(str); }
    }

    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}