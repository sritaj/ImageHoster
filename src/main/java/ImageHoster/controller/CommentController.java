package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageTitle") String title, @PathVariable("imageId") Integer imageId, @RequestParam("comment") String userComment, Comment comment, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
        User user = (User) session.getAttribute("loggeduser");
        comment.setUser(user);
        comment.setText(userComment);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(imageService.getImage(imageId));
        commentService.createComment(comment);
        redirectAttributes.addAttribute("id", imageId).addFlashAttribute("id", imageId);
        redirectAttributes.addAttribute("title", title).addFlashAttribute("title", title);

        return "redirect:/images/{id}/{title}";
    }
}
