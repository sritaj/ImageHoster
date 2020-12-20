package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /**
     * This method calls createComment method of CommentRepository to persist a new comment.
     * @param newComment - New comment to be persisted.
     * @return - Persisted comment object.
     */
    public Comment createComment(Comment newComment) {
        return commentRepository.createComment(newComment);
    }

}
