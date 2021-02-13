package eg.kaboo.newsapp.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	public List<ArticlesItem> getArticles(){
		return articles;
	}

}