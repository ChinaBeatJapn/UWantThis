package com.swordzpp.downP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PareseQianHtml {

	/**
	 * 相应的cookies内容
	 * 
	 * @return
	 */
	private static HashMap<String, String> getTheWebsiteCookies() {

		HashMap<String, String> cookies = new HashMap<String, String>();
		// 搜索当前页面，此为所有作品的信息
		cookies.put("e2", "");
		cookies.put("nb", "2");
		cookies.put("ns", "2");
		cookies.put("nread", "2");
		cookies.put("hiijack", "0");
		cookies.put("RK", "l5PnxsoOai");
		cookies.put("pgv_pvi", "8508093440");
		cookies.put("pt2gguin", "o1318791989");
		cookies.put("ptui_loginuin", "1318791989");
		cookies.put("newstatisticUUID", "1499157345_1277153322");
		cookies.put("_csrfToken", "5G7HP4K2HETVJuf702Uoc61DXc31nA8i4hMDxheY");
		cookies.put("ptcz", "7a2bc561efcdd79a395f6aa7e2783d88ee6b9de51f1f31b0810432095e0a79e0");
		cookies.put("e1", "%7B%22pid%22%3A%22qd_p_qidian%22%2C%22eid%22%3A%22qd_A15%22%2C%22l1%22%3A3%7D");

		return cookies;
	}

	public static Document getDocumentbyPage(int page) {
		// User-Agent:Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML,
		// like Gecko) Chrome/59.0.3071.115 Safari/537.36
		Document doc = null;
		try {
			doc = Jsoup
					.connect("http://a.qidian.com/?size=-1&sign=-1&tag=-1&chanId=-1&subCateId=-1&orderId=&update=-1"
							+ "&page=" + page + "&month=-1&style=1&action=-1&vip=-1")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36")
					.cookies(getTheWebsiteCookies()).timeout(3000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}

	// 得到书籍介绍div
	public static Elements getBookIntro(Document doc) {
		// Elements eles = doc.select("book-mid-info");
		
		Elements eles = doc.getElementsByClass("book-mid-info");
		return eles;

	}

	public static List<HashMap<String, String>> getConcreteBookInfo(Elements eles) {
		List<HashMap<String, String>> bookInfoList = new ArrayList<>();
		
		System.out.println(eles.size());
		for (Element bookEle : eles) {
			HashMap<String,String> bookAttritues= new HashMap<>();
			
			String bookName = bookEle.select("h4").select("a").text();// bookName
			String author = bookEle.select("p").select("a").eq(0).text(); // author
			String bookType = bookEle.select("p").select("a").eq(1).text(); // type
			String bookSubType = bookEle.select("p").select("a").eq(2).text(); // subType
			String bookStatus = bookEle.select("p").select("span").eq(0).text(); // bookStatus
			String wordCount = bookEle.select("p").select("span").eq(1).text(); // wordCount
			
			//addBookAttritues
			bookAttritues.put("bookName", bookName);
			bookAttritues.put("author", author);
			bookAttritues.put("bookType", bookType);
			bookAttritues.put("bookSubType", bookSubType);
			bookAttritues.put("bookStatus", bookStatus);
			bookAttritues.put("wordCount", wordCount);
			bookInfoList.add(bookAttritues);
			System.out.println("name is " + bookName + ",author:" + author + " " + bookType + " " + bookSubType + " "
					+ bookStatus + " " + wordCount);
		}

		return bookInfoList;
	}

}
