package mz.ciuem.sipma.service.impl;

import org.springframework.stereotype.Service;

import mz.ciuem.sipma.entity.Comment;
import mz.ciuem.sipma.service.CommentService;

@Service("commentService")
public class CommentServiceImpl extends GenericServiceImpl<Comment> implements CommentService{

}
