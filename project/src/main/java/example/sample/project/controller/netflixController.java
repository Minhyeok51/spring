package example.sample.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import example.sample.project.domain.Movie;
import example.sample.project.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/netflix")
public class netflixController {
	private final MovieRepository movieRepository = MovieRepository.getInstance();
	@GetMapping
	public String netflix() {
		return "movie/index";
	}
	@GetMapping("/home")
	public String home(Model model) {
		Movie movie1 = new Movie("공조");
		movieRepository.insert(movie1);
		Movie movie2 = new Movie("아바타");
		movieRepository.insert(movie2);
		Movie movie3 = new Movie("올빼미");
		movieRepository.insert(movie3);
		
		List<Movie> movieList = new ArrayList<Movie>();
		movieList.add(movie1);
		movieList.add(movie2);
		movieList.add(movie3);
		
		
		model.addAttribute("movieList", movieList);
		return "movie/home";
	}
//	@GetMapping("/{movieNum}")
//	public String food(Model model, @PathVariable("movieNum") int movieNum) {
//		FoodItem foodItem = foodRepository.selectById(movieNum);
//		model.addAttribute("food", foodItem);
//		
//		return "foods/food";
//	}
	@GetMapping("/movieInfo/{movieNum}")
	public String movieInfo(@ModelAttribute Movie movieItem,Model model,
			@PathVariable("movieNum") int movieNum) {
		log.info("번호: " + movieNum);
		Movie movie = movieRepository.selectById(movieNum);
		model.addAttribute("movie",movie);
		return "movie/movieInfo";
	}
}
