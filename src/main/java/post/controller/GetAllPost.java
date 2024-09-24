package post.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import post.bean.PostBean;
import post.service.PostService;



//@Controller
//@RequestMapping("/posts/*")
//public class GetAllPost{
//	
//	@Autowired
//	private PostService postService ;
//	
// 	public GetAllPost() {
//	
//	}
//
// 	@PostMapping("/getallposts")
// 	public String allPost(Model model) {
// 		List<PostBean> posts = postService.findAll();
// 		model.addAttribute("posts",posts);
// 		
// 		return "PostList";	
//		
//	}
// 
//}
