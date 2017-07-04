package com.swordzpp.downP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 总的作品数没有任何分类 只按照页面序号
 * @author Administrator
 *
 */
public class DownByPageThread extends Thread{
	
	private int page;
	
	public DownByPageThread(int page){
		
		this.page = page;
	}

	public DownByPageThread(int page,int currentPage){
	
		
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		AddBookInfoByPage();
//		if(page % 11 == 0){
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
//	bookAttritues.put("bookName", bookName);
//	bookAttritues.put("author", author);
//	bookAttritues.put("bookType", bookType);
//	bookAttritues.put("bookSubType", bookSubType);
//	bookAttritues.put("bookStatus", bookStatus);
//	bookAttritues.put("wordCount", wordCount);
	private void AddBookInfoByPage(){
		Document doc = PareseQianHtml.getDocumentbyPage(page);
		// System.out.println(doc.html());
		Elements eles = PareseQianHtml.getBookIntro(doc);
		
		List<HashMap<String,String>> books = PareseQianHtml.getConcreteBookInfo(eles);
		for(int i = 0;i<books.size();i++){
			String bookName = books.get(i).get("bookName");
			String author = books.get(i).get("author");
			String bookType = books.get(i).get("bookType");
			String bookSubType = books.get(i).get("bookSubType");
			String bookStatus = books.get(i).get("bookStatus");
			String wordCount = books.get(i).get("wordCount");
			
		String sql ="INSERT into  qi_dian_book(book_name,author,book_type,book_sub_type,book_status,word_count)"
				+"values('"+bookName+"','"+author+"','"+bookType+"','"+bookSubType+"','"+bookStatus+"','"+wordCount+"')";
		SaveSingleBookInfo saveInfo = new SaveSingleBookInfo();
		saveInfo.saveBookInfo(sql);
		}
	}
	
//	public static void main(String[] args) {
//		Thread t = new Thread(new DownByPageThread(2));
//		long starTime = System.currentTimeMillis();
//		t.start();
//
//		long endTime = System.currentTimeMillis();
//		long Time = endTime - starTime;
//		System.out.println("执行耗时 : " + Time + " 毫秒 ");
//		System.out.println("执行耗时 : " + Time / 1000f + " 秒 ");
//	}
}
