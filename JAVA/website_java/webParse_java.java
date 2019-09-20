import java.io.IOException;


import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class web_parse2 {

	public static void main(String[] args) {
		
		Document doc;
		try {
			doc = Jsoup.connect("https://vnexpress.net").get();
			// option 1
//			Elements hinh = doc.select("body > section.featured.container.clearfix > article > h1 > a");		
//			for(Element item : hinh) {
//				String href = item.attr("href");
//				System.out.println(href);
//			}
			
			//option 2
//			Element hinh = doc.select("body > section.featured.container.clearfix > article > h1 > a").first();
//			System.out.println(hinh.attr("href"));
		
			//option 3
//			 String hinh3 = doc.select("body > section.featured.container.clearfix > article > h1 > a").first().attr("href");
//			 System.out.println(hinh3);
		Elements hinh = doc.select("")	;
		for(Element image: hinh)
		{
			System.out.println("\nsrc: " + image.attr("src"));

            System.out.println("height: " + image.attr("height"));

            System.out.println("width: " + image.attr("width"));

            System.out.println("alt: " + image.attr("alt"));
		}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
