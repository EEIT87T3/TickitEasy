package post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import post.bean.CommentBean;
import post.bean.PostBean;
import post.service.CommentService;
import post.service.PostService;

@Controller
@RequestMapping("/post/*")

public class PostController {
	
	@Autowired
	private PostService postService ;
	@Autowired
	private CommentService commentService ;
	
	//新增貼文
	@GetMapping("/showInsertForm")
	    public String showInsertForm() {
	        return "post/insertpost";  // 返回 insertpost.jsp 
	    }
	//取得所有貼文
 	@GetMapping("/findAll")
 	public String findAll(Model model) {
 		List<PostBean> posts = postService.findAll();
 		model.addAttribute("posts",posts);
 		
 		return "post/PostList";	
		
	}
 	//根據貼文ID取得單筆貼文
 	@GetMapping("/findById")
 	public String findById(
 			@ModelAttribute PostBean post,
 			@RequestParam("postID")Integer postID,
 			Model model) {
 		
 		PostBean findPost = postService.findById(postID);
 	
 		model.addAttribute("post",findPost);
	    
 		List<CommentBean> findComments = commentService.findById(postID);
 		
 		model.addAttribute("comment", findComments);
 		return "post/post";	
 		
 	}
 	@PostMapping("/getUpdatePost")
 	public String getUpdatePost(@RequestParam("postID")Integer postID,Model model) {
 		

		PostBean foundPost = postService.findById(postID); 
		model.addAttribute("post", foundPost);
 		
 		return "post/UpdatePost";	
 		
 	}
 	//根據貼文主題取得多筆貼文
 	@GetMapping("/findByTheme")
 	public String findByTheme(
 			@RequestParam("themeID") Integer themeID,
 			Model model) {
 		
 		List<PostBean> posts = postService.findByTheme(themeID);
 		model.addAttribute("posts",posts);
 		if(themeID == 0) {
 			return "redirect:/post/findAll";
 		}
 		return "post/PostList";	
 		
 	}
 	//根據搜尋內容取得多筆貼文
 	@GetMapping("/findByEnter")
 	public String findByEnter(
 			@RequestParam("enter") String enter,
 			Model model) {
 		
 		List<PostBean> posts = postService.findByEnter(enter);
 		model.addAttribute("posts",posts);
 		
 		return "post/PostList";	
 		
 	}
 	//新增單筆貼文
 	@PostMapping("/insertPost")
 	public String insert(
 			@ModelAttribute PostBean post,
 			Model model) {
 		postService.insert(post);
 		
		PostBean insertpost = postService.findById(post.getPostID());
 		//不能直接redirect:/post/findById 因為抓不到postID
 		
 		model.addAttribute("post",insertpost);
 		//將更改過後的文章addAttribute
 		//"post"對應request.getAttribute("post");
 		
 		return "post/post";	
 		
 	}
 	//更新單筆貼文
 	@PostMapping("/updatePost")
 	public String update(
 			@ModelAttribute PostBean post,
 			@RequestParam("postID") Integer postID,
 			Model model) {
 		
 		postService.update(postID,post);
 		
 		PostBean updatepost = postService.findById(postID);
 		//不能直接redirect:/post/findById 因為抓不到postID
 		
 		model.addAttribute("post",updatepost);
 		//將更改過後的文章addAttribute
 		//"post"對應request.getAttribute("post");
 		
 		return "post/post";	
 		//post資料夾的post.jsp
 		
 	}
 	//刪除單筆貼文
 	@PostMapping("/deletePost")
 	public String delete(@RequestParam("postID") Integer postID) {
 		postService.delete(postID);
 		
 		return "redirect:/post/findAll";	
 	}
 	
}
