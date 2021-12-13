package edu.skku.map.personal_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import okhttp3.HttpUrl;


public class MainActivity extends AppCompatActivity {
    Button button, beforeb, afterb;
    EditText game;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    CustomAdapter2 adapter2;
    TextView presentpo;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    int start = 1;
    int searchicount = 0;
    int searchmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        button = (Button) findViewById(R.id.sendingGET);
        game = (EditText)findViewById(R.id.game);
        presentpo = findViewById(R.id.present);
        final RadioButton gameb = findViewById(R.id.gamebutton);
        final RadioButton entb = findViewById(R.id.entbutton);
        beforeb = findViewById(R.id.before);
        beforeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchicount == 0)
                {
                    Toast.makeText(MainActivity.this, "표시 할 데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
                else if(start == 1)
                {
                    Toast.makeText(MainActivity.this, "이전 페이지가 없습니다", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(gameb.isChecked())
                    {
                        start = start - 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", game.getText().toString());
                        urlBulder.addQueryParameter("entname", "");
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask2 task = new GetXMLTask2();
                        task.execute(nurl);
                    }
                    else{
                        start = start - 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", "");
                        urlBulder.addQueryParameter("entname", game.getText().toString());
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask2 task = new GetXMLTask2();
                        task.execute(nurl);
                    }
                }
            }
        });
        afterb = findViewById(R.id.after);
        afterb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchicount == 0)
                {
                    Toast.makeText(MainActivity.this, "표시 할 데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
                if(start == searchmax)
                {
                    Toast.makeText(MainActivity.this, "다음 페이지가 없습니다", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(gameb.isChecked())
                    {
                        start = start + 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", game.getText().toString());
                        urlBulder.addQueryParameter("entname", "");
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask2 task = new GetXMLTask2();
                        task.execute(nurl);
                    }
                    else{
                        start = start + 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", "");
                        urlBulder.addQueryParameter("entname", game.getText().toString());
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask2 task = new GetXMLTask2();
                        task.execute(nurl);
                    }
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(gameb.isChecked())
                    {
                        start = 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", game.getText().toString());
                        urlBulder.addQueryParameter("entname", "");
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask task = new GetXMLTask();
                        task.execute(nurl);
                    }
                    else if(entb.isChecked())
                    {
                        start = 1;
                        presentpo.setText(String.valueOf(start));
                        HttpUrl.Builder urlBulder = HttpUrl.parse("http://www.grac.or.kr/WebService/GameSearchSvc.asmx/game").newBuilder();
                        urlBulder.addQueryParameter("gametitle", "");
                        urlBulder.addQueryParameter("entname", game.getText().toString());
                        urlBulder.addQueryParameter("rateno", "");
                        urlBulder.addQueryParameter("display", "10");
                        urlBulder.addQueryParameter("pageno", String.valueOf(start));
                        String nurl = urlBulder.build().toString();
                        GetXMLTask task = new GetXMLTask();
                        task.execute(nurl);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "검색할 데이터를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            Document doc = null;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            ArrayList<itemModel> newsibal = new ArrayList<>();
            NodeList count = doc.getElementsByTagName("tcount");
            Element countElement = (Element) count.item(0);
            count = countElement.getChildNodes();
            String c = ((Node) count.item(0)).getNodeValue();
            searchicount = Integer.parseInt(c);
            Log.d("hello", "" + searchicount);
            if(searchicount % 10 == 0)
            {
                searchmax = searchicount / 10;
            }
            else{
                searchmax = (searchicount / 10) + 1;
            }
            Log.d("hello", "" + searchmax);
            if (searchicount == 0) {
                Toast.makeText(MainActivity.this, "데이터가 존재하지 않습니다...", Toast.LENGTH_SHORT).show();
                adapter = new CustomAdapter(newsibal);
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                NodeList nodeList = doc.getElementsByTagName("item");
                //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
                for (int i = 0; i < nodeList.getLength(); i++) {

                    //날씨 데이터를 추출
                    String gmti;
                    String enti;
                    String hoti;
                    String giti;
                    String dati;
                    Node node = nodeList.item(i); //data엘리먼트 노드
                    Element fstElmnt = (Element) node;
                    NodeList nameList = fstElmnt.getElementsByTagName("gametitle");
                    if (nameList.item(0).getChildNodes().item(0) == null) {
                        gmti = "데이터 없음";
                    } else {
                        gmti = nameList.item(0).getChildNodes().item(0).getNodeValue();
                    }
                    NodeList websiteList = fstElmnt.getElementsByTagName("entname");
                    if (websiteList.item(0).getChildNodes().item(0) == null) {
                        enti = "데이터 없음";
                    } else {
                        enti = websiteList.item(0).getChildNodes().item(0).getNodeValue();
                    }
                    NodeList hopelist = fstElmnt.getElementsByTagName("hoperate");
                    if (hopelist.item(0).getChildNodes().item(0) == null) {
                        hoti = "데이터 없음";
                    } else {
                        hoti = hopelist.item(0).getChildNodes().item(0).getNodeValue();
                    }
                    NodeList givelist = fstElmnt.getElementsByTagName("givenrate");
                    if (givelist.item(0).getChildNodes().item(0) == null) {
                        giti = "데이터 없음";
                    } else {
                        giti = givelist.item(0).getChildNodes().item(0).getNodeValue();
                    }
                    NodeList datelist = fstElmnt.getElementsByTagName("rateddate");
                    if (datelist.item(0).getChildNodes().item(0) == null) {
                        dati = "데이터 없음";
                    } else {
                        dati = datelist.item(0).getChildNodes().item(0).getNodeValue();
                    }
                    itemModel gaesae = new itemModel(gmti, enti, hoti, giti, dati);
                    newsibal.add(gaesae);
                }
                adapter = new CustomAdapter(newsibal);
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
            super.onPostExecute(doc);
        }
    }
        private class GetXMLTask2 extends AsyncTask<String, Void, Document>{

            @Override
            protected Document doInBackground(String... urls) {
                URL url;
                Document doc = null;
                try {
                    url = new URL(urls[0]);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                    doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                    doc.getDocumentElement().normalize();

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
                }
                return doc;
            }

            @Override
            protected void onPostExecute(Document doc) {

                //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
                ArrayList<itemModel> newsibal = new ArrayList<>();

                    NodeList nodeList = doc.getElementsByTagName("item");
                    //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
                    for (int i = 0; i < nodeList.getLength(); i++) {

                        //날씨 데이터를 추출
                        String gmti;
                        String enti;
                        String hoti;
                        String giti;
                        String dati;
                        Node node = nodeList.item(i); //data엘리먼트 노드
                        Element fstElmnt = (Element) node;
                        NodeList nameList = fstElmnt.getElementsByTagName("gametitle");
                        if(nameList.item(0).getChildNodes().item(0) == null)
                        {
                            gmti = "데이터 없음";
                        }
                        else{
                            gmti = nameList.item(0).getChildNodes().item(0).getNodeValue();
                        }
                        NodeList websiteList = fstElmnt.getElementsByTagName("entname");
                        if(websiteList.item(0).getChildNodes().item(0) == null)
                        {
                            enti = "데이터 없음";
                        }
                        else{
                            enti = websiteList.item(0).getChildNodes().item(0).getNodeValue();
                        }
                        NodeList hopelist = fstElmnt.getElementsByTagName("hoperate");
                        if(hopelist.item(0).getChildNodes().item(0) == null)
                        {
                            hoti = "데이터 없음";
                        }
                        else{
                            hoti = hopelist.item(0).getChildNodes().item(0).getNodeValue();
                        }
                        NodeList givelist = fstElmnt.getElementsByTagName("givenrate");
                        if(givelist.item(0).getChildNodes().item(0) == null)
                        {
                            giti = "데이터 없음";
                        }
                        else{
                            giti = givelist.item(0).getChildNodes().item(0).getNodeValue();
                        }
                        NodeList datelist = fstElmnt.getElementsByTagName("rateddate");
                        if(datelist.item(0).getChildNodes().item(0) == null)
                        {
                            dati = "데이터 없음";
                        }
                        else{
                            dati = datelist.item(0).getChildNodes().item(0).getNodeValue();
                        }
                        itemModel gaesae = new itemModel(gmti, enti, hoti, giti, dati);
                        newsibal.add(gaesae);
                    }
                    adapter2 = new CustomAdapter2(newsibal);
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter2);

                super.onPostExecute(doc);
            }

    }//end inner class - GetXMLTask
    public class itemModel{

        String gametitle;
        String entname;
        String hoperate;
        String givenrate;
        String ratedate;
        String rateno;

        itemModel(String gametitle, String entname, String hoperate, String givenrate, String ratedate)
        {
            this.gametitle = gametitle;
            this.entname = entname;
            this.hoperate = hoperate;
            this.givenrate = givenrate;
            this.ratedate =ratedate;
        }
        public String getGametitle() {
            return gametitle;
        }

        public void setGametitle(String gametitle) {
            this.gametitle = gametitle;
        }

        public String getEntname() {
            return entname;
        }

        public void setEntname(String entname) {
            this.entname = entname;
        }

        public String getGivenrate() {
            return givenrate;
        }

        public void setGivenrate(String givenrate) {
            this.givenrate = givenrate;
        }

        public String getRatedate() {
            return ratedate;
        }

        public void setRatedate(String ratedate) {
            this.ratedate = ratedate;
        }

        public String getHoperate() {
            return hoperate;
        }

        public void setHoperate(String hoperate) {
            this.hoperate = hoperate;
        }

        public String getRateno() {
            return rateno;
        }

        public void setRateno(String rateno) {
            this.rateno = rateno;
        }
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

        ArrayList<itemModel> itemModels;
        public CustomAdapter(ArrayList<itemModel> itemModels) {this.itemModels = itemModels;}

        @Override
        public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list, viewGroup, false);
            return new viewHolder(view);
        }
        @Override
        public void onBindViewHolder(viewHolder viewHolder, final int position) {
            viewHolder.gmET.setText(itemModels.get(position).getGametitle());
            viewHolder.entET.setText(itemModels.get(position).getEntname());
            viewHolder.hopeET.setText(itemModels.get(position).getHoperate());
            viewHolder.givenET.setText(itemModels.get(position).getGivenrate());
            viewHolder.dateET.setText(itemModels.get(position).getRatedate());
            viewHolder.searchet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchmem = itemModels.get(position).getGametitle();
                    Intent intent2 = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent2.putExtra(SearchManager.QUERY, searchmem);
                    if(intent2.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent2);
                    }
                }
            });
            final viewHolder viewHolder1 = viewHolder;
        }
        @Override
        public int getItemCount() {
            return itemModels.size();
        }

        class viewHolder extends RecyclerView.ViewHolder {
            TextView gmET;
            TextView entET;
            TextView hopeET;
            TextView givenET;
            TextView dateET;
            Button searchet;

            public viewHolder(View itemView) {
                super(itemView);
                this.gmET = (TextView) itemView.findViewById(R.id.title);
                this.entET = (TextView) itemView.findViewById(R.id.entain);
                this.hopeET = (TextView) itemView.findViewById(R.id.hope);
                this.givenET = (TextView) itemView.findViewById(R.id.given);
                this.dateET = (TextView) itemView.findViewById(R.id.date);
                this.searchet = (Button) itemView.findViewById(R.id.search);
            }
        }
    }
        public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.viewHolder> {

            ArrayList<itemModel> itemModels;
            public CustomAdapter2(ArrayList<itemModel> itemModels) {this.itemModels = itemModels;}

            @Override
            public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list, viewGroup, false);
                return new viewHolder(view);
            }
            @Override
            public void onBindViewHolder(viewHolder viewHolder, final int position) {
                viewHolder.gmET.setText(itemModels.get(position).getGametitle());
                viewHolder.entET.setText(itemModels.get(position).getEntname());
                viewHolder.hopeET.setText(itemModels.get(position).getHoperate());
                viewHolder.givenET.setText(itemModels.get(position).getGivenrate());
                viewHolder.dateET.setText(itemModels.get(position).getRatedate());
                viewHolder.searchet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String searchmem = itemModels.get(position).getGametitle();
                        Intent intent2 = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent2.putExtra(SearchManager.QUERY, searchmem);
                        if(intent2.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent2);
                        }
                    }
                });
                final viewHolder viewHolder1 = viewHolder;
            }
            @Override
            public int getItemCount() {
                return itemModels.size();
            }

            class viewHolder extends RecyclerView.ViewHolder {
                TextView gmET;
                TextView entET;
                TextView hopeET;
                TextView givenET;
                TextView dateET;
                Button searchet;

                public viewHolder(View itemView) {
                    super(itemView);
                    this.gmET = (TextView) itemView.findViewById(R.id.title);
                    this.entET = (TextView) itemView.findViewById(R.id.entain);
                    this.hopeET = (TextView) itemView.findViewById(R.id.hope);
                    this.givenET = (TextView) itemView.findViewById(R.id.given);
                    this.dateET = (TextView) itemView.findViewById(R.id.date);
                    this.searchet = (Button) itemView.findViewById(R.id.search);
                }
            }
        }
}