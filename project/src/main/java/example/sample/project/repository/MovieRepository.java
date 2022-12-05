package example.sample.project.repository;

import java.util.ArrayList;
import java.util.List;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.Movie;

public class MovieRepository {

	private static List<Movie> db = new ArrayList<>();
	private static int seq = 1;
	private static final MovieRepository instance = new MovieRepository();
	public static MovieRepository getInstance() {
		return instance;
	}

	private MovieRepository() {
		//	db = new ArrayList<>(); //new 할때마다 db 내용 날라가니까 그렇게 하지 못하게 하기 위해
	}
	public Movie insert(Movie movieItem) {
		movieItem.setMNum(seq++);
		db.add(movieItem);
		return movieItem;
	}
	public Movie selectById(int mNum) {
		for(Movie movieItem : db) {
			if(movieItem.getMNum() == mNum) {
				return movieItem;
			}
		}
		return null;
	}

	public List<Movie> selectAll() {
		return db;
	}

	public boolean update(int id, Movie movieItem) {
		boolean result = false;
		try {
			Movie targetMovieItem = selectById(id);
			targetMovieItem.setMName(movieItem.getMName());
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void deleteAll() {
		db.clear();
	}
}